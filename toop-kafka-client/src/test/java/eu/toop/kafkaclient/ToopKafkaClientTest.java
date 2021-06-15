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
package eu.toop.kafkaclient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.kafka.common.KafkaException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.error.level.EErrorLevel;

/**
 * Test class for class {@link ToopKafkaClient}.
 *
 * @author Philip Helger
 */
public final class ToopKafkaClientTest
{
  @BeforeClass
  public static void beforeAll ()
  {
    ToopKafkaSettings.setKafkaEnabled (true);
  }

  @AfterClass
  public static void afterAll ()
  {
    // Disable again for other tests
    ToopKafkaSettings.setKafkaEnabled (false);
  }

  @Test
  public void testBasic ()
  {
    if (false)
    {
      // Set the correct server to see real messages
      ToopKafkaSettings.defaultProperties ().put ("bootstrap.servers", "tracker.acc.exchange.toop.eu:7073");
    }
    try
    {
      // Don't send too many - will take forever if no Kafka server is up and
      // running!
      for (int i = 0; i < 5; ++i)
        ToopKafkaClient.send (EErrorLevel.INFO, "Value" + i);
    }
    catch (final KafkaException ex)
    {
      // lets act as if we are not surprised...
    }
    finally
    {
      ToopKafkaClient.close ();
    }
  }

  @Test
  public void testDefaultProperties ()
  {
    final ICommonsMap <String, String> aProps = ToopKafkaSettings.defaultProperties ();
    assertNotNull (aProps);
    // Ensure mutable map
    aProps.put ("foo", "bar");
    assertEquals ("bar", ToopKafkaSettings.defaultProperties ().get ("foo"));
  }
}
