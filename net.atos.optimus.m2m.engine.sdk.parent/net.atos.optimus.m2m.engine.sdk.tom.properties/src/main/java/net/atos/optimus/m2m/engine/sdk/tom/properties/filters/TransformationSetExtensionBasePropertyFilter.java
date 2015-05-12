package net.atos.optimus.m2m.engine.sdk.tom.properties.filters;

import net.atos.optimus.m2m.engine.sdk.tom.TomPackage;

import org.eclipse.emf.ecore.EObject;

import com.worldline.gmf.propertysections.core.AbstractFilter;

/**
 * Models the base filter for transformation set extension
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 *
 */

public class TransformationSetExtensionBasePropertyFilter extends AbstractFilter {

	@Override
	public boolean select(Object toTest) {
		EObject eObjectToTest = this.convertToEMF(toTest);
		return eObjectToTest != null
				&& eObjectToTest.eClass() == TomPackage.eINSTANCE.getTransformationSetExtension();
	}

}