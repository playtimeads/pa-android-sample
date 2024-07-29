package com.zodiacscope.zenith.astroverse.Model;

public class Zodiac_Info {
    private String zodiacsign;
    private String zodiacLongitude;

    public Zodiac_Info(String zodiacSign, String zodiacLongitude) {
        this.zodiacsign = zodiacSign;
        this.zodiacLongitude = zodiacLongitude;
    }

    public String getZodiacSign() {
        return zodiacsign;
    }

    public String getZodiacLongitude() {
        return zodiacLongitude;
    }
}