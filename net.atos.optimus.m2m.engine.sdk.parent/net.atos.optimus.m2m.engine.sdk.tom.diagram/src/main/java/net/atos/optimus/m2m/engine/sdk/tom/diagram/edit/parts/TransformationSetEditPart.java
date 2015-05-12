package net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts;

import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.policies.TransformationSetItemSemanticEditPolicy;
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
public class TransformationSetEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 2004;

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
	public TransformationSetEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new TransformationSetItemSemanticEditPolicy());
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
		return primaryShape = new TransformationSetFigure();
	}

	/**
	 * @generated
	 */
	public TransformationSetFigure getPrimaryShape() {
		return (TransformationSetFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof TransformationSetNameEditPart) {
			((TransformationSetNameEditPart) childEditPart).setLabel(getPrimaryShape()
					.getFigureTransformationSetNameFigure());
			return true;
		}
		if (childEditPart instanceof TransformationSetTransformationSetCompartmentEditPart) {
			IFigure pane = getPrimaryShape().getFigureTransformationSetCompartmentFigure();
			setupContentPane(pane); // FIXME each comparment should handle his content pane in his own way 
			pane.add(((TransformationSetTransformationSetCompartmentEditPart) childEditPart).getFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof TransformationSetNameEditPart) {
			return true;
		}
		if (childEditPart instanceof TransformationSetTransformationSetCompartmentEditPart) {
			IFigure pane = getPrimaryShape().getFigureTransformationSetCompartmentFigure();
			pane.remove(((TransformationSetTransformationSetCompartmentEditPart) childEditPart).getFigure());
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
		if (editPart instanceof TransformationSetTransformationSetCompartmentEditPart) {
			return getPrimaryShape().getFigureTransformationSetCompartmentFigure();
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
				.getType(TransformationSetNameEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public class TransformationSetFigure extends RectangleFigure {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureTransformationSetNameFigure;
		/**
		 * @generated
		 */
		private RectangleFigure fFigureTransformationSetCompartmentFigure;

		/**
		 * @generated
		 */
		public TransformationSetFigure() {

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

			RectangleFigure transformationSetUpperFigure0 = new RectangleFigure();

			transformationSetUpperFigure0.setFill(false);
			transformationSetUpperFigure0.setOutline(false);

			GridData constraintTransformationSetUpperFigure0 = new GridData();
			constraintTransformationSetUpperFigure0.verticalAlignment = GridData.CENTER;
			constraintTransformationSetUpperFigure0.horizontalAlignment = GridData.FILL;
			constraintTransformationSetUpperFigure0.horizontalIndent = 0;
			constraintTransformationSetUpperFigure0.horizontalSpan = 0;
			constraintTransformationSetUpperFigure0.verticalSpan = 0;
			constraintTransformationSetUpperFigure0.grabExcessHorizontalSpace = true;
			constraintTransformationSetUpperFigure0.grabExcessVerticalSpace = false;
			this.add(transformationSetUpperFigure0, constraintTransformationSetUpperFigure0);

			GridLayout layoutTransformationSetUpperFigure0 = new GridLayout();
			layoutTransformationSetUpperFigure0.numColumns = 1;
			layoutTransformationSetUpperFigure0.makeColumnsEqualWidth = true;
			layoutTransformationSetUpperFigure0.horizontalSpacing = 0;
			layoutTransformationSetUpperFigure0.verticalSpacing = 0;
			layoutTransformationSetUpperFigure0.marginWidth = 0;
			layoutTransformationSetUpperFigure0.marginHeight = 0;
			transformationSetUpperFigure0.setLayoutManager(layoutTransformationSetUpperFigure0);

			RectangleFigure transformationSetNameHolder1 = new RectangleFigure();

			transformationSetNameHolder1.setFill(false);
			transformationSetNameHolder1.setOutline(false);

			GridData constraintTransformationSetNameHolder1 = new GridData();
			constraintTransformationSetNameHolder1.verticalAlignment = GridData.CENTER;
			constraintTransformationSetNameHolder1.horizontalAlignment = GridData.CENTER;
			constraintTransformationSetNameHolder1.horizontalIndent = 0;
			constraintTransformationSetNameHolder1.horizontalSpan = 1;
			constraintTransformationSetNameHolder1.verticalSpan = 1;
			constraintTransformationSetNameHolder1.grabExcessHorizontalSpace = true;
			constraintTransformationSetNameHolder1.grabExcessVerticalSpace = true;
			transformationSetUpperFigure0.add(transformationSetNameHolder1, constraintTransformationSetNameHolder1);

			GridLayout layoutTransformationSetNameHolder1 = new GridLayout();
			layoutTransformationSetNameHolder1.numColumns = 1;
			layoutTransformationSetNameHolder1.makeColumnsEqualWidth = true;
			layoutTransformationSetNameHolder1.horizontalSpacing = 0;
			layoutTransformationSetNameHolder1.verticalSpacing = 0;
			layoutTransformationSetNameHolder1.marginWidth = 0;
			layoutTransformationSetNameHolder1.marginHeight = 0;
			transformationSetNameHolder1.setLayoutManager(layoutTransformationSetNameHolder1);

			fFigureTransformationSetNameFigure = new WrappingLabel();

			fFigureTransformationSetNameFigure.setText("<name>");

			GridData constraintFFigureTransformationSetNameFigure = new GridData();
			constraintFFigureTransformationSetNameFigure.verticalAlignment = GridData.CENTER;
			constraintFFigureTransformationSetNameFigure.horizontalAlignment = GridData.CENTER;
			constraintFFigureTransformationSetNameFigure.horizontalIndent = 0;
			constraintFFigureTransformationSetNameFigure.horizontalSpan = 0;
			constraintFFigureTransformationSetNameFigure.verticalSpan = 0;
			constraintFFigureTransformationSetNameFigure.grabExcessHorizontalSpace = true;
			constraintFFigureTransformationSetNameFigure.grabExcessVerticalSpace = true;
			transformationSetNameHolder1.add(fFigureTransformationSetNameFigure,
					constraintFFigureTransformationSetNameFigure);

			RectangleFigure transformationSetCompartmentHolder0 = new RectangleFigure();

			transformationSetCompartmentHolder0.setFill(false);
			transformationSetCompartmentHolder0.setOutline(false);

			GridData constraintTransformationSetCompartmentHolder0 = new GridData();
			constraintTransformationSetCompartmentHolder0.verticalAlignment = GridData.FILL;
			constraintTransformationSetCompartmentHolder0.horizontalAlignment = GridData.FILL;
			constraintTransformationSetCompartmentHolder0.horizontalIndent = 0;
			constraintTransformationSetCompartmentHolder0.horizontalSpan = 0;
			constraintTransformationSetCompartmentHolder0.verticalSpan = 0;
			constraintTransformationSetCompartmentHolder0.grabExcessHorizontalSpace = true;
			constraintTransformationSetCompartmentHolder0.grabExcessVerticalSpace = true;
			this.add(transformationSetCompartmentHolder0, constraintTransformationSetCompartmentHolder0);

			GridLayout layoutTransformationSetCompartmentHolder0 = new GridLayout();
			layoutTransformationSetCompartmentHolder0.numColumns = 1;
			layoutTransformationSetCompartmentHolder0.makeColumnsEqualWidth = true;
			layoutTransformationSetCompartmentHolder0.horizontalSpacing = 0;
			layoutTransformationSetCompartmentHolder0.verticalSpacing = 0;
			layoutTransformationSetCompartmentHolder0.marginWidth = 0;
			layoutTransformationSetCompartmentHolder0.marginHeight = 0;
			transformationSetCompartmentHolder0.setLayoutManager(layoutTransformationSetCompartmentHolder0);

			fFigureTransformationSetCompartmentFigure = new RectangleFigure();

			fFigureTransformationSetCompartmentFigure.setForegroundColor(ColorConstants.black);
			fFigureTransformationSetCompartmentFigure
					.setBackgroundColor(FFIGURETRANSFORMATIONSETCOMPARTMENTFIGURE_BACK);
			fFigureTransformationSetCompartmentFigure.setBorder(new MarginBorder(getMapMode().DPtoLP(0), getMapMode()
					.DPtoLP(0), getMapMode().DPtoLP(0), getMapMode().DPtoLP(0)));

			GridData constraintFFigureTransformationSetCompartmentFigure = new GridData();
			constraintFFigureTransformationSetCompartmentFigure.verticalAlignment = GridData.FILL;
			constraintFFigureTransformationSetCompartmentFigure.horizontalAlignment = GridData.FILL;
			constraintFFigureTransformationSetCompartmentFigure.horizontalIndent = 0;
			constraintFFigureTransformationSetCompartmentFigure.horizontalSpan = 0;
			constraintFFigureTransformationSetCompartmentFigure.verticalSpan = 0;
			constraintFFigureTransformationSetCompartmentFigure.grabExcessHorizontalSpace = true;
			constraintFFigureTransformationSetCompartmentFigure.grabExcessVerticalSpace = true;
			transformationSetCompartmentHolder0.add(fFigureTransformationSetCompartmentFigure,
					constraintFFigureTransformationSetCompartmentFigure);

		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureTransformationSetNameFigure() {
			return fFigureTransformationSetNameFigure;
		}

		/**
		 * @generated
		 */
		public RectangleFigure getFigureTransformationSetCompartmentFigure() {
			return fFigureTransformationSetCompartmentFigure;
		}

	}

	/**
	 * @generated
	 */
	static final Color THIS_BACK = new Color(null, 224, 238, 224);

	/**
	 * @generated
	 */
	static final Color FFIGURETRANSFORMATIONSETCOMPARTMENTFIGURE_BACK = new Color(null, 224, 238, 224);

}
