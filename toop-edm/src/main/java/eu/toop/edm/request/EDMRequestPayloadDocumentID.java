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
package eu.toop.edm.request;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.regrep.slot.ISlotProvider;
import com.helger.regrep.slot.predefined.SlotId;

/**
 * Request payload: Document ID
 *
 * @author Philip Helger
 * @since 2.0.0-beta3
 */
public class EDMRequestPayloadDocumentID implements IEDMRequestPayloadDocumentID
{
  private final String m_sDocumentID;

  public EDMRequestPayloadDocumentID (@Nonnull @Nonempty final String sDocumentID)
  {
    ValueEnforcer.notEmpty (sDocumentID, "DocumentID");
    m_sDocumentID = sDocumentID;
  }

  @Nonnull
  @Nonempty
  public final String getDocumentID ()
  {
    return m_sDocumentID;
  }

  @Nonnull
  public ISlotProvider getAsSlotProvider ()
  {
    return new SlotId (m_sDocumentID);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final EDMRequestPayloadDocumentID rhs = (EDMRequestPayloadDocumentID) o;
    return EqualsHelper.equals (m_sDocumentID, rhs.m_sDocumentID);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sDocumentID).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("DocumentID", m_sDocumentID).getToString ();
  }
}
