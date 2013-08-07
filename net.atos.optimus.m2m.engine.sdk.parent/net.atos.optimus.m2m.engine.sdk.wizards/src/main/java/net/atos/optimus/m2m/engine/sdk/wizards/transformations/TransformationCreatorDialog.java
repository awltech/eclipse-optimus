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
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 */
public class TransformationCreatorDialog {

	private final Shell shell;

	private final Text idText;

	private String id;

	private final Text packageText;

	private IPackageFragment fragment;

	private final Text trnText;

	private String trn;

	private final Text factoryText;

	private String factory;

	private final Text typeText;

	private String type;

	private final Text trnSetText;

	private String trnSet;

	private final Button computeButton;

	private boolean keepOpen = true;

	private boolean isOK = false;

	private final IJavaProject javaProject;

	public TransformationCreatorDialog(final IJavaProject javaProject,
			final TransformationCreatorPreferences preferences) {

		this.javaProject = javaProject;

		this.shell = new Shell(Display.getCurrent() != null ? Display.getCurrent() : Display.getDefault(),
				SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM | SWT.RESIZE);

		this.shell.setLayout(new FillLayout());
		this.shell.setText(Messages.DIALOG_TITLE.message());

		final Composite background = new Composite(this.shell, SWT.NONE);
		background.setLayout(new FormLayout());

		final Group group1 = new Group(background, SWT.NONE);
		group1.setLayout(new FormLayout());
		group1.setText(Messages.EXTPT_GROUP_TITLE.message());

		// Manage the transformation id
		final Label idLabel = new Label(group1, SWT.NONE);
		idLabel.setText(Messages.TRN_ID.message());
		idLabel.setToolTipText(Messages.TRN_ID_TOOLTIP.message());
		this.idText = new Text(group1, SWT.BORDER);

		// Manage the Transformation Set name
		final Label trnSetLabel = new Label(group1, SWT.NONE);
		trnSetLabel.setText(Messages.TRNSET_ID.message());
		trnSetLabel.setToolTipText(Messages.TRNSET_ID_TOOLTIP.message());
		this.trnSetText = new Text(group1, SWT.BORDER);

		final Group group2 = new Group(background, SWT.NONE);
		group2.setLayout(new FormLayout());
		group2.setText(Messages.JAVA_GROUP_TITLE.message());

		// Manage the transformation package
		final Label packageLabel = new Label(group2, SWT.NONE);
		packageLabel.setText(Messages.JAVAPACK_LABEL.message());
		packageLabel.setToolTipText(Messages.JAVAPACK_TOOLTIP.message());
		this.packageText = new Text(group2, SWT.BORDER);
		final Button packageButton = new Button(group2, SWT.PUSH);
		packageButton.setText(Messages.JAVAPACK_BUTTON.message());

		// Manage the Transformation Class Name
		final Label trnLabel = new Label(group2, SWT.NONE);
		trnLabel.setText(Messages.TRNCLASS_LABEL.message());
		trnLabel.setToolTipText(Messages.TRNCLASS_TOOLTIP.message());

		this.trnText = new Text(group2, SWT.BORDER);

		// Manage the Transformation Class Name
		final Label factoryLabel = new Label(group2, SWT.NONE);
		factoryLabel.setText(Messages.TRNFACT_LABEL.message());
		factoryLabel.setToolTipText(Messages.TRNFACT_TOOLTIP.message());

		this.factoryText = new Text(group2, SWT.BORDER);

		// Manage the type of transformed object
		final Label typeLabel = new Label(group2, SWT.NONE);
		typeLabel.setText(Messages.TRNELT_LABEL.message());
		typeLabel.setToolTipText(Messages.TRNELT_TOOLTIP.message());
		this.typeText = new Text(group2, SWT.BORDER);
		this.typeText.setEnabled(false);
		final Button typeButton = new Button(group2, SWT.PUSH);
		typeButton.setText(Messages.TRNELT_BUTTON.message());

		final Button okButton = new Button(background, SWT.PUSH);
		okButton.setText(Messages.OK_BUTTON.message());

		this.computeButton = new Button(group2, SWT.PUSH);
		this.computeButton.setText("Compute...");

		// Define the layout between all the objects.
		FormDataBuilder.on(group1).top().horizontal();
		FormDataBuilder.on(group2).top(group1).left().right().bottom(okButton);

		FormDataBuilder.on(idLabel).top().left().width(200);
		FormDataBuilder.on(this.idText).top().left(idLabel).right().width(600);

		FormDataBuilder.on(trnSetLabel).top(this.idText).left().width(200);
		FormDataBuilder.on(this.trnSetText).top(this.idText).left(trnSetLabel).right().width(600);

		FormDataBuilder.on(packageLabel).top().left().width(200);
		FormDataBuilder.on(this.packageText).top().left(packageLabel).right(packageButton);
		FormDataBuilder.on(packageButton).top().right().width(80).height(22);

		FormDataBuilder.on(trnLabel).top(this.packageText).left().width(200);
		FormDataBuilder.on(this.trnText).top(this.packageText).left(trnLabel).width(600).right(this.computeButton);

		FormDataBuilder.on(factoryLabel).top(this.trnText).left().width(200);
		FormDataBuilder.on(this.factoryText).top(this.trnText).left(factoryLabel).width(600).right(this.computeButton);

		FormDataBuilder.on(this.computeButton).top(this.packageText).right().bottom(this.typeText).width(80);

		FormDataBuilder.on(typeLabel).top(this.factoryText).left().width(200);
		FormDataBuilder.on(this.typeText).top(this.factoryText).left(typeLabel).right(typeButton);
		FormDataBuilder.on(typeButton).top(this.factoryText).right().width(80).height(22);

		FormDataBuilder.on(okButton).bottom().right();

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
		} catch (final NullPointerException npe) {
			bounds = this.shell.getDisplay().getPrimaryMonitor().getBounds();
		}
		final Rectangle rect = this.shell.getBounds();
		this.shell.setLocation(bounds.x + ((bounds.width - rect.width) / 2), bounds.y
				+ ((bounds.height - rect.height) / 2));

		okButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				TransformationCreatorDialog.this.isOK = true;
				TransformationCreatorDialog.this.keepOpen = false;
				TransformationCreatorDialog.this.id = TransformationCreatorDialog.this.idText.getText();
				TransformationCreatorDialog.this.trn = TransformationCreatorDialog.this.trnText.getText();
				TransformationCreatorDialog.this.factory = TransformationCreatorDialog.this.factoryText.getText();
				TransformationCreatorDialog.this.trnSet = TransformationCreatorDialog.this.trnSetText.getText();
				TransformationCreatorDialog.this.type = TransformationCreatorDialog.this.typeText.getText();
				TransformationCreatorDialog.this.shell.update();
			}
		});

		this.computeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				String text = TransformationCreatorDialog.this.idText.getText();
				if (text.indexOf(".") > -1) {
					text = text.substring(text.lastIndexOf(".") + 1);
				}
				TransformationCreatorDialog.this.trnText.setText(text);
				TransformationCreatorDialog.this.factoryText.setText(text.concat("Factory"));
			}
		});

		packageButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				try {
					final SelectionDialog createPackageDialog = JavaUI.createPackageDialog(new Shell(
							TransformationCreatorDialog.this.shell), TransformationCreatorDialog.this.javaProject,
							IJavaElementSearchConstants.CONSIDER_REQUIRED_PROJECTS);
					createPackageDialog.setMessage(Messages.JAVAPACK_SELECTOR_LABEL.message());
					createPackageDialog.setTitle(Messages.DIALOG_TITLE.message());
					if ((createPackageDialog.open() == Window.OK) && (createPackageDialog.getResult().length > 0)) {
						final Object o = createPackageDialog.getResult()[0];
						if (o instanceof IPackageFragment) {
							TransformationCreatorDialog.this.fragment = (IPackageFragment) o;
							TransformationCreatorDialog.this.packageText.setText(((IPackageFragment) o)
									.getElementName());
							TransformationCreatorDialog.this.packageText.update();
						}
					}
				} catch (final JavaModelException e1) {
					e1.printStackTrace();
				}
			}
		});

		typeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				try {
					final IType eObjectType = TransformationCreatorDialog.this.javaProject
							.findType("org.eclipse.emf.ecore.EObject");
					final Shell childShell = new Shell(TransformationCreatorDialog.this.shell);
					final SelectionDialog createTypeDialog = JavaUI.createTypeDialog(childShell, null,
							SearchEngine.createHierarchyScope(eObjectType),
							IJavaElementSearchConstants.CONSIDER_CLASSES_AND_INTERFACES, false);
					createTypeDialog.setTitle(Messages.DIALOG_TITLE.message());
					createTypeDialog.setMessage(Messages.TRNELT_SELECTOR_LABEL.message());
					if ((createTypeDialog.open() == Window.OK) && (createTypeDialog.getResult().length > 0)) {
						final Object o = createTypeDialog.getResult()[0];
						if (o instanceof IType) {
							TransformationCreatorDialog.this.typeText.setText(((IType) o).getFullyQualifiedName());
							TransformationCreatorDialog.this.typeText.update();
						}
					}
				} catch (final JavaModelException e1) {
					e1.printStackTrace();
				}
			}
		});

	}

	public boolean open() {
		this.shell.open();
		while (this.keepOpen && !this.shell.isDisposed()) {
			if (!this.shell.getDisplay().readAndDispatch()) {
				this.shell.getDisplay().sleep();
			}
		}
		this.shell.dispose();
		return this.isOK;
	}

	public IPackageFragment getPackageFragment() {
		return this.fragment;
	}

	public String getId() {
		return this.id;
	}

	public IPackageFragment getFragment() {
		return this.fragment;
	}

	public String getTrn() {
		return this.trn;
	}

	public String getFactory() {
		return this.factory;
	}

	public String getType() {
		return this.type;
	}

	public String getTrnSet() {
		return this.trnSet;
	}

}
