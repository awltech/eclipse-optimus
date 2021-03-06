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
package net.atos.optimus.m2m.engine.ctxinject.exceptions;

/**
 * Thrown when an system exception is raised when trying to get the context
 * retriever instance, when using the CustomContextElement annotation
 * 
 * @author mvanbesien
 * 
 */
public class NullInstanceException extends Exception {

	private static final String MESSAGE = "Could not instantiate/get implementation %s for injection to field %s";
	private String name;
	private Class<?> clazz;

	public NullInstanceException(String name, Class<?> clazz) {
		this.name = name;
		this.clazz = clazz;
	}

	public NullInstanceException(String name, Class<?> clazz, Exception cause) {
		super(cause);
		this.name = name;
		this.clazz = clazz;
	}

	@Override
	public String getMessage() {
		return String.format(MESSAGE, this.clazz == null ? null : this.clazz.getName(), this.name);
	}

	private static final long serialVersionUID = 1L;
}
