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
package net.atos.optimus.common.tools.jdt;

import java.util.Map;

import net.atos.optimus.common.tools.Activator;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.TextEdit;

/**
 * Tools class used to format generated code.
 *
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 */
public class XACodeFormatter {

	/**
	 * Format a String . Use the default code formatter
	 *
	 * @param sourceToFormat
	 *            String to format
	 *
	 */
	public static String format(String inputSource, IJavaProject project) {

		Map<?, ?> options = project != null ? project.getOptions(true)
				: JavaCore.getOptions();

		String sourceToFormat = preformat(inputSource);

		CodeFormatter formatter = ToolFactory.createCodeFormatter(options, ToolFactory.M_FORMAT_EXISTING);
		IDocument document = new Document(sourceToFormat);

		TextEdit textEdit = formatter.format(CodeFormatter.K_COMPILATION_UNIT
				| CodeFormatter.F_INCLUDE_COMMENTS , sourceToFormat, 0,
				sourceToFormat.length(), 0, null);

		if (textEdit != null) {
			try {
				textEdit.apply(document);
				String destination = document.get();
				return destination;
			} catch (Exception e) {
				Activator.getDefault().logError(
						"The compilation unit could not be"
								+ " read because of thrown Exception.", e);
				// In this case, return initial content
				return sourceToFormat;
			}
		} else {
			// In this case, return initial content
			return sourceToFormat;
		}
	}

	private static String preformat(String inputSource) {
		String preformatedInputSource = inputSource.replace("\r","");
		preformatedInputSource = preformatedInputSource.replaceAll("\n\\s*\n", "\n");
		return preformatedInputSource;
	}
}
