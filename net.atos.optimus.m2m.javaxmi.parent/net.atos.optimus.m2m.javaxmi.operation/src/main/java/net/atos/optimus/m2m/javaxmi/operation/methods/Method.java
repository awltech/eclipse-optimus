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
import net.atos.optimus.m2m.javaxmi.operation.instruction.Instruction;
import net.atos.optimus.m2m.javaxmi.operation.instruction.builder.block.BlockBuilder;

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

public class Method extends AbstractMethod {

	/**
	 * Constructor of method
	 * 
	 * @param methodDeclaration
	 *            the method declaration.
	 */
	public Method(MethodDeclaration methodDeclaration) {
		super(methodDeclaration);
	}

	public MethodDeclaration getMethodDeclaration() {
		return (MethodDeclaration) this.getAbstractMethodDeclaration();
	}

	public String getName() {
		return this.getAbstractMethodDeclaration().getName();
	}

	/**
	 * Add a list of instructions to the current method
	 * 
	 * @param instructions
	 *            the added instructions list of the method.
	 * @return the method with the instructions list added to its body.
	 */
	public Method addInstructions(Instruction... instructions) {
		Block block = this.getMethodDeclaration().getBody();
		if (block == null) {
			block = BlockBuilder.builder().build();
			this.getMethodDeclaration().setBody(block);
		}
		for (Instruction instruction : instructions) {
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
		EList<TypeAccess> exceptionsList = this.getAbstractMethodDeclaration().getThrownExceptions();
		for (String exceptionName : exceptionsNames) {
			exceptionsList.add(TypeAccessHelper.createExceptionTypeAccess(exceptionName));
		}
		return this;
	}

}
