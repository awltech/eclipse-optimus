package net.atos.optimus.m2m.engine.core.transformations;

import java.util.Collection;

/**
 * Transformation Data Source interface. The purpose of this data source is to
 * provide the runtime the list of available transformations, referenced through
 * the application
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.2
 * 
 */
public abstract class TransformationDataSource {

	/**
	 * Name of the Transformation Data Source
	 */
	private final String name;

	/**
	 * Name of the transformation Data Source
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Creates new Transformation Data Source instance with name
	 * 
	 * @param name
	 */
	public TransformationDataSource(String name) {
		this.name = name;
	}

	/**
	 * Returns the Transformation Reference with the id provided as parameter
	 * 
	 * @param id
	 *            transformation id
	 * @return Transformation Reference
	 */
	public abstract TransformationReference getById(String id);

	/**
	 * Returns the list of all the transformations.
	 * 
	 * @return
	 */
	public abstract Collection<TransformationReference> getAll();

}
