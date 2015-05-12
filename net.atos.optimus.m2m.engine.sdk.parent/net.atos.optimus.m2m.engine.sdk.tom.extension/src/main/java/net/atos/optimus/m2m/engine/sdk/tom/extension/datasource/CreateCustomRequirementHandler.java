package net.atos.optimus.m2m.engine.sdk.tom.extension.datasource;

import net.atos.optimus.m2m.engine.core.requirements.AbstractRequirement;
import net.atos.optimus.m2m.engine.core.transformations.TransformationReference;
import net.atos.optimus.m2m.engine.sdk.tom.CustomRequirement;
import net.atos.optimus.m2m.engine.sdk.tom.Requirement;
import net.atos.optimus.m2m.engine.sdk.tom.extension.Activator;
import net.atos.optimus.m2m.engine.sdk.tom.extension.logging.DependencyDiagramMessages;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.Bundle;

/**
 * An handler creating custom requirement
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class CreateCustomRequirementHandler extends CreateAbstractRequirementHandler {

	@Override
	public AbstractRequirement createAbstractRequirement(TransformationReference transformationReference,
			Requirement requirement, Bundle bundle) {
		if (!(requirement instanceof CustomRequirement) || ((CustomRequirement) requirement).getImplementation() == null) {
			return null;
		}
		AbstractRequirement abstractRequirement = null;
		try {
			abstractRequirement = (AbstractRequirement) bundle.loadClass(
					((CustomRequirement) requirement).getImplementation()).newInstance();
		} catch (InstantiationException e) {
			handlingExceptionLoadingCustomRequirement(e, requirement);
		} catch (IllegalAccessException e) {
			handlingExceptionLoadingCustomRequirement(e, requirement);
		} catch (ClassNotFoundException e) {
			handlingExceptionLoadingCustomRequirement(e, requirement);
		}
		DependencyDiagramMessages.EP15.log(transformationReference.getId(), requirement.getReference().getName(),
				((CustomRequirement) requirement).getImplementation());
		return abstractRequirement;
	}

	/**
	 * Handler when loading custom requirement encountered an exception
	 * 
	 * @param e
	 *            the exception.
	 * @param requirement
	 *            the requirement attended to load.
	 */
	protected void handlingExceptionLoadingCustomRequirement(Exception e, Requirement requirement) {
		DependencyDiagramMessages.EP12.log(e.getMessage());
		Activator
				.getDefault()
				.getLog()
				.log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Invalid custom requirement implementation : "
						+ ((CustomRequirement) requirement).getImplementation(), e));
	}

}
