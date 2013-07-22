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

import static org.eclipse.jdt.core.dom.MethodDeclaration.BODY_PROPERTY;
import static org.eclipse.jdt.core.dom.MethodDeclaration.JAVADOC_PROPERTY;
import static org.eclipse.jdt.core.dom.MethodDeclaration.MODIFIERS2_PROPERTY;
import static org.eclipse.jdt.core.dom.MethodDeclaration.PARAMETERS_PROPERTY;
import static org.eclipse.jdt.core.dom.MethodDeclaration.THROWN_EXCEPTIONS_PROPERTY;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.atos.optimus.common.tools.jdt.ASTHelper;
import net.atos.optimus.common.tools.jdt.JavaCodeHelper;
import net.atos.optimus.m2t.merger.java.core.internal.Activator;
import net.atos.optimus.m2t.merger.java.core.internal.MergerLogger;
import net.atos.optimus.m2t.merger.java.core.internal.MergerLoggerMessages;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.ChildListPropertyDescriptor;
import org.eclipse.jdt.core.dom.ChildPropertyDescriptor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;


/**
 * Merge two MethodDeclaration instance. A MethodDeclaration is composed by a
 * javadoc, modifiers, parameters list and exceptions.
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 */
class MethodDeclarationMerger extends BodyDeclarationMerger {

	private final Document generated;

	/**
	 * Constructor
	 * 
	 * @param log
	 *            A Merger Logger instance
	 * @param astr
	 *            An ASTRewrite instance
	 * @param generated
	 *            A document containing generated code (used to merge method
	 *            bodies)
	 */
	public MethodDeclarationMerger(ASTRewrite astr, Document generated) {
		super(astr);
		this.generated = generated;
	}

	/**
	 * Returns a StringPlaceholder block containing the source code of a given
	 * block This is usefull to preserve comments of the original code. (these
	 * commments are not representated in the AST)
	 * 
	 * @param block
	 *            the node to get the source of
	 * @return a StringPlaceholder block containing the block's source code
	 */
	private Block createSourceCodePlaceholder(Block block) {
		// For Abstract method and interfaces methods
		if (block == null) {
			return null;
		}
		try {
			return (Block) astr.createStringPlaceholder(generated.get(block.getStartPosition(), block.getLength()),
					ASTNode.BLOCK);
		} catch (BadLocationException e) {
			if (MergerLogger.enabled())
				MergerLogger.log(MergerLoggerMessages.METHODDECL_ERRORSETBODY.value(e.toString()));
			Activator.getDefault().logError("An exception has been thrown", e);

			// We were not able to extract the original source code, return the
			// node back
			return block;
		}
	}

	@Override
	protected ChildPropertyDescriptor getJavadocPropertyDescriptor() {
		return JAVADOC_PROPERTY;
	}

