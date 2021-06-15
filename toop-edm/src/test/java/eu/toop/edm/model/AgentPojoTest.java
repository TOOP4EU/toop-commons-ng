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
package eu.toop.edm.model;

import static org.junit.Assert.assertNotNull;

import javax.annotation.Nonnull;

import org.junit.Test;

import com.helger.commons.mock.CommonsTestHelper;

import eu.toop.edm.jaxb.cv.agent.AgentType;
import eu.toop.edm.xml.cagv.AgentMarshaller;

/**
 * Test class for class {@link AgentPojo}
 *
 * @author Philip Helger
 */
public final class AgentPojoTest
{
  private static void _testWriteAndRead (@Nonnull final AgentPojo x)
  {
    assertNotNull (x);

    final AgentType aObj = x.getAsAgent ();
    assertNotNull (aObj);
    CommonsTestHelper.testDefaultImplementationWithEqualContentObject (aObj, aObj.clone ());

    final AgentMarshaller m = new AgentMarshaller ();
    m.setFormattedOutput (true);
    assertNotNull (m.getAsDocument (aObj));

    final AgentPojo y = AgentPojo.builder (aObj).build ();
    CommonsTestHelper.testDefaultImplementationWithEqualContentObject (x, y);
  }

  @Test
  public void testBasic ()
  {
    final AgentPojo x = AgentPojo.builder ()
                                 .id ("ID")
                                 .idSchemeID ("IDType")
                                 .name ("Name")
                                 .address (y -> y.fullAddress ("FullAddress")
                                                 .streetName ("StreetName")
                                                 .buildingNumber ("BuildingNumber")
                                                 .town ("Town")
                                                 .postalCode ("PostalCode")
                                                 .countryCode ("CountryCode"))
                                 .build ();
    _testWriteAndRead (x);
  }

  @Test
  public void testMinimum ()
  {
    final AgentPojo x = AgentPojo.builder ().build ();
    _testWriteAndRead (x);
  }

  @Test
  public void testNoAddress ()
  {
    final AgentPojo x = AgentPojo.builder ().id ("ID").idSchemeID ("IDType").name ("Name").build ();
    _testWriteAndRead (x);
  }
}
