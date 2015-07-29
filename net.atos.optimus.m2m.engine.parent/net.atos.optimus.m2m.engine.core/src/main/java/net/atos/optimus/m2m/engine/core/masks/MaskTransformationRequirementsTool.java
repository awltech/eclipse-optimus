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
package net.atos.optimus.m2m.engine.core.masks;

import java.util.HashSet;
import java.util.Set;

import net.atos.optimus.m2m.engine.core.requirements.AbstractRequirement;
import net.atos.optimus.m2m.engine.core.transformations.TransformationDataSource;
import net.atos.optimus.m2m.engine.core.transformations.TransformationDataSourceManager;
import net.atos.optimus.m2m.engine.core.transformations.TransformationReference;

/**
 * Tool dedicated to scan requirements between transformation when
 * desactivate/activate one within a mask
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class MaskTransformationRequirementsTool {

	/**
	 * Give the set of transformations to activate whether the specified
	 * transformation will be activated according to the specified mask
	 * 
	 * @param mask
	 *            the mask in which we want to activate the specified
	 *            transformation.
	 * @param reference
	 *            the transformation reference to activate in the specified
	 *            mask.
	 * @return the set of transformations to activate whether the specified
	 *         transformation will be activated according to the specified mask.
	 */
	public static Set<String> requirementsToActivate(ITransformationMask mask, TransformationReference reference) {
		Set<String> transformationsToActivate = new HashSet<String>();
		MaskTransformationRequirementsTool.requirementsToActivate(mask, reference, transformationsToActivate);
		return transformationsToActivate;
	}

	/**
	 * Construct the set of transformations to activate whether the specified
	 * transformation will be activated according to the specified mask
	 * 
	 * This method will look recursively into the transformation reference to
	 * know the other transformations required by the current reference.
	 * 
	 * @param mask
	 *            the mask in which we want to activate the specified
	 *            transformation.
	 * @param reference
	 *            the transformation reference to activate in the specified
	 *            mask.
	 * @param transformationsToActivate
	 *            the set under construction.
	 */
	private static void requirementsToActivate(ITransformationMask mask, TransformationReference reference,
			Set<String> transformationsToActivate) {
		if (reference != null) {
			for (AbstractRequirement req : reference.getRequirements()) {
				String reqId = req.getId();
				if (reqId != null && !mask.isTransformationEnabled(reqId) && !transformationsToActivate.contains(reqId)) {
					transformationsToActivate.add(reqId);
					MaskTransformationRequirementsTool.requirementsToActivate(mask,
							TransformationDataSourceManager.INSTANCE.getById(reqId), transformationsToActivate);
				}
			}
		}
	}

	/**
	 * Give the set of transformations to desactivate whether the specified
	 * transformation will be desactivated according to the specified mask
	 * 
	 * @param mask
	 *            the mask in which we want to desactivate the specified
	 *            transformation.
	 * @param reference
	 *            the transformation reference to desactivate in the specified
	 *            mask.
	 * @return the set of transformations to desactivate whether the specified
	 *         transformation will be desactivated according to the specified
	 *         mask.
	 */
	public static Set<String> requirementsToDesactivate(ITransformationMask mask, TransformationReference reference) {
		Set<String> transformationsToDesactivate = new HashSet<String>();
		MaskTransformationRequirementsTool.requirementsToDesactivate(mask, reference, transformationsToDesactivate);
		return transformationsToDesactivate;
	}

	/**
	 * Construct the set of transformations to desactivate whether the specified
	 * transformation will be desactivated according to the specified mask
	 * 
	 * This method will look deeply into the referenced transformations to know
	 * whether the current reference is required by another transformation.
	 * 
	 * @param mask
	 *            the mask in which we want to desactivate the specified
	 *            transformation.
	 * @param reference
	 *            the transformation reference to desactivate in the specified
	 *            mask.
	 * @param transformationsToDesactivate
	 *            the set under construction.
	 */
	private static void requirementsToDesactivate(ITransformationMask mask, TransformationReference reference,
			Set<String> transformationsToDesactivate) {
		if (reference != null) {
			String reqId = reference.getId();
			for (TransformationDataSource transformationDataSource : TransformationDataSourceManager.INSTANCE
					.getTransformationDataSources()) {
				for (TransformationReference transfo : transformationDataSource.getAll()) {
					String transfoId = transfo.getId();
					if (MaskTransformationRequirementsTool.requireDirectlyAnotherTransformation(transfo, reqId)
							&& mask.isTransformationEnabled(transfoId)
							&& !transformationsToDesactivate.contains(transfoId)) {
						transformationsToDesactivate.add(transfoId);
						MaskTransformationRequirementsTool.requirementsToDesactivate(mask,
								TransformationDataSourceManager.INSTANCE.getById(transfoId),
								transformationsToDesactivate);
					}
				}
			}
		}
	}

	/**
	 * Test if a transformation requires directly another transformation
	 * 
	 * @param transformation
	 *            the transformation under test.
	 * @param anotherTransformationId
	 *            the transformation id of the possible required transformation.
	 * @return true if the specified transformation requires the transformation
	 *         with the specified id, false otherwise.
	 */
	public static boolean requireDirectlyAnotherTransformation(TransformationReference transformation,
			String anotherTransformationId) {
		for (AbstractRequirement req : transformation.getRequirements()) {
			String reqId = req.getId();
			if (reqId != null && reqId.equals(anotherTransformationId)) {
				return true;
			}
		}
		return false;
	}

}
