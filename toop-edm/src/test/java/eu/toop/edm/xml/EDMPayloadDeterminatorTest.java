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
