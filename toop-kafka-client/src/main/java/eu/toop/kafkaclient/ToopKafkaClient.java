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
package eu.toop.kafkaclient;

import java.util.Locale;
import java.util.function.Supplier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.error.level.IErrorLevel;
import com.helger.commons.lang.ClassHelper;
import com.helger.commons.log.LogHelper;

/**
 * Global TOOP Kafka Client. It is disabled by default.
 *
 * @author Philip Helger
 */
public final class ToopKafkaClient
{
  private static final Logger LOGGER = LoggerFactory.getLogger (ToopKafkaClient.class);

  private ToopKafkaClient ()
  {}

  private static void _sendIfKafkaEnabled (@Nullable final IErrorLevel aErrorLevel, @Nonnull final String sValue)
  {
    String sLogText = sValue;
    if (aErrorLevel != null)
      sLogText = "[" + aErrorLevel.getID ().toUpperCase (Locale.US) + "] " + sLogText;

    if (LOGGER.isDebugEnabled ())
      LOGGER.debug ("Sending to Kafka: '" + sLogText + "'");

    // Send but don't wait for the commit!
    ToopKafkaManager.send ((String) null, sLogText, null);
  }

  /**
   * Send a message, if it is enabled.
   *
   * @param aErrorLevel
   *        Error level to log the message. May be <code>null</code> to not log
   *        it.
   * @param sValue
   *        Value to send. May not be <code>null</code>.
   * @see ToopKafkaSettings#isKafkaEnabled()
   */
  public static void send (@Nullable final IErrorLevel aErrorLevel, @Nonnull final String sValue)
  {
    if (aErrorLevel != null && ToopKafkaSettings.isLoggingEnabled ())
      LogHelper.log (ToopKafkaClient.class, aErrorLevel, sValue);
    if (ToopKafkaSettings.isKafkaEnabled ())
      _sendIfKafkaEnabled (aErrorLevel, sValue);
  }

  /**
   * Send a message, if it is enabled.
   *
   * @param aErrorLevel
   *        Error level to log the message. May be <code>null</code> to not log
   *        it.
   * @param aValue
   *        Value supplier to send. Is only evaluated if enabled. May not be
   *        <code>null</code>.
   * @see ToopKafkaSettings#isKafkaEnabled()
   */
  public static void send (@Nullable final IErrorLevel aErrorLevel, @Nonnull final Supplier <String> aValue)
  {
    send (aErrorLevel, aValue, (Throwable) null);
  }

  /**
   * Send a message, if it is enabled.
   *
   * @param aErrorLevel
   *        Error level to log the message. May be <code>null</code> to not log
   *        it.
   * @param aValue
   *        Value supplier to send. Is only evaluated if enabled. May not be
   *        <code>null</code>.
   * @param t
   *        Exception to be logged. May be <code>null</code>.
   * @see ToopKafkaSettings#isKafkaEnabled()
   */
  public static void send (@Nullable final IErrorLevel aErrorLevel, @Nonnull final Supplier <String> aValue, @Nullable final Throwable t)
  {
    String sValue = null;
    if (aErrorLevel != null && ToopKafkaSettings.isLoggingEnabled ())
    {
      sValue = aValue.get ();
      LogHelper.log (ToopKafkaClient.class, aErrorLevel, sValue, t);
    }
    if (ToopKafkaSettings.isKafkaEnabled ())
    {
      if (sValue == null)
        sValue = aValue.get ();
      if (t != null)
        sValue += " -- " + ClassHelper.getClassLocalName (t.getClass ()) + ": " + t.getMessage ();
      _sendIfKafkaEnabled (aErrorLevel, sValue);
    }
  }

  /**
   * Shutdown at the end. Note: this only does something, if the client is
   * enabled. Do this only once globally on application shutdown.
   *
   * @see ToopKafkaSettings#isKafkaEnabled()
   */
  public static void close ()
  {
    if (ToopKafkaSettings.isKafkaEnabled ())
    {
      ToopKafkaManager.shutdown ();
      if (LOGGER.isInfoEnabled ())
        LOGGER.info ("Successfully shutdown Kafka client");
    }
  }
}
