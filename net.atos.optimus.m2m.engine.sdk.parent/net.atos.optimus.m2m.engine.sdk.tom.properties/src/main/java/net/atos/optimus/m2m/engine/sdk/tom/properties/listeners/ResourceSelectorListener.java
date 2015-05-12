package net.atos.optimus.m2m.engine.sdk.tom.properties.listeners;

import net.atos.optimus.m2m.engine.sdk.tom.properties.Activator;
import net.atos.optimus.m2m.engine.sdk.tom.properties.resourceselectors.ResourceInfoUtil;
import net.atos.optimus.m2m.engine.sdk.tom.properties.zones.ResourceSelectorZone;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipselabs.resourceselector.core.processor.ResourceProcessorFactory;
import org.eclipselabs.resourceselector.core.resources.ResourceInfo;
import org.eclipselabs.resourceselector.core.selector.ResourceSelector;

/**
 * A listener dedicated to a class name from a resource selector
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class ResourceSelectorListener implements SelectionListener {

	/** The listened text zone */
	protected ResourceSelectorZone textZone;

	/**
	 * Constructor
	 * 
	 * @param textZone
	 *            the listened text zone.
	 */
	public ResourceSelectorListener(ResourceSelectorZone textZone) {
		this.textZone = textZone;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		/* Resource selector creation and run */
		ResourceProcessorFactory[] factories = new ResourceProcessorFactory[] { this.textZone
				.getResourceProcessorFactory() };
		Resource resource = this.textZone.giveEObject().eResource();
		ResourceSelector resourceSelector = new ResourceSelector(factories, WorkspaceSynchronizer.getFile(resource).getProject());
		resourceSelector.setTitle(this.textZone.getTitleResourceSelector());
		resourceSelector.run();

		/* Set the value in the model with the resource selected by user */
		ResourceInfo savedResourceInfo = resourceSelector.getSavedResourceInfo();

		if (savedResourceInfo != null) {
			String value = ResourceInfoUtil.extractFullQualifiedName(savedResourceInfo);
			EStructuralFeature feature = this.textZone.getAssociatedFeature();
			SetRequest request = new SetRequest(this.textZone.giveEditingDomain(), this.textZone.giveEObject(),
					feature, value);
			SetValueCommand command = new SetValueCommand(request);

			try {
				command.execute(new NullProgressMonitor(), null);
			} catch (ExecutionException e1) {
				Activator
						.getDefault()
						.getLog()
						.log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Listener creation fail on the feature : "
								+ feature.getClass().getCanonicalName(), e1));
			}

			this.textZone.refreshZoneAndDiagram();
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
