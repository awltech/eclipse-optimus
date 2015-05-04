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
package net.atos.optimus.m2m.engine.ctxinject.api;

/**
 * Defines the scope of the context injection
 * 
 * @author mvanbesien
 * 
 */
public enum ContextElementVisibility {
	/**
	 * For field injection only
	 */
	IN(true, false),

	/**
	 * for field update only
	 */
	OUT(false, true),

	/**
	 * For field injection and update
	 */
	INOUT(true, true);

	/**
	 * If field can be injected (writeable)
	 */
	private boolean injectable;

	/**
	 * If field value can be updated in context (readable)
	 */
	private boolean updatable;

	/*
	 * Private constructor
	 */
	private ContextElementVisibility(boolean injectable, boolean updatable) {
		this.injectable = injectable;
		this.updatable = updatable;
	}

	/**
	 * 
	 * @return true If field can be injected (writeable)
	 */
	public boolean isInjectable() {
		return this.injectable;
	}

	/**
	 * 
	 * @return If field value can be updated in context (readable)
	 */
	public boolean isUpdatable() {
		return this.updatable;
	}
}
