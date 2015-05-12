package net.atos.optimus.m2m.engine.sdk.tom.extension.datasource;

import net.atos.optimus.m2m.engine.core.requirements.AbstractRequirement;
import net.atos.optimus.m2m.engine.core.transformations.TransformationReference;
import net.atos.optimus.m2m.engine.sdk.tom.ParentRequirement;
import net.atos.optimus.m2m.engine.sdk.tom.Requirement;
import net.atos.optimus.m2m.engine.sdk.tom.extension.logging.DependencyDiagramMessages;

import org.osgi.framework.Bundle;

/**
 * An handler creating parent requirement
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class CreateParentRequirementHandler extends CreateAbstractRequirementHandler {

	@Override
	public AbstractRequirement createAbstractRequirement(TransformationReference transformationReference,
			Requirement requirement, Bundle bundle) {
		if (!(requirement instanceof ParentRequirement)) {
			return null;
		}
		AbstractRequirement abstractRequirement = new net.atos.optimus.m2m.engine.core.requirements.ParentRequirement();
		DependencyDiagramMessages.EP14.log(transformationReference.getId(), requirement.getReference().getName());
		return abstractRequirement;
	}

}
