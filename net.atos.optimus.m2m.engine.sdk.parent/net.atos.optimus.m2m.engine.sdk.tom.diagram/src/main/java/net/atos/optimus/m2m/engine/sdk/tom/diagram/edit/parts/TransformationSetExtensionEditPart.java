package net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts;

import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.policies.TransformationSetExtensionItemSemanticEditPolicy;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.part.TransformationDependencyVisualIDRegistry;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;

/**
 * @generated
 */
public class TransformationSetExtensionEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 2005;

	/**
	 * @generated
	 */
	protected IFigure contentPane;

	/**
	 * @generated
	 */
	protected IFigure primaryShape;

	/**
	 * @generated
	 */
	public TransformationSetExtensionEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new TransformationSetExtensionItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		// XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	/**
	 * @generated
	 */
	protected LayoutEditPolicy createLayoutEditPolicy() {
		org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy lep = new org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy() {

			protected EditPolicy createChildEditPolicy(EditPart child) {
				EditPolicy result = child.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
				if (result == null) {
					result = new NonResizableEditPolicy();
				}
				return result;
			}

			protected Command getMoveChildrenCommand(Request request) {
				return null;
			}

			protected Command getCreateCommand(CreateRequest request) {
				return null;
			}
		};
		return lep;
	}

	/**
	 * @generated
	 */
	protected IFigure createNodeShape() {
		return primaryShape = new TransformationSetExtensionFigure();
	}

	/**
	 * @generated
	 */
	public TransformationSetExtensionFigure getPrimaryShape() {
		return (TransformationSetExtensionFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof TransformationSetExtensionTransformationSetIdEditPart) {
			((TransformationSetExtensionTransformationSetIdEditPart) childEditPart).setLabel(getPrimaryShape()
					.getFigureTransformationSetExtensionTransformationSetIdFigure());
			return true;
		}
		if (childEditPart instanceof TransformationSetExtensionTransformationSetExtensionCompartmentEditPart) {
			IFigure pane = getPrimaryShape().getFigureTransformationSetExtensionCompartmentFigure();
			setupContentPane(pane); // FIXME each comparment should handle his content pane in his own way 
			pane.add(((TransformationSetExtensionTransformationSetExtensionCompartmentEditPart) childEditPart)
					.getFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof TransformationSetExtensionTransformationSetIdEditPart) {
			return true;
		}
		if (childEditPart instanceof TransformationSetExtensionTransformationSetExtensionCompartmentEditPart) {
			IFigure pane = getPrimaryShape().getFigureTransformationSetExtensionCompartmentFigure();
			pane.remove(((TransformationSetExtensionTransformationSetExtensionCompartmentEditPart) childEditPart)
					.getFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected void addChildVisual(EditPart childEditPart, int index) {
		if (addFixedChild(childEditPart)) {
			return;
		}
		super.addChildVisual(childEditPart, -1);
	}

	/**
	 * @generated
	 */
	protected void removeChildVisual(EditPart childEditPart) {
		if (removeFixedChild(childEditPart)) {
			return;
		}
		super.removeChildVisual(childEditPart);
	}

	/**
	 * @generated
	 */
	protected IFigure getContentPaneFor(IGraphicalEditPart editPart) {
		if (editPart instanceof TransformationSetExtensionTransformationSetExtensionCompartmentEditPart) {
			return getPrimaryShape().getFigureTransformationSetExtensionCompartmentFigure();
		}
		return getContentPane();
	}

	/**
	 * @generated
	 */
	protected NodeFigure createNodePlate() {
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(300, 300);
		return result;
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model
	 * so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */
	protected NodeFigure createNodeFigure() {
		NodeFigure figure = createNodePlate();
		figure.setLayoutManager(new StackLayout());
		IFigure shape = createNodeShape();
		figure.add(shape);
		contentPane = setupContentPane(shape);
		return figure;
	}

	/**
	 * Default implementation treats passed figure as content pane.
	 * Respects layout one may have set for generated figure.
	 * @param nodeShape instance of generated figure class
	 * @generated
	 */
	protected IFigure setupContentPane(IFigure nodeShape) {
		if (nodeShape.getLayoutManager() == null) {
			ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
			layout.setSpacing(5);
			nodeShape.setLayoutManager(layout);
		}
		return nodeShape; // use nodeShape itself as contentPane
	}

	/**
	 * @generated
	 */
	public IFigure getContentPane() {
		if (contentPane != null) {
			return contentPane;
		}
		return super.getContentPane();
	}

	/**
	 * @generated
	 */
	protected void setForegroundColor(Color color) {
		if (primaryShape != null) {
			primaryShape.setForegroundColor(color);
		}
	}

	/**
	 * @generated
	 */
	protected void setBackgroundColor(Color color) {
		if (primaryShape != null) {
			primaryShape.setBackgroundColor(color);
		}
	}

	/**
	 * @generated
	 */
	protected void setLineWidth(int width) {
		if (primaryShape instanceof Shape) {
			((Shape) primaryShape).setLineWidth(width);
		}
	}

	/**
	 * @generated
	 */
	protected void setLineType(int style) {
		if (primaryShape instanceof Shape) {
			((Shape) primaryShape).setLineStyle(style);
		}
	}

	/**
	 * @generated
	 */
	public EditPart getPrimaryChildEditPart() {
		return getChildBySemanticHint(TransformationDependencyVisualIDRegistry
				.getType(TransformationSetExtensionTransformationSetIdEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public class TransformationSetExtensionFigure extends RectangleFigure {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureTransformationSetExtensionHeaderFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureTransformationSetExtensionTransformationSetIdFigure;
		/**
		 * @generated
		 */
		private RectangleFigure fFigureTransformationSetExtensionCompartmentFigure;

		/**
		 * @generated
		 */
		public TransformationSetExtensionFigure() {

			GridLayout layoutThis = new GridLayout();
			layoutThis.numColumns = 1;
			layoutThis.makeColumnsEqualWidth = true;
			layoutThis.horizontalSpacing = 0;
			layoutThis.verticalSpacing = 0;
			layoutThis.marginWidth = 0;
			layoutThis.marginHeight = 0;
			this.setLayoutManager(layoutThis);

			this.setForegroundColor(ColorConstants.black);
			this.setBackgroundColor(THIS_BACK);
			this.setPreferredSize(new Dimension(getMapMode().DPtoLP(300), getMapMode().DPtoLP(300)));
			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {

			RectangleFigure transformationSetExtensionUpperFigure0 = new RectangleFigure();

			transformationSetExtensionUpperFigure0.setFill(false);
			transformationSetExtensionUpperFigure0.setOutline(false);

			GridData constraintTransformationSetExtensionUpperFigure0 = new GridData();
			constraintTransformationSetExtensionUpperFigure0.verticalAlignment = GridData.CENTER;
			constraintTransformationSetExtensionUpperFigure0.horizontalAlignment = GridData.FILL;
			constraintTransformationSetExtensionUpperFigure0.horizontalIndent = 0;
			constraintTransformationSetExtensionUpperFigure0.horizontalSpan = 0;
			constraintTransformationSetExtensionUpperFigure0.verticalSpan = 0;
			constraintTransformationSetExtensionUpperFigure0.grabExcessHorizontalSpace = true;
			constraintTransformationSetExtensionUpperFigure0.grabExcessVerticalSpace = false;
			this.add(transformationSetExtensionUpperFigure0, constraintTransformationSetExtensionUpperFigure0);

			GridLayout layoutTransformationSetExtensionUpperFigure0 = new GridLayout();
			layoutTransformationSetExtensionUpperFigure0.numColumns = 1;
			layoutTransformationSetExtensionUpperFigure0.makeColumnsEqualWidth = true;
			layoutTransformationSetExtensionUpperFigure0.horizontalSpacing = 0;
			layoutTransformationSetExtensionUpperFigure0.verticalSpacing = 0;
			layoutTransformationSetExtensionUpperFigure0.marginWidth = 0;
			layoutTransformationSetExtensionUpperFigure0.marginHeight = 0;
			transformationSetExtensionUpperFigure0.setLayoutManager(layoutTransformationSetExtensionUpperFigure0);

			RectangleFigure transformationSetExtensionHeaderHolder1 = new RectangleFigure();

			transformationSetExtensionHeaderHolder1.setFill(false);
			transformationSetExtensionHeaderHolder1.setOutline(false);

			GridData constraintTransformationSetExtensionHeaderHolder1 = new GridData();
			constraintTransformationSetExtensionHeaderHolder1.verticalAlignment = GridData.CENTER;
			constraintTransformationSetExtensionHeaderHolder1.horizontalAlignment = GridData.CENTER;
			constraintTransformationSetExtensionHeaderHolder1.horizontalIndent = 0;
			constraintTransformationSetExtensionHeaderHolder1.horizontalSpan = 1;
			constraintTransformationSetExtensionHeaderHolder1.verticalSpan = 1;
			constraintTransformationSetExtensionHeaderHolder1.grabExcessHorizontalSpace = true;
			constraintTransformationSetExtensionHeaderHolder1.grabExcessVerticalSpace = true;
			transformationSetExtensionUpperFigure0.add(transformationSetExtensionHeaderHolder1,
					constraintTransformationSetExtensionHeaderHolder1);

			GridLayout layoutTransformationSetExtensionHeaderHolder1 = new GridLayout();
			layoutTransformationSetExtensionHeaderHolder1.numColumns = 1;
			layoutTransformationSetExtensionHeaderHolder1.makeColumnsEqualWidth = true;
			layoutTransformationSetExtensionHeaderHolder1.horizontalSpacing = 0;
			layoutTransformationSetExtensionHeaderHolder1.verticalSpacing = 0;
			layoutTransformationSetExtensionHeaderHolder1.marginWidth = 0;
			layoutTransformationSetExtensionHeaderHolder1.marginHeight = 0;
			transformationSetExtensionHeaderHolder1.setLayoutManager(layoutTransformationSetExtensionHeaderHolder1);

			fFigureTransformationSetExtensionHeaderFigure = new WrappingLabel();

			fFigureTransformationSetExtensionHeaderFigure.setText("Extension of");

			GridData constraintFFigureTransformationSetExtensionHeaderFigure = new GridData();
			constraintFFigureTransformationSetExtensionHeaderFigure.verticalAlignment = GridData.CENTER;
			constraintFFigureTransformationSetExtensionHeaderFigure.horizontalAlignment = GridData.CENTER;
			constraintFFigureTransformationSetExtensionHeaderFigure.horizontalIndent = 0;
			constraintFFigureTransformationSetExtensionHeaderFigure.horizontalSpan = 0;
			constraintFFigureTransformationSetExtensionHeaderFigure.verticalSpan = 0;
			constraintFFigureTransformationSetExtensionHeaderFigure.grabExcessHorizontalSpace = true;
			constraintFFigureTransformationSetExtensionHeaderFigure.grabExcessVerticalSpace = true;
			transformationSetExtensionHeaderHolder1.add(fFigureTransformationSetExtensionHeaderFigure,
					constraintFFigureTransformationSetExtensionHeaderFigure);

			RectangleFigure transformationSetExtensionIdHolder1 = new RectangleFigure();

			transformationSetExtensionIdHolder1.setFill(false);
			transformationSetExtensionIdHolder1.setOutline(false);

			GridData constraintTransformationSetExtensionIdHolder1 = new GridData();
			constraintTransformationSetExtensionIdHolder1.verticalAlignment = GridData.CENTER;
			constraintTransformationSetExtensionIdHolder1.horizontalAlignment = GridData.CENTER;
			constraintTransformationSetExtensionIdHolder1.horizontalIndent = 0;
			constraintTransformationSetExtensionIdHolder1.horizontalSpan = 1;
			constraintTransformationSetExtensionIdHolder1.verticalSpan = 1;
			constraintTransformationSetExtensionIdHolder1.grabExcessHorizontalSpace = true;
			constraintTransformationSetExtensionIdHolder1.grabExcessVerticalSpace = true;
			transformationSetExtensionUpperFigure0.add(transformationSetExtensionIdHolder1,
					constraintTransformationSetExtensionIdHolder1);

			GridLayout layoutTransformationSetExtensionIdHolder1 = new GridLayout();
			layoutTransformationSetExtensionIdHolder1.numColumns = 1;
			layoutTransformationSetExtensionIdHolder1.makeColumnsEqualWidth = true;
			layoutTransformationSetExtensionIdHolder1.horizontalSpacing = 0;
			layoutTransformationSetExtensionIdHolder1.verticalSpacing = 0;
			layoutTransformationSetExtensionIdHolder1.marginWidth = 0;
			layoutTransformationSetExtensionIdHolder1.marginHeight = 0;
			transformationSetExtensionIdHolder1.setLayoutManager(layoutTransformationSetExtensionIdHolder1);

			fFigureTransformationSetExtensionTransformationSetIdFigure = new WrappingLabel();

			fFigureTransformationSetExtensionTransformationSetIdFigure.setText("<extension>");

			GridData constraintFFigureTransformationSetExtensionTransformationSetIdFigure = new GridData();
			constraintFFigureTransformationSetExtensionTransformationSetIdFigure.verticalAlignment = GridData.CENTER;
			constraintFFigureTransformationSetExtensionTransformationSetIdFigure.horizontalAlignment = GridData.CENTER;
			constraintFFigureTransformationSetExtensionTransformationSetIdFigure.horizontalIndent = 0;
			constraintFFigureTransformationSetExtensionTransformationSetIdFigure.horizontalSpan = 0;
			constraintFFigureTransformationSetExtensionTransformationSetIdFigure.verticalSpan = 0;
			constraintFFigureTransformationSetExtensionTransformationSetIdFigure.grabExcessHorizontalSpace = true;
			constraintFFigureTransformationSetExtensionTransformationSetIdFigure.grabExcessVerticalSpace = true;
			transformationSetExtensionIdHolder1.add(fFigureTransformationSetExtensionTransformationSetIdFigure,
					constraintFFigureTransformationSetExtensionTransformationSetIdFigure);

			RectangleFigure transformationSetExtensionCompartmentHolder0 = new RectangleFigure();

			transformationSetExtensionCompartmentHolder0.setFill(false);
			transformationSetExtensionCompartmentHolder0.setOutline(false);

			GridData constraintTransformationSetExtensionCompartmentHolder0 = new GridData();
			constraintTransformationSetExtensionCompartmentHolder0.verticalAlignment = GridData.FILL;
			constraintTransformationSetExtensionCompartmentHolder0.horizontalAlignment = GridData.FILL;
			constraintTransformationSetExtensionCompartmentHolder0.horizontalIndent = 0;
			constraintTransformationSetExtensionCompartmentHolder0.horizontalSpan = 0;
			constraintTransformationSetExtensionCompartmentHolder0.verticalSpan = 0;
			constraintTransformationSetExtensionCompartmentHolder0.grabExcessHorizontalSpace = true;
			constraintTransformationSetExtensionCompartmentHolder0.grabExcessVerticalSpace = true;
			this.add(transformationSetExtensionCompartmentHolder0,
					constraintTransformationSetExtensionCompartmentHolder0);

			GridLayout layoutTransformationSetExtensionCompartmentHolder0 = new GridLayout();
			layoutTransformationSetExtensionCompartmentHolder0.numColumns = 1;
			layoutTransformationSetExtensionCompartmentHolder0.makeColumnsEqualWidth = true;
			layoutTransformationSetExtensionCompartmentHolder0.horizontalSpacing = 0;
			layoutTransformationSetExtensionCompartmentHolder0.verticalSpacing = 0;
			layoutTransformationSetExtensionCompartmentHolder0.marginWidth = 0;
			layoutTransformationSetExtensionCompartmentHolder0.marginHeight = 0;
			transformationSetExtensionCompartmentHolder0
					.setLayoutManager(layoutTransformationSetExtensionCompartmentHolder0);

			fFigureTransformationSetExtensionCompartmentFigure = new RectangleFigure();

			fFigureTransformationSetExtensionCompartmentFigure.setForegroundColor(ColorConstants.black);
			fFigureTransformationSetExtensionCompartmentFigure
					.setBackgroundColor(FFIGURETRANSFORMATIONSETEXTENSIONCOMPARTMENTFIGURE_BACK);
			fFigureTransformationSetExtensionCompartmentFigure.setBorder(new MarginBorder(getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(0), getMapMode().DPtoLP(0), getMapMode().DPtoLP(0)));

			GridData constraintFFigureTransformationSetExtensionCompartmentFigure = new GridData();
			constraintFFigureTransformationSetExtensionCompartmentFigure.verticalAlignment = GridData.FILL;
			constraintFFigureTransformationSetExtensionCompartmentFigure.horizontalAlignment = GridData.FILL;
			constraintFFigureTransformationSetExtensionCompartmentFigure.horizontalIndent = 0;
			constraintFFigureTransformationSetExtensionCompartmentFigure.horizontalSpan = 0;
			constraintFFigureTransformationSetExtensionCompartmentFigure.verticalSpan = 0;
			constraintFFigureTransformationSetExtensionCompartmentFigure.grabExcessHorizontalSpace = true;
			constraintFFigureTransformationSetExtensionCompartmentFigure.grabExcessVerticalSpace = true;
			transformationSetExtensionCompartmentHolder0.add(fFigureTransformationSetExtensionCompartmentFigure,
					constraintFFigureTransformationSetExtensionCompartmentFigure);

		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureTransformationSetExtensionHeaderFigure() {
			return fFigureTransformationSetExtensionHeaderFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureTransformationSetExtensionTransformationSetIdFigure() {
			return fFigureTransformationSetExtensionTransformationSetIdFigure;
		}

		/**
		 * @generated
		 */
		public RectangleFigure getFigureTransformationSetExtensionCompartmentFigure() {
			return fFigureTransformationSetExtensionCompartmentFigure;
		}

	}

	/**
	 * @generated
	 */
	static final Color THIS_BACK = new Color(null, 224, 238, 224);

	/**
	 * @generated
	 */
	static final Color FFIGURETRANSFORMATIONSETEXTENSIONCOMPARTMENTFIGURE_BACK = new Color(null, 224, 238, 224);

}
