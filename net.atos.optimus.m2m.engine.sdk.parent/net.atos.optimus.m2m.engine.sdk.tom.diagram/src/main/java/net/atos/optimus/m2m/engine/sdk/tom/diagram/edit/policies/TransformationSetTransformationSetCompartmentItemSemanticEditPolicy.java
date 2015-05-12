package net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.policies;

import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.commands.TransformationCreateCommand;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.providers.TransformationDependencyElementTypes;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

/**
 * @generated
 */
public class TransformationSetTransformationSetCompartmentItemSemanticEditPolicy extends
		TransformationDependencyBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public TransformationSetTransformationSetCompartmentItemSemanticEditPolicy() {
		super(TransformationDependencyElementTypes.TransformationSet_2004);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (TransformationDependencyElementTypes.Transformation_3003 == req.getElementType()) {
			return getGEFWrapper(new TransformationCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
