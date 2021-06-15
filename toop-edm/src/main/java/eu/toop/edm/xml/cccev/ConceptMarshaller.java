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
