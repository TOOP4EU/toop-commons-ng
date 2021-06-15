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

import javax.annotation.Nonnull;

import eu.toop.edm.model.DatasetPojo;

/**
 * A single EDM Response payload of type "Document reference"
 *
 * @author Philip Helger
 * @since 2.0.0-beta3
 */
public interface IEDMResponsePayloadDocumentReference extends IEDMResponsePayloadProvider
{
  @Nonnull
  DatasetPojo getDataset ();
}
