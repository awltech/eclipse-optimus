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
package net.atos.optimus.m2m.javaxmi.core.annotations;

import java.util.Iterator;

import net.atos.optimus.m2m.javaxmi.operation.annotations.JavaAnnotation;
import net.atos.optimus.m2m.javaxmi.operation.element.AbstractDeclaration;
import net.atos.optimus.m2m.javaxmi.operation.instruction.elementary.ArrayInitializerInstruction;
import net.atos.optimus.m2m.javaxmi.operation.parameters.Parameter;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.Annotation;
import org.eclipse.gmt.modisco.java.AnnotationMemberValuePair;
import org.eclipse.gmt.modisco.java.AnnotationTypeDeclaration;
import org.eclipse.gmt.modisco.java.AnnotationTypeMemberDeclaration;
import org.eclipse.gmt.modisco.java.ArrayInitializer;
import org.eclipse.gmt.modisco.java.BodyDeclaration;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.StringLiteral;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * The purpose of such class is to help with the creation of parameters to
 * annotation.
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 * 
 */
public class JavaAnnotationHelper {

	/**
	 * Adds a new string value to an annotation property. Note that is the
	 * annotation already has a valued property with the same name, the value of
	 * the property will be updated with an array
	 * 
	 * @param annotation
	 * @param propertyName
	 * @param propertyValue
	 * @deprecated use method with 4 parameters, for specifying if double-quotes
	 *             are required or not..
	 */
	@Deprecated
	public static void addAnnotationParameter(Annotation annotation, String propertyName, String propertyValue) {
		StringLiteral propertyExpression = JavaFactory.eINSTANCE.createStringLiteral();
		propertyExpression.setEscapedValue("\"" + propertyValue + "\"");
		addAnnotationParameter(annotation, propertyName, propertyExpression);
	}

	/**
	 * Adds a new object value to an annotation property. Note that is the
	 * annotation already has a valued property with the same name, the value of
	 * the property will be updated with an array
	 * 
	 * @param annotation
	 * @param propertyName
	 * @param propertyValue
	 * @deprecated use method with 4 parameters, for specifying if double-quotes
	 *             are required or not.
	 */
	@Deprecated
	public static void addAnnotationParameter(Annotation annotation, String propertyName, Object propertyValue) {
		StringLiteral propertyExpression = JavaFactory.eINSTANCE.createStringLiteral();
		propertyExpression.setEscapedValue(String.valueOf(propertyValue));
		addAnnotationParameter(annotation, propertyName, propertyExpression);
	}

	/**
	 * Adds a new object value to an annotation property. Note that is the
	 * annotation already has a valued property with the same name, the value of
	 * the property will be updated with an array
	 * 
	 * @param annotation
	 * @param propertyName
	 * @param propertyValue
	 * @param escape
	 *            if true, value generated in double quotes
	 * @deprecated use the add annotation parameter method in JavaAnnotation in
	 *             the net.atos.optimus.m2m.javaxmi.operation package.
	 */
	@Deprecated
	public static void addAnnotationParameter(Annotation annotation, String propertyName, Object propertyValue,
			boolean escape) {
		if (annotation == null) {
			return;
		}
		JavaAnnotation javaAnnotation = new JavaAnnotation(annotation);
		javaAnnotation.addAnnotationParameter(propertyName, propertyValue, escape);
		// StringLiteral propertyExpression =
		// JavaFactory.eINSTANCE.createStringLiteral();
		// String value = (escape ? "\"" : "") + String.valueOf(propertyValue) +
		// (escape ? "\"" : "");
		// propertyExpression.setEscapedValue(value);
		// addAnnotationParameter(annotation, propertyName, propertyExpression);
	}

