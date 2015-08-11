package net.atos.optimus.m2m.engine.ui.prefs.dialog.list;

import net.atos.optimus.m2m.engine.core.masks.TransformationMaskReference;

public class SelectedTransformationMask {

	protected TransformationMaskReference transformationMaskReference;
	
	protected boolean isInclusive;
	
	public SelectedTransformationMask(TransformationMaskReference transformationMaskReference, boolean isInclusive) {
		super();
		this.transformationMaskReference = transformationMaskReference;
		this.isInclusive = isInclusive;
	}

	public TransformationMaskReference getTransformationMaskReference() {
		return transformationMaskReference;
	}

	public boolean isInclusive() {
		return isInclusive;
	}

}
