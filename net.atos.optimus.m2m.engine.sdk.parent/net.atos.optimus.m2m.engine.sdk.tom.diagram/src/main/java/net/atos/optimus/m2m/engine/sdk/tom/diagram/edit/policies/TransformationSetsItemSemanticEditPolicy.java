package net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.policies;

import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.commands.ExternalTransformationCreateCommand;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.commands.TransformationSetCreateCommand;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.commands.TransformationSetExtensionCreateCommand;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.providers.TransformationDependencyElementTypes;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;

/**
 * @generated
 */
public class TransformationSetsItemSemanticEditPolicy extends TransformationDependencyBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public TransformationSetsItemSemanticEditPolicy() {
		super(TransformationDependencyElementTypes.TransformationSets_1000);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (TransformationDependencyElementTypes.TransformationSet_2004 == req.getElementType()) {
			return getGEFWrapper(new TransformationSetCreateCommand(req));
		}
		if (TransformationDependencyElementTypes.TransformationSetExtension_2005 == req.getElementType()) {
			return getGEFWrapper(new TransformationSetExtensionCreateCommand(req));
		}
		if (TransformationDependencyElementTypes.ExternalTransformation_2006 == req.getElementType()) {
			return getGEFWrapper(new ExternalTransformationCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getDuplicateCommand(DuplicateElementsRequest req) {
		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
		return getGEFWrapper(new DuplicateAnythingCommand(editingDomain, req));
	}

	/**
	 * @generated
	 */
	private static class DuplicateAnythingCommand extends DuplicateEObjectsCommand {

		/**
		 * @generated
		 */
		public DuplicateAnythingCommand(TransactionalEditingDomain editingDomain, DuplicateElementsRequest req) {
			super(editingDomain, req.getLabel(), req.getElementsToBeDuplicated(), req.getAllDuplicatedElementsMap());
		}

	}

}
