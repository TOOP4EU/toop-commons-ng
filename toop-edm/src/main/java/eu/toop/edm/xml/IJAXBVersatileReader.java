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

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.file.Path;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;

import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.charset.CharsetHelper;
import com.helger.commons.charset.CharsetHelper.InputStreamAndCharset;
import com.helger.commons.io.IHasInputStream;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.commons.io.stream.ByteBufferInputStream;
import com.helger.commons.io.stream.NonBlockingByteArrayInputStream;
import com.helger.commons.io.stream.NonBlockingStringReader;
import com.helger.commons.io.stream.StreamHelper;
import com.helger.xml.EXMLParserFeature;
import com.helger.xml.sax.InputSourceFactory;
import com.helger.xml.serialize.read.SAXReaderFactory;
import com.helger.xml.serialize.read.SAXReaderSettings;

/**
 * Interface for reading XML documents from various sources.
 *
 * @author Philip Helger
 * @param <T>
 *        The type to be read
 */
public interface IJAXBVersatileReader <T>
{
  /**
   * Read a document from the specified input source. The secure reading feature
   * has affect when using this method.
   *
   * @param aInputSource
   *        The source to read. May not be <code>null</code>.
   * @return <code>null</code> in case reading fails.
   */
  @Nullable
  default T read (@Nonnull final InputSource aInputSource)
  {
    // Initialize settings with defaults
    return read (new SAXReaderSettings (), aInputSource);
  }

  /**
   * Read a document from the specified input source using the specified SAX
   * reader settings. The secure reading must be enabled in the SAX settings.
   *
   * @param aSettings
   *        The SAX Settings to use.
   * @param aInputSource
   *        The source to read. May not be <code>null</code>.
   * @return <code>null</code> in case reading fails.
   */
  @Nullable
  default T read (@Nonnull final SAXReaderSettings aSettings, @Nonnull final InputSource aInputSource)
  {
    ValueEnforcer.notNull (aSettings, "Settings");
    ValueEnforcer.notNull (aInputSource, "InputSource");

    // Apply settings that make reading more secure
    aSettings.setFeatureValues (EXMLParserFeature.AVOID_XML_ATTACKS);

    // Create new XML reader
    final XMLReader aParser = SAXReaderFactory.createXMLReader ();
    aSettings.applyToSAXReader (aParser);

    // And read via JAXB
    return read (new SAXSource (aParser, aInputSource));
  }

  /**
   * Read a document from the specified input stream. The secure reading feature
   * has affect when using this method.
   *
   * @param aIS
   *        The input stream to read. May not be <code>null</code>.
   * @return <code>null</code> in case reading fails.
   */
  @Nullable
  default T read (@Nonnull final InputStream aIS)
  {
    ValueEnforcer.notNull (aIS, "InputStream");

    final InputStreamAndCharset aISAndBOM = CharsetHelper.getInputStreamAndCharsetFromBOM (aIS);
    if (aISAndBOM.hasCharset ())
    {
      // BOM was found - read from Reader
      return read (StreamHelper.createReader (aISAndBOM.getInputStream (), aISAndBOM.getCharset ()));
    }

    // No BOM found - use the returned InputStream anyway, so that the pushed
    // back bytes are read
    return read (InputSourceFactory.create (aISAndBOM.getInputStream ()));
  }

  /**
   * Read a document from the specified reader. The secure reading feature has
   * affect when using this method.
   *
   * @param aReader
   *        The reader to read. May not be <code>null</code>.
   * @return <code>null</code> in case reading fails.
   */
  @Nullable
  default T read (@Nonnull final Reader aReader)
  {
    ValueEnforcer.notNull (aReader, "Reader");
    return read (InputSourceFactory.create (aReader));
  }

  /**
   * Read a document from the specified file. The secure reading feature has
   * affect when using this method.
   *
   * @param aFile
   *        The file to read. May not be <code>null</code>.
   * @return <code>null</code> in case reading fails.
   */
  @Nullable
  default T read (@Nonnull final File aFile)
  {
    ValueEnforcer.notNull (aFile, "File");

    // Use InputSourceFactory to have the systemId if possible
    return read (InputSourceFactory.create (aFile));
  }

