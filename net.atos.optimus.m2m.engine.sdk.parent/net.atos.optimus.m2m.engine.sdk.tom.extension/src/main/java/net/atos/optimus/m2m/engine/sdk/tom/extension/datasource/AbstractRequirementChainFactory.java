package net.atos.optimus.m2m.engine.sdk.tom.extension.datasource;

import net.atos.optimus.m2m.engine.core.requirements.AbstractRequirement;
import net.atos.optimus.m2m.engine.core.transformations.TransformationReference;
import net.atos.optimus.m2m.engine.sdk.tom.Requirement;

import org.osgi.framework.Bundle;

/** A chain factory to create abstract requirement
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class AbstractRequirementChainFactory {
	
	/** The unique instance of AbstractRequirementChainFactory */
	public static final AbstractRequirementChainFactory INSTANCE = new AbstractRequirementChainFactory();
	
	protected CreateAbstractRequirementHandler firstHandler;
	
	/** Constructor : initialize the factories chain
	 * 
	 */
	private AbstractRequirementChainFactory(){
		this.firstHandler = new CreateObjectRequirementHandler();
		CreateAbstractRequirementHandler secondHandler = new CreateParentRequirementHandler();
		this.firstHandler.setHandler(secondHandler);
		secondHandler.setHandler(new CreateCustomRequirementHandler());
	}
	
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
	 *         or null if all handlers fail in the chain.
	 */
	public AbstractRequirement createAbstractRequirement(TransformationReference transformationReference,
			Requirement requirement, Bundle bundle){
		return this.firstHandler.postCreateAbstractRequirement(transformationReference, requirement, bundle);
	}

}
