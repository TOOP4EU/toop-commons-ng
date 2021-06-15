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

import java.io.InputStream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.WillClose;
import javax.annotation.concurrent.Immutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.helger.commons.io.stream.StreamHelper;
import com.helger.regrep.CRegRep4;
import com.helger.xml.XMLHelper;
import com.helger.xml.serialize.read.DOMReader;

import eu.toop.edm.EDMErrorResponse;
import eu.toop.edm.EDMRequest;
import eu.toop.edm.EDMResponse;
import eu.toop.edm.IEDMTopLevelObject;

/**
 * Helper class to determine the type of the object from payload (for reading).
 *
 * @author Philip Helger
 * @since 2.0.0-beta3
 */
@Immutable
public final class EDMPayloadDeterminator
{
  private static final Logger LOGGER = LoggerFactory.getLogger (EDMPayloadDeterminator.class);

  private EDMPayloadDeterminator ()
  {}

  /**
   * Determine and parse the payload and see whether it is an
   * {@link EDMRequest}, an {@link EDMResponse} or an {@link EDMErrorResponse}.
   *
   * @param aIS
   *        The input stream to parse. May not be <code>null</code>.
   * @return <code>null</code> if no object could be found.
   */
  @Nullable
  public static IEDMTopLevelObject parseAndFind (@Nonnull @WillClose final InputStream aIS)
  {
    try
    {
      final Document aDoc = DOMReader.readXMLDOM (aIS);
      if (aDoc != null)
      {
        final Element eRoot = aDoc.getDocumentElement ();
        if (eRoot != null)
        {
          // Start digging
          final String sNamespaceURI = eRoot.getNamespaceURI ();
          final String sLocalName = eRoot.getLocalName ();
          if (CRegRep4.NAMESPACE_URI_QUERY.equals (sNamespaceURI) && "QueryRequest".equals (sLocalName))
          {
            // It's supposed to be an EDMRequest
            final EDMRequest ret = EDMRequest.reader ().read (aDoc);
            if (ret != null)
            {
              LOGGER.info ("Sucessfully read the payload as an EDMRequest");
              return ret;
            }
            LOGGER.warn ("Failed to read the payload as an EDMRequest");
          }
          else
            if (CRegRep4.NAMESPACE_URI_QUERY.equals (sNamespaceURI) && "QueryResponse".equals (sLocalName))
            {
              // It's supposed to be an EDMResponse or an EDMErrorResponse
              if (XMLHelper.getFirstChildElementOfName (eRoot, CRegRep4.NAMESPACE_URI_RS, "Exception") != null)
              {
                // It's supposed to be an EDMErrorResponse
                final EDMErrorResponse ret = EDMErrorResponse.reader ().read (aDoc);
                if (ret != null)
                {
                  LOGGER.info ("Sucessfully read the payload as an EDMErrorResponse");
                  return ret;
                }
                LOGGER.warn ("Failed to read the payload as an EDMErrorResponse, even though an Exception is present");
              }

              // It's supposed to be an EDMResponse
              final EDMResponse ret = EDMResponse.reader ().read (aDoc);
              if (ret != null)
              {
                LOGGER.info ("Sucessfully read the payload as an EDMResponse");
                return ret;
              }
              LOGGER.warn ("Failed to read the payload as an EDMResponse");
            }
            else
            {
              if (LOGGER.isWarnEnabled ())
                LOGGER.warn ("The contained XML could not be interpreted. Root element is {" + sNamespaceURI + "}" + sLocalName);
            }
        }
        else
          LOGGER.warn ("The parsed XML document has no root element");
      }
      else
        LOGGER.warn ("Failed to parse the InputStream as an XML document");
      return null;
    }
    finally
    {
      StreamHelper.close (aIS);
    }
  }
}
