package net.atos.optimus.m2m.engine.sdk.tom.diagram.providers;

import net.atos.optimus.m2m.engine.sdk.tom.diagram.part.TransformationDependencyDiagramEditorPlugin;

/**
 * @generated
 */
public class ElementInitializers {

	protected ElementInitializers() {
		// use #getInstance to access cached instance
	}

	/**
	 * @generated
	 */
	public static ElementInitializers getInstance() {
		ElementInitializers cached = TransformationDependencyDiagramEditorPlugin.getInstance().getElementInitializers();
		if (cached == null) {
			TransformationDependencyDiagramEditorPlugin.getInstance().setElementInitializers(
					cached = new ElementInitializers());
		}
		return cached;
	}
}
