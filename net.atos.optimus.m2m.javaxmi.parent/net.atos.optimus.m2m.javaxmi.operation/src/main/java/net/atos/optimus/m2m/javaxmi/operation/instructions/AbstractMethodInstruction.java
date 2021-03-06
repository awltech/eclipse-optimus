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
package net.atos.optimus.m2m.javaxmi.operation.instructions;

import net.atos.optimus.m2m.javaxmi.operation.instructions.builders.ExpressionStatementBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instructions.elementary.ElementaryInstructionHelper;
import net.atos.optimus.m2m.javaxmi.operation.instructions.elementary.IElementaryInstruction;

import org.eclipse.gmt.modisco.java.AbstractMethodInvocation;
import org.eclipse.gmt.modisco.java.ClassInstanceCreation;
import org.eclipse.gmt.modisco.java.MethodInvocation;
import org.eclipse.gmt.modisco.java.SuperConstructorInvocation;
import org.eclipse.gmt.modisco.java.SuperMethodInvocation;

/**
 * Models an abstract method instruction
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class AbstractMethodInstruction extends Instruction {

	/** The abstract method invocation */
	private AbstractMethodInvocation abstractMethodInvocation;

	/**
	 * Constructor of abstract method instruction
	 * 
	 * @param methodInvocation
	 *            the method invocation statement.
	 */
	public AbstractMethodInstruction(MethodInvocation methodInvocation) {
		super(ExpressionStatementBuilder.builder().setExpression(methodInvocation).build());
		this.abstractMethodInvocation = methodInvocation;
	}

	/**
	 * Constructor of abstract super method instruction
	 * 
	 * @param superMethodInvocation
	 *            the super method invocation statement.
	 */
	public AbstractMethodInstruction(SuperMethodInvocation superMethodInvocation) {
		super(ExpressionStatementBuilder.builder().setExpression(superMethodInvocation).build());
		this.abstractMethodInvocation = superMethodInvocation;
	}

	/**
	 * Constructor of abstract super constructor instruction
	 * 
	 * @param superConstructorInvocation
	 *            the super constructor invocation statement.
	 */
	public AbstractMethodInstruction(SuperConstructorInvocation superConstructorInvocation) {
		super(superConstructorInvocation);
		this.abstractMethodInvocation = superConstructorInvocation;
	}

	/**
	 * Constructor of abstract class instance instruction
	 * 
	 * @param classInstanceCreation
	 *            the class instance creation statement.
	 */
	public AbstractMethodInstruction(ClassInstanceCreation classInstanceCreation) {
		super(ExpressionStatementBuilder.builder().setExpression(classInstanceCreation).build());
		this.abstractMethodInvocation = classInstanceCreation;
	}

	@Override
	public AbstractMethodInstruction addJavadoc(String documentation, boolean addEmptyLine) {
		super.addJavadoc(documentation, addEmptyLine);
		return this;
	}

	@Override
	public AbstractMethodInstruction addComment(String commentText, boolean prefixOfParent) {
		super.addComment(commentText, prefixOfParent);
		return this;
	}

	/**
	 * Add the null argument to the abstract method
	 * 
	 * @return the current abstract method with the new added argument.
	 */
	public AbstractMethodInstruction addNullArgument() {
		this.abstractMethodInvocation.getArguments().add(
				ElementaryInstructionHelper.createNullInstruction().getExpression());
		return this;
	}

	/**
	 * Add a boolean argument to the abstract method
	 * 
	 * @param argument
	 *            the boolean argument.
	 * @return the current abstract method with the new added argument.
	 */
	public AbstractMethodInstruction addArgument(boolean argument) {
		this.abstractMethodInvocation.getArguments().add(
				ElementaryInstructionHelper.createInstruction(argument).getExpression());
		return this;
	}

	/**
	 * Add an int argument to the abstract method
	 * 
	 * @param argument
	 *            the int argument.
	 * @return the current abstract method with the new added argument.
	 */
	public AbstractMethodInstruction addArgument(int argument) {
		this.abstractMethodInvocation.getArguments().add(
				ElementaryInstructionHelper.createInstruction(argument).getExpression());
		return this;
	}

	/**
	 * Add a char argument to the abstract method
	 * 
	 * @param argument
	 *            the char argument.
	 * @return the current abstract method with the new added argument.
	 */
	public AbstractMethodInstruction addArgument(char argument) {
		this.abstractMethodInvocation.getArguments().add(
				ElementaryInstructionHelper.createInstruction(argument).getExpression());
		return this;
	}

	/**
	 * Add a String argument to the abstract method
	 * 
	 * @param argument
	 *            the String argument.
	 * @return the current abstract method with the new added argument.
	 */
	public AbstractMethodInstruction addArgument(String argument) {
		this.abstractMethodInvocation.getArguments().add(
				ElementaryInstructionHelper.createInstruction(argument).getExpression());
		return this;
	}

	/**
	 * Add a this argument to the abstract method
	 * 
	 * @return the current abstract method with the new added argument.
	 */
	public AbstractMethodInstruction addThisArgument() {
		this.abstractMethodInvocation.getArguments().add(
				ElementaryInstructionHelper.createThisInstruction().getExpression());
		return this;
	}

	/**
	 * Add a field argument to the abstract method
	 * 
	 * @param fieldName
	 *            the field name argument.
	 * @return the current abstract method with the new added argument.
	 */
	public AbstractMethodInstruction addFieldArgument(String fieldName) {
		this.abstractMethodInvocation.getArguments().add(
				ElementaryInstructionHelper.createFieldInstruction(fieldName).getExpression());
		return this;
	}

	/**
	 * Add a variable argument to the abstract method
	 * 
	 * @param variableName
	 *            the variable name argument.
	 * @return the current abstract method with the new added argument.
	 */
	public AbstractMethodInstruction addVariableArgument(String variableName) {
		this.abstractMethodInvocation.getArguments().add(
				ElementaryInstructionHelper.createVariableInstruction(variableName).getExpression());
		return this;
	}

	/**
	 * Add an instruction argument to the abstract method
	 * 
	 * @param argument
	 *            the instruction argument.
	 * @return the current abstract method with the new added argument.
	 */
	public AbstractMethodInstruction addArgument(IElementaryInstruction argument) {
		this.abstractMethodInvocation.getArguments().add(argument.getExpression());
		return this;
	}

}
