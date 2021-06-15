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
