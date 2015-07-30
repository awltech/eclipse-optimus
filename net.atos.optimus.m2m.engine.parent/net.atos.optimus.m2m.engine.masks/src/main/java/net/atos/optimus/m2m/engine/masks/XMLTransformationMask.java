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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import net.atos.optimus.m2m.engine.core.masks.TransformationMaskReference;
import net.atos.optimus.m2m.engine.core.transformations.TransformationDataSourceManager;
import net.atos.optimus.m2m.engine.core.transformations.TransformationReference;
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

public abstract class XMLTransformationMask implements IEditableTransformationMask {

	/** The file of the transformation mask */
	protected File transformationMaskFile;

	/**
	 * The date of the last modification of the file associated to the
	 * transformation mask
	 */
	protected long lastModificationDate;

	/** The set holding the enabled/disabled transformations */
	protected Set<String> transformationMask;

	/** The transformation mask reference associated to the transformation mask */
	protected TransformationMaskReference associatedTransformationMaskReference;

	/**
	 * Constructor
	 * 
	 * @param transformationMaskFilename
	 *            the file containing the transformation mask.
	 * @param associatedTransformationMaskReference
	 *            the transformation mask reference associated to the
	 *            transformation mask. 
	 */
	public XMLTransformationMask(File transformationMaskFilename, XMLTransformationMaskReference associatedTransformationMaskReference) {
		this.transformationMaskFile = transformationMaskFilename;
		this.associatedTransformationMaskReference = associatedTransformationMaskReference;
		this.lastModificationDate = -1;
		this.loadUserTransformationMask();
	}

	/**
	 * Load user transformation mask
	 * 
	 */
	protected void loadUserTransformationMask() {
		if (this.transformationMaskFile.exists()) {
			if (this.lastModificationDate != this.transformationMaskFile.lastModified()) {
				Source source = new StreamSource(this.transformationMaskFile);
				try {
					XMLFileTransformationMaskDataSource.validatorXMLTransformationMask.validate(source);
					this.updateTransformationMask();
					this.lastModificationDate = transformationMaskFile.lastModified();
				} catch (IOException e) {
					OptimusM2MMaskMessages.UM15.message(this.transformationMaskFile.getPath(), e.getMessage());
					UserTransformationMaskTool.createUserTransformationMask(this.transformationMaskFile,
							this.associatedTransformationMaskReference);
				} catch (SAXException e) {
					OptimusM2MMaskMessages.UM16.message(this.transformationMaskFile.getPath(), e.getMessage());
					UserTransformationMaskTool.createUserTransformationMask(this.transformationMaskFile,
							this.associatedTransformationMaskReference);
				} catch (JDOMException e) {
					OptimusM2MMaskMessages.UM15.message(this.transformationMaskFile.getPath(), e.getMessage());
					UserTransformationMaskTool.createUserTransformationMask(this.transformationMaskFile,
							this.associatedTransformationMaskReference);
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
		Document document = sxb.build(this.transformationMaskFile);
		this.transformationMask = new HashSet<String>();

		Element transformationMask = document.getRootElement();
		Iterator<?> transformationsIt = transformationMask.getChildren("transformation").iterator();

		while (transformationsIt.hasNext()) {
			Element transformation = (Element) transformationsIt.next();
			String transformationName = transformation.getAttributeValue("name");
			this.insertTransformationAndAssociatedRequirements(transformationName);
		}
		OptimusM2MMaskMessages.UM19.log(this.transformationMaskFile.getName());
	}

	/**
	 * Insert transformation in the mask and their associated requirements
	 * 
	 * @param transformationName
	 *            the name of the transformation to insert.
	 */
	protected void insertTransformationAndAssociatedRequirements(String transformationName) {
		TransformationReference transformationRef = TransformationDataSourceManager.INSTANCE
				.getById(transformationName);
		if (transformationRef != null && !this.transformationMask.contains(transformationName)) {
			this.transformationMask.add(transformationName);
			this.addTransformationLog(transformationName);
			for (String requirement : this.getRequirementsTransformation(transformationRef)) {
				this.addTransformationLog(requirement);
				this.transformationMask.add(requirement);
			}
		}
	}

	/**
	 * The transformation log used when insert a transformation in the current
	 * mask
	 * 
	 * @param transformationName
	 *            the name of the insert transformation
	 */
	protected abstract void addTransformationLog(String transformationName);

	/**
	 * Give the set of required transformation for a specified transformation
	 * according to the current mask
	 * 
	 * @param reference
	 *            the transformation reference.
	 * @return the set of required transformation for a specified transformation
	 *         according to the current mask.
	 */
	protected abstract Set<String> getRequirementsTransformation(TransformationReference reference);

}
