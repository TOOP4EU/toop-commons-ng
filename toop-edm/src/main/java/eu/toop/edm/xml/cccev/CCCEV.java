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
package eu.toop.edm.xml.cccev;

import java.util.List;

import javax.annotation.Nonnull;

import com.helger.commons.collection.impl.CommonsLinkedHashSet;
import com.helger.commons.collection.impl.ICommonsSet;
import com.helger.commons.io.resource.ClassPathResource;

import eu.toop.edm.xml.cv.CCV;
import eu.toop.edm.xml.dcatap.CDCatAP;

/**
 * CCCEV constants
 *
 * @author Philip Helger
 */
public final class CCCEV
{
  @Nonnull
  private static final ClassLoader _getCL ()
  {
    return CCCEV.class.getClassLoader ();
  }

  public static final List <ClassPathResource> XSDS;
  static
  {
    final ICommonsSet <ClassPathResource> aSet = new CommonsLinkedHashSet <> ();
    aSet.addAll (CCV.XSDS);
    aSet.addAll (CDCatAP.XSDS);
    aSet.addAll (new ClassPathResource ("schemas/CV-CommonAggregateComponents.xsd", _getCL ()),
                 new ClassPathResource ("schemas/CV-Agent.xsd", _getCL ()),
                 new ClassPathResource ("schemas/owl.xsd", _getCL ()),
                 new ClassPathResource ("schemas/cccev-2.0.0.xsd", _getCL ()));
    XSDS = aSet.getCopyAsList ().getAsUnmodifiable ();
  }

  private CCCEV ()
  {}
}
