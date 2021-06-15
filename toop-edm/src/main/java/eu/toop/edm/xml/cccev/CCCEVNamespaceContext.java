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

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Singleton;
import com.helger.xml.namespace.MapBasedNamespaceContext;

import eu.toop.edm.xml.dcatap.DCatNamespaceContext;

/**
 * XML Namespace context for CCCEV
 *
 * @author Philip Helger
 */
@Singleton
public class CCCEVNamespaceContext extends MapBasedNamespaceContext
{
  private static final class SingletonHolder
  {
    static final CCCEVNamespaceContext s_aInstance = new CCCEVNamespaceContext ();
  }

  protected CCCEVNamespaceContext ()
  {
    setMappings (DCatNamespaceContext.getInstance ());
    addMapping ("owl", "http://www.w3.org/2002/07/owl#");
    addMapping ("cccev", "https://data.europe.eu/semanticassets/ns/cv/cccev_v2.0.0#");
  }

  @Nonnull
  public static CCCEVNamespaceContext getInstance ()
  {
    return SingletonHolder.s_aInstance;
  }
}
