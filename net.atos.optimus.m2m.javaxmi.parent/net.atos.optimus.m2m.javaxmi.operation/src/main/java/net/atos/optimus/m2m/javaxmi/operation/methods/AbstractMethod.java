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

import net.atos.optimus.m2m.javaxmi.operation.accesses.TypeAccessHelper;
import net.atos.optimus.m2m.javaxmi.operation.elements.AbstractDeclaration;
import net.atos.optimus.m2m.javaxmi.operation.instructions.builders.complex.BlockBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instructions.complex.IComplexInstruction;
import net.atos.optimus.m2m.javaxmi.operation.parameters.Parameter;
import net.atos.optimus.m2m.javaxmi.operation.parameters.ParameterHelper;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.TypeAccess;

/**
 * Models an abstract method : method or constructor
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class AbstractMethod<M extends AbstractMethodDeclaration> extends AbstractDeclaration<M> {

	/**
	 * Constructor of abstract method
	 * 
	 * @param abstractMethodDeclaration
	 *            the abstract method declaration.
	 */
	public AbstractMethod(M abstractMethodDeclaration) {
		super(abstractMethodDeclaration);
	}

	public CompilationUnit getCompilationUnit() {
		return this.getDelegate().getOriginalCompilationUnit();
	}

	@Override
	public AbstractMethod<M> addJavadoc(String documentation, boolean addEmptyLine) {
		super.addJavadoc(documentation, addEmptyLine);
		return this;
	}

	@Override
	public AbstractMethod<M> addComment(String commentText, boolean prefixOfParent) {
		super.addComment(commentText, prefixOfParent);
		return this;
	}

	@Override
	public AbstractMethod<M> addAnnotation(String packageName, String annotationName) {
		super.addAnnotation(packageName, annotationName);
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
	public AbstractMethod<M> addParameter(String parameterTypeName, String parameterName) {
		return this.addParameters(ParameterHelper.builder(parameterTypeName).setName(parameterName).build());
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
	public AbstractMethod<M> addParameters(String... parameterTypeNames) {
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
	public AbstractMethod<M> addParameters(Parameter... parameters) {
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

	/**
	 * Add a list of instructions to the current abstract method
	 * 
	 * @param instructions
	 *            the added instructions list of the constructor.
	 * @return the constructor with the instructions list added to its body.
	 */
	public AbstractMethod<M> addInstructions(IComplexInstruction... instructions) {
		Block block = this.getDelegate().getBody();
		if (block == null) {
			block = BlockBuilder.builder().build();
			this.getDelegate().setBody(block);
		}
		for (IComplexInstruction instruction : instructions) {
			block.getStatements().add(instruction.getStatement());
		}
		return this;
	}

	/**
	 * Add an exceptions list to the current abstract method
	 * 
	 * @param exceptionsNames
	 *            the exceptions list to add to the current abstract method.
	 * @return the abstract method with the exceptions list added.
	 */
	public AbstractMethod<M> addExceptions(String... exceptionsNames) {
		EList<TypeAccess> exceptionsList = this.getDelegate().getThrownExceptions();
		for (String exceptionName : exceptionsNames) {
			exceptionsList.add(TypeAccessHelper.createExceptionTypeAccess(exceptionName));
		}
		return this;
	}

	/**
	 * Remove parameters of the current abstract method
	 * 
	 * @return the current abstract method.
	 */
	public AbstractMethod<M> removeParameters() {
		this.getDelegate().getParameters().clear();
		return this;
	}

}
