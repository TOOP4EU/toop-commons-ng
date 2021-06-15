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
package eu.toop.codelist.tools.item;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroElement;

/**
 * Single item of a transport profile code list entry
 *
 * @author Philip Helger
 */
public class ToopCLTransportProfileItem extends AbstractToopCLItem
{
  private final String m_sVersion;

  public ToopCLTransportProfileItem (@Nonnull @Nonempty final String sName,
                                     @Nonnull @Nonempty final String sVersion,
                                     @Nonnull @Nonempty final String sID,
                                     @Nonnull @Nonempty final String sSince,
                                     final boolean bDeprecated,
                                     @Nullable final String sDeprecatedSince)
  {
    super (sName, sID, sSince, bDeprecated, sDeprecatedSince);
    ValueEnforcer.notEmpty (sVersion, "Version");
    m_sVersion = sVersion;
  }

  @Nonnull
  @Nonempty
  public final String getVersion ()
  {
    return m_sVersion;
  }

  @Nonnull
  public IMicroElement getAsMicroElement ()
  {
    final IMicroElement ret = new MicroElement ("transport-profile");
    fillMicroElement (ret);
    ret.setAttribute ("version", m_sVersion);
    return ret;
  }

  @Nonnull
  public static ToopCLTransportProfileItem create (@Nonnull final IMicroElement aElement)
  {
    return new ToopCLTransportProfileItem (aElement.getAttributeValue ("name"),
                                           aElement.getAttributeValue ("version"),
                                           aElement.getAttributeValue ("id"),
                                           aElement.getAttributeValue ("since"),
                                           aElement.getAttributeValueAsBool ("deprecated", DEFAULT_DEPRECATED),
                                           aElement.getAttributeValue ("deprecated-since"));
  }
}
