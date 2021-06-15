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
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.regrep.slot.ISlotProvider;

import eu.toop.edm.model.ConceptPojo;
import eu.toop.edm.slot.SlotConceptRequestList;

/**
 * Request payload: Concepts
 *
 * @author Philip Helger
 * @since 2.0.0-beta3
 */
public class EDMRequestPayloadConcepts implements IEDMRequestPayloadConcepts
{
  private final ICommonsList <ConceptPojo> m_aConcepts = new CommonsArrayList <> ();

  public EDMRequestPayloadConcepts (@Nonnull @Nonempty final ICommonsList <ConceptPojo> aConcepts)
  {
    ValueEnforcer.notEmpty (aConcepts, "Concepts");
    m_aConcepts.addAll (aConcepts);
  }

  @Nonnull
  @ReturnsMutableObject
  public final ICommonsList <ConceptPojo> concepts ()
  {
    return m_aConcepts;
  }

  @Nonnull
  @ReturnsMutableCopy
  public final ICommonsList <ConceptPojo> getAllConcepts ()
  {
    return m_aConcepts.getClone ();
  }

  @Nonnull
  public ISlotProvider getAsSlotProvider ()
  {
    return new SlotConceptRequestList (m_aConcepts);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final EDMRequestPayloadConcepts rhs = (EDMRequestPayloadConcepts) o;
    return EqualsHelper.equals (m_aConcepts, rhs.m_aConcepts);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aConcepts).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("Concepts", m_aConcepts).getToString ();
  }
}
