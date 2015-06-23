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
package net.atos.optimus.m2m.javaxmi.operation.javadoc;

import org.eclipse.gmt.modisco.java.TextElement;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create text element of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class TextElementBuilder {

	/** The build text element */
	private TextElement buildTextElement;

	/**
	 * Give a text element builder
	 * 
	 * @return a new text element builder.
	 */
	public static TextElementBuilder builder() {
		return new TextElementBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private TextElementBuilder() {
		this.buildTextElement = JavaFactory.eINSTANCE.createTextElement();
	}

	/**
	 * Build a text element of modisco model
	 * 
	 * @return a new text element of modisco model.
	 */
	public TextElement build() {
		return this.buildTextElement;
	}

	/**
	 * Set the text of the text element under construction
	 * 
	 * @param text
	 *            the text of the text element under construction.
	 * @return the builder.
	 */
	public TextElementBuilder setText(String text) {
		this.buildTextElement.setText(text);
		return this;
	}

}
