package net.atos.optimus.m2m.engine.core.ctxinject;

import org.eclipse.emf.ecore.EObject;

public interface IContextRetriever {

	EObject getFromEObject(EObject eObject);

}