	@Override
	protected ChildListPropertyDescriptor getModifiersPropertyDescriptor() {
		return MODIFIERS2_PROPERTY;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insert(ASTNode parent, BodyDeclaration originalNode) {
		super.insert(parent, originalNode);

		MethodDeclaration lastMethod = null;
		AbstractTypeDeclaration parentType = (AbstractTypeDeclaration) parent;

		/*
		 * Read all methods of the type declaration (used to insert this method
		 * at the right place)
		 */
		List<MethodDeclaration> existingMethods = JavaCodeHelper.getTypedChildren(parentType, MethodDeclaration.class);

		// Save the last method if a method already exist
		if (existingMethods.size() > 0) {
			lastMethod = existingMethods.get(existingMethods.size() - 1);
		}

		// We need to clone this node, to change its body
		MethodDeclaration methodToInsert = (MethodDeclaration) ASTNode.copySubtree(astr.getAST(), originalNode);

		// Set the method Body as a copy of the original (not parsed) body
		// This preserves comments in the generated method body
		methodToInsert.setBody(createSourceCodePlaceholder(methodToInsert.getBody()));

		// perform the rewrite
		ListRewrite lw = astr.getListRewrite(parentType,
				JavaCodeHelper.getBodyDeclarationProperty(parentType.getClass()));

		if (lastMethod == null) {
			// ... at the end of the type if this is the first method
			lw.insertLast(methodToInsert, null);
		} else {
			// ... after the previous method if a method already exist
			lw.insertAfter(methodToInsert, lastMethod, null);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void merge(BodyDeclaration existing, BodyDeclaration generated, Set<String> generatedAnnotations) {
		super.merge(existing, generated, generatedAnnotations);

		MethodDeclaration existingMethod = (MethodDeclaration) existing;
		MethodDeclaration generatedMethod = (MethodDeclaration) generated;

		int existingMethodBodyAnnotationHashCode = 0;
		int generatedMethodBodyAnnotationHashCode = 0;
		int existingMethodBodyCalculatedHashCode = 0;

		try {
			/*
			 * Try to read the hashcode of the existing method in the associated
			 * 
			 * @Generated annotation
			 */
			existingMethodBodyAnnotationHashCode = ASTHelper.getHashCodeFromGeneratedAnnotation(existingMethod);

			/*
			 * Test if the method body is not null before doing the hash code
			 * processing
			 */
			if (existingMethod.getBody() != null) {
				existingMethodBodyCalculatedHashCode = ASTHelper
						.methodBodyHashCode(existingMethod.getBody().toString());
			}

			if (existingMethodBodyAnnotationHashCode == existingMethodBodyCalculatedHashCode) {
				/*
				 * The calculated hashCode body of the existing method is equal
				 * to the hashCode read from @Generated of the method. The body
				 * has not been customized by the user
				 */

				/*
				 * Try to read the hashcode of the generated method in the
				 * associated @Generated annotation
				 */
				generatedMethodBodyAnnotationHashCode = ASTHelper.getHashCodeFromGeneratedAnnotation(generatedMethod);

				if (existingMethodBodyAnnotationHashCode != generatedMethodBodyAnnotationHashCode) {
					/*
					 * The generated method body and the existing method body
					 * are not the same. The existing method body must (and can)
					 * be updated
					 */
					replaceMethodBody(existingMethod, generatedMethod);
					replaceGeneratedAnnotation(existingMethod, generatedMethod);
				}
			}
		} catch (IllegalArgumentException iae) {
			/*
			 * If this excpetion is thrown, the associated annotation doesn't
			 * contain this information. In this case, copy the new method body
			 */
			replaceMethodBody(existingMethod, generatedMethod);
			replaceGeneratedAnnotation(existingMethod, generatedMethod);
		} catch (UnsupportedOperationException uoe) {
			// Nothing to do
		}
		// Updates the name in case of refactoring:
		if (MergerLogger.enabled())
			MergerLogger.log(MergerLoggerMessages.METHODDECL_RENAME.value(existingMethod.getName().getIdentifier(), generatedMethod
					.getName().getIdentifier()));
		astr.replace(existingMethod.getName(), generatedMethod.getName(), null);

		// Return type is replaced (test before if return types are not null)
		if (existingMethod.getReturnType2() != null && generatedMethod.getReturnType2() != null) {
			mergeTypes(existingMethod.getReturnType2(), generatedMethod.getReturnType2());
		}

		// Exceptions are merged
		mergeExceptions(existingMethod, generatedMethod);

		// Parameters from generated methods should become parameters for
		// existing method.
		if (existingMethod.parameters() != null && generatedMethod.parameters() != null) {
			if (MergerLogger.enabled())
				MergerLogger.log(MergerLoggerMessages.METHODDECL_REPLACEPARAMS.value(JavaCodeHelper.getName(existing),
						JavaCodeHelper.getDescription(existing)));
			if (MergerLogger.enabled())
				MergerLogger.log(getParametersLog(existingMethod, generatedMethod));
			ListRewrite lw = astr.getListRewrite(existingMethod, PARAMETERS_PROPERTY);
			for (SingleVariableDeclaration svd : (List<SingleVariableDeclaration>) existingMethod.parameters()) {
				lw.remove(svd, null);
			}
			for (SingleVariableDeclaration svd : (List<SingleVariableDeclaration>) generatedMethod.parameters()) {
				lw.insertLast(svd, null);
			}
		}
	}

	private String getParametersLog(MethodDeclaration existingMethod, MethodDeclaration generatedMethod) {
		StringBuffer sb = new StringBuffer("\t\tWas :");
		if (existingMethod.parameters() == null || existingMethod.parameters().size() == 0)
			sb.append("[none]");
		else
			for (Object param : existingMethod.parameters()) {
				sb.append("[").append(param).append("]");
			}
		sb.append(", now : ");
		if (generatedMethod.parameters() == null || generatedMethod.parameters().size() == 0)
			sb.append("[none]");
		else
			for (Object param : generatedMethod.parameters()) {
				sb.append("[").append(param).append("]");
			}
		return sb.toString();
	}

	/**
	 * Replace method body
	 */
	private void replaceMethodBody(MethodDeclaration existingMethod, MethodDeclaration generatedMethod) {
		Block generatedBody = generatedMethod.getBody();
		Block existingBody = existingMethod.getBody();

		if (generatedBody == null) {
			/* Nothing to do */
			return;
		}

		if (existingBody != null) {
			if (MergerLogger.enabled())
				MergerLogger.log(MergerLoggerMessages.METHODDECL_REPLACEBODY.value(JavaCodeHelper.getName(existingMethod),
						JavaCodeHelper.getDescription(existingMethod)));
		} else {
			if (MergerLogger.enabled())
				MergerLogger.log(MergerLoggerMessages.METHODDECL_ADDBODY.value(JavaCodeHelper.getName(existingMethod),
						JavaCodeHelper.getDescription(existingMethod)));
		}

		astr.set(existingMethod, BODY_PROPERTY, createSourceCodePlaceholder(generatedBody), null);
	}

	/**
	 * Replace @Generated annotation
	 */
	private void replaceGeneratedAnnotation(MethodDeclaration existingMethod, MethodDeclaration generatedMethod) {
		Annotation existingAnnotation = ASTHelper.getAnnotation(JavaCodeHelper.GENERATED_SIMPLECLASSNAME,
				existingMethod);
		Annotation generatedAnnotation = ASTHelper.getAnnotation(JavaCodeHelper.GENERATED_SIMPLECLASSNAME,
				generatedMethod);

		if (existingAnnotation != null && generatedAnnotation != null && generatedAnnotation.isNormalAnnotation()) {
			astr.replace(existingAnnotation, generatedAnnotation, null);
		}
	}

	/**
	 * Merge exceptions from existingMethod and generatedMethod to an AST
	 * Rewrite object instance.
	 * 
	 * @param astr
	 *            The ASTRewrite object instance containing the merge result
	 */
	@SuppressWarnings("unchecked")
	protected void mergeExceptions(MethodDeclaration existingMethod, MethodDeclaration generatedMethod) {

		List<String> existingExceptions = new ArrayList<String>(3);

		// Create an annotations list in String format.
		for (Name n : (List<Name>) existingMethod.thrownExceptions()) {
			existingExceptions.add(JavaCodeHelper.getSimpleName(n));
		}

		/*
		 * Get a ListRewrite object used to modify annotations in the existing
		 * code.
		 */
		ListRewrite lw = astr.getListRewrite(existingMethod, THROWN_EXCEPTIONS_PROPERTY);

		// For each annotation of the generated source
		for (Name n : (List<Name>) generatedMethod.thrownExceptions()) {
			if (!existingExceptions.contains(JavaCodeHelper.getSimpleName(n))) {
				/*
				 * This exception is containing in the generated code but not in
				 * the existing code => Add this exception in the merge result.
				 */
				if (MergerLogger.enabled())
					MergerLogger.log(MergerLoggerMessages.METHODDECL_ADDEXCEPTION.value(n.getFullyQualifiedName(),
							JavaCodeHelper.getName(existingMethod), JavaCodeHelper.getDescription(existingMethod)));

				lw.insertLast(n, null);
			}
		}
	}

}
