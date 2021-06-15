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
package eu.toop.edm.xml.cv;

import com.helger.jaxb.GenericJAXBMarshaller;

import eu.toop.edm.jaxb.w3.cv.ac.CorePersonType;
import eu.toop.edm.jaxb.w3.cv.ac.ObjectFactory;

/**
 * Core Person XML marshaller
 * 
 * @author Philip Helger
 */
public class PersonMarshaller extends GenericJAXBMarshaller <CorePersonType>
{
  public PersonMarshaller ()
  {
    super (CorePersonType.class, CCV.XSDS, x -> new ObjectFactory ().createCorePerson (x));
    setNamespaceContext (CCVNamespaceContext.getInstance ());
  }
}
