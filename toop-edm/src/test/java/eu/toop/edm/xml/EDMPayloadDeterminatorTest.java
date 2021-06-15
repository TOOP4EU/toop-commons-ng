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
package eu.toop.edm.xml;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.helger.commons.io.resource.ClassPathResource;

import eu.toop.edm.EDMErrorResponse;
import eu.toop.edm.EDMRequest;
import eu.toop.edm.EDMResponse;
import eu.toop.edm.IEDMTopLevelObject;

/**
 * Test class for class {@link EDMPayloadDeterminator}.
 *
 * @author Philip Helger
 */
public final class EDMPayloadDeterminatorTest
{
  @Test
  public void testGoodCases ()
  {
    IEDMTopLevelObject aTLO;

    // EDM Requests
    aTLO = EDMPayloadDeterminator.parseAndFind (ClassPathResource.getInputStream ("Concept Request_LP.xml"));
    assertNotNull (aTLO);
    assertTrue (aTLO instanceof EDMRequest);

    aTLO = EDMPayloadDeterminator.parseAndFind (ClassPathResource.getInputStream ("Concept Request_NP.xml"));
    assertNotNull (aTLO);
    assertTrue (aTLO instanceof EDMRequest);

    aTLO = EDMPayloadDeterminator.parseAndFind (ClassPathResource.getInputStream ("Document Request_LP.xml"));
    assertNotNull (aTLO);
    assertTrue (aTLO instanceof EDMRequest);

    aTLO = EDMPayloadDeterminator.parseAndFind (ClassPathResource.getInputStream ("Document Request_NP.xml"));
    assertNotNull (aTLO);
    assertTrue (aTLO instanceof EDMRequest);

    // EDM Responses
    aTLO = EDMPayloadDeterminator.parseAndFind (ClassPathResource.getInputStream ("Concept Response.xml"));
    assertNotNull (aTLO);
    assertTrue (aTLO instanceof EDMResponse);

    aTLO = EDMPayloadDeterminator.parseAndFind (ClassPathResource.getInputStream ("Document Response.xml"));
    assertNotNull (aTLO);
    assertTrue (aTLO instanceof EDMResponse);

    // EDM Error Responses
    aTLO = EDMPayloadDeterminator.parseAndFind (ClassPathResource.getInputStream ("Error Response 1.xml"));
    assertNotNull (aTLO);
    assertTrue (aTLO instanceof EDMErrorResponse);
  }

  @Test
  public void testBadCases ()
  {
    IEDMTopLevelObject aTLO;

    // EDM Requests
    aTLO = EDMPayloadDeterminator.parseAndFind (ClassPathResource.getInputStream ("Bogus.xml"));
    assertNull (aTLO);
  }
}
