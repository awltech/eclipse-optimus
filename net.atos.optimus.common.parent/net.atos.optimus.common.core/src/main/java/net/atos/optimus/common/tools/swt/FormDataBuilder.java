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
package net.atos.optimus.common.tools.swt;

import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Control;

/**
 * This builder is able to create, populate and link a form data to a SWT
 * Control object.
 * 
 * @see FormAttachment
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 * 
 */
public class FormDataBuilder {

	/*
	 * Internal FormData value
	 */
	private final FormData data;

	/*
	 * Internal value for default offset, value in px
	 */
	private int defaultOffset;

	/**
	 * Updates the default offset value, saved internally
	 * 
	 * @param defaultOffset
	 *            new default offset value in px
	 * @return this
	 */
	public FormDataBuilder setDefaultOffset(final int defaultOffset) {
		this.defaultOffset = defaultOffset;
		return this;
	}

	/**
	 * Creates new FormData builder instance with default offset value set to 5
	 * px
	 */
	public FormDataBuilder() {
		this.data = new FormData();
		this.defaultOffset = 5;
	}

	/**
	 * Creates a new FormData builder instance, with default offset value passed
	 * as parameter
	 * 
	 * @param defaultOffset
	 *            custom default offset value, in px
	 */
	public FormDataBuilder(final int defaultOffset) {
		this.data = new FormData();
		this.defaultOffset = defaultOffset;
	}

	/**
	 * Specifies the left side attachment of the control.
	 * 
	 * @param numerator
	 *            the percentage of the position, in the parent control
	 * @param offset
	 *            the offset of the side from the position
	 * @return this
	 */
	public FormDataBuilder left(final int numerator, final int offset) {
		this.data.left = new FormAttachment(numerator, offset);
		return this;
	}

	/**
	 * Specifies the left side attachment of the control.
	 * 
	 * @param control
	 *            the control the side is attached to
	 * @param offset
	 *            the offset of the side from the position
	 * @return this
	 */
	public FormDataBuilder left(final Control control, final int offset) {
		this.data.left = new FormAttachment(control, offset);
		return this;
	}

	/**
	 * Specifies the left side attachment of the control. Spaced with default
	 * offset
	 * 
	 * @param numerator
	 *            the percentage of the position, in the parent control
	 * @return this
	 */
	public FormDataBuilder left(final int numerator) {
		return this.left(numerator, this.defaultOffset);
	}

	/**
	 * Specifies the left side attachment of the control. Spaced with default
	 * offset
	 * 
	 * @param control
	 *            the control the side is attached to
	 * @return this
	 */
	public FormDataBuilder left(final Control control) {
		return this.left(control, this.defaultOffset);
	}

	/**
	 * Specifies the left side attachment of the control. Since no parameter is
	 * proposed, the control will be attached to the left side of the parent
	 * control, with default offset
	 * 
	 * @return this
	 */
	public FormDataBuilder left() {
		return this.left(0, this.defaultOffset);
	}

	/**
	 * Specifies the right side attachment of the control.
	 * 
	 * @param control
	 *            the control the side is attached to
	 * @param offset
	 *            the offset of the side from the position
	 * @return this
	 */
	public FormDataBuilder right(final int numerator, final int offset) {
		this.data.right = new FormAttachment(numerator, offset);
		return this;
	}

	/**
	 * Specifies the right side attachment of the control.
	 * 
	 * @param control
	 *            the control the side is attached to
	 * @param offset
	 *            the offset of the side from the position
	 * @return this
	 */
	public FormDataBuilder right(final Control control, final int offset) {
		this.data.right = new FormAttachment(control, offset);
		return this;
	}

	/**
	 * Specifies the right side attachment of the control. Spaced with default
	 * offset
	 * 
	 * @param numerator
	 *            the percentage of the position, in the parent control
	 * @return this
	 */
	public FormDataBuilder right(final int numerator) {
		return this.right(numerator, -this.defaultOffset);
	}

	/**
	 * Specifies the right side attachment of the control. Spaced with default
	 * offset
	 * 
	 * @param control
	 *            the control the side is attached to
	 * @return this
	 */
	public FormDataBuilder right(final Control control) {
		return this.right(control, -this.defaultOffset);
	}

	/**
	 * Specifies the right side attachment of the control. Since no parameter is
	 * proposed, the control will be attached to the right side of the parent
	 * control, with default offset
	 * 
	 * @return this
	 */
	public FormDataBuilder right() {
		return this.right(100, -this.defaultOffset);
	}

	/**
	 * Specifies the top side attachment of the control.
	 * 
	 * @param control
	 *            the control the side is attached to
	 * @param offset
	 *            the offset of the side from the position
	 * @return this
	 */
	public FormDataBuilder top(final int numerator, final int offset) {
		this.data.top = new FormAttachment(numerator, offset);
		return this;
	}

