package net.atos.optimus.m2m.engine.sdk.tom.diagram.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import net.atos.optimus.m2m.engine.sdk.tom.TomPackage;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.CustomRequirementEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.ExternalTransformationEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.ParentRequirementEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.SelfRequirementEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.Transformation2EditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationSetEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationSetExtensionEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationSetsEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.part.TransformationDependencyDiagramEditorPlugin;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.tooling.runtime.providers.DiagramElementTypeImages;
import org.eclipse.gmf.tooling.runtime.providers.DiagramElementTypes;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

/**
 * @generated
 */
public class TransformationDependencyElementTypes {

	/**
	 * @generated
	 */
	private TransformationDependencyElementTypes() {
	}

	/**
	 * @generated
	 */
	private static Map<IElementType, ENamedElement> elements;

	/**
	 * @generated
	 */
	private static DiagramElementTypeImages elementTypeImages = new DiagramElementTypeImages(
			TransformationDependencyDiagramEditorPlugin.getInstance().getItemProvidersAdapterFactory());

	/**
	 * @generated
	 */
	private static Set<IElementType> KNOWN_ELEMENT_TYPES;

	/**
	 * @generated
	 */
	public static final IElementType TransformationSets_1000 = getElementType("net.atos.optimus.m2m.engine.sdk.tom.diagram.TransformationSets_1000"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType TransformationSet_2004 = getElementType("net.atos.optimus.m2m.engine.sdk.tom.diagram.TransformationSet_2004"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType TransformationSetExtension_2005 = getElementType("net.atos.optimus.m2m.engine.sdk.tom.diagram.TransformationSetExtension_2005"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType ExternalTransformation_2006 = getElementType("net.atos.optimus.m2m.engine.sdk.tom.diagram.ExternalTransformation_2006"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Transformation_3003 = getElementType("net.atos.optimus.m2m.engine.sdk.tom.diagram.Transformation_3003"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Transformation_3004 = getElementType("net.atos.optimus.m2m.engine.sdk.tom.diagram.Transformation_3004"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType SelfRequirement_4004 = getElementType("net.atos.optimus.m2m.engine.sdk.tom.diagram.SelfRequirement_4004"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType ParentRequirement_4005 = getElementType("net.atos.optimus.m2m.engine.sdk.tom.diagram.ParentRequirement_4005"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType CustomRequirement_4006 = getElementType("net.atos.optimus.m2m.engine.sdk.tom.diagram.CustomRequirement_4006"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(ENamedElement element) {
		return elementTypeImages.getImageDescriptor(element);
	}

	/**
	 * @generated
	 */
	public static Image getImage(ENamedElement element) {
		return elementTypeImages.getImage(element);
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
		return getImageDescriptor(getElement(hint));
	}

	/**
	 * @generated
	 */
	public static Image getImage(IAdaptable hint) {
		return getImage(getElement(hint));
	}

	/**
	 * Returns 'type' of the ecore object associated with the hint.
	 * 
	 * @generated
	 */
	public static ENamedElement getElement(IAdaptable hint) {
		Object type = hint.getAdapter(IElementType.class);
		if (elements == null) {
			elements = new IdentityHashMap<IElementType, ENamedElement>();

			elements.put(TransformationSets_1000, TomPackage.eINSTANCE.getTransformationSets());

			elements.put(TransformationSet_2004, TomPackage.eINSTANCE.getTransformationSet());

			elements.put(TransformationSetExtension_2005, TomPackage.eINSTANCE.getTransformationSetExtension());

			elements.put(ExternalTransformation_2006, TomPackage.eINSTANCE.getExternalTransformation());

			elements.put(Transformation_3003, TomPackage.eINSTANCE.getTransformation());

			elements.put(Transformation_3004, TomPackage.eINSTANCE.getTransformation());

			elements.put(SelfRequirement_4004, TomPackage.eINSTANCE.getSelfRequirement());

			elements.put(ParentRequirement_4005, TomPackage.eINSTANCE.getParentRequirement());

			elements.put(CustomRequirement_4006, TomPackage.eINSTANCE.getCustomRequirement());
		}
		return (ENamedElement) elements.get(type);
	}

	/**
	 * @generated
	 */
	private static IElementType getElementType(String id) {
		return ElementTypeRegistry.getInstance().getType(id);
	}

	/**
	 * @generated
	 */
	public static boolean isKnownElementType(IElementType elementType) {
		if (KNOWN_ELEMENT_TYPES == null) {
			KNOWN_ELEMENT_TYPES = new HashSet<IElementType>();
			KNOWN_ELEMENT_TYPES.add(TransformationSets_1000);
			KNOWN_ELEMENT_TYPES.add(TransformationSet_2004);
			KNOWN_ELEMENT_TYPES.add(TransformationSetExtension_2005);
			KNOWN_ELEMENT_TYPES.add(ExternalTransformation_2006);
			KNOWN_ELEMENT_TYPES.add(Transformation_3003);
			KNOWN_ELEMENT_TYPES.add(Transformation_3004);
			KNOWN_ELEMENT_TYPES.add(SelfRequirement_4004);
			KNOWN_ELEMENT_TYPES.add(ParentRequirement_4005);
			KNOWN_ELEMENT_TYPES.add(CustomRequirement_4006);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	 * @generated
	 */
	public static IElementType getElementType(int visualID) {
		switch (visualID) {
		case TransformationSetsEditPart.VISUAL_ID:
			return TransformationSets_1000;
		case TransformationSetEditPart.VISUAL_ID:
			return TransformationSet_2004;
		case TransformationSetExtensionEditPart.VISUAL_ID:
			return TransformationSetExtension_2005;
		case ExternalTransformationEditPart.VISUAL_ID:
			return ExternalTransformation_2006;
		case TransformationEditPart.VISUAL_ID:
			return Transformation_3003;
		case Transformation2EditPart.VISUAL_ID:
			return Transformation_3004;
		case SelfRequirementEditPart.VISUAL_ID:
			return SelfRequirement_4004;
		case ParentRequirementEditPart.VISUAL_ID:
			return ParentRequirement_4005;
		case CustomRequirementEditPart.VISUAL_ID:
			return CustomRequirement_4006;
		}
		return null;
	}

	/**
	 * @generated
	 */
	public static final DiagramElementTypes TYPED_INSTANCE = new DiagramElementTypes(elementTypeImages) {

		/**
		 * @generated
		 */
		@Override
		public boolean isKnownElementType(IElementType elementType) {
			return net.atos.optimus.m2m.engine.sdk.tom.diagram.providers.TransformationDependencyElementTypes
					.isKnownElementType(elementType);
		}

		/**
		 * @generated
		 */
		@Override
		public IElementType getElementTypeForVisualId(int visualID) {
			return net.atos.optimus.m2m.engine.sdk.tom.diagram.providers.TransformationDependencyElementTypes
					.getElementType(visualID);
		}

		/**
		 * @generated
		 */
		@Override
		public ENamedElement getDefiningNamedElement(IAdaptable elementTypeAdapter) {
			return net.atos.optimus.m2m.engine.sdk.tom.diagram.providers.TransformationDependencyElementTypes
					.getElement(elementTypeAdapter);
		}
	};

}
