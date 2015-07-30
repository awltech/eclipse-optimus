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
package net.atos.optimus.m2m.engine.masks;

import java.io.File;
import java.util.Map.Entry;
import java.util.Set;

import net.atos.optimus.m2m.engine.core.transformations.TransformationReference;
import net.atos.optimus.m2m.engine.masks.logging.OptimusM2MMaskMessages;

/**
 * Inclusive transformation mask linked with an XML file
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */
public class InclusiveXMLTransformationMask extends XMLTransformationMask {

	/**
	 * Constructor
	 * 
	 * @param transformationMaskFilename
	 *            the file containing the transformation mask.
	 * @param associatedTransformationMaskReference
	 *            the transformation mask reference associated to the
	 *            transformation mask.
	 */
	public InclusiveXMLTransformationMask(File transformationMaskFilename,
			XMLTransformationMaskReference associatedTransformationMaskReference) {
		super(transformationMaskFilename, associatedTransformationMaskReference);
	}

	@Override
	public boolean isTransformationEnabled(String id) {
		return this.transformationMask.contains(id);
	}

	@Override
	protected void addTransformationLog(String transformationName) {
		OptimusM2MMaskMessages.UM17.log(transformationName, this.transformationMaskFile.getName());
	}

	@Override
	protected Set<String> getRequirementsTransformation(TransformationReference reference) {
		return MaskTransformationRequirementsTool.requirementsToActivate(this, reference);
	}

	@Override
	public void submitMaskModification(Set<Entry<String, Boolean>> maskModifications) {
		for (Entry<String, Boolean> modification : maskModifications) {
			if (modification.getValue()) {
				this.transformationMask.add(modification.getKey());
			} else {
				this.transformationMask.remove(modification.getKey());
			}
		}
		UserTransformationMaskTool.createUserTransformationMask(this.transformationMaskFile,
				this.associatedTransformationMaskReference);
	}

}
