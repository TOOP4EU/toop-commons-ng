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

import javax.annotation.Nonnull;

import com.helger.commons.annotation.MustImplementEqualsAndHashcode;
import com.helger.commons.annotation.Nonempty;
import com.helger.regrep.rim.ObjectRefType;
import com.helger.regrep.rim.RegistryObjectType;

/**
 * Abstract EDM Response payload provider.
 *
 * @author Philip Helger
 * @since 2.0.0-beta3
 */
@MustImplementEqualsAndHashcode
public interface IEDMResponsePayloadProvider
{
  @Nonnull
  @Nonempty
  String getRegistryObjectID ();

  @Nonnull
  default RegistryObjectType getAsRegistryObject ()
  {
    throw new UnsupportedOperationException ();
  }

  @Nonnull
  default ObjectRefType getAsObjectRef ()
  {
    throw new UnsupportedOperationException ();
  }
}
