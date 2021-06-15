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

import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.transform.Source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.debug.GlobalDebug;
import com.helger.jaxb.IJAXBReader;

/**
 * Default implementation of {@link IJAXBVersatileReader}
 *
 * @author Philip Helger
 * @param <JAXBTYPE>
 *        internal JAXB type
 * @param <T>
 *        Outside data type to be read
 */
public class JAXBVersatileReader <JAXBTYPE, T> implements IJAXBVersatileReader <T>
{
  private static final Logger LOGGER = LoggerFactory.getLogger (JAXBVersatileReader.class);

  private final IJAXBReader <JAXBTYPE> m_aReader;
  private final Function <JAXBTYPE, T> m_aMapper;

  public JAXBVersatileReader (@Nonnull final IJAXBReader <JAXBTYPE> aReader, @Nonnull final Function <JAXBTYPE, T> aMapper)
  {
    ValueEnforcer.notNull (aReader, "Reader");
    ValueEnforcer.notNull (aMapper, "Mapper");
    m_aReader = aReader;
    m_aMapper = aMapper;
  }

  @Nullable
  private T _read (@Nonnull final JAXBTYPE aObj)
  {
    try
    {
      return m_aMapper.apply (aObj);
    }
    catch (final RuntimeException ex)
    {
      if (GlobalDebug.isDebugMode ())
        LOGGER.warn ("Error mapping the read XML (" + aObj + ") to the target type", ex);
      return null;
    }
  }

  @Nullable
  public T read (@Nonnull final Source aSource)
  {
    final JAXBTYPE aObj = m_aReader.read (aSource);
    return aObj == null ? null : _read (aObj);
  }

  @Nullable
  public T read (@Nonnull final Node aNode)
  {
    final JAXBTYPE aObj = m_aReader.read (aNode);
    return aObj == null ? null : _read (aObj);
  }
}
