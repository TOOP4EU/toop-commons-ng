/**
 * This work is protected under copyrights held by the members of the
 * TOOP Project Consortium as indicated at
 * http://wiki.ds.unipi.gr/display/TOOP/Contributors
 * (c) 2018-2021. All rights reserved.
 *
 * This work is licensed under the EUPL 1.2.
 *
 *  = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
 *
 * Licensed under the EUPL, Version 1.2 or – as soon they will be approved
 * by the European Commission - subsequent versions of the EUPL
 * (the "Licence");
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 *
 *         https://joinup.ec.europa.eu/software/page/eupl
 */
package eu.toop.edm.model;

import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;

import eu.toop.edm.jaxb.w3.cv.ac.CoreBusinessType;
import eu.toop.edm.jaxb.w3.cv.bc.LegalEntityIDType;
import eu.toop.edm.jaxb.w3.cv.bc.LegalEntityLegalIDType;
import eu.toop.edm.jaxb.w3.cv.bc.LegalEntityLegalNameType;

/**
 * Represents a "Business"
 *
 * @author Philip Helger
 */
@Immutable
public class BusinessPojo
{
  private final String m_sLegalID;
  private final String m_sLegalIDSchemeID;
  private final String m_sID;
  private final String m_sIDSchemeID;
  private final String m_sLegalName;
  private final AddressPojo m_aAddress;

  public BusinessPojo (@Nullable final String sLegalID,
                       @Nullable final String sLegalIDSchemeID,
                       @Nullable final String sID,
                       @Nullable final String sIDSchemeID,
                       @Nullable final String sLegalName,
                       @Nullable final AddressPojo aAddress)
  {
    m_sLegalID = sLegalID;
    m_sLegalIDSchemeID = sLegalIDSchemeID;
    m_sID = sID;
    m_sIDSchemeID = sIDSchemeID;
    m_sLegalName = sLegalName;
    m_aAddress = aAddress;
  }

  @Nullable
  public final String getLegalID ()
  {
    return m_sLegalID;
  }

  @Nullable
  public final String getLegalIDSchemeID ()
  {
    return m_sLegalIDSchemeID;
  }

  @Nullable
  public final String getID ()
  {
    return m_sID;
  }

  @Nullable
  public final String getIDSchemeID ()
  {
    return m_sIDSchemeID;
  }

  @Nullable
  public final String getLegalName ()
  {
    return m_sLegalName;
  }

  @Nullable
  public final AddressPojo getAddress ()
  {
    return m_aAddress;
  }

