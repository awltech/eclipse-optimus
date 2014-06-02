package net.atos.optimus.m2m.engine.sdk.wizards.modelgen;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;

/**
 * Simple Action to generate UML model from contributed code generators, through extension points.
 * 
 * @author mvanbesien
 * 
 */
public class ModelGenerationAction implements IActionDelegate {

	/**
	 * Selected Java projects
	 */
	private IJavaProject javaProject;

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		new ModelGenerationJob().withJavaProject(this.javaProject).schedule();

	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
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
