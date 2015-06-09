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

import net.atos.optimus.m2m.javaxmi.operation.modifiers.ModifierBuilder;
import net.atos.optimus.m2m.javaxmi.operation.statements.BlockBuilder;
import net.atos.optimus.m2m.javaxmi.operation.statements.ComplexStatementHelper;
import net.atos.optimus.m2m.javaxmi.operation.variables.VariableHelper;

import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.ConstructorDeclaration;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.Statement;
import org.eclipse.gmt.modisco.java.VisibilityKind;

/**
 * The purpose of such class is to help with the building of constructors
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class ConstructorBuilderHelper {

	/** The build constructor declaration */
	private ConstructorDeclaration buildConstructorDeclaration;

	/**
	 * Give a new constructor builder : by default the constructor is public
	 * with no body and no parameter
	 * 
	 * @param javaClass
	 *            the class where is the constructor under construction.
	 * 
	 * @return a new constructor builder.
	 */
	public static ConstructorBuilderHelper builder(ClassDeclaration javaClass) {
		return new ConstructorBuilderHelper(javaClass);
	}

	/**
	 * Private constructor : by default the constructor is public with no body
	 * and no parameter
	 * 
	 * @param javaClass
	 *            the class where is the constructor under construction.
	 */
	private ConstructorBuilderHelper(ClassDeclaration javaClass) {
		this.buildConstructorDeclaration = ConstructorBuilderHelper.createBasicConstructor(javaClass,
				VisibilityKind.PUBLIC);
	}

	/**
	 * Build a constructor
	 * 
	 * @return the build constructor.
	 */
	public ConstructorDeclaration build() {
		return this.buildConstructorDeclaration;
	}

	/**
	 * Create a constructor with no parameter and no body
	 * 
	 * @param javaClass
	 *            the class where is the created constructor.
	 * @param visibility
	 *            the visibility the created constructor.
	 * @return the created constructor with no parameter and no body accordingly
	 *         to the specified parameters.
	 */
	protected static ConstructorDeclaration createBasicConstructor(ClassDeclaration javaClass, VisibilityKind visibility) {
		Modifier modifier = ModifierBuilder.builder().setVisibility(visibility)
				.setCompilationUnit(javaClass.getOriginalCompilationUnit()).build();
		return ConstructorDeclarationBuilder.builder().setModifier(modifier).setName(javaClass.getName())
				.setAbstractTypeDeclaration(javaClass).setCompilationUnit(javaClass.getOriginalCompilationUnit())
				.build();
	}

	/**
	 * Set the visibility of the constructor under construction
	 * 
	 * @param visibility
	 *            the visibility of the constructor under construction.
	 * @return the builder.
	 */
	public ConstructorBuilderHelper setVisibility(VisibilityKind visibility) {
		Modifier modifier = ModifierBuilder.builder().setVisibility(visibility)
				.setCompilationUnit(this.buildConstructorDeclaration.getOriginalCompilationUnit()).build();
		this.buildConstructorDeclaration.setModifier(modifier);
		return this;
	}

	/**
	 * Add a parameter to the constructor under construction
	 * 
	 * @param parameterTypeName
	 *            the type name of the parameter to add to the constructor under
	 *            construction.
	 * @param parameterName
	 *            the name of the parameter to add to the constructor under
	 *            construction.
	 * @return the builder.
	 */
	public ConstructorBuilderHelper addParameter(String parameterTypeName, String parameterName) {
		SingleVariableDeclaration singleVariableDeclaration = VariableHelper.createVariableDeclaration(
				this.buildConstructorDeclaration, parameterTypeName, parameterName);
		this.buildConstructorDeclaration.getParameters().add(singleVariableDeclaration);
		return this;
	}

	/**
	 * Add a parameters list to the constructor under construction with
	 * generated names
	 * 
	 * @param parameterTypeNames
	 *            the type names list of the parameter to add to the constructor
	 *            under construction.
	 * @return the builder.
	 */
	public ConstructorBuilderHelper addParameters(String... parameterTypeNames) {
		for (String parameterTypeName : parameterTypeNames) {
			String parameterName = ConstructorBuilderHelper.createParameterName(parameterTypeName);
			SingleVariableDeclaration singleVariableDeclaration = VariableHelper.createVariableDeclaration(
					this.buildConstructorDeclaration, parameterTypeName, parameterName);
			this.buildConstructorDeclaration.getParameters().add(singleVariableDeclaration);
		}
		return this;
	}

	/**
	 * Add a statements list to the constructor under construction
	 * 
	 * @param statements
	 *            the statements list to add to the constructor under
	 *            construction.
	 * @return the builder.
	 */
	public ConstructorBuilderHelper addStatements(Statement... statements) {
		Block block = this.buildConstructorDeclaration.getBody();
		if (block == null) {
			block = BlockBuilder.builder().addStatements(statements).build();
			this.buildConstructorDeclaration.setBody(block);
		} else {
			for (Statement statement : statements) {
				block.getStatements().add(statement);
			}
		}
		return this;
	}

	/**
	 * Add a generated parameter in the constructor and set the associated field
	 * in the constructor body
	 * 
	 * @param fieldTypeName
	 *            the type name of the field associated to the parameter.
	 * @param fieldName
	 *            the name of the field associated to the parameter.
	 * @return the builder.
	 */
	public ConstructorBuilderHelper addParameterAndSetAssociatedField(String fieldTypeName, String fieldName) {
		String parameterName = ConstructorBuilderHelper.createParameterName(fieldTypeName);
		this.addParameter(fieldTypeName, parameterName);
		this.addStatements(ComplexStatementHelper.createSetFieldStatement(fieldName, parameterName));
		return this;
	}

	/**
	 * Add a parameter in the constructor and set the associated field in the
	 * constructor body
	 * 
	 * @param parameterName
	 *            the name of the parameter to add to the constructor under
	 *            construction.
	 * @param fieldTypeName
	 *            the type name of the field associated to the parameter.
	 * @param fieldName
	 *            the name of the field associated to the parameter.
	 * @return the builder.
	 */
	public ConstructorBuilderHelper addParameterAndSetAssociatedField(String parameterName, String fieldTypeName,
			String fieldName) {
		this.addParameter(fieldTypeName, parameterName);
		this.addStatements(ComplexStatementHelper.createSetFieldStatement(fieldName, parameterName));
		return this;
	}

	/**
	 * Add a list of statements to a constructor
	 * 
	 * @param constructorDeclaration
	 *            the constructor which we add the statements list.
	 * @param statements
	 *            the added statements list of the constructor.
	 * @return the constructor with the specified body.
	 */
	public static ConstructorDeclaration addConstructorBody(ConstructorDeclaration constructorDeclaration,
			Statement... statements) {
		Block block = constructorDeclaration.getBody();
		if (block == null) {
			block = BlockBuilder.builder().addStatements(statements).build();
			constructorDeclaration.setBody(block);
		} else {
			for (Statement statement : statements) {
				block.getStatements().add(statement);
			}
		}
		return constructorDeclaration;
	}

	/**
	 * Create the parameter name with the parameter type name
	 * 
	 * @param parameterTypeName
	 *            the parameter type name
	 * @return the parameter name associated to the parameter type name.
	 */
	protected static String createParameterName(String parameterTypeName) {
		StringBuilder s = new StringBuilder();
		if (s != null && !"".equals(parameterTypeName.trim())) {
			s.append(parameterTypeName.trim().substring(0, 1).toUpperCase());
			if (parameterTypeName.length() > 1) {
				s.append(parameterTypeName.substring(1));
			}
		}
		return s.toString();
	}

}
