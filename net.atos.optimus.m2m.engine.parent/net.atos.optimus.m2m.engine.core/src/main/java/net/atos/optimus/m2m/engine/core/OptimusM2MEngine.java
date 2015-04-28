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
package net.atos.optimus.m2m.engine.core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import net.atos.optimus.m2m.engine.core.adapters.AbstractOptimusAdapter;
import net.atos.optimus.m2m.engine.core.adapters.EObjectChildAdditionAdapter;
import net.atos.optimus.m2m.engine.core.adapters.EObjectLockAdapter;
import net.atos.optimus.m2m.engine.core.exceptions.TransformationFailedException;
import net.atos.optimus.m2m.engine.core.hooks.TransformationExecutionHookManager;
import net.atos.optimus.m2m.engine.core.logging.EObjectLabelProvider;
import net.atos.optimus.m2m.engine.core.logging.OptimusM2MEngineMessages;
import net.atos.optimus.m2m.engine.core.masks.ITransformationMask;
import net.atos.optimus.m2m.engine.core.masks.TransformationMaskDataSource;
import net.atos.optimus.m2m.engine.core.masks.TransformationMaskDataSourceManager;
import net.atos.optimus.m2m.engine.core.requirements.AbstractRequirement;
import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationFactory;
import net.atos.optimus.m2m.engine.core.transformations.TransformationDataSource;
import net.atos.optimus.m2m.engine.core.transformations.TransformationDataSourceManager;
import net.atos.optimus.m2m.engine.core.transformations.TransformationReference;
import net.atos.optimus.m2m.engine.core.transformations.TransformationSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.LabelProvider;

/**
 * The purpose of this class is to propose a chaining process to execute the
 * registered transformations, that follows the user's defined requirements.
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 * 
 */
public class OptimusM2MEngine {

	/**
	 * Internal object, to be provided to objects that can have access to this
	 * API of this instance.
	 */
	private Object password = new Object();

	/**
	 * Internal wrapper class, that links a pending transformation and the
	 * object it should be applied on.
	 * 
	 * @author Maxence Vanbésien (mvaawl@gmail.com)
	 * @since 1.0
	 * 
	 */
	private static class PendingTransformation {

		/**
		 * Pending Transformation Reference
		 */
		TransformationReference reference;

		/**
		 * EObject on which the pending Transformation should be applied.
		 */
		EObject eObject;

	}

	/**
	 * Set to true if transformations are filtered according to their
	 * transformation set
	 */
	private boolean transformationsSetLimited = false;

	/**
	 * Array of transformation set ids. If boolean above set to true, a given
	 * transformation eligibility will be conditionned whether its
	 * transformation set is available from this array
	 */
	private String[] allowedTransformationSetsID = new String[0];

	/**
	 * Set of Objects, from the user selection
	 */
	private Set<EObject> selectedElements = new LinkedHashSet<EObject>();

	/**
	 * Set of Objects, corresponding to the objects that will be eligible for
	 * transitions. Set is computed from user selected elements.
	 */
	private Set<EObject> resolvedElements = new LinkedHashSet<EObject>();

	/**
	 * Map of transformations sorted by EObject. This structure acts as a cache
	 * 
	 */
	private Map<EObject, Set<AbstractTransformation<?>>> transformationsCache = new HashMap<EObject, Set<AbstractTransformation<?>>>();

	/**
	 * Map that contains the transformations that can be executed in this
	 * transformation set limitation context.
	 */
	private Map<String, TransformationReference> accessibleTransformationReferences = new LinkedHashMap<String, TransformationReference>();

	/**
	 * Queue containing the EObjects to transform, that have been added as
	 * children of resolved elements during transformations.
	 */
	private Queue<EObject> pendingEObjects = new ConcurrentLinkedQueue<EObject>();

	/**
	 * Queue containing the transformations that have been scheduled by other
	 * transformations.
	 */
	private Queue<PendingTransformation> pendingTransformations = new ConcurrentLinkedQueue<PendingTransformation>();

