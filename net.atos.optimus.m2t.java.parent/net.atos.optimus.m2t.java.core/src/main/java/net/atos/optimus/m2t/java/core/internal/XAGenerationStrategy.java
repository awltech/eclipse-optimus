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
package net.atos.optimus.m2t.java.core.internal;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;

import net.atos.optimus.m2t.java.core.IPostGenerationVetoStrategy;
import net.atos.optimus.m2t.java.core.IPostProcessor;

import org.eclipse.acceleo.engine.AcceleoEngineMessages;
import org.eclipse.acceleo.engine.AcceleoEnginePlugin;
import org.eclipse.acceleo.engine.AcceleoEvaluationException;
import org.eclipse.acceleo.engine.generation.strategy.DefaultStrategy;
import org.eclipse.acceleo.engine.generation.strategy.IAcceleoGenerationStrategy;
import org.eclipse.acceleo.engine.generation.writers.AbstractAcceleoWriter;

/**
 * XA Generation Strategy It will work as the default strategy, but with support
 * of post processors.
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 * 
 */
public class XAGenerationStrategy extends DefaultStrategy {

	/**
	 * List of post processors
	 */
	private Collection<IPostProcessor> postProcessors = new ArrayList<IPostProcessor>();

	/**
	 * Veto Strategy
	 */
	private IPostGenerationVetoStrategy vetoStrategy;

	/**
	 * Creates new strategy with provided post processors.
	 * 
	 * @param postProcessors
	 */
	public XAGenerationStrategy(Collection<IPostProcessor> postProcessors, IPostGenerationVetoStrategy vetoStrategy) {
		this.postProcessors = postProcessors;
		this.vetoStrategy = vetoStrategy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.acceleo.engine.generation.strategy.AbstractGenerationStrategy
	 * #createWriterFor(java.io.File,
	 * org.eclipse.acceleo.engine.generation.writers.AbstractAcceleoWriter,
	 * boolean, boolean, java.lang.String)
	 */
	@Override
	public AbstractAcceleoWriter createWriterFor(File file, AbstractAcceleoWriter previous, boolean appendMode,
			boolean hasJMergeTags, String charset) throws IOException {
		final AbstractAcceleoWriter writer;
		if (!file.getParentFile().exists())
			if (!file.getParentFile().mkdirs())
				throw new AcceleoEvaluationException(AcceleoEngineMessages.getString(
						"AcceleoEvaluationContext.FolderCreationError", file.getParentFile())); //$NON-NLS-1$

		if (file.isDirectory())
			throw new AcceleoEvaluationException(AcceleoEngineMessages.getString(
					"AcceleoEvaluationContext.FileNameIsDirectory", file)); //$NON-NLS-1$

		boolean fileExisted = file.exists();

		if (!hasJMergeTags || appendMode) {
			if (charset != null) {
				if (Charset.isSupported(charset)) {
					writer = new XAAcceleoWriter(file, appendMode, charset);
					((XAAcceleoWriter) writer).setPostProcessors(this.postProcessors).setVetoStrategy(vetoStrategy);
				} else {
					final String message = AcceleoEngineMessages.getString(
							"AcceleoGenerationStrategy.UnsupportedCharset", charset); //$NON-NLS-1$
					AcceleoEnginePlugin.log(message, false);
					writer = new XAAcceleoWriter(file, appendMode);
					((XAAcceleoWriter) writer).setPostProcessors(this.postProcessors).setVetoStrategy(vetoStrategy);
				}
			} else {
				writer = new XAAcceleoWriter(file, appendMode);
				((XAAcceleoWriter) writer).setPostProcessors(this.postProcessors).setVetoStrategy(vetoStrategy);
			}
			if (appendMode && fileExisted)
				writer.append(IAcceleoGenerationStrategy.LINE_SEPARATOR);
		} else {
			writer = new XAAcceleoWriter(file.getPath());
			((XAAcceleoWriter) writer).setPostProcessors(this.postProcessors).setVetoStrategy(vetoStrategy);
		}
		return writer;
	}

}
