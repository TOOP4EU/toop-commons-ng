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
package eu.toop.edm.xml;

import javax.annotation.Nonnull;

import com.helger.jaxb.IJAXBWriter;

/**
 * The default implementation of {@link IJAXBVersatileWriter} using a constant
 * value and an instance of {@link IJAXBWriter}.
 *
 * @author Philip Helger
 * @param <T>
 *        Type to be written.
 */
public final class JAXBVersatileWriter <T> implements IJAXBVersatileWriter <T>
{
  private final T m_aObject;
  private final IJAXBWriter <T> m_aWriter;

  public JAXBVersatileWriter (@Nonnull final T aObject, @Nonnull final IJAXBWriter <T> aWriter)
  {
    m_aObject = aObject;
    m_aWriter = aWriter;
  }

  @Nonnull
  public T getObjectToWrite ()
  {
    return m_aObject;
  }

  @Nonnull
  public IJAXBWriter <T> getWriter ()
  {
    return m_aWriter;
  }
}
