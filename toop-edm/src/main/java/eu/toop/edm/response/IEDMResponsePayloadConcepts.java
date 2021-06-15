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
package eu.toop.edm.response;

import java.util.List;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;

import eu.toop.edm.model.ConceptPojo;

/**
 * A single EDM Response payload of type "Concept"
 *
 * @author Philip Helger
 * @since 2.0.0-beta3
 */
public interface IEDMResponsePayloadConcepts extends IEDMResponsePayloadProvider
{
  /**
   * @return All contains concepts as a mutable list. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableObject
  List <ConceptPojo> concepts ();

  /**
   * @return All contains concepts as a copied list. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  List <ConceptPojo> getAllConcepts ();
}
