package net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.policies;

import java.util.Iterator;

import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.commands.CustomRequirementCreateCommand;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.commands.CustomRequirementReorientCommand;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.commands.ParentRequirementCreateCommand;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.commands.ParentRequirementReorientCommand;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.commands.SelfRequirementCreateCommand;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.commands.SelfRequirementReorientCommand;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.CustomRequirementEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.ParentRequirementEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.SelfRequirementEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.part.TransformationDependencyVisualIDRegistry;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.providers.TransformationDependencyElementTypes;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class TransformationItemSemanticEditPolicy extends TransformationDependencyBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public TransformationItemSemanticEditPolicy() {
		super(TransformationDependencyElementTypes.Transformation_3003);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		View view = (View) getHost().getModel();
		CompositeTransactionalCommand cmd = new CompositeTransactionalCommand(getEditingDomain(), null);
		cmd.setTransactionNestingEnabled(false);
		for (Iterator<?> it = view.getTargetEdges().iterator(); it.hasNext();) {
			Edge incomingLink = (Edge) it.next();
			if (TransformationDependencyVisualIDRegistry.getVisualID(incomingLink) == SelfRequirementEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(incomingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
			if (TransformationDependencyVisualIDRegistry.getVisualID(incomingLink) == ParentRequirementEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(incomingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
			if (TransformationDependencyVisualIDRegistry.getVisualID(incomingLink) == CustomRequirementEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(incomingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
		}
		for (Iterator<?> it = view.getSourceEdges().iterator(); it.hasNext();) {
			Edge outgoingLink = (Edge) it.next();
			if (TransformationDependencyVisualIDRegistry.getVisualID(outgoingLink) == SelfRequirementEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
			if (TransformationDependencyVisualIDRegistry.getVisualID(outgoingLink) == ParentRequirementEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
			if (TransformationDependencyVisualIDRegistry.getVisualID(outgoingLink) == CustomRequirementEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
		}
		EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
		if (annotation == null) {
			// there are indirectly referenced children, need extra commands: false
			addDestroyShortcutsCommand(cmd, view);
			// delete host element
			cmd.add(new DestroyElementCommand(req));
		} else {
			cmd.add(new DeleteCommand(getEditingDomain(), view));
		}
		return getGEFWrapper(cmd.reduce());
	}

	/**
	 * @generated
	 */
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
		Command command = req.getTarget() == null ? getStartCreateRelationshipCommand(req)
				: getCompleteCreateRelationshipCommand(req);
		return command != null ? command : super.getCreateRelationshipCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getStartCreateRelationshipCommand(CreateRelationshipRequest req) {
		if (TransformationDependencyElementTypes.SelfRequirement_4004 == req.getElementType()) {
			return getGEFWrapper(new SelfRequirementCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if (TransformationDependencyElementTypes.ParentRequirement_4005 == req.getElementType()) {
			return getGEFWrapper(new ParentRequirementCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if (TransformationDependencyElementTypes.CustomRequirement_4006 == req.getElementType()) {
			return getGEFWrapper(new CustomRequirementCreateCommand(req, req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(CreateRelationshipRequest req) {
		if (TransformationDependencyElementTypes.SelfRequirement_4004 == req.getElementType()) {
			return getGEFWrapper(new SelfRequirementCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if (TransformationDependencyElementTypes.ParentRequirement_4005 == req.getElementType()) {
			return getGEFWrapper(new ParentRequirementCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if (TransformationDependencyElementTypes.CustomRequirement_4006 == req.getElementType()) {
			return getGEFWrapper(new CustomRequirementCreateCommand(req, req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * Returns command to reorient EClass based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientRelationshipCommand(ReorientRelationshipRequest req) {
		switch (getVisualID(req)) {
		case SelfRequirementEditPart.VISUAL_ID:
			return getGEFWrapper(new SelfRequirementReorientCommand(req));
		case ParentRequirementEditPart.VISUAL_ID:
			return getGEFWrapper(new ParentRequirementReorientCommand(req));
		case CustomRequirementEditPart.VISUAL_ID:
			return getGEFWrapper(new CustomRequirementReorientCommand(req));
		}
		return super.getReorientRelationshipCommand(req);
	}

}
