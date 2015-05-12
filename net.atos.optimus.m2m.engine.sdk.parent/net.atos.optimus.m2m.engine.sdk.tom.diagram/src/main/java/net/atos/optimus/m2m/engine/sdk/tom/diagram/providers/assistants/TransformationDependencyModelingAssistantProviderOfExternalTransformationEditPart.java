package net.atos.optimus.m2m.engine.sdk.tom.diagram.providers.assistants;

import java.util.ArrayList;
import java.util.List;

import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.ExternalTransformationEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.providers.TransformationDependencyElementTypes;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.providers.TransformationDependencyModelingAssistantProvider;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

/**
 * @generated
 */
public class TransformationDependencyModelingAssistantProviderOfExternalTransformationEditPart extends
		TransformationDependencyModelingAssistantProvider {

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getRelTypesOnTarget(IAdaptable target) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnTarget((ExternalTransformationEditPart) targetEditPart);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetRelTypesOnTarget(ExternalTransformationEditPart target) {
		List<IElementType> types = new ArrayList<IElementType>(3);
		types.add(TransformationDependencyElementTypes.SelfRequirement_4004);
		types.add(TransformationDependencyElementTypes.ParentRequirement_4005);
		types.add(TransformationDependencyElementTypes.CustomRequirement_4006);
		return types;
	}

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getTypesForSource(IAdaptable target, IElementType relationshipType) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		return doGetTypesForSource((ExternalTransformationEditPart) targetEditPart, relationshipType);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetTypesForSource(ExternalTransformationEditPart target, IElementType relationshipType) {
		List<IElementType> types = new ArrayList<IElementType>();
		if (relationshipType == TransformationDependencyElementTypes.SelfRequirement_4004) {
			types.add(TransformationDependencyElementTypes.Transformation_3003);
			types.add(TransformationDependencyElementTypes.Transformation_3004);
		} else if (relationshipType == TransformationDependencyElementTypes.ParentRequirement_4005) {
			types.add(TransformationDependencyElementTypes.Transformation_3003);
			types.add(TransformationDependencyElementTypes.Transformation_3004);
		} else if (relationshipType == TransformationDependencyElementTypes.CustomRequirement_4006) {
			types.add(TransformationDependencyElementTypes.Transformation_3003);
			types.add(TransformationDependencyElementTypes.Transformation_3004);
		}
		return types;
	}

}
