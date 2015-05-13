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
package net.atos.optimus.m2m.engine.masks;

import java.io.File;
import java.io.IOException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import net.atos.optimus.m2m.engine.masks.extension.XMLFileTransformationMaskDataSource;
import net.atos.optimus.m2m.engine.masks.logging.OptimusM2MMaskMessages;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.SAXException;

/**
 * Transformation mask reference linked with an XML file
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class XMLTransformationMaskReference extends EditableTransformationMaskReference {

	/** The file of the transformation mask filename */
	protected File transformationMaskFilename;

	/**
	 * The date of the last modification of the file associated to the
	 * transformation mask
	 */
	protected long lastModificationDate;

	/**
	 * Create a new transformation mask reference
	 * 
	 * @param name
	 *            the mask name.
	 * @param description
	 *            the mask description.
	 * @param transformationMaskFilename
	 *            the file containing the transformation mask.
	 */
	public XMLTransformationMaskReference(File transformationMaskFilename) {
		super(UserTransformationMaskTool.giveAssociatedMaskName(transformationMaskFilename), "",
				new XMLTransformationMask(transformationMaskFilename));
		this.transformationMaskFilename = transformationMaskFilename;
		this.lastModificationDate = -1;
		((XMLTransformationMask) this.implementation).setAssociatedTransformationMaskReference(this);
		this.loadUserTransformationMaskReference();
	}

	/**
	 * Load user transformation mask reference
	 * 
	 */
	protected void loadUserTransformationMaskReference() {
		if (this.transformationMaskFilename.exists()) {
			if (this.lastModificationDate != this.transformationMaskFilename.lastModified()) {
				Source source = new StreamSource(this.transformationMaskFilename);
				try {
					XMLFileTransformationMaskDataSource.validatorXMLTransformationMask.validate(source);
					this.updateTransformationMaskReference();
					this.lastModificationDate = transformationMaskFilename.lastModified();
				} catch (IOException e) {
					OptimusM2MMaskMessages.UM15.message(this.transformationMaskFilename.getPath(), e.getMessage());
					UserTransformationMaskTool.createUserTransformationMask(this.transformationMaskFilename, this);
				} catch (SAXException e) {
					OptimusM2MMaskMessages.UM16.message(this.transformationMaskFilename.getPath(), e.getMessage());
					UserTransformationMaskTool.createUserTransformationMask(this.transformationMaskFilename, this);
				} catch (JDOMException e) {
					OptimusM2MMaskMessages.UM15.message(this.transformationMaskFilename.getPath(), e.getMessage());
					UserTransformationMaskTool.createUserTransformationMask(this.transformationMaskFilename, this);
				}
			}
		}
	}

	/**
	 * Update the transformations mask according to the XML file
	 * 
	 */
	protected void updateTransformationMaskReference() throws JDOMException, IOException {
		SAXBuilder sxb = new SAXBuilder();
		Document document = sxb.build(this.transformationMaskFilename);
		Element transformationMask = document.getRootElement();
		Element descriptionElement = transformationMask.getChild("description");
		this.setDescription(descriptionElement.getValue());
		OptimusM2MMaskMessages.UM19.log(this.transformationMaskFilename.getName());
	}

	@Override
	public String getDescription() {
		this.loadUserTransformationMaskReference();
		return super.getDescription();
	}

}
