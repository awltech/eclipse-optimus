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
import net.atos.optimus.m2m.javaxmi.operation.instruction.builder.complex.BlockBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instruction.complex.IComplexInstruction;
import net.atos.optimus.m2m.javaxmi.operation.parameters.Parameter;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.TypeAccess;

/**
 * Models a method : wrapper of MethodDeclaration in modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class Method extends AbstractMethod<MethodDeclaration> {

	/**
	 * Constructor of method
	 * 
	 * @param methodDeclaration
	 *            the method declaration.
	 */
	public Method(MethodDeclaration methodDeclaration) {
		super(methodDeclaration);
	}

	public String getName() {
		return this.getDelegate().getName();
	}

	@Override
	public Method addJavadoc(String documentation, boolean addEmptyLine) {
		super.addJavadoc(documentation, addEmptyLine);
		return this;
	}

	@Override
	public Method addComment(String commentText, boolean prefixOfParent) {
		super.addComment(commentText, prefixOfParent);
		return this;
	}

	@Override
	public Method addAnnotation(String packageName, String annotationName) {
		super.addAnnotation(packageName, annotationName);
		return this;
	}

	@Override
	public Method addParameter(String parameterTypeName, String parameterName) {
		super.addParameter(parameterTypeName, parameterName);
		return this;
	}

	@Override
	public Method addParameters(String... parameterTypeNames) {
		super.addParameters(parameterTypeNames);
		return this;
	}

	@Override
	public Method addParameters(Parameter... parameters) {
		super.addParameters(parameters);
		return this;
	}

	@Override
	public Method removeParameters() {
		super.removeParameters();
		return this;
	}

	/**
	 * Add a list of instructions to the current method
	 * 
	 * @param instructions
	 *            the added instructions list of the method.
	 * @return the method with the instructions list added to its body.
	 */
	public Method addInstructions(IComplexInstruction... instructions) {
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
	 * Add an exceptions list to the current method
	 * 
	 * @param exceptionsNames
	 *            the exceptions list to add to the current method.
	 * @return the method with the exceptions list added.
	 */
	public Method addExceptions(String... exceptionsNames) {
		EList<TypeAccess> exceptionsList = this.getDelegate().getThrownExceptions();
		for (String exceptionName : exceptionsNames) {
			exceptionsList.add(TypeAccessHelper.createExceptionTypeAccess(exceptionName));
		}
		return this;
	}

}