	/**
	 * Adds a new value to an annotation property. Note that is the annotation
	 * already has a valued property with the same name, the value of the
	 * property will be updated with an array
	 * 
	 * @param annotation
	 * @param propertyName
	 * @param propertyExpression
	 */
	private static void addAnnotationParameter(Annotation annotation, String propertyName, Expression propertyExpression) {

		// If no annotation specified, returns,
		if (annotation == null || annotation.getType() == null)
			return;

		// Retrieves the annotation type, so the declaration can be updated with
		// new property
		AnnotationTypeDeclaration annotationDeclaration = (AnnotationTypeDeclaration) annotation.getType().getType();
		getTypeMemberDeclaration(annotationDeclaration, propertyName);

		// Checks in the annotation if pair with name already exists in
		// annotation
		AnnotationMemberValuePair mvp = null;
		for (Iterator<AnnotationMemberValuePair> iterator = annotation.getValues().iterator(); iterator.hasNext()
				&& mvp == null;) {
			AnnotationMemberValuePair tempMvp = iterator.next();
			if (propertyName == null ? tempMvp.getName() == null : propertyName.equals(tempMvp.getName())) {
				mvp = tempMvp;
			}
		}

		// If no property found before, it means we have to create a new one
		if (mvp == null) {
			mvp = JavaFactory.eINSTANCE.createAnnotationMemberValuePair();
			mvp.setName(propertyName);
			annotation.getValues().add(mvp);
		}

		// Initializes expression, and set it to pair. Note that if pair has
		// value and is an array, we add the value directly. If value is not an
		// array, we turn it into an array to allow the addition of new value

		if (mvp.getValue() != null) {
			ArrayInitializer arrayInitializer = null;
			Expression value = mvp.getValue();
			if (value instanceof ArrayInitializer) {
				arrayInitializer = (ArrayInitializer) value;
			} else {
				arrayInitializer = JavaFactory.eINSTANCE.createArrayInitializer();
				mvp.setValue(arrayInitializer);
				arrayInitializer.getExpressions().add(value);
			}
			arrayInitializer.getExpressions().add(propertyExpression);
		} else
			mvp.setValue(propertyExpression);

		AnnotationTypeMemberDeclaration atmd = null;
		for (Iterator<BodyDeclaration> iterator = annotationDeclaration.getBodyDeclarations().iterator(); iterator
				.hasNext() && atmd == null;) {
			BodyDeclaration d = iterator.next();
			if (d instanceof AnnotationTypeMemberDeclaration && d.getName().equals(mvp.getName()))
				atmd = (AnnotationTypeMemberDeclaration) d;
		}
		if (atmd != null)
			mvp.setMember(atmd);

	}

	/**
	 * Adds a new property definition of the annotation declaration
	 * 
	 * @param annotationDeclaration
	 * @param propertyName
	 * @return
	 */
	private static AnnotationTypeMemberDeclaration getTypeMemberDeclaration(
			AnnotationTypeDeclaration annotationDeclaration, String propertyName) {

		if (annotationDeclaration != null) {
			EList<BodyDeclaration> bodyDeclarations = annotationDeclaration.getBodyDeclarations();
			for (BodyDeclaration bodyDeclaration : bodyDeclarations)
				if (bodyDeclaration instanceof AnnotationTypeMemberDeclaration) {
					AnnotationTypeMemberDeclaration atmd = (AnnotationTypeMemberDeclaration) bodyDeclaration;
					if (propertyName.equals(atmd.getName()))
						return atmd;
				}
		}

		AnnotationTypeMemberDeclaration typeMemberDeclaration = JavaFactory.eINSTANCE
				.createAnnotationTypeMemberDeclaration();
		typeMemberDeclaration.setName(propertyName);
		if (annotationDeclaration != null)
			typeMemberDeclaration.setAbstractTypeDeclaration(annotationDeclaration);
		return typeMemberDeclaration;
	}

