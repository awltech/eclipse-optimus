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
				new InputTextWithLabelZone(getBackGround(), false, TomPackage.eINSTANCE
						.getTransformationSet_Name(), "Name :", TransformationSetBasePropertySection.LABEL_WIDTH));
		this.getZones().put(
				"descriptionZone",
				new InputLargeTextZone(getBackGround(), false, TomPackage.eINSTANCE
						.getTransformationSet_Description(), "Description :",
						TransformationSetBasePropertySection.LABEL_WIDTH));
		this.getZones().put(
				"implementationZone",
				new ResourceSelectorZone(getBackGround(), false, TomPackage.eINSTANCE
						.getTransformationSet_Implementation(), "Implementation :",
						TransformationSetBasePropertySection.LABEL_WIDTH, false,
						TransformationSetBasePropertySection.TITLE_RESOURCE_SELECTOR,
						implementationClassResourceProcessor));
		this.getZones().put(
				"privateZone",
				new InputBooleanZone(getBackGround(), false, TomPackage.eINSTANCE
						.getTransformationSet_Private(), "Use private Transformation Set"));
	}

	@Override
	protected void addLayoutsToParts() {
		FormDataBuilder.on(this.getZone("nameZone")).top().horizontal();
		FormDataBuilder.on(this.getZone("descriptionZone")).top(this.getZone("nameZone")).horizontal();
		FormDataBuilder.on(this.getZone("implementationZone")).top(this.getZone("descriptionZone")).horizontal();
		FormDataBuilder.on(this.getZone("privateZone")).top(this.getZone("implementationZone")).horizontal();
	}

}
