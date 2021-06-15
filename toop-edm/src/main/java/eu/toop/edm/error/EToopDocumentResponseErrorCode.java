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
package eu.toop.edm.error;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.lang.EnumHelper;

/**
 * Source: DocumentResponseErrorCode-CodeList.gc<br>
 * Content created by MainCreateJavaCode_DocumentResponseErrorCode_GC
 *
 * @author Philip Helger
 */
public enum EToopDocumentResponseErrorCode implements IToopErrorCode
{
  /** Unknown document type */
  DP_DOC_001 ("DP_DOC_001", "Unknown document type"),
  /** Unauthorized */
  DP_DOC_002 ("DP_DOC_002", "Unauthorized"),
  /** Unavailable */
  DP_DOC_003 ("DP_DOC_003", "Unavailable"),
  /** Internal processing error */
  DP_DOC_004 ("DP_DOC_004", "Internal processing error"),
  /** Payload too large */
  DP_DOC_005 ("DP_DOC_005", "Payload too large"),
  /** Timeout */
  DP_DOC_006 ("DP_DOC_006", "Timeout"),
  /** Different mime type */
  DP_DOC_007 ("DP_DOC_007", "Different mime type");

  private final String m_sID;
  private final String m_sDisplayName;

  EToopDocumentResponseErrorCode (@Nonnull @Nonempty final String sID, @Nonnull @Nonempty final String sDisplayName)
  {
    m_sID = sID;
    m_sDisplayName = sDisplayName;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  @Nonnull
  @Nonempty
  public String getDisplayName ()
  {
    return m_sDisplayName;
  }

  @Nullable
  public static EToopDocumentResponseErrorCode getFromIDOrNull (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrNull (EToopDocumentResponseErrorCode.class, sID);
  }
}
