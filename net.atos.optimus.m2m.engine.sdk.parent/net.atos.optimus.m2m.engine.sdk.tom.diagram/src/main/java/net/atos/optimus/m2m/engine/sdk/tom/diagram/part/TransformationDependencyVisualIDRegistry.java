package net.atos.optimus.m2m.engine.sdk.tom.diagram.part;

import net.atos.optimus.m2m.engine.sdk.tom.TomPackage;
import net.atos.optimus.m2m.engine.sdk.tom.TransformationSets;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.CustomRequirementEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.CustomRequirementLabelEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.ExternalTransformationEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.ExternalTransformationNameEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.ParentRequirementEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.ParentRequirementLabelEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.SelfRequirementEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.SelfRequirementLabelEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.Transformation2EditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationName2EditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationNameEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationSetEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationSetExtensionEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationSetExtensionTransformationSetExtensionCompartmentEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationSetExtensionTransformationSetIdEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationSetNameEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationSetTransformationSetCompartmentEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationSetsEditPart;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.structure.DiagramStructure;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class TransformationDependencyVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "net.atos.optimus.m2m.engine.sdk.tom.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (TransformationSetsEditPart.MODEL_ID.equals(view.getType())) {
				return TransformationSetsEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return net.atos.optimus.m2m.engine.sdk.tom.diagram.part.TransformationDependencyVisualIDRegistry
				.getVisualID(view.getType());
	}

	/**
	 * @generated
	 */
	public static String getModelID(View view) {
		View diagram = view.getDiagram();
		while (view != diagram) {
			EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
			if (annotation != null) {
				return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
			}
			view = (View) view.eContainer();
		}
		return diagram != null ? diagram.getType() : null;
	}

	/**
	 * @generated
	 */
	public static int getVisualID(String type) {
		try {
			return Integer.parseInt(type);
		} catch (NumberFormatException e) {
			if (Boolean.TRUE.toString().equalsIgnoreCase(Platform.getDebugOption(DEBUG_KEY))) {
				TransformationDependencyDiagramEditorPlugin.getInstance().logError(
						"Unable to parse view type as a visualID number: " + type);
			}
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static String getType(int visualID) {
		return Integer.toString(visualID);
	}

	/**
	 * @generated
	 */
	public static int getDiagramVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (TomPackage.eINSTANCE.getTransformationSets().isSuperTypeOf(domainElement.eClass())
				&& isDiagram((TransformationSets) domainElement)) {
			return TransformationSetsEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static int getNodeVisualID(View containerView, EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		String containerModelID = net.atos.optimus.m2m.engine.sdk.tom.diagram.part.TransformationDependencyVisualIDRegistry
				.getModelID(containerView);
		if (!TransformationSetsEditPart.MODEL_ID.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (TransformationSetsEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = net.atos.optimus.m2m.engine.sdk.tom.diagram.part.TransformationDependencyVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = TransformationSetsEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case TransformationSetsEditPart.VISUAL_ID:
			if (TomPackage.eINSTANCE.getTransformationSet().isSuperTypeOf(domainElement.eClass())) {
				return TransformationSetEditPart.VISUAL_ID;
			}
			if (TomPackage.eINSTANCE.getTransformationSetExtension().isSuperTypeOf(domainElement.eClass())) {
				return TransformationSetExtensionEditPart.VISUAL_ID;
			}
			if (TomPackage.eINSTANCE.getExternalTransformation().isSuperTypeOf(domainElement.eClass())) {
				return ExternalTransformationEditPart.VISUAL_ID;
			}
			break;
		case TransformationSetTransformationSetCompartmentEditPart.VISUAL_ID:
			if (TomPackage.eINSTANCE.getTransformation().isSuperTypeOf(domainElement.eClass())) {
				return TransformationEditPart.VISUAL_ID;
			}
			break;
		case TransformationSetExtensionTransformationSetExtensionCompartmentEditPart.VISUAL_ID:
			if (TomPackage.eINSTANCE.getTransformation().isSuperTypeOf(domainElement.eClass())) {
				return Transformation2EditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = net.atos.optimus.m2m.engine.sdk.tom.diagram.part.TransformationDependencyVisualIDRegistry
				.getModelID(containerView);
		if (!TransformationSetsEditPart.MODEL_ID.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (TransformationSetsEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = net.atos.optimus.m2m.engine.sdk.tom.diagram.part.TransformationDependencyVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = TransformationSetsEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case TransformationSetsEditPart.VISUAL_ID:
			if (TransformationSetEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TransformationSetExtensionEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ExternalTransformationEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case TransformationSetEditPart.VISUAL_ID:
			if (TransformationSetNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TransformationSetTransformationSetCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case TransformationSetExtensionEditPart.VISUAL_ID:
			if (TransformationSetExtensionTransformationSetIdEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TransformationSetExtensionTransformationSetExtensionCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ExternalTransformationEditPart.VISUAL_ID:
			if (ExternalTransformationNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case TransformationEditPart.VISUAL_ID:
			if (TransformationNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case Transformation2EditPart.VISUAL_ID:
			if (TransformationName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case TransformationSetTransformationSetCompartmentEditPart.VISUAL_ID:
			if (TransformationEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case TransformationSetExtensionTransformationSetExtensionCompartmentEditPart.VISUAL_ID:
			if (Transformation2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case SelfRequirementEditPart.VISUAL_ID:
			if (SelfRequirementLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ParentRequirementEditPart.VISUAL_ID:
			if (ParentRequirementLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case CustomRequirementEditPart.VISUAL_ID:
			if (CustomRequirementLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static int getLinkWithClassVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (TomPackage.eINSTANCE.getSelfRequirement().isSuperTypeOf(domainElement.eClass())) {
			return SelfRequirementEditPart.VISUAL_ID;
		}
		if (TomPackage.eINSTANCE.getParentRequirement().isSuperTypeOf(domainElement.eClass())) {
			return ParentRequirementEditPart.VISUAL_ID;
		}
		if (TomPackage.eINSTANCE.getCustomRequirement().isSuperTypeOf(domainElement.eClass())) {
			return CustomRequirementEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(TransformationSets element) {
		return true;
	}

	/**
	 * @generated
	 */
	public static boolean checkNodeVisualID(View containerView, EObject domainElement, int candidate) {
		if (candidate == -1) {
			//unrecognized id is always bad
			return false;
		}
		int basic = getNodeVisualID(containerView, domainElement);
		return basic == candidate;
	}

	/**
	 * @generated
	 */
	public static boolean isCompartmentVisualID(int visualID) {
		switch (visualID) {
		case TransformationSetTransformationSetCompartmentEditPart.VISUAL_ID:
		case TransformationSetExtensionTransformationSetExtensionCompartmentEditPart.VISUAL_ID:
			return true;
		default:
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static boolean isSemanticLeafVisualID(int visualID) {
		switch (visualID) {
		case TransformationSetsEditPart.VISUAL_ID:
			return false;
		case ExternalTransformationEditPart.VISUAL_ID:
		case TransformationEditPart.VISUAL_ID:
		case Transformation2EditPart.VISUAL_ID:
			return true;
		default:
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static final DiagramStructure TYPED_INSTANCE = new DiagramStructure() {
		/**
		 * @generated
		 */
		@Override
		public int getVisualID(View view) {
			return net.atos.optimus.m2m.engine.sdk.tom.diagram.part.TransformationDependencyVisualIDRegistry
					.getVisualID(view);
		}

		/**
		 * @generated
		 */
		@Override
		public String getModelID(View view) {
			return net.atos.optimus.m2m.engine.sdk.tom.diagram.part.TransformationDependencyVisualIDRegistry
					.getModelID(view);
		}

		/**
		 * @generated
		 */
		@Override
		public int getNodeVisualID(View containerView, EObject domainElement) {
			return net.atos.optimus.m2m.engine.sdk.tom.diagram.part.TransformationDependencyVisualIDRegistry
					.getNodeVisualID(containerView, domainElement);
		}

		/**
		 * @generated
		 */
		@Override
		public boolean checkNodeVisualID(View containerView, EObject domainElement, int candidate) {
			return net.atos.optimus.m2m.engine.sdk.tom.diagram.part.TransformationDependencyVisualIDRegistry
					.checkNodeVisualID(containerView, domainElement, candidate);
		}

		/**
		 * @generated
		 */
		@Override
		public boolean isCompartmentVisualID(int visualID) {
			return net.atos.optimus.m2m.engine.sdk.tom.diagram.part.TransformationDependencyVisualIDRegistry
					.isCompartmentVisualID(visualID);
		}

		/**
		 * @generated
		 */
		@Override
		public boolean isSemanticLeafVisualID(int visualID) {
			return net.atos.optimus.m2m.engine.sdk.tom.diagram.part.TransformationDependencyVisualIDRegistry
					.isSemanticLeafVisualID(visualID);
		}
	};

}
