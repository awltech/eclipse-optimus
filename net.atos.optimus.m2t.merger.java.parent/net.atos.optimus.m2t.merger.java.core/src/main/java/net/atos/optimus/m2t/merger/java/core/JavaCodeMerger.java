/**
 * Optimus, framework for Model Transformation
 *
 * Copyright (C) 2013 Worldline or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 */
package net.atos.optimus.m2t.merger.java.core;

import static org.eclipse.jdt.core.dom.CompilationUnit.IMPORTS_PROPERTY;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.atos.optimus.common.tools.jdt.ASTParserFactory;
import net.atos.optimus.common.tools.jdt.JavaCodeHelper;
import net.atos.optimus.m2t.merger.java.core.internal.MergerLogger;
import net.atos.optimus.m2t.merger.java.core.internal.MergerLoggerMessages;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.TagElement;
import org.eclipse.jdt.core.dom.TextElement;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;
import org.eclipse.jface.text.Document;
import org.eclipse.text.edits.MalformedTreeException;

/**
 * Merge java sources.
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 */
@SuppressWarnings("unchecked")
public abstract class JavaCodeMerger {

	/**
	 * Constant used for @Generated comment argument
	 */
	public static final String GENERATED_COMMENT_ARGUMENT = "comments";

	/**
	 * This set contains the list of predefined annotations. This set is used to
	 * perform specific treatments on each annotation (e.g. Remove an annotation
	 * when it's needed)
	 */
	private Set<String> preDefinedAnnotations = new HashSet<String>(5);

	/**
	 * A merging strategy instance. This object is used to customize the merging
	 * process.
	 */
	private MergingStrategy ms;

	/**
	 * Empty constructor
	 */
	public JavaCodeMerger() {
	}

	/**
	 * Constructor with a special annotations set as single argument.
	 */
	public JavaCodeMerger(Set<String> pda) {
		super();

		if (pda != null) {
			preDefinedAnnotations = pda;
		}
	}

	/**
	 * Merge 2 Java contents and return the result of the merge operation as
	 * String
	 * 
	 * @param existingContent
	 *            The initial content
	 * @param generatedContent
	 *            Result of a generation process
	 * @return The merge operation result as String
	 * @throws JavaModelException
	 *             If an error occurs during merge operation
	 */
	public String merge(String existingContent, String generatedContent) throws JavaModelException {
		return merge("", existingContent, generatedContent);
	}

	/**
	 * Merge a CompilationUnit in packageName from this name with generated
	 * content.
	 * 
	 * @param compilationUnitName
	 *            The name of the CompilationUnit without java extension
	 * @param newContent
	 *            The content to store in this CompilationUnit
	 * @param packageFragment
	 *            The source package where this CompilationUnit must be store
	 * @return The merge operation result as String
	 * @throws JavaModelException
	 *             If an error occurs during merge operation
	 */
	public String merge(String compilationUnitName, String newContent, IPackageFragment packageFragment)
			throws JavaModelException {
		// Create ICompilationUnit object associated with initial source
		ICompilationUnit initialIcp = packageFragment.getCompilationUnit(compilationUnitName + ".java");

		// REFACTORING managment:
		// If a compilatioUnit is not found with the same name, we look for one
		// with the same UniqueId (only in same package)
		// boolean classRenamed = false;
		// if(initialIcp == null || !initialIcp.exists()) {
		// String uniqueId = getUniqueIdFromClassContent(newContent,
		// compilationUnitName);
		// initialIcp = findCorrespondingCUByUniqueId(packageFragment,
		// uniqueId);
		// if (initialIcp != null && initialIcp.exists()) {
		// classRenamed = true;
		// }
		// }
		if (initialIcp != null && initialIcp.exists()) {
			// Merge initial source and generated source
			newContent = merge(initialIcp.getJavaProject().getElementName(), initialIcp.getSource(), newContent);
		}
		// if(classRenamed) {
		// try {
		// initialIcp.getResource().delete(true, null);
		// } catch (CoreException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		return newContent;
	}

