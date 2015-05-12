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
