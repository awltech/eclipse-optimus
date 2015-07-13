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
package net.atos.optimus.m2m.javaxmi.operation.constructors;

import net.atos.optimus.m2m.javaxmi.operation.instructions.complex.IComplexInstruction;
import net.atos.optimus.m2m.javaxmi.operation.methods.AbstractMethod;
import net.atos.optimus.m2m.javaxmi.operation.parameters.Parameter;

import org.eclipse.gmt.modisco.java.ConstructorDeclaration;

/**
 * Models a constructor : wrapper of ConstructorDeclaration in modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class Constructor extends AbstractMethod<ConstructorDeclaration> {

	/**
	 * Constructor of constructor
	 * 
	 * @param constructorDeclaration
	 *            the constructor declaration.
	 */
	public Constructor(ConstructorDeclaration constructorDeclaration) {
		super(constructorDeclaration);
	}

	@Override
	public Constructor addJavadoc(String documentation, boolean addEmptyLine) {
		super.addJavadoc(documentation, addEmptyLine);
		return this;
	}

	@Override
	public Constructor addComment(String commentText, boolean prefixOfParent) {
		super.addComment(commentText, prefixOfParent);
		return this;
	}

	@Override
	public Constructor addAnnotation(String packageName, String annotationName) {
		super.addAnnotation(packageName, annotationName);
		return this;
	}

	@Override
	public Constructor addParameter(String parameterTypeName, String parameterName) {
		super.addParameter(parameterTypeName, parameterName);
		return this;
	}

	@Override
	public Constructor addParameters(String... parameterTypeNames) {
		super.addParameters(parameterTypeNames);
		return this;
	}

	@Override
	public Constructor addParameters(Parameter... parameters) {
		super.addParameters(parameters);
		return this;
	}

	@Override
	public Constructor removeParameters() {
		super.removeParameters();
		return this;
	}

	@Override
	public Constructor addInstructions(IComplexInstruction... instructions) {
		super.addInstructions(instructions);
		return this;
	}

	@Override
	public Constructor addExceptions(String... exceptionsNames) {
		super.addExceptions(exceptionsNames);
		return this;
	}

}
