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
package net.atos.optimus.m2m.engine.sdk.tom.properties.transformations;


/** A class holding data used in the transformation generation
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class TransformationGenerationData {
	
	/** The default factory name */
	public static final String FACTORYDEFAULT = "Factory";

	/** The default element */
	public static final String ELEMENTDEFAULT = "org.eclipse.emf.ecore.EObject";
	
	/** The package name */
	private String packageName;

	/** The factory name */
	private String factory;

	/** The transformation name */
	private String trn;

	/** The type name of the transformed element */
	private String type;
	
	public TransformationGenerationData(){
		this.packageName = "";
		this.factory = TransformationGenerationData.FACTORYDEFAULT;
		this.trn = "";
		this.type = TransformationGenerationData.ELEMENTDEFAULT;
	}
	
	public String getPackage() {
		return this.packageName;
	}

	public String getFactory() {
		return this.factory;
	}

	public String getTrn() {
		return this.trn;
	}

	public String getType() {
		return this.type;
	}

	public void setPackage(String packageName) {
		this.packageName = packageName;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public void setTrn(String trn) {
		this.trn = trn;
	}

	public void setType(String type) {
		this.type = type;
	}

}
