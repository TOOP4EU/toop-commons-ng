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
