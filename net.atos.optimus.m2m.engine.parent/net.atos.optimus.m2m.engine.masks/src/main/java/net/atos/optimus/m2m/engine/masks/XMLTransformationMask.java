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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
 * Transformation mask linked with an XML file
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class XMLTransformationMask implements IEditableTransformationMask {

	/** The file of the transformation mask filename */
	protected File transformationMaskFilename;

	/**
	 * The date of the last modification of the file associated to the
	 * transformation mask
	 */
	protected long lastModificationDate;

	/** The map holding the enabled/disabled transformations */
	protected Map<String, Boolean> transformationMask;

	/** Constructor
	 * 
	 * @param transformationMaskFilename the file containing the transformation mask.
	 */
	public XMLTransformationMask(File transformationMaskFilename) {
		this.transformationMaskFilename = transformationMaskFilename;
		this.lastModificationDate = -1;
		this.transformationMask = new HashMap<String, Boolean>();
		this.loadUserTransformationMask();
	}

	/**
	 * Load user transformation mask
	 * 
	 */
	protected void loadUserTransformationMask() {
		if (this.transformationMaskFilename.exists()) {
			if (this.lastModificationDate != this.transformationMaskFilename.lastModified()) {
				Source source = new StreamSource(this.transformationMaskFilename);
				try {
					XMLFileTransformationMaskDataSource.validatorXMLTransformationMask.validate(source);
					this.updateTransformationMask();
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
	protected void updateTransformationMask() throws JDOMException, IOException {
		SAXBuilder sxb = new SAXBuilder();
		Document document = sxb.build(this.transformationMaskFilename);
		Element transformationMask = document.getRootElement();
		List<?> transformations = transformationMask.getChildren("transformation");
		Iterator<?> transformationsIt = transformations.iterator();
		while (transformationsIt.hasNext()) {
			Element transformation = (Element) transformationsIt.next();
			boolean enable = Boolean.parseBoolean(transformation.getAttributeValue("enable"));
			String transformationName = transformation.getAttributeValue("name");
			this.transformationMask.put(transformationName, enable);
			if (enable) {
				OptimusM2MMaskMessages.UM17.log(transformationName, this.transformationMaskFilename.getName());
			} else {
				OptimusM2MMaskMessages.UM18.log(transformationName, this.transformationMaskFilename.getName());
			}
		}
		OptimusM2MMaskMessages.UM19.log(this.transformationMaskFilename.getName());
	}

	@Override
	public boolean isTransformationEnabled(String id) {
		this.loadUserTransformationMask();
		return this.transformationMask.containsKey(id) ? this.transformationMask.get(id) : true;
	}
	
	@Override
	public void submitMaskModification(Set<Entry<String, Boolean>> maskModifications){
		for(Entry<String, Boolean> modification : maskModifications){
			this.transformationMask.put(modification.getKey(), modification.getValue());
		}
		UserTransformationMaskTool.createUserTransformationMask(this.transformationMaskFilename, this);
	}

}
