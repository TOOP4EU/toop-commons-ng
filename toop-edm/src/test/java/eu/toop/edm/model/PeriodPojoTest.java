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

import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.mock.CommonsTestHelper;

import eu.toop.edm.jaxb.cv.cac.PeriodType;

/**
 * Test class for class {@link PeriodPojo}
 *
 * @author Philip Helger
 */
public final class PeriodPojoTest
{
  private static void _testWriteAndRead (@Nonnull final PeriodPojo x)
  {
    assertNotNull (x);

    final PeriodType aObj = x.getAsPeriod ();
    assertNotNull (aObj);
    CommonsTestHelper.testDefaultImplementationWithEqualContentObject (aObj, aObj.clone ());

    // Re-read
    final PeriodPojo y = PeriodPojo.builder (aObj).build ();
    CommonsTestHelper.testDefaultImplementationWithEqualContentObject (x, y);
  }

  @Test
  public void testBasic ()
  {
    final PeriodPojo x = PeriodPojo.builder ()
                                   .startDateTime (PDTFactory.getCurrentLocalDateTime ().minusMonths (1))
                                   .endDateTime (PDTFactory.getCurrentLocalDateTime ().plusMonths (7))
                                   .build ();
    _testWriteAndRead (x);
  }

  @Test
  public void testDateOnly ()
  {
    final PeriodPojo x = PeriodPojo.builder ()
                                   .startDate (PDTFactory.getCurrentLocalDate ().minusMonths (1))
                                   .endDate (PDTFactory.getCurrentLocalDate ().plusMonths (7))
                                   .build ();
    _testWriteAndRead (x);
  }

  @Test
  public void testMinimum ()
  {
    final PeriodPojo x = PeriodPojo.builder ().build ();
    _testWriteAndRead (x);
  }
}
