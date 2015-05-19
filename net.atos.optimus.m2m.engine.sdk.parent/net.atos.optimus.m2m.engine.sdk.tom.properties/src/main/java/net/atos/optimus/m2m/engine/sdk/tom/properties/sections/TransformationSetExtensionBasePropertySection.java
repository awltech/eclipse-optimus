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
import net.atos.optimus.m2m.engine.sdk.tom.properties.resourceselectors.TransformationSetResourceProcessorFactory;
import net.atos.optimus.m2m.engine.sdk.tom.properties.zones.ResourceSelectorZone;

import org.eclipselabs.resourceselector.core.processor.ResourceProcessorFactory;

import com.worldline.gmf.propertysections.core.AbstractSection;
import com.worldline.gmf.propertysections.core.tools.FormDataBuilder;

/**
 * Models the base section for transformation set extension
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 *
 */

public class TransformationSetExtensionBasePropertySection extends AbstractSection {

	public static final int LABEL_WIDTH = 150;

	public static final String TITLE_RESOURCE_SELECTOR = "Choose a Transformation Set Id (partial result)";

	@Override
	protected void initParts() {
		ResourceProcessorFactory transformationSetNameProcessor = new TransformationSetResourceProcessorFactory();
		this.getZones().put(
				"transformationSetIdZone",
				new ResourceSelectorZone(getBackGround(), false, TomPackage.eINSTANCE
						.getTransformationSet_Name(), "Transformation set Id :",
						TransformationSetExtensionBasePropertySection.LABEL_WIDTH, true,
						TransformationSetExtensionBasePropertySection.TITLE_RESOURCE_SELECTOR,
						transformationSetNameProcessor));
	}

	@Override
	protected void addLayoutsToParts() {
		FormDataBuilder.on(this.getZone("transformationSetIdZone")).top().horizontal();
	}

}
