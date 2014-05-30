package net.atos.optimus.m2m.engine.sdk.wizards.modelgen;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;

public class ModelGenerationAction implements IActionDelegate {

	private IJavaProject javaProject;

	@Override
	public void run(IAction action) {
		new ModelGenerationJob().withJavaProject(this.javaProject).schedule();

	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.javaProject = null;
		if (selection instanceof IStructuredSelection) {
			Object o = ((IStructuredSelection) selection).getFirstElement();
			if (o instanceof IJavaProject) {
				this.javaProject = (IJavaProject) o;
			}
		}
	}

}