	/**
	 * 
	 * 
	 * @param project
	 * @param existingContent
	 * @param generatedContent
	 * @return
	 * @throws JavaModelException
	 */
	public String merge(String project, String existingContent, String generatedContent) throws JavaModelException {
		if (existingContent == null || existingContent.length() == 0) {
			return generatedContent;
		}

		if (generatedContent == null || generatedContent.length() == 0) {
			return existingContent;
		}

		// Create a Document object for the existing content
		Document existingDocument = new Document(existingContent);

		ASTParser existingContentParser = ASTParserFactory.INSTANCE.newParser();
		existingContentParser.setSource(existingDocument.get().toCharArray());
		existingContentParser.setCompilerOptions(JavaCodeHelper.compilerOptions);
		CompilationUnit existingCU = (CompilationUnit) existingContentParser.createAST(null);

		// Create an ASTRewrite object used to update the existing content
		ASTRewrite resultRewriter = ASTRewrite.create(existingCU.getAST());

		// Create a Document object for generated content
		Document generatedDocument = new Document(generatedContent);

		// Create an AST parser for generated content (use JSL3 to support JDK
		// 1.5)
		ASTParser generatedContentParser = ASTParserFactory.INSTANCE.newParser();
		generatedContentParser.setSource(generatedDocument.get().toCharArray());
		generatedContentParser.setCompilerOptions(JavaCodeHelper.compilerOptions);
		CompilationUnit generatedContentCU = (CompilationUnit) generatedContentParser.createAST(null);

		String pack = "";

		if (existingCU.getPackage() != null) {
			pack = existingCU.getPackage().getName().getFullyQualifiedName();
		}

		try {

			/* Create a merger logger instance */
			if (MergerLogger.enabled())
				MergerLogger.log(MergerLoggerMessages.MERGER_START.value(pack, JavaCodeHelper.getMainType(existingCU)
						.getName().getFullyQualifiedName(), project));

			// Merge imports
			mergeImports(existingCU, generatedContentCU, resultRewriter);

			// For each type defined in the generated source...
			for (AbstractTypeDeclaration generatedType : (List<AbstractTypeDeclaration>) generatedContentCU.types()) {
				/*
				 * ... call the merge process
				 */
				mergeTwoFragments(existingCU, JavaCodeHelper.getTypeDeclaration(existingCU,
						((AbstractTypeDeclaration) existingCU.types().get(0)).getName().getFullyQualifiedName()),
				// TODO: find a better way!
						generatedType, generatedDocument, resultRewriter);
			}

			// For each type defined in the existing source...
			for (AbstractTypeDeclaration td : (List<AbstractTypeDeclaration>) existingCU.types()) {
				/*
				 * ... call the merge process only if the type in the generated
				 * type doesn't exist
				 */

				/* The generatedType is a class or an interface */
				if (JavaCodeHelper.getType(generatedContentCU, JavaCodeHelper.getTypeName(td)) == null) {
					mergeTwoFragments(existingCU, td, null, generatedDocument, resultRewriter);
				}
			}

			// Execute merge operations on existing content
			try {
				resultRewriter.rewriteAST(existingDocument, null).apply(existingDocument);
			} catch (MalformedTreeException mte) {
				throw new JavaModelException(mte, 0);
			} catch (org.eclipse.jface.text.BadLocationException ble) {
				throw new JavaModelException(ble, 0);
			}

			// Return result of the merging process as String object
			return existingDocument.get();
		} finally {
			if (MergerLogger.enabled())
				MergerLogger.log(MergerLoggerMessages.MERGER_END.value(pack, JavaCodeHelper.getMainType(existingCU)
						.getName().getFullyQualifiedName(), project));
		}
	}

