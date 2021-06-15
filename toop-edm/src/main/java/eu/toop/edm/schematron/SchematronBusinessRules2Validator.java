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
package eu.toop.edm.schematron;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.io.resource.IReadableResource;

/**
 * TOOP Schematron validator for the 2.0.0 data model Business Rules. Apply only
 * <b>after</b> EDM. Validate DOM documents or other resources using the
 * predefined TOOP Schematron rules. This should be run AFTER
 * {@link SchematronEDM2Validator}.
 *
 * @author Philip Helger
 */
@ThreadSafe
public class SchematronBusinessRules2Validator extends AbstractSchematronValidator
{
  public SchematronBusinessRules2Validator ()
  {}

  @Override
  @Nonnull
  protected final IReadableResource getSchematronXSLTResource ()
  {
    return CEDMSchematron.TOOP_BUSINESS_RULES_XSLT;
  }
}
