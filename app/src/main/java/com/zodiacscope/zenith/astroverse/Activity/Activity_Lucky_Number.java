package com.zodiacscope.zenith.astroverse.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.zodiacscope.zenith.astroverse.Adapter.Adapter_Horoscope;
import com.zodiacscope.zenith.astroverse.Apis.Api_DailyHoroscope;
import com.zodiacscope.zenith.astroverse.Interface.Interface_Horoscope;
import com.zodiacscope.zenith.astroverse.Model.DailyHoroscope_ResponseModel;
import com.zodiacscope.zenith.astroverse.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Lucky_Number extends AppCompatActivity {

    Activity activity;
    private ImageView iv_back, iv_no_choose, iv_info;
    private TextView tv_header, tv_date, tv_lucky_number, tv_lucky_color, tv_lucky_remedy, tv_spinner_item;
    private LinearLayout ll_rashi_item, ll_data;
    String zodiacSign = "aries", date;
    private boolean isButtonClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky_number);

        activity = Activity_Lucky_Number.this;

        initview();

    }

    private void initview() {

        iv_back = findViewById(R.id.iv_back);
        tv_header = findViewById(R.id.tv_header);
        tv_lucky_number = findViewById(R.id.tv_lucky_number);
        tv_lucky_color = findViewById(R.id.tv_lucky_color);
        tv_lucky_remedy = findViewById(R.id.tv_lucky_remedy);
        tv_spinner_item = findViewById(R.id.tv_spinner_item);
        ll_rashi_item = findViewById(R.id.ll_rashi_item);
        ll_data = findViewById(R.id.ll_data);
        tv_date = findViewById(R.id.tv_date);
        iv_no_choose = findViewById(R.id.iv_no_choose);
        iv_info = findViewById(R.id.iv_info);
        iv_info.setVisibility(View.VISIBLE);
        iv_info.setOnClickListener(v -> startActivity(new Intent(activity, Activity_Rashi_Info.class)));

        tv_header.setSelected(true);
        tv_header.setText(R.string.lucky_number);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ll_rashi_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v);
            }
        });

        date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        tv_date.setText(getString(R.string.today_date) + " " + date);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void showPopupMenu(View v) {

        PopupWindow popupWindow = new PopupWindow(activity);
        View popupView = LayoutInflater.from(activity).inflate(R.layout.spinner_dropdown_item, null);
        popupWindow.setContentView(popupView);

        RecyclerView rv_sign = popupView.findViewById(R.id.recycle_view);
        ArrayList<String> delayOptions = new ArrayList<>();
        ArrayList<Integer> zodiacImages = new ArrayList<>();

        delayOptions.add(getString(R.string.Aries));
        delayOptions.add(getString(R.string.Taurus));
        delayOptions.add(getString(R.string.Gemini));
        delayOptions.add(getString(R.string.Cancer));
        delayOptions.add(getString(R.string.Leo));
        delayOptions.add(getString(R.string.Virgo));
        delayOptions.add(getString(R.string.Libra));
        delayOptions.add(getString(R.string.Scorpio));
        delayOptions.add(getString(R.string.Sagittarius));
        delayOptions.add(getString(R.string.Capricorn));
        delayOptions.add(getString(R.string.Aquarius));
        delayOptions.add(getString(R.string.Pisces));
        zodiacImages.add(R.drawable.ic_sign_aries);
        zodiacImages.add(R.drawable.ic_sign_taurus);
        zodiacImages.add(R.drawable.ic_sign_gemini);
        zodiacImages.add(R.drawable.ic_sign_cancer);
        zodiacImages.add(R.drawable.ic_sign_leo);
        zodiacImages.add(R.drawable.ic_sign_virgo);
        zodiacImages.add(R.drawable.ic_sign_libra);
        zodiacImages.add(R.drawable.ic_sign_scorpio);
        zodiacImages.add(R.drawable.ic_sign_sagittarius);
        zodiacImages.add(R.drawable.ic_sign_capricorn);
        zodiacImages.add(R.drawable.ic_sign_aquarius);
        zodiacImages.add(R.drawable.ic_sign_pisces);

        Adapter_Horoscope adapter = new Adapter_Horoscope(delayOptions,zodiacImages);
        rv_sign.setLayoutManager(new LinearLayoutManager(activity));
        rv_sign.setAdapter(adapter);


        adapter.setListener(position -> {

            isButtonClicked = true;
            zodiacSign = position;
            tv_spinner_item.setText(zodiacSign);
            popupWindow.dismiss();
            fetchHoroscope(zodiacSign);

            Log.d("api_call", "Zodiac_Sign : " + zodiacSign);
            if (isButtonClicked) {
                ll_data.setVisibility(View.VISIBLE);
                iv_no_choose.setVisibility(View.GONE);
            } else {
                ll_data.setVisibility(View.GONE);
                iv_no_choose.setVisibility(View.VISIBLE);
            }

        });


        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);

        popupWindow.showAsDropDown(ll_rashi_item, 0, 0);

    }

    private void fetchHoroscope(String zodiacSign) {
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

                            String color = horoscope.getColor();
                            String number = horoscope.getNumber();
                            String remedy = horoscope.getRemedy();
                            String cleanedRemedy = remedy.replace("&nbsp;", " ");


                            tv_lucky_number.setText(getString(R.string.your_lucky_number_is) + " " + number);
                            tv_lucky_color.setText(getString(R.string.your_lucky_color_is) + " " + color);
                            tv_lucky_remedy.setText(getString(R.string.your_remedy_is) + " " + cleanedRemedy);


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
}