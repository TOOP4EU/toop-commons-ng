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
package eu.toop.edm.request;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.collection.impl.ICommonsList;

import eu.toop.edm.model.ConceptPojo;

/**
 * EDM Request payload provider for "Concepts"
 *
 * @author Philip Helger
 * @since 2.0.0-beta3
 */
public interface IEDMRequestPayloadConcepts extends IEDMRequestPayloadProvider
{
  /**
   * @return All contains concepts as a mutable list. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableObject
  ICommonsList <ConceptPojo> concepts ();

  /**
   * @return All contains concepts as a copied list. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsList <ConceptPojo> getAllConcepts ();
}
