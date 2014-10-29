package net.atos.optimus.m2m.engine.sdk.wizards.parentinfo;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;

/**
 * Popup action implementation, active when an EMF object is selected, to
 * display information about its relation with its parent. This is mainly use,
 * in case of derived references, to know what to use when modifying a model
 * programmatically.
 * 
 * @author mvanbesien (mvaawl@gmail.com)
 *
 */
public class DisplayParentInformationAction implements IActionDelegate {

	/*
	 * 
	 */
	private static final String UML_SEPARATOR = "::";

	/**
	 * Processed EObject
	 */
	private EObject eObject;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		MessageBox messageBox = new MessageBox(new Shell());
		messageBox.setText(DisplayParentInformationMessages.DIALOG_TITLE.value());

		if (this.eObject == null) {
			messageBox.setMessage(DisplayParentInformationMessages.MESSAGE_NULL_ELEMENT.value());
		} else if (this.eObject.eContainingFeature() == null) {
			messageBox.setMessage(DisplayParentInformationMessages.MESSAGE_NULL_PARENT.value());
		} else {
			EObject parent = this.eObject.eContainer();
			EStructuralFeature eContainingFeature = this.eObject.eContainingFeature();
			String name = eContainingFeature.getName();
			boolean isMultiple = eContainingFeature.isMany();
			messageBox.setMessage(DisplayParentInformationMessages.MESSAGE_INFO.value(
					parent.eClass().getEPackage().getName() + UML_SEPARATOR + parent.eClass().getName(),
					name,
					isMultiple ? DisplayParentInformationMessages.IS.value() : DisplayParentInformationMessages.IS_NOT
							.value()));
		}
		messageBox.open();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action
	 * .IAction, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.eObject = null;
		if (selection instanceof IStructuredSelection) {
			Object o = ((IStructuredSelection) selection).getFirstElement();
			if (o instanceof EObject) {
				this.eObject = (EObject) o;
			}
		}
		action.setEnabled(this.eObject != null);
	}

}
