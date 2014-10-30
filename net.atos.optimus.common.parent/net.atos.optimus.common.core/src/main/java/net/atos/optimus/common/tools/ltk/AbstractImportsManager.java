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
package net.atos.optimus.common.tools.ltk;

import net.atos.optimus.common.tools.Activator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.TextEdit;

/**
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 */
public abstract class AbstractImportsManager {

	/**
	 * Generates imports for the file located at the given path, with given
	 * contents
	 * 
	 * @param filePath
	 *            : Destination File Path (used to retrieve project, in order to
	 *            get CU's context only)
	 * @param initialContents
	 *            : Initial Contents
	 * @return Updated Contents.
	 */
	public static CharSequence execute(String filePath, CharSequence initialContents) {
		ASTParser parser = ASTParser.newParser(AST.JLS4);

		IPath absolutePath = new Path(filePath);
		try {
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			IPath workspacePath = root.getLocation();

			IPath relativePath = absolutePath.makeRelativeTo(workspacePath);
			IFile file = root.getFile(relativePath);
			IProject project = file.getProject();

			IJavaProject javaProject = JavaCore.create(project);

			parser.setProject(javaProject);
			parser.setUnitName(relativePath.removeFileExtension().lastSegment());
		} catch (IllegalStateException e) {
			// Workspace is closed
			// Binding resolution will probably not work
		}
		parser.setBindingsRecovery(true);
		parser.setResolveBindings(true);

		parser.setIgnoreMethodBodies(false);

		parser.setStatementsRecovery(true);
		parser.setSource(initialContents.toString().toCharArray());

		ASTNode astNode = parser.createAST(new NullProgressMonitor());
		IDocument document = new Document(initialContents.toString().toString());
		if (astNode instanceof CompilationUnit) {
			CompilationUnit compilationUnit = (CompilationUnit) astNode;
			compilationUnit.recordModifications();
			if (ImportsGenerationVisitor.apply(compilationUnit)) {
				TextEdit rewrite = compilationUnit.rewrite(document, null);
				try {
					rewrite.apply(document);
					return document.get();
				} catch (MalformedTreeException e) {
					Activator.getDefault().logError("Import Creation encountered Exception", e);
				} catch (BadLocationException e) {
					Activator.getDefault().logError("Import Creation encountered Exception", e);
				}
			}
		}
		return initialContents;
	}

	protected abstract boolean apply(CompilationUnit compilationUnit);

}
