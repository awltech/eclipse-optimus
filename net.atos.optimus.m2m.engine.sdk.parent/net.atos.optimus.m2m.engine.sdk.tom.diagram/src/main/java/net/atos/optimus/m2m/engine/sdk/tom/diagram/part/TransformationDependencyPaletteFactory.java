package net.atos.optimus.m2m.engine.sdk.tom.diagram.part;

import java.util.ArrayList;
import java.util.Collections;

import net.atos.optimus.m2m.engine.sdk.tom.diagram.providers.TransformationDependencyElementTypes;

import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.tooling.runtime.part.DefaultLinkToolEntry;
import org.eclipse.gmf.tooling.runtime.part.DefaultNodeToolEntry;

/**
 * @generated
 */
public class TransformationDependencyPaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createTransformations1Group());
	}

	/**
	 * Creates "transformations" palette tool group
	 * @generated
	 */
	private PaletteContainer createTransformations1Group() {
		PaletteGroup paletteContainer = new PaletteGroup(Messages.Transformations1Group_title);
		paletteContainer.setId("createTransformations1Group"); //$NON-NLS-1$
		paletteContainer.add(createTransformationSet1CreationTool());
		paletteContainer.add(createTransformationSetExtension2CreationTool());
		paletteContainer.add(createTransformation3CreationTool());
		paletteContainer.add(createExternalTransformation4CreationTool());
		paletteContainer.add(createSelfRequirement5CreationTool());
		paletteContainer.add(createParentRequirement6CreationTool());
		paletteContainer.add(createCustomRequirement7CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createTransformationSet1CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.TransformationSet1CreationTool_title,
				Messages.TransformationSet1CreationTool_desc,
				Collections.singletonList(TransformationDependencyElementTypes.TransformationSet_2004));
		entry.setId("createTransformationSet1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(TransformationDependencyDiagramEditorPlugin
				.findImageDescriptor("/net.atos.optimus.m2m.engine.sdk.tom.edit/icons/full/obj16/TransformationSet.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createTransformationSetExtension2CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.TransformationSetExtension2CreationTool_title,
				Messages.TransformationSetExtension2CreationTool_desc,
				Collections.singletonList(TransformationDependencyElementTypes.TransformationSetExtension_2005));
		entry.setId("createTransformationSetExtension2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(TransformationDependencyDiagramEditorPlugin
				.findImageDescriptor("/net.atos.optimus.m2m.engine.sdk.tom.edit/icons/full/obj16/TransformationSetExtension.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createTransformation3CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(TransformationDependencyElementTypes.Transformation_3003);
		types.add(TransformationDependencyElementTypes.Transformation_3004);
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.Transformation3CreationTool_title,
				Messages.Transformation3CreationTool_desc, types);
		entry.setId("createTransformation3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(TransformationDependencyDiagramEditorPlugin
				.findImageDescriptor("/net.atos.optimus.m2m.engine.sdk.tom.edit/icons/full/obj16/Transformation.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createExternalTransformation4CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.ExternalTransformation4CreationTool_title,
				Messages.ExternalTransformation4CreationTool_desc,
				Collections.singletonList(TransformationDependencyElementTypes.ExternalTransformation_2006));
		entry.setId("createExternalTransformation4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(TransformationDependencyDiagramEditorPlugin
				.findImageDescriptor("/net.atos.optimus.m2m.engine.sdk.tom.edit/icons/full/obj16/ExternalTransformation.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createSelfRequirement5CreationTool() {
		DefaultLinkToolEntry entry = new DefaultLinkToolEntry(Messages.SelfRequirement5CreationTool_title,
				Messages.SelfRequirement5CreationTool_desc,
				Collections.singletonList(TransformationDependencyElementTypes.SelfRequirement_4004));
		entry.setId("createSelfRequirement5CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(TransformationDependencyDiagramEditorPlugin
				.findImageDescriptor("/net.atos.optimus.m2m.engine.sdk.tom.edit/icons/full/obj16/SelfRequirement.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createParentRequirement6CreationTool() {
		DefaultLinkToolEntry entry = new DefaultLinkToolEntry(Messages.ParentRequirement6CreationTool_title,
				Messages.ParentRequirement6CreationTool_desc,
				Collections.singletonList(TransformationDependencyElementTypes.ParentRequirement_4005));
		entry.setId("createParentRequirement6CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(TransformationDependencyDiagramEditorPlugin
				.findImageDescriptor("/net.atos.optimus.m2m.engine.sdk.tom.edit/icons/full/obj16/ParentRequirement.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createCustomRequirement7CreationTool() {
		DefaultLinkToolEntry entry = new DefaultLinkToolEntry(Messages.CustomRequirement7CreationTool_title,
				Messages.CustomRequirement7CreationTool_desc,
				Collections.singletonList(TransformationDependencyElementTypes.CustomRequirement_4006));
		entry.setId("createCustomRequirement7CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(TransformationDependencyDiagramEditorPlugin
				.findImageDescriptor("/net.atos.optimus.m2m.engine.sdk.tom.edit/icons/full/obj16/CustomRequirement.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

}