	/**
	 * 
	 * @param classContentString
	 * @param typeNameInContent
	 * @return
	 */
	/*
	 * private String getUniqueIdFromClassContent(String classContentString,
	 * String typeNameInContent) { // Create a Document object for generated
	 * content Document classDocument = new Document(classContentString);
	 * 
	 * // Create an AST parser for generated content (use JSL3 to support JDK //
	 * 1.5) ASTParser generatedContentParser = ASTParser.newParser(AST.JLS3);
	 * generatedContentParser.setSource(classDocument.get().toCharArray());
	 * generatedContentParser.setCompilerOptions(compilerOptions);
	 * CompilationUnit classContentCU = (CompilationUnit)
	 * generatedContentParser.createAST(null); return
	 * ASTHelper.getUniqueIdFromGeneratedAnnotation
	 * (JavaCodeHelper.getType(classContentCU, typeNameInContent)); }
	 * 
	 * private ICompilationUnit findCorrespondingCUByUniqueId(IPackageFragment
	 * packageFragmentToSearchIn, String uniqueIdTofind){
	 * if(packageFragmentToSearchIn != null && uniqueIdTofind != null &&
	 * uniqueIdTofind.length() > 0) { try { ICompilationUnit[] compilationUnits
	 * = packageFragmentToSearchIn.getCompilationUnits(); for(ICompilationUnit
	 * compilationUnitsInSamePackage : compilationUnits) { IType
	 * compilationUnitType = compilationUnitsInSamePackage.getType(
	 * compilationUnitsInSamePackage.getElementName().replace(".java", ""));
	 * String uniqueIdFromComment = getUniqueId(compilationUnitType);
	 * if(uniqueIdFromComment != null &&
	 * uniqueIdFromComment.equals(uniqueIdTofind)) { return
	 * compilationUnitsInSamePackage; } } } catch (JavaModelException jme) {
	 * Activator.getDefault().logError("Error trying to find UniqueId (" +
	 * uniqueIdTofind + ") in Classes from package: " +
	 * packageFragmentToSearchIn.getElementName() , jme); } } return null; }
	 * 
	 * private String getUniqueId(IType compilationUnitType) throws
	 * JavaModelException { if(compilationUnitType != null &&
	 * compilationUnitType.exists()) { IAnnotation generatedAnnotation =
	 * compilationUnitType.getAnnotation("Generated"); if(generatedAnnotation !=
	 * null && generatedAnnotation.exists()) { IMemberValuePair[] valuePairs =
	 * generatedAnnotation.getMemberValuePairs(); for(IMemberValuePair valuePair
	 * : valuePairs) { if(valuePair.getMemberName().equals("comments") &&
	 * valuePair.getValueKind() == IMemberValuePair.K_STRING) { String comment =
	 * (String)valuePair.getValue(); return
	 * GeneratedAnnotationHelper.getUniqueIdFromComment(comment); } } } } return
	 * null; }
	 */

	/**
	 * Merge imports
	 */
	private void mergeImports(CompilationUnit initialContentCU, CompilationUnit generatedContentCU,
			ASTRewrite resultRewriter) {
		// If at least one of these three parameters is null, we cannot process
		// imports.
		if (initialContentCU == null || generatedContentCU == null || resultRewriter == null) {
			return;
		}

		List<String> initialImportsName = null;

		// Retrieve the list of imports from initial source.
		List<?> initialImports = initialContentCU.imports();

		// Create an imports list in String format.
		if (initialImports != null) {
			initialImportsName = new ArrayList<String>(5);

			for (Object o : initialImports) {
				initialImportsName.add(((ImportDeclaration) o).getName().getFullyQualifiedName());
			}
		}

		/*
		 * Get a ListRewrite object used to enhance existing imports with new
		 * generated imports
		 */
		ListRewrite lw = resultRewriter.getListRewrite(initialContentCU, IMPORTS_PROPERTY);

		// For each import of the generated source
		for (Object o : generatedContentCU.imports()) {
			ImportDeclaration id = (ImportDeclaration) o;

			if (!initialImportsName.contains(id.getName().getFullyQualifiedName())) {
				// This import is containing in the generated code but not in
				// the existing code => Add this import in the merge result.

				if (MergerLogger.enabled())
					MergerLogger.log(MergerLoggerMessages.MERGER_ADDIMPORT.value(id.getName().getFullyQualifiedName()));

				lw.insertLast(id, null);
			}
		}
	}

