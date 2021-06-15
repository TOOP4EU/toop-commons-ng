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

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Singleton;
import com.helger.ubl23.UBL23NamespaceContext;
import com.helger.xml.namespace.MapBasedNamespaceContext;
import com.helger.xsds.ccts.cct.schemamodule.CCCTS;
import com.helger.xsds.xlink.CXLink;

/**
 * XML Namespace context for DCAT AP.
 *
 * @author yerlibilgin
 * @author Philip Helger
 */
@Singleton
public class DCatNamespaceContext extends MapBasedNamespaceContext
{
  private static final class SingletonHolder
  {
    static final DCatNamespaceContext s_aInstance = new DCatNamespaceContext ();
  }

  protected DCatNamespaceContext ()
  {
    addMappings (UBL23NamespaceContext.getInstance ());
    addMapping (CCCTS.DEFAULT_PREFIX, CCCTS.NAMESPACE_URI);
    addMapping (CXLink.DEFAULT_PREFIX, CXLink.NAMESPACE_URI);
    addMapping ("foaf", "http://xmlns.com/foaf/0.1/");
    addMapping ("locn", "http://www.w3.org/ns/locn#");
    addMapping ("skos", "http://www.w3.org/2004/02/skos/core#");
    addMapping ("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
    addMapping ("prov", "http://www.w3.org/ns/prov#");
    addMapping ("dct", "http://purl.org/dc/terms/");
    addMapping ("csdt", "https://data.europe.eu/semanticassets/ns/cv/common/dataTypes-2.0.0#");
    addMapping ("csbc", "https://data.europe.eu/semanticassets/ns/cv/common/cbc_v2.0.0#");
    addMapping ("adms", "http://www.w3.org/ns/adms#");
    addMapping ("odrl", "http://www.w3.org/ns/odrl/2/");
    addMapping ("spdx", "spdx:xsd::1.0");
    addMapping ("vcard", "http://www.w3.org/2001/vcard-rdf/3.0#");
    addMapping ("dcat", "http://data.europa.eu/r5r/");
  }

  @Nonnull
  public static DCatNamespaceContext getInstance ()
  {
    return SingletonHolder.s_aInstance;
  }
}
