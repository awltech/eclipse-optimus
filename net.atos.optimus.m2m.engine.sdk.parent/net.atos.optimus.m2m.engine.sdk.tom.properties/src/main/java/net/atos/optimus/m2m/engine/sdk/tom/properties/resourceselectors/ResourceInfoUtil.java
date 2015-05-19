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
package net.atos.optimus.m2m.engine.sdk.tom.properties.resourceselectors;

import org.eclipselabs.resourceselector.core.resources.ResourceInfo;

/**
 * Util class for resource info processing
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 *
 */

public class ResourceInfoUtil {

	/**
	 * Extract the full qualified name of the resource associated to the
	 * resource info
	 * 
	 * @param resourceInfo
	 *            the resource info.
	 * 
	 * @return the full qualified name of the resource associated to the
	 *         resource info.
	 */
	public static String extractFullQualifiedName(ResourceInfo resourceInfo) {
		StringBuilder stringBuilder = new StringBuilder();
		if(!"".equals(resourceInfo.getPackageName())){
			stringBuilder.append(resourceInfo.getPackageName());
			stringBuilder.append('.');
		}
		stringBuilder.append(resourceInfo.getElementName());
		return stringBuilder.toString();
	}

}
