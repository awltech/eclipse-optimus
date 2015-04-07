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
package net.atos.optimus.common.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

import net.atos.optimus.common.tools.logging.OptimusConsoleManager;
import net.atos.optimus.common.tools.logging.OptimusLogger;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "net.atos.optimus.common.core"; //$NON-NLS-1$

	private List<Level> levels;

	// The shared instance
	private static Activator plugin;

	public static final String CONSOLELOGGER_LEVEL_KEY = Activator.PLUGIN_ID + ".ConsoleLoggerLevel";

	/**
	 * The constructor
	 */
	public Activator() {
		List<Level> temp = new ArrayList<Level>();
		temp.add(Level.OFF);
		temp.add(Level.SEVERE);
		temp.add(Level.WARNING);
		temp.add(Level.INFO);
		temp.add(Level.CONFIG);
		temp.add(Level.FINE);
		temp.add(Level.FINER);
		temp.add(Level.FINEST);
		temp.add(Level.ALL);
		levels = Collections.unmodifiableList(temp);
	}

	public List<Level> getLevels() {
		return levels;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		OptimusConsoleManager.getInstance().register(OptimusLogger.logger);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	public void logError(String message, Throwable e) {
		Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, message, e));
	}
	
	/**
	  * Returns image in plugin
	  * 
	  * @param pluginId
	  *            : Id of the plugin containing thie image
	  * @param imageFilePath
	  *            : image File Path in plugin
	  * @return Image if exists
	  */
	  public Image getImage(String pluginId, String imageFilePath) {
	        Image image = Activator.getDefault().getImageRegistry().get(pluginId + ":" + imageFilePath);
	        if (image == null) {
	               image = loadImage(pluginId, imageFilePath);
	        }
	        return image;
	 }

	 /**
	  * Loads image in Image Registry is not available in it
	  *
	  * @param pluginId
	  *            : Id of the plugin containing thie image
	  * @param imageFilePath
	  *            : image File Path in plugin
	  * @return Image if loaded
	  */
	  private synchronized Image loadImage(String pluginId, String imageFilePath) {
	        String id = pluginId + ":" + imageFilePath;
	        Image image = Activator.getDefault().getImageRegistry().get(id);
	        if (image != null)
	              return image;
	        ImageDescriptor imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(pluginId, imageFilePath);
	        if (imageDescriptor != null) {
	              image = imageDescriptor.createImage();
	              Activator.getDefault().getImageRegistry().put(pluginId + ":" + imageFilePath, image);
	        }
	        return image;
	  }

}