	/**
	 * Adds an annotation to a body declaration
	 * 
	 * @param bodyDeclaration
	 * @param packageName
	 * @param annotationName
	 * @return
	 * @deprecated use the add annotation method in AbstractDeclaration in the
	 *             net.atos.optimus.m2m.javaxmi.operation package.
	 */
	@Deprecated
	public static Annotation addAnnotation(BodyDeclaration bodyDeclaration, String packageName, String annotationName) {
		return (new AbstractDeclaration<BodyDeclaration>(bodyDeclaration)).addAnnotation(packageName, annotationName)
				.getDelegate();

		// CompilationUnit compilationUnit = bodyDeclaration == null ? null :
		// bodyDeclaration.getOriginalCompilationUnit();
		//
		// AnnotationTypeDeclaration annotationDeclaration = compilationUnit !=
		// null ? getAnnotation(bodyDeclaration,
		// packageName, annotationName) : getOrphanAnnotation(bodyDeclaration,
		// packageName + "." + annotationName);
		//
		// // Now, we create a new annotation instance and set it on the body
		// // declaration.
		// Annotation annotation = JavaFactory.eINSTANCE.createAnnotation();
		// annotation.setOriginalCompilationUnit(compilationUnit);
		// TypeAccess typeAccess = JavaFactory.eINSTANCE.createTypeAccess();
		// typeAccess.setType(annotationDeclaration);
		// annotation.setType(typeAccess);
		//
		// if (bodyDeclaration != null) {
		// bodyDeclaration.getAnnotations().add(annotation);
		// addMissingImport(annotationDeclaration, bodyDeclaration);
		// }
		//
		// return annotation;
	}

	/**
	 * Adds annotation in the array initializer of another annotation
	 * 
	 * @param arrayInitializer
	 * @param packageName
	 * @param annotationName
	 * @return
	 * @deprecated use the add annotation method in ArrayInitializerInstruction
	 *             in the net.atos.optimus.m2m.javaxmi.operation package.
	 */
	@Deprecated
	public static Annotation addAnnotation(ArrayInitializer arrayInitializer, String packageName, String annotationName) {
		return (new ArrayInitializerInstruction(arrayInitializer)).addAnnotation(packageName, annotationName)
				.getDelegate();
		// CompilationUnit compilationUnit =
		// arrayInitializer.getOriginalCompilationUnit();
		//
		// AnnotationTypeDeclaration annotationDeclaration = compilationUnit !=
		// null ? getAnnotation(arrayInitializer,
		// packageName, annotationName)
		// : getOrphanAnnotation(arrayInitializer, packageName + "." +
		// annotationName);
		//
		// // Now, we create a new annotation instance and set it on the body
		// // declaration.
		// Annotation annotation = JavaFactory.eINSTANCE.createAnnotation();
		// annotation.setOriginalCompilationUnit(arrayInitializer.getOriginalCompilationUnit());
		// TypeAccess typeAccess = JavaFactory.eINSTANCE.createTypeAccess();
		// typeAccess.setType(annotationDeclaration);
		// annotation.setType(typeAccess);
		// arrayInitializer.getExpressions().add(annotation);
		//
		// if (compilationUnit != null)
		// addMissingImport(annotationDeclaration, compilationUnit);
		//
		// return annotation;
	}

