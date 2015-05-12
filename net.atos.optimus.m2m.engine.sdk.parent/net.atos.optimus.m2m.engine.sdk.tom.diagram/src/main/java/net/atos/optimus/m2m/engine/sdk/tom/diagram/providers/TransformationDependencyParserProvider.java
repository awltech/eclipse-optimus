package net.atos.optimus.m2m.engine.sdk.tom.diagram.providers;

import net.atos.optimus.m2m.engine.sdk.tom.TomPackage;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.ExternalTransformationNameEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationName2EditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationNameEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationSetExtensionTransformationSetIdEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationSetNameEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.parsers.MessageFormatParser;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.part.TransformationDependencyVisualIDRegistry;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class TransformationDependencyParserProvider extends AbstractProvider implements IParserProvider {

	/**
	 * @generated
	 */
	private IParser transformationSetName_5007Parser;

	/**
	 * @generated
	 */
	private IParser getTransformationSetName_5007Parser() {
		if (transformationSetName_5007Parser == null) {
			EAttribute[] features = new EAttribute[] { TomPackage.eINSTANCE.getTransformationSet_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			transformationSetName_5007Parser = parser;
		}
		return transformationSetName_5007Parser;
	}

	/**
	 * @generated
	 */
	private IParser transformationSetExtensionTransformationSetId_5009Parser;

	/**
	 * @generated
	 */
	private IParser getTransformationSetExtensionTransformationSetId_5009Parser() {
		if (transformationSetExtensionTransformationSetId_5009Parser == null) {
			EAttribute[] features = new EAttribute[] { TomPackage.eINSTANCE
					.getTransformationSetExtension_TransformationSetId() };
			MessageFormatParser parser = new MessageFormatParser(features);
			transformationSetExtensionTransformationSetId_5009Parser = parser;
		}
		return transformationSetExtensionTransformationSetId_5009Parser;
	}

	/**
	 * @generated
	 */
	private IParser externalTransformationName_5010Parser;

	/**
	 * @generated
	 */
	private IParser getExternalTransformationName_5010Parser() {
		if (externalTransformationName_5010Parser == null) {
			EAttribute[] features = new EAttribute[] { TomPackage.eINSTANCE.getTransformationReference_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			externalTransformationName_5010Parser = parser;
		}
		return externalTransformationName_5010Parser;
	}

	/**
	 * @generated
	 */
	private IParser transformationName_5006Parser;

	/**
	 * @generated
	 */
	private IParser getTransformationName_5006Parser() {
		if (transformationName_5006Parser == null) {
			EAttribute[] features = new EAttribute[] { TomPackage.eINSTANCE.getTransformationReference_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			transformationName_5006Parser = parser;
		}
		return transformationName_5006Parser;
	}

	/**
	 * @generated
	 */
	private IParser transformationName_5008Parser;

	/**
	 * @generated
	 */
	private IParser getTransformationName_5008Parser() {
		if (transformationName_5008Parser == null) {
			EAttribute[] features = new EAttribute[] { TomPackage.eINSTANCE.getTransformationReference_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			transformationName_5008Parser = parser;
		}
		return transformationName_5008Parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case TransformationSetNameEditPart.VISUAL_ID:
			return getTransformationSetName_5007Parser();
		case TransformationSetExtensionTransformationSetIdEditPart.VISUAL_ID:
			return getTransformationSetExtensionTransformationSetId_5009Parser();
		case ExternalTransformationNameEditPart.VISUAL_ID:
			return getExternalTransformationName_5010Parser();
		case TransformationNameEditPart.VISUAL_ID:
			return getTransformationName_5006Parser();
		case TransformationName2EditPart.VISUAL_ID:
			return getTransformationName_5008Parser();
		}
		return null;
	}

	/**
	 * Utility method that consults ParserService
	 * @generated
	 */
	public static IParser getParser(IElementType type, EObject object, String parserHint) {
		return ParserService.getInstance().getParser(new HintAdapter(type, object, parserHint));
	}

	/**
	 * @generated
	 */
	public IParser getParser(IAdaptable hint) {
		String vid = (String) hint.getAdapter(String.class);
		if (vid != null) {
			return getParser(TransformationDependencyVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(TransformationDependencyVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (TransformationDependencyElementTypes.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}

	/**
	 * @generated
	 */
	private static class HintAdapter extends ParserHintAdapter {

		/**
		 * @generated
		 */
		private final IElementType elementType;

		/**
		 * @generated
		 */
		public HintAdapter(IElementType type, EObject object, String parserHint) {
			super(object, parserHint);
			assert type != null;
			elementType = type;
		}

		/**
		 * @generated
		 */
		public Object getAdapter(Class adapter) {
			if (IElementType.class.equals(adapter)) {
				return elementType;
			}
			return super.getAdapter(adapter);
		}
	}

}
