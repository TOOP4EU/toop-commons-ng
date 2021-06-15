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
import com.helger.regrep.rim.InternationalStringType;
import com.helger.regrep.rim.SlotType;
import com.helger.regrep.slot.ISlotProvider;
import com.helger.regrep.slot.SlotBuilder;

/**
 * "Procedure" slot
 *
 * @author Philip Helger
 */
public class SlotProcedure implements ISlotProvider
{
  public static final String NAME = "Procedure";

  private final InternationalStringType m_aProcedure;

  public SlotProcedure (@Nonnull final InternationalStringType aProcedure)
  {
    ValueEnforcer.notNull (aProcedure, "Procedure");
    m_aProcedure = aProcedure;
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
    return new SlotBuilder ().setName (NAME).setValue (m_aProcedure).build ();
  }
}
