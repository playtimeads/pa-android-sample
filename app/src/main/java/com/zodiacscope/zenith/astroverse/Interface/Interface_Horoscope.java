package com.zodiacscope.zenith.astroverse.Interface;

import com.zodiacscope.zenith.astroverse.Model.DailyHoroscope_ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Interface_Horoscope {

    @FormUrlEncoded
    @POST("astrozenithzodiac")
    Call<DailyHoroscope_ResponseModel> getHoroscope(@Field("details") String details);
}



