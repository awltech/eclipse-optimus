package net.atos.optimus.m2m.engine.sdk.tom.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.atos.optimus.m2m.engine.sdk.tom.CustomRequirement;
import net.atos.optimus.m2m.engine.sdk.tom.ExternalTransformation;
import net.atos.optimus.m2m.engine.sdk.tom.ParentRequirement;
import net.atos.optimus.m2m.engine.sdk.tom.SelfRequirement;
import net.atos.optimus.m2m.engine.sdk.tom.TomPackage;
import net.atos.optimus.m2m.engine.sdk.tom.Transformation;
import net.atos.optimus.m2m.engine.sdk.tom.TransformationReference;
import net.atos.optimus.m2m.engine.sdk.tom.TransformationSet;
import net.atos.optimus.m2m.engine.sdk.tom.TransformationSetExtension;
import net.atos.optimus.m2m.engine.sdk.tom.TransformationSets;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.CustomRequirementEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.ExternalTransformationEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.ParentRequirementEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.SelfRequirementEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.Transformation2EditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationSetEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationSetExtensionEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationSetExtensionTransformationSetExtensionCompartmentEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationSetTransformationSetCompartmentEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationSetsEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.providers.TransformationDependencyElementTypes;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.update.DiagramUpdater;

/**
 * @generated
 */
public class TransformationDependencyDiagramUpdater {

