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
import net.atos.optimus.common.tools.jdt.ASTParserFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaCore;
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
	 * Addition of new method to be able to retrieve the right project in the case of multi modules (maven case).
	 * @param filePath
	 * @return
	 */
	private static ICompilationUnit getCUFromFullPath(String filePath) {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		
		IPath localPath = new Path(filePath);
		IFile fileForLocation = root.getFileForLocation(localPath);
		if (fileForLocation != null && fileForLocation.exists()) {
			IJavaElement create = JavaCore.create(fileForLocation);
			if (create instanceof ICompilationUnit && create.exists()) {
				return (ICompilationUnit) create;
			}
			
			// If we are here, the CU exists but it is not resolveable... Because we may be in too high project.
			// So we trim its path, to try to retrieve it from a child module !
			IPath tempPath = fileForLocation.getFullPath();
			while (tempPath.segmentCount() > 0) {
				tempPath = tempPath.removeFirstSegments(1);
				IFile file = root.getFile(tempPath);
				if (file != null && file.exists()) {
					IJavaElement create2 = JavaCore.create(file);
					if (create2 instanceof ICompilationUnit && create2.exists()) {
						return (ICompilationUnit) create2;
					}
				}
			}
			
			// Better to return something not fully complete than null... :/
			return (ICompilationUnit) create;
		}

		return null;
	}

	/**
	 * Executes the imports process on the file located at the given path, with
	 * given contents
	 * 
	 * @param filePath
	 *            : Destination File Path (used to retrieve project, in order to
	 *            get CU's context only)
	 * @param initialContents
	 *            : Initial Contents
	 * @return Updated Contents.
	 */
	public CharSequence execute(String filePath, CharSequence initialContents) {
		ASTParser parser = ASTParserFactory.INSTANCE.newParser();

		try {

			ICompilationUnit compilationUnit = getCUFromFullPath(filePath);
			if (compilationUnit == null) {
				return initialContents;
			}

			parser.setUnitName(compilationUnit.getResource().getFullPath().removeFileExtension().lastSegment());
			parser.setBindingsRecovery(true);
			parser.setResolveBindings(true);
			parser.setIgnoreMethodBodies(false);
			parser.setStatementsRecovery(true);
			parser.setProject(compilationUnit.getJavaProject());
			parser.setSource(initialContents.toString().toCharArray());

			ASTNode astNode = parser.createAST(new NullProgressMonitor());
			IDocument document = new Document(initialContents.toString().toString());
			// Valeur du contenu du document qui n'est pas bonne ?
			if (astNode instanceof CompilationUnit) {
				CompilationUnit domCompilationUnit = (CompilationUnit) astNode;
				domCompilationUnit.recordModifications();
				if (apply(domCompilationUnit)) {
					// test avec retour direct de la compilation unit ?
					TextEdit rewrite = domCompilationUnit.rewrite(document, null);
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

		} catch (IllegalStateException e) {
			// Workspace is closed
			// Binding resolution will probably not work
		}

		return initialContents;
	}

	/**
	 * Effectively launches the process on the Compilation Unit
	 * 
	 * @param compilationUnit
	 * @return
	 */
	protected abstract boolean apply(CompilationUnit compilationUnit);

}
