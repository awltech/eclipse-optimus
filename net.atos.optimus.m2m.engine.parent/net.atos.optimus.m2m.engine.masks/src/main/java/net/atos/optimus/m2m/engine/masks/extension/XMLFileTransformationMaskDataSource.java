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
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.XMLConstants;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import net.atos.optimus.m2m.engine.core.masks.TransformationMaskDataSource;
import net.atos.optimus.m2m.engine.core.masks.TransformationMaskReference;
import net.atos.optimus.m2m.engine.masks.Activator;
import net.atos.optimus.m2m.engine.masks.UserTransformationMaskTool;
import net.atos.optimus.m2m.engine.masks.XMLTransformationMaskReference;
import net.atos.optimus.m2m.engine.masks.logging.OptimusM2MMaskMessages;

import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
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
	protected Map<String, XMLTransformationMaskReference> transformationMaskReferences;

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
			validatorXMLTransformationMask = new Validator() {

				@Override
				public ErrorHandler getErrorHandler() {
					return null;
				}

				@Override
				public LSResourceResolver getResourceResolver() {
					return null;
				}

				@Override
				public void reset() {
				}

				@Override
				public void setErrorHandler(ErrorHandler errorHandler) {
				}

				@Override
				public void setResourceResolver(LSResourceResolver resourceResolver) {
				}

				@Override
				public void validate(Source source, Result result) throws SAXException, IOException {
				}

			};
		} else {
			validatorXMLTransformationMask = schemaXSD.newValidator();
		}
	}

	/**
	 * Constructor
	 */
	public XMLFileTransformationMaskDataSource() {
		super(XMLFileTransformationMaskDataSource.DESCRIPTION);
		this.transformationMaskReferences = new HashMap<String, XMLTransformationMaskReference>();
	}

	/**
	 * Load the user transformation masks from XML files
	 */
	protected void loadTransformationUserMasks() {
		File transformationMaskDirectory = new File(UserTransformationMaskTool.TRANSFORMATION_MASK_DIRECTORY);
		if (transformationMaskDirectory.exists()) {

			// Check if XML transformation mask files still exist
			Set<String> transformationToRemove = new HashSet<String>();
			for (String transformationName : this.transformationMaskReferences.keySet()) {
				File transformationMaskFile = this.transformationMaskReferences.get(transformationName)
						.getTransformationMaskFile();
				if (!transformationMaskFile.exists()) {
					OptimusM2MMaskMessages.UM09.log(transformationMaskFile.getPath());
					transformationToRemove.add(transformationName);
				} else {
					Source source = new StreamSource(transformationMaskFile);
					/* Corrected the key if the name has change */
					XMLTransformationMaskReference transformationMaskReference = this.transformationMaskReferences
							.get(transformationName);
					String maskName = transformationMaskReference.getName();
					if (!transformationName.equals(maskName)) {
						transformationToRemove.add(transformationName);
						if (this.transformationMaskReferences.get(maskName) != null) {
							OptimusM2MMaskMessages.UM06.log(maskName);
						} else {
							this.transformationMaskReferences.put(maskName, transformationMaskReference);
						}
					}
					try {
						XMLFileTransformationMaskDataSource.validatorXMLTransformationMask.validate(source);

					} catch (IOException e) {
						transformationToRemove.add(transformationMaskReference.getName());
						OptimusM2MMaskMessages.UM10.log(transformationMaskFile.getPath(), e.getMessage());
					} catch (SAXException e) {
						transformationToRemove.add(transformationMaskReference.getName());
						OptimusM2MMaskMessages.UM11.log(transformationMaskFile.getPath(), e.getMessage());
					}
				}
			}
			for (String transformationName : transformationToRemove) {
				this.transformationMaskReferences.remove(transformationName);
			}

			// Check if new XML transformation mask files exists
			Set<String> existingMasks = new HashSet<String>();
			for (File transformationMaskFile : transformationMaskDirectory.listFiles()) {
				if (transformationMaskFile.getName().endsWith(".xml")) {
					Source source = new StreamSource(transformationMaskFile);
					try {
						XMLFileTransformationMaskDataSource.validatorXMLTransformationMask.validate(source);
						XMLTransformationMaskReference transformationMaskReference = new XMLTransformationMaskReference(
								transformationMaskFile);
						String maskName = transformationMaskReference.getName();
						if (!this.transformationMaskReferences.containsKey(maskName)) {
							OptimusM2MMaskMessages.UM12.log(maskName, transformationMaskFile.getPath());
							this.transformationMaskReferences.put(maskName, transformationMaskReference);
							existingMasks.add(maskName);
						} else {
							if (existingMasks.contains(maskName)) {
								OptimusM2MMaskMessages.UM06.log(maskName);
							} else {
								existingMasks.add(maskName);
							}
						}
					} catch (IOException e) {
						OptimusM2MMaskMessages.UM13.log(transformationMaskFile.getPath(), e.getMessage());
					} catch (SAXException e) {
						OptimusM2MMaskMessages.UM14.log(transformationMaskFile.getPath(), e.getMessage());
					}
				}
			}

		} else {
			this.transformationMaskReferences.clear();
		}
	}

	@Override
	public Collection<TransformationMaskReference> getAllMasks() {
		this.loadTransformationUserMasks();
		List<TransformationMaskReference> transformationMaskReferencesResult = new LinkedList<TransformationMaskReference>();
		for (TransformationMaskReference transformationMaskReference : this.transformationMaskReferences.values()) {
			transformationMaskReferencesResult.add(transformationMaskReference);
		}
		return Collections.unmodifiableCollection(transformationMaskReferencesResult);
	}

}
