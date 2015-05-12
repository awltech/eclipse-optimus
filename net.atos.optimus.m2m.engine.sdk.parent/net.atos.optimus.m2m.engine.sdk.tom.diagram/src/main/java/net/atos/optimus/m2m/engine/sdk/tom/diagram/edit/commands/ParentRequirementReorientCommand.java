package net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.commands;

import net.atos.optimus.m2m.engine.sdk.tom.ParentRequirement;
import net.atos.optimus.m2m.engine.sdk.tom.Transformation;
import net.atos.optimus.m2m.engine.sdk.tom.TransformationReference;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.policies.TransformationDependencyBaseItemSemanticEditPolicy;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

/**
 * @generated
 */
public class ParentRequirementReorientCommand extends EditElementCommand {

	/**
	 * @generated
	 */
	private final int reorientDirection;

	/**
	 * @generated
	 */
	private final EObject oldEnd;

	/**
	 * @generated
	 */
	private final EObject newEnd;

	/**
	 * @generated
	 */
	public ParentRequirementReorientCommand(ReorientRelationshipRequest request) {
		super(request.getLabel(), request.getRelationship(), request);
		reorientDirection = request.getDirection();
		oldEnd = request.getOldRelationshipEnd();
		newEnd = request.getNewRelationshipEnd();
	}

	/**
	 * @generated
	 */
	public boolean canExecute() {
		if (false == getElementToEdit() instanceof ParentRequirement) {
			return false;
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return canReorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return canReorientTarget();
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean canReorientSource() {
		if (!(oldEnd instanceof Transformation && newEnd instanceof Transformation)) {
			return false;
		}
		TransformationReference target = getLink().getReference();
		return TransformationDependencyBaseItemSemanticEditPolicy.getLinkConstraints().canExistParentRequirement_4005(
				getLink(), getNewSource(), target);
	}

	/**
	 * @generated
	 */
	protected boolean canReorientTarget() {
		if (!(oldEnd instanceof TransformationReference && newEnd instanceof TransformationReference)) {
			return false;
		}
		if (!(getLink().eContainer() instanceof Transformation)) {
			return false;
		}
		Transformation source = (Transformation) getLink().eContainer();
		return TransformationDependencyBaseItemSemanticEditPolicy.getLinkConstraints().canExistParentRequirement_4005(
				getLink(), source, getNewTarget());
	}

	/**
	 * @generated
	 */
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		if (!canExecute()) {
			throw new ExecutionException("Invalid arguments in reorient link command"); //$NON-NLS-1$
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return reorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return reorientTarget();
		}
		throw new IllegalStateException();
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientSource() throws ExecutionException {
		getOldSource().getRequirements().remove(getLink());
		getNewSource().getRequirements().add(getLink());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientTarget() throws ExecutionException {
		getLink().setReference(getNewTarget());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected ParentRequirement getLink() {
		return (ParentRequirement) getElementToEdit();
	}

	/**
	 * @generated
	 */
	protected Transformation getOldSource() {
		return (Transformation) oldEnd;
	}

	/**
	 * @generated
	 */
	protected Transformation getNewSource() {
		return (Transformation) newEnd;
	}

	/**
	 * @generated
	 */
	protected TransformationReference getOldTarget() {
		return (TransformationReference) oldEnd;
	}

	/**
	 * @generated
	 */
	protected TransformationReference getNewTarget() {
		return (TransformationReference) newEnd;
	}
}
