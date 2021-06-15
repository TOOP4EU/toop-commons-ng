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
import com.helger.commons.id.IHasID;
import com.helger.commons.lang.EnumHelper;
import com.helger.commons.state.IErrorIndicator;

/**
 * Source: ErrorSeverity-CodeList.gc
 *
 * @author Philip Helger
 */
public enum EToopErrorSeverity implements IHasID <String>, IErrorIndicator
{
  WARNING ("WARNING"),
  FAILURE ("FAILURE");

  private final String m_sID;

  EToopErrorSeverity (@Nonnull @Nonempty final String sID)
  {
    m_sID = sID;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  public boolean isError ()
  {
    return this == FAILURE;
  }

  @Nullable
  public static EToopErrorSeverity getFromIDOrNull (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrNull (EToopErrorSeverity.class, sID);
  }
}
