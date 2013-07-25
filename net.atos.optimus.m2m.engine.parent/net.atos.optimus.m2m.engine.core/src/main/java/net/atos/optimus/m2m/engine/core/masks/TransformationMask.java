package net.atos.optimus.m2m.engine.core.masks;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.atos.optimus.m2m.engine.core.transformations.ExtensionPointTransformationDataSource;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationDataSource;
import net.atos.optimus.m2m.engine.core.transformations.TransformationReference;

public class TransformationMask implements ITransformationMask {

	private boolean defaultEnabled = true;

	private Set<String> exceptions = new HashSet<String>();

	private ITransformationDataSource transformationDataSource = ExtensionPointTransformationDataSource.instance();

	private TransformationMask(boolean defaultEnabled) {
		this.defaultEnabled = defaultEnabled;
	}

	public TransformationMask withTransformationDataSource(ITransformationDataSource transformationDataSource) {
		this.transformationDataSource = transformationDataSource;
		return this;
	}

	public static TransformationMask allOn() {
		return new TransformationMask(true);
	}

	public static TransformationMask allOff() {
		return new TransformationMask(false);
	}

	public TransformationMask withTransformation(String id) {
		this.manageTransformation(id, true);
		return this;
	}

	public TransformationMask withTransformationSet(String id) {
		this.manageTransformationSet(id, true);
		return this;
	}

	public TransformationMask withoutTransformation(String id) {
		this.manageTransformation(id, false);
		return this;
	}

	public TransformationMask withoutTransformationSet(String id) {
		this.manageTransformationSet(id, false);
		return this;
	}

	private void manageTransformation(String id, boolean enable) {
		if (id == null)
			return;

		if (enable == this.defaultEnabled)
			this.exceptions.remove(id);
		else
			this.exceptions.add(id);

	}

	private void manageTransformationSet(String id, boolean enable) {

		if (id == null)
			return;

		Collection<TransformationReference> allReferences = transformationDataSource.getAll();
		for (TransformationReference reference : allReferences) {
			String transformationSetID = reference.getTransformationSet() != null ? reference.getTransformationSet()
					.getId() : null;
			if (id.equals(transformationSetID)) {
				this.manageTransformation(reference.getId(), enable);
			}
		}
	}

	public boolean isTransformationEnabled(String id) {
		return this.exceptions.contains(id) ? !defaultEnabled : defaultEnabled;
	}

	// be careful! the call of this method is not reflexive !!! a.merge(b) is
	// not b.merge(a) !!!
	public TransformationMask merge(TransformationMask transformationMask) {

		if (this.transformationDataSource != transformationMask.transformationDataSource)
			return this;

		if (this.defaultEnabled == transformationMask.defaultEnabled) {
			this.exceptions.addAll(transformationMask.exceptions);
		} else {

			if (transformationMask.exceptions.size() == 0) {
				return this;
			}

			for (TransformationReference transformationReference : this.transformationDataSource.getAll()) {
				if (!transformationMask.exceptions.contains(transformationReference.getId())) {
					this.exceptions.add(transformationReference.getId());
				}
			}
		}
		return this;
	}

}
