package net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts;

import net.atos.optimus.m2m.engine.sdk.tom.diagram.part.TransformationDependencyVisualIDRegistry;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.directedit.locator.CellEditorLocatorAccess;

/**
 * @generated
 */
public class TransformationDependencyEditPartFactory implements EditPartFactory {

	/**
	 * @generated
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			switch (TransformationDependencyVisualIDRegistry.getVisualID(view)) {

			case TransformationSetsEditPart.VISUAL_ID:
				return new TransformationSetsEditPart(view);

			case TransformationSetEditPart.VISUAL_ID:
				return new TransformationSetEditPart(view);

			case TransformationSetNameEditPart.VISUAL_ID:
				return new TransformationSetNameEditPart(view);

			case TransformationSetExtensionEditPart.VISUAL_ID:
				return new TransformationSetExtensionEditPart(view);

			case TransformationSetExtensionTransformationSetIdEditPart.VISUAL_ID:
				return new TransformationSetExtensionTransformationSetIdEditPart(view);

			case ExternalTransformationEditPart.VISUAL_ID:
				return new ExternalTransformationEditPart(view);

			case ExternalTransformationNameEditPart.VISUAL_ID:
				return new ExternalTransformationNameEditPart(view);

			case TransformationEditPart.VISUAL_ID:
				return new TransformationEditPart(view);

			case TransformationNameEditPart.VISUAL_ID:
				return new TransformationNameEditPart(view);

			case Transformation2EditPart.VISUAL_ID:
				return new Transformation2EditPart(view);

			case TransformationName2EditPart.VISUAL_ID:
				return new TransformationName2EditPart(view);

			case TransformationSetTransformationSetCompartmentEditPart.VISUAL_ID:
				return new TransformationSetTransformationSetCompartmentEditPart(view);

			case TransformationSetExtensionTransformationSetExtensionCompartmentEditPart.VISUAL_ID:
				return new TransformationSetExtensionTransformationSetExtensionCompartmentEditPart(view);

			case SelfRequirementEditPart.VISUAL_ID:
				return new SelfRequirementEditPart(view);

			case SelfRequirementLabelEditPart.VISUAL_ID:
				return new SelfRequirementLabelEditPart(view);

			case ParentRequirementEditPart.VISUAL_ID:
				return new ParentRequirementEditPart(view);

			case ParentRequirementLabelEditPart.VISUAL_ID:
				return new ParentRequirementLabelEditPart(view);

			case CustomRequirementEditPart.VISUAL_ID:
				return new CustomRequirementEditPart(view);

			case CustomRequirementLabelEditPart.VISUAL_ID:
				return new CustomRequirementLabelEditPart(view);

			}
		}
		return createUnrecognizedEditPart(context, model);
	}

	/**
	 * @generated
	 */
	private EditPart createUnrecognizedEditPart(EditPart context, Object model) {
		// Handle creation of unrecognized child node EditParts here
		return null;
	}

	/**
	 * @generated
	 */
	public static CellEditorLocator getTextCellEditorLocator(ITextAwareEditPart source) {
		return CellEditorLocatorAccess.INSTANCE.getTextCellEditorLocator(source);
	}

}