	/**
	 * Adds annotation in a Single Variable Declaration of another annotation
	 * 
	 * @param singleVariableDeclaration
	 * @param packageName
	 * @param annotationName
	 * @return
	 * @deprecated use the add annotation method in Parameter in the
	 *             net.atos.optimus.m2m.javaxmi.operation package.
	 */
	@Deprecated
	public static Annotation addAnnotation(SingleVariableDeclaration singleVariableDeclaration, String packageName,
			String annotationName) {
		return (new Parameter(singleVariableDeclaration)).addAnnotation(packageName, annotationName).getDelegate();
		// CompilationUnit compilationUnit =
		// singleVariableDeclaration.getOriginalCompilationUnit();
		//
		// AnnotationTypeDeclaration annotationDeclaration = compilationUnit !=
		// null ? getAnnotation(
		// singleVariableDeclaration, packageName, annotationName) :
		// getOrphanAnnotation(
		// singleVariableDeclaration, packageName + "." + annotationName);
		//
		// // Now, we create a new annotation instance and set it on the body
		// // declaration.
		// Annotation annotation = JavaFactory.eINSTANCE.createAnnotation();
		// annotation.setOriginalCompilationUnit(singleVariableDeclaration.getOriginalCompilationUnit());
		// TypeAccess typeAccess = JavaFactory.eINSTANCE.createTypeAccess();
		// typeAccess.setType(annotationDeclaration);
		// annotation.setType(typeAccess);
		// singleVariableDeclaration.getAnnotations().add(annotation);
		//
		// if (compilationUnit != null)
		// addMissingImport(annotationDeclaration, compilationUnit);
		//
		// return annotation;
	}

	/**
	 * Retrieves in a node, an annotation with provided package and annotation
	 * name
	 * 
	 * @param node
	 * @param packageName
	 * @param annotationName
	 * @return
	 */
	// private static AnnotationTypeDeclaration getAnnotation(ASTNode node,
	// String packageName, String annotationName) {
	//
	// Model model = getModel(node);
	//
	// // Find recursively the package in the model. Creates chunks i
	// Package annotationPackage = getPackage(model, packageName);
	//
	// // Now in the package, find the Annotation
	// AnnotationTypeDeclaration generatedAnnotation = null;
	//
	// for (Iterator<AbstractTypeDeclaration> declarationIterator =
	// annotationPackage.getOwnedElements().iterator(); declarationIterator
	// .hasNext() && generatedAnnotation == null;) {
	// AbstractTypeDeclaration next = declarationIterator.next();
	// if (next instanceof AnnotationTypeDeclaration &&
	// annotationName.equals(next.getName()))
	// generatedAnnotation = (AnnotationTypeDeclaration) next;
	// }
	//
	// // If annotation does not exist, we create it
	// if (generatedAnnotation == null) {
	// generatedAnnotation =
	// JavaFactory.eINSTANCE.createAnnotationTypeDeclaration();
	// generatedAnnotation.setName(annotationName);
	// generatedAnnotation.setPackage(annotationPackage);
	// generatedAnnotation.setProxy(true);
	// }
	//
	// // And we return the retrieved or created element.
	// return generatedAnnotation;
	//
	// }

	// private static Model getModel(ASTNode node) {
	// // Retrieves the root Model
	// Model model = null;
	// EObject temp = node;
	// while (model == null && temp != null) {
	// if (temp instanceof Model)
	// model = (Model) temp;
	// else
	// temp = temp.eContainer();
	// }
	//
	// return model;
	// }

	// private static AnnotationTypeDeclaration getOrphanAnnotation(ASTNode
	// node, String fullyQualifiedName) {
	// Model model = getModel(node);
	//
	// UnresolvedAnnotationDeclaration annotation = null;
	// if (model != null) {
	// for (Iterator<Type> iterator = model.getOrphanTypes().iterator();
	// iterator.hasNext() && annotation == null;) {
	// Type orphanType = iterator.next();
	// if (orphanType instanceof UnresolvedAnnotationDeclaration
	// && fullyQualifiedName.equals(orphanType.getName()))
	// annotation = (UnresolvedAnnotationDeclaration) orphanType;
	// }
	// }
	//
	// if (annotation == null) {
	// annotation =
	// JavaFactory.eINSTANCE.createUnresolvedAnnotationDeclaration();
	// annotation.setName(fullyQualifiedName);
	// if (model != null)
	// model.getOrphanTypes().add(annotation);
	// }
	//
	// return annotation;
	//
	// }

