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
import com.helger.regrep.rim.SlotType;
import com.helger.regrep.slot.ISlotProvider;
import com.helger.regrep.slot.SlotBuilder;

/**
 * Error "ErrorOrigin" slot
 *
 * @author Philip Helger
 */
public class SlotErrorOrigin implements ISlotProvider
{
  public static final String NAME = "ErrorOrigin";

  private final String m_sValue;

  public SlotErrorOrigin (@Nonnull final String sValue)
  {
    ValueEnforcer.notNull (sValue, "Value");
    m_sValue = sValue;
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
    return new SlotBuilder ().setName (NAME).setValue (m_sValue).build ();
  }
}
