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
 * "ConceptValues" slot
 *
 * @author Philip Helger
 */
public class SlotConceptValues implements ISlotProvider
{
  public static final String NAME = "ConceptValues";

  private final ICommonsList <ConceptPojo> m_aConcepts = new CommonsArrayList <> ();

  public SlotConceptValues (@Nonnull final ConceptPojo... aConcepts)
  {
    ValueEnforcer.noNullValue (aConcepts, "Concepts");
    m_aConcepts.addAll (aConcepts);
  }

  public SlotConceptValues (@Nonnull final Iterable <ConceptPojo> aConcepts)
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
