package net.atos.optimus.m2m.engine.sdk.tom.properties.listeners;

import net.atos.optimus.m2m.engine.sdk.tom.properties.Activator;
import net.atos.optimus.m2m.engine.sdk.tom.properties.zones.InputZone;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.diagram.ui.properties.views.TextChangeHelper;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.swt.widgets.Control;

/**
 * A listener dedicated to observe change on an input text zone
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class TextChangeListener extends TextChangeHelper {

	/** The listened text zone */
	protected InputZone inputZone;

	/**
	 * Constructor
	 * 
	 * @param inputZone
	 *            the listened text zone.
	 */
	public TextChangeListener(InputZone inputZone) {
		this.inputZone = inputZone;
	}

	@Override
	public void textChanged(Control control) {
		/* Get the text and the associated feature */
		Object value = this.inputZone.getInputValue();
		EStructuralFeature feature = this.inputZone.getAssociatedFeature();

		/* Set the value in the model */
		SetRequest request = new SetRequest(this.inputZone.giveEditingDomain(), this.inputZone.giveEObject(), feature,
				value);
		SetValueCommand command = new SetValueCommand(request);
		try {
			command.execute(new NullProgressMonitor(), null);
		} catch (ExecutionException e) {
			Activator
					.getDefault()
					.getLog()
					.log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Listener creation fail on the feature : "
							+ feature.getClass().getCanonicalName(), e));
		}

		this.inputZone.refreshZoneAndDiagram();
	}

}
