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
package net.atos.optimus.m2m.engine.core.transformations;

import net.atos.optimus.m2m.engine.core.OptimusM2MEngine;
import net.atos.optimus.m2m.engine.core.exceptions.TransformationFailedException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * Abstract implementation of a Transformation.
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * 
 * @param <T>
 *            EObject derived class.
 * @since 1.0
 */
public abstract class AbstractTransformation<T extends EObject> {

	/**
	 * 
	 */
	private Object password;

	/**
	 * 
	 */
	private OptimusM2MEngine transformationEngine;

	/**
	 * ID of the transformation. Normally provided by the extension point
	 */
	private String id;

	/**
	 * boolean to know whether transformation has already been executed or not.
	 */
	private boolean isExecuted = false;

	/**
	 * Input EObject
	 */
	private T eObject;

	/**
	 * @return true if the transformation has already been executed, false
	 *         otherwise
	 */
	public boolean isExecuted() {
		return isExecuted;
	}

	/**
	 * Flags the transformation as executed
	 */
	private void setExecuted() {
		this.isExecuted = true;
	}

	/**
	 * Creates a new transformation with ID and input EObject
	 * 
	 * @param eObject
	 *            input EObject
	 * @param id
	 *            ID
	 */
	public AbstractTransformation(T eObject, String id) {
		this.eObject = eObject;
		this.id = id;
	}

	/**
	 * @return the input EObject of the transformation
	 */
	public T getEObject() {
		return this.eObject;
	}

	/**
	 * 
	 * @return the ID of the transformation, as defined in its extension point
	 */
	public String getId() {
		return id;
	}

	/**
	 * Executes the transformation.
	 * 
	 * @param context
	 *            : global transformation context
	 * @param force
	 *            : true if the user wants to force the execution of the
	 *            transformation, even if it has been disabled in the Eclipse
	 *            preferences.
	 */
	public final void execute(ITransformationContext context) {
		this.transform(context);
		setExecuted();
	}

	/**
	 * Transformation method, that will hold the transformation code itself.
	 * 
	 * @param context
	 *            Transformation Context.
	 */
	protected abstract void transform(ITransformationContext context);

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return getIdentifier().hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof AbstractTransformation))
			return false;
		return this.getIdentifier().equals(((AbstractTransformation<?>) obj).getIdentifier());
	}

	/**
	 * Computes identifier of the current transformation instance, using the id
	 * of the transformation and the input EObject.
	 * 
	 * @return
	 */
	private String getIdentifier() {
		return id + "_" + EcoreUtil.getURI(eObject);
	}

	/**
	 * Sets the Transformation Engine that manages this transformation.
	 * 
	 * @param engine
	 */
	public void setTransformationEngine(OptimusM2MEngine engine) {
		this.transformationEngine = engine;
	}

	/**
	 * Sets the Transformation Engine's password
	 * 
	 * @param password
	 */
	public void setPassword(Object password) {
		this.password = password;
	}

	/**
	 * Schedules a new transformation, identified by the provided transformation
	 * ID, on the EObject passed as parameter. The real scheduling process is
	 * done by the associated Transformation Engine.
	 * 
	 * @param transformationId
	 * @param eObject
	 */
	protected void scheduleTransformation(String transformationId, EObject eObject) {
		if (this.transformationEngine != null) {
			TransformationReference reference = this.transformationEngine.getTransformationReference(transformationId);
			if (reference != null)
				this.transformationEngine.scheduleTransformation(eObject, reference, this.password);
		}
	}

	/**
	 * Runs immediately a new transformation, identified by the provided
	 * transformation ID, on the EObject passed as parameter. The real execution
	 * is managed by the associated Transformation Engine.
	 * 
	 * @param transformationId
	 * @param eObject
	 * @throws TransformationFailedException
	 */
	@Deprecated
	protected void runTransformation(String transformationId, EObject eObject) throws TransformationFailedException {
		if (this.transformationEngine != null) {
			TransformationReference reference = this.transformationEngine.getTransformationReference(transformationId);
			if (reference != null)
				this.transformationEngine.executeTransformation(eObject, reference, this.password);
		}
	}
}
