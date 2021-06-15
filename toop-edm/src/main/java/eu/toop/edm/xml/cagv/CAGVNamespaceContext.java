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
package eu.toop.edm.xml.cagv;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Singleton;
import com.helger.xml.namespace.MapBasedNamespaceContext;
import com.helger.xsds.ccts.cct.schemamodule.CCCTS;
import com.helger.xsds.xlink.CXLink;

import eu.toop.edm.xml.cv.CCVNamespaceContext;

/**
 * XML Namespace context for CAGV
 *
 * @author Philip Helger
 */
@Singleton
public class CAGVNamespaceContext extends MapBasedNamespaceContext
{
  private static final class SingletonHolder
  {
    static final CAGVNamespaceContext s_aInstance = new CAGVNamespaceContext ();
  }

  protected CAGVNamespaceContext ()
  {
    // Add W3 CoreVocabularies
    addMappings (CCVNamespaceContext.getInstance ());
    addMapping (CCCTS.DEFAULT_PREFIX, CCCTS.NAMESPACE_URI);
    addMapping (CXLink.DEFAULT_PREFIX, CXLink.NAMESPACE_URI);
    addMapping ("owl", "http://www.w3.org/2002/07/owl#");
    addMapping ("skos", "http://www.w3.org/2004/02/skos/core#");
    addMapping ("locn", "http://www.w3.org/ns/locn#");
    addMapping ("foaf", "http://xmlns.com/foaf/0.1/");
    addMapping ("org", "http://www.w3.org/ns/org#");
    addMapping ("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
    addMapping ("dct", "http://purl.org/dc/terms/");
    addMapping ("regorg", "http://www.w3.org/ns/regorg#");
    addMapping ("csdt", "https://data.europe.eu/semanticassets/ns/cv/common/dataTypes-2.0.0#");
    addMapping ("csbc", "https://data.europe.eu/semanticassets/ns/cv/common/cbc_v2.0.0#");
    addMapping ("csac", "https://data.europe.eu/semanticassets/ns/cv/common/cac_v2.0.0#");
    addMapping ("cagv", "https://semic.org/sa/cv/cagv/agent-2.0.0#");
  }

  @Nonnull
  public static CAGVNamespaceContext getInstance ()
  {
    return SingletonHolder.s_aInstance;
  }
}
