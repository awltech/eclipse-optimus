package net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.policies;

import net.atos.optimus.m2m.engine.sdk.tom.diagram.providers.TransformationDependencyElementTypes;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

/**
 * @generated
 */
public class CustomRequirementItemSemanticEditPolicy extends TransformationDependencyBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public CustomRequirementItemSemanticEditPolicy() {
		super(TransformationDependencyElementTypes.CustomRequirement_4006);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return getGEFWrapper(new DestroyElementCommand(req));
	}

}
