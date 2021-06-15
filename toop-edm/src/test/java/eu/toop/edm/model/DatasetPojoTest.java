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

import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.mock.CommonsTestHelper;

import eu.toop.edm.jaxb.dcatap.DCatAPDatasetType;
import eu.toop.edm.xml.dcatap.DatasetMarshaller;

/**
 * Test class for class {@link DatasetPojo}
 *
 * @author Philip Helger
 */
public final class DatasetPojoTest
{
  private static final Logger LOGGER = LoggerFactory.getLogger (DatasetPojoTest.class);

  private static void _testWriteAndRead (@Nonnull final DatasetPojo x)
  {
    assertNotNull (x);

    final DCatAPDatasetType aObj = x.getAsDataset ();
    assertNotNull (aObj);
    CommonsTestHelper.testDefaultImplementationWithEqualContentObject (aObj, aObj.clone ());

    // Write
    final DatasetMarshaller m = new DatasetMarshaller ();
    m.setFormattedOutput (true);
    assertNotNull (m.getAsDocument (aObj));
    if (false)
      LOGGER.info (m.getAsString (aObj));

    // Re-read
    final DatasetPojo y = DatasetPojo.builder (aObj).build ();
    CommonsTestHelper.testDefaultImplementationWithEqualContentObject (x, y);
  }

  @Test
  public void testBasic ()
  {
    final DatasetPojo x = DatasetPojo.builder ()
                                     .description ("bla desc")
                                     .title ("bla title")
                                     .distribution (DocumentReferencePojo.builder ()
                                                                         .documentURI ("URI")
                                                                         .documentDescription ("DocumentDescription")
                                                                         .documentType ("docType"))
                                     .language (EToopLanguageCode.DE)
                                     .creator (AgentPojo.builder ()
                                                        .name ("Agent name")
                                                        .address (AddressPojo.builder ().town ("Kewlkidshome")))
                                     .addID ("bla")
                                     .id ("foo")
                                     .ids ("RE238918378", "DOC-555")
                                     .issuedNow ()
                                     .lastModifiedNow ()
                                     .validFrom (PDTFactory.getCurrentLocalDate ().minusMonths (1))
                                     .validTo (PDTFactory.getCurrentLocalDate ().plusYears (1))
                                     .qualifiedRelation (QualifiedRelationPojo.builder ()
                                                                              .description ("LegalResourceDesc")
                                                                              .title ("Name")
                                                                              .id ("RE238918378"))
                                     .build ();
    _testWriteAndRead (x);
  }

  @Test
  public void testMinimum ()
  {
    final DatasetPojo x = DatasetPojo.builder ().title ("bla title").description ("bla desc").build ();
    _testWriteAndRead (x);
  }
}
