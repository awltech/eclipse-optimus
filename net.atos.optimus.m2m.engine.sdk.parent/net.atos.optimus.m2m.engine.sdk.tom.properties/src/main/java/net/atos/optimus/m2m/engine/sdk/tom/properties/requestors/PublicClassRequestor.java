package net.atos.optimus.m2m.engine.sdk.tom.properties.requestors;

import net.atos.optimus.m2m.engine.sdk.tom.properties.resourceselectors.ClassResourceProcessor;

import org.eclipse.jdt.core.search.TypeNameRequestor;
import org.eclipselabs.resourceselector.processor.java.JavaTypeInfo;
import org.eclipselabs.resourceselector.processor.java.JavaTypeInfo.JavaTypeVisibility;

/**
 * A requestor dedicated to searching all public classes in a search engine
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 *
 */

public class PublicClassRequestor extends TypeNameRequestor {

	/** The resource processor exploiting the requestor */
	protected ClassResourceProcessor resourceProcessor;

	/**
	 * Constructor
	 * 
	 * @param resourceProcessor
	 *            the resource processor exploiting the requestor.
	 */
	public PublicClassRequestor(ClassResourceProcessor resourceProcessor) {
		this.resourceProcessor = resourceProcessor;
	}

	@Override
	public void acceptType(int modifiers, char[] packageName, char[] simpleTypeName, char[][] enclosingTypeNames,
			String path) {

		/* Package and type name creation */
		String packageNameAsString = new String(packageName);
		String simpleTypeNameAsString = new String(simpleTypeName);

		/* Associated resource creation */
		JavaTypeInfo javaTypeInfo = new JavaTypeInfo(simpleTypeNameAsString, packageNameAsString, path, null, modifiers);

		/* Acceptance test */
		if (this.isValidInfo(javaTypeInfo)) {
			this.resourceProcessor.addResult(javaTypeInfo);
		}
	}

	/**
	 * Test if a resource info must be adding to the resource selector
	 * 
	 * @param javaTypeInfo
	 *            the resource info under test.
	 * 
	 * @return true if the specified resource info must be adding to the
	 *         resource selector
	 */
	protected boolean isValidInfo(JavaTypeInfo javaTypeInfo) {
		return !javaTypeInfo.isInnerElement() && javaTypeInfo.getTypeVisibility().equals(JavaTypeVisibility.PUBLIC);
	}

}
