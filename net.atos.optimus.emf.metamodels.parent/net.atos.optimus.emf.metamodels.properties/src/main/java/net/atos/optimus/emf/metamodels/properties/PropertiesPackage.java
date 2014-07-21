/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.atos.optimus.emf.metamodels.properties;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see net.atos.optimus.emf.metamodels.properties.PropertiesFactory
 * @model kind="package"
 * @generated
 */
public interface PropertiesPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "properties";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://xa.atos.net/optimus/properties/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "properties";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PropertiesPackage eINSTANCE = net.atos.optimus.emf.metamodels.properties.impl.PropertiesPackageImpl.init();

	/**
	 * The meta object id for the '{@link net.atos.optimus.emf.metamodels.properties.impl.PropertiesImpl <em>Properties</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.atos.optimus.emf.metamodels.properties.impl.PropertiesImpl
	 * @see net.atos.optimus.emf.metamodels.properties.impl.PropertiesPackageImpl#getProperties()
	 * @generated
	 */
	int PROPERTIES = 0;

	/**
	 * The feature id for the '<em><b>Contents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTIES__CONTENTS = 0;

	/**
	 * The number of structural features of the '<em>Properties</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTIES_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link net.atos.optimus.emf.metamodels.properties.impl.ContentImpl <em>Content</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.atos.optimus.emf.metamodels.properties.impl.ContentImpl
	 * @see net.atos.optimus.emf.metamodels.properties.impl.PropertiesPackageImpl#getContent()
	 * @generated
	 */
	int CONTENT = 1;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT__PROPERTIES = 0;

	/**
	 * The number of structural features of the '<em>Content</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link net.atos.optimus.emf.metamodels.properties.impl.PairImpl <em>Pair</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.atos.optimus.emf.metamodels.properties.impl.PairImpl
	 * @see net.atos.optimus.emf.metamodels.properties.impl.PropertiesPackageImpl#getPair()
	 * @generated
	 */
	int PAIR = 2;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAIR__PROPERTIES = CONTENT__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAIR__KEY = CONTENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAIR__VALUE = CONTENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Pair</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAIR_FEATURE_COUNT = CONTENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link net.atos.optimus.emf.metamodels.properties.impl.CommentImpl <em>Comment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.atos.optimus.emf.metamodels.properties.impl.CommentImpl
	 * @see net.atos.optimus.emf.metamodels.properties.impl.PropertiesPackageImpl#getComment()
	 * @generated
	 */
	int COMMENT = 3;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__PROPERTIES = CONTENT__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__VALUE = CONTENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Comment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT_FEATURE_COUNT = CONTENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link net.atos.optimus.emf.metamodels.properties.impl.CarriageReturnImpl <em>Carriage Return</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.atos.optimus.emf.metamodels.properties.impl.CarriageReturnImpl
	 * @see net.atos.optimus.emf.metamodels.properties.impl.PropertiesPackageImpl#getCarriageReturn()
	 * @generated
	 */
	int CARRIAGE_RETURN = 4;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARRIAGE_RETURN__PROPERTIES = CONTENT__PROPERTIES;

	/**
	 * The number of structural features of the '<em>Carriage Return</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARRIAGE_RETURN_FEATURE_COUNT = CONTENT_FEATURE_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link net.atos.optimus.emf.metamodels.properties.Properties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Properties</em>'.
	 * @see net.atos.optimus.emf.metamodels.properties.Properties
	 * @generated
	 */
	EClass getProperties();

	/**
	 * Returns the meta object for the containment reference list '{@link net.atos.optimus.emf.metamodels.properties.Properties#getContents <em>Contents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Contents</em>'.
	 * @see net.atos.optimus.emf.metamodels.properties.Properties#getContents()
	 * @see #getProperties()
	 * @generated
	 */
	EReference getProperties_Contents();

	/**
	 * Returns the meta object for class '{@link net.atos.optimus.emf.metamodels.properties.Content <em>Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Content</em>'.
	 * @see net.atos.optimus.emf.metamodels.properties.Content
	 * @generated
	 */
	EClass getContent();

	/**
	 * Returns the meta object for the container reference '{@link net.atos.optimus.emf.metamodels.properties.Content#getProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Properties</em>'.
	 * @see net.atos.optimus.emf.metamodels.properties.Content#getProperties()
	 * @see #getContent()
	 * @generated
	 */
	EReference getContent_Properties();

	/**
	 * Returns the meta object for class '{@link net.atos.optimus.emf.metamodels.properties.Pair <em>Pair</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pair</em>'.
	 * @see net.atos.optimus.emf.metamodels.properties.Pair
	 * @generated
	 */
	EClass getPair();

	/**
	 * Returns the meta object for the attribute '{@link net.atos.optimus.emf.metamodels.properties.Pair#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see net.atos.optimus.emf.metamodels.properties.Pair#getKey()
	 * @see #getPair()
	 * @generated
	 */
	EAttribute getPair_Key();

	/**
	 * Returns the meta object for the attribute '{@link net.atos.optimus.emf.metamodels.properties.Pair#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see net.atos.optimus.emf.metamodels.properties.Pair#getValue()
	 * @see #getPair()
	 * @generated
	 */
	EAttribute getPair_Value();

	/**
	 * Returns the meta object for class '{@link net.atos.optimus.emf.metamodels.properties.Comment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Comment</em>'.
	 * @see net.atos.optimus.emf.metamodels.properties.Comment
	 * @generated
	 */
	EClass getComment();

	/**
	 * Returns the meta object for the attribute '{@link net.atos.optimus.emf.metamodels.properties.Comment#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see net.atos.optimus.emf.metamodels.properties.Comment#getValue()
	 * @see #getComment()
	 * @generated
	 */
	EAttribute getComment_Value();

	/**
	 * Returns the meta object for class '{@link net.atos.optimus.emf.metamodels.properties.CarriageReturn <em>Carriage Return</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Carriage Return</em>'.
	 * @see net.atos.optimus.emf.metamodels.properties.CarriageReturn
	 * @generated
	 */
	EClass getCarriageReturn();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PropertiesFactory getPropertiesFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link net.atos.optimus.emf.metamodels.properties.impl.PropertiesImpl <em>Properties</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.atos.optimus.emf.metamodels.properties.impl.PropertiesImpl
		 * @see net.atos.optimus.emf.metamodels.properties.impl.PropertiesPackageImpl#getProperties()
		 * @generated
		 */
		EClass PROPERTIES = eINSTANCE.getProperties();

		/**
		 * The meta object literal for the '<em><b>Contents</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTIES__CONTENTS = eINSTANCE.getProperties_Contents();

		/**
		 * The meta object literal for the '{@link net.atos.optimus.emf.metamodels.properties.impl.ContentImpl <em>Content</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.atos.optimus.emf.metamodels.properties.impl.ContentImpl
		 * @see net.atos.optimus.emf.metamodels.properties.impl.PropertiesPackageImpl#getContent()
		 * @generated
		 */
		EClass CONTENT = eINSTANCE.getContent();

		/**
		 * The meta object literal for the '<em><b>Properties</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTENT__PROPERTIES = eINSTANCE.getContent_Properties();

		/**
		 * The meta object literal for the '{@link net.atos.optimus.emf.metamodels.properties.impl.PairImpl <em>Pair</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.atos.optimus.emf.metamodels.properties.impl.PairImpl
		 * @see net.atos.optimus.emf.metamodels.properties.impl.PropertiesPackageImpl#getPair()
		 * @generated
		 */
		EClass PAIR = eINSTANCE.getPair();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAIR__KEY = eINSTANCE.getPair_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAIR__VALUE = eINSTANCE.getPair_Value();

		/**
		 * The meta object literal for the '{@link net.atos.optimus.emf.metamodels.properties.impl.CommentImpl <em>Comment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.atos.optimus.emf.metamodels.properties.impl.CommentImpl
		 * @see net.atos.optimus.emf.metamodels.properties.impl.PropertiesPackageImpl#getComment()
		 * @generated
		 */
		EClass COMMENT = eINSTANCE.getComment();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMENT__VALUE = eINSTANCE.getComment_Value();

		/**
		 * The meta object literal for the '{@link net.atos.optimus.emf.metamodels.properties.impl.CarriageReturnImpl <em>Carriage Return</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.atos.optimus.emf.metamodels.properties.impl.CarriageReturnImpl
		 * @see net.atos.optimus.emf.metamodels.properties.impl.PropertiesPackageImpl#getCarriageReturn()
		 * @generated
		 */
		EClass CARRIAGE_RETURN = eINSTANCE.getCarriageReturn();

	}

} //PropertiesPackage
