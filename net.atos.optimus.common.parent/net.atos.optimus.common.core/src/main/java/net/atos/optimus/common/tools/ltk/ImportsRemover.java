package net.atos.optimus.common.tools.ltk;

import org.eclipse.jdt.core.dom.CompilationUnit;

/**
 * Implementation of Imports manager, that removes imports and puts back fully
 * qualified named in source code.
 * 
 * @author mvanbesien (mvaawl@gmail.com)
 * @since 1.1
 *
 */
public class ImportsRemover extends AbstractImportsManager {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.atos.optimus.common.tools.ltk.AbstractImportsManager#apply(org.eclipse
	 * .jdt.core.dom.CompilationUnit)
	 */
	@Override
	protected boolean apply(CompilationUnit compilationUnit) {
		return ImportsRemovalVisitor.apply(compilationUnit);
	}

}
