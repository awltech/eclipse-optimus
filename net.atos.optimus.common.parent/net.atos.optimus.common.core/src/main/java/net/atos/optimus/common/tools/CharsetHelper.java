package net.atos.optimus.common.tools;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;

/**
 * 
 * Facility to retrieve the Eclipse's charset of the file, from the
 * java.io.file.
 * 
 * @author mvanbesien
 * @since 1.1.0
 */
public final class CharsetHelper {

	/**
	 * Retrieves the File's charset.
	 * 
	 * @param file
	 * @return
	 */
	public static final String getCharset(final File file) {
		IFile eclipseFile = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(new Path(file.getPath()));
		if (eclipseFile == null) {
			return null;
		}
		try {
			if (eclipseFile.exists()) {
				String charset = eclipseFile.getCharset();
				return charset;
			} else {
				IProject project = eclipseFile.getProject();
				return project.getDefaultCharset(true);
			}
		} catch (Exception e) {
			Activator
					.getDefault()
					.getLog()
					.log(new Status(IStatus.WARNING, Activator.PLUGIN_ID, "Failed to get charset from file: "
							+ eclipseFile.getFullPath().toString()));
		}
		return null;
	}
}
