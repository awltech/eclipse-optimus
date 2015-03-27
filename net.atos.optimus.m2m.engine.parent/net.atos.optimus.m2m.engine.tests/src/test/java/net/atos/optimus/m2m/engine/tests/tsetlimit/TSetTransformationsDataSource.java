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
package net.atos.optimus.m2m.engine.tests.tsetlimit;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import net.atos.optimus.m2m.engine.core.transformations.DefaultTransformationSet;
import net.atos.optimus.m2m.engine.core.transformations.TransformationDataSource;
import net.atos.optimus.m2m.engine.core.transformations.TransformationReference;
import net.atos.optimus.m2m.engine.core.transformations.TransformationSet;

/**
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 */
public class TSetTransformationsDataSource extends TransformationDataSource {

	private Map<String, TransformationReference> transformationReferences = new LinkedHashMap<String, TransformationReference>();

	public TSetTransformationsDataSource() {
		super("TSetTransformationsDataSource");
		TransformationSet set1 = new DefaultTransformationSet(
				"transformationSet1");
		set1.setPrivate(true);
		TransformationReference reference1 = new TransformationReference("t1",
				new TransformationFactory1(), set1, null, 0);
		TransformationSet set2 = new DefaultTransformationSet(
				"transformationSet2");
		TransformationReference reference2 = new TransformationReference("t2",
				new TransformationFactory2(), set2, null, 0);

		transformationReferences.put("t1", reference1);
		transformationReferences.put("t2", reference2);

	}

	@Override
	public TransformationReference getById(String id) {
		return transformationReferences.get(id);
	}

	@Override
	public Collection<TransformationReference> getAll() {
		return transformationReferences.values();
	}

}
