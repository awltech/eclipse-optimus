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

import org.eclipse.gmt.modisco.java.Model;

/**
 * The purpose of such class is to help with the creation of package
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class PackageHelper {

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
	public static Package createPackage(Model javaModel, String packageName) {
		String[] packageNames = packageName.split("\\.");
		int packageNumber = packageNames.length;
		org.eclipse.gmt.modisco.java.Package createdPackage = null;

		if (packageNumber != 0) {
			createdPackage = PackageBuilder.builder().setModel(javaModel).setName(packageName).build();
		}
		for (int indexPackage = 1; indexPackage < packageNumber; indexPackage++) {
			createdPackage = PackageBuilder.builder().setPackage(createdPackage).setName(packageNames[indexPackage])
					.build();
		}

		return new Package(createdPackage);
	}

}
