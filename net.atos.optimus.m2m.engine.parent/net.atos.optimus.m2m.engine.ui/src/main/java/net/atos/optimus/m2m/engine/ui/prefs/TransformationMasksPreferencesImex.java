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
package net.atos.optimus.m2m.engine.ui.prefs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import net.atos.optimus.m2m.engine.core.Activator;
import net.atos.optimus.m2m.engine.core.masks.TransformationMaskReference;
import net.atos.optimus.m2m.engine.masks.UserTransformationMaskTool;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * Imex for import/export transformation masks
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class TransformationMasksPreferencesImex {

	public static final String EXPORT_PREF = "PreferencesExportPath";

	public static final String IMPORT_PATH_PREF = "PreferencesImportPath";

	public static final String IMPORT_NAME_PREF = "PreferencesImportName";

	/**
	 * Export a transformation mask in XML file
	 * 
	 * @param exportedMask
	 *            the transformation mask to export.
	 */
	public static void exportTransformationMask(TransformationMaskReference exportedMask) {
		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		String pathName = preferenceStore.getString(TransformationMasksPreferencesImex.EXPORT_PREF);

		FileDialog dialog = new FileDialog(new Shell(Display.getDefault()));
		if (pathName != null && pathName.length() > 1) {
			dialog.setFilterPath(pathName);
		}
		String maskName = exportedMask.getName().trim().replace(" ", "_");
		dialog.setFileName(maskName + ".xml");
		dialog.setText(TransformationMasksPreferencesMessages.EXPORT_WINDOW.message());

		String result = dialog.open();
		if (result == null) {
			return;
		}

		String newPathName = dialog.getFilterPath();
		String newfileName = dialog.getFileName();
		if (!newPathName.equals(pathName)) {
			preferenceStore.putValue(TransformationMasksPreferencesImex.EXPORT_PREF, newPathName);
		}

		String fullpath = newPathName + File.separator + newfileName;
		File transformationMaskFile = new File(fullpath);

		UserTransformationMaskTool.createUserTransformationMask(transformationMaskFile,
				exportedMask.getImplementation());
	}

	/**
	 * Import a transformation mask describing in an XML file
	 * 
	 */
	public static void importTransformationMask() {
		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		FileDialog dialog = new FileDialog(new Shell(Display.getDefault()));
		String pathName = preferenceStore.getString(TransformationMasksPreferencesImex.IMPORT_PATH_PREF);
		String fileName = preferenceStore.getString(TransformationMasksPreferencesImex.IMPORT_NAME_PREF);

		dialog.setText(TransformationMasksPreferencesMessages.IMPORT_WINDOW.message());
		dialog.setFilterExtensions(new String[] { "*.xml" });
		dialog.setFilterPath(pathName);
		dialog.setFileName(fileName);
		dialog.open();

		String newFileName = dialog.getFileName();
		String newPathName = dialog.getFilterPath();

		if (!newFileName.equals(fileName)) {
			preferenceStore.putValue(IMPORT_NAME_PREF, newFileName);
		}
		if (!newPathName.equals(pathName)) {
			preferenceStore.putValue(IMPORT_NAME_PREF, newPathName);
		}

		String fullPath = newPathName + File.separator + newFileName;
		File importMaskFile = new File(fullPath);

		File transformationMaskFile = UserTransformationMaskTool.giveAssociatedXMLFile(newFileName.replace(
				".xml", ""));

		UserTransformationMaskTool.configureFileSystem();
		FileInputStream sourceFile;
		try {
			sourceFile = new FileInputStream(importMaskFile);
			int c;
			FileOutputStream destFile = new FileOutputStream(transformationMaskFile);
			while ((c = sourceFile.read()) != -1) {
				destFile.write(c);
			}
			destFile.close();
			sourceFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
