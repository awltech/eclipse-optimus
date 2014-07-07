package net.atos.optimus.m2m.engine.core.ctxinject;

import org.eclipse.emf.ecore.EObject;

/**
 * Implementations of this interface are to be used in the CustomContextElement
 * annotation. They will be used to retrieve the EObject to use as the context's
 * key, from the EObject used as current in a default transformation.
 * 
 * @author mvanbesien
 * 
 */
public interface IContextRetriever {

	/**
	 * Retrieves expected eObject from eObject passed as parameter.
	 * 
	 * @param eObject
	 * @return
	 */
	EObject getFromEObject(EObject eObject);

}
