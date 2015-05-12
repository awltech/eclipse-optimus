package net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.policies;

import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.commands.Transformation2CreateCommand;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.providers.TransformationDependencyElementTypes;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

/**
 * @generated
 */
public class TransformationSetExtensionTransformationSetExtensionCompartmentItemSemanticEditPolicy extends
		TransformationDependencyBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public TransformationSetExtensionTransformationSetExtensionCompartmentItemSemanticEditPolicy() {
		super(TransformationDependencyElementTypes.TransformationSetExtension_2005);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (TransformationDependencyElementTypes.Transformation_3004 == req.getElementType()) {
			return getGEFWrapper(new Transformation2CreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
