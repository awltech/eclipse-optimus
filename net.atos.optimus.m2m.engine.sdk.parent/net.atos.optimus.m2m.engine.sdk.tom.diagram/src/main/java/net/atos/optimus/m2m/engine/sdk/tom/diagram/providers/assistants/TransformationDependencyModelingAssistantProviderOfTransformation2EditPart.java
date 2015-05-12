package net.atos.optimus.m2m.engine.sdk.tom.diagram.providers.assistants;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.ExternalTransformationEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.Transformation2EditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.providers.TransformationDependencyElementTypes;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.providers.TransformationDependencyModelingAssistantProvider;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

/**
 * @generated
 */
public class TransformationDependencyModelingAssistantProviderOfTransformation2EditPart extends
		TransformationDependencyModelingAssistantProvider {

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getRelTypesOnSource(IAdaptable source) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnSource((Transformation2EditPart) sourceEditPart);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetRelTypesOnSource(Transformation2EditPart source) {
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
	public List<IElementType> getRelTypesOnSourceAndTarget(IAdaptable source, IAdaptable target) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnSourceAndTarget((Transformation2EditPart) sourceEditPart, targetEditPart);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetRelTypesOnSourceAndTarget(Transformation2EditPart source,
			IGraphicalEditPart targetEditPart) {
		List<IElementType> types = new LinkedList<IElementType>();
		if (targetEditPart instanceof ExternalTransformationEditPart) {
			types.add(TransformationDependencyElementTypes.SelfRequirement_4004);
		}
		if (targetEditPart instanceof TransformationEditPart) {
			types.add(TransformationDependencyElementTypes.SelfRequirement_4004);
		}
		if (targetEditPart instanceof Transformation2EditPart) {
			types.add(TransformationDependencyElementTypes.SelfRequirement_4004);
		}
		if (targetEditPart instanceof ExternalTransformationEditPart) {
			types.add(TransformationDependencyElementTypes.ParentRequirement_4005);
		}
		if (targetEditPart instanceof TransformationEditPart) {
			types.add(TransformationDependencyElementTypes.ParentRequirement_4005);
		}
		if (targetEditPart instanceof Transformation2EditPart) {
			types.add(TransformationDependencyElementTypes.ParentRequirement_4005);
		}
		if (targetEditPart instanceof ExternalTransformationEditPart) {
			types.add(TransformationDependencyElementTypes.CustomRequirement_4006);
		}
		if (targetEditPart instanceof TransformationEditPart) {
			types.add(TransformationDependencyElementTypes.CustomRequirement_4006);
		}
		if (targetEditPart instanceof Transformation2EditPart) {
			types.add(TransformationDependencyElementTypes.CustomRequirement_4006);
		}
		return types;
	}

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getTypesForTarget(IAdaptable source, IElementType relationshipType) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		return doGetTypesForTarget((Transformation2EditPart) sourceEditPart, relationshipType);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetTypesForTarget(Transformation2EditPart source, IElementType relationshipType) {
		List<IElementType> types = new ArrayList<IElementType>();
		if (relationshipType == TransformationDependencyElementTypes.SelfRequirement_4004) {
			types.add(TransformationDependencyElementTypes.ExternalTransformation_2006);
			types.add(TransformationDependencyElementTypes.Transformation_3003);
			types.add(TransformationDependencyElementTypes.Transformation_3004);
		} else if (relationshipType == TransformationDependencyElementTypes.ParentRequirement_4005) {
			types.add(TransformationDependencyElementTypes.ExternalTransformation_2006);
			types.add(TransformationDependencyElementTypes.Transformation_3003);
			types.add(TransformationDependencyElementTypes.Transformation_3004);
		} else if (relationshipType == TransformationDependencyElementTypes.CustomRequirement_4006) {
			types.add(TransformationDependencyElementTypes.ExternalTransformation_2006);
			types.add(TransformationDependencyElementTypes.Transformation_3003);
			types.add(TransformationDependencyElementTypes.Transformation_3004);
		}
		return types;
	}

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getRelTypesOnTarget(IAdaptable target) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnTarget((Transformation2EditPart) targetEditPart);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetRelTypesOnTarget(Transformation2EditPart target) {
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
		return doGetTypesForSource((Transformation2EditPart) targetEditPart, relationshipType);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetTypesForSource(Transformation2EditPart target, IElementType relationshipType) {
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
