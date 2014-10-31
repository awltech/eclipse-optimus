package net.atos.optimus.common.tools.ltk;

import org.eclipse.jdt.core.dom.CompilationUnit;

/**
 * Implementation of Imports manager, to generate imports from source code with
 * fully qualified names
 * 
 * @author mvanbesien (mvaawl@gmail.com)
 * @since 1.1
 *
 */
public class ImportsGenerator extends AbstractImportsManager {

	/*
	 * (non-Javadoc)
	 * @see net.atos.optimus.common.tools.ltk.AbstractImportsManager#apply(org.eclipse.jdt.core.dom.CompilationUnit)
	 */
	@Override
	protected boolean apply(CompilationUnit compilationUnit) {
		return ImportsGenerationVisitor.apply(compilationUnit);
	}

}
