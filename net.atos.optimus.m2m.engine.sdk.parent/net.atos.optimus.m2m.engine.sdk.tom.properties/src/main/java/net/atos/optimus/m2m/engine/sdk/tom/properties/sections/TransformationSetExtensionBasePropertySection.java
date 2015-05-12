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
