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
