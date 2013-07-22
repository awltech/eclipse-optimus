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
package net.atos.optimus.m2t.merger.java.core;

import static org.eclipse.jdt.core.dom.CompilationUnit.TYPES_PROPERTY;
import static org.eclipse.jdt.core.dom.EnumDeclaration.BODY_DECLARATIONS_PROPERTY;
import static org.eclipse.jdt.core.dom.EnumDeclaration.JAVADOC_PROPERTY;
import static org.eclipse.jdt.core.dom.EnumDeclaration.MODIFIERS2_PROPERTY;
import static org.eclipse.jdt.core.dom.EnumDeclaration.SUPER_INTERFACE_TYPES_PROPERTY;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.atos.optimus.common.tools.jdt.JavaCodeHelper;
import net.atos.optimus.m2t.merger.java.core.internal.MergerLogger;
import net.atos.optimus.m2t.merger.java.core.internal.MergerLoggerMessages;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.ChildListPropertyDescriptor;
import org.eclipse.jdt.core.dom.ChildPropertyDescriptor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;
import org.eclipse.jface.text.Document;


/**
 *  @author Maxence Vanbésien (mvaawl@gmail.com)
 *  @since 1.0
 */
public class EnumMerger extends BodyDeclarationMerger {

	protected JavaCodeMerger jcm = null;

	/**
	 * EnumMerger constructor
	 * 
	 * @param jcm
	 * @param astr
	 *            An AST Rewrite instance A JavaCodeMerger instance
	 * @param log
	 *            A Merger Logger instance
	 */
	public EnumMerger(JavaCodeMerger jcm, ASTRewrite astr) {
		super(astr);
		this.jcm = jcm;
	}

	@Override
	protected ChildPropertyDescriptor getJavadocPropertyDescriptor() {
		return JAVADOC_PROPERTY;
	}

	@Override
	protected ChildListPropertyDescriptor getModifiersPropertyDescriptor() {
		return MODIFIERS2_PROPERTY;
	}

	protected ChildListPropertyDescriptor getBodyDeclarationsPropertyDescriptor() {
		return BODY_DECLARATIONS_PROPERTY;
	}

	protected ChildListPropertyDescriptor getSuperInterfacesPropertyDescriptor() {
		return SUPER_INTERFACE_TYPES_PROPERTY;
	}

	@SuppressWarnings("unchecked")
	protected List<Type> getSuperInterfacesTypes(AbstractTypeDeclaration atd) {
		return ((EnumDeclaration) atd).superInterfaceTypes();
	}

	@Override
	public void insert(ASTNode parent, BodyDeclaration fragmentToInsert) {
		super.insert(parent, fragmentToInsert);

		if (parent instanceof CompilationUnit) {
			ListRewrite listRewrite = astr.getListRewrite(parent, TYPES_PROPERTY);

			if (listRewrite != null) {
				listRewrite.insertLast(fragmentToInsert, null);
			}
		} else {
			AbstractTypeDeclaration lastType = null;

			/*
			 * Read all types of the parentType type declaration (used to insert
			 * this type at the right place)
			 */
			List<AbstractTypeDeclaration> existingTypes = JavaCodeHelper.getTypedChildren(
					(AbstractTypeDeclaration) parent, AbstractTypeDeclaration.class);

			// Save the last type if a type already exist
			if (existingTypes.size() > 0) {
				lastType = existingTypes.get(existingTypes.size() - 1);
			}

			ListRewrite lw = astr.getListRewrite(parent, getBodyDeclarationsPropertyDescriptor());

			if (lastType == null) {
				// ... at the end of the type if this is the first method
				lw.insertLast(fragmentToInsert, null);
			} else {
				// ... after the previous type if a type already exist
				lw.insertAfter(fragmentToInsert, lastType, null);
			}
		}
	}

	@Override
	public void merge(BodyDeclaration existing, BodyDeclaration generated, Set<String> preDefinedAnnotations) {

		AbstractTypeDeclaration existingType = (AbstractTypeDeclaration) existing;
		AbstractTypeDeclaration generatedType = (AbstractTypeDeclaration) generated;

		mergeDeclaration(existingType, generatedType, preDefinedAnnotations);
	}

