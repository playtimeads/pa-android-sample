package com.zodiacscope.zenith.astroverse.Interface;

import com.zodiacscope.zenith.astroverse.Model.Festival_Response;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Festival_Api_Interface {
    @FormUrlEncoded
    @POST("AstrozenithZodiac/api100/BABU")
    Call<Festival_Response> getFestivalDetails(@Field("details") String details);
}