  @Nonnull
  public CoreBusinessType getAsCoreBusiness ()
  {
    final CoreBusinessType ret = new CoreBusinessType ();

    if (StringHelper.hasText (m_sLegalID))
    {
      final LegalEntityLegalIDType aLegalID = new LegalEntityLegalIDType ();
      aLegalID.setValue (m_sLegalID);
      aLegalID.setSchemeID (m_sLegalIDSchemeID);
      ret.addLegalEntityLegalID (aLegalID);
    }
    if (StringHelper.hasText (m_sID))
    {
      final LegalEntityIDType aLegalID = new LegalEntityIDType ();
      aLegalID.setValue (m_sID);
      aLegalID.setSchemeID (m_sIDSchemeID);
      ret.addLegalEntityID (aLegalID);
    }
    if (StringHelper.hasText (m_sLegalName))
    {
      final LegalEntityLegalNameType aLegalName = new LegalEntityLegalNameType ();
      aLegalName.setValue (m_sLegalName);
      ret.addLegalEntityLegalName (aLegalName);
    }
    if (m_aAddress != null)
      ret.setLegalEntityCoreAddress (m_aAddress.getAsCoreAddress ());

    return ret;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final BusinessPojo rhs = (BusinessPojo) o;
    return EqualsHelper.equals (m_sLegalID, rhs.m_sLegalID) &&
           EqualsHelper.equals (m_sLegalIDSchemeID, rhs.m_sLegalIDSchemeID) &&
           EqualsHelper.equals (m_sID, rhs.m_sID) &&
           EqualsHelper.equals (m_sIDSchemeID, rhs.m_sIDSchemeID) &&
           EqualsHelper.equals (m_sLegalName, rhs.m_sLegalName) &&
           EqualsHelper.equals (m_aAddress, rhs.m_aAddress);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sLegalID)
                                       .append (m_sLegalIDSchemeID)
                                       .append (m_sID)
                                       .append (m_sIDSchemeID)
                                       .append (m_sLegalName)
                                       .append (m_aAddress)
                                       .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("LegalID", m_sLegalID)
                                       .append ("LegalIDSchemeID", m_sLegalIDSchemeID)
                                       .append ("sID", m_sID)
                                       .append ("IDSchemeID", m_sIDSchemeID)
                                       .append ("LegalName", m_sLegalName)
                                       .append ("Address", m_aAddress)
                                       .getToString ();
  }

  @Nonnull
  public static Builder builder ()
  {
    return new Builder ();
  }

  @Nonnull
  public static Builder builder (@Nullable final CoreBusinessType a)
  {
    final Builder ret = new Builder ();
    if (a != null)
    {
      if (a.hasLegalEntityLegalIDEntries ())
      {
        final LegalEntityLegalIDType aItem = a.getLegalEntityLegalIDAtIndex (0);
        ret.legalID (aItem.getValue ()).legalIDSchemeID (aItem.getSchemeID ());
      }
      if (a.hasLegalEntityIDEntries ())
      {
        final LegalEntityIDType aItem = a.getLegalEntityIDAtIndex (0);
        ret.id (aItem.getValue ()).idSchemeID (aItem.getSchemeID ());
      }
      if (a.hasLegalEntityLegalNameEntries ())
        ret.legalName (a.getLegalEntityLegalNameAtIndex (0).getValue ());
      if (a.getLegalEntityCoreAddress () != null)
        ret.address (AddressPojo.builder (a.getLegalEntityCoreAddress ()));
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
    private String m_sLegalID;
    private String m_sLegalIDSchemeID;
    private String m_sID;
    private String m_sIDSchemeID;
    private String m_sLegalName;
    private AddressPojo m_aAddress;

    public Builder ()
    {}

    @Nonnull
    public Builder legalID (@Nullable final String s)
    {
      m_sLegalID = s;
      return this;
    }

    @Nonnull
    public Builder legalIDSchemeID (@Nullable final EToopIdentifierType e)
    {
      return legalIDSchemeID (e == null ? null : e.getID ());
    }

    @Nonnull
    public Builder legalIDSchemeID (@Nullable final String s)
    {
      m_sLegalIDSchemeID = s;
      return this;
    }

    @Nonnull
    public Builder id (@Nullable final String s)
    {
      m_sID = s;
      return this;
    }

    @Nonnull
    public Builder idSchemeID (@Nullable final EToopIdentifierType e)
    {
      return idSchemeID (e == null ? null : e.getID ());
    }

    @Nonnull
    public Builder idSchemeID (@Nullable final String s)
    {
      m_sIDSchemeID = s;
      return this;
    }

    @Nonnull
    public Builder legalName (@Nullable final String s)
    {
      m_sLegalName = s;
      return this;
    }

    @Nonnull
    public Builder address (@Nullable final Consumer <? super AddressPojo.Builder> a)
    {
      if (a != null)
      {
        final AddressPojo.Builder aBuilder = AddressPojo.builder ();
        a.accept (aBuilder);
        address (aBuilder);
      }
      return this;
    }

    @Nonnull
    public Builder address (@Nullable final AddressPojo.Builder a)
    {
      return address (a == null ? null : a.build ());
    }

    @Nonnull
    public Builder address (@Nullable final AddressPojo a)
    {
      m_aAddress = a;
      return this;
    }

    @Nonnull
    public BusinessPojo build ()
    {
      return new BusinessPojo (m_sLegalID, m_sLegalIDSchemeID, m_sID, m_sIDSchemeID, m_sLegalName, m_aAddress);
    }
  }
}
