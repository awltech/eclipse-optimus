package net.atos.optimus.m2m.engine.sdk.tom.diagram.navigator;

import net.atos.optimus.m2m.engine.sdk.tom.diagram.part.TransformationDependencyVisualIDRegistry;

import org.eclipse.jface.viewers.ViewerSorter;

/**
 * @generated
 */
public class TransformationDependencyNavigatorSorter extends ViewerSorter {

	/**
	 * @generated
	 */
	private static final int GROUP_CATEGORY = 7006;

	/**
	 * @generated
	 */
	public int category(Object element) {
		if (element instanceof TransformationDependencyNavigatorItem) {
			TransformationDependencyNavigatorItem item = (TransformationDependencyNavigatorItem) element;
			return TransformationDependencyVisualIDRegistry.getVisualID(item.getView());
		}
		return GROUP_CATEGORY;
	}

}
