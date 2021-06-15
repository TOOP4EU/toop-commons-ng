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

import java.util.List;

import javax.annotation.Nonnull;

import com.helger.commons.collection.impl.CommonsLinkedHashSet;
import com.helger.commons.collection.impl.ICommonsSet;
import com.helger.commons.io.resource.ClassPathResource;

import eu.toop.edm.xml.cv.CCV;
import eu.toop.edm.xml.dcatap.CDCatAP;

/**
 * CCCEV constants
 *
 * @author Philip Helger
 */
public final class CCCEV
{
  @Nonnull
  private static final ClassLoader _getCL ()
  {
    return CCCEV.class.getClassLoader ();
  }

  public static final List <ClassPathResource> XSDS;
  static
  {
    final ICommonsSet <ClassPathResource> aSet = new CommonsLinkedHashSet <> ();
    aSet.addAll (CCV.XSDS);
    aSet.addAll (CDCatAP.XSDS);
    aSet.addAll (new ClassPathResource ("schemas/CV-CommonAggregateComponents.xsd", _getCL ()),
                 new ClassPathResource ("schemas/CV-Agent.xsd", _getCL ()),
                 new ClassPathResource ("schemas/owl.xsd", _getCL ()),
                 new ClassPathResource ("schemas/cccev-2.0.0.xsd", _getCL ()));
    XSDS = aSet.getCopyAsList ().getAsUnmodifiable ();
  }

  private CCCEV ()
  {}
}