	/**
	 * @generated
	 */
	public static List<TransformationDependencyNodeDescriptor> getSemanticChildren(View view) {
		switch (TransformationDependencyVisualIDRegistry.getVisualID(view)) {
		case TransformationSetsEditPart.VISUAL_ID:
			return getTransformationSets_1000SemanticChildren(view);
		case TransformationSetTransformationSetCompartmentEditPart.VISUAL_ID:
			return getTransformationSetTransformationSetCompartment_7003SemanticChildren(view);
		case TransformationSetExtensionTransformationSetExtensionCompartmentEditPart.VISUAL_ID:
			return getTransformationSetExtensionTransformationSetExtensionCompartment_7004SemanticChildren(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<TransformationDependencyNodeDescriptor> getTransformationSets_1000SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		TransformationSets modelElement = (TransformationSets) view.getElement();
		LinkedList<TransformationDependencyNodeDescriptor> result = new LinkedList<TransformationDependencyNodeDescriptor>();
		for (Iterator<?> it = modelElement.getTransformationSets().iterator(); it.hasNext();) {
			TransformationSet childElement = (TransformationSet) it.next();
			int visualID = TransformationDependencyVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == TransformationSetEditPart.VISUAL_ID) {
				result.add(new TransformationDependencyNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getTransformationSetExtensions().iterator(); it.hasNext();) {
			TransformationSetExtension childElement = (TransformationSetExtension) it.next();
			int visualID = TransformationDependencyVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == TransformationSetExtensionEditPart.VISUAL_ID) {
				result.add(new TransformationDependencyNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getExternalTransformations().iterator(); it.hasNext();) {
			ExternalTransformation childElement = (ExternalTransformation) it.next();
			int visualID = TransformationDependencyVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == ExternalTransformationEditPart.VISUAL_ID) {
				result.add(new TransformationDependencyNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<TransformationDependencyNodeDescriptor> getTransformationSetTransformationSetCompartment_7003SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		TransformationSet modelElement = (TransformationSet) containerView.getElement();
		LinkedList<TransformationDependencyNodeDescriptor> result = new LinkedList<TransformationDependencyNodeDescriptor>();
		for (Iterator<?> it = modelElement.getTransformations().iterator(); it.hasNext();) {
			Transformation childElement = (Transformation) it.next();
			int visualID = TransformationDependencyVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == TransformationEditPart.VISUAL_ID) {
				result.add(new TransformationDependencyNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<TransformationDependencyNodeDescriptor> getTransformationSetExtensionTransformationSetExtensionCompartment_7004SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		TransformationSetExtension modelElement = (TransformationSetExtension) containerView.getElement();
		LinkedList<TransformationDependencyNodeDescriptor> result = new LinkedList<TransformationDependencyNodeDescriptor>();
		for (Iterator<?> it = modelElement.getTransformations().iterator(); it.hasNext();) {
			Transformation childElement = (Transformation) it.next();
			int visualID = TransformationDependencyVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == Transformation2EditPart.VISUAL_ID) {
				result.add(new TransformationDependencyNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<TransformationDependencyLinkDescriptor> getContainedLinks(View view) {
		switch (TransformationDependencyVisualIDRegistry.getVisualID(view)) {
		case TransformationSetsEditPart.VISUAL_ID:
			return getTransformationSets_1000ContainedLinks(view);
		case TransformationSetEditPart.VISUAL_ID:
			return getTransformationSet_2004ContainedLinks(view);
		case TransformationSetExtensionEditPart.VISUAL_ID:
			return getTransformationSetExtension_2005ContainedLinks(view);
		case ExternalTransformationEditPart.VISUAL_ID:
			return getExternalTransformation_2006ContainedLinks(view);
		case TransformationEditPart.VISUAL_ID:
			return getTransformation_3003ContainedLinks(view);
		case Transformation2EditPart.VISUAL_ID:
			return getTransformation_3004ContainedLinks(view);
		case SelfRequirementEditPart.VISUAL_ID:
			return getSelfRequirement_4004ContainedLinks(view);
		case ParentRequirementEditPart.VISUAL_ID:
			return getParentRequirement_4005ContainedLinks(view);
		case CustomRequirementEditPart.VISUAL_ID:
			return getCustomRequirement_4006ContainedLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<TransformationDependencyLinkDescriptor> getIncomingLinks(View view) {
		switch (TransformationDependencyVisualIDRegistry.getVisualID(view)) {
		case TransformationSetEditPart.VISUAL_ID:
			return getTransformationSet_2004IncomingLinks(view);
		case TransformationSetExtensionEditPart.VISUAL_ID:
			return getTransformationSetExtension_2005IncomingLinks(view);
		case ExternalTransformationEditPart.VISUAL_ID:
			return getExternalTransformation_2006IncomingLinks(view);
		case TransformationEditPart.VISUAL_ID:
			return getTransformation_3003IncomingLinks(view);
		case Transformation2EditPart.VISUAL_ID:
			return getTransformation_3004IncomingLinks(view);
		case SelfRequirementEditPart.VISUAL_ID:
			return getSelfRequirement_4004IncomingLinks(view);
		case ParentRequirementEditPart.VISUAL_ID:
			return getParentRequirement_4005IncomingLinks(view);
		case CustomRequirementEditPart.VISUAL_ID:
			return getCustomRequirement_4006IncomingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<TransformationDependencyLinkDescriptor> getOutgoingLinks(View view) {
		switch (TransformationDependencyVisualIDRegistry.getVisualID(view)) {
		case TransformationSetEditPart.VISUAL_ID:
			return getTransformationSet_2004OutgoingLinks(view);
		case TransformationSetExtensionEditPart.VISUAL_ID:
			return getTransformationSetExtension_2005OutgoingLinks(view);
		case ExternalTransformationEditPart.VISUAL_ID:
			return getExternalTransformation_2006OutgoingLinks(view);
		case TransformationEditPart.VISUAL_ID:
			return getTransformation_3003OutgoingLinks(view);
		case Transformation2EditPart.VISUAL_ID:
			return getTransformation_3004OutgoingLinks(view);
		case SelfRequirementEditPart.VISUAL_ID:
			return getSelfRequirement_4004OutgoingLinks(view);
		case ParentRequirementEditPart.VISUAL_ID:
			return getParentRequirement_4005OutgoingLinks(view);
		case CustomRequirementEditPart.VISUAL_ID:
			return getCustomRequirement_4006OutgoingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<TransformationDependencyLinkDescriptor> getTransformationSets_1000ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<TransformationDependencyLinkDescriptor> getTransformationSet_2004ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<TransformationDependencyLinkDescriptor> getTransformationSetExtension_2005ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<TransformationDependencyLinkDescriptor> getExternalTransformation_2006ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<TransformationDependencyLinkDescriptor> getTransformation_3003ContainedLinks(View view) {
		Transformation modelElement = (Transformation) view.getElement();
		LinkedList<TransformationDependencyLinkDescriptor> result = new LinkedList<TransformationDependencyLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_SelfRequirement_4004(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_ParentRequirement_4005(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_CustomRequirement_4006(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<TransformationDependencyLinkDescriptor> getTransformation_3004ContainedLinks(View view) {
		Transformation modelElement = (Transformation) view.getElement();
		LinkedList<TransformationDependencyLinkDescriptor> result = new LinkedList<TransformationDependencyLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_SelfRequirement_4004(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_ParentRequirement_4005(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_CustomRequirement_4006(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<TransformationDependencyLinkDescriptor> getSelfRequirement_4004ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<TransformationDependencyLinkDescriptor> getParentRequirement_4005ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<TransformationDependencyLinkDescriptor> getCustomRequirement_4006ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<TransformationDependencyLinkDescriptor> getTransformationSet_2004IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<TransformationDependencyLinkDescriptor> getTransformationSetExtension_2005IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<TransformationDependencyLinkDescriptor> getExternalTransformation_2006IncomingLinks(View view) {
		ExternalTransformation modelElement = (ExternalTransformation) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer.find(view
				.eResource().getResourceSet().getResources());
		LinkedList<TransformationDependencyLinkDescriptor> result = new LinkedList<TransformationDependencyLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SelfRequirement_4004(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_ParentRequirement_4005(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_CustomRequirement_4006(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<TransformationDependencyLinkDescriptor> getTransformation_3003IncomingLinks(View view) {
		Transformation modelElement = (Transformation) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer.find(view
				.eResource().getResourceSet().getResources());
		LinkedList<TransformationDependencyLinkDescriptor> result = new LinkedList<TransformationDependencyLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SelfRequirement_4004(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_ParentRequirement_4005(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_CustomRequirement_4006(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<TransformationDependencyLinkDescriptor> getTransformation_3004IncomingLinks(View view) {
		Transformation modelElement = (Transformation) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer.find(view
				.eResource().getResourceSet().getResources());
		LinkedList<TransformationDependencyLinkDescriptor> result = new LinkedList<TransformationDependencyLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SelfRequirement_4004(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_ParentRequirement_4005(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_CustomRequirement_4006(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<TransformationDependencyLinkDescriptor> getSelfRequirement_4004IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<TransformationDependencyLinkDescriptor> getParentRequirement_4005IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<TransformationDependencyLinkDescriptor> getCustomRequirement_4006IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<TransformationDependencyLinkDescriptor> getTransformationSet_2004OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<TransformationDependencyLinkDescriptor> getTransformationSetExtension_2005OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<TransformationDependencyLinkDescriptor> getExternalTransformation_2006OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<TransformationDependencyLinkDescriptor> getTransformation_3003OutgoingLinks(View view) {
		Transformation modelElement = (Transformation) view.getElement();
		LinkedList<TransformationDependencyLinkDescriptor> result = new LinkedList<TransformationDependencyLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_SelfRequirement_4004(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_ParentRequirement_4005(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_CustomRequirement_4006(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<TransformationDependencyLinkDescriptor> getTransformation_3004OutgoingLinks(View view) {
		Transformation modelElement = (Transformation) view.getElement();
		LinkedList<TransformationDependencyLinkDescriptor> result = new LinkedList<TransformationDependencyLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_SelfRequirement_4004(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_ParentRequirement_4005(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_CustomRequirement_4006(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<TransformationDependencyLinkDescriptor> getSelfRequirement_4004OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<TransformationDependencyLinkDescriptor> getParentRequirement_4005OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<TransformationDependencyLinkDescriptor> getCustomRequirement_4006OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	private static Collection<TransformationDependencyLinkDescriptor> getContainedTypeModelFacetLinks_SelfRequirement_4004(
			Transformation container) {
		LinkedList<TransformationDependencyLinkDescriptor> result = new LinkedList<TransformationDependencyLinkDescriptor>();
		for (Iterator<?> links = container.getRequirements().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof SelfRequirement) {
				continue;
			}
			SelfRequirement link = (SelfRequirement) linkObject;
			if (SelfRequirementEditPart.VISUAL_ID != TransformationDependencyVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			TransformationReference dst = link.getReference();
			result.add(new TransformationDependencyLinkDescriptor(container, dst, link,
					TransformationDependencyElementTypes.SelfRequirement_4004, SelfRequirementEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<TransformationDependencyLinkDescriptor> getContainedTypeModelFacetLinks_ParentRequirement_4005(
			Transformation container) {
		LinkedList<TransformationDependencyLinkDescriptor> result = new LinkedList<TransformationDependencyLinkDescriptor>();
		for (Iterator<?> links = container.getRequirements().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof ParentRequirement) {
				continue;
			}
			ParentRequirement link = (ParentRequirement) linkObject;
			if (ParentRequirementEditPart.VISUAL_ID != TransformationDependencyVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			TransformationReference dst = link.getReference();
			result.add(new TransformationDependencyLinkDescriptor(container, dst, link,
					TransformationDependencyElementTypes.ParentRequirement_4005, ParentRequirementEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<TransformationDependencyLinkDescriptor> getContainedTypeModelFacetLinks_CustomRequirement_4006(
			Transformation container) {
		LinkedList<TransformationDependencyLinkDescriptor> result = new LinkedList<TransformationDependencyLinkDescriptor>();
		for (Iterator<?> links = container.getRequirements().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof CustomRequirement) {
				continue;
			}
			CustomRequirement link = (CustomRequirement) linkObject;
			if (CustomRequirementEditPart.VISUAL_ID != TransformationDependencyVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			TransformationReference dst = link.getReference();
			result.add(new TransformationDependencyLinkDescriptor(container, dst, link,
					TransformationDependencyElementTypes.CustomRequirement_4006, CustomRequirementEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<TransformationDependencyLinkDescriptor> getIncomingTypeModelFacetLinks_SelfRequirement_4004(
			TransformationReference target, Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<TransformationDependencyLinkDescriptor> result = new LinkedList<TransformationDependencyLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != TomPackage.eINSTANCE.getRequirement_Reference()
					|| false == setting.getEObject() instanceof SelfRequirement) {
				continue;
			}
			SelfRequirement link = (SelfRequirement) setting.getEObject();
			if (SelfRequirementEditPart.VISUAL_ID != TransformationDependencyVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			if (false == link.eContainer() instanceof Transformation) {
				continue;
			}
			Transformation container = (Transformation) link.eContainer();
			result.add(new TransformationDependencyLinkDescriptor(container, target, link,
					TransformationDependencyElementTypes.SelfRequirement_4004, SelfRequirementEditPart.VISUAL_ID));

		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<TransformationDependencyLinkDescriptor> getIncomingTypeModelFacetLinks_ParentRequirement_4005(
			TransformationReference target, Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<TransformationDependencyLinkDescriptor> result = new LinkedList<TransformationDependencyLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != TomPackage.eINSTANCE.getRequirement_Reference()
					|| false == setting.getEObject() instanceof ParentRequirement) {
				continue;
			}
			ParentRequirement link = (ParentRequirement) setting.getEObject();
			if (ParentRequirementEditPart.VISUAL_ID != TransformationDependencyVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			if (false == link.eContainer() instanceof Transformation) {
				continue;
			}
			Transformation container = (Transformation) link.eContainer();
			result.add(new TransformationDependencyLinkDescriptor(container, target, link,
					TransformationDependencyElementTypes.ParentRequirement_4005, ParentRequirementEditPart.VISUAL_ID));

		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<TransformationDependencyLinkDescriptor> getIncomingTypeModelFacetLinks_CustomRequirement_4006(
			TransformationReference target, Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<TransformationDependencyLinkDescriptor> result = new LinkedList<TransformationDependencyLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != TomPackage.eINSTANCE.getRequirement_Reference()
					|| false == setting.getEObject() instanceof CustomRequirement) {
				continue;
			}
			CustomRequirement link = (CustomRequirement) setting.getEObject();
			if (CustomRequirementEditPart.VISUAL_ID != TransformationDependencyVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			if (false == link.eContainer() instanceof Transformation) {
				continue;
			}
			Transformation container = (Transformation) link.eContainer();
			result.add(new TransformationDependencyLinkDescriptor(container, target, link,
					TransformationDependencyElementTypes.CustomRequirement_4006, CustomRequirementEditPart.VISUAL_ID));

		}
		return result;
	}

	/**
	 * @generated
	 */
	public static final DiagramUpdater TYPED_INSTANCE = new DiagramUpdater() {
		/**
		 * @generated
		 */
		@Override
		public List<TransformationDependencyNodeDescriptor> getSemanticChildren(View view) {
			return TransformationDependencyDiagramUpdater.getSemanticChildren(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<TransformationDependencyLinkDescriptor> getContainedLinks(View view) {
			return TransformationDependencyDiagramUpdater.getContainedLinks(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<TransformationDependencyLinkDescriptor> getIncomingLinks(View view) {
			return TransformationDependencyDiagramUpdater.getIncomingLinks(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<TransformationDependencyLinkDescriptor> getOutgoingLinks(View view) {
			return TransformationDependencyDiagramUpdater.getOutgoingLinks(view);
		}
	};

}
