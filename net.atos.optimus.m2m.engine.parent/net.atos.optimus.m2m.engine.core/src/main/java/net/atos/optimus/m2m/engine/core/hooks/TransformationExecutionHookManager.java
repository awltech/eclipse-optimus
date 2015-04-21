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
package net.atos.optimus.m2m.engine.core.hooks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;

public enum TransformationExecutionHookManager {
	INSTANCE;
	
	private final Collection<AbstractTransformationExecutionHook> transformationExecutionHooks;
	
	private TransformationExecutionHookManager() {
		// TODO Implement Retrieval of hooks
		this.transformationExecutionHooks = Collections.unmodifiableList(new ArrayList<AbstractTransformationExecutionHook>());
	}
	
	public void onPreExecute(AbstractTransformation<?> transformation, ITransformationContext context) {
		// TODO
	}
	
	public void onPostExecute(AbstractTransformation<?> transformation, ITransformationContext context) {
		// TODO
	}
}
