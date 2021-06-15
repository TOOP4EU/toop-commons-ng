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
package eu.toop.edm.error;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.lang.EnumHelper;

/**
 * Source: DataElementResponseErrorCode-CodeList.gc<br>
 * Content created by MainCreateJavaCode_DataElementResponseErrorCode_GC
 *
 * @author Philip Helger
 */
public enum EToopDataElementResponseErrorCode implements IToopErrorCode
{
  /** Unknown concept */
  DP_ELE_001 ("DP_ELE_001", "Unknown concept"),
  /** Unauthorized */
  DP_ELE_002 ("DP_ELE_002", "Unauthorized"),
  /** Ambiguous concept */
  DP_ELE_003 ("DP_ELE_003", "Ambiguous concept"),
  /** Unavailable */
  DP_ELE_004 ("DP_ELE_004", "Unavailable"),
  /** Internal processing error */
  DP_ELE_005 ("DP_ELE_005", "Internal processing error"),
  /** Insufficient input */
  DP_ELE_006 ("DP_ELE_006", "Insufficient input");

  private final String m_sID;
  private final String m_sDisplayName;

  EToopDataElementResponseErrorCode (@Nonnull @Nonempty final String sID, @Nonnull @Nonempty final String sDisplayName)
  {
    m_sID = sID;
    m_sDisplayName = sDisplayName;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  @Nonnull
  @Nonempty
  public String getDisplayName ()
  {
    return m_sDisplayName;
  }

  @Nullable
  public static EToopDataElementResponseErrorCode getFromIDOrNull (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrNull (EToopDataElementResponseErrorCode.class, sID);
  }
}
