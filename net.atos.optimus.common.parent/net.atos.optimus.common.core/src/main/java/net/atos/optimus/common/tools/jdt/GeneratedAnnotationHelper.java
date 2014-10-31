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
package net.atos.optimus.common.tools.jdt;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.atos.optimus.common.tools.Activator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.MalformedTreeException;

/**
 * Helper class to manage the hashcode computation, in Generated Annotations
 * (value used by the Java Code Merger.
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 * 
 */
@SuppressWarnings("unchecked")
public class GeneratedAnnotationHelper {

	/**
	 * Default hashCode value to overwrite
	 */
	public static final String VALUE_TO_OVERWRITE = "ToBeCalculated";
	
	/**
	 * Comment parameter name of the Generated annotation
	 */
	public static final String COMMENTS_PARAM = "comments";
	
	private static final String COMMENT_SEPARATOR  = ",";
	private static final int HASHCODE_RANK_IN_COMMENT = 0;
	private static final int UNIQUEID_RANK_IN_COMMENT = 1;
	
	/**
	 * Compiler options used inside this Java code Merger
	 */
	public static Map<Object, Object> compilerOptions = null;

	static {
		// Set compiler options to 1.5 level
		GeneratedAnnotationHelper.compilerOptions = JavaCore.getOptions();
		GeneratedAnnotationHelper.compilerOptions.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_5);
	}

	/**
	 * Visitor which role is to detect methods from Compilation unit, where
	 * Generated annotation is missing
	 * @author Maxence Vanbésien (mvaawl@gmail.com)
	 * @since 1.0
	 */
	private static class GeneratedAnnotationVisitor extends ASTVisitor {

		/**
		 * Methods to process list
		 */
		private List<NormalAnnotation> methodsToProcess = new ArrayList<NormalAnnotation>();

		/**
		 * Execute this visitor on Compilation Unit passed as parameter
		 * 
		 * @param cu
		 *            AST Compilation Unit
		 */
		public void process(CompilationUnit cu) {
			this.methodsToProcess.clear();
			cu.accept(this);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom
		 * .NormalAnnotation)
		 */
		@Override
		public boolean visit(NormalAnnotation node) {
			if (this.needToEnrichAnnotation(node))
				this.methodsToProcess.add(node);
			return super.visit(node);
		}

		/**
		 * Looks in declaration to find if the Generated annotation is present
		 * 
		 * @param methodDeclaration
		 *            MethodDeclaration
		 * @return true is annotation is present, false otherwise
		 */
		private boolean needToEnrichAnnotation(NormalAnnotation annotation) {
			String hashcode = getGeneratedAnnotationHashcode(annotation);
			return VALUE_TO_OVERWRITE.equals(hashcode);
		}
		
		

		/**
		 * @return List of method that should be processed by post process (i.e.
		 *         without Generated annotation)
		 */
		public List<NormalAnnotation> getAnnotationsToEnrich() {
			return this.methodsToProcess;
		}

	}

	/**
	 *Stores source code passed as parameter, in file, which path is passed as
	 * parameter.
	 * 
	 * @param source
	 *            : Source code
	 * @param path
	 *            : destination file path
	 */
	public static void writeSourceToFile(String source, String path) {
		IPath cuLocation = new Path(path);
		ICompilationUnit javaCU = null;

		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		IProject myProject = null;
		for (int i = 0; i < projects.length && myProject == null; i++) {
			if (projects[i].getLocation().isPrefixOf(cuLocation)) {
				myProject = projects[i];
			}
		}
		if (myProject != null) {
			IPath relativePath = cuLocation.removeFirstSegments(myProject.getLocation()
					.segmentCount());
			IFile file = myProject.getFile(relativePath);
			if (!file.exists())
				try {
					file.getParent().refreshLocal(IResource.DEPTH_ONE, new NullProgressMonitor());
				} catch (CoreException e) {
					Activator.getDefault().logError(
							"Exception thrown while refreshing package file parent of "
									+ cuLocation.toString(), e);
				}

			javaCU = (ICompilationUnit) JavaCore.create(file);
		}

		if (javaCU == null || !javaCU.exists()) {
			Activator.getDefault().logError(
					"Java Compilation Unit couldn't be found from File at path: "
							+ cuLocation.toString(), new FileNotFoundException());
			return;
		}
		try {
			// Writes file on disk
			javaCU.getBuffer().setContents(source);
			javaCU.getBuffer().save(null, true);
		} catch (Exception e) {
			Activator.getDefault().logError("Error when writing file on disk " + javaCU, e);
		}
	}

	/**
	 * Updates the source code passed as parameter (updates the annotation
	 * value), and stores it in file, according to path passed as parameter.
	 * 
	 * @param source
	 *            : Source code
	 * @param path
	 *            : destination file path
	 */
	public static void updateAndWriteHashcodeInSource(String source, String path) {
		String updatedSource = updateHashcodeInSource(source);
		if (!source.equals(updatedSource)) {
			writeSourceToFile(updatedSource, path);
		}
	}

	/**
	 * Updates the source passed as parameter, by populating the hashCode
	 * values.
	 * 
	 * @param source
	 *            : Source
	 * @return updated Source
	 */
	public static String updateHashcodeInSource(String source) {
		ASTParser parser = ASTParserFactory.INSTANCE.newParser();
		parser.setSource(source.toCharArray());
		parser.setCompilerOptions(GeneratedAnnotationHelper.compilerOptions);
		CompilationUnit cu = (CompilationUnit) parser.createAST(null);

		// Create and call the new Visitor, which role will be to
		// gather the methods on which the Generated annotation is yet not
		// defined.
		GeneratedAnnotationVisitor ev = new GeneratedAnnotationVisitor();
		ev.process(cu);

		// Get the annotations to modify, fetched by the Visitor
		List<NormalAnnotation> annotationsToEnrich = ev.getAnnotationsToEnrich();
		if (annotationsToEnrich.size() > 0) {
			cu.recordModifications();
			boolean modified = false;
			for (NormalAnnotation annotationToEnrich : annotationsToEnrich) {
				// For each annotation to modify, replace the null value of
				// the comments field by the method's hashcode
				ASTNode parent = annotationToEnrich.getParent();
				if (parent instanceof MethodDeclaration) {
					MethodDeclaration methodDeclaration = (MethodDeclaration) parent;
					if (methodDeclaration.getBody() != null) {
						int hashCode = ASTHelper.methodBodyHashCode(methodDeclaration.getBody().toString());
						modified = setGeneratedAnnotationHashcode(cu, annotationToEnrich, String.valueOf(hashCode));
					}
					else {
						modified = setGeneratedAnnotationHashcode(cu, annotationToEnrich, "0");
					}
				} else if (parent instanceof EnumConstantDeclaration) {
					EnumConstantDeclaration enumConstantDeclaration = (EnumConstantDeclaration) parent;
					int hashCode = ASTHelper.enumConstantHashCode(enumConstantDeclaration);
					modified = setGeneratedAnnotationHashcode(cu, annotationToEnrich, String.valueOf(hashCode));
				} else if (parent instanceof FieldDeclaration) {
					modified = setGeneratedAnnotationHashcode(cu, annotationToEnrich, "0");
				}

			}
			if (modified) {
				// Writes file on disk
				IDocument document = new Document(source);
				try {
					cu.rewrite(document, null).apply(document);
					return document.get();
				} catch (MalformedTreeException e) {
					Activator.getDefault().logError(
							"The compilation unit could not be"
									+ " written on FileSystem because of thrown Exception.", e);
				} catch (BadLocationException e) {
					Activator.getDefault().logError(
							"The compilation unit could not be"
									+ " written on FileSystem because of thrown Exception", e);
				}
			}
		}
		return source;
	}

	//--------------------------------------------------------------------------------------
	//
	//                  MANAGES MULTIPLE INFO IN COMMENT (separated by ',')
	//
	//--------------------------------------------------------------------------------------
	
	
	public static boolean setGeneratedAnnotationHashcode(CompilationUnit cu, NormalAnnotation annotation, String hashcode) {
		return setGeneratedAnnotationPart(cu, annotation, hashcode, HASHCODE_RANK_IN_COMMENT);
	}

	public static boolean setGeneratedAnnotationUniqueId(CompilationUnit cu, NormalAnnotation annotation, String uniqueId) {
		return setGeneratedAnnotationPart(cu, annotation, uniqueId, UNIQUEID_RANK_IN_COMMENT);
	}

	public static String getGeneratedAnnotationHashcode(NormalAnnotation annotation) {
		StringLiteral comment = getGeneratedAnnotationComment(annotation);
		if(comment != null) {
			return getGeneratedAnnotationCommentPart(comment.getLiteralValue(), HASHCODE_RANK_IN_COMMENT);
		}
		return null;
	}
	
	public static String getGeneratedAnnotationUniqueId(NormalAnnotation annotation) {
		StringLiteral comment = getGeneratedAnnotationComment(annotation);
		if(comment != null) {
			return getGeneratedAnnotationCommentPart(comment.getLiteralValue(), UNIQUEID_RANK_IN_COMMENT);
		}
		return null;
	}
	
	public static String getHashCodeFromComment(String comment) {
		return getGeneratedAnnotationCommentPart(comment, HASHCODE_RANK_IN_COMMENT);
	}
	
	public static String getUniqueIdFromComment(String comment) {
		return getGeneratedAnnotationCommentPart(comment, UNIQUEID_RANK_IN_COMMENT);
	}
	

	private static boolean setGeneratedAnnotationPart(CompilationUnit cu, NormalAnnotation annotation, String newPartValue, int rankInComment) {
		StringLiteral oldCommentLiteral = getGeneratedAnnotationComment(annotation);
		if (oldCommentLiteral != null) {
			String[] oldCommentParts = oldCommentLiteral.getLiteralValue().split(COMMENT_SEPARATOR);
			StringBuilder newCommentSB = new StringBuilder(25).append("");
			for(int i = 0; i<oldCommentParts.length; i++) {
				if(i == rankInComment) {
					newCommentSB.append(newPartValue);
				}
				else {
					newCommentSB.append(oldCommentParts[i]);
				}
				if(i<(oldCommentParts.length-1)) {
					newCommentSB.append(COMMENT_SEPARATOR);
				}
			}
			oldCommentLiteral.setLiteralValue(newCommentSB.toString());
			
			return true;
		}
		return false;
	}

	private static String getGeneratedAnnotationCommentPart(String comment, int rankInComment) {
		if(comment != null) {
			String[] commentParts = comment.split(COMMENT_SEPARATOR);
			if(commentParts != null && commentParts.length>rankInComment)
				return commentParts[rankInComment];
		}
		return null;
	}


	private static StringLiteral getGeneratedAnnotationComment(NormalAnnotation annotation) {
		MemberValuePair mvp = null;
		for (Object o : annotation.values()) {
			if (o instanceof MemberValuePair) {
				MemberValuePair tempMVP = (MemberValuePair) o;
				if (COMMENTS_PARAM.equals(tempMVP.getName().toString())) {
					mvp = tempMVP;
					break;
				}
			}
		}
		if (mvp != null && mvp.getValue() != null && mvp.getValue() instanceof StringLiteral) {
			StringLiteral literal = (StringLiteral) mvp.getValue();
			return literal;
		}
		else{
			return null;
		}
	}
}
