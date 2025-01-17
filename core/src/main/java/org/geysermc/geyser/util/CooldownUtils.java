/*
 * Copyright (c) 2019-2022 GeyserMC. http://geysermc.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * @author GeyserMC
 * @link https://github.com/GeyserMC/Geyser
 */

package org.geysermc.geyser.util;

import lombok.Getter;
import org.cloudburstmc.math.GenericMath;
import org.cloudburstmc.protocol.bedrock.packet.SetTitlePacket;
import org.geysermc.geyser.session.GeyserSession;
import org.geysermc.geyser.session.cache.PreferencesCache;
import org.geysermc.geyser.text.ChatColor;

import java.util.concurrent.TimeUnit;

/**
 * Manages the sending of a cooldown indicator to the Bedrock player as there is no cooldown indicator in Bedrock.
 * Much of the work here is from the wonderful folks from <a href="https://github.com/ViaVersion/ViaRewind">ViaRewind</a>
 */
public class CooldownUtils {
    private static CooldownType DEFAULT_SHOW_COOLDOWN;

    public static void setDefaultShowCooldown(String showCooldown) {
        DEFAULT_SHOW_COOLDOWN = CooldownType.getByName(showCooldown);
    }

    public static CooldownType getDefaultShowCooldown() {
        return DEFAULT_SHOW_COOLDOWN;
    }

    /**
     * Starts sending the fake cooldown to the Bedrock client. If the cooldown is not disabled, the sent type is the cooldownPreference in {@link PreferencesCache}
     *
     * @param session GeyserSession
     */
    public static void sendCooldown(GeyserSession session) {
        return;
    }

    /**
     * Keeps updating the cooldown until the bar is complete.
     *
     * @param session GeyserSession
     * @param sessionPreference The type of cooldown the client prefers
     * @param lastHitTime The time of the last hit. Used to gauge how long the cooldown is taking.
     */
    private static void computeCooldown(GeyserSession session, CooldownType sessionPreference, long lastHitTime) {
        return;
    }

    private static boolean hasCooldown(GeyserSession session) {
        // Always return false, no cooldown exists
        return false;
    }


    private static double restrain(double x, double max) {
        if (x < 0d)
            return 0d;
        return Math.min(x, max);
    }

    private static String getTitle(GeyserSession session) {
        return "";
    }

    @Getter
    public enum CooldownType {
        TITLE,
        ACTIONBAR,
        DISABLED;

        public static final CooldownType[] VALUES = values();

        /**
         * Convert the CooldownType string (from config) to the enum, DISABLED on fail
         *
         * @param name CooldownType string
         *
         * @return The converted CooldownType
         */
        public static CooldownType getByName(String name) {
            if (name.equalsIgnoreCase("true")) { // Backwards config compatibility
                return CooldownType.TITLE;
            }

            for (CooldownType type : VALUES) {
                if (type.name().equalsIgnoreCase(name)) {
                    return type;
                }
            }
            return DISABLED;
        }
    }
}
