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
package net.atos.optimus.m2m.javaxmi.operation.packages;

import java.util.Iterator;

import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.gmt.modisco.java.Package;

/**
 * The purpose of such class is to help with the creation of packages
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class PackageHelper {

	/**
	 * Private constructor
	 * 
	 */
	private PackageHelper() {
	}

	/**
	 * Create a package and its complete hierarchy in a java model
	 * 
	 * @param javaModel
	 *            the java model of the created package.
	 * @param packageName
	 *            the full package name.
	 * @return the created package with the specified name in the specified java
	 *         model, null if the package name is invalid.
	 */
	public static JavaPackage createPackage(Model javaModel, String packageName) {
		String[] packageNames = packageName.split("\\.");
		int packageNumber = packageNames.length;

		Package fatherPackage = null;
		Iterator<Package> iteratorOnModelPackages = javaModel.getOwnedElements().iterator();
		while (iteratorOnModelPackages.hasNext() && fatherPackage == null) {
			Package next = iteratorOnModelPackages.next();
			if (packageNames[0].equals(next.getName())) {
				fatherPackage = next;
			}
		}

		if (fatherPackage == null) {
			fatherPackage = PackageBuilder.builder().setModel(javaModel).setName(packageNames[0]).build();
		}

		for (int indexPackage = 1; indexPackage < packageNumber; indexPackage++) {
			Package childPackage = null;
			Iterator<Package> iteratorOnSubPackages = fatherPackage.getOwnedPackages().iterator();

			while (iteratorOnSubPackages.hasNext() && childPackage == null) {
				Package next = iteratorOnSubPackages.next();
				if (packageNames[indexPackage].equals(next.getName())) {
					childPackage = next;
				}
			}

			if (childPackage == null) {
				fatherPackage = PackageBuilder.builder().setPackage(fatherPackage).setName(packageNames[indexPackage])
						.build();
			} else {
				fatherPackage = childPackage;
			}

		}

		return new JavaPackage(fatherPackage);
	}

}
