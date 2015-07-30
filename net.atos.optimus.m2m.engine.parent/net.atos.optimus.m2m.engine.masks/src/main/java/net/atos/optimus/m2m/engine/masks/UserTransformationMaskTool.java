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
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import net.atos.optimus.m2m.engine.core.masks.ITransformationMask;
import net.atos.optimus.m2m.engine.core.masks.TransformationMaskReference;
import net.atos.optimus.m2m.engine.core.transformations.TransformationDataSource;
import net.atos.optimus.m2m.engine.core.transformations.TransformationDataSourceManager;
import net.atos.optimus.m2m.engine.core.transformations.TransformationReference;
import net.atos.optimus.m2m.engine.masks.logging.OptimusM2MMaskMessages;

import org.eclipse.core.resources.ResourcesPlugin;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * Tool for the creation of user transformation mask based on XML persistent
 * files
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class UserTransformationMaskTool {

	public static final String TRANSFORMATION_MASK_DIRECTORY = ResourcesPlugin.getWorkspace().getRoot().getLocation()
			+ "/.metadata/.plugins/" + Activator.PLUGIN_ID + "/";

	/**
	 * Generate a filename for the mask with the specified name
	 * 
	 * @return the generated filename for the mask with the specified name.
	 */
	public static String generateXMLFileName() {
		return UserTransformationMaskTool.TRANSFORMATION_MASK_DIRECTORY + UUID.randomUUID().getMostSignificantBits()
				+ ".xml";
	}

	/**
	 * Configure the file system to create user transformation mask
	 * 
	 */
	public static void configureFileSystem() {
		File transformationMaskDirectory = new File(UserTransformationMaskTool.TRANSFORMATION_MASK_DIRECTORY);
		if (!transformationMaskDirectory.exists()) {
			if (transformationMaskDirectory.mkdirs()) {
				OptimusM2MMaskMessages.UM01.log();
			} else {
				OptimusM2MMaskMessages.UM02.log();
			}
		}
	}

	/**
	 * Create the XML document with an initial transformation mask reference
	 * 
	 * @param transformationMaskReference
	 *            the transformation mask reference.
	 * @return the XML document associated to the reference transformation mask.
	 */
	private static Document createXMLDocumentWithMaskReference(TransformationMaskReference transformationMaskReference) {
		// Creating the root element
		Element transformationMaskRoot = new Element("transformationMask");

		// Creating the XML document
		Document document = new Document(transformationMaskRoot);

		Element nameElement = new Element("name");
		nameElement.setText(transformationMaskReference.getName());
		transformationMaskRoot.addContent(nameElement);

		Element descriptionElement = new Element("description");
		descriptionElement.setText(transformationMaskReference.getDescription());
		transformationMaskRoot.addContent(descriptionElement);

		Element typeElement = new Element("type");
		typeElement.setText("inclusive");
		transformationMaskRoot.addContent(typeElement);

		ITransformationMask initialTransformationMask = transformationMaskReference.getImplementation();
		for (TransformationDataSource transformationDataSource : TransformationDataSourceManager.INSTANCE
				.getTransformationDataSources()) {
			for (TransformationReference transformationReference : transformationDataSource.getAll()) {
				if (initialTransformationMask.isTransformationEnabled(transformationReference.getId())) {
					Element transformation = new Element("transformation");
					Attribute transformationName = new Attribute("name", transformationReference.getId());
					transformation.setAttribute(transformationName);
					transformationMaskRoot.addContent(transformation);
				}
			}
		}

		return document;
	}

	/**
	 * Write the XML file associated to the transformation mask in the file
	 * system.
	 * 
	 * @param file
	 *            the file of the transformation mask.
	 * @param document
	 *            the document XML to write in the file system.
	 */
	private static void writeTransformationMask(File file, Document document) {
		try {
			XMLOutputter output = new XMLOutputter(Format.getPrettyFormat());
			FileOutputStream outputStream = new FileOutputStream(file);
			output.output(document, outputStream);
			outputStream.close();
		} catch (IOException e) {
			OptimusM2MMaskMessages.UM05.log(file.getName(), e.getMessage());
		}
	}

	/**
	 * Create an user transformation mask reference extended another
	 * transformation mask reference
	 * 
	 * @param extendedTransformationMaskReference
	 *            the extended transformation mask reference.
	 */
	public static void createUserTransformationMask(TransformationMaskReference extendedTransformationMaskReference) {
		UserTransformationMaskTool.configureFileSystem();
		File transformationMaskFile = new File(UserTransformationMaskTool.generateXMLFileName());
		while (transformationMaskFile.exists()) {
			transformationMaskFile = new File(UserTransformationMaskTool.generateXMLFileName());
		}
		Document document = UserTransformationMaskTool
				.createXMLDocumentWithMaskReference(extendedTransformationMaskReference);
		UserTransformationMaskTool.writeTransformationMask(transformationMaskFile, document);
		OptimusM2MMaskMessages.UM04.log(extendedTransformationMaskReference.getName());
	}

	/**
	 * Create an XML file containing a transformation mask
	 * 
	 * @param transformationMaskFile
	 *            the file containing the transformation mask.
	 * @param newTransformationMaskReference
	 *            the new transformation mask.
	 */
	public static void createUserTransformationMask(File transformationMaskFile,
			TransformationMaskReference newTransformationMaskReference) {
		UserTransformationMaskTool.configureFileSystem();
		Document document = UserTransformationMaskTool
				.createXMLDocumentWithMaskReference(newTransformationMaskReference);
		UserTransformationMaskTool.writeTransformationMask(transformationMaskFile, document);
		OptimusM2MMaskMessages.UM20.log(newTransformationMaskReference.getName());
	}

}
