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
package net.atos.optimus.m2m.javaxmi.operation.instruction.elementary;

import net.atos.optimus.m2m.javaxmi.operation.instruction.complex.ComplexInstruction;

import org.eclipse.gmt.modisco.java.Expression;

/**
 * General description of an elementary instruction
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public interface IElementaryInstruction {

	public Expression getExpression();

	/**
	 * Add parenthesis to the current instruction
	 * 
	 * @return the instruction with parentheses based on the current
	 *         instruction.
	 */
	public ElementaryInstruction withParentheses();

	/**
	 * Convert the current instruction to a return instruction
	 * 
	 * @return the return instruction based on the current instruction.
	 */
	public ComplexInstruction convertToReturnInstruction();

	/**
	 * Convert the current instruction to a cast instruction
	 * 
	 * @param castName
	 *            the class name or the interface name of the cast instruction.
	 * @return the cast instruction based on the current instruction.
	 */
	public ElementaryInstruction convertToCastInstruction(String castName);

	/**
	 * Convert the current instruction to an instanceof instruction
	 * 
	 * @param typeName
	 *            the type name of the instanceof instruction.
	 * @return the instanceof instruction based on the current instruction.
	 */
	public ElementaryInstruction convertToInstanceOfInstruction(String typeName);

}
