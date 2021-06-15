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
package eu.toop.edm.supplementary.tools;

import java.io.File;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.regex.RegExHelper;
import com.helger.commons.string.StringHelper;
import com.helger.genericode.Genericode10Helper;
import com.helger.genericode.builder.GenericodeReader;
import com.helger.genericode.v10.CodeListDocument;
import com.helger.genericode.v10.Row;

import eu.toop.edm.model.EToopLanguageCode;

/**
 * Extract {@link EToopLanguageCode} enum content from Genericode file
 *
 * @author Philip Helger
 */
public final class MainCreateJavaCode_LanguageCode_GC
{
  private static final Logger LOGGER = LoggerFactory.getLogger (MainCreateJavaCode_LanguageCode_GC.class);

  @Nonnull
  public static String _id (@Nonnull final String s)
  {
    return StringHelper.replaceAllRepeatedly (StringHelper.trimEnd (RegExHelper.getAsIdentifier (s.toUpperCase (Locale.US)), '_'),
                                              "__",
                                              "_");
  }

  public static void main (final String [] args)
  {
    final CodeListDocument aCLD = GenericodeReader.gc10CodeList ()
                                                  .read (new File ("src/main/resources/codelist/external/LanguageCode-2.2.gc"));
    final StringBuilder aSB = new StringBuilder ();
    final Set <String> aUniqueIDs = new HashSet <> ();
    for (final Row aRow : aCLD.getSimpleCodeList ().getRow ())
    {
      final String sID = Genericode10Helper.getRowValue (aRow, "code");
      if (aUniqueIDs.add (sID))
      {
        final String sName = Genericode10Helper.getRowValue (aRow, "name");
        if (sName != null)
          aSB.append ("/** ").append (sName).append (" */\n");
        if (sID != null)
          aSB.append (_id (sID)).append (" (\"").append (sID).append ("\", \"").append (sName).append ("\"),\n");
      }
    }
    LOGGER.info (aSB.toString ());
  }
}