	/**
	 * Perform two fragments merging. To do this, take an
	 * {@code existingFragment}, take a {@code generatedFragment} and enhance
	 * {@code astr} ASTRewrite instance. If a fragment must be inserted, the
	 * complete fragment is inserted. If a fragment must be updated, only the
	 * generated fragment is updated (for exemple, with a type declaration
	 * fragment, only the declaration is updated, not fields and methods). If a
	 * fragment must be deleted, the complete fragment is deleted.
	 * 
	 * @param parent
	 *            The fragment parent
	 * @param existingFragment
	 *            An existing fragment
	 * @param generatedFragment
	 *            A generated fragment
	 * @param astr
	 *            An ASTRewrite instance used to enhance the merge result
	 */
	void mergeTwoFragments(ASTNode parent, BodyDeclaration existingFragment, BodyDeclaration generatedFragment,
			Document generatedDoc, ASTRewrite astr) {
		if (parent != null) {

			if (existingFragment == null && generatedFragment == null) {
				/*
				 * Case 1 : Nothing in existing code, nothing in generated code
				 * => Nothing to do.
				 */
				return;
			}

			/* Get the right merger instance according to the type to merge */
			FragmentMerger fm = getMerger(existingFragment, generatedFragment, generatedDoc, astr);

			if (existingFragment == null) {
				// The element doesn't exist in existing code.

				if (isGenerated(generatedFragment)) {
					/*
					 * Case 2 : Nothing in existing code, element marked as
					 * generated in generated code => Copy generated element in
					 * existing code.
					 */

					if (MergerLogger.enabled())
						MergerLogger.log(MergerLoggerMessages.MERGER_DOMERGE_GENERATED.value(
								JavaCodeHelper.getName(generatedFragment),
								JavaCodeHelper.getDescription(generatedFragment)));

					fm.insert(parent, generatedFragment);
				} else {
					/*
					 * Case 3 : Nothing in existing code, element marked as not
					 * generated in generated code => Copy not generated element
					 * in existing code.
					 */

					if (MergerLogger.enabled())
						MergerLogger.log(MergerLoggerMessages.MERGER_DOMERGE_NOTGENERATED.value(
								JavaCodeHelper.getName(generatedFragment),
								JavaCodeHelper.getDescription(generatedFragment)));

					fm.insert(parent, generatedFragment);
				}

				/*
				 * These two previous steps perform the same operation. But to
				 * understand the complete merging process, if and else has been
				 * kept.
				 */
			}

			if (generatedFragment == null) {
				// The element doesn't exist in generated code

				if (isGenerated(existingFragment)) {
					/*
					 * Case 4 : Element marked as generated in existing code,
					 * nothing in generated code => Remove element from existing
					 * code.
					 */

					if (MergerLogger.enabled())
						MergerLogger.log(MergerLoggerMessages.MERGER_DOMERGE_EMPTYGENERATED.value(
								JavaCodeHelper.getName(existingFragment),
								JavaCodeHelper.getDescription(existingFragment)));

					fm.remove(existingFragment);
				} else {
					/*
					 * Case 5 : Element marked as not generated in existing
					 * code, nothing in generated code => Nothing to do.
					 */

					if (MergerLogger.enabled())
						MergerLogger.log(MergerLoggerMessages.MERGER_NOMERGE.value(
								JavaCodeHelper.getName(existingFragment),
								JavaCodeHelper.getDescription(existingFragment)));

				}
			}

			// The element exist in existing code and generated code.
			if (existingFragment != null && generatedFragment != null) {
				if (isGenerated(existingFragment)) {
					// The exisiting element is marked as generated.

					if (isGenerated(generatedFragment)) {
						/*
						 * Case 6 : Element marked as generated in existing
						 * code, element marked as generated in generated code
						 * => Merge existing element with generated element.
						 */

						if (MergerLogger.enabled())
							MergerLogger.log(MergerLoggerMessages.MERGER_DOMERGE_CASE6.value(
									JavaCodeHelper.getName(existingFragment),
									JavaCodeHelper.getDescription(existingFragment)));

						fm.merge(existingFragment, generatedFragment, preDefinedAnnotations);
					} else {
						/*
						 * Case 7 : Element marked as generated in existing
						 * code, element marked as not generated in generated
						 * code => Nothing to do. Remark : Very special case.
						 */

						if (MergerLogger.enabled())
							MergerLogger.log(MergerLoggerMessages.MERGER_DOMERGE_CASE7.value(
									JavaCodeHelper.getName(existingFragment),
									JavaCodeHelper.getDescription(existingFragment)));

					}
				} else {
					// The exisiting element is marked as not generated.

					if (isGenerated(generatedFragment)) {
						/*
						 * Case 8 : Element marked as not generated in existing
						 * code, element marked as generated in generated code
						 * => Nothing to do.
						 */

						if (MergerLogger.enabled())
							MergerLogger.log(MergerLoggerMessages.MERGER_DOMERGE_CASE8.value(
									JavaCodeHelper.getName(existingFragment),
									JavaCodeHelper.getDescription(existingFragment)));

					} else {
						/*
						 * Case 9 : Element marked as not generated in existing
						 * code, element marked as not generated in generated
						 * code => Nothing to do.
						 */

						if (MergerLogger.enabled())
							MergerLogger.log(MergerLoggerMessages.MERGER_DOMERGE_CASE9.value(
									JavaCodeHelper.getName(existingFragment),
									JavaCodeHelper.getDescription(existingFragment)));

					}
				}

				// Javadoc is merged
				mergeJavadoc(existingFragment, generatedFragment, astr);
			}

			/* Merge sub fragments if needed */
			fm.mergeSubFragments(existingFragment, generatedFragment, generatedDoc);

		}
	}

