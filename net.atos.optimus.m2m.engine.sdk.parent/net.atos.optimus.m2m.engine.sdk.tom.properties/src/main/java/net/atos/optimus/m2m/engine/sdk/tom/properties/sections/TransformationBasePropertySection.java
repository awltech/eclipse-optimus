package net.atos.optimus.m2m.engine.sdk.tom.properties.sections;

import net.atos.optimus.m2m.engine.sdk.tom.TomPackage;
import net.atos.optimus.m2m.engine.sdk.tom.properties.resourceselectors.ClassResourceProcessorFactory;
import net.atos.optimus.m2m.engine.sdk.tom.properties.zones.InputIntegerZone;
import net.atos.optimus.m2m.engine.sdk.tom.properties.zones.InputLargeTextZone;
import net.atos.optimus.m2m.engine.sdk.tom.properties.zones.InputTextWithLabelZone;
import net.atos.optimus.m2m.engine.sdk.tom.properties.zones.ResourceSelectorZone;

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
				new InputLargeTextZone(getBackGround(), false, TomPackage.eINSTANCE
						.getTransformation_Description(), "Description :",
						TransformationBasePropertySection.LABEL_WIDTH));
		this.getZones()
				.put("factoryZone",
						new ResourceSelectorZone(getBackGround(), false, TomPackage.eINSTANCE
								.getTransformation_Factory(), "Factory :",
								TransformationBasePropertySection.LABEL_WIDTH, false,
								TransformationBasePropertySection.TITLE_RESOURCE_SELECTOR,
								factoryClassResourceProcessor));
		this.getZones().put(
				"priorityZone",
				new InputIntegerZone(getBackGround(), false, TomPackage.eINSTANCE
						.getTransformation_Priority(), "Priority :", TransformationBasePropertySection.LABEL_WIDTH));
	}

	@Override
	protected void addLayoutsToParts() {
		FormDataBuilder.on(this.getZone("nameZone")).top().horizontal();
		FormDataBuilder.on(this.getZone("descriptionZone")).top(this.getZone("nameZone")).horizontal();
		FormDataBuilder.on(this.getZone("factoryZone")).top(this.getZone("descriptionZone")).horizontal();
		FormDataBuilder.on(this.getZone("priorityZone")).top(this.getZone("factoryZone")).horizontal();
	}

}
