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

import javax.annotation.Nonnull;
import javax.xml.namespace.QName;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.id.IHasID;

/**
 * Abstract interface for a predefined TOOP concept.
 *
 * @author Philip Helger
 */
public interface IConceptName extends IHasID <String>
{
  @Nonnull
  @Nonempty
  String getConceptNamespaceURI ();

  /**
   * @return a new QName from {@link #getConceptNamespaceURI()} and
   *         {@link #getID()}
   */
  @Nonnull
  @ReturnsMutableCopy
  default QName getAsQName ()
  {
    return new QName (getConceptNamespaceURI (), getID ());
  }
}