	/**
	 * Utility method to find a package in a model with provided chunks. Creates
	 * it if not exists
	 * 
	 * @param model
	 * @param packageChunks
	 * @return
	 */
	// private static Package getPackage(Model model, String packageName) {
	// String[] packageChunks = packageName.split("\\.");
	// Package temp = null;
	//
	// // At first, look in model to retrieve package corresponding to the
	// // first chunk.
	// for (Iterator<Package> iteratorOnModelPackages =
	// model.getOwnedElements().iterator(); iteratorOnModelPackages
	// .hasNext() && temp == null;) {
	// Package next = iteratorOnModelPackages.next();
	// if (packageChunks[0].equals(next.getName()))
	// temp = next;
	// }
	//
	// // If package in model does not exist, create it
	// if (temp == null) {
	// temp = JavaFactory.eINSTANCE.createPackage();
	// temp.setModel(model);
	// temp.setName(packageChunks[0]);
	// }
	//
	// // Now, for each other chunk
	// for (int i = 1; i < packageChunks.length; i++) {
	// Package childPackage = null;
	//
	// // Check if sub package exists
	// for (Iterator<Package> iteratorOnSubPackages =
	// temp.getOwnedPackages().iterator(); iteratorOnSubPackages
	// .hasNext() && childPackage == null;) {
	// Package next = iteratorOnSubPackages.next();
	// if (packageChunks[i].equals(next.getName()))
	// childPackage = next;
	// }
	//
	// // If not the case, create it
	// if (childPackage == null) {
	// childPackage = JavaFactory.eINSTANCE.createPackage();
	// childPackage.setPackage(temp);
	// childPackage.setName(packageChunks[i]);
	// }
	//
	// // Set child package as current, and continue to next chunk !
	// temp = childPackage;
	// }
	//
	// return temp;
	// }

	/**
	 * If body declaration's compilation unit doesn't have import of annotation
	 * declaration, add one
	 * 
	 * @param generatedAnnotation
	 * @param bodyDeclaration
	 */
	// private static void addMissingImport(AnnotationTypeDeclaration
	// generatedAnnotation, BodyDeclaration bodyDeclaration) {
	// AbstractTypeDeclaration atd = null;
	//
	// if (bodyDeclaration instanceof AbstractTypeDeclaration) {
	// atd = (AbstractTypeDeclaration) bodyDeclaration;
	// } else if (bodyDeclaration instanceof EnumConstantDeclaration) {
	// atd = ((AbstractTypeDeclaration) bodyDeclaration.eContainer());
	// } else {
	// atd = bodyDeclaration.getAbstractTypeDeclaration();
	// }
	//
	// if (atd == null)
	// return;
	//
	// CompilationUnit compilationUnit = atd.getOriginalCompilationUnit();
	//
	// if (compilationUnit != null)
	// addMissingImport(generatedAnnotation, compilationUnit);
	//
	// }

	/**
	 * Adds import (if missing) in the compilation unit, for the provided
	 * declaration
	 * 
	 * @param generatedAnnotation
	 * @param compilationUnit
	 */
	// private static void addMissingImport(AnnotationTypeDeclaration
	// generatedAnnotation, CompilationUnit compilationUnit) {
	//
	// if (generatedAnnotation instanceof UnresolvedAnnotationDeclaration ||
	// compilationUnit == null)
	// return;
	//
	// boolean hasMatchingImport = false;
	// EList<ImportDeclaration> imports = compilationUnit.getImports();
	// for (Iterator<ImportDeclaration> importIterator = imports.iterator();
	// importIterator.hasNext()
	// && hasMatchingImport == false;) {
	// ImportDeclaration i = importIterator.next();
	// if (i.getImportedElement().equals(generatedAnnotation))
	// hasMatchingImport = true;
	// }
	//
	// if (!hasMatchingImport) {
	// ImportDeclaration declaration =
	// JavaFactory.eINSTANCE.createImportDeclaration();
	// declaration.setImportedElement(generatedAnnotation);
	// compilationUnit.getImports().add(declaration);
	// }
	//
	// }

}
