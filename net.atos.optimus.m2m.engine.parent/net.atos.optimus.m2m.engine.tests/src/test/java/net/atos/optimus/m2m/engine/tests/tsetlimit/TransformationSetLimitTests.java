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
package net.atos.optimus.m2m.engine.tests.tsetlimit;

import java.util.HashSet;
import java.util.Set;

import net.atos.optimus.m2m.engine.core.OptimusM2MEngine;
import net.atos.optimus.m2m.engine.core.transformations.DefaultTransformationContext;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.engine.tests.ExtendedOptimusM2MEngine;
import net.atos.optimus.m2m.engine.tests.OptimusM2MEngineTestsMessages;

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
public class TransformationSetLimitTests {

	@Test
	public void transformationSetLimitTest() {
		ITransformationContext context = new DefaultTransformationContext();
		OptimusM2MEngine engine = new ExtendedOptimusM2MEngine(context, false, false,
				new TSetTransformationsDataSource());

		EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
		Set<EObject> input = new HashSet<EObject>();
		input.add(pack);
		engine.setElements(input);

		engine.limitTransformationSetsTo("transformationSet1");
		IStatus status = engine.execute();
		if (status.getSeverity() == IStatus.ERROR)
			Assert.fail(OptimusM2MEngineTestsMessages.LOCK_EXCEPTION_MESSAGE.value());

		Assert.assertNotNull(context.getRoot("object1"));
		Assert.assertNull(context.getRoot("object2"));
	}

	@Test
	public void transformationSetNoLimitTest() {
		ITransformationContext context = new DefaultTransformationContext();
		OptimusM2MEngine engine = new ExtendedOptimusM2MEngine(context, false, false,
				new TSetTransformationsDataSource());

		EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
		Set<EObject> input = new HashSet<EObject>();
		input.add(pack);
		engine.setElements(input);

		IStatus status = engine.execute();
		if (status.getSeverity() == IStatus.ERROR)
			Assert.fail(OptimusM2MEngineTestsMessages.LOCK_EXCEPTION_MESSAGE.value());

		Assert.assertNull(context.getRoot("object1"));
		Assert.assertNotNull(context.getRoot("object2"));
	}
}
