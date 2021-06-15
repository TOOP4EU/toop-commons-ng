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
package eu.toop.edm.pilot.gbm;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.helger.commons.regex.RegExHelper;
import com.helger.commons.string.StringHelper;

/**
 * Test class for class {@link EToopConcept}.
 *
 * @author Philip Helger
 */
public final class EToopConceptTest
{
  @Test
  public void testBasic ()
  {
    for (final EToopConcept e : EToopConcept.values ())
    {
      assertTrue (StringHelper.hasText (e.getID ()));
      assertSame (e, EToopConcept.getFromIDOrNull (e.getID ()));
    }
  }

  @Test
  public void testOldNames ()
  {
    // Taken from
    // https://docs.google.com/spreadsheets/d/1E5vPDWnfcA5cCGxzQL3tB5QZy7V65PuXoibnIN8Fop8/edit#gid=27052854
    // column d
    final String sOldNames = "CompanyCode\r\n" +
                             "CompanyName\r\n" +
                             "CompanyType\r\n" +
                             "FoundationDate\r\n" +
                             "RegistrationAuthority\r\n" +
                             "RegistrationDate\r\n" +
                             "RegistrationNumber\r\n" +
                             "SSNumber\r\n" +
                             "VATNumber\r\n" +
                             "CapitalType\r\n" +
                             "LegalStatus\r\n" +
                             "LegalStatusEffectiveDate\r\n" +
                             "ActivityDescription\r\n" +
                             "NaceCode\r\n" +
                             "CountryName\r\n" +
                             "Locality\r\n" +
                             "PostalCode\r\n" +
                             "Region\r\n" +
                             "StreetAddress\r\n" +
                             "EmailAddress\r\n" +
                             "FaxNumber\r\n" +
                             "TelephoneNumber\r\n" +
                             "\r\n" +
                             "LegalRepresentativeBirthDate\r\n" +
                             "LegalRepresentativeFamilyName\r\n" +
                             "LegalRepresentativeGivenName";
    for (final String s : RegExHelper.getSplitToArray (sOldNames, "\r\n"))
      if (StringHelper.hasText (s))
        assertNotNull (EToopConcept.getFromIDOrNull (s));
  }
}
