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
package eu.toop.commons.playground;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.helger.security.keystore.KeyStoreHelper;
import com.helger.security.keystore.LoadedKeyStore;

/**
 * Test class for class {@link CToopPlayground}.
 *
 * @author Philip Helger
 */
public final class CToopPlaygroundTest
{
  @Test
  public void testLoadTrustStore ()
  {
    assertTrue (CToopPlayground.PATH_PLAYGROUND_TRUST_STORE.exists ());

    final LoadedKeyStore aKS = KeyStoreHelper.loadKeyStore (CToopPlayground.TYPE_PLAYGROUND_TRUST_STORE,
                                                            CToopPlayground.PATH_PLAYGROUND_TRUST_STORE.getPath (),
                                                            CToopPlayground.PASSWORD_PLAYGROUND_TRUST_STORE);
    assertNotNull (aKS);
  }
}