	@Override
	public void mergeSubFragments(BodyDeclaration existing, BodyDeclaration generated, Document generatedDoc) {
		EnumDeclaration existingType = (EnumDeclaration) existing;
		EnumDeclaration generatedType = (EnumDeclaration) generated;

		mergeBodyDeclarationsChildren(existingType, generatedType, generatedDoc, FieldDeclaration.class);
		mergeBodyDeclarationsChildren(existingType, generatedType, generatedDoc, MethodDeclaration.class);
		mergeBodyDeclarationsChildren(existingType, generatedType, generatedDoc, AbstractTypeDeclaration.class);
		mergeBodyDeclarationsChildren(existingType, generatedType, generatedDoc, EnumConstantDeclaration.class);
	}

	/**
	 * Merge type declaration
	 */
	protected void mergeDeclaration(AbstractTypeDeclaration existing, AbstractTypeDeclaration generated,
			Set<String> preDefinedAnnotations) {
		super.merge(existing, generated, preDefinedAnnotations);

		// Interfaces are merged
		mergeInterfaces(existing, generated);
	}

	/**
	 * Merge interfaces from existingType and generatedType to an AST Rewrite
	 * object instance.
	 * 
	 * @param et
	 *            The existing type
	 * @param generatedTYpe
	 *            The generated type
	 * @param astr
	 *            The ASTRewrite object instance containing the merge result
	 */
	protected void mergeInterfaces(AbstractTypeDeclaration et, AbstractTypeDeclaration gt) {

		List<String> existingInterfaces = new ArrayList<String>(3);

		// Create an interfaces list in String format.
		for (Type t : getSuperInterfacesTypes(et)) {
			existingInterfaces.add(JavaCodeHelper.getSimpleName(JavaCodeHelper.getName(t)));
		}

		/*
		 * Get a ListRewrite object used to modify interfaces in the existing
		 * code.
		 */
		ListRewrite lw = astr.getListRewrite(et, getSuperInterfacesPropertyDescriptor());

		// For each interfaces of the generated source
		for (Type t : getSuperInterfacesTypes(gt)) {

			if (!existingInterfaces.contains(JavaCodeHelper.getSimpleName(JavaCodeHelper.getName(t)))) {
				/*
				 * This interface is containing in the generated code but not in
				 * the existing code => Add this interface in the merge result.
				 */

				if (MergerLogger.enabled())
					MergerLogger.log(MergerLoggerMessages.ENUM_ADDINTERFACE.value(JavaCodeHelper.getName(t), JavaCodeHelper.getName(et),
							JavaCodeHelper.getDescription(et)));

				lw.insertLast(t, null);
			}
		}
	}

	/**
	 * Merge all body declaration of a Java source typed with
	 * {@code bodyDeclarationType} class.
	 */
	protected <T extends BodyDeclaration> void mergeBodyDeclarationsChildren(AbstractTypeDeclaration existingType,
			AbstractTypeDeclaration generatedType, Document generatedDoc, Class<T> bodyDeclarationType) {

		if (generatedType != null) {
			// For each typed body declaration of the generated type...
			List<T> generatedBDs = JavaCodeHelper.getTypedChildren(generatedType, bodyDeclarationType);

			for (BodyDeclaration generatedBD : generatedBDs) {
				BodyDeclaration existingBD = JavaCodeHelper.getBodyDeclaration(existingType, generatedBD);

				// if this is a method and it is not found by signature,
				// we try to make correspondance by XMI-ID (if available)
				if (existingBD == null) {
					existingBD = JavaCodeHelper.getBodyDeclarationFromUniqueId(existingType, generatedBD);
				}

				// ... call the merge process
				jcm.mergeTwoFragments(existingType, existingBD, generatedBD, generatedDoc, astr);
			}
		}

		if (existingType != null) {
			// For each typed body declaration of the existing type...
			List<T> existingBDs = JavaCodeHelper.getTypedChildren(existingType, bodyDeclarationType);

			for (BodyDeclaration existingBD : existingBDs) {
				/*
				 * ... call the merge process if the body declaration in the
				 * generated type doesn't exist
				 */
				if ((JavaCodeHelper.getBodyDeclaration(generatedType, existingBD) == null)
						&& (JavaCodeHelper.getBodyDeclarationFromUniqueId(generatedType, existingBD) == null)) {
					jcm.mergeTwoFragments(existingType, existingBD, null, generatedDoc, astr);
				}
			}
		}
	}
}