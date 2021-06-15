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

import com.helger.commons.mock.CommonsTestHelper;

import eu.toop.edm.jaxb.dcatap.DCatAPRelationshipType;
import eu.toop.edm.xml.dcatap.QualifiedRelationshipMarshaller;

/**
 * Test class for class {@link QualifiedRelationPojo}
 *
 * @author Philip Helger
 */
public final class QualifiedRelationPojoTest
{
  private static final Logger LOGGER = LoggerFactory.getLogger (QualifiedRelationPojoTest.class);

  private static void _testWriteAndRead (@Nonnull final QualifiedRelationPojo x)
  {
    assertNotNull (x);

    final DCatAPRelationshipType aObj = x.getAsRelationship ();
    assertNotNull (aObj);
    CommonsTestHelper.testDefaultImplementationWithEqualContentObject (aObj, aObj.clone ());

    final QualifiedRelationshipMarshaller m = new QualifiedRelationshipMarshaller ();
    m.setFormattedOutput (true);
    assertNotNull (m.getAsDocument (aObj));
    if (false)
      LOGGER.info (m.getAsString (aObj));

    final QualifiedRelationPojo y = QualifiedRelationPojo.builder (aObj).build ();
    CommonsTestHelper.testDefaultImplementationWithEqualContentObject (x, y);
  }

  @Test
  public void testBasic ()
  {
    final QualifiedRelationPojo x = QualifiedRelationPojo.builder ()
                                                         .addDescription ("desc1")
                                                         .addDescription ("desc2")
                                                         .titles ("Title", "Title2")
                                                         .addID ("ID1")
                                                         .addID ("ID2")
                                                         .build ();
    _testWriteAndRead (x);
  }

  @Test
  public void testMinimum ()
  {
    final QualifiedRelationPojo x = QualifiedRelationPojo.builder ().description ("desc").title ("ti").build ();
    _testWriteAndRead (x);
  }
}
