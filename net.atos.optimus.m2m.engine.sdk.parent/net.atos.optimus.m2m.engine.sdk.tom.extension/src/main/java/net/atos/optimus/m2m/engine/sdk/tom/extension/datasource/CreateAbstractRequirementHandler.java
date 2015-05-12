package net.atos.optimus.m2m.engine.sdk.tom.extension.datasource;

import net.atos.optimus.m2m.engine.core.requirements.AbstractRequirement;
import net.atos.optimus.m2m.engine.core.transformations.TransformationReference;
import net.atos.optimus.m2m.engine.sdk.tom.Requirement;
import net.atos.optimus.m2m.engine.sdk.tom.extension.logging.DependencyDiagramMessages;

import org.osgi.framework.Bundle;

/**
 * Models an handler creating abstract requirement
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public abstract class CreateAbstractRequirementHandler {

	/** The next handler in the chain */
	protected CreateAbstractRequirementHandler nextHandler;

	/**
	 * Create an abstract requirement according to the specified requirement
	 * with the current handler
	 * 
	 * @param transformationReference
	 *            the transformation reference associated to the specified
	 *            requirement.
	 * 
	 * @param requirement
	 *            the requirement.
	 * @param bundle
	 *            the bundle associated to the requirement.
	 * 
	 * @return the abstract requirement according to the specified requirement
	 *         or null if the handler fail to treat the requirement.
	 */
	public abstract AbstractRequirement createAbstractRequirement(TransformationReference transformationReference,
			Requirement requirement, Bundle bundle);

	/**
	 * Create an abstract requirement according to the specified requirement
	 * 
	 * @param transformationReference
	 *            the transformation reference associated to the specified
	 *            requirement.
	 * @param requirement
	 *            the requirement.
	 * @param bundle
	 *            the bundle associated to the requirement.
	 * @return the abstract requirement according to the specified requirement
	 *         or null if all next handlers fail.
	 */
	public AbstractRequirement postCreateAbstractRequirement(TransformationReference transformationReference,
			Requirement requirement, Bundle bundle) {
		AbstractRequirement abstractRequirement = this.createAbstractRequirement(transformationReference, requirement, bundle);
		if (abstractRequirement != null) {
			abstractRequirement.setId(requirement.getReference().getName());
			return abstractRequirement;
		}
		if(this.nextHandler == null){
			DependencyDiagramMessages.EP19.log(transformationReference.getId(),requirement.getReference().getName());
			return null;
		}
		return this.nextHandler.postCreateAbstractRequirement(transformationReference, requirement, bundle);
	}

	/**
	 * Set the next handler of the current handler
	 * 
	 * @param nextHandler
	 *            the next handler.
	 */
	public void setHandler(CreateAbstractRequirementHandler nextHandler) {
		this.nextHandler = nextHandler;
	}

}