	/**
	 * Specifies the top side attachment of the control.
	 * 
	 * @param control
	 *            the control the side is attached to
	 * @param offset
	 *            the offset of the side from the position
	 * @return this
	 */
	public FormDataBuilder top(final Control control, final int offset) {
		this.data.top = new FormAttachment(control, offset);
		return this;
	}

	/**
	 * Specifies the top side attachment of the control. Spaced with default
	 * offset
	 * 
	 * @param numerator
	 *            the percentage of the position, in the parent control
	 * @return this
	 */
	public FormDataBuilder top(final int numerator) {
		return this.top(numerator, this.defaultOffset);
	}

	/**
	 * Specifies the top side attachment of the control. Spaced with default
	 * offset
	 * 
	 * @param control
	 *            the control the side is attached to
	 * @return this
	 */
	public FormDataBuilder top(final Control control) {
		return this.top(control, this.defaultOffset);
	}

	/**
	 * Specifies the top side attachment of the control. Since no parameter is
	 * proposed, the control will be attached to the top side of the parent
	 * control, with default offset
	 * 
	 * @return this
	 */
	public FormDataBuilder top() {
		return this.top(0, this.defaultOffset);
	}

	/**
	 * Specifies the bottom side attachment of the control.
	 * 
	 * @param control
	 *            the control the side is attached to
	 * @param offset
	 *            the offset of the side from the position
	 * @return this
	 */
	public FormDataBuilder bottom(final int numerator, final int offset) {
		this.data.bottom = new FormAttachment(numerator, offset);
		return this;
	}

	/**
	 * Specifies the bottom side attachment of the control.
	 * 
	 * @param control
	 *            the control the side is attached to
	 * @param offset
	 *            the offset of the side from the position
	 * @return this
	 */
	public FormDataBuilder bottom(final Control control, final int offset) {
		this.data.bottom = new FormAttachment(control, offset);
		return this;
	}

	/**
	 * Specifies the bottom side attachment of the control. Spaced with default
	 * offset
	 * 
	 * @param numerator
	 *            the percentage of the position, in the parent control
	 * @return this
	 */
	public FormDataBuilder bottom(final int numerator) {
		return this.bottom(numerator, -this.defaultOffset);
	}

	/**
	 * Specifies the bottom side attachment of the control. Spaced with default
	 * offset
	 * 
	 * @param control
	 *            the control the side is attached to
	 * @return this
	 */
	public FormDataBuilder bottom(final Control control) {
		return this.bottom(control, -this.defaultOffset);
	}

	/**
	 * Specifies the bottom side attachment of the control. Since no parameter
	 * is proposed, the control will be attached to the bottom side of the
	 * parent control, with default offset
	 * 
	 * @return this
	 */
	public FormDataBuilder bottom() {
		return this.bottom(100, -this.defaultOffset);
	}

	/**
	 * Specifies the preferred width in pixels
	 * 
	 * @param width
	 * @return this
	 */
	public FormDataBuilder width(final int width) {
		this.data.width = width;
		return this;
	}

	/**
	 * Specifies the preferred height in pixels
	 * 
	 * @param height
	 * @return this
	 */
	public FormDataBuilder height(final int height) {
		this.data.height = height;
		return this;
	}

	/**
	 * Specifies left and right attachments, with specified offset
	 * 
	 * @param offset
	 *            the offset of the sides from the position
	 * @return this
	 */
	public FormDataBuilder horizontal(final int offset) {
		return this.left(offset).right(offset);
	}

	/**
	 * Specifies left and right attachments, with default offset
	 * 
	 * @return this
	 */
	public FormDataBuilder horizontal() {
		return this.left().right();
	}

	/**
	 * Specifies top and bottom attachments, with specified offset
	 * 
	 * @param offset
	 *            the offset of the sides from the position
	 * @return this
	 */
	public FormDataBuilder vertical(final int offset) {
		return this.top(offset).bottom(offset);
	}

	/**
	 * Specifies top and bottom attachments, with default offset
	 * 
	 * @return this
	 */
	public FormDataBuilder vertical() {
		return this.top().bottom();
	}

	/**
	 * Specifies all attachments (top, left, bottom & right), with specified
	 * offset
	 * 
	 * @param offset
	 *            the offset of the sides from the position
	 * @return this
	 */
	public FormDataBuilder fill(final int offset) {
		return this.horizontal(offset).vertical(offset);
	}

	/**
	 * Specifies all attachments (top, left, bottom & right), with default
	 * offset
	 * 
	 * @return this
	 */
	public FormDataBuilder fill() {
		return this.horizontal().vertical();
	}

	/**
	 * Applies the defined layout data to the control passed as parameter.
	 * 
	 * @param control
	 */
	public void apply(final Control control) {
		control.setLayoutData(this.data);
	}

}
