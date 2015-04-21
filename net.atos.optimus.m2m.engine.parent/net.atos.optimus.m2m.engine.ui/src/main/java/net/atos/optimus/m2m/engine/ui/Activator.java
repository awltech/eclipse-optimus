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
package net.atos.optimus.m2m.engine.ui;

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
	public static final String PLUGIN_ID = "net.atos.optimus.m2m.engine.ui"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {
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
		this.getImageRegistry().get(null);
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
		this.getImageRegistry().dispose();
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
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

	/**
	  * Returns image in this plugin
	  *
	  * @param imageFilePath
	  *            : image File Path in this plugin
	  * @return Image if exists
	  */
	 public Image getImage(String imageFilePath) {
	        Image image = Activator.getDefault().getImageRegistry().get(Activator.PLUGIN_ID + ":" + imageFilePath);
	       if (image == null)
	              image = loadImage(Activator.PLUGIN_ID, imageFilePath);
	       return image;
	 }
	
}
