package net.atos.optimus.m2m.engine.sdk.tom.diagram.providers;

import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationDependencyEditPartFactory;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationSetsEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.part.TransformationDependencyVisualIDRegistry;

import org.eclipse.gmf.tooling.runtime.providers.DefaultEditPartProvider;

/**
 * @generated
 */
public class TransformationDependencyEditPartProvider extends DefaultEditPartProvider {

	/**
	 * @generated
	 */
	public TransformationDependencyEditPartProvider() {
		super(new TransformationDependencyEditPartFactory(), TransformationDependencyVisualIDRegistry.TYPED_INSTANCE,
				TransformationSetsEditPart.MODEL_ID);
	}

}
