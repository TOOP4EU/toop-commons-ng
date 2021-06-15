/**
 * This work is protected under copyrights held by the members of the
 * TOOP Project Consortium as indicated at
 * http://wiki.ds.unipi.gr/display/TOOP/Contributors
 * (c) 2018-2021. All rights reserved.
 *
 * This work is licensed under the EUPL 1.2.
 *
 *  = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
 *
 * Licensed under the EUPL, Version 1.2 or – as soon they will be approved
 * by the European Commission - subsequent versions of the EUPL
 * (the "Licence");
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 *
 *         https://joinup.ec.europa.eu/software/page/eupl
 */
package eu.toop.edm.xml.dcatap;

import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.jaxb.GenericJAXBMarshaller;
import com.helger.jaxb.JAXBContextCache;

import eu.toop.edm.xml.cccev.CCCEV;
import eu.toop.edm.xml.cccev.CCCEVNamespaceContext;

/**
 * Abstract DCat AP XML marshaller
 *
 * @author Philip Helger
 * @param <T>
 *        The type to be marshaled
 */
public abstract class AbstractDCatMarshaller <T> extends GenericJAXBMarshaller <T>
{
  public AbstractDCatMarshaller (@Nonnull final Class <T> aType,
                                 @Nonnull final Function <? super T, ? extends JAXBElement <T>> aJAXBElementWrapper)
  {
    super (aType, CCCEV.XSDS, aJAXBElementWrapper);
    setNamespaceContext (CCCEVNamespaceContext.getInstance ());
    setIndentString ("  ");
  }

  @Override
  protected JAXBContext getJAXBContext (@Nullable final ClassLoader aClassLoader) throws JAXBException
  {
    final Class <?> [] aClasses = new Class <?> [] { com.helger.xsds.ccts.cct.schemamodule.ObjectFactory.class,
                                                     com.helger.xsds.xlink.ObjectFactory.class,
                                                     eu.toop.edm.jaxb.cv.cbc.ObjectFactory.class,
                                                     eu.toop.edm.jaxb.cv.dt.ObjectFactory.class,
                                                     eu.toop.edm.jaxb.dcatap.ObjectFactory.class,
                                                     eu.toop.edm.jaxb.dcterms.ObjectFactory.class,
                                                     eu.toop.edm.jaxb.foaf.ObjectFactory.class,
                                                     eu.toop.edm.jaxb.rdf.ObjectFactory.class,
                                                     eu.toop.edm.jaxb.spdx.ObjectFactory.class,
                                                     eu.toop.edm.jaxb.vcard.ObjectFactory.class,
                                                     eu.toop.edm.jaxb.w3.adms.ObjectFactory.class,
                                                     eu.toop.edm.jaxb.w3.locn.ObjectFactory.class,
                                                     eu.toop.edm.jaxb.w3.odrl.ObjectFactory.class,
                                                     eu.toop.edm.jaxb.w3.org.ObjectFactory.class,
                                                     eu.toop.edm.jaxb.w3.skos.ObjectFactory.class };

    if (isUseContextCache ())
      return JAXBContextCache.getInstance ().getFromCache (new CommonsArrayList <> (aClasses));
    return JAXBContext.newInstance (aClasses);
  }
}
