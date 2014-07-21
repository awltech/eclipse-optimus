/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.atos.optimus.emf.metamodels.properties.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Map;

import net.atos.optimus.emf.metamodels.properties.CarriageReturn;
import net.atos.optimus.emf.metamodels.properties.Comment;
import net.atos.optimus.emf.metamodels.properties.Content;
import net.atos.optimus.emf.metamodels.properties.Pair;
import net.atos.optimus.emf.metamodels.properties.Properties;
import net.atos.optimus.emf.metamodels.properties.PropertiesFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;

/**
 * <!-- begin-user-doc --> The <b>Resource </b> associated with the package.
 * <!-- end-user-doc -->
 * 
 * @see net.atos.optimus.emf.metamodels.properties.util.PropertiesResourceFactoryImpl
 * @generated
 */
public class PropertiesResourceImpl extends ResourceImpl {
	/**
	 * Creates an instance of the resource. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param uri
	 *            the URI of the new resource.
	 * @generated
	 */
	public PropertiesResourceImpl(URI uri) {
		super(uri);
	}

	/**
	 * Constant used to define the EQUALS keyword
	 */
	private static final String EQUALS = "=";

	/**
	 * Constant used to define the SHARP keyword (for comments)
	 */
	private static final String SHARP = "#";

	/**
	 * Constant used to define the Carriage Return keyword (properties separator)
	 */
	private static final String CR = "\n";

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.resource.impl.ResourceImpl#doLoad(java.io.InputStream, java.util.Map)
	 */
	@Override
	protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {

		Properties properties = PropertiesFactory.eINSTANCE.createProperties();
		getContents().add(properties);

		InputStreamReader reader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(reader);
		while (bufferedReader.ready()) {
			String line = bufferedReader.readLine().trim();
			if (line.length() == 0) {
				properties.getContents().add(PropertiesFactory.eINSTANCE.createCarriageReturn());
			} else if (line.startsWith(SHARP)) {
				String value = line.substring(1);
				Comment comment = PropertiesFactory.eINSTANCE.createComment();
				comment.setValue(value);
				properties.getContents().add(comment);
			} else if (line.indexOf(EQUALS) > 0) {
				String[] chunks = line.split(EQUALS);
				Pair pair = PropertiesFactory.eINSTANCE.createPair();
				pair.setKey(chunks[0]);
				pair.setValue(chunks[1]);
				properties.getContents().add(pair);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.resource.impl.ResourceImpl#doSave(java.io.OutputStream, java.util.Map)
	 */
	@Override
	protected void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException {
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
		if (getContents().size() > 0 && getContents().get(0) instanceof Properties) {
			Properties properties = (Properties) getContents().get(0);
			for (Content content : properties.getContents()) {
				if (content instanceof Pair) {
					Pair pair = (Pair) content;
					bufferedOutputStream.write((pair.getKey() + EQUALS + pair.getValue() + CR).getBytes());
				} else if (content instanceof Comment) {
					Comment comment = (Comment) content;
					bufferedOutputStream.write((SHARP + comment.getValue() + CR).getBytes());
				} else if (content instanceof CarriageReturn) {
					bufferedOutputStream.write(CR.getBytes());
				}
			}
		}
		bufferedOutputStream.close();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.resource.impl.ResourceImpl#doUnload()
	 */
	@Override
	protected void doUnload() {
		super.doUnload();
	}

} // PropertiesResourceImpl
