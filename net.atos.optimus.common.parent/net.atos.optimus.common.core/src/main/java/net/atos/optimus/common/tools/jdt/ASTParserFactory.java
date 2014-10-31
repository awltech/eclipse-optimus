package net.atos.optimus.common.tools.jdt;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;

/**
 * Singleton that wraps the AST.JLS & Java versions mapping. The aim here is to
 * be able to retrieve the JLS value according to the corresponding project or
 * to get the highest possible !
 * 
 * @author mvanbesien (mvaawl@gmail.com)
 * @since 1.1
 *
 */
public enum ASTParserFactory {

	/**
	 * Singleton instance
	 */
	INSTANCE;

	/**
	 * @see JavaCore.VERSION_1_8
	 */
	private static final String JAVA8 = "1.8";

	/**
	 * @see JavaCore.VERSION_1_7
	 */
	private static final String JAVA7 = "1.7";

	/**
	 * @see JavaCore.VERSION_1_6
	 */
	private static final String JAVA6 = "1.6";

	/**
	 * @see JavaCore.VERSION_1_5
	 */
	private static final String JAVA5 = "1.5";

	/**
	 * @see JavaCore.VERSION_1_4
	 */
	private static final String JAVA4 = "1.4";

	/**
	 * @see JavaCore.VERSION_1_3
	 */
	private static final String JAVA3 = "1.3";

	/**
	 * @see AST.JLS2
	 */
	private static final int ASTJLS2 = 2;

	/**
	 * @see AST.JLS3
	 */
	private static final int ASTJLS3 = 3;

	/**
	 * @see AST.JLS4
	 */
	private static final int ASTJLS4 = 4;

	/**
	 * @see AST.JLS8
	 */
	private static final int ASTJLS8 = 8;

	/**
	 * Mapping between the java version and the AST level
	 */
	private Map<String, Integer> jlsMapping;

	/**
	 * Default ast level to use, if mapping is not resolveable or if no
	 * project/compilation is found
	 */
	private Integer jlsDefaultLevel;

	/*
	 * 
	 */
	private ASTParserFactory() {
		jlsMapping = new LinkedHashMap<String, Integer>();
		jlsMapping.put(ASTParserFactory.JAVA3, ASTParserFactory.ASTJLS2);
		jlsMapping.put(ASTParserFactory.JAVA4, ASTParserFactory.ASTJLS2);
		jlsMapping.put(ASTParserFactory.JAVA5, ASTParserFactory.ASTJLS3);
		jlsMapping.put(ASTParserFactory.JAVA6, ASTParserFactory.ASTJLS3);
		jlsMapping.put(ASTParserFactory.JAVA7, ASTParserFactory.ASTJLS4);
		jlsMapping.put(ASTParserFactory.JAVA8, /* ASTParserFactory.ASTJLS8 */ASTParserFactory.ASTJLS4);

		// Defines the default value of AST from taking all the values, in
		// decreasing order, and try to instanciate them as soon as one is
		// created
		int[] jlsLevelsDesc = new int[] { /* ASTParserFactory.ASTJLS8, */ASTParserFactory.ASTJLS4,
				ASTParserFactory.ASTJLS3, ASTParserFactory.ASTJLS2 };

		for (int i = 0; i < jlsLevelsDesc.length && jlsDefaultLevel == null; i++) {
			int astLevel = jlsLevelsDesc[i];
			try {
				ASTParser parser = ASTParser.newParser(astLevel);
				if (parser != null) {
					jlsDefaultLevel = astLevel;
				}
			} catch (IllegalArgumentException iae) {
				// Swallow it.
			}
		}

	}

	/**
	 * Creates a new ASTParser, with AST level corresponding as the compliance
	 * level specified in the project it is contained by.
	 * 
	 * @param compilationUnit
	 * @return
	 */
	public ASTParser newParserFor(ICompilationUnit compilationUnit) {
		return this.newParserFor(compilationUnit != null ? compilationUnit.getJavaProject() : null);
	}

	/**
	 * Creates a new ASTParser, with AST level corresponding as the compliance
	 * level specified in the project
	 * 
	 * @param javaProject
	 * @return
	 */
	public ASTParser newParserFor(IJavaProject javaProject) {

		if (javaProject != null && javaProject.exists()) {
			Map<?, ?> options = javaProject.getOptions(true);
			Object object = options.get(JavaCore.COMPILER_COMPLIANCE);
			if (object != null) {
				Integer astLevel = jlsMapping.get(object);
				if (astLevel != null) {
					try {
						return ASTParser.newParser(astLevel);
					} catch (IllegalArgumentException iae) {
						// Swallow it.
					}
				}
			}
		}

		return newParser();
	}

	/**
	 * Creates new ASTParser with highest possible JLS level.
	 * 
	 * @return
	 */
	public ASTParser newParser() {
		return ASTParser.newParser(this.jlsDefaultLevel);
	}
}
