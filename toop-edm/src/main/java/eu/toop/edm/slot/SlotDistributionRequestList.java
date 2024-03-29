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

import eu.toop.edm.model.DistributionPojo;
import eu.toop.edm.xml.dcatap.DistributionMarshaller;

/**
 * "DistributionRequestList" slot
 *
 * @author Philip Helger
 */
public class SlotDistributionRequestList implements ISlotProvider
{
  public static final String NAME = "DistributionRequestList";

  private final ICommonsList <DistributionPojo> m_aDistributions = new CommonsArrayList <> ();

  public SlotDistributionRequestList (@Nonnull final DistributionPojo... aDistributions)
  {
    ValueEnforcer.noNullValue (aDistributions, "Distributions");
    m_aDistributions.addAll (aDistributions);
  }

  public SlotDistributionRequestList (@Nonnull final Iterable <DistributionPojo> aDistributions)
  {
    ValueEnforcer.noNullValue (aDistributions, "Distributions");
    m_aDistributions.addAll (aDistributions);
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
    final DistributionMarshaller m = new DistributionMarshaller ();
    return new SlotBuilder ().setName (NAME)
                             .setValue (ERegRepCollectionType.SORTED_SET,
                                        m_aDistributions.getAllMapped (x -> SlotHelper.createSlotValue (m.getAsDocument (x.getAsDistribution ())
                                                                                                         .getDocumentElement ())))
                             .build ();
  }
}
