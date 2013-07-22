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
package net.atos.optimus.m2m.engine.sdk.wizards.transformations;

import net.atos.optimus.common.tools.swt.FormDataBuilder;
import net.atos.optimus.m2m.engine.sdk.wizards.Messages;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.ui.IJavaElementSearchConstants;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.SelectionDialog;

/**
 *  @author Maxence Vanbésien (mvaawl@gmail.com)
 *  @since 1.0
 */
public class TransformationCreatorDialog {

	private Shell shell;

	private Text idText;

	private String id;

	private Text packageText;

	private IPackageFragment fragment;

	private Text trnText;

	private String trn;

	private Text factoryText;

	private String factory;

	private Text typeText;

	private String type;

	private Text trnSetText;

	private String trnSet;

	private Button computeButton;

	private boolean keepOpen = true;

	private boolean isOK = false;

	private IJavaProject javaProject;

	public TransformationCreatorDialog(final IJavaProject javaProject, TransformationCreatorPreferences preferences) {

		this.javaProject = javaProject;

		this.shell = new Shell(Display.getCurrent() != null ? Display.getCurrent() : Display.getDefault(),
				SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM | SWT.RESIZE);

		this.shell.setLayout(new FillLayout());
		this.shell.setText(Messages.DIALOG_TITLE.message());

		Composite background = new Composite(this.shell, SWT.NONE);
		background.setLayout(new FormLayout());

		Group group1 = new Group(background, SWT.NONE);
		group1.setLayout(new FormLayout());
		group1.setText(Messages.EXTPT_GROUP_TITLE.message());

		// Manage the transformation id
		Label idLabel = new Label(group1, SWT.NONE);
		idLabel.setText(Messages.TRN_ID.message());
		idLabel.setToolTipText(Messages.TRN_ID_TOOLTIP.message());
		this.idText = new Text(group1, SWT.BORDER);

		// Manage the Transformation Set name
		Label trnSetLabel = new Label(group1, SWT.NONE);
		trnSetLabel.setText(Messages.TRNSET_ID.message());
		trnSetLabel.setToolTipText(Messages.TRNSET_ID_TOOLTIP.message());
		this.trnSetText = new Text(group1, SWT.BORDER);

		Group group2 = new Group(background, SWT.NONE);
		group2.setLayout(new FormLayout());
		group2.setText(Messages.JAVA_GROUP_TITLE.message());

		// Manage the transformation package
		Label packageLabel = new Label(group2, SWT.NONE);
		packageLabel.setText(Messages.JAVAPACK_LABEL.message());
		packageLabel.setToolTipText(Messages.JAVAPACK_TOOLTIP.message());
		this.packageText = new Text(group2, SWT.BORDER);
		Button packageButton = new Button(group2, SWT.PUSH);
		packageButton.setText(Messages.JAVAPACK_BUTTON.message());

		// Manage the Transformation Class Name
		Label trnLabel = new Label(group2, SWT.NONE);
		trnLabel.setText(Messages.TRNCLASS_LABEL.message());
		trnLabel.setToolTipText(Messages.TRNCLASS_TOOLTIP.message());

		this.trnText = new Text(group2, SWT.BORDER);

		// Manage the Transformation Class Name
		Label factoryLabel = new Label(group2, SWT.NONE);
		factoryLabel.setText(Messages.TRNFACT_LABEL.message());
		factoryLabel.setToolTipText(Messages.TRNFACT_TOOLTIP.message());

		this.factoryText = new Text(group2, SWT.BORDER);

		// Manage the type of transformed object
		Label typeLabel = new Label(group2, SWT.NONE);
		typeLabel.setText(Messages.TRNELT_LABEL.message());
		typeLabel.setToolTipText(Messages.TRNELT_TOOLTIP.message());
		this.typeText = new Text(group2, SWT.BORDER);
		this.typeText.setEnabled(false);
		Button typeButton = new Button(group2, SWT.PUSH);
		typeButton.setText(Messages.TRNELT_BUTTON.message());

		Button okButton = new Button(background, SWT.PUSH);
		okButton.setText(Messages.OK_BUTTON.message());

		this.computeButton = new Button(group2, SWT.PUSH);
		this.computeButton.setText("Compute...");

		// Define the layout between all the objects.
		new FormDataBuilder().top().horizontal().apply(group1);
		new FormDataBuilder().top(group1).left().right().bottom(okButton).apply(group2);

		new FormDataBuilder().top().left().width(200).apply(idLabel);
		new FormDataBuilder().top().left(idLabel).right().width(600).apply(idText);

		new FormDataBuilder().top(idText).left().width(200).apply(trnSetLabel);
		new FormDataBuilder().top(idText).left(trnSetLabel).right().width(600).apply(trnSetText);

		new FormDataBuilder().top().left().width(200).apply(packageLabel);
		new FormDataBuilder().top().left(packageLabel).right(packageButton).apply(packageText);
		new FormDataBuilder().top().right().width(80).height(22).apply(packageButton);

		new FormDataBuilder().top(packageText).left().width(200).apply(trnLabel);
		new FormDataBuilder().top(packageText).left(trnLabel).width(600).right(computeButton).apply(trnText);

		new FormDataBuilder().top(trnText).left().width(200).apply(factoryLabel);
		new FormDataBuilder().top(trnText).left(factoryLabel).width(600).right(computeButton).apply(factoryText);

		new FormDataBuilder().top(packageText).right().bottom(typeText).width(80).apply(computeButton);

		new FormDataBuilder().top(factoryText).left().width(200).apply(typeLabel);
		new FormDataBuilder().top(factoryText).left(typeLabel).right(typeButton).apply(typeText);
		new FormDataBuilder().top(factoryText).right().width(80).height(22).apply(typeButton);

		new FormDataBuilder().bottom().right().apply(okButton);

		this.idText.setText(preferences.getTransformationName());
		this.factoryText.setText(preferences.getFactoryName());
		this.packageText.setText(preferences.getFragmentName());
		this.trnText.setText(preferences.getClassName());
		this.trnSetText.setText(preferences.getTransformationSetName());
		this.typeText.setText(preferences.getElementName());
		
		group1.pack();
		group2.pack();
		background.pack();
		this.shell.pack();

		Rectangle bounds;
		try {
			bounds = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().getBounds();
		} catch (NullPointerException npe) {
			bounds = shell.getDisplay().getPrimaryMonitor().getBounds();
		}
		Rectangle rect = shell.getBounds();
		shell.setLocation(bounds.x + (bounds.width - rect.width) / 2, bounds.y + (bounds.height - rect.height) / 2);

		okButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TransformationCreatorDialog.this.isOK = true;
				TransformationCreatorDialog.this.keepOpen = false;
				id = idText.getText();
				trn = trnText.getText();
				factory = factoryText.getText();
				trnSet = trnSetText.getText();
				type = typeText.getText();
				shell.update();
			}
		});

		computeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String text = idText.getText();
				if (text.indexOf(".") > -1)
					text = text.substring(text.lastIndexOf(".") + 1);
				trnText.setText(text);
				factoryText.setText(text.concat("Factory"));
			}
		});

		packageButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					SelectionDialog createPackageDialog = JavaUI.createPackageDialog(new Shell(shell),
							TransformationCreatorDialog.this.javaProject,
							IJavaElementSearchConstants.CONSIDER_REQUIRED_PROJECTS);
					createPackageDialog.setMessage(Messages.JAVAPACK_SELECTOR_LABEL.message());
					createPackageDialog.setTitle(Messages.DIALOG_TITLE.message());
					if (createPackageDialog.open() == Window.OK && createPackageDialog.getResult().length > 0) {
						Object o = createPackageDialog.getResult()[0];
						if (o instanceof IPackageFragment) {
							fragment = (IPackageFragment) o;
							packageText.setText(((IPackageFragment) o).getElementName());
							packageText.update();
						}
					}
				} catch (JavaModelException e1) {
					e1.printStackTrace();
				}
			}
		});

		typeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					IType eObjectType = TransformationCreatorDialog.this.javaProject
							.findType("org.eclipse.emf.ecore.EObject");
					Shell childShell = new Shell(shell);
					SelectionDialog createTypeDialog = JavaUI.createTypeDialog(childShell, null,
							SearchEngine.createHierarchyScope(eObjectType),
							IJavaElementSearchConstants.CONSIDER_CLASSES_AND_INTERFACES, false);
					createTypeDialog.setTitle(Messages.DIALOG_TITLE.message());
					createTypeDialog.setMessage(Messages.TRNELT_SELECTOR_LABEL.message());
					if (createTypeDialog.open() == Window.OK && createTypeDialog.getResult().length > 0) {
						Object o = createTypeDialog.getResult()[0];
						if (o instanceof IType) {
							typeText.setText(((IType) o).getFullyQualifiedName());
							typeText.update();
						}
					}
				} catch (JavaModelException e1) {
					e1.printStackTrace();
				}
			}
		});

	}

	public boolean open() {
		shell.open();
		while (keepOpen && !shell.isDisposed()) {
			if (!shell.getDisplay().readAndDispatch()) {
				shell.getDisplay().sleep();
			}
		}
		shell.dispose();
		return isOK;
	}

	public IPackageFragment getPackageFragment() {
		return fragment;
	}

	public String getId() {
		return id;
	}

	public IPackageFragment getFragment() {
		return fragment;
	}

	public String getTrn() {
		return trn;
	}

	public String getFactory() {
		return factory;
	}

	public String getType() {
		return type;
	}

	public String getTrnSet() {
		return trnSet;
	}

}
