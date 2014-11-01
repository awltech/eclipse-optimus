package net.atos.optimus.common.tools.ltk;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.ChildListPropertyDescriptor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.StructuralPropertyDescriptor;

/**
 * Implementation of AST visitor that parses source code and refactors it to
 * puts fully qualified names everywhere & remove the imports.
 * 
 * @author mvanbesien (mvaawl@gmail.com)
 *
 */
public class ImportsRemovalVisitor extends ASTVisitor {

	/*
	 * To prevent external instanciation. Use apply method instead
	 */
	private ImportsRemovalVisitor() {
	}

	/**
	 * True if source code has been modified here
	 */
	private boolean modified = false;

	/**
	 * Instanciates a visitor and executes it against the provided compilation
	 * unit.
	 * 
	 * @param compilationUnit
	 * @return true if source has been modified during process.
	 */
	public static boolean apply(CompilationUnit compilationUnit) {
		ImportsRemovalVisitor importsRemovalVisitor = new ImportsRemovalVisitor();
		compilationUnit.accept(importsRemovalVisitor);
		return importsRemovalVisitor.modified;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.
	 * CompilationUnit)
	 */
	@Override
	public boolean visit(CompilationUnit node) {
		// We assume there is nothing to do if there are no imports in the
		// source code...
		return node.imports().size() > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.SimpleType
	 * )
	 */
	@Override
	public boolean visit(SimpleType node) {
		ITypeBinding resolvedBinding = node.resolveBinding();
		if (resolvedBinding == null) {
			return true;
		}

		String qualifiedName = null;

		if (resolvedBinding.isParameterizedType()) {
			ITypeBinding erasure = resolvedBinding.getErasure();
			if (erasure != null && !erasure.isRecovered()) {
				qualifiedName = erasure.getQualifiedName();
			}
		} else if (!resolvedBinding.isRecovered()) {
			qualifiedName = resolvedBinding.getQualifiedName();
		}

		if (qualifiedName != null) {
			node.setName(node.getAST().newName(qualifiedName));
			this.modified = true;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.
	 * MethodInvocation)
	 */
	@Override
	public boolean visit(MethodInvocation node) {
		// For the static methods invocation
		IMethodBinding resolvedMethodBinding = node.resolveMethodBinding();
		if (resolvedMethodBinding != null && !resolvedMethodBinding.isRecovered()
				&& Modifier.isStatic(resolvedMethodBinding.getModifiers())) {
			Expression expression = node.getExpression();
			if (expression instanceof SimpleName) {
				ITypeBinding typeBinding = expression.resolveTypeBinding();
				overrideValueInParent(expression, typeBinding);
			}
		}

		return super.visit(node);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.
	 * QualifiedName)
	 */
	@Override
	public boolean visit(QualifiedName node) {
		// For the static fields & enumeration literals invocation
		if (node.resolveBinding() instanceof IVariableBinding) {
			IVariableBinding binding = (IVariableBinding) node.resolveBinding();
			if (!binding.isRecovered() && Modifier.isStatic(binding.getModifiers())) {
				if (node.getQualifier() instanceof SimpleName) {
					ITypeBinding typeBinding = node.getQualifier().resolveTypeBinding();
					Name qualifier = node.getQualifier();
					overrideValueInParent(qualifier, typeBinding);
				}
			}
		}
		return super.visit(node);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.
	 * MarkerAnnotation)
	 */
	@Override
	public boolean visit(MarkerAnnotation node) {
		return visitAnnotation(node);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.
	 * SingleMemberAnnotation)
	 */
	@Override
	public boolean visit(SingleMemberAnnotation node) {
		return visitAnnotation(node);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.
	 * NormalAnnotation)
	 */
	@Override
	public boolean visit(NormalAnnotation node) {
		return visitAnnotation(node);
	}

	/**
	 * Processes the annotation to replace its name by FQN
	 * 
	 * @param node
	 * @return
	 */
	private boolean visitAnnotation(Annotation node) {
		if (node.getTypeName().isSimpleName()) {
			ITypeBinding resolvedBinding = node.resolveTypeBinding();
			if (resolvedBinding != null) {
				String qualifiedName = null;

				if (resolvedBinding.isParameterizedType()) {
					ITypeBinding erasure = resolvedBinding.getErasure();
					if (erasure != null && !erasure.isRecovered()) {
						qualifiedName = erasure.getQualifiedName();
					}
				} else if (!resolvedBinding.isRecovered()) {
					qualifiedName = resolvedBinding.getQualifiedName();
				}
				if (qualifiedName != null) {
					node.setTypeName(node.getAST().newName(qualifiedName));
					this.modified = true;
				}
			}
		}
		return true;
	}

	/**
	 * This method is called when it is necessary to remove a name from a parent
	 * AST node.
	 * 
	 * @param node
	 * @param typeBinding
	 */
	@SuppressWarnings("unchecked")
	private void overrideValueInParent(ASTNode node, ITypeBinding typeBinding) {
		String qualifiedName = null;
		if (typeBinding.isParameterizedType()) {
			ITypeBinding erasure = typeBinding.getErasure();
			if (erasure != null && !erasure.isRecovered()) {
				qualifiedName = erasure.getQualifiedName();
			}
		} else if (!typeBinding.isRecovered()) {
			qualifiedName = typeBinding.getQualifiedName();
		}

		if (qualifiedName != null) {
			StructuralPropertyDescriptor locationInParent = node.getLocationInParent();
			if (locationInParent.isChildListProperty()) {
				ChildListPropertyDescriptor clpd = (ChildListPropertyDescriptor) locationInParent;
				List<ASTNode> astNodes = (List<ASTNode>) node.getParent().getStructuralProperty(clpd);
				try {
					int index = astNodes.indexOf(node);
					astNodes.remove(node);
					astNodes.add(index, node.getAST().newName(qualifiedName));
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				try {
					node.getParent().setStructuralProperty(locationInParent, node.getAST().newName(qualifiedName));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			this.modified = true;
		}
	}

	@Override
	public boolean visit(SimpleName node) {
		ITypeBinding resolveTypeBinding = node.resolveTypeBinding();
		if (resolveTypeBinding != null && !resolveTypeBinding.isRecovered()) {
			System.out.print(node + " ==> " + node.getParent().getClass());
			System.out.println();
		}
		return super.visit(node);
	}

	@Override
	public boolean visit(MethodDeclaration node) {
		List<?> thrownExceptions = new ArrayList<Object>();
		thrownExceptions.addAll(node.thrownExceptions());
		for (Object o : thrownExceptions) {
			if (o instanceof SimpleName) {
				SimpleName name = (SimpleName) o;
				overrideValueInParent(name, name.resolveTypeBinding());
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jdt.core.dom.ASTVisitor#endVisit(org.eclipse.jdt.core.dom
	 * .CompilationUnit)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void endVisit(CompilationUnit node) {
		List<ImportDeclaration> toRemove = new ArrayList<ImportDeclaration>();
		for (Object o : node.imports()) {
			ImportDeclaration declaration = (ImportDeclaration) o;
			IBinding resolvedBinding = declaration.resolveBinding();
			if (resolvedBinding != null && !resolvedBinding.isRecovered()) {
				toRemove.add(declaration);
			}
		}
		// To clean the imports from the source code.
		node.imports().removeAll(toRemove);
	}

}
