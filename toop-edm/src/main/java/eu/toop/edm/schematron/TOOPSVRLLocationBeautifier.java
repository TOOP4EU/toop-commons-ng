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
package eu.toop.edm.schematron;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.IsSPIImplementation;
import com.helger.regrep.RegRep4NamespaceContext;
import com.helger.schematron.svrl.ISVRLLocationBeautifierSPI;

import eu.toop.edm.xml.cccev.CCCEVNamespaceContext;

/**
 * Beautifier for Schematron error messages.
 *
 * @author Philip Helger
 */
@IsSPIImplementation
public final class TOOPSVRLLocationBeautifier implements ISVRLLocationBeautifierSPI
{
  @Nullable
  public String getReplacementText (@Nonnull final String sNamespaceURI, @Nonnull final String sLocalName)
  {
    String sPrefix = CCCEVNamespaceContext.getInstance ().getCustomPrefix (sNamespaceURI);
    if (sPrefix == null)
      sPrefix = RegRep4NamespaceContext.getInstance ().getCustomPrefix (sNamespaceURI);

    if (sPrefix != null)
      return sPrefix + ':' + sLocalName;

    return null;
  }
}
