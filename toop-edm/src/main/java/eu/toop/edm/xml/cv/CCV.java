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
package eu.toop.edm.xml.cv;

import java.util.List;

import javax.annotation.Nonnull;

import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.ubl23.CUBL23;

/**
 * Constants for the Core vocabulary
 *
 * @author Philip Helger
 */
public final class CCV
{
  @Nonnull
  private static final ClassLoader _getCL ()
  {
    return CCV.class.getClassLoader ();
  }

  public static final List <ClassPathResource> XSDS = new CommonsArrayList <> (CUBL23.XSD_UNQUALIFIED_DATA_TYPES,
                                                                               new ClassPathResource ("schemas/CoreVocabularies-BasicComponents-1.1.xsd",
                                                                                                      _getCL ()),
                                                                               new ClassPathResource ("schemas/CoreVocabularies-AggregateComponents-1.1.xsd",
                                                                                                      _getCL ())).getAsUnmodifiable ();

  private CCV ()
  {}
}
