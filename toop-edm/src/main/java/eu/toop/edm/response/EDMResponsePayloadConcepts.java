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
package eu.toop.edm.response;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.regrep.rim.ExtrinsicObjectType;

import eu.toop.edm.model.ConceptPojo;
import eu.toop.edm.slot.SlotConceptValues;

/**
 * Represents a single "Concept response" payload.
 *
 * @author Philip Helger
 * @since 2.0.0-beta3
 */
public class EDMResponsePayloadConcepts implements IEDMResponsePayloadConcepts
{
  private final String m_sRegistryObjectID;
  private final ICommonsList <ConceptPojo> m_aConcepts = new CommonsArrayList <> ();

  public EDMResponsePayloadConcepts (@Nonnull @Nonempty final String sRegistryObjectID,
                                     @Nullable final ICommonsList <ConceptPojo> aConcepts)
  {
    ValueEnforcer.notEmpty (sRegistryObjectID, "RegistryObjectID");
    ValueEnforcer.notEmpty (aConcepts, "Concepts");

    m_sRegistryObjectID = sRegistryObjectID;
    m_aConcepts.addAll (aConcepts);
  }

  @Nonnull
  @Nonempty
  public final String getRegistryObjectID ()
  {
    return m_sRegistryObjectID;
  }

  @Nonnull
  @ReturnsMutableObject
  public final List <ConceptPojo> concepts ()
  {
    return m_aConcepts;
  }

  @Nonnull
  @ReturnsMutableCopy
  public final List <ConceptPojo> getAllConcepts ()
  {
    return m_aConcepts.getClone ();
  }

  @Nonnull
  @Override
  public ExtrinsicObjectType getAsRegistryObject ()
  {
    final ExtrinsicObjectType ret = new ExtrinsicObjectType ();
    ret.setId (m_sRegistryObjectID);

    // ConceptValues
    ret.addSlot (new SlotConceptValues (m_aConcepts).createSlot ());

    return ret;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final EDMResponsePayloadConcepts rhs = (EDMResponsePayloadConcepts) o;
    return EqualsHelper.equals (m_sRegistryObjectID, rhs.m_sRegistryObjectID) && EqualsHelper.equals (m_aConcepts, rhs.m_aConcepts);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sRegistryObjectID).append (m_aConcepts).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("RegistryObjectID", m_sRegistryObjectID).append ("Concepts", m_aConcepts).getToString ();
  }
}
