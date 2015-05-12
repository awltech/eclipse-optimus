/**
 *
 * $Id$
 */
package net.atos.optimus.m2m.engine.sdk.tom.validation;

import net.atos.optimus.m2m.engine.sdk.tom.Transformation;

import org.eclipse.emf.common.util.EList;

/**
 * A sample validator interface for {@link net.atos.optimus.m2m.engine.sdk.tom.TransformationSetExtension}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface TransformationSetExtensionValidator {
	boolean validate();

	boolean validateTransformationSetId(String value);
	boolean validateTransformations(EList<Transformation> value);
}