	/**
	 * Context implementation to be provided to the transformations.
	 */
	private ITransformationContext context;

	/**
	 * List of adapters that are used during Optimus execution
	 */
	private Set<AbstractOptimusAdapter> optimusAdapters = new HashSet<AbstractOptimusAdapter>();

	/**
	 * Label Provider which purpose is to provide naming system for EObjects.
	 */
	private LabelProvider eObjectLabelProvider = new EObjectLabelProvider();

	/**
	 * Instance of transformation data source. Default implementation heads to extension points management
	 */
	protected List<TransformationDataSource> transformationDataSources = TransformationDataSourceManager.INSTANCE.getTransformationDataSources();

	/**
	 * Instance of transformation mask data source.
	 */
	protected List<TransformationMaskDataSource> transformationMaskDataSources = TransformationMaskDataSourceManager.INSTANCE.getTransformationMaskDataSources();
	
	/**
	 * Instance of Mask used to filter the transformations
	 */
	private ITransformationMask userTransformationMask = null;
	
	/**
	 * Creates new transformation engine, with provided context implementation
	 * 
	 * This default constructor will allow the input elements to be modified or
	 * removed during the transformation and added elements won't be considered
	 * by transformation
	 * 
	 * @param context
	 */
	public OptimusM2MEngine(ITransformationContext context) {
		this(context, false, false);
	}

	/**
	 * Creates new transformation engine, with provided context implementation
	 * 
	 * @param context
	 * @param lockInput
	 *            : true if all the resolved elements should be locked in
	 *            modification & deletion during transformation
	 * @param trackAddition
	 *            : true if all the children of input, created during process,
	 *            have to be considered as input of the process.
	 */
	public OptimusM2MEngine(ITransformationContext context, boolean lockInput, boolean trackAddition) {
		this.context = context;
		if (lockInput)
			this.optimusAdapters.add(new EObjectLockAdapter());
		if (trackAddition)
			this.optimusAdapters.add(new EObjectChildAdditionAdapter(this, this.password));
	}

	/**
	 * Limits the possible transformation sets. If this method is called, A
	 * given transformation will be executed only if its transformation set is
	 * referenced in the provided ones.
	 * 
	 * @param transformationSetIDs
	 * @return
	 */
	public OptimusM2MEngine limitTransformationSetsTo(String... transformationSetIDs) {
		this.allowedTransformationSetsID = transformationSetIDs;
		this.transformationsSetLimited = true;
		OptimusM2MEngineMessages.TE03.log(Arrays.toString(transformationSetIDs));
		return this;
	}
	
	/**
	 * Applies a mask on the transformations, to prevent them from being
	 * enabled. Applying a mask will NOT go over the user configuration
	 * specified in the Optimus preferences...
	 * 
	 * @param transformationMask
	 * @return
	 */
	public OptimusM2MEngine applyTransformationMask(ITransformationMask transformationMask) {
		this.userTransformationMask = transformationMask;
		return this;
	}
	
	/**
	 * Sets user selection
	 * 
	 * @param selectedElements
	 * @return
	 */
	public OptimusM2MEngine setElements(Set<? extends EObject> selectedElements) {
		this.selectedElements.clear();
		this.selectedElements.addAll(selectedElements);
		return this;
	}

	/**
	 * Sets user selection
	 * 
	 * @param selectedElement
	 * @return
	 */
	public OptimusM2MEngine setElement(EObject selectedElement) {
		this.selectedElements.clear();
		this.selectedElements.add(selectedElement);
		return this;
	}
	
