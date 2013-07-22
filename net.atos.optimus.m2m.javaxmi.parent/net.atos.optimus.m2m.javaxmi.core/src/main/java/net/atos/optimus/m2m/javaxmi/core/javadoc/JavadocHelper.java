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
package net.atos.optimus.m2m.javaxmi.core.javadoc;

import java.io.PrintWriter;

import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.BodyDeclaration;
import org.eclipse.gmt.modisco.java.Javadoc;
import org.eclipse.gmt.modisco.java.TagElement;
import org.eclipse.gmt.modisco.java.TextElement;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * The purpose of such class is to help with the creation of javadoc on an
 * element.
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @version 1.0
 * @since 1.0
 */
public class JavadocHelper {

	/**
	 * Adds a {@link Javadoc} element to a {@link BodyDeclaration}. Javadoc's
	 * content is provided as a {@link String}, and it's possible to provide
	 * multiple lines by using the
	 * 
	 * <pre>
	 * \n
	 * </pre>
	 * 
	 * separator in the String. This feature lets you provide the String in a
	 * standard Java way.
	 * 
	 * <i>Tip: The {@link String} is parsed using the
	 * 
	 * <pre>
	 * \n
	 * </pre>
	 * 
	 * separator so you can easily use something like a {@link PrintWriter} to
	 * create the javadoc content. </i>
	 * 
	 * @param bodyDeclaration
	 *            the element to add the created javadoc on
	 * @param documentation
	 *            the javadoc's content
	 * @return the javadoc applied to the provided {@link BodyDeclaration}
	 */
	public static Javadoc addJavadoc(ASTNode bodyDeclaration, String documentation) {
		return addJavadoc(bodyDeclaration, documentation, true);
	}
	
	public static Javadoc addJavadoc(ASTNode bodyDeclaration, String documentation, boolean addEmptyLine) {
		Javadoc javadoc = JavaFactory.eINSTANCE.createJavadoc();
		
		if(documentation != null) {
			for(String documentationChunk : documentation.split("\\n")){
				TagElement tagElement = JavaFactory.eINSTANCE.createTagElement();
				tagElement.getFragments().add(createTextElement(documentationChunk));
				if(addEmptyLine) {
					tagElement.getFragments().add(createTextElement(""));
				}
				javadoc.getTags().add(tagElement);
			}
		}
		javadoc.setPrefixOfParent(true);
		
		if(bodyDeclaration != null) {
			bodyDeclaration.getComments().add(javadoc);
		}
		return javadoc;
		
	}

	/**
	 * This helper method allows to create a {@link TextElement} from a
	 * {@link String}
	 * 
	 * @param value
	 *            the String to use in the {@link TextElement}
	 * @return a {@link TextElement} which contains the provided {@link String}
	 */
	protected static TextElement createTextElement(String value) {
		TextElement textElement = JavaFactory.eINSTANCE.createTextElement();
		textElement.setText(value);
		return textElement;
	}

}
