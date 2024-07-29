package com.zodiacscope.zenith.astroverse.Model;

import android.content.SharedPreferences;


public class Muhrat_Session {
    public static final String USER_INTERACTION_COUNT = "USER_INTERACTION_COUNT";

    public static int getInteractionCount() {
        return Zodiac_MyApp.getPreference().getInt(USER_INTERACTION_COUNT, 0);
    }

    public static void incrementInteractionCount() {
        SharedPreferences.Editor edit = Zodiac_MyApp.getPreference().edit();
        edit.putInt(USER_INTERACTION_COUNT, getInteractionCount() + 1);
        edit.apply();
    }
}