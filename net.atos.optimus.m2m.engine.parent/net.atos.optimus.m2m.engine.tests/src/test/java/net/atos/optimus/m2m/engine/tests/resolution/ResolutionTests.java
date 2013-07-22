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
package net.atos.optimus.m2m.engine.tests.resolution;

import java.util.Collections;
import java.util.HashSet;

import net.atos.optimus.m2m.engine.core.OptimusM2MEngine;
import net.atos.optimus.m2m.engine.core.transformations.DefaultTransformationContext;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.engine.tests.ExtendedOptimusM2MEngine;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 *  @author Maxence Vanbésien (mvaawl@gmail.com)
 *  @since 1.0
 */
public class ResolutionTests {

	@Test
	public void objectResolutionTest() {
		
		// Create input model
		EPackage ePackage1 = EcoreFactory.eINSTANCE.createEPackage();
		ePackage1.setName("a");
		EPackage ePackage2 = EcoreFactory.eINSTANCE.createEPackage();
		ePackage2.setName("b");
		EPackage ePackage3 = EcoreFactory.eINSTANCE.createEPackage();
		ePackage3.setName("c");
		EPackage ePackage4 = EcoreFactory.eINSTANCE.createEPackage();
		ePackage4.setName("d");
		EPackage ePackage5 = EcoreFactory.eINSTANCE.createEPackage();
		ePackage5.setName("e");
		ePackage1.getESubpackages().add(ePackage2);
		ePackage1.getESubpackages().add(ePackage3);
		ePackage2.getESubpackages().add(ePackage4);
		ePackage2.getESubpackages().add(ePackage5);
		
		// Initialize
		ITransformationContext context = new DefaultTransformationContext();
		OptimusM2MEngine engine = new ExtendedOptimusM2MEngine(context, false, false, new ResolutionTransformationDS());
		engine.setElements(new HashSet<EObject>(Collections.singleton(ePackage2)));
		
		// Run engine
		IStatus status = engine.execute();
		if (status.getSeverity() == IStatus.ERROR)
			Assert.fail(status.getMessage());
		
		// Validate
		Assert.assertNotNull(context.getRoot("a"));
		Assert.assertNotNull(context.getRoot("b"));
		Assert.assertNull(context.getRoot("c"));
		Assert.assertNotNull(context.getRoot("d"));
		Assert.assertNotNull(context.getRoot("e"));
		
	}

}