	/**
	 * Merge javadoc comment from existingFragment and generatedFragment to an
	 * AST Rewrite object instance. This method is call only when
	 * {@code existingFragment} is marked as generated and
	 * {@code generatedFragment} exist.
	 * 
	 * @param existingFragment
	 *            The existing fragment
	 * @param generatedFragment
	 *            The generated fragment
	 * @param astr
	 *            The ASTRewrite object instance containing the merge result
	 */
	protected void mergeJavadoc(BodyDeclaration existingFragment, BodyDeclaration generatedFragment, ASTRewrite astr) {
		boolean existingCommentIsGenerated = false;
		boolean generatedCommentIsGenerated = false;
		boolean existingCommentFound = true;
		boolean generatedCommentFound = true;

		try {
			existingCommentIsGenerated = isJavadocCommentGenerated(existingFragment.getJavadoc());
		} catch (IllegalArgumentException iae) {
			existingCommentFound = false;
		}

		try {
			generatedCommentIsGenerated = isJavadocCommentGenerated(generatedFragment.getJavadoc());
		} catch (IllegalArgumentException iae) {
			generatedCommentFound = false;
		}

		if (existingCommentIsGenerated) {
			if (!generatedCommentFound) {
				if (MergerLogger.enabled())
					MergerLogger.log(MergerLoggerMessages.MERGER_REMOVE_JAVADOC.value(
							JavaCodeHelper.getName(existingFragment), JavaCodeHelper.getDescription(existingFragment)));

				astr.remove(existingFragment.getJavadoc(), null);
			} else if (generatedCommentIsGenerated) {
				if (MergerLogger.enabled())
					MergerLogger.log(MergerLoggerMessages.MERGER_REPLACE_JAVADOC.value(
							JavaCodeHelper.getName(existingFragment), JavaCodeHelper.getName(generatedFragment)));

				astr.replace(existingFragment.getJavadoc(), generatedFragment.getJavadoc(), null);
			}
		} else if (!existingCommentFound && generatedCommentIsGenerated) {
			if (MergerLogger.enabled())
				MergerLogger
						.log(MergerLoggerMessages.MERGER_ADD_JAVADOC.value(JavaCodeHelper.getName(existingFragment)));
			astr.set(existingFragment, existingFragment.getJavadocProperty(), generatedFragment.getJavadoc(), null);
		}
	}

