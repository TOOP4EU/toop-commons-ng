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

import com.helger.commons.annotation.CodingStyleguideUnaware;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.version.Version;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;


/**
 * This file was automatically generated.
 * Do NOT edit!
 */
@CodingStyleguideUnaware
public enum EPredefinedTransportProfile
    implements IPredefined
{

    /**
     * CEF AS4 1.0 - <code>bdxr-transport-ebms3-as4-v1p0</code><br>
     * 
     * @since code list v1
     */
    BDXR_TRANSPORT_EBMS3_AS4_V1P0("CEF AS4 1.0", "bdxr-transport-ebms3-as4-v1p0", Version.parse("1"), false, null);
    private final String m_sName;
    private final String m_sID;
    private final Version m_aSince;
    private final boolean m_bDeprecated;
    private final Version m_aDeprecatedSince;

    private EPredefinedTransportProfile(@Nonnull @Nonempty final String sName,
        @Nonnull @Nonempty final String sID,
        @Nonnull final Version aSince,
        final boolean bDeprecated,
        @Nullable final Version aDeprecatedSince) {
        m_sName = sName;
        m_sID = sID;
        m_aSince = aSince;
        m_bDeprecated = bDeprecated;
        m_aDeprecatedSince = aDeprecatedSince;
    }

    @Nonnull
    @Nonempty
    public String getName() {
        return m_sName;
    }

    @Nonnull
    @Nonempty
    public String getID() {
        return m_sID;
    }

    @Nonnull
    public Version getSince() {
        return m_aSince;
    }

    public boolean isDeprecated() {
        return m_bDeprecated;
    }

    @Nullable
    public Version getDeprecatedSince() {
        return m_aDeprecatedSince;
    }

    @Nullable
    public static EPredefinedTransportProfile getFromTransportProfileOrNull(@Nullable final String sTransportProfileID) {
        for (EPredefinedTransportProfile e: EPredefinedTransportProfile.values()) {
            if (e.getID().equals(sTransportProfileID)) {
                return e;
            }
        }
        return null;
    }
}
