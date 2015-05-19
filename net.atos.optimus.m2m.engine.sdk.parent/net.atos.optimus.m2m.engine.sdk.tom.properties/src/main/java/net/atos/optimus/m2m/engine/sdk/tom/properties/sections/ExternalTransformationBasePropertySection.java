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
package net.atos.optimus.m2m.engine.sdk.tom.properties.sections;

import net.atos.optimus.m2m.engine.sdk.tom.TomPackage;
import net.atos.optimus.m2m.engine.sdk.tom.properties.resourceselectors.TransformationResourceProcessorFactory;
import net.atos.optimus.m2m.engine.sdk.tom.properties.zones.ResourceSelectorZone;

import org.eclipselabs.resourceselector.core.processor.ResourceProcessorFactory;

import com.worldline.gmf.propertysections.core.AbstractSection;
import com.worldline.gmf.propertysections.core.tools.FormDataBuilder;

public class ExternalTransformationBasePropertySection extends AbstractSection {

	public static final int LABEL_WIDTH = 150;

	public static final String TITLE_RESOURCE_SELECTOR = "Choose a Transformation Id (partial result)";

	@Override
	protected void initParts() {
		ResourceProcessorFactory transformationNameProcessor = new TransformationResourceProcessorFactory();
		this.getZones().put(
				"transformationIdZone",
				new ResourceSelectorZone(getBackGround(), false, TomPackage.eINSTANCE
						.getTransformationSet_Name(), "Transformation Id :",
						TransformationSetExtensionBasePropertySection.LABEL_WIDTH, true,
						TransformationSetExtensionBasePropertySection.TITLE_RESOURCE_SELECTOR,
						transformationNameProcessor));
	}

	@Override
	protected void addLayoutsToParts() {
		FormDataBuilder.on(this.getZone("transformationIdZone")).top().horizontal();
	}

}
