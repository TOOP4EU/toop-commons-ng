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
package eu.toop.edm.model;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.id.IHasID;
import com.helger.commons.lang.EnumHelper;

/**
 * The RegRep Query "queryDefinition" value to use
 *
 * @author Philip Helger
 */
public enum EToopQueryDefinitionType implements IHasID <String>
{
  /**
   * Query for concepts (name-value-pairs).
   */
  CONCEPT ("ConceptQuery"),
  /**
   * Query for an unstructured document (like PDF or an image).
   */
  DOCUMENT_BY_DISTRIBUTION ("DocumentQuery"),
  /**
   * Query for an unstructured document with an ID only.
   *
   * @since 2.0.0 beta 3
   */
  DOCUMENT_BY_ID ("urn:oasis:names:tc:ebxml-regrep:query:GetObjectById");

  private final String m_sID;

  EToopQueryDefinitionType (@Nonnull @Nonempty final String sID)
  {
    m_sID = sID;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  /**
   * @return <code>true</code> if no data subject is required.
   * @since 2.1.0
   */
  public boolean isDataSujectOptional ()
  {
    return this == DOCUMENT_BY_ID;
  }

  @Nullable
  public static EToopQueryDefinitionType getFromIDOrNull (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrNull (EToopQueryDefinitionType.class, sID);
  }
}
