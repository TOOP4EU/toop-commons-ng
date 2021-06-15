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

import javax.annotation.concurrent.Immutable;

import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.security.keystore.EKeyStoreType;
import com.helger.security.keystore.IKeyStoreType;

/**
 * Playground constants
 *
 * @author Philip Helger
 */
@Immutable
public final class CToopPlayground
{
  /** Trust store file type */
  public static final IKeyStoreType TYPE_PLAYGROUND_TRUST_STORE = EKeyStoreType.JKS;
  /** Trust store classpath */
  public static final IReadableResource PATH_PLAYGROUND_TRUST_STORE = new ClassPathResource ("/truststore/playground-truststore-v5.jks",
                                                                                             CToopPlayground.class.getClassLoader ());
  /** Trust store password */
  public static final String PASSWORD_PLAYGROUND_TRUST_STORE = "toop4eu";

  private CToopPlayground ()
  {}
}
