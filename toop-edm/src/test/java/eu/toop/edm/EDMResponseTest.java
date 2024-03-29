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
package eu.toop.edm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.Month;
import java.util.UUID;

import javax.annotation.Nonnull;

import org.junit.Test;
import org.w3c.dom.Document;

import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.mock.CommonsTestHelper;
import com.helger.regrep.ERegRepResponseStatus;
import com.helger.schematron.svrl.AbstractSVRLMessage;

import eu.toop.edm.model.ConceptPojo;
import eu.toop.edm.model.DatasetPojo;
import eu.toop.edm.model.EToopIdentifierType;
import eu.toop.edm.model.EToopLanguageCode;
import eu.toop.edm.pilot.gbm.EToopConcept;
import eu.toop.edm.schematron.SchematronBusinessRules2Validator;
import eu.toop.edm.schematron.SchematronEDM2Validator;

/**
 * Test class for class {@link EDMResponse}.
 *
 * @author Philip Helger
 */
public final class EDMResponseTest
{
  private static void _testWriteAndRead (@Nonnull final EDMResponse aResp)
  {
    assertNotNull (aResp);

    // Write
    final byte [] aBytes = aResp.getWriter ().getAsBytes ();
    assertNotNull (aBytes);

    // Re-read
    final EDMResponse aResp2 = EDMResponse.reader ().read (aBytes);
    // Compare with original
    assertEquals (aResp, aResp2);
    CommonsTestHelper.testDefaultImplementationWithEqualContentObject (aResp, aResp2);

    {
      // Schematron validation
      final Document aDoc = aResp.getWriter ().getAsDocument ();
      assertNotNull (aDoc);
      // Schematron 1
      ICommonsList <AbstractSVRLMessage> aMsgs = new SchematronEDM2Validator ().validateDocument (aDoc);
      assertTrue (aMsgs.toString (), aMsgs.isEmpty ());

      // Schematron 2
      aMsgs = new SchematronBusinessRules2Validator ().validateDocument (aDoc);
      assertTrue (aMsgs.toString (), aMsgs.isEmpty ());
    }
  }

  @Nonnull
  private static <T extends EDMResponse.AbstractBuilder <T>> T _resp (@Nonnull final T aBuilder)
  {
    return aBuilder.requestID (UUID.randomUUID ())
                   .issueDateTimeNow ()
                   .specificationIdentifier (CToopEDM.SPECIFICATION_IDENTIFIER_TOOP_EDM_V21)
                   .dataProvider (x -> x.address (y -> y.town ("MyTown")
                                                        .streetName ("MyStreet")
                                                        .buildingNumber ("22")
                                                        .countryCode ("GR")
                                                        .fullAddress ("MyStreet 22, 11134, MyTown, GR")
                                                        .postalCode ("11134"))
                                        .name ("DP NAME")
                                        .id ("1234")
                                        .idSchemeID (EToopIdentifierType.EIDAS))
                   .responseStatus (ERegRepResponseStatus.SUCCESS);
  }

  @Nonnull
  private static EDMResponse.BuilderConcept _respConcept ()
  {
    return _resp (EDMResponse.builderConcept ()).concept (x -> x.id ("ConceptID-1")
                                                                .name (EToopConcept.REGISTERED_ORGANIZATION)
                                                                .addChild (y -> y.randomID ()
                                                                                 .name (EToopConcept.COMPANY_NAME)
                                                                                 .valueText ("Helger Enterprises"))
                                                                .addChild (y -> y.randomID ()
                                                                                 .name (EToopConcept.FAX_NUMBER)
                                                                                 .valueText ("342342424"))
                                                                .addChild (y -> y.randomID ()
                                                                                 .name (EToopConcept.FOUNDATION_DATE)
                                                                                 .valueDate (PDTFactory.createLocalDate (1960,
                                                                                                                         Month.AUGUST,
                                                                                                                         12))));
  }

  @Nonnull
  private static DatasetPojo.Builder _dataset ()
  {
    return DatasetPojo.builder ()
                      .description ("bla desc")
                      .title ("bla title")
                      .distribution (x -> x.documentURI ("URI")
                                           .documentDescription ("DocumentDescription")
                                           .documentType ("application/xml"))
                      .language (EToopLanguageCode.EN)
                      .creator (x -> x.name ("Agent name").address (y -> y.town ("Kewlkidshome")))
                      .ids ("RE238918378", "DOC-555")
                      .issuedNow ()
                      .lastModifiedNow ()
                      .validFrom (PDTFactory.getCurrentLocalDate ().minusMonths (1))
                      .validTo (PDTFactory.getCurrentLocalDate ().plusYears (1))
                      .addQualifiedRelation (x -> x.description ("LegalResourceDesc").title ("Name").id ("RE238918378"))
                      .addQualifiedRelation (x -> x.descriptions ("LegalResourceDesc2", "nice isn't it")
                                                   .titles ("Name1", "Name2")
                                                   .ids ("RE238918378", "and-id2"));
  }

  @Nonnull
  private static EDMResponse.BuilderDocument _respDocument ()
  {
    return _resp (EDMResponse.builderDocument ()).addResponseObject (x -> x.dataset (_dataset ())
                                                                           .repositoryItemRef (y -> y.title ("Evidence.pdf")
                                                                                                     .link ("https://www.example.com/evidence.pdf")));
  }

  @Nonnull
  private static EDMResponse.BuilderDocumentReference _respDocumentRef ()
  {
    return _resp (EDMResponse.builderDocumentReference ()).addResponseObject (x -> x.randomRegistryObjectID ().dataset (_dataset ()))
                                                          .addResponseObject (x -> x.randomRegistryObjectID ().dataset (_dataset ()));
  }

  @Test
  public void createConceptResponse ()
  {
    final EDMResponse aResp = _respConcept ().build ();
    _testWriteAndRead (aResp);
  }

  @Test
  public void createDocumentResponse ()
  {
    final EDMResponse aResp = _respDocument ().build ();
    _testWriteAndRead (aResp);
  }

  @Test
  public void createDocumentRefResponse ()
  {
    final EDMResponse aResp = _respDocumentRef ().build ();
    _testWriteAndRead (aResp);
  }

  public void createDocumentResponseWithConceptType ()
  {
    try
    {
      // This attempts to create an EDMResponse with a dataset element but with
      // ConceptQuery set as the QueryDefinition
      // which is not permitted and fails
      _respConcept ().concepts ((ConceptPojo []) null).build ();
      fail ();
    }
    catch (final IllegalStateException ex)
    {
      // Expected
    }
  }

  @Test
  public void testReadAndWriteExampleFiles ()
  {
    EDMResponse aResponse = EDMResponse.reader ().read (new ClassPathResource ("Concept Response.xml"));
    _testWriteAndRead (aResponse);

    aResponse = EDMResponse.reader ().read (new ClassPathResource ("Document Response.xml"));
    _testWriteAndRead (aResponse);
  }

  @Test
  public void testBadCases ()
  {
    EDMResponse aResponse = EDMResponse.reader ().read (new ClassPathResource ("Bogus.xml"));
    assertNull (aResponse);

    aResponse = EDMResponse.reader ().read (new ClassPathResource ("Concept Request_LP.xml"));
    assertNull (aResponse);

    aResponse = EDMResponse.reader ().read (new ClassPathResource ("Error Response 1.xml"));
    assertNull (aResponse);
  }
}
