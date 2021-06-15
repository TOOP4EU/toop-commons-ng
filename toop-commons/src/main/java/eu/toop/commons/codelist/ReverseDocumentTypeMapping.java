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
package eu.toop.commons.codelist;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.collection.impl.CommonsEnumMap;
import com.helger.commons.collection.impl.ICommonsMap;

/**
 * This class manages the relationship between TOOP request and response
 * Document types. It is used to create the appropriate "response" document type
 * from an arbitrary source document type.
 *
 * @author Philip Helger
 */
@SuppressWarnings ("deprecation")
public final class ReverseDocumentTypeMapping
{
  private static final ICommonsMap <EPredefinedDocumentTypeIdentifier, EPredefinedDocumentTypeIdentifier> MAP = new CommonsEnumMap <> (EPredefinedDocumentTypeIdentifier.class);

  private static void _add (@Nonnull final EPredefinedDocumentTypeIdentifier aRequest,
                            @Nonnull final EPredefinedDocumentTypeIdentifier aResponse)
  {
    MAP.put (aRequest, aResponse);
    MAP.put (aResponse, aRequest);
  }

  static
  {
    // Fill the map

    // EDM v1, Codelist v2
    _add (EPredefinedDocumentTypeIdentifier.URN_EU_TOOP_NS_DATAEXCHANGE_1P40_REQUEST_URN_EU_TOOP_REQUEST_REGISTEREDORGANIZATION_LIST_1_40,
          EPredefinedDocumentTypeIdentifier.URN_EU_TOOP_NS_DATAEXCHANGE_1P40_RESPONSE_URN_EU_TOOP_RESPONSE_REGISTEREDORGANIZATION_LIST_1_40);
    _add (EPredefinedDocumentTypeIdentifier.URN_EU_TOOP_NS_DATAEXCHANGE_1P40_REQUEST_URN_EU_TOOP_REQUEST_REGISTEREDORGANIZATION_1_40,
          EPredefinedDocumentTypeIdentifier.URN_EU_TOOP_NS_DATAEXCHANGE_1P40_RESPONSE_URN_EU_TOOP_RESPONSE_REGISTEREDORGANIZATION_1_40);
    _add (EPredefinedDocumentTypeIdentifier.URN_EU_TOOP_NS_DATAEXCHANGE_1P40_REQUEST_URN_EU_TOOP_REQUEST_SHIPCERTIFICATE_LIST_1_40,
          EPredefinedDocumentTypeIdentifier.URN_EU_TOOP_NS_DATAEXCHANGE_1P40_RESPONSE_URN_EU_TOOP_RESPONSE_SHIPCERTIFICATE_LIST_1_40);
    _add (EPredefinedDocumentTypeIdentifier.URN_EU_TOOP_NS_DATAEXCHANGE_1P40_REQUEST_URN_EU_TOOP_REQUEST_SHIPCERTIFICATE_1_40,
          EPredefinedDocumentTypeIdentifier.URN_EU_TOOP_NS_DATAEXCHANGE_1P40_RESPONSE_URN_EU_TOOP_RESPONSE_SHIPCERTIFICATE_1_40);
    _add (EPredefinedDocumentTypeIdentifier.URN_EU_TOOP_NS_DATAEXCHANGE_1P40_REQUEST_URN_EU_TOOP_REQUEST_CREWCERTIFICATE_LIST_1_40,
          EPredefinedDocumentTypeIdentifier.URN_EU_TOOP_NS_DATAEXCHANGE_1P40_RESPONSE_URN_EU_TOOP_RESPONSE_CREWCERTIFICATE_LIST_1_40);
    _add (EPredefinedDocumentTypeIdentifier.URN_EU_TOOP_NS_DATAEXCHANGE_1P40_REQUEST_URN_EU_TOOP_REQUEST_CREWCERTIFICATE_1_40,
          EPredefinedDocumentTypeIdentifier.URN_EU_TOOP_NS_DATAEXCHANGE_1P40_RESPONSE_URN_EU_TOOP_RESPONSE_CREWCERTIFICATE_1_40);
    _add (EPredefinedDocumentTypeIdentifier.URN_EU_TOOP_NS_DATAEXCHANGE_1P40_REQUEST_URN_EU_TOOP_REQUEST_EVIDENCE_LIST_1_40,
          EPredefinedDocumentTypeIdentifier.URN_EU_TOOP_NS_DATAEXCHANGE_1P40_RESPONSE_URN_EU_TOOP_RESPONSE_EVIDENCE_LIST_1_40);
    _add (EPredefinedDocumentTypeIdentifier.URN_EU_TOOP_NS_DATAEXCHANGE_1P40_REQUEST_URN_EU_TOOP_REQUEST_EVIDENCE_1_40,
          EPredefinedDocumentTypeIdentifier.URN_EU_TOOP_NS_DATAEXCHANGE_1P40_RESPONSE_URN_EU_TOOP_RESPONSE_EVIDENCE_1_40);

    // EDM v2, Codelist v3
    _add (EPredefinedDocumentTypeIdentifier.REGISTEREDORGANIZATION_REGISTERED_ORGANIZATION_TYPE_CONCEPT_CCCEV_TOOP_EDM_V2_0,
          EPredefinedDocumentTypeIdentifier.QUERYRESPONSE_TOOP_EDM_V2_0);
    _add (EPredefinedDocumentTypeIdentifier.FINANCIALRECORD_FINANCIAL_RECORD_TYPE_UNSTRUCTURED_TOOP_EDM_V2_0,
          EPredefinedDocumentTypeIdentifier.QUERYRESPONSE_TOOP_EDM_V2_0);

    // EDM v2.1, Codelist v5
    _add (EPredefinedDocumentTypeIdentifier.REGISTEREDORGANIZATION_REGISTERED_ORGANIZATION_TYPE_CONCEPT_CCCEV_TOOP_EDM_V2_1,
          EPredefinedDocumentTypeIdentifier.QUERYRESPONSE_TOOP_EDM_V2_1);
    _add (EPredefinedDocumentTypeIdentifier.FINANCIALRECORD_FINANCIAL_RECORD_TYPE_UNSTRUCTURED_TOOP_EDM_V2_1,
          EPredefinedDocumentTypeIdentifier.QUERYRESPONSE_TOOP_EDM_V2_1);
  }

  private ReverseDocumentTypeMapping ()
  {}

  @Nullable
  public static EPredefinedDocumentTypeIdentifier getReverseDocumentTypeOrNull (@Nullable final EPredefinedDocumentTypeIdentifier eDocType)
  {
    return MAP.get (eDocType);
  }

  @Nonnull
  @Nonempty
  public static EPredefinedDocumentTypeIdentifier getReverseDocumentType (@Nullable final EPredefinedDocumentTypeIdentifier eDocType)
  {
    final EPredefinedDocumentTypeIdentifier ret = getReverseDocumentTypeOrNull (eDocType);
    if (ret == null)
      throw new IllegalArgumentException ("Unsupported document type " + eDocType);
    return ret;
  }

  /**
   * @return A copy of the map used for reverse mapping. Never
   *         <code>null</code>.
   * @since 0.10.2
   */
  @Nonnull
  @ReturnsMutableObject
  public static ICommonsMap <EPredefinedDocumentTypeIdentifier, EPredefinedDocumentTypeIdentifier> getAllMappings ()
  {
    return MAP.getClone ();
  }
}
