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
import net.atos.optimus.m2m.engine.sdk.tom.properties.resourceselectors.ClassResourceProcessorFactory;
import net.atos.optimus.m2m.engine.sdk.tom.properties.zones.InputBooleanZone;
import net.atos.optimus.m2m.engine.sdk.tom.properties.zones.InputLargeTextZone;
import net.atos.optimus.m2m.engine.sdk.tom.properties.zones.InputTextWithLabelZone;
import net.atos.optimus.m2m.engine.sdk.tom.properties.zones.ResourceSelectorZone;

import org.eclipselabs.resourceselector.core.processor.ResourceProcessorFactory;

import com.worldline.gmf.propertysections.core.AbstractSection;
import com.worldline.gmf.propertysections.core.tools.FormDataBuilder;

/**
 * Models the base section for transformation set
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 *
 */

public class TransformationSetBasePropertySection extends AbstractSection {

	public static final int LABEL_WIDTH = 100;

	public static final String TITLE_RESOURCE_SELECTOR = "Choose a subclass of TransformationSet";

	@Override
	protected void initParts() {
		ResourceProcessorFactory implementationClassResourceProcessor = new ClassResourceProcessorFactory(
				net.atos.optimus.m2m.engine.core.transformations.TransformationSet.class);
		this.getZones().put(
				"nameZone",
				new InputTextWithLabelZone(getBackGround(), false, TomPackage.eINSTANCE.getTransformationSet_Name(),
						"Name :", TransformationSetBasePropertySection.LABEL_WIDTH));
		this.getZones().put(
				"descriptionZone",
				new InputLargeTextZone(getBackGround(), false, TomPackage.eINSTANCE.getTransformationSet_Description(),
						"Description :", TransformationSetBasePropertySection.LABEL_WIDTH));
		this.getZones().put(
				"implementationZone",
				new ResourceSelectorZone(getBackGround(), false, TomPackage.eINSTANCE
						.getTransformationSet_Implementation(), "Implementation :",
						TransformationSetBasePropertySection.LABEL_WIDTH, false,
						TransformationSetBasePropertySection.TITLE_RESOURCE_SELECTOR,
						implementationClassResourceProcessor));
		this.getZones().put(
				"privateZone",
				new InputBooleanZone(getBackGround(), false, TomPackage.eINSTANCE.getTransformationSet_Private(),
						"Use private Transformation Set"));
	}

	@Override
	protected void addLayoutsToParts() {
		FormDataBuilder.on(this.getZone("nameZone")).top().horizontal();
		FormDataBuilder.on(this.getZone("descriptionZone")).top(this.getZone("nameZone")).horizontal();
		FormDataBuilder.on(this.getZone("implementationZone")).top(this.getZone("descriptionZone")).horizontal();
		FormDataBuilder.on(this.getZone("privateZone")).top(this.getZone("implementationZone")).horizontal();
	}

}
