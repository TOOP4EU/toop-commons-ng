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
package eu.toop.edm.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.annotation.Nonnull;

import org.junit.Test;

import com.helger.commons.mock.CommonsTestHelper;

import eu.toop.edm.jaxb.cv.cac.AddressType;
import eu.toop.edm.jaxb.w3.cv.ac.CoreAddressType;

/**
 * Test class for class {@link AddressPojo}
 *
 * @author Philip Helger
 */
public final class AddressPojoTest
{
  private static void _testWriteAndRead (@Nonnull final AddressPojo x)
  {
    assertNotNull (x);

    // one version
    final CoreAddressType aObj = x.getAsCoreAddress ();
    assertNotNull (aObj);
    CommonsTestHelper.testDefaultImplementationWithEqualContentObject (aObj, aObj.clone ());

    AddressPojo y = AddressPojo.builder (aObj).build ();
    CommonsTestHelper.testDefaultImplementationWithEqualContentObject (x, y);

    // Another version
    final AddressType aObj2 = x.getAsAgentAddress ();
    assertNotNull (aObj2);
    CommonsTestHelper.testDefaultImplementationWithEqualContentObject (aObj2, aObj2.clone ());

    y = AddressPojo.builder (aObj2).build ();
    CommonsTestHelper.testDefaultImplementationWithEqualContentObject (x, y);
  }

  @Test
  public void testBasic ()
  {
    final AddressPojo x = AddressPojo.builder ()
                                     .fullAddress ("FullAddress")
                                     .streetName ("StreetName")
                                     .buildingNumber ("BuildingNumber")
                                     .town ("Town")
                                     .postalCode ("PostalCode")
                                     .countryCode ("CountryCode")
                                     .build ();
    _testWriteAndRead (x);
  }

  @Test
  public void testMinimum ()
  {
    final AddressPojo x = AddressPojo.builder ().fullAddress ("FullAddress").build ();
    _testWriteAndRead (x);
  }

  @Test
  public void testEmpty ()
  {
    final AddressPojo x = AddressPojo.builder ().build ();
    assertNotNull (x);
    assertNull (x.getAsCoreAddress ());
    assertNull (x.getAsAgentAddress ());
  }
}