	/**
	 * Executes the transformations for the user selection
	 * 
	 * @throws TransformationFailedException
	 */
	public IStatus execute() {
		OptimusM2MEngineMessages.TE01.log();
		long begin = System.currentTimeMillis();
		this.resolveTransformations();
		this.resolveElements();
		try {
			this.executeAllTransformations();
			OptimusM2MEngineMessages.TE02.log(System.currentTimeMillis() - begin);
			return Status.OK_STATUS;
		} catch (TransformationFailedException tfe) {
			OptimusM2MEngineMessages.TE23
					.log(tfe.getTransformationID(), eObjectLabelProvider.getText(tfe.getEObject()));
			IStatus status = new Status(IStatus.ERROR, Activator.PLUGIN_ID, OptimusM2MEngineMessages.TFE.message(tfe
					.getTransformationID()), tfe);
			Activator.getDefault().getLog().log(status);
			return status;
		} finally {
			for (AbstractOptimusAdapter optimusAdapter : optimusAdapters)
				optimusAdapter.release();
		}
	}

	/**
	 * The purpose of this method is to compute the subset of transformation
	 * references that are accessible through this instance, according to
	 * transformation sets limitations and/or their privacy.
	 */
	private void resolveTransformations() {
		OptimusM2MEngineMessages.TE24.log();
		for (final TransformationDataSource transformationDataSource : this.transformationDataSources) {
			for (final TransformationReference reference : transformationDataSource.getAll()) {
				String transformationSetID = reference.getTransformationSet().getId();
				if (this.transformationsSetLimited) {
					boolean found = false;
					for (int i = 0; i < this.allowedTransformationSetsID.length && !found; i++) {
						if (this.allowedTransformationSetsID[i].equals(transformationSetID))
							found = true;
					}
					if (found) {
						OptimusM2MEngineMessages.TE26.log(reference.getId(), transformationSetID);
						this.accessibleTransformationReferences.put(reference.getId(), reference);
					} else {
						OptimusM2MEngineMessages.TE27.log(reference.getId(), transformationSetID);
					}
				} else {
					if (!reference.getTransformationSet().isPrivate()) {
						OptimusM2MEngineMessages.TE28.log(reference.getId(), transformationSetID);
						this.accessibleTransformationReferences.put(reference.getId(), reference);
					} else {
						OptimusM2MEngineMessages.TE29.log(reference.getId(), transformationSetID);
					}
				}
			}
		}
		OptimusM2MEngineMessages.TE25.log();
	}

	/**
	 * Executes all transformations for all resolved elements.
	 * 
	 * @throws TransformationFailedException
	 */
	private void executeAllTransformations() throws TransformationFailedException {
		OptimusM2MEngineMessages.TE04.log();
		long begin = System.currentTimeMillis();

		OptimusM2MEngineMessages.TE30.log();
		for (EObject eObject : this.resolvedElements) {
			for (TransformationReference reference : this.accessibleTransformationReferences.values()) {
				executeTransformation(eObject, reference, this.password);
			}
		}

		OptimusM2MEngineMessages.TE31.log();
		while (!pendingEObjects.isEmpty()) {
			EObject eObject = pendingEObjects.poll();
			for (TransformationReference reference : this.accessibleTransformationReferences.values()) {
				executeTransformation(eObject, reference, this.password);
			}
		}
		OptimusM2MEngineMessages.TE05.log(System.currentTimeMillis() - begin);
	}

