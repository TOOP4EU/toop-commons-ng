/**
 * This work is protected under copyrights held by the members of the
 * TOOP Project Consortium as indicated at
 * http://wiki.ds.unipi.gr/display/TOOP/Contributors
 * (c) 2018-2021. All rights reserved.
 *
 * This work is dual licensed under Apache License, Version 2.0
 * and the EUPL 1.2.
 *
 *  = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
