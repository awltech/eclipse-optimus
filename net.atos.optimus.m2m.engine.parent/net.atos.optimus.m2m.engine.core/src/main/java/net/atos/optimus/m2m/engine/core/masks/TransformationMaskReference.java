package net.atos.optimus.m2m.engine.core.masks;

/**
 * This class is mainly a wrapper. It is used to name the transformation masks,
 * for user reusability
 * 
 * @author mvanbesien
 * @since 1.1
 * 
 */
public class TransformationMaskReference {

	/**
	 * Mask Name
	 */
	private String name;

	/**
	 * Mask implementation
	 */
	private ITransformationMask implementation;

	/**
	 * Creates new Transformation Mask Reference
	 * 
	 * @param name
	 * @param implementation
	 */
	public TransformationMaskReference(String name, ITransformationMask implementation) {
		this.name = name;
		this.implementation = implementation;
	}

	/**
	 * @return transformation mask implementation
	 */
	public ITransformationMask getImplementation() {
		return implementation;
	}

	/**
	 * @return transformation mask name
	 */
	public String getName() {
		return name;
	}

}
