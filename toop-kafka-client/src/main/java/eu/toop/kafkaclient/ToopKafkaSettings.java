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

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.collection.impl.ICommonsMap;

/**
 * Global TOOP Kafka settings.
 *
 * @author Philip Helger
 */
public final class ToopKafkaSettings
{
  public static final String DEFAULT_KAFKA_TOPIC = "toop";

  private static final Logger LOGGER = LoggerFactory.getLogger (ToopKafkaSettings.class);
  private static final AtomicBoolean s_aLoggingEnabled = new AtomicBoolean (true);
  private static final AtomicBoolean s_aKafkaEnabled = new AtomicBoolean (false);
  private static final AtomicReference <String> s_aKafkaTopic = new AtomicReference <> (DEFAULT_KAFKA_TOPIC);

  private ToopKafkaSettings ()
  {}

  /**
   * @return The default properties for customization. Changes to this map only
   *         effect new connections! Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableObject
  public static ICommonsMap <String, String> defaultProperties ()
  {
    return ToopKafkaManager.defaultProperties ();
  }

  /**
   * Enable or disable logging globally.
   *
   * @param bLoggingEnabled
   *        <code>true</code> to enable, <code>false</code> to disable.
   */
  public static void setLoggingEnabled (final boolean bLoggingEnabled)
  {
    s_aLoggingEnabled.set (bLoggingEnabled);
    if (LOGGER.isInfoEnabled ())
      LOGGER.info ("TOOP Logging is now " + (bLoggingEnabled ? "enabled" : "disabled"));
  }

  /**
   * @return <code>true</code> if Logging is enabled, <code>false</code> if not.
   *         By default is is enabled.
   */
  public static boolean isLoggingEnabled ()
  {
    return s_aLoggingEnabled.get ();
  }

  /**
   * Enable or disable globally. Call this only globally on startup.
   *
   * @param bEnabled
   *        <code>true</code> to enable, <code>false</code> to disable.
   */
  public static void setKafkaEnabled (final boolean bEnabled)
  {
    s_aKafkaEnabled.set (bEnabled);
    if (LOGGER.isInfoEnabled ())
      LOGGER.info ("TOOP Kafka Client is now " + (bEnabled ? "enabled" : "disabled"));
  }

  /**
   * @return <code>true</code> if client is enabled, <code>false</code> if not.
   *         By default is is disabled.
   */
  public static boolean isKafkaEnabled ()
  {
    return s_aKafkaEnabled.get ();
  }

  public static void setKafkaTopic (@Nonnull final String sTopic)
  {
    ValueEnforcer.notNull (sTopic, "Topic");
    s_aKafkaTopic.set (sTopic);
    if (LOGGER.isInfoEnabled ())
      LOGGER.info ("Kafka Client is now set to topic: " + s_aKafkaTopic);
  }

  @Nonnull
  public static String getKafkaTopic ()
  {
    return s_aKafkaTopic.get ();
  }
}
