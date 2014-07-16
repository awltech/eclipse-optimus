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
package net.atos.optimus.common.tools.ui;

import java.net.MalformedURLException;
import java.net.URL;

import net.atos.optimus.common.tools.Activator;
import net.atos.optimus.common.tools.swt.FormDataBuilder;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;

/**
 * Main Preference Page for Optimus. By default displays information about the
 * tool & authors
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 * 
 */
public class MainPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

	private static final String WLTECH_IMAGE_PATH = "images/optimus-logo.png";

	@Override
	public void init(IWorkbench workbench) {
	}

	@Override
	protected Control createContents(Composite parent) {

		Composite background = new Composite(parent, SWT.NONE);
		background.setLayout(new FormLayout());

		Label title = new Label(background, SWT.WRAP);
		Label description = new Label(background, SWT.WRAP);
		Label image = new Label(background, SWT.BORDER);
		Link moreInfo = new Link(background, SWT.RIGHT);
		Link moreInfo1 = new Link(background, SWT.RIGHT);
		Link moreInfo2 = new Link(background, SWT.RIGHT);

		description.setText(MainPreferencePageMessages.DESCRIPTION.value());
		moreInfo.setText(MainPreferencePageMessages.MORE_INFO.value());
		moreInfo1.setText(MainPreferencePageMessages.MORE_INFO_1.value());
		moreInfo2.setText(MainPreferencePageMessages.MORE_INFO_2.value());
		image.setBackgroundImage(Activator.getDefault().getImage(Activator.PLUGIN_ID, WLTECH_IMAGE_PATH));

		moreInfo.addSelectionListener(new OpenLinkActionSelectionAdapter());
		moreInfo1.addSelectionListener(new OpenLinkActionSelectionAdapter());
		moreInfo2.addSelectionListener(new OpenLinkActionSelectionAdapter());

		FormDataBuilder.on(title).top().left().right();
		FormDataBuilder.on(description).top(title).left().right().bottom(image);
		FormDataBuilder.on(image).left().bottom().width(140).height(55);
		FormDataBuilder.on(moreInfo).bottom(moreInfo1).right();
		FormDataBuilder.on(moreInfo1).bottom(moreInfo2).right();
		FormDataBuilder.on(moreInfo2).bottom().right();

		return background;
	}

	private static final class OpenLinkActionSelectionAdapter extends SelectionAdapter {

		@Override
		public void widgetSelected(SelectionEvent e) {
			IWorkbenchBrowserSupport browserSupport = PlatformUI.getWorkbench().getBrowserSupport();
			IWebBrowser browser;
			try {
				browser = browserSupport.createBrowser(IWorkbenchBrowserSupport.LOCATION_BAR
						| IWorkbenchBrowserSupport.NAVIGATION_BAR, null,
						MainPreferencePageMessages.BROWSER_TEXT.value(),
						MainPreferencePageMessages.BROWSER_TEXT.value());
				browser.openURL(new URL(e.text));
			} catch (PartInitException e1) {
				Activator.getDefault().getLog()
						.log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, e1.getMessage(), e1));
			} catch (MalformedURLException e1) {
				Activator.getDefault().getLog()
						.log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, e1.getMessage(), e1));
			}

		}
	}

}
