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
package eu.toop.edm.supplementary.tools;

import java.io.File;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.regex.RegExHelper;
import com.helger.genericode.Genericode10Helper;
import com.helger.genericode.builder.GenericodeReader;
import com.helger.genericode.v10.CodeListDocument;
import com.helger.genericode.v10.Row;

import eu.toop.edm.error.EToopDocumentResponseErrorCode;

/**
 * Extract {@link EToopDocumentResponseErrorCode} enum content from Genericode
 * file
 *
 * @author Philip Helger
 */
public final class MainCreateJavaCode_DocumentResponseErrorCode_GC
{
  private static final Logger LOGGER = LoggerFactory.getLogger (MainCreateJavaCode_DocumentResponseErrorCode_GC.class);

  public static void main (final String [] args)
  {
    final CodeListDocument aCLD = GenericodeReader.gc10CodeList ()
                                                  .read (new File ("src/main/resources/codelist/toop/DocumentResponseErrorCode-CodeList.gc"));
    final StringBuilder aSB = new StringBuilder ();
    for (final Row aRow : aCLD.getSimpleCodeList ().getRow ())
    {
      final String sID = Genericode10Helper.getRowValue (aRow, "code");
      final String sName = Genericode10Helper.getRowValue (aRow, "name-en");
      aSB.append ("/** ").append (sName).append (" */\n");
      aSB.append (RegExHelper.getAsIdentifier (sID.toUpperCase (Locale.US)))
         .append (" (\"")
         .append (sID)
         .append ("\", \"")
         .append (sName)
         .append ("\"),\n");
    }
    LOGGER.info (aSB.toString ());
  }
}
