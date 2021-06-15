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

import javax.annotation.Nonnull;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.mock.CommonsTestHelper;

import eu.toop.edm.jaxb.w3.cv.ac.CorePersonType;
import eu.toop.edm.xml.cv.PersonMarshaller;

/**
 * Test class for class {@link PersonPojo}.
 *
 * @author Philip Helger
 */
public final class PersonPojoTest
{
  private static final Logger LOGGER = LoggerFactory.getLogger (PersonPojoTest.class);

  private static void _testWriteAndRead (@Nonnull final PersonPojo x)
  {
    assertNotNull (x);

    final CorePersonType aObj = x.getAsCorePerson ();
    assertNotNull (aObj);
    CommonsTestHelper.testDefaultImplementationWithEqualContentObject (aObj, aObj.clone ());

    final PersonMarshaller m = new PersonMarshaller ();
    m.setFormattedOutput (true);
    assertNotNull (m.getAsDocument (aObj));
    if (false)
      LOGGER.info (m.getAsString (aObj));

    final PersonPojo y = PersonPojo.builder (aObj).build ();
    CommonsTestHelper.testDefaultImplementationWithEqualContentObject (x, y);
  }

  @Test
  public void testBasic ()
  {
    final AddressPojo a = AddressPojo.builder ()
                                     .fullAddress ("FullAddress")
                                     .streetName ("StreetName")
                                     .buildingNumber ("BuildingNumber")
                                     .town ("Town")
                                     .postalCode ("PostalCode")
                                     .countryCode ("CountryCode")
                                     .build ();
    final PersonPojo x = PersonPojo.builder ()
                                   .id ("ID")
                                   .idSchemeID ("idSchemeID")
                                   .familyName ("FamilyName")
                                   .givenName ("GivenName")
                                   .genderCode (EToopGenderCode.M)
                                   .birthName ("BirthName")
                                   .birthDate (PDTFactory.getCurrentLocalDate ())
                                   .address (a)
                                   .build ();
    _testWriteAndRead (x);
  }

  @Test
  public void testMinimum ()
  {
    final PersonPojo x = PersonPojo.builder ()
                                   .id ("ID")
                                   .idSchemeID ("idSchemeID")
                                   .familyName ("FamilyName")
                                   .givenName ("GivenName")
                                   .birthDate (PDTFactory.getCurrentLocalDate ())

                                   .build ();
    _testWriteAndRead (x);
  }
}
