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
package net.atos.optimus.m2m.engine.masks.extension;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import net.atos.optimus.m2m.engine.core.masks.ITransformationMask;
import net.atos.optimus.m2m.engine.core.masks.TransformationMaskDataSource;
import net.atos.optimus.m2m.engine.core.masks.TransformationMaskReference;
import net.atos.optimus.m2m.engine.masks.Activator;
import net.atos.optimus.m2m.engine.masks.UserTransformationMaskTool;
import net.atos.optimus.m2m.engine.masks.XMLTransformationMask;
import net.atos.optimus.m2m.engine.masks.logging.OptimusM2MMaskMessages;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Implementation of User Transformation Masks management singleton
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class XMLFileTransformationMaskDataSource extends TransformationMaskDataSource {

	/** The description of the transformation data source */
	public static final String DESCRIPTION = "Optimus User Transformation Mask";

	/** The map holding the transformation mask preference */
	protected Map<String, TransformationMaskReference> transformationMaskReferences;

	/** The schema validator of transformation mask */
	public static final Validator validatorXMLTransformationMask;

	/** The schema of transformation mask */
	public static final String SCHEMA_TRANSFORMATION_MASK = "schema/TransformationMasks.xsd";

	static {
		SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schemaXSD = null;
		try {
			URL url = new URL("platform:/plugin/" + Activator.PLUGIN_ID + "/"
					+ XMLFileTransformationMaskDataSource.SCHEMA_TRANSFORMATION_MASK);
			SAXSource sourceXSD = new SAXSource(new InputSource(url.openConnection().getInputStream()));
			schemaXSD = factory.newSchema(sourceXSD);
		} catch (IOException e) {
			OptimusM2MMaskMessages.UM07.log(e.getMessage());
		} catch (SAXException e) {
			OptimusM2MMaskMessages.UM08.log(e.getMessage());
		}
		if (schemaXSD == null) {
			validatorXMLTransformationMask = null;
		} else {
			validatorXMLTransformationMask = schemaXSD.newValidator();
		}
	}

	/**
	 * Constructor
	 */
	public XMLFileTransformationMaskDataSource() {
		super(XMLFileTransformationMaskDataSource.DESCRIPTION);
		this.transformationMaskReferences = new HashMap<String, TransformationMaskReference>();
	}

	/**
	 * Load the user transformation masks from XML files
	 */
	protected void loadTransformationUserMasks() {
		File transformationMaskDirectory = new File(UserTransformationMaskTool.TRANSFORMATION_MASK_DIRECTORY);
		if (transformationMaskDirectory.exists()) {

			// Check if XML transformation mask files still exist
			for (String transformationName : this.transformationMaskReferences.keySet()) {
				File transformationMaskFile = UserTransformationMaskTool.giveAssociatedXMLFile(transformationName);
				if (!transformationMaskFile.exists()) {
					OptimusM2MMaskMessages.UM09.log(transformationMaskFile.getPath());
					transformationMaskReferences.remove(transformationName);
				} else {
					Source source = new StreamSource(transformationMaskFile);
					try {
						XMLFileTransformationMaskDataSource.validatorXMLTransformationMask.validate(source);
					} catch (IOException e) {
						transformationMaskReferences.remove(transformationName);
						OptimusM2MMaskMessages.UM10.log(transformationMaskFile.getPath(), e.getMessage());
					} catch (SAXException e) {
						transformationMaskReferences.remove(transformationName);
						OptimusM2MMaskMessages.UM11.log(transformationMaskFile.getPath(), e.getMessage());
					}
				}
			}

			// Check if new XML transformation mask files exists
			for (File transformationMaskFile : transformationMaskDirectory.listFiles()) {
				if(transformationMaskFile.getName().endsWith(".xml")){
					String transformationMaskName = UserTransformationMaskTool.giveAssociatedMaskName(transformationMaskFile);
					if (!transformationMaskReferences.containsKey(transformationMaskName)) {
						Source source = new StreamSource(transformationMaskFile);
						try {
							XMLFileTransformationMaskDataSource.validatorXMLTransformationMask.validate(source);
							ITransformationMask transformationMask = new XMLTransformationMask(transformationMaskFile);
							OptimusM2MMaskMessages.UM12.log(transformationMaskName, transformationMaskFile.getPath());
							TransformationMaskReference transformationMaskReference = new TransformationMaskReference(
									transformationMaskName, "", transformationMask);
							this.transformationMaskReferences.put(transformationMaskName,
									transformationMaskReference);
						} catch (IOException e) {
							OptimusM2MMaskMessages.UM13.log(transformationMaskFile.getPath(), e.getMessage());
						} catch (SAXException e) {
							OptimusM2MMaskMessages.UM14.log(transformationMaskFile.getPath(), e.getMessage());
						}
					}
				}
			}
		} else {
			this.transformationMaskReferences.clear();
		}
	}

	@Override
	public TransformationMaskReference getMaskById(String id) {
		this.loadTransformationUserMasks();
		return this.transformationMaskReferences.get(id);
	}

	@Override
	public Collection<TransformationMaskReference> getAllMasks() {
		this.loadTransformationUserMasks();
		return Collections.unmodifiableCollection(this.transformationMaskReferences.values());
	}

}
