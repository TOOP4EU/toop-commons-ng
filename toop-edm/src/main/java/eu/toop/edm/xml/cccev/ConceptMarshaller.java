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
package eu.toop.edm.xml.cccev;

import javax.annotation.Nullable;

import com.helger.xml.namespace.IIterableNamespaceContext;
import com.helger.xml.namespace.MapBasedNamespaceContext;

import eu.toop.edm.jaxb.cccev.CCCEVConceptType;

/**
 * XML marshaller for CCCEV concept
 * 
 * @author Philip Helger
 */
public class ConceptMarshaller extends AbstractCCCEVMarshaller <CCCEVConceptType>
{
  public ConceptMarshaller ()
  {
    this (null);
  }

  public ConceptMarshaller (@Nullable final IIterableNamespaceContext aAdditionalNSPrefixes)
  {
    super (CCCEVConceptType.class, x -> new eu.toop.edm.jaxb.cccev.ObjectFactory ().createConcept (x));

    if (aAdditionalNSPrefixes != null)
    {
      final MapBasedNamespaceContext aCtx = CCCEVNamespaceContext.getInstance ().getClone ();
      aCtx.addMappings (aAdditionalNSPrefixes);
      setNamespaceContext (aCtx);
    }
  }
}
