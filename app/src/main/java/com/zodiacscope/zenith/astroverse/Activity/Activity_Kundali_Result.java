package com.zodiacscope.zenith.astroverse.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import com.zodiacscope.zenith.astroverse.Apis.Api_DailyHoroscope;
import com.zodiacscope.zenith.astroverse.Interface.Interface_Horoscope;
import com.zodiacscope.zenith.astroverse.Model.DailyHoroscope_ResponseModel;
import com.zodiacscope.zenith.astroverse.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Kundali_Result extends AppCompatActivity {

    Activity activity;
    private ImageView iv_back, iv_info;
    private TextView tv_header, tv_kundali_result;
    private String name, date, place, time, sunSign, moonSign, nakshatra;

    View view;
    private static final int PERMISSION_REQUEST_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kundali_result);

        activity = Activity_Kundali_Result.this;

        initViews();

        name = getIntent().getStringExtra("kundali_name");
        date = getIntent().getStringExtra("kundali_birthdate");
        time = getIntent().getStringExtra("kundali_birthtime");
        place = getIntent().getStringExtra("kundali_place");
        sunSign = getIntent().getStringExtra("kundali_zodiacSign");
        moonSign = getIntent().getStringExtra("kundali_moonSign");
        nakshatra = getIntent().getStringExtra("kundali_nakshatra");

        fetchHoroscope(sunSign);
    }

    private void initViews() {
        iv_back = findViewById(R.id.iv_back);
        tv_header = findViewById(R.id.tv_header);
        tv_header.setSelected(true);
        tv_header.setText(R.string.kundli_result);
        view = findViewById(R.id.parent_layout);
        tv_kundali_result = findViewById(R.id.tv_kundali_result);
        iv_info = findViewById(R.id.iv_info);
        iv_info.setVisibility(View.VISIBLE);

        iv_info.setImageResource(R.drawable.ic_share_kundali);

        iv_info.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSION_REQUEST_CODE);
            } else {
                captureAndShareScreenshot();
            }
        });

        iv_back.setOnClickListener(v -> onBackPressed());

    }

    private void captureAndShareScreenshot() {

        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String pathofBmp = MediaStore.Images.Media.insertImage(activity.getContentResolver(), bitmap, "IMG_" + Calendar.getInstance().getTime(), null);

        Uri uri = Uri.parse(pathofBmp);
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Share");
        shareIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.app_name) + ", Hey View My Kundali Analysis ");
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        activity.startActivity(Intent.createChooser(shareIntent, "hello hello"));


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                captureAndShareScreenshot();
            } else {
                Toast.makeText(this, "Permission denied. Cannot share screenshot.", Toast.LENGTH_SHORT).show();
            }
        }
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

                            String prediction = horoscope.getPrediction();
                            String shortDescription = generateShortDescription(prediction);

                            String astroAnalysis = "Name : " + name + "\n" + "Date of Birth : " + date + "\n" + "Birth Time : " + time + "\n" + "Birth Place : " + place + "\n\n" +
                                    "Based on your Kundali analysis, here are some insights for you :\n\n" +
                                    "Sun Sign (Zodiac Sign) : " + sunSign + "\n" +
                                    "Zodiac Longitude : " + moonSign + "\n" +
                                    "Nakshatra : " + nakshatra + "\n\n" +
                                    "From Your Zodiac Your Astrological Analysis : " + " " + shortDescription;

                            tv_kundali_result.setText(astroAnalysis);

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

    private String generateShortDescription(String prediction) {
        StringBuilder shortDescription = new StringBuilder();
        String[] sentences = prediction.split("\\.\\s+");

        int wordCount = 0;
        boolean foundCompleteSentence = false;

        for (String sentence : sentences) {
            String[] words = sentence.split("\\s+");

            for (String word : words) {
                shortDescription.append(word).append(' ');
                wordCount++;

                if (wordCount % 40 == 0) {
                    shortDescription.append("\n\n");
                }

                if (wordCount >= 100) {
                    foundCompleteSentence = true;
                    break;
                }
            }

            if (foundCompleteSentence) {
                break;
            }
        }

        String result = shortDescription.toString().trim();
        if (!result.endsWith(".")) {
            result += ".";
        }

        return result;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
