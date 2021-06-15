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

import java.util.List;

import javax.annotation.Nonnull;

import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.ubl23.CUBL23;

/**
 * Constants for the Core vocabulary
 *
 * @author Philip Helger
 */
public final class CCV
{
  @Nonnull
  private static final ClassLoader _getCL ()
  {
    return CCV.class.getClassLoader ();
  }

  public static final List <ClassPathResource> XSDS = new CommonsArrayList <> (CUBL23.XSD_UNQUALIFIED_DATA_TYPES,
                                                                               new ClassPathResource ("schemas/CoreVocabularies-BasicComponents-1.1.xsd",
                                                                                                      _getCL ()),
                                                                               new ClassPathResource ("schemas/CoreVocabularies-AggregateComponents-1.1.xsd",
                                                                                                      _getCL ())).getAsUnmodifiable ();

  private CCV ()
  {}
}
