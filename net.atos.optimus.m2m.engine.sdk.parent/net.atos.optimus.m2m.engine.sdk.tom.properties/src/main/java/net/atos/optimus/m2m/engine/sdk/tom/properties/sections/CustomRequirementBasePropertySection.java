package net.atos.optimus.m2m.engine.sdk.tom.properties.sections;

import net.atos.optimus.m2m.engine.sdk.tom.TomPackage;
import net.atos.optimus.m2m.engine.sdk.tom.properties.resourceselectors.ClassResourceProcessorFactory;
import net.atos.optimus.m2m.engine.sdk.tom.properties.zones.ResourceSelectorZone;

import org.eclipselabs.resourceselector.core.processor.ResourceProcessorFactory;

import com.worldline.gmf.propertysections.core.AbstractSection;
import com.worldline.gmf.propertysections.core.tools.FormDataBuilder;

/**
 * Models the base section for custom requirement
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 *
 */

public class CustomRequirementBasePropertySection extends AbstractSection {

	public static final int LABEL_WIDTH = 100;

	public static final String TITLE_RESOURCE_SELECTOR = "Choose a subclass of AbsractRequirement";

	@Override
	protected void initParts() {
		ResourceProcessorFactory implementationClassResourceProcessor = new ClassResourceProcessorFactory(
				net.atos.optimus.m2m.engine.core.requirements.AbstractRequirement.class);
		this.getZones().put(
				"implementationZone",
				new ResourceSelectorZone(getBackGround(), false, TomPackage.eINSTANCE
						.getCustomRequirement_Implementation(), "Implementation :",
						CustomRequirementBasePropertySection.LABEL_WIDTH, false,
						CustomRequirementBasePropertySection.TITLE_RESOURCE_SELECTOR,
						implementationClassResourceProcessor));
	}

	@Override
	protected void addLayoutsToParts() {
		FormDataBuilder.on(this.getZone("implementationZone")).top().horizontal();
	}

}
