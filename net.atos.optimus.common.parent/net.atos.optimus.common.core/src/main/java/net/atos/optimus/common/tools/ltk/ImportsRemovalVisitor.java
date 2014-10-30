package net.atos.optimus.common.tools.ltk;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.SimpleType;

public class ImportsRemovalVisitor extends ASTVisitor {

	private ImportsRemovalVisitor() {
	}

	private boolean modified = false;

	public static boolean apply(CompilationUnit compilationUnit) {
		ImportsRemovalVisitor importsRemovalVisitor = new ImportsRemovalVisitor();
		compilationUnit.accept(importsRemovalVisitor);
		return importsRemovalVisitor.modified;
	}

	@Override
	public boolean visit(CompilationUnit node) {
		return node.imports().size() > 0;
	}

	@Override
	public boolean visit(SimpleType node) {
		ITypeBinding resolvedBinding = node.resolveBinding();
		if (resolvedBinding == null || resolvedBinding.isRecovered()) {
			return true;
		}

		if (resolvedBinding.isParameterizedType()) {
			// TODO
		} else {
			String qualifiedName = resolvedBinding.getQualifiedName();
			node.setName(node.getAST().newName(qualifiedName));
		}
		this.modified = true;
		return true;
	}

	@Override
	public void endVisit(CompilationUnit node) {
		node.imports().clear();
	}

}
