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

import net.atos.optimus.m2m.javaxmi.operation.accesses.TypeAccessHelper;
import net.atos.optimus.m2m.javaxmi.operation.classes.AbstractClass;
import net.atos.optimus.m2m.javaxmi.operation.fields.Field;
import net.atos.optimus.m2m.javaxmi.operation.instructions.AssignmentInstructionHelper;
import net.atos.optimus.m2m.javaxmi.operation.instructions.builders.complex.BlockBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instructions.complex.IComplexInstruction;
import net.atos.optimus.m2m.javaxmi.operation.modifiers.ModifierBuilder;
import net.atos.optimus.m2m.javaxmi.operation.util.NameGenerator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.AssignmentKind;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.ConstructorDeclaration;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.VisibilityKind;

/**
 * The purpose of such class is to help with the creation of constructors
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class ConstructorHelper {

	/** The build constructor */
	private ConstructorDeclaration buildConstructor;

	/**
	 * Launch the build of a new constructor (public, with no body and no
	 * parameter by default)
	 * 
	 * @param abstractClass
	 *            the abstract class associated to the constructor under
	 *            construction.
	 * 
	 * @return a new constructor helper.
	 */
	public static ConstructorHelper builder(AbstractClass<? extends AbstractTypeDeclaration> abstractClass) {
		return new ConstructorHelper(abstractClass);
	}

	/**
	 * Private constructor : a new constructor (public, with no body and no
	 * parameter by default)
	 * 
	 * @param abstractClass
	 *            the abstract class associated to the constructor under
	 *            construction.
	 */
	private ConstructorHelper(AbstractClass<? extends AbstractTypeDeclaration> abstractClass) {
		AbstractTypeDeclaration internalClass = abstractClass.getDelegate();
		Modifier modifier = ModifierBuilder.builder().setVisibility(VisibilityKind.PUBLIC)
				.setCompilationUnit(internalClass.getOriginalCompilationUnit()).build();
		this.buildConstructor = ConstructorDeclarationBuilder.builder().setModifier(modifier)
				.setName(abstractClass.getName()).setAbstractTypeDeclaration(internalClass)
				.setCompilationUnit(internalClass.getOriginalCompilationUnit()).build();
	}

	/**
	 * Give the build constructor
	 * 
	 * @return the build constructor.
	 */
	public Constructor build() {
		return new Constructor(this.buildConstructor);
	}

	/**
	 * Set the visibility of the constructor under construction
	 * 
	 * @param visibility
	 *            the visibility of the constructor under construction.
	 * @return the helper.
	 */
	public ConstructorHelper setVisibility(VisibilityKind visibility) {
		Modifier modifier = ModifierBuilder.builder().setVisibility(visibility)
				.setCompilationUnit(this.buildConstructor.getOriginalCompilationUnit()).build();
		this.buildConstructor.setModifier(modifier);
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
	 * @return the helper.
	 */
	public ConstructorHelper addParameter(String parameterTypeName, String parameterName) {
		new Constructor(this.buildConstructor).addParameter(parameterTypeName, parameterName);
		return this;
	}

	/**
	 * Add a parameters list (parameters names are generated) to the constructor
	 * under construction
	 * 
	 * @param parameterTypeNames
	 *            the type names list of parameters to add to the constructor
	 *            under construction.
	 * @return the helper.
	 */
	public ConstructorHelper addParameters(String... parameterTypeNames) {
		new Constructor(this.buildConstructor).addParameters(parameterTypeNames);
		return this;
	}

	/**
	 * Add an exceptions list to the constructor under construction
	 * 
	 * @param exceptionsNames
	 *            the exceptions list to add to the constructor under
	 *            construction.
	 * @return the helper.
	 */
	public ConstructorHelper addExceptions(String... exceptionsNames) {
		EList<TypeAccess> exceptionsList = this.buildConstructor.getThrownExceptions();
		for (String exceptionName : exceptionsNames) {
			exceptionsList.add(TypeAccessHelper.createExceptionTypeAccess(exceptionName));
		}
		return this;
	}

	/**
	 * Add a parameter list with generated names in the constructor and set the
	 * fields in the specified list in the constructor body
	 * 
	 * @param fields
	 *            the fields list to set.
	 * @return the helper.
	 */
	public ConstructorHelper addParameterAndSetAssociatedFields(Field... fields) {
		for (Field field : fields) {
			String parameterName = NameGenerator.generateNameWithTypeName(field.getTypeName());
			this.addParameter(field.getTypeName(), parameterName);
			this.addInstructions(AssignmentInstructionHelper.builder().setOperator(AssignmentKind.ASSIGN)
					.setLeftFieldOperand(field.getName()).setRightVariableOperand(parameterName).build());
		}
		return this;
	}

	/**
	 * Add a parameter in the constructor and set the specified field in the
	 * constructor body
	 * 
	 * @param parameterName
	 *            the name of the parameter to add to the constructor under
	 *            construction.
	 * @param field
	 *            the field to set.
	 * @return the helper.
	 */
	public ConstructorHelper addParameterAndSetAssociatedField(String parameterName, Field field) {
		this.addParameter(field.getTypeName(), parameterName);
		return this.addInstructions(AssignmentInstructionHelper.builder().setOperator(AssignmentKind.ASSIGN)
				.setLeftFieldOperand(field.getName()).setRightVariableOperand(parameterName).build());
	}

	/**
	 * Add an instructions list to the constructor under construction
	 * 
	 * @param instructions
	 *            the instructions list to add to the constructor under
	 *            construction.
	 * @return the helper.
	 */
	public ConstructorHelper addInstructions(IComplexInstruction... instructions) {
		Block block = this.buildConstructor.getBody();
		if (block == null) {
			block = BlockBuilder.builder().build();
			this.buildConstructor.setBody(block);
		}
		for (IComplexInstruction instruction : instructions) {
			block.getStatements().add(instruction.getStatement());
		}
		return this;
	}

	/**
	 * Create a constructor with automatic set fields
	 * 
	 * @param abstractClass
	 *            the abstract class where is the created constructor.
	 * @param visibility
	 *            the visibility the created constructor.
	 * @param fields
	 *            the fields list to set with the constructor under
	 *            construction.
	 * @return the created constructor with automatic set fields accordingly to
	 *         the specified parameters.
	 */
	public static Constructor createConstructor(AbstractClass<? extends AbstractTypeDeclaration> abstractClass,
			VisibilityKind visibility, Field... fields) {
		ConstructorHelper helper = ConstructorHelper.builder(abstractClass).setVisibility(visibility);
		for (Field field : fields) {
			helper.addParameterAndSetAssociatedFields(field);
		}
		return helper.build();
	}

}
