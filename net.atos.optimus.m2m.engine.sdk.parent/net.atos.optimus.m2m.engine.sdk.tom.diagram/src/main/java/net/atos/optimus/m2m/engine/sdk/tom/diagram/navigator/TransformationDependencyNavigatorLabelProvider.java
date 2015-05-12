package net.atos.optimus.m2m.engine.sdk.tom.diagram.navigator;

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
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationSetExtensionTransformationSetIdEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationSetNameEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationSetsEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.part.TransformationDependencyDiagramEditorPlugin;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.part.TransformationDependencyVisualIDRegistry;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.providers.TransformationDependencyElementTypes;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.providers.TransformationDependencyParserProvider;

import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ITreePathLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.ViewerLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;

/**
 * @generated
 */
public class TransformationDependencyNavigatorLabelProvider extends LabelProvider implements ICommonLabelProvider,
		ITreePathLabelProvider {

	/**
	 * @generated
	 */
	static {
		TransformationDependencyDiagramEditorPlugin.getInstance().getImageRegistry()
				.put("Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
		TransformationDependencyDiagramEditorPlugin.getInstance().getImageRegistry()
				.put("Navigator?ImageNotFound", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	public void updateLabel(ViewerLabel label, TreePath elementPath) {
		Object element = elementPath.getLastSegment();
		if (element instanceof TransformationDependencyNavigatorItem
				&& !isOwnView(((TransformationDependencyNavigatorItem) element).getView())) {
			return;
		}
		label.setText(getText(element));
		label.setImage(getImage(element));
	}

	/**
	 * @generated
	 */
	public Image getImage(Object element) {
		if (element instanceof TransformationDependencyNavigatorGroup) {
			TransformationDependencyNavigatorGroup group = (TransformationDependencyNavigatorGroup) element;
			return TransformationDependencyDiagramEditorPlugin.getInstance().getBundledImage(group.getIcon());
		}

		if (element instanceof TransformationDependencyNavigatorItem) {
			TransformationDependencyNavigatorItem navigatorItem = (TransformationDependencyNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return super.getImage(element);
			}
			return getImage(navigatorItem.getView());
		}

		return super.getImage(element);
	}

	/**
	 * @generated
	 */
	public Image getImage(View view) {
		switch (TransformationDependencyVisualIDRegistry.getVisualID(view)) {
		case TransformationSetsEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Diagram?http://www.eclipse.org/emf/net/atos/optimus/?TransformationSets", TransformationDependencyElementTypes.TransformationSets_1000); //$NON-NLS-1$
		case TransformationSetEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://www.eclipse.org/emf/net/atos/optimus/?TransformationSet", TransformationDependencyElementTypes.TransformationSet_2004); //$NON-NLS-1$
		case TransformationSetExtensionEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://www.eclipse.org/emf/net/atos/optimus/?TransformationSetExtension", TransformationDependencyElementTypes.TransformationSetExtension_2005); //$NON-NLS-1$
		case ExternalTransformationEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://www.eclipse.org/emf/net/atos/optimus/?ExternalTransformation", TransformationDependencyElementTypes.ExternalTransformation_2006); //$NON-NLS-1$
		case TransformationEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://www.eclipse.org/emf/net/atos/optimus/?Transformation", TransformationDependencyElementTypes.Transformation_3003); //$NON-NLS-1$
		case Transformation2EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://www.eclipse.org/emf/net/atos/optimus/?Transformation", TransformationDependencyElementTypes.Transformation_3004); //$NON-NLS-1$
		case SelfRequirementEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.eclipse.org/emf/net/atos/optimus/?SelfRequirement", TransformationDependencyElementTypes.SelfRequirement_4004); //$NON-NLS-1$
		case ParentRequirementEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.eclipse.org/emf/net/atos/optimus/?ParentRequirement", TransformationDependencyElementTypes.ParentRequirement_4005); //$NON-NLS-1$
		case CustomRequirementEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.eclipse.org/emf/net/atos/optimus/?CustomRequirement", TransformationDependencyElementTypes.CustomRequirement_4006); //$NON-NLS-1$
		}
		return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private Image getImage(String key, IElementType elementType) {
		ImageRegistry imageRegistry = TransformationDependencyDiagramEditorPlugin.getInstance().getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null && elementType != null
				&& TransformationDependencyElementTypes.isKnownElementType(elementType)) {
			image = TransformationDependencyElementTypes.getImage(elementType);
			imageRegistry.put(key, image);
		}

		if (image == null) {
			image = imageRegistry.get("Navigator?ImageNotFound"); //$NON-NLS-1$
			imageRegistry.put(key, image);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public String getText(Object element) {
		if (element instanceof TransformationDependencyNavigatorGroup) {
			TransformationDependencyNavigatorGroup group = (TransformationDependencyNavigatorGroup) element;
			return group.getGroupName();
		}

		if (element instanceof TransformationDependencyNavigatorItem) {
			TransformationDependencyNavigatorItem navigatorItem = (TransformationDependencyNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return null;
			}
			return getText(navigatorItem.getView());
		}

		return super.getText(element);
	}

	/**
	 * @generated
	 */
	public String getText(View view) {
		if (view.getElement() != null && view.getElement().eIsProxy()) {
			return getUnresolvedDomainElementProxyText(view);
		}
		switch (TransformationDependencyVisualIDRegistry.getVisualID(view)) {
		case TransformationSetsEditPart.VISUAL_ID:
			return getTransformationSets_1000Text(view);
		case TransformationSetEditPart.VISUAL_ID:
			return getTransformationSet_2004Text(view);
		case TransformationSetExtensionEditPart.VISUAL_ID:
			return getTransformationSetExtension_2005Text(view);
		case ExternalTransformationEditPart.VISUAL_ID:
			return getExternalTransformation_2006Text(view);
		case TransformationEditPart.VISUAL_ID:
			return getTransformation_3003Text(view);
		case Transformation2EditPart.VISUAL_ID:
			return getTransformation_3004Text(view);
		case SelfRequirementEditPart.VISUAL_ID:
			return getSelfRequirement_4004Text(view);
		case ParentRequirementEditPart.VISUAL_ID:
			return getParentRequirement_4005Text(view);
		case CustomRequirementEditPart.VISUAL_ID:
			return getCustomRequirement_4006Text(view);
		}
		return getUnknownElementText(view);
	}

	/**
	 * @generated
	 */
	private String getTransformationSets_1000Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getTransformationSet_2004Text(View view) {
		IParser parser = TransformationDependencyParserProvider.getParser(
				TransformationDependencyElementTypes.TransformationSet_2004,
				view.getElement() != null ? view.getElement() : view,
				TransformationDependencyVisualIDRegistry.getType(TransformationSetNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			TransformationDependencyDiagramEditorPlugin.getInstance()
					.logError("Parser was not found for label " + 5007); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getTransformationSetExtension_2005Text(View view) {
		IParser parser = TransformationDependencyParserProvider.getParser(
				TransformationDependencyElementTypes.TransformationSetExtension_2005,
				view.getElement() != null ? view.getElement() : view, TransformationDependencyVisualIDRegistry
						.getType(TransformationSetExtensionTransformationSetIdEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			TransformationDependencyDiagramEditorPlugin.getInstance()
					.logError("Parser was not found for label " + 5009); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getExternalTransformation_2006Text(View view) {
		IParser parser = TransformationDependencyParserProvider.getParser(
				TransformationDependencyElementTypes.ExternalTransformation_2006,
				view.getElement() != null ? view.getElement() : view,
				TransformationDependencyVisualIDRegistry.getType(ExternalTransformationNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			TransformationDependencyDiagramEditorPlugin.getInstance()
					.logError("Parser was not found for label " + 5010); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getTransformation_3003Text(View view) {
		IParser parser = TransformationDependencyParserProvider.getParser(
				TransformationDependencyElementTypes.Transformation_3003, view.getElement() != null ? view.getElement()
						: view, TransformationDependencyVisualIDRegistry.getType(TransformationNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			TransformationDependencyDiagramEditorPlugin.getInstance()
					.logError("Parser was not found for label " + 5006); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getTransformation_3004Text(View view) {
		IParser parser = TransformationDependencyParserProvider
				.getParser(TransformationDependencyElementTypes.Transformation_3004,
						view.getElement() != null ? view.getElement() : view,
						TransformationDependencyVisualIDRegistry.getType(TransformationName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			TransformationDependencyDiagramEditorPlugin.getInstance()
					.logError("Parser was not found for label " + 5008); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getSelfRequirement_4004Text(View view) {
		IParser parser = TransformationDependencyParserProvider.getParser(
				TransformationDependencyElementTypes.SelfRequirement_4004,
				view.getElement() != null ? view.getElement() : view,
				TransformationDependencyVisualIDRegistry.getType(SelfRequirementLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			TransformationDependencyDiagramEditorPlugin.getInstance()
					.logError("Parser was not found for label " + 6004); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getParentRequirement_4005Text(View view) {
		IParser parser = TransformationDependencyParserProvider.getParser(
				TransformationDependencyElementTypes.ParentRequirement_4005,
				view.getElement() != null ? view.getElement() : view,
				TransformationDependencyVisualIDRegistry.getType(ParentRequirementLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			TransformationDependencyDiagramEditorPlugin.getInstance()
					.logError("Parser was not found for label " + 6005); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getCustomRequirement_4006Text(View view) {
		IParser parser = TransformationDependencyParserProvider.getParser(
				TransformationDependencyElementTypes.CustomRequirement_4006,
				view.getElement() != null ? view.getElement() : view,
				TransformationDependencyVisualIDRegistry.getType(CustomRequirementLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			TransformationDependencyDiagramEditorPlugin.getInstance()
					.logError("Parser was not found for label " + 6006); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getUnknownElementText(View view) {
		return "<UnknownElement Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	 * @generated
	 */
	private String getUnresolvedDomainElementProxyText(View view) {
		return "<Unresolved domain element Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	 * @generated
	 */
	public void init(ICommonContentExtensionSite aConfig) {
	}

	/**
	 * @generated
	 */
	public void restoreState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public void saveState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public String getDescription(Object anElement) {
		return null;
	}

	/**
	 * @generated
	 */
	private boolean isOwnView(View view) {
		return TransformationSetsEditPart.MODEL_ID.equals(TransformationDependencyVisualIDRegistry.getModelID(view));
	}

}
