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
package eu.toop.edm.slot;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.regrep.rim.SlotType;
import com.helger.regrep.slot.ERegRepCollectionType;
import com.helger.regrep.slot.ISlotProvider;
import com.helger.regrep.slot.SlotBuilder;
import com.helger.regrep.slot.SlotHelper;

import eu.toop.edm.model.ConceptPojo;
import eu.toop.edm.xml.cccev.ConceptMarshaller;

/**
 * "ConceptRequestList" slot
 *
 * @author Philip Helger
 */
public class SlotConceptRequestList implements ISlotProvider
{
  public static final String NAME = "ConceptRequestList";

  private final ICommonsList <ConceptPojo> m_aConcepts = new CommonsArrayList <> ();

  public SlotConceptRequestList (@Nonnull final ConceptPojo... aConcepts)
  {
    ValueEnforcer.noNullValue (aConcepts, "Concepts");
    m_aConcepts.addAll (aConcepts);
  }

  public SlotConceptRequestList (@Nonnull final Iterable <ConceptPojo> aConcepts)
  {
    ValueEnforcer.noNullValue (aConcepts, "Concepts");
    m_aConcepts.addAll (aConcepts);
  }

  @Nonnull
  @Nonempty
  public String getName ()
  {
    return NAME;
  }

  @Nonnull
  public SlotType createSlot ()
  {
    final ConceptMarshaller m = new ConceptMarshaller ();
    return new SlotBuilder ().setName (NAME)
                             .setValue (ERegRepCollectionType.SET,
                                        m_aConcepts.getAllMapped (x -> SlotHelper.createSlotValue (m.getAsDocument (x.getAsCCCEVConcept ())
                                                                                                    .getDocumentElement ())))
                             .build ();
  }
}
