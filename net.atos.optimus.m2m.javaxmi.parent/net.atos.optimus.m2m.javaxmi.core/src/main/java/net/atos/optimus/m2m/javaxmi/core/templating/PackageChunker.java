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
package net.atos.optimus.m2m.javaxmi.core.templating;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * The purpose of this tool is to transform a single javaxmi package, to bunch
 * of package chunks, accordingly to the initial package name.
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 * 
 */
public class PackageChunker {

	/**
	 * Update the packages of the provided model
	 * 
	 * @param model
	 */
	public static void chunkPackages(final Model model) {
		for (final Package pack : model.getOwnedElements()) {
			PackageChunker.chunkPackage(pack);
			for (final Package subPack : pack.getOwnedPackages())
				PackageChunker.chunkPackage(subPack);

		}
	}

	/**
	 * Update the processed package
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void chunkPackage(final Package pack) {
		final String name = pack.getName();
		final String[] nameChunks = name.split("\\.");
		if (nameChunks.length > 1) {
			final Package[] packages = new Package[nameChunks.length];
			packages[0] = pack;
			pack.setName(nameChunks[0]);
			for (int i = 1; i < nameChunks.length; i++) {
				final Package packageChunk = JavaFactory.eINSTANCE.createPackage();
				packageChunk.setName(nameChunks[i]);
				packages[i] = packageChunk;
				packageChunk.setPackage(packages[i - 1]);
			}
			final Package lastPackage = packages[nameChunks.length - 1];
			final Collection<EObject> contentsToProcess = new ArrayList<EObject>();
			contentsToProcess.addAll(pack.eContents());
			for (final EObject eObject : contentsToProcess)
				if (!(eObject instanceof Package)) {
					final EReference eReference = eObject.eContainmentFeature();
					if (eReference.isMany())
						((EList) lastPackage.eGet(eReference)).add(eObject);
					else
						lastPackage.eSet(eReference, eObject);
				}
			for (final CompilationUnit compilationUnit : pack.getModel().getCompilationUnits())
				if (compilationUnit.getPackage() == pack)
					compilationUnit.setPackage(lastPackage);
		}
	}

}
