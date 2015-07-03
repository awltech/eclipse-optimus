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
package net.atos.optimus.m2m.javaxmi.operation.element;

import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import net.atos.optimus.m2m.javaxmi.operation.comments.LineCommentBuilder;
import net.atos.optimus.m2m.javaxmi.operation.javadoc.JavadocBuilder;
import net.atos.optimus.m2m.javaxmi.operation.javadoc.TagElementBuilder;
import net.atos.optimus.m2m.javaxmi.operation.javadoc.TextElementBuilder;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.Javadoc;
import org.eclipse.gmt.modisco.java.LineComment;
import org.eclipse.gmt.modisco.java.TagElement;

/**
 * Models the model root : wrapper of ASTNode in modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class Element<T extends ASTNode> implements EObject {

	public static final String BEGIN_COMMENT = "//";

	private T astNode;

	public T getDelegate() {
		return this.astNode;
	}

	/**
	 * Constructor of element
	 * 
	 * @param astNode
	 *            the AST node.
	 */
	public Element(T astNode) {
		this.astNode = astNode;
	}

	/**
	 * Adds a {@link Javadoc} to the current element. Javadoc's content is
	 * provided as a {@link String}, and it's possible to provide multiple lines
	 * by using the
	 * 
	 * <pre>
	 * \n
	 * </pre>
	 * 
	 * separator in the String. This feature lets you provide the String in a
	 * standard Java way.
	 * 
	 * <i>Tip: The {@link String} is parsed using the
	 * 
	 * <pre>
	 * \n
	 * </pre>
	 * 
	 * separator so you can easily use something like a {@link PrintWriter} to
	 * create the javadoc content. </i>
	 * 
	 * @param documentation
	 *            the javadoc's content
	 * @return the current element.
	 */
	public Element<T> addJavadoc(String documentation, boolean addEmptyLine) {
		Javadoc javadoc = JavadocBuilder.builder().setPrefixOfParent(true).build();
		EList<TagElement> tags = javadoc.getTags();

		if (documentation != null) {
			for (String documentationChunk : documentation.split("\\n")) {
				if (addEmptyLine) {
					tags.add(TagElementBuilder
							.builder()
							.addFragments(TextElementBuilder.builder().setText(documentationChunk).build(),
									TextElementBuilder.builder().setText("").build()).build());
				} else {
					tags.add(TagElementBuilder.builder()
							.addFragments(TextElementBuilder.builder().setText(documentationChunk).build()).build());
				}
			}
		}

		if (this.astNode != null) {
			astNode.getComments().add(javadoc);
		}

		return this;
	}

	/**
	 * Add a comment to the current element
	 * 
	 * @param commentText
	 *            the text of the comment.
	 * @param commentText
	 * @param prefixOfParent
	 *            the prefix of parent state of the comment.
	 * @return the current element.
	 */
	public Element<T> addComment(String commentText, boolean prefixOfParent) {
		if (!commentText.startsWith(Element.BEGIN_COMMENT)) {
			commentText = Element.BEGIN_COMMENT + commentText;
		}
		LineComment comment = LineCommentBuilder.builder().setContent(commentText).setPrefixOfParent(prefixOfParent)
				.build();
		this.astNode.getComments().add(comment);
		return this;
	}

	@Override
	public EList<Adapter> eAdapters() {
		return this.astNode.eAdapters();
	}

	@Override
	public boolean eDeliver() {
		return this.astNode.eDeliver();
	}

	@Override
	public void eSetDeliver(boolean deliver) {
		this.astNode.eSetDeliver(deliver);
	}

	@Override
	public void eNotify(Notification notification) {
		this.astNode.eNotify(notification);
	}

	@Override
	public EClass eClass() {
		return this.astNode.eClass();
	}

	@Override
	public Resource eResource() {
		return this.astNode.eResource();
	}

	@Override
	public EObject eContainer() {
		return this.astNode.eContainer();
	}

	@Override
	public EStructuralFeature eContainingFeature() {
		return this.astNode.eContainingFeature();
	}

	@Override
	public EReference eContainmentFeature() {
		return this.astNode.eContainmentFeature();
	}

	@Override
	public EList<EObject> eContents() {
		return this.astNode.eContents();
	}

	@Override
	public TreeIterator<EObject> eAllContents() {
		return this.astNode.eAllContents();
	}

	@Override
	public boolean eIsProxy() {
		return this.astNode.eIsProxy();
	}

	@Override
	public EList<EObject> eCrossReferences() {
		return this.astNode.eCrossReferences();
	}

	@Override
	public Object eGet(EStructuralFeature feature) {
		return this.astNode.eGet(feature);
	}

	@Override
	public Object eGet(EStructuralFeature feature, boolean resolve) {
		return this.astNode.eGet(feature, resolve);
	}

	@Override
	public void eSet(EStructuralFeature feature, Object newValue) {
		this.astNode.eSet(feature, newValue);
	}

	@Override
	public boolean eIsSet(EStructuralFeature feature) {
		return this.astNode.eIsSet(feature);
	}

	@Override
	public void eUnset(EStructuralFeature feature) {
		this.astNode.eUnset(feature);
	}

	@Override
	public Object eInvoke(EOperation operation, EList<?> arguments) throws InvocationTargetException {
		return this.astNode.eInvoke(operation, arguments);
	}
}
