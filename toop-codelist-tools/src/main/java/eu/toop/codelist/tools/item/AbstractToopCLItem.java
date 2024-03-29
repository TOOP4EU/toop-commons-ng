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
package eu.toop.codelist.tools.item;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.string.StringHelper;
import com.helger.xml.microdom.IMicroElement;

/**
 * Abstract code list item
 *
 * @author Philip Helger
 */
public abstract class AbstractToopCLItem implements Serializable
{
  public static final boolean DEFAULT_DEPRECATED = false;

  private final String m_sName;
  private final String m_sID;
  private final String m_sSince;
  private final boolean m_bDeprecated;
  private final String m_sDeprecatedSince;

  public AbstractToopCLItem (@Nonnull @Nonempty final String sName,
                             @Nonnull @Nonempty final String sID,
                             @Nonnull @Nonempty final String sSince,
                             final boolean bDeprecated,
                             @Nullable final String sDeprecatedSince)
  {
    ValueEnforcer.notEmpty (sName, "Name");
    ValueEnforcer.notEmpty (sID, "ID");
    ValueEnforcer.notEmpty (sSince, "Since");
    if (bDeprecated && StringHelper.hasNoText (sDeprecatedSince))
      throw new IllegalStateException ("Code list entry is deprecated but there is no deprecated-since entry");

    m_sName = sName;
    m_sID = sID;
    m_sSince = sSince;
    m_bDeprecated = bDeprecated;
    m_sDeprecatedSince = sDeprecatedSince;
  }

  @Nonnull
  @Nonempty
  public final String getName ()
  {
    return m_sName;
  }

  @Nonnull
  @Nonempty
  public final String getID ()
  {
    return m_sID;
  }

  @Nonnull
  @Nonempty
  public final String getSince ()
  {
    return m_sSince;
  }

  public final boolean isDeprecated ()
  {
    return m_bDeprecated;
  }

  @Nullable
  public final String getDeprecatedSince ()
  {
    return m_sDeprecatedSince;
  }

  protected void fillMicroElement (@Nonnull final IMicroElement aElement)
  {
    aElement.setAttribute ("name", m_sName);
    aElement.setAttribute ("id", m_sID);
    aElement.setAttribute ("since", m_sSince);
    aElement.setAttribute ("deprecated", m_bDeprecated);
    aElement.setAttribute ("deprecated-since", m_sDeprecatedSince);
  }
}
