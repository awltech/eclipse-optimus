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
package net.atos.optimus.m2m.engine.tests.adapters;

import java.util.Collections;
import java.util.HashSet;

import net.atos.optimus.m2m.engine.core.OptimusM2MEngine;
import net.atos.optimus.m2m.engine.core.exceptions.TransformationFailedException;
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
public class AdaptersTests {

	/**
	 * Validates the behaviour of the Child Addition Adapter.
	 */
	@Test
	public void validateAdditionAdapter() {

		// Initialization
		EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
		ITransformationContext context = new DefaultTransformationContext();
		OptimusM2MEngine engine = new ExtendedOptimusM2MEngine(context, false, true,
				new AdaptersTransformationsDataSource());
		engine.setElements(new HashSet<EObject>(Collections.singleton(ePackage)));

		// Execution
		IStatus status = engine.execute();
		if (status.getSeverity() == IStatus.ERROR)
			Assert.fail(status.getMessage());

		// Result
		Assert.assertNotNull(context.getRoot("EClass"));
	}

	/**
	 * Validates the behaviour of the Child Addition Adapter.
	 */
	@Test
	public void validateLockAdapter() {

		// Initialization
		EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
		ITransformationContext context = new DefaultTransformationContext();
		OptimusM2MEngine engine = new ExtendedOptimusM2MEngine(context, true, false,
				new AdaptersTransformationsDataSource());
		engine.setElements(new HashSet<EObject>(Collections.singleton(ePackage)));

		// Execution
		IStatus status = engine.execute();
		if (status.getSeverity() == IStatus.ERROR) {
			Throwable exception = status.getException();
			if (exception instanceof TransformationFailedException) {
				if (exception.getCause() instanceof IllegalStateException) {
					Assert.assertEquals(exception.getCause().getMessage(),
							OptimusM2MEngineTestsMessages.LOCK_EXCEPTION_MESSAGE.value());
					return;
				}
				
			}
		}
		// Result
		Assert.fail(OptimusM2MEngineTestsMessages.LOCK_TEST_FAILURE_MESSAGE.value());
	}

}
