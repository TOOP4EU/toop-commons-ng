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
package eu.toop.edm.pilot.gbm;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.lang.EnumHelper;

import eu.toop.edm.model.IConceptName;

/**
 * Predefined TOOP concepts for "registered organization". Source
 * http://wiki.ds.unipi.gr/display/TOOPPILOTS/Datasets
 *
 * @author Philip Helger
 */
public enum EToopConcept implements IConceptName
{
  ACTIVITY_DESCRIPTION ("ActivityDescription"),
  BIRTH_DATE ("LegalRepresentativeBirthDate"),
  CAPTIAL_TYPE ("CapitalType"),
  COMPANY_CODE ("CompanyCode"),
  COMPANY_NAME ("CompanyName"),
  COMPANY_TYPE ("CompanyType"),
  COUNTRY_NAME ("CountryName"),
  EMAIL_ADDRESS ("EmailAddress"),
  FAMILY_NAME ("LegalRepresentativeFamilyName"),
  FAX_NUMBER ("FaxNumber"),
  FOUNDATION_DATE ("FoundationDate"),
  GIVEN_NAME ("LegalRepresentativeGivenName"),
  HAS_LEGAL_REPRESENTATIVE ("HasLegalRepresentative"),
  LEGAL_STATUS ("LegalStatus"),
  LEGAL_STATUS_EFFECTIVE_DATE ("LegalStatusEffectiveDate"),
  LOCALITY ("Locality"),
  NACE_CODE ("NaceCode"),
  PERSON ("Person"),
  POSTAL_CODE ("PostalCode"),
  REGION ("Region"),
  REGISTERED_ORGANIZATION ("RegisteredOrganization"),
  REGISTRATION_AUTH ("RegistrationAuthority"),
  REGISTRATION_DATE ("RegistrationDate"),
  REGISTRATION_NUMBER ("RegistrationNumber"),
  SOCIAL_SEC_NUMBER ("SSNumber"),
  STREET_ADDRESS ("StreetAddress"),
  TELEPHONE_NUMBER ("TelephoneNumber"),
  VAT_NUMBER ("VATNumber");

  /**
   * The namespace URI to be used for these concepts
   */
  public static final String NAMESPACE_URI = "http://toop.eu/registered-organization";

  private final String m_sID;

  EToopConcept (@Nonnull @Nonempty final String sID)
  {
    m_sID = sID;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  @Nonnull
  @Nonempty
  public String getConceptNamespaceURI ()
  {
    return NAMESPACE_URI;
  }

  @Nullable
  public static EToopConcept getFromIDOrNull (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrNull (EToopConcept.class, sID);
  }
}
