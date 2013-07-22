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
import java.util.ArrayList;
import java.util.Collection;

import net.atos.optimus.m2t.java.core.FileHandler;
import net.atos.optimus.m2t.java.core.IPostGenerationVetoStrategy;
import net.atos.optimus.m2t.java.core.IPostProcessor;

import org.eclipse.acceleo.engine.generation.writers.AbstractAcceleoWriter;

/**
 * Specific Acceleo Writer. It simulates the Acceleo File Writer, but with post
 * processors support.
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 * 
 */
public class XAAcceleoWriter extends AbstractAcceleoWriter {

	/**
	 * Acceleo File Writer delegate
	 */
	private LazyAcceleoFileWriter lazyFileWriter;

	/**
	 * List of post processors.
	 */
	private Collection<IPostProcessor> postProcessors = new ArrayList<IPostProcessor>();

	/**
	 * Veto Strategy
	 */
	private IPostGenerationVetoStrategy vetoStrategy;

	/**
	 * Create new XA Acceleo Writer
	 * 
	 * @param target
	 * @param appendMode
	 * @throws IOException
	 */
	public XAAcceleoWriter(File target, boolean appendMode) throws IOException {
		super();
		this.lazyFileWriter = new LazyAcceleoFileWriter(target, appendMode);
	}

	/**
	 * Create new XA Acceleo Writer
	 * 
	 * @param target
	 * @param appendMode
	 * @param charset
	 * @throws IOException
	 */
	public XAAcceleoWriter(File target, boolean appendMode, String charset) throws IOException {
		super();
		this.lazyFileWriter = new LazyAcceleoFileWriter(target, appendMode, charset);
	}

	/**
	 * Create new XA Acceleo Writer
	 * 
	 * @param filePath
	 */
	public XAAcceleoWriter(String filePath) {
		super();
		this.lazyFileWriter = new LazyAcceleoFileWriter(filePath);
	}

	/**
	 * Create new XA Acceleo Writer
	 * 
	 * @param filePath
	 * @param charset
	 */
	public XAAcceleoWriter(String filePath, String charset) {
		super();
		this.lazyFileWriter = new LazyAcceleoFileWriter(filePath, charset);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.acceleo.engine.generation.writers.AbstractAcceleoWriter#close
	 * ()
	 */
	@Override
	public void close() throws IOException {
		FileHandler fileHandler = new FileHandler(this.delegate, this.lazyFileWriter.getTargetPath());

		if (this.vetoStrategy != null && this.vetoStrategy.hasVeto(fileHandler)) {
			delegate.close();
			return;
		}
		
		for (IPostProcessor postProcessor : this.postProcessors)
			postProcessor.beforeWrite(fileHandler);
		
		AbstractAcceleoWriter writer = this.lazyFileWriter.getAcceleoFileWriter();
		writer.write(fileHandler.getWriter().toString());
		writer.close();
		delegate.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.acceleo.engine.generation.writers.AbstractAcceleoWriter#
	 * getTargetPath()
	 */
	@Override
	public String getTargetPath() {
		return this.lazyFileWriter.getTargetPath();
	}

	/**
	 * Sets the post processors
	 * 
	 * @param postProcessors
	 */
	public XAAcceleoWriter setPostProcessors(Collection<IPostProcessor> postProcessors) {
		this.postProcessors.clear();
		this.postProcessors.addAll(postProcessors);
		return this;
	}

	public XAAcceleoWriter setVetoStrategy(IPostGenerationVetoStrategy vetoStrategy) {
		this.vetoStrategy = vetoStrategy;
		return this;
	}

}
