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
