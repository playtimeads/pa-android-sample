package com.zodiacscope.zenith.astroverse.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DailyHoroscope_ResponseModel {

    @SerializedName("astrozenithzodiac")
    private List<DailyHoroscope_ResponseModel> horoscopes;


    @SerializedName("status")
    private String status;
    @SerializedName("id")
    private String id;

    @SerializedName("horoscope")
    private String horoscope;

    @SerializedName("prediction")
    private String prediction;

    @SerializedName("number")
    private String number;

    @SerializedName("color")
    private String color;


    @SerializedName("remedy")
    private String remedy;



    public List<DailyHoroscope_ResponseModel> getHoroscopes() {
        return horoscopes;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getPrediction() {
        return prediction;
    }


    public String getNumber() {
        return number;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public String getRemedy() {
        return remedy;
    }


}
