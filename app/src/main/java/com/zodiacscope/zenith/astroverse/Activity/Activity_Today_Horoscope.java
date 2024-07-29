package com.zodiacscope.zenith.astroverse.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.zodiacscope.zenith.astroverse.Apis.Api_DailyHoroscope;
import com.zodiacscope.zenith.astroverse.Interface.Interface_Horoscope;
import com.zodiacscope.zenith.astroverse.Model.DailyHoroscope_ResponseModel;
import com.zodiacscope.zenith.astroverse.R;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Today_Horoscope extends AppCompatActivity {

    private TextView tv_header;
    private ImageView iv_back;
    private LinearLayout[] ll_zodiac;
    Activity activity;
    private ImageView iv_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_horoscope);

        activity = Activity_Today_Horoscope.this;

        initViews();
        setupClickListeners();
    }

    private void initViews() {
        iv_back = findViewById(R.id.iv_back);
        tv_header = findViewById(R.id.tv_header);

        tv_header.setSelected(true);
        tv_header.setText(R.string.today_horoscope);

        iv_info = findViewById(R.id.iv_info);
        iv_info.setVisibility(View.VISIBLE);
        iv_info.setOnClickListener(v -> startActivity(new Intent(activity, Activity_Rashi_Info.class)));


        ll_zodiac = new LinearLayout[]{
                findViewById(R.id.ll_aries),
                findViewById(R.id.ll_taurus),
                findViewById(R.id.ll_gemini),
                findViewById(R.id.ll_cancer),
                findViewById(R.id.ll_leo),
                findViewById(R.id.ll_virgo),
                findViewById(R.id.ll_libra),
                findViewById(R.id.ll_scorpio),
                findViewById(R.id.ll_sagittarius),
                findViewById(R.id.ll_capricorn),
                findViewById(R.id.ll_aquarius),
                findViewById(R.id.ll_pisces)
        };

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setupClickListeners() {

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String zodiacSign = "aries";
                int zodiac_images = R.drawable.ic_aries;

                if (view.getId() == R.id.ll_taurus) {
                    zodiacSign = "taurus";
                    zodiac_images = R.drawable.ic_taurus;
                } else if (view.getId() == R.id.ll_gemini) {
                    zodiacSign = "gemini";
                    zodiac_images = R.drawable.ic_gemini;
                } else if (view.getId() == R.id.ll_cancer) {
                    zodiacSign = "cancer";
                    zodiac_images = R.drawable.ic_cancer;
                } else if (view.getId() == R.id.ll_leo) {
                    zodiacSign = "leo";
                    zodiac_images = R.drawable.ic_leo;
                } else if (view.getId() == R.id.ll_virgo) {
                    zodiacSign = "virgo";
                    zodiac_images = R.drawable.ic_virgo;
                } else if (view.getId() == R.id.ll_libra) {
                    zodiacSign = "libra";
                    zodiac_images = R.drawable.ic_libra;
                } else if (view.getId() == R.id.ll_scorpio) {
                    zodiacSign = "scorpio";
                    zodiac_images = R.drawable.ic_scorpio;
                } else if (view.getId() == R.id.ll_sagittarius) {
                    zodiacSign = "sagittarius";
                    zodiac_images = R.drawable.ic_sagittarius;
                } else if (view.getId() == R.id.ll_capricorn) {
                    zodiacSign = "capricorn";
                    zodiac_images = R.drawable.ic_capricorn;
                } else if (view.getId() == R.id.ll_aquarius) {
                    zodiacSign = "aquarius";
                    zodiac_images = R.drawable.ic_aquarius;
                } else if (view.getId() == R.id.ll_pisces) {
                    zodiacSign = "pisces";
                    zodiac_images = R.drawable.ic_pisces;
                }

                fetchHoroscope(zodiacSign, zodiac_images);
            }
        };

        for (LinearLayout ll_click : ll_zodiac) {
            ll_click.setOnClickListener(clickListener);
        }
    }

    private void fetchHoroscope(String zodiacSign, int zodiac_images) {
        try {
            Interface_Horoscope service = Api_DailyHoroscope.getApiService();

            JSONObject details = new JSONObject();
            details.put("horoscope", zodiacSign);

            Log.d("api_call", "Request Body : " + details.toString());
            Log.d("api_call", "Request : " + service.getHoroscope(details.toString()).request().url().toString());

            Call<DailyHoroscope_ResponseModel> call = service.getHoroscope(details.toString());

            call.enqueue(new Callback<DailyHoroscope_ResponseModel>() {
                @Override
                public void onResponse(Call<DailyHoroscope_ResponseModel> call, Response<DailyHoroscope_ResponseModel> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        DailyHoroscope_ResponseModel dailyHoroscopeResponseModel = response.body();

                        Log.d("api_call", "Response : " + new Gson().toJson(response.body()));

                        if (dailyHoroscopeResponseModel.getHoroscopes() != null && !dailyHoroscopeResponseModel.getHoroscopes().isEmpty()) {
                            DailyHoroscope_ResponseModel horoscope = dailyHoroscopeResponseModel.getHoroscopes().get(0);

                            String prediction = horoscope.getPrediction();
                            prediction = formatPrediction(prediction);

                            startActivity(new Intent(activity, Activity_Prediction_Description.class)
                                    .putExtra("prediction_desc", prediction)
                                    .putExtra("prediction_header", zodiacSign)
                                    .putExtra("zodiac_images", zodiac_images));


                        } else {
                            Toast.makeText(activity, "No horoscope data found", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(activity, "Failed to retrieve horoscope", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<DailyHoroscope_ResponseModel> call, Throwable t) {
                    Toast.makeText(activity, "API call failed", Toast.LENGTH_SHORT).show();
                    Log.e("api_call", "Failed: " + t.getMessage());
                }
            });

        } catch (JSONException e) {
            Toast.makeText(activity, "JSONException: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("JSONException", "JSONException: " + e.getMessage());
        }
    }

    private String formatPrediction(String prediction) {
        int periodCount = 0;

        for (int i = 0; i < prediction.length(); i++) {
            if (prediction.charAt(i) == '.') {
                periodCount++;
                if (periodCount == 5 || periodCount == 6 || periodCount == 3 || periodCount == 2 ) {
                    prediction = prediction.substring(0, i + 1) + "\n\n" + prediction.substring(i + 1).trim();
                    i++;
                }
            }
        }

        return prediction;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
