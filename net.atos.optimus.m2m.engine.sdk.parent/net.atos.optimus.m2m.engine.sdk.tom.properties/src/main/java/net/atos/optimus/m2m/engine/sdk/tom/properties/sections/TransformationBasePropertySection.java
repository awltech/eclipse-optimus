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
import net.atos.optimus.m2m.engine.sdk.tom.properties.zones.TransformationGeneratorSelectorZone;
import net.atos.optimus.m2m.engine.sdk.tom.properties.zones.InputIntegerZone;
import net.atos.optimus.m2m.engine.sdk.tom.properties.zones.InputLargeTextZone;
import net.atos.optimus.m2m.engine.sdk.tom.properties.zones.InputTextWithLabelZone;

import org.eclipselabs.resourceselector.core.processor.ResourceProcessorFactory;

import com.worldline.gmf.propertysections.core.AbstractSection;
import com.worldline.gmf.propertysections.core.tools.FormDataBuilder;

/**
 * Models the base section for transformation
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 *
 */

public class TransformationBasePropertySection extends AbstractSection {

	public static final int LABEL_WIDTH = 80;

	public static final String TITLE_RESOURCE_SELECTOR = "Choose a subclass of ITransformationFactory";

	@Override
	protected void initParts() {
		ResourceProcessorFactory factoryClassResourceProcessor = new ClassResourceProcessorFactory(
				net.atos.optimus.m2m.engine.core.transformations.ITransformationFactory.class);
		this.getZones().put(
				"nameZone",
				new InputTextWithLabelZone(getBackGround(), false, TomPackage.eINSTANCE
						.getTransformationReference_Name(), "Name :", TransformationBasePropertySection.LABEL_WIDTH));
		this.getZones().put(
				"descriptionZone",
				new InputLargeTextZone(getBackGround(), false, TomPackage.eINSTANCE.getTransformation_Description(),
						"Description :", TransformationBasePropertySection.LABEL_WIDTH));
		this.getZones().put(
				"factoryZone",
				new TransformationGeneratorSelectorZone(getBackGround(), false, TomPackage.eINSTANCE.getTransformation_Factory(),
						"Factory :", TransformationBasePropertySection.LABEL_WIDTH, false,
						TransformationBasePropertySection.TITLE_RESOURCE_SELECTOR, factoryClassResourceProcessor));
		this.getZones().put(
				"priorityZone",
				new InputIntegerZone(getBackGround(), false, TomPackage.eINSTANCE.getTransformation_Priority(),
						"Priority :", TransformationBasePropertySection.LABEL_WIDTH));
	}

	@Override
	protected void addLayoutsToParts() {
		FormDataBuilder.on(this.getZone("nameZone")).top().horizontal();
		FormDataBuilder.on(this.getZone("descriptionZone")).top(this.getZone("nameZone")).horizontal();
		FormDataBuilder.on(this.getZone("factoryZone")).top(this.getZone("descriptionZone")).horizontal();
		FormDataBuilder.on(this.getZone("priorityZone")).top(this.getZone("factoryZone")).horizontal();
	}

}