	/**
	 * Executes a transformation identified by the provided reference, on the
	 * eobject. Password object has to be provided, if external process wants to
	 * invoke this method from the outside.
	 * 
	 * @param eObject
	 * @param reference
	 * @param password
	 * @return
	 * @throws TransformationFailedException
	 */
	public boolean executeTransformation(EObject eObject, TransformationReference reference, Object password)
			throws TransformationFailedException {

		if (this.password != password) {
			OptimusM2MEngineMessages.TE07.log(reference.getId());
			return false;
		}

		ITransformationFactory factory = reference.getTransformationFactory();
		TransformationSet transformationSet = reference.getTransformationSet();

		if (transformationsSetLimited || transformationSet.isPrivate()) {
			boolean found = false;
			for (int i = 0; i < this.allowedTransformationSetsID.length && !found; i++) {
				if (transformationSet != null && allowedTransformationSetsID[i].equals(transformationSet.getId()))
					found = true;
			}

			if (!found) {
				OptimusM2MEngineMessages.TE08.log(reference.getId());
				return false;
			}
		}

		if (checkAlreadyExecuted(eObject, reference)) {
			OptimusM2MEngineMessages.TE09.log(reference.getId(), reference.getDescription(),
					eObjectLabelProvider.getText(eObject));
			return true;
		}

		if (factory.isEligible(eObject) && (transformationSet == null || transformationSet.isEligible(eObject))) {

			OptimusM2MEngineMessages.TE06.log(reference.getId(), reference.getDescription(),
					eObjectLabelProvider.getText(eObject));

			if (!reference.isEnabled(this.getTransformationMask())) {
				OptimusM2MEngineMessages.TE10.log(reference.getId());
				return false;
			}

			OptimusM2MEngineMessages.TE11.log();
			for (AbstractRequirement requirement : reference.getRequirements()) {
				TransformationReference requiredReference = this.accessibleTransformationReferences.get(requirement
						.getId());
				if (requiredReference == null) {
					OptimusM2MEngineMessages.TE22.log(requirement.getId());
				} else
					for (EObject requiredEObject : requirement.getMatchingEObjects(eObject, this.context)) {
						boolean executed = executeTransformation(requiredEObject, requiredReference, password);
						if (!executed) {
							OptimusM2MEngineMessages.TE12.log(requiredReference.getId());
							return false;
						}
					}
			}

			AbstractTransformation<?> transformation = factory.create(eObject, reference.getId());
			transformation.setPassword(password);
			transformation.setTransformationEngine(this);

			Set<AbstractTransformation<?>> cachedTransformationsForEObject = this.transformationsCache.get(eObject);
			if (cachedTransformationsForEObject == null) {
				cachedTransformationsForEObject = new LinkedHashSet<AbstractTransformation<?>>();
				this.transformationsCache.put(eObject, cachedTransformationsForEObject);
			}

			cachedTransformationsForEObject.add(transformation);
			try {
				TransformationExecutionHookManager.INSTANCE.beforeExecution(transformation, this.context);
				transformation.execute(this.context);
				OptimusM2MEngineMessages.TE13.log(reference.getId(), this.eObjectLabelProvider.getText(eObject));
				TransformationExecutionHookManager.INSTANCE.afterExecution(transformation, this.context);
			} catch (Exception e) {
				throw new TransformationFailedException(reference.getId(), eObject, e);
			}

			while (!pendingTransformations.isEmpty()) {
				PendingTransformation pendingTransformation = pendingTransformations.poll();
				OptimusM2MEngineMessages.TE14.log(pendingTransformation.reference,
						this.eObjectLabelProvider.getText(pendingTransformation.eObject));
				this.executeTransformation(pendingTransformation.eObject, pendingTransformation.reference, password);
			}
		}
		return true;
	}

	/**
	 * Returns the transformation mask to apply for this execution.
	 * 
	 * If none is defined by the user, it returns the one selected through preferences.
	 * 
	 * @return
	 */
	private ITransformationMask getTransformationMask() {
		ITransformationMask transformationMask = this.userTransformationMask != null ? this.userTransformationMask : TransformationMaskDataSourceManager.INSTANCE.getPreferredTransformationMask().getImplementation();
 		if(transformationMask == null){
 			OptimusM2MEngineMessages.TE32.log();
 		}
		return transformationMask;
	}

	/**
	 * Checks if a transformation instance (identified by reference) has already
	 * been executed for the given EObject.
	 * 
	 * @param eObject
	 * @param reference
	 * @return
	 */
	private boolean checkAlreadyExecuted(EObject eObject, TransformationReference reference) {

		Set<AbstractTransformation<?>> transformations = this.transformationsCache.get(eObject);
		if (transformations == null || transformations.size() == 0)
			return false;

		String id = reference.getId();
		for (AbstractTransformation<?> transformation : transformations)
			if (id.equals(transformation.getId()))
				return true;

		return false;
	}

