/**
 * Copyright 2021 - TOOP Project
 *
 * This file and its contents are licensed under the EUPL, Version 1.2
 * or – as soon they will be approved by the European Commission – subsequent
 * versions of the EUPL (the "Licence");
 *
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 *
 *       https://joinup.ec.europa.eu/collection/eupl/eupl-text-eupl-12
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the Licence for the specific language governing permissions and limitations under the Licence.
 */
package eu.toop.edm.xml.dcatap;

import java.util.List;

import javax.annotation.Nonnull;

import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.xsds.ccts.cct.schemamodule.CCCTS;
import com.helger.xsds.xlink.CXLink;
import com.helger.xsds.xml.CXML_XSD;

/**
 * Constants for handling DCatAP
 *
 * @author Philip Helger
 */
public final class CDCatAP
{
  @Nonnull
  private static ClassLoader _getCL ()
  {
    return CDCatAP.class.getClassLoader ();
  }

  public static final List <ClassPathResource> XSDS = new CommonsArrayList <> (CXML_XSD.getXSDResource (),
                                                                               CCCTS.getXSDResource (),
                                                                               CXLink.getXSDResource (),
                                                                               new ClassPathResource ("schemas/foaf.xsd", _getCL ()),
                                                                               new ClassPathResource ("schemas/locn.xsd", _getCL ()),
                                                                               new ClassPathResource ("schemas/skos.xsd", _getCL ()),
                                                                               new ClassPathResource ("schemas/org.xsd", _getCL ()),
                                                                               new ClassPathResource ("schemas/rdf.xsd", _getCL ()),
                                                                               new ClassPathResource ("schemas/prov.xsd", _getCL ()),
                                                                               new ClassPathResource ("schemas/dcterms.xsd", _getCL ()),
                                                                               new ClassPathResource ("schemas/CV-DataTypes.xsd",
                                                                                                      _getCL ()),
                                                                               new ClassPathResource ("schemas/CV-CommonBasicComponents.xsd",
                                                                                                      _getCL ()),
                                                                               new ClassPathResource ("schemas/adms.xsd", _getCL ()),
                                                                               new ClassPathResource ("schemas/odrl.xsd", _getCL ()),
                                                                               new ClassPathResource ("schemas/spdx.xsd", _getCL ()),
                                                                               new ClassPathResource ("schemas/vcard.xsd", _getCL ()),
                                                                               new ClassPathResource ("schemas/dcat-ap.xsd", _getCL ()))
                                                                                                                                        .getAsUnmodifiable ();

  private CDCatAP ()
  {}
}