	/**
	 * Return the good merge instance according to the type of the fragment to
	 * merge.
	 * 
	 * @param existing
	 *            The first fragment to merge. This parameter is used to know
	 *            wich type must be merged.
	 * @param generated
	 *            The second fragment to merge. This parameter is used only if
	 *            existing is null.
	 * 
	 */
	protected FragmentMerger getMerger(BodyDeclaration existing, BodyDeclaration generated, Document generatedDoc,
			ASTRewrite astr) {
		BodyDeclaration bd = (existing == null) ? generated : existing;

		BodyDeclarationMerger bdm = null;

		/*
		 * Check the bd type and return the right merger instance according to
		 * this type.
		 */
		if (bd instanceof FieldDeclaration) {
			// It's a field, return a FieldMerger
			bdm = new FieldDeclarationMerger(astr);
		} else if (bd instanceof MethodDeclaration) {
			// It's a method, return a MethodMerger
			bdm = new MethodDeclarationMerger(astr, generatedDoc);
		} else if (bd instanceof TypeDeclaration) {
			// It's a type, return a TypeChecker taken this as parameter
			bdm = new TypeMerger(this, astr);
		} else if (bd instanceof EnumDeclaration) {
			// It's an enumeration, return a EnumMerger taken this as parameter
			bdm = new EnumMerger(this, astr);
		} else if (bd instanceof EnumConstantDeclaration) {
			// It's an enumeration, return a EnumMerger taken this as parameter
			bdm = new EnumConstantMerger(astr);
		}

		if (ms != null) {
			bdm.setMergingStrategy(ms);
		}

		return bdm;
	}

	/**
	 * Return true if the BodyDeclaration object has been generated. Generated
	 * informations is annotated with.
	 * 
	 * @param bd
	 *            BodyDeclaration used as input test
	 * @return true if the BodyDeclaration object has been generated, false
	 *         otherwise
	 */
	protected boolean isGenerated(BodyDeclaration bd) {
		boolean isGenerated = false;
		List<?> modifiers = bd.modifiers();

		// Test if this BodyDeclaration contains modifiers
		if (modifiers != null) {
			Iterator<?> modifiersIterator = bd.modifiers().iterator();

			// For each modifier, search for @Generated(<GENERATOR_NAME>) marker
			// annotation
			while ((!isGenerated) && modifiersIterator.hasNext()) {
				IExtendedModifier modifier = (IExtendedModifier) modifiersIterator.next();

				if (modifier.isAnnotation()) {

					Annotation a = (Annotation) modifier;
					String annotationType = a.getTypeName().toString();

					if (annotationType.equals(JavaCodeHelper.GENERATED_CLASSNAME)
							|| annotationType.equals(JavaCodeHelper.GENERATED_SIMPLECLASSNAME)) {
						if (a.isSingleMemberAnnotation()) {
							isGenerated = ((SingleMemberAnnotation) a).getValue().toString()
									.contains(getGeneratorName());
						} else if (((Annotation) modifier).isNormalAnnotation()) {
							NormalAnnotation na = (NormalAnnotation) a;

							List<MemberValuePair> values = na.values();

							for (int inc = 0; inc < values.size() && !isGenerated; inc++) {
								MemberValuePair mvp = values.get(inc);

								if (mvp != null && mvp.getValue() != null) {
									isGenerated = mvp.getValue().toString().contains(getGeneratorName());
								}
							}
						}
					}
				}
			}
		}

		return isGenerated;
	}

	/**
	 * Return if a javadoc comment is generated or not
	 */
	private boolean isJavadocCommentGenerated(Javadoc jd) {
		if (jd == null) {
			throw new IllegalArgumentException();
		}

		boolean found = false;
		List<TagElement> tags = jd.tags();

		if (tags != null) {
			TagElement te = null;

			for (int inc = 0; inc < tags.size() && !found; inc++) {
				te = tags.get(inc);

				if ("@generated".equals(te.getTagName())) {
					List<ASTNode> fragments = te.fragments();

					if (fragments != null && fragments.size() == 1) {
						if (fragments.get(0) instanceof TextElement) {
							String commentText = ((TextElement) fragments.get(0)).getText().trim();
							found = commentText != null && commentText.startsWith(getGeneratorName());
						}
					}
				}
			}
		} else {
			return false;
		}

		return found;
	}

	/**
	 * Return the generator name. The generator name is the value used by the
	 * code merger to know if the merge must be done or not.
	 * 
	 * @return The generator name used by the merger to identify of the merge
	 *         must be done or not
	 */
	protected abstract String getGeneratorName();

	/**
	 * Set the merging strategy. This method must be used to customize the
	 * merging process.
	 * 
	 * @param ms
	 *            A Merging Strategy instance
	 */
	public void setMergingStrategy(MergingStrategy ms) {
		this.ms = ms;
	}

}