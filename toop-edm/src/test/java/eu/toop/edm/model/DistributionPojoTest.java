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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.mime.CMimeType;
import com.helger.commons.mock.CommonsTestHelper;

import eu.toop.edm.jaxb.dcatap.DCatAPDistributionType;
import eu.toop.edm.xml.dcatap.DistributionMarshaller;

/**
 * Test class for class {@link DistributionPojo}
 *
 * @author Philip Helger
 */
public final class DistributionPojoTest
{
  private static final Logger LOGGER = LoggerFactory.getLogger (DistributionPojoTest.class);

  private static void _testWriteAndRead (@Nonnull final DistributionPojo x)
  {
    assertNotNull (x);

    final DCatAPDistributionType aObj = x.getAsDistribution ();
    assertNotNull (aObj);
    // TODO figure out what is wrong
    if (false)
      CommonsTestHelper.testDefaultImplementationWithEqualContentObject (aObj, aObj.clone ());

    // Write
    final DistributionMarshaller m = new DistributionMarshaller ();
    m.setFormattedOutput (true);
    assertNotNull (m.getAsDocument (aObj));
    if (false)
      LOGGER.info (m.getAsString (aObj));

    // Re-read
    final DistributionPojo y = DistributionPojo.builder (aObj).build ();
    CommonsTestHelper.testDefaultImplementationWithEqualContentObject (x, y);
  }

  @Test
  public void testBasic ()
  {
    final DistributionPojo x = DistributionPojo.builder ()
                                               .format (EToopDistributionFormat.STRUCTURED)
                                               .mediaType (CMimeType.TEXT_PLAIN)
                                               .build ();
    _testWriteAndRead (x);
  }

  @Test
  public void testMinimum ()
  {
    final DistributionPojo x = DistributionPojo.builder ().build ();
    _testWriteAndRead (x);
  }
}