  /**
   * Read a document from the specified Path. The secure reading feature has
   * affect when using this method.
   *
   * @param aPath
   *        The path to read. May not be <code>null</code>.
   * @return <code>null</code> in case reading fails.
   */
  @Nullable
  default T read (@Nonnull final Path aPath)
  {
    ValueEnforcer.notNull (aPath, "Path");

    // Use InputSourceFactory to have the systemId if possible
    return read (InputSourceFactory.create (aPath));
  }

  /**
   * Read a document from the specified resource. The secure reading feature has
   * affect when using this method.
   *
   * @param aResource
   *        The resource to read. May not be <code>null</code>.
   * @return <code>null</code> in case reading fails.
   */
  @Nullable
  default T read (@Nonnull final IReadableResource aResource)
  {
    ValueEnforcer.notNull (aResource, "Resource");

    // Use InputSourceFactory to have the systemId if possible
    return read (InputSourceFactory.create (aResource));
  }

  /**
   * Read a document from the specified input stream provider. The secure
   * reading feature has affect when using this method.
   *
   * @param aISP
   *        The input stream provider to read. May not be <code>null</code>.
   * @return <code>null</code> in case reading fails.
   */
  @Nullable
  default T read (@Nonnull final IHasInputStream aISP)
  {
    ValueEnforcer.notNull (aISP, "Resource");

    // Ensure to use InputStream for BOM handling
    return read (aISP.getInputStream ());
  }

  /**
   * Read a document from the specified byte array. The secure reading feature
   * has affect when using this method.
   *
   * @param aXML
   *        The XML bytes to read. May not be <code>null</code>.
   * @return <code>null</code> in case reading fails.
   */
  @Nullable
  default T read (@Nonnull final byte [] aXML)
  {
    ValueEnforcer.notNull (aXML, "XML");

    // Ensure to use InputStream for BOM handling
    return read (new NonBlockingByteArrayInputStream (aXML));
  }

  /**
   * Read a document from the specified byte buffer. The secure reading feature
   * has affect when using this method.
   *
   * @param aXML
   *        The XML bytes to read. May not be <code>null</code>.
   * @return <code>null</code> in case reading fails.
   */
  @Nullable
  default T read (@Nonnull final ByteBuffer aXML)
  {
    ValueEnforcer.notNull (aXML, "XML");

    // Ensure to use InputStream for BOM handling
    return read (new ByteBufferInputStream (aXML));
  }

  /**
   * Read a document from the specified String. The secure reading feature has
   * affect when using this method.
   *
   * @param sXML
   *        The XML string to read. May not be <code>null</code>.
   * @return <code>null</code> in case reading fails.
   */
  @Nullable
  default T read (@Nonnull final String sXML)
  {
    ValueEnforcer.notNull (sXML, "XML");
    return read (new NonBlockingStringReader (sXML));
  }

  /**
   * Read a document from the specified char array. The secure reading feature
   * has affect when using this method.
   *
   * @param aXML
   *        The XML string to read. May not be <code>null</code>.
   * @return <code>null</code> in case reading fails.
   */
  @Nullable
  default T read (@Nonnull final char [] aXML)
  {
    ValueEnforcer.notNull (aXML, "XML");
    return read (new NonBlockingStringReader (aXML));
  }

  /**
   * Read a document from the specified source. The secure reading feature has
   * <b>NO</b> affect when using this method because the parameter type is too
   * generic.
   *
   * @param aSource
   *        The source to read. May not be <code>null</code>.
   * @return <code>null</code> in case reading fails.
   */
  @Nullable
  T read (@Nonnull final Source aSource);

  /**
   * Read a document from the specified DOM node. The secure reading feature has
   * <b>NO</b> affect when using this method because no parsing happens! To
   * ensure secure reading the Node must first be serialized to a String and be
   * parsed again!
   *
   * @param aNode
   *        The DOM node to read. May not be <code>null</code>.
   * @return <code>null</code> in case reading fails.
   */
  @Nullable
  T read (@Nonnull final Node aNode);
}
