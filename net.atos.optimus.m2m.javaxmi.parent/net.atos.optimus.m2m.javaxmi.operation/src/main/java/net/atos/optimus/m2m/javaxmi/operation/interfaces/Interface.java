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
package net.atos.optimus.m2m.javaxmi.operation.interfaces;

import net.atos.optimus.m2m.javaxmi.operation.accesses.TypeAccessHelper;
import net.atos.optimus.m2m.javaxmi.operation.classes.AbstractClass;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.InterfaceDeclaration;
import org.eclipse.gmt.modisco.java.TypeAccess;

/**
 * Models an interface : wrapper of InterfaceDeclaration in modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class Interface extends AbstractClass {

	/**
	 * Constructor of interface
	 * 
	 * @param interfaceDeclaration
	 *            the interface declaration.
	 */
	public Interface(InterfaceDeclaration interfaceDeclaration) {
		super(interfaceDeclaration);
	}

	public InterfaceDeclaration getInterfaceDeclaration() {
		return (InterfaceDeclaration) this.getAbstractTypeDeclaration();
	}

	/**
	 * Add interfaces list to the interface
	 * 
	 * @param interfacesNames
	 *            the interfaces names list to add to the interface.
	 * @return the builder.
	 */
	public Interface addInterfaces(String... interfacesNames) {
		EList<TypeAccess> interfacesList = this.getInterfaceDeclaration().getSuperInterfaces();
		for (String javaInterface : interfacesNames) {
			interfacesList.add(TypeAccessHelper.createInterfaceTypeAccess(javaInterface));
		}
		return this;
	}

}
