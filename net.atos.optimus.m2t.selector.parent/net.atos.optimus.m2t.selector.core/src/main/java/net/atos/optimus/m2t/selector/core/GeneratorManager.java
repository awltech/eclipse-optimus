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
package net.atos.optimus.m2t.selector.core;

import java.util.HashMap;
import java.util.Map;

import net.atos.optimus.m2t.selector.core.internal.Activator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;

/**
 * M2T Generator Manager
 * 
 * The purpose of this class is to load extension point related to M2T
 * Generators. Determination of the code generator to use will be done using the
 * type of a given EObject
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 * 
 */
public class GeneratorManager {

	/**
	 * Singleton Holder class
	 * 
	 * @author Maxence Vanbésien (mvaawl@gmail.com)
	 * @since 1.0
	 * 
	 */
	private static class SingletonHolder {
		static GeneratorManager instance = new GeneratorManager();
	}

	/**
	 * @return the Generator Manager single instance
	 */
	public static GeneratorManager instance() {
		return SingletonHolder.instance;
	}

	/**
	 * Private Constructor
	 */
	private GeneratorManager() {
		this.generatorsMap = new HashMap<EClass, IGenerator>();
		this.loadExtensions();
	}

	/**
	 * Map containing generators, stored by EClass
	 */
	private Map<EClass, IGenerator> generatorsMap;

	/**
	 * Load the Extension point
	 */
	private void loadExtensions() {
		IExtensionPoint extensionPoint = Platform.getExtensionRegistry().getExtensionPoint(Activator.PLUGIN_ID,
				"Generators");
		for (IExtension extension : extensionPoint.getExtensions()) {
			for (IConfigurationElement configurationElement : extension.getConfigurationElements()) {
				try {
					if ("generator".equals(configurationElement.getName())) {
						String nameSpace = configurationElement.getAttribute("namespace");
						String eClassName = configurationElement.getAttribute("eclass");
						IGenerator lts = (IGenerator) configurationElement.createExecutableExtension("generator");
						EPackage ePackage = EPackageRegistryImpl.INSTANCE.getEPackage(nameSpace);
						EClassifier eClassifier = ePackage.getEClassifier(eClassName);
						if (eClassifier instanceof EClass) {
							this.generatorsMap.put((EClass) eClassifier, lts);
						}
					}
				} catch (CoreException ce) {
					ce.printStackTrace();
				}
			}
		}
	}

	/**
	 * Select and invokes the code generator related to the input Eobject, and
	 * stores the result in the output Object.
	 * 
	 * If required by implementations, arguments can be provided to the code
	 * generator
	 * 
	 * @param input
	 *            : EObject
	 * @param output
	 *            : Object
	 * @param arguments
	 *            : varargs of Object
	 */
	public void generate(EObject input, Object output, Object... arguments) {
		EClass eClass = input.eClass();
		IGenerator generator = this.generatorsMap.get(eClass);
		if (generator != null)
			generator.generate(input, output, arguments);
	}
}
