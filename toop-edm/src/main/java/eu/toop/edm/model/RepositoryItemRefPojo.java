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
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.regrep.rim.SimpleLinkType;

/**
 * Represents a single "Repository item reference" object.
 *
 * @author Konstantinos Douloudis
 * @author Philip Helger
 */
@Immutable
public class RepositoryItemRefPojo
{
  private final String m_sTitle;
  private final String m_sLink;

  public RepositoryItemRefPojo (@Nullable final String sTitle, @Nullable final String sLink)
  {
    m_sTitle = sTitle;
    m_sLink = sLink;
  }

  @Nullable
  public final String getTitle ()
  {
    return m_sTitle;
  }

  @Nullable
  public final String getLink ()
  {
    return m_sLink;
  }

  @Nonnull
  public SimpleLinkType getAsSimpleLink ()
  {
    final SimpleLinkType ret = new SimpleLinkType ();
    if (m_sTitle != null)
      ret.setTitle (m_sTitle);
    if (m_sLink != null)
      ret.setHref (m_sLink);
    return ret;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final RepositoryItemRefPojo rhs = (RepositoryItemRefPojo) o;
    return EqualsHelper.equals (m_sTitle, rhs.m_sTitle) && EqualsHelper.equals (m_sLink, rhs.m_sLink);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sTitle).append (m_sLink).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("Title", m_sTitle).append ("Link", m_sLink).getToString ();
  }

  @Nonnull
  public static RepositoryItemRefPojo.Builder builder ()
  {
    return new RepositoryItemRefPojo.Builder ();
  }

  @Nonnull
  public static RepositoryItemRefPojo.Builder builder (@Nullable final SimpleLinkType a)
  {
    final RepositoryItemRefPojo.Builder ret = new RepositoryItemRefPojo.Builder ();
    if (a != null)
    {
      ret.title (a.getTitle ()).link (a.getHref ());
    }
    return ret;
  }

  /**
   * A builder for this class
   *
   * @author Philip Helger
   */
  @NotThreadSafe
  public static class Builder
  {
    private String m_sTitle;
    private String m_sLink;

    public Builder ()
    {}

    @Nonnull
    public Builder title (@Nullable final String sTitle)
    {
      m_sTitle = sTitle;
      return this;
    }

    @Nonnull
    public Builder link (@Nullable final String sLink)
    {
      m_sLink = sLink;
      return this;
    }

    @Nonnull
    public RepositoryItemRefPojo build ()
    {
      return new RepositoryItemRefPojo (m_sTitle, m_sLink);
    }
  }
}
