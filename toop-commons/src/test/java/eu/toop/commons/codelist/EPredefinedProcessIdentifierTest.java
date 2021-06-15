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
package eu.toop.commons.codelist;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

/**
 * Test class for class {@link EPredefinedProcessIdentifier}.
 *
 * @author Philip Helger
 */
public final class EPredefinedProcessIdentifierTest
{
  @Test
  public void testBasic ()
  {
    for (final EPredefinedProcessIdentifier e : EPredefinedProcessIdentifier.values ())
    {
      assertNotNull (e.getName ());
      assertEquals (EPredefinedProcessIdentifier.PROCESS_SCHEME, e.getScheme ());
      assertNotNull (e.getID ());
      assertNotNull (e.getURIEncoded ());
      assertSame (e, EPredefinedProcessIdentifier.getFromProcessIdentifierOrNull (e.getID ()));
      assertSame (e, EPredefinedProcessIdentifier.getFromProcessIdentifierOrNull (e.getScheme (), e.getID ()));
    }
  }
}
