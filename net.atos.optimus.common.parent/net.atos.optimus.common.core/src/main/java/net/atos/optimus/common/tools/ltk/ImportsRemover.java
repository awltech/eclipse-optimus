package net.atos.optimus.common.tools.ltk;

import org.eclipse.jdt.core.dom.CompilationUnit;

public class ImportsRemover extends AbstractImportsManager {

	@Override
	protected boolean apply(CompilationUnit compilationUnit) {
		return ImportsRemovalVisitor.apply(compilationUnit);
	}

}
