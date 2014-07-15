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
package net.atos.optimus.m2m.engine.core.exceptions;

/**
 * Thrown when an system exception is raised when trying to inject a field into
 * a transformation
 * 
 * @author mvanbesien
 * 
 */
public class FieldUpdateException extends Exception {

	private static final long serialVersionUID = 1L;

	private static final String MESSAGE = "An error occurred while retrieving value into field: %s.";

	private String name;

	public FieldUpdateException(String name, Exception e) {
		super(e);
		this.name = name;
	}

	@Override
	public String getMessage() {
		return String.format(MESSAGE, this.name);
	}

}