	/**
	 * Resolves all the eligible elements from the user selection
	 */
	private void resolveElements() {
		OptimusM2MEngineMessages.TE15.log();
		this.resolvedElements.clear();

		// For each selected element
		for (EObject selectedElement : this.selectedElements) {

			// Add the selected element itself
			this.resolvedElements.add(selectedElement);
			this.adapt(selectedElement);
			OptimusM2MEngineMessages.TE16.log(eObjectLabelProvider.getText(selectedElement));

			// Add all the parents of the selected object
			EObject parent = selectedElement.eContainer();

			while (parent != null) {
				this.adapt(parent);
				this.resolvedElements.add(parent);
				OptimusM2MEngineMessages.TE17.log(eObjectLabelProvider.getText(parent));
				parent = parent.eContainer();
			}

			// Add all the children of the selected object
			TreeIterator<EObject> allChildren = selectedElement.eAllContents();
			while (allChildren.hasNext()) {
				EObject child = allChildren.next();
				this.resolvedElements.add(child);
				this.adapt(child);
				OptimusM2MEngineMessages.TE18.log(eObjectLabelProvider.getText(child));
			}
		}
	}

	/**
	 * Adds Optimus Adapters to eObject
	 * 
	 * @param eObject
	 */
	private void adapt(EObject eObject) {
		for (AbstractOptimusAdapter optimusAdapter : this.optimusAdapters)
			optimusAdapter.adaptEObject(eObject);
	}

	/**
	 * Schedules a transformation (identified by reference) for the EObject
	 * provided. Password object has to be provided, if external process wants
	 * to invoke this method from the outside.
	 * 
	 * @param eObject
	 * @param reference
	 * @param password
	 */
	public void scheduleTransformation(EObject eObject, TransformationReference reference, Object password) {
		OptimusM2MEngineMessages.TE19.log(reference.getId(), eObjectLabelProvider.getText(eObject));

		if (this.password != password) {
			OptimusM2MEngineMessages.TE20.log();
			return;
		}

		this.adapt(eObject);

		PendingTransformation pendingTransformation = new PendingTransformation();
		pendingTransformation.eObject = eObject;
		pendingTransformation.reference = reference;
		this.pendingTransformations.offer(pendingTransformation);
		OptimusM2MEngineMessages.TE21.log();
	}

	public void scheduleEObject(EObject eObject, Object password) {
		if (this.password != password) {
			OptimusM2MEngineMessages.TE20.log();
			return;
		}

		if (pendingEObjects.contains(eObject) || this.resolvedElements.contains(eObject)) {
			// Add message
			return;
		}

		this.adapt(eObject);
		this.pendingEObjects.offer(eObject);
	}

	/**
	 * Returns internal transformation datasource implementation
	 * 
	 * @return implementation
	 */
	@Deprecated
	public TransformationDataSource getTransformationDataSource() {
		return transformationDataSources.size() > 0 ? transformationDataSources.iterator().next() : null;
	}
	
	/**
	 * Returns a transformation reference from its id. It tries to locate it in all the transformation data sources identified in this engine.
	 * 
	 * @param transformationReferenceId
	 * @return
	 */
	public TransformationReference getTransformationReference(String transformationReferenceId) {
		if (this.transformationDataSources == null) {
			return null;
		}
		for (Iterator<TransformationDataSource> iterator = this.transformationDataSources.iterator();iterator.hasNext();) {
			TransformationDataSource transformationDataSource = iterator.next();
			TransformationReference reference = transformationDataSource != null ? transformationDataSource.getById(transformationReferenceId) : null;
			if (reference != null) {
				return reference;
			}
		}
		return null;
	}
}
