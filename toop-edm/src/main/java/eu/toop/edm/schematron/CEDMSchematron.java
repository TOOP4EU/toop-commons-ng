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
import javax.annotation.concurrent.Immutable;

import com.helger.commons.io.resource.ClassPathResource;

/**
 * TOOP EDM Schematron constants
 *
 * @author Philip Helger
 * @since 2.0.0-beta5
 */
@Immutable
public final class CEDMSchematron
{
  @Nonnull
  private static ClassLoader _getCL ()
  {
    return CEDMSchematron.class.getClassLoader ();
  }

  public static final ClassPathResource TOOP_IS_REQUEST = new ClassPathResource ("210/TOOP_is_request.xslt", _getCL ());
  public static final ClassPathResource TOOP_IS_RESPONSE = new ClassPathResource ("210/TOOP_is_response.xslt", _getCL ());
  public static final ClassPathResource TOOP_IS_ERROR_RESPONSE = new ClassPathResource ("210/TOOP_is_error.xslt", _getCL ());

  /**
   * The resource with the rules. Important: this Schematron requires additional
   * code lists in a relative directory!
   */
  public static final ClassPathResource TOOP_EDM2_XSLT = new ClassPathResource ("210/TOOP_EDM.xslt", _getCL ());

  /**
   * The resource with the rules. Important: this Schematron requires additional
   * code lists in a relative directory!
   */
  public static final ClassPathResource TOOP_BUSINESS_RULES_XSLT = new ClassPathResource ("210/TOOP_BUSINESS_RULES.xslt", _getCL ());

  private CEDMSchematron ()
  {}
}
