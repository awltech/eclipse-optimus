package net.atos.optimus.m2m.engine.sdk.tom.diagram.navigator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.CustomRequirementEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.ExternalTransformationEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.ParentRequirementEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.SelfRequirementEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.Transformation2EditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationSetEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationSetExtensionEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationSetExtensionTransformationSetExtensionCompartmentEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationSetTransformationSetCompartmentEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.edit.parts.TransformationSetsEditPart;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.part.Messages;
import net.atos.optimus.m2m.engine.sdk.tom.diagram.part.TransformationDependencyVisualIDRegistry;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;

/**
 * @generated
 */
public class TransformationDependencyNavigatorContentProvider implements ICommonContentProvider {

	/**
	 * @generated
	 */
	private static final Object[] EMPTY_ARRAY = new Object[0];

	/**
	 * @generated
	 */
	private Viewer myViewer;

	/**
	 * @generated
	 */
	private AdapterFactoryEditingDomain myEditingDomain;

	/**
	 * @generated
	 */
	private WorkspaceSynchronizer myWorkspaceSynchronizer;

	/**
	 * @generated
	 */
	private Runnable myViewerRefreshRunnable;

	/**
	 * @generated
	 */
	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	public TransformationDependencyNavigatorContentProvider() {
		TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE.createEditingDomain();
		myEditingDomain = (AdapterFactoryEditingDomain) editingDomain;
		myEditingDomain.setResourceToReadOnlyMap(new HashMap() {
			public Object get(Object key) {
				if (!containsKey(key)) {
					put(key, Boolean.TRUE);
				}
				return super.get(key);
			}
		});
		myViewerRefreshRunnable = new Runnable() {
			public void run() {
				if (myViewer != null) {
					myViewer.refresh();
				}
			}
		};
		myWorkspaceSynchronizer = new WorkspaceSynchronizer(editingDomain, new WorkspaceSynchronizer.Delegate() {
			public void dispose() {
			}

			public boolean handleResourceChanged(final Resource resource) {
				unloadAllResources();
				asyncRefresh();
				return true;
			}

			public boolean handleResourceDeleted(Resource resource) {
				unloadAllResources();
				asyncRefresh();
				return true;
			}

			public boolean handleResourceMoved(Resource resource, final URI newURI) {
				unloadAllResources();
				asyncRefresh();
				return true;
			}
		});
	}

	/**
	 * @generated
	 */
	public void dispose() {
		myWorkspaceSynchronizer.dispose();
		myWorkspaceSynchronizer = null;
		myViewerRefreshRunnable = null;
		myViewer = null;
		unloadAllResources();
		((TransactionalEditingDomain) myEditingDomain).dispose();
		myEditingDomain = null;
	}

	/**
	 * @generated
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		myViewer = viewer;
	}

	/**
	 * @generated
	 */
	void unloadAllResources() {
		for (Resource nextResource : myEditingDomain.getResourceSet().getResources()) {
			nextResource.unload();
		}
	}

	/**
	 * @generated
	 */
	void asyncRefresh() {
		if (myViewer != null && !myViewer.getControl().isDisposed()) {
			myViewer.getControl().getDisplay().asyncExec(myViewerRefreshRunnable);
		}
	}

	/**
	 * @generated
	 */
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
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
	public void init(ICommonContentExtensionSite aConfig) {
	}

