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
package net.atos.optimus.m2m.javaxmi.operation.methods;

import net.atos.optimus.m2m.javaxmi.operation.annotations.JavaAnnotation;
import net.atos.optimus.m2m.javaxmi.operation.element.AbstractDeclaration;
import net.atos.optimus.m2m.javaxmi.operation.parameters.Parameter;
import net.atos.optimus.m2m.javaxmi.operation.parameters.ParameterHelper;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;

/**
 * Models an abstract method : method or constructor
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class AbstractMethod<S extends AbstractMethodDeclaration> extends AbstractDeclaration<S> {

	/**
	 * Constructor of abstract method
	 * 
	 * @param abstractMethodDeclaration
	 *            the abstract method declaration.
	 */
	public AbstractMethod(S abstractMethodDeclaration) {
		super(abstractMethodDeclaration);
	}

	public CompilationUnit getCompilationUnit() {
		return this.getDelegate().getOriginalCompilationUnit();
	}

	public EList<SingleVariableDeclaration> getParameters() {
		return this.getDelegate().getParameters();
	}

	@Override
	public AbstractMethod<S> addJavadoc(String documentation, boolean addEmptyLine) {
		super.addJavadoc(documentation, addEmptyLine);
		return this;
	}

	@Override
	public AbstractMethod<S> addComment(String commentText, boolean prefixOfParent) {
		super.addComment(commentText, prefixOfParent);
		return this;
	}

	@Override
	public AbstractMethod<S> addAnnotation(String packageName, String annotationName) {
		super.addAnnotation(packageName, annotationName);
		return this;
	}

	@Override
	public AbstractMethod<S> addAnnotations(JavaAnnotation... annotations) {
		super.addAnnotations(annotations);
		return this;
	}

	/**
	 * Add a parameter to the current abstract method
	 * 
	 * @param parameterTypeName
	 *            the type name of the parameter to add to the current abstract
	 *            method.
	 * @param parameterName
	 *            the name of the parameter to add to the current abstract
	 *            method.
	 * @return the current abstract method.
	 */
	public AbstractMethod<S> addParameter(String parameterTypeName, String parameterName) {
		Parameter parameter = ParameterHelper.builder(parameterTypeName).setName(parameterName).build();
		return this.addParameters(parameter);
	}

	/**
	 * Add a parameters list (parameters names are generated) to the current
	 * abstract method
	 * 
	 * @param parameterTypeNames
	 *            the type names list of the parameter to add to the current
	 *            abstract method.
	 * @return the current abstract method.
	 */
	public AbstractMethod<S> addParameters(String... parameterTypeNames) {
		for (String parameterTypeName : parameterTypeNames) {
			Parameter parameter = ParameterHelper.builder(parameterTypeName).build();
			this.addParameters(parameter);
		}
		return this;
	}

	/**
	 * Add a parameters list to the current abstract method
	 * 
	 * @param parameters
	 *            the parameters list to add to the current abstract method.
	 * @return the current abstract method.
	 */
	public AbstractMethod<S> addParameters(Parameter... parameters) {
		AbstractMethodDeclaration abstractMethodDeclaration = this.getDelegate();
		EList<SingleVariableDeclaration> parametersList = abstractMethodDeclaration.getParameters();
		for (Parameter parameter : parameters) {
			SingleVariableDeclaration param = parameter.getDelegate();
			param.setOriginalCompilationUnit(abstractMethodDeclaration.getOriginalCompilationUnit());
			param.setMethodDeclaration(abstractMethodDeclaration);
			parametersList.add(parameter.getDelegate());
		}
		return this;
	}

}
