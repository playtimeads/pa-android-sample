package com.zodiacscope.zenith.astroverse.Activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zodiacscope.zenith.astroverse.Model.Zodiac_Info;
import com.zodiacscope.zenith.astroverse.R;

import java.util.Calendar;

public class Activity_Kundali extends AppCompatActivity {

    Activity activity;
    Button btn_submit;
    EditText et_place, et_name;
    TextView tv_bt, tv_bd;

    SharedPreferences sharedPreferences;
    private ImageView iv_back;
    private View tv_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kundali);

        activity = Activity_Kundali.this;
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        initview();
    }

    private void initview() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_header = (TextView) findViewById(R.id.tv_header);
        tv_header.setSelected(true);

        iv_back.setOnClickListener(v -> {
            onBackPressed();
        });

        btn_submit = findViewById(R.id.btn_submit);
        et_place = findViewById(R.id.et_place);
        tv_bt = findViewById(R.id.tv_bt);
        tv_bd = findViewById(R.id.tv_bd);
        et_name = findViewById(R.id.et_name);

        tv_bd.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    activity,
                    R.style.CustomDatePickerDialog,
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        tv_bd.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1);
                    },
                    year, month, day);
            datePickerDialog.show();
        });

        tv_bt.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    activity,
                    R.style.CustomDatePickerDialog,
                    (view, hourOfDay, minute1) -> {
                        String timeString = String.format("%02d:%02d", hourOfDay, minute1);
                        tv_bt.setText(timeString);
                    },
                    hour, minute, true);
            timePickerDialog.show();
        });

        btn_submit.setOnClickListener(v -> {
            String name = et_name.getText().toString();
            String place = et_place.getText().toString();
            String birthdate = tv_bd.getText().toString();
            String birthtime = tv_bt.getText().toString();

            if (name.isEmpty() || place.isEmpty() || birthdate.isEmpty() || birthtime.isEmpty()) {
                Toast.makeText(activity, "No Empty Field Allowed", Toast.LENGTH_SHORT).show();
            } else if (name.length() < 3 || place.length() < 3 || birthdate.length() < 3 || birthtime.length() < 3) {
                Toast.makeText(activity, "More than 3 characters required", Toast.LENGTH_SHORT).show();
            } else {


                String initials = extractInitials(name);
                Zodiac_Info zodiacInfo = determineZodiacSign(initials);

                String zodiacSign = zodiacInfo.getZodiacSign();
                String zodiacLongitude = zodiacInfo.getZodiacLongitude();

                String nakshatra = determineNakshatra(zodiacSign);

                startActivity(new Intent(activity, Activity_Kundali_Result.class)
                        .putExtra("kundali_name", name)
                        .putExtra("kundali_place", place)
                        .putExtra("kundali_birthdate", birthdate)
                        .putExtra("kundali_birthtime", birthtime)
                        .putExtra("kundali_zodiacSign", zodiacSign)
                        .putExtra("kundali_moonSign", zodiacLongitude)
                        .putExtra("kundali_nakshatra", nakshatra)
                );
            }
        });
    }

    private String extractInitials(String name) {
        String[] names = name.split(" ");
        StringBuilder initials = new StringBuilder();
        for (String n : names) {
            if (!n.isEmpty()) {
                initials.append(n.charAt(0));
            }
        }
        return initials.toString().toUpperCase();
    }

    private Zodiac_Info determineZodiacSign(String initials) {
        String zodiacSign = "";
        String zoidac_Longitude = "0°";

        switch (initials.toLowerCase()) {
            case "a":
            case "l":
            case "e":
            case "i":
            case "o":
                zodiacSign = "Aries";
                zoidac_Longitude = "0°";
                break;
            case "b":
            case "v":
            case "u":
            case "w":
                zodiacSign = "Taurus";
                zoidac_Longitude = "30°";
                break;
            case "k":
            case "chh":
            case "gh":
            case "q":
            case "c":
                zodiacSign = "Gemini";
                zoidac_Longitude = "60°";
                break;
            case "da":
            case "ha":
                zodiacSign = "Cancer";
                zoidac_Longitude = "90°";
                break;
            case "m":
            case "ta":
                zodiacSign = "Leo";
                zoidac_Longitude = "120°";
                break;
            case "p":
            case "tha":
                zodiacSign = "Virgo";
                zoidac_Longitude = "150°";
                break;
            case "r":
            case "t":
                zodiacSign = "Libra";
                zoidac_Longitude = "180°";
                break;
            case "n":
            case "y":
                zodiacSign = "Scorpio";
                zoidac_Longitude = "210°";
                break;
            case "bh":
            case "f":
            case "dh":
                zodiacSign = "Sagittarius";
                zoidac_Longitude = "240°";
                break;
            case "kh":
            case "j":
                zodiacSign = "Capricorn";
                zoidac_Longitude = "270°";
                break;
            case "g":
            case "s":
            case "sh":
                zodiacSign = "Aquarius";
                zoidac_Longitude = "300°";
                break;
            case "d":
            case "ch":
            case "z":
            case "th":
                zodiacSign = "Pisces";
                zoidac_Longitude = "330°";
                break;
            default:
                zodiacSign = "Not Found";
                break;
        }

        return (new Zodiac_Info(zodiacSign, zoidac_Longitude));
    }

    private String determineNakshatra(String zodiacSign) {
        switch (zodiacSign) {
            case "Aries":
                return "Ashwini";
            case "Taurus":
                return "Krittika";
            case "Gemini":
                return "Mrigashira";
            case "Cancer":
                return "Pushya";
            case "Leo":
                return "Magha";
            case "Virgo":
                return "Hasta";
            case "Libra":
                return "Chitra";
            case "Scorpio":
                return "Jyeshtha";
            case "Sagittarius":
                return "Mula";
            case "Capricorn":
                return "Uttara Ashadha";
            case "Aquarius":
                return "Shatabhisha";
            case "Pisces":
                return "Revati";
            default:
                return "Not Found";
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
