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
package eu.toop.codelist.tools;

import javax.annotation.Nonnull;

import com.helger.commons.version.Version;

/**
 * Abstract base class with shared ideas
 *
 * @author Philip Helger
 */
abstract class AbstractMain
{
  public static final Version CODELIST_VERSION = new Version (5, 0, 0);
  public static final String DO_NOT_EDIT = "This file was automatically generated.\nDo NOT edit!";
  public static final String CODELIST_XLSX_DIR = "../../toop/Code Lists/";
  private static final String CL_XML_DIRECTORY = CODELIST_XLSX_DIR;

  protected AbstractMain ()
  {}

  @Nonnull
  protected static final String getDocTypFilePrefix ()
  {
    return CL_XML_DIRECTORY + "ToopDocumentTypeIdentifiers-v" + CODELIST_VERSION.getAsString (false);
  }

  @Nonnull
  protected static final String getParticipantIdentifierSchemesFilePrefix ()
  {
    return CL_XML_DIRECTORY + "ToopParticipantIdentifierSchemes-v" + CODELIST_VERSION.getAsString (false);
  }

  @Nonnull
  protected static final String getProcessFilePrefix ()
  {
    return CL_XML_DIRECTORY + "ToopProcessIdentifiers-v" + CODELIST_VERSION.getAsString (false);
  }

  @Nonnull
  protected static final String getTransportProfilesPrefix ()
  {
    return CL_XML_DIRECTORY + "ToopTransportProfiles-v" + CODELIST_VERSION.getAsString (false);
  }
}
