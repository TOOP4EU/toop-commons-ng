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
package eu.toop.commons.codelist;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.id.IHasID;
import com.helger.commons.name.IHasName;
import com.helger.commons.version.Version;

/**
 * Base interface for a single predefined code list item.
 *
 * @author Philip Helger
 */
public interface IPredefined extends IHasID <String>, IHasName
{
  /**
   * @return The code list version when the item was added. Never
   *         <code>null</code>.
   */
  @Nonnull
  Version getSince ();

  /**
   * @return <code>true</code> if this code list entry is deprecated in the code
   *         list.
   * @see #getDeprecatedSince()
   */
  boolean isDeprecated ();

  /**
   * @return The code list version when the item was deprecated. May be
   *         <code>null</code>. Must not be <code>null</code> if
   *         {@link #isDeprecated()} returns <code>true</code>.
   * @see #isDeprecated()
   */
  @Nullable
  Version getDeprecatedSince ();
}
