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
	 * Give the file associated to the mask with the specified name
	 * 
	 * @param maskName
	 *            the name of the mask.
	 * @return the file associated to the mask with the specified name.
	 */
	public static File giveAssociatedXMLFile(String maskName) {
		return new File(UserTransformationMaskTool.TRANSFORMATION_MASK_DIRECTORY + maskName + ".xml");
	}

	/**
	 * Give the old file associated to the mask with the specified name
	 * 
	 * @param maskName
	 *            the name of the mask.
	 * @return the old file associated to the mask with the specified name.
	 */
	public static File giveAssociatedOldFile(String maskName) {
		return new File(UserTransformationMaskTool.TRANSFORMATION_MASK_DIRECTORY + maskName + ".old");
	}

	/**
	 * Give the mask name associated to the transformation file
	 * 
	 * @param transformationMaskFile
	 *            the transformation mask file.
	 * @return the mask name associated to the transformation file.
	 */
	public static String giveAssociatedMaskName(File transformationMaskFile) {
		String maskName = transformationMaskFile.getName();
		maskName = maskName.substring(0, maskName.lastIndexOf(".xml"));
		return maskName;
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

		Element descriptionElement = new Element("description");
		descriptionElement.setText(transformationMaskReference.getDescription());
		transformationMaskRoot.addContent(descriptionElement);

		ITransformationMask initialTransformationMask = transformationMaskReference.getImplementation();
		for (TransformationDataSource transformationDataSource : TransformationDataSourceManager.INSTANCE
				.getTransformationDataSources()) {
			for (TransformationReference transformationReference : transformationDataSource.getAll()) {
				Element transformation = new Element("transformation");
				Attribute transformationName = new Attribute("name", transformationReference.getId());
				transformation.setAttribute(transformationName);
				Attribute transformationEnabled = new Attribute("enable", Boolean.toString(initialTransformationMask
						.isTransformationEnabled(transformationReference.getId())));
				transformation.setAttribute(transformationEnabled);
				transformationMaskRoot.addContent(transformation);
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
		String maskName = extendedTransformationMaskReference.getName();
		UserTransformationMaskTool.configureFileSystem();
		File transformationMaskFile = UserTransformationMaskTool.giveAssociatedXMLFile(maskName);
		if (transformationMaskFile.exists()) {
			OptimusM2MMaskMessages.UM06.log(maskName);
		} else {
			Document document = UserTransformationMaskTool
					.createXMLDocumentWithMaskReference(extendedTransformationMaskReference);
			UserTransformationMaskTool.writeTransformationMask(transformationMaskFile, document);
			OptimusM2MMaskMessages.UM04.log(maskName);
		}
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
		OptimusM2MMaskMessages.UM20.log(transformationMaskFile.getName());
	}

	public static void suppressUserTransformationMask(String maskName) {
		File transformationMaskFile = UserTransformationMaskTool.giveAssociatedXMLFile(maskName);
		File transformationOldMaskFile = UserTransformationMaskTool.giveAssociatedOldFile(maskName);
		if (transformationOldMaskFile.exists()) {
			transformationOldMaskFile.delete();
		}
		transformationMaskFile.renameTo(transformationOldMaskFile);
	}

}
