package net.atos.optimus.m2m.engine.sdk.tom.diagram.providers.assistants;

import java.util.ArrayList;
import java.util.List;

import net.atos.optimus.m2m.engine.sdk.tom.diagram.providers.TransformationDependencyElementTypes;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.providers.TransformationDependencyModelingAssistantProvider;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

/**
 * @generated
 */
public class TransformationDependencyModelingAssistantProviderOfTransformationSetsEditPart extends
		TransformationDependencyModelingAssistantProvider {

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getTypesForPopupBar(IAdaptable host) {
		List<IElementType> types = new ArrayList<IElementType>(3);
		types.add(TransformationDependencyElementTypes.TransformationSet_2004);
		types.add(TransformationDependencyElementTypes.TransformationSetExtension_2005);
		types.add(TransformationDependencyElementTypes.ExternalTransformation_2006);
		return types;
	}

}
