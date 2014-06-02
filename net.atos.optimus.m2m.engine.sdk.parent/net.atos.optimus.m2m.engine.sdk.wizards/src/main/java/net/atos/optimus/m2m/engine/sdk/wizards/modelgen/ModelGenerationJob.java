package net.atos.optimus.m2m.engine.sdk.wizards.modelgen;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.atos.optimus.m2m.engine.core.requirements.AbstractRequirement;
import net.atos.optimus.m2m.engine.core.transformations.ExtensionPointTransformationDataSource;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationDataSource;
import net.atos.optimus.m2m.engine.core.transformations.TransformationReference;
import net.atos.optimus.m2m.engine.core.transformations.TransformationSet;
import net.atos.optimus.m2m.engine.sdk.wizards.Activator;

import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLFactory;

/**
 * Eclipse Job, that generates an UML model from transformation sets
 * 
 * @author mvanbesien
 * 
 */
public class ModelGenerationJob extends WorkspaceJob {

	/**
	 * Project in which we create the model (initial user selection)
	 */
	private IJavaProject javaProject;

	/**
	 * Transformation Data Source. Defaultly instanciated to the extension points one.
	 */
	private ITransformationDataSource transformationDataSource = ExtensionPointTransformationDataSource.instance();

	/**
	 * Required. Set the java project, for which the model will be created in
	 * @param javaProject
	 * @return
	 */
	public ModelGenerationJob withJavaProject(IJavaProject javaProject) {
		this.javaProject = javaProject;
		return this;
	}

	/**
	 * Use this method if you want to use an external transformation data source.
	 * @param transformationDataSource
	 * @return
	 */
	public ModelGenerationJob withTransformationDataSource(ITransformationDataSource transformationDataSource) {
		this.transformationDataSource = transformationDataSource;
		return this;
	}

	public ModelGenerationJob() {
		super("Transformations Model Generator");
		this.setPriority(BUILD);
		this.setUser(true);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.resources.WorkspaceJob#runInWorkspace(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
		if (this.javaProject == null) {
			return new Status(IStatus.CANCEL, Activator.PLUGIN_ID, "Java Project is null");
		}

		ResourceSet resourceSet = new ResourceSetImpl();
		String fileName = String.format("TransformationModel-%d.uml", System.currentTimeMillis());
		URI uri = URI.createPlatformResourceURI(
				this.javaProject.getProject().getFullPath().append(fileName).toString(), true);
		Resource resource = resourceSet.createResource(uri);

		Model model = UMLFactory.eINSTANCE.createModel();
		model.setName("Transformations");
		resource.getContents().add(model);

		Collection<TransformationReference> transformationReferences = transformationDataSource.getAll();

		Map<TransformationSet, org.eclipse.uml2.uml.Package> packageMap = new HashMap<TransformationSet, Package>();
		Map<TransformationReference, Class> classMap = new HashMap<TransformationReference, Class>();

		for (TransformationReference transformationReference : transformationReferences) {
			TransformationSet transformationSet = transformationReference.getTransformationSet();
			Package ePackage = packageMap.get(transformationSet);
			if (ePackage == null) {
				ePackage = UMLFactory.eINSTANCE.createPackage();
				ePackage.setName(transformationSet.getId());
				model.getPackagedElements().add(ePackage);
				packageMap.put(transformationSet, ePackage);
			}
			Class eClass = UMLFactory.eINSTANCE.createClass();
			eClass.setName(transformationReference.getId());
			ePackage.getPackagedElements().add(eClass);
			classMap.put(transformationReference, eClass);
		}

		for (TransformationReference transformationReference : transformationReferences) {
			Class eClass = classMap.get(transformationReference);
			for (AbstractRequirement requirement : transformationReference.getRequirements()) {
				TransformationReference requiredTransformation = transformationDataSource.getById(requirement.getId());
				if (requiredTransformation != null) {
					Class referencedClass = classMap.get(requiredTransformation);
					if (referencedClass != null) {
						Dependency dependency = eClass.createDependency(referencedClass);
						dependency.setName("<<" + requirement.getClass().getSimpleName() + ">>");
					}
				}
			}
		}

		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return Status.OK_STATUS;
	}
}
