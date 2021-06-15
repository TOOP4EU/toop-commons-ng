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
 * Response option. Used in the request to determine the desired response
 * layout. Used in the response to depict the actually used layout.
 *
 * @since 2.0.0-beta3
 * @author Philip Helger
 */
public enum EToopResponseOptionType implements IHasID <String>
{
  /**
   * Response payload is part of the response.
   */
  INLINE ("LeafClassWithRepositoryItem"),
  /**
   * Response payload is referenced from within the response.
   */
  REFERENCE ("ObjectRef");

  private final String m_sID;

  EToopResponseOptionType (@Nonnull @Nonempty final String sID)
  {
    m_sID = sID;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  @Nullable
  public static EToopResponseOptionType getFromIDOrNull (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrNull (EToopResponseOptionType.class, sID);
  }
}
