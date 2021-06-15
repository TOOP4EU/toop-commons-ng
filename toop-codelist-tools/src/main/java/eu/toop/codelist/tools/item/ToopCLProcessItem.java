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

import com.helger.commons.annotation.Nonempty;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroElement;

/**
 * Single item of a process ID code list entry
 *
 * @author Philip Helger
 */
public class ToopCLProcessItem extends AbstractToopCLItem
{
  public ToopCLProcessItem (@Nonnull @Nonempty final String sName,
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
    final IMicroElement ret = new MicroElement ("process");
    fillMicroElement (ret);
    return ret;
  }

  @Nonnull
  public static ToopCLProcessItem create (@Nonnull final IMicroElement aElement)
  {
    return new ToopCLProcessItem (aElement.getAttributeValue ("name"),
                                  aElement.getAttributeValue ("id"),
                                  aElement.getAttributeValue ("since"),
                                  aElement.getAttributeValueAsBool ("deprecated", DEFAULT_DEPRECATED),
                                  aElement.getAttributeValue ("deprecated-since"));
  }
}
