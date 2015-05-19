/**
 * Optimus, framework for Model Transformation
 *
 * Copyright (C) 2013 Worldline or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 */
package net.atos.optimus.m2m.engine.sdk.tom.properties.listeners;

import net.atos.optimus.m2m.engine.sdk.tom.properties.Activator;
import net.atos.optimus.m2m.engine.sdk.tom.properties.zones.InputZone;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

/**
 * A listener dedicated to observe change on checkbox
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class CheckboxListener implements SelectionListener {

	/** The listened text zone */
	protected InputZone inputZone;

	/**
	 * Constructor
	 * 
	 * @param inputZone
	 *            the listened text zone.
	 */
	public CheckboxListener(InputZone inputZone) {
		this.inputZone = inputZone;
	}

	@Override
	public void widgetSelected(SelectionEvent event) {
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

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
