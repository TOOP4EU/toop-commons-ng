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
package eu.toop.edm.model;

import javax.annotation.Nonnull;
import javax.xml.namespace.QName;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.id.IHasID;

/**
 * Abstract interface for a predefined TOOP concept.
 *
 * @author Philip Helger
 */
public interface IConceptName extends IHasID <String>
{
  @Nonnull
  @Nonempty
  String getConceptNamespaceURI ();

  /**
   * @return a new QName from {@link #getConceptNamespaceURI()} and
   *         {@link #getID()}
   */
  @Nonnull
  @ReturnsMutableCopy
  default QName getAsQName ()
  {
    return new QName (getConceptNamespaceURI (), getID ());
  }
}