	/**
	 * @generated
	 */
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof IFile) {
			IFile file = (IFile) parentElement;
			URI fileURI = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
			Resource resource = myEditingDomain.getResourceSet().getResource(fileURI, true);
			ArrayList<TransformationDependencyNavigatorItem> result = new ArrayList<TransformationDependencyNavigatorItem>();
			ArrayList<View> topViews = new ArrayList<View>(resource.getContents().size());
			for (EObject o : resource.getContents()) {
				if (o instanceof View) {
					topViews.add((View) o);
				}
			}
			result.addAll(createNavigatorItems(selectViewsByType(topViews, TransformationSetsEditPart.MODEL_ID), file,
					false));
			return result.toArray();
		}

		if (parentElement instanceof TransformationDependencyNavigatorGroup) {
			TransformationDependencyNavigatorGroup group = (TransformationDependencyNavigatorGroup) parentElement;
			return group.getChildren();
		}

		if (parentElement instanceof TransformationDependencyNavigatorItem) {
			TransformationDependencyNavigatorItem navigatorItem = (TransformationDependencyNavigatorItem) parentElement;
			if (navigatorItem.isLeaf() || !isOwnView(navigatorItem.getView())) {
				return EMPTY_ARRAY;
			}
			return getViewChildren(navigatorItem.getView(), parentElement);
		}

		return EMPTY_ARRAY;
	}

	/**
	 * @generated
	 */
	private Object[] getViewChildren(View view, Object parentElement) {
		switch (TransformationDependencyVisualIDRegistry.getVisualID(view)) {

		case TransformationSetsEditPart.VISUAL_ID: {
			LinkedList<TransformationDependencyAbstractNavigatorItem> result = new LinkedList<TransformationDependencyAbstractNavigatorItem>();
			Diagram sv = (Diagram) view;
			TransformationDependencyNavigatorGroup links = new TransformationDependencyNavigatorGroup(
					Messages.NavigatorGroupName_TransformationSets_1000_links,
					"icons/linksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(TransformationSetEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(TransformationSetExtensionEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(ExternalTransformationEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getDiagramLinksByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(SelfRequirementEditPart.VISUAL_ID));
			links.addChildren(createNavigatorItems(connectedViews, links, false));
			connectedViews = getDiagramLinksByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(ParentRequirementEditPart.VISUAL_ID));
			links.addChildren(createNavigatorItems(connectedViews, links, false));
			connectedViews = getDiagramLinksByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(CustomRequirementEditPart.VISUAL_ID));
			links.addChildren(createNavigatorItems(connectedViews, links, false));
			if (!links.isEmpty()) {
				result.add(links);
			}
			return result.toArray();
		}

		case TransformationSetEditPart.VISUAL_ID: {
			LinkedList<TransformationDependencyAbstractNavigatorItem> result = new LinkedList<TransformationDependencyAbstractNavigatorItem>();
			Node sv = (Node) view;
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry
							.getType(TransformationSetTransformationSetCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					TransformationDependencyVisualIDRegistry.getType(TransformationEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			return result.toArray();
		}

		case TransformationSetExtensionEditPart.VISUAL_ID: {
			LinkedList<TransformationDependencyAbstractNavigatorItem> result = new LinkedList<TransformationDependencyAbstractNavigatorItem>();
			Node sv = (Node) view;
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry
							.getType(TransformationSetExtensionTransformationSetExtensionCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					TransformationDependencyVisualIDRegistry.getType(Transformation2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			return result.toArray();
		}

		case ExternalTransformationEditPart.VISUAL_ID: {
			LinkedList<TransformationDependencyAbstractNavigatorItem> result = new LinkedList<TransformationDependencyAbstractNavigatorItem>();
			Node sv = (Node) view;
			TransformationDependencyNavigatorGroup incominglinks = new TransformationDependencyNavigatorGroup(
					Messages.NavigatorGroupName_ExternalTransformation_2006_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(SelfRequirementEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(ParentRequirementEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(CustomRequirementEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			return result.toArray();
		}

		case TransformationEditPart.VISUAL_ID: {
			LinkedList<TransformationDependencyAbstractNavigatorItem> result = new LinkedList<TransformationDependencyAbstractNavigatorItem>();
			Node sv = (Node) view;
			TransformationDependencyNavigatorGroup incominglinks = new TransformationDependencyNavigatorGroup(
					Messages.NavigatorGroupName_Transformation_3003_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			TransformationDependencyNavigatorGroup outgoinglinks = new TransformationDependencyNavigatorGroup(
					Messages.NavigatorGroupName_Transformation_3003_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(SelfRequirementEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(SelfRequirementEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(ParentRequirementEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(ParentRequirementEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(CustomRequirementEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(CustomRequirementEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case Transformation2EditPart.VISUAL_ID: {
			LinkedList<TransformationDependencyAbstractNavigatorItem> result = new LinkedList<TransformationDependencyAbstractNavigatorItem>();
			Node sv = (Node) view;
			TransformationDependencyNavigatorGroup incominglinks = new TransformationDependencyNavigatorGroup(
					Messages.NavigatorGroupName_Transformation_3004_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			TransformationDependencyNavigatorGroup outgoinglinks = new TransformationDependencyNavigatorGroup(
					Messages.NavigatorGroupName_Transformation_3004_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(SelfRequirementEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(SelfRequirementEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(ParentRequirementEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(ParentRequirementEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(CustomRequirementEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(CustomRequirementEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case SelfRequirementEditPart.VISUAL_ID: {
			LinkedList<TransformationDependencyAbstractNavigatorItem> result = new LinkedList<TransformationDependencyAbstractNavigatorItem>();
			Edge sv = (Edge) view;
			TransformationDependencyNavigatorGroup target = new TransformationDependencyNavigatorGroup(
					Messages.NavigatorGroupName_SelfRequirement_4004_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			TransformationDependencyNavigatorGroup source = new TransformationDependencyNavigatorGroup(
					Messages.NavigatorGroupName_SelfRequirement_4004_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(ExternalTransformationEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(TransformationEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(Transformation2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(TransformationEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(Transformation2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case ParentRequirementEditPart.VISUAL_ID: {
			LinkedList<TransformationDependencyAbstractNavigatorItem> result = new LinkedList<TransformationDependencyAbstractNavigatorItem>();
			Edge sv = (Edge) view;
			TransformationDependencyNavigatorGroup target = new TransformationDependencyNavigatorGroup(
					Messages.NavigatorGroupName_ParentRequirement_4005_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			TransformationDependencyNavigatorGroup source = new TransformationDependencyNavigatorGroup(
					Messages.NavigatorGroupName_ParentRequirement_4005_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(ExternalTransformationEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(TransformationEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(Transformation2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(TransformationEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(Transformation2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case CustomRequirementEditPart.VISUAL_ID: {
			LinkedList<TransformationDependencyAbstractNavigatorItem> result = new LinkedList<TransformationDependencyAbstractNavigatorItem>();
			Edge sv = (Edge) view;
			TransformationDependencyNavigatorGroup target = new TransformationDependencyNavigatorGroup(
					Messages.NavigatorGroupName_CustomRequirement_4006_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			TransformationDependencyNavigatorGroup source = new TransformationDependencyNavigatorGroup(
					Messages.NavigatorGroupName_CustomRequirement_4006_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(ExternalTransformationEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(TransformationEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(Transformation2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(TransformationEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					TransformationDependencyVisualIDRegistry.getType(Transformation2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}
		}
		return EMPTY_ARRAY;
	}

	/**
	 * @generated
	 */
	private Collection<View> getLinksSourceByType(Collection<Edge> edges, String type) {
		LinkedList<View> result = new LinkedList<View>();
		for (Edge nextEdge : edges) {
			View nextEdgeSource = nextEdge.getSource();
			if (type.equals(nextEdgeSource.getType()) && isOwnView(nextEdgeSource)) {
				result.add(nextEdgeSource);
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<View> getLinksTargetByType(Collection<Edge> edges, String type) {
		LinkedList<View> result = new LinkedList<View>();
		for (Edge nextEdge : edges) {
			View nextEdgeTarget = nextEdge.getTarget();
			if (type.equals(nextEdgeTarget.getType()) && isOwnView(nextEdgeTarget)) {
				result.add(nextEdgeTarget);
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<View> getOutgoingLinksByType(Collection<? extends View> nodes, String type) {
		LinkedList<View> result = new LinkedList<View>();
		for (View nextNode : nodes) {
			result.addAll(selectViewsByType(nextNode.getSourceEdges(), type));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<View> getIncomingLinksByType(Collection<? extends View> nodes, String type) {
		LinkedList<View> result = new LinkedList<View>();
		for (View nextNode : nodes) {
			result.addAll(selectViewsByType(nextNode.getTargetEdges(), type));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<View> getChildrenByType(Collection<? extends View> nodes, String type) {
		LinkedList<View> result = new LinkedList<View>();
		for (View nextNode : nodes) {
			result.addAll(selectViewsByType(nextNode.getChildren(), type));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<View> getDiagramLinksByType(Collection<Diagram> diagrams, String type) {
		ArrayList<View> result = new ArrayList<View>();
		for (Diagram nextDiagram : diagrams) {
			result.addAll(selectViewsByType(nextDiagram.getEdges(), type));
		}
		return result;
	}

	// TODO refactor as static method
	/**
	 * @generated
	 */
	private Collection<View> selectViewsByType(Collection<View> views, String type) {
		ArrayList<View> result = new ArrayList<View>();
		for (View nextView : views) {
			if (type.equals(nextView.getType()) && isOwnView(nextView)) {
				result.add(nextView);
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private boolean isOwnView(View view) {
		return TransformationSetsEditPart.MODEL_ID.equals(TransformationDependencyVisualIDRegistry.getModelID(view));
	}

	/**
	 * @generated
	 */
	private Collection<TransformationDependencyNavigatorItem> createNavigatorItems(Collection<View> views,
			Object parent, boolean isLeafs) {
		ArrayList<TransformationDependencyNavigatorItem> result = new ArrayList<TransformationDependencyNavigatorItem>(
				views.size());
		for (View nextView : views) {
			result.add(new TransformationDependencyNavigatorItem(nextView, parent, isLeafs));
		}
		return result;
	}

	/**
	 * @generated
	 */
	public Object getParent(Object element) {
		if (element instanceof TransformationDependencyAbstractNavigatorItem) {
			TransformationDependencyAbstractNavigatorItem abstractNavigatorItem = (TransformationDependencyAbstractNavigatorItem) element;
			return abstractNavigatorItem.getParent();
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean hasChildren(Object element) {
		return element instanceof IFile || getChildren(element).length > 0;
	}

}
