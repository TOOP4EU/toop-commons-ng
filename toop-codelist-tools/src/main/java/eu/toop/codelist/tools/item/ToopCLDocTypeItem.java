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
package eu.toop.codelist.tools.item;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroElement;

/**
 * Single item of a document type ID code list entry
 *
 * @author Philip Helger
 */
public class ToopCLDocTypeItem extends AbstractToopCLItem
{
  public ToopCLDocTypeItem (@Nonnull @Nonempty final String sName,
                            @Nonnull @Nonempty final String sID,
                            @Nonnull @Nonempty final String sSince,
                            final boolean bDeprecated,
                            @Nullable final String sDeprecatedSince)
  {
    super (sName, sID, sSince, bDeprecated, sDeprecatedSince);
  }

  @Nonnull
  public IMicroElement getAsMicroElement ()
  {
    final IMicroElement ret = new MicroElement ("document-type");
    fillMicroElement (ret);
    return ret;
  }

  @Nonnull
  public static ToopCLDocTypeItem create (@Nonnull final IMicroElement aElement)
  {
    return new ToopCLDocTypeItem (aElement.getAttributeValue ("name"),
                                  aElement.getAttributeValue ("id"),
                                  aElement.getAttributeValue ("since"),
                                  aElement.getAttributeValueAsBool ("deprecated", DEFAULT_DEPRECATED),
                                  aElement.getAttributeValue ("deprecated-since"));
  }
}
