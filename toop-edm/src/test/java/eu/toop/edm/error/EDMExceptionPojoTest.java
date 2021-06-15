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
package eu.toop.edm.error;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Nonnull;

import org.junit.Test;

import com.helger.commons.mock.CommonsTestHelper;
import com.helger.regrep.RegRep4Reader;
import com.helger.regrep.RegRep4Writer;

/**
 * Test class for class {@link EDMExceptionPojo}.
 *
 * @author Philip Helger
 */
public final class EDMExceptionPojoTest
{
  private static void _testWriteAndRead (@Nonnull final EDMExceptionPojo aEx)
  {
    assertNotNull (aEx);

    // Write
    final byte [] aBytes = RegRep4Writer.registryException ().getAsBytes (aEx.getAsRegistryException ());
    assertNotNull (aBytes);

    // Re-read
    final EDMExceptionPojo aResp2 = EDMExceptionPojo.builder (RegRep4Reader.registryException ().read (aBytes)).build ();
    assertNotNull (aResp2);

    // Compare with original
    assertEquals (aEx, aResp2);
    CommonsTestHelper.testDefaultImplementationWithEqualContentObject (aEx, aResp2);
  }

  @Test
  public void testBasic ()
  {
    for (final EEDMExceptionType eType : EEDMExceptionType.values ())
    {
      final EDMExceptionPojo aEx = EDMExceptionPojo.builder ()
                                                   .exceptionType (eType)
                                                   .errorCode ("ec1")
                                                   .errorDetail ("Stacktrace")
                                                   .errorMessage ("What went wrong: nothing")
                                                   .severity (EToopErrorSeverity.FAILURE)
                                                   .timestampNow ()
                                                   .errorOrigin (EToopErrorOrigin.RESPONSE_RECEPTION)
                                                   .build ();
      _testWriteAndRead (aEx);
    }
  }

  @Test
  public void testMinimum ()
  {
    final EDMExceptionPojo aEx = EDMExceptionPojo.builder ()
                                                 .exceptionType (EEDMExceptionType.OBJECT_NOT_FOUND)
                                                 .severityFailure ()
                                                 .errorMessage ("What went wrong: nothing")
                                                 .timestampNow ()
                                                 .build ();
    _testWriteAndRead (aEx);
  }
}
