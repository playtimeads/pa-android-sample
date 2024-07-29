package com.zodiacscope.zenith.astroverse.Activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zodiacscope.zenith.astroverse.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Activtiy_MatchMaking_And_Love extends AppCompatActivity {

    Activity activity;
    private ImageView iv_back;
    private TextView tv_header;
    private int selected_activity;
    private EditText et_name;
    private TextView tv_bt;
    private TextView tv_bd;
    private EditText et_place;
    private EditText et_partnername;
    private TextView tv_partnerbd;
    private TextView tv_partnerbt;
    private EditText et_partnerplace;
    private Button btn_submit;
    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener date;
    private TimePickerDialog.OnTimeSetListener time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activtiy_match_making);
        activity = Activtiy_MatchMaking_And_Love.this;

        initview();
    }

    private void initview() {

        iv_back = findViewById(R.id.iv_back);
        tv_header = findViewById(R.id.tv_header);
        tv_header.setSelected(true);

        et_name = findViewById(R.id.et_name);
        tv_bd = findViewById(R.id.tv_bd);
        tv_bt = findViewById(R.id.tv_bt);
        et_place = findViewById(R.id.et_place);
        et_partnername = findViewById(R.id.et_partnername);
        tv_partnerbd = findViewById(R.id.tv_partnerbd);
        tv_partnerbt = findViewById(R.id.tv_partnerbt);
        et_partnerplace = findViewById(R.id.et_partnerplace);
        btn_submit = findViewById(R.id.btn_submit);

        iv_back.setOnClickListener(v -> {
            onBackPressed();
        });

        selected_activity = getIntent().getIntExtra("which_page", 0);

        if (selected_activity == R.id.iv_love_compability) {
            tv_header.setText(R.string.love_compability);

            tv_partnerbt.setVisibility(View.GONE);
            tv_bt.setVisibility(View.GONE);

            et_place.setVisibility(View.GONE);
            et_partnerplace.setVisibility(View.GONE);

            btn_submit.setText(R.string.check_love_compatibility);

        } else {
            tv_header.setText(R.string.match_making);
        }


        myCalendar = Calendar.getInstance();

        date = (view, year, monthOfYear, dayOfMonth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(tv_bd);
        };


        time = (view, hourOfDay, minute) -> {
            myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            myCalendar.set(Calendar.MINUTE, minute);
            updatetime(tv_bt);
        };

        DatePickerDialog.OnDateSetListener partnerDate = (view, year, monthOfYear, dayOfMonth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(tv_partnerbd);
        };

        TimePickerDialog.OnTimeSetListener partnerTime = (view, hourOfDay, minute) -> {
            myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            myCalendar.set(Calendar.MINUTE, minute);
            updatetime(tv_partnerbt);
        };


        tv_bd.setOnClickListener(v -> new DatePickerDialog(Activtiy_MatchMaking_And_Love.this, R.style.CustomDatePickerDialog, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show());


        tv_bt.setOnClickListener(v -> new TimePickerDialog(Activtiy_MatchMaking_And_Love.this, R.style.CustomDatePickerDialog, time, myCalendar
                .get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE), true).show());


        tv_partnerbd.setOnClickListener(v -> new DatePickerDialog(Activtiy_MatchMaking_And_Love.this, R.style.CustomDatePickerDialog, partnerDate, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show());


        tv_partnerbt.setOnClickListener(v -> new TimePickerDialog(Activtiy_MatchMaking_And_Love.this, R.style.CustomDatePickerDialog, partnerTime, myCalendar
                .get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE), true).show());

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selected_activity == R.id.iv_love_compability) {
                    tv_partnerbt.setVisibility(View.GONE);
                    tv_bt.setVisibility(View.GONE);

                    et_place.setVisibility(View.GONE);
                    et_partnerplace.setVisibility(View.GONE);

                    if (et_name.getText().toString().isEmpty() || et_partnername.getText().toString().isEmpty() || tv_bd.getText().toString().isEmpty() || tv_partnerbd.getText().toString().isEmpty() && et_name.length() < 2 || et_partnername.length() < 2) {
                        Toast.makeText(activity, "Enter field detail Properly", Toast.LENGTH_SHORT).show();
                    } else {
                        startActivity(new Intent(activity, Activtiy_MatchMaking_And_Love_Result.class)
                                .putExtra("love_name", et_name.getText().toString())
                                .putExtra("partner_name", et_partnername.getText().toString())
                                .putExtra("love_bd", tv_bd.getText().toString())
                                .putExtra("partner_bd", tv_partnerbd.getText().toString())
                                .putExtra("view_type", v.getId()));
                    }


                } else {
                    if (et_name.getText().toString().isEmpty() || et_place.getText().toString().isEmpty() ||
                            et_partnername.getText().toString().isEmpty() || et_partnerplace.getText().toString().isEmpty() ||
                            tv_bd.getText().toString().isEmpty() || tv_bt.getText().toString().isEmpty() ||
                            tv_partnerbd.getText().toString().isEmpty() || tv_partnerbt.getText().toString().isEmpty()) {
                        Toast.makeText(activity, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                    } else if (et_name.length() < 3 || et_place.length() < 3 || et_partnername.length() < 3 ||
                            et_partnerplace.length() < 3 || tv_bd.length() < 3 || tv_bt.length() < 3 ||
                            tv_partnerbd.length() < 3 || tv_partnerbt.length() < 3) {
                        Toast.makeText(activity, "Please enter at least 3 characters", Toast.LENGTH_SHORT).show();
                    } else {

                        String initials = extractInitials(et_name.getText().toString());
                        String partner_initials = extractInitials(et_partnername.getText().toString());

                        String zodiacSign = determineZodiacSign(initials);
                        String partner_zodiacSign = determineZodiacSign(partner_initials);

                        String birthMonth = tv_bd.getText().toString();
                        String partner_birthMonth = tv_partnerbd.getText().toString();

                        String mooon1 = determineMoonSignFromBirthMonth(getMonth(birthMonth));
                        String mooon2 = determineMoonSignFromBirthMonth(getMonth(partner_birthMonth));

                        String ascendant1 = determineAscendantSign(zodiacSign);
                        String ascendant2 = determineAscendantSign(zodiacSign);

                        startActivity(new Intent(activity, Activtiy_MatchMaking_And_Love_Result.class)
                                .putExtra("love_name", et_name.getText().toString())
                                .putExtra("love_place", et_place.getText().toString())
                                .putExtra("love_bd", tv_bd.getText().toString())
                                .putExtra("love_bt", tv_bt.getText().toString())
                                .putExtra("partner_name", et_partnername.getText().toString())
                                .putExtra("partner_place", et_partnerplace.getText().toString())
                                .putExtra("partner_bd", tv_partnerbd.getText().toString())
                                .putExtra("partner_bt", tv_partnerbt.getText().toString())
                                .putExtra("zodiac_sign", zodiacSign).putExtra("partner_zodiac_sign", partner_zodiacSign)
                                .putExtra("love_moon", mooon1)
                                .putExtra("partner_moon", mooon2)
                                .putExtra("love_ascendant", ascendant1)
                                .putExtra("partner_ascendant", ascendant2)
                        );
                    }
                }
            }
        });
    }

    private int getMonth(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        try {
            Date parsedDate = sdf.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parsedDate);
            return calendar.get(Calendar.MONTH) + 1;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }


    private void updateLabel(TextView textView) {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        textView.setText(sdf.format(myCalendar.getTime()));
    }

    private void updatetime(TextView textView) {
        String myFormat = "hh:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        textView.setText(sdf.format(myCalendar.getTime()));
    }


    private String extractInitials(String name) {
        String[] names = name.split(" ");
        StringBuilder initials = new StringBuilder();
        for (String n : names) {
            if (!n.isEmpty()) {
                initials.append(n.charAt(0));
            }
        }
        return initials.toString().toLowerCase();
    }

    private String determineMoonSignFromBirthMonth(int birthMonth) {
        switch (birthMonth) {
            case 1:
                return "Moon in Capricorn";
            case 2:
                return "Moon in Aquarius";
            case 3:
                return "Moon in Pisces";
            case 4:
                return "Moon in Aries";
            case 5:
                return "Moon in Taurus";
            case 6:
                return "Moon in Gemini";
            case 7:
                return "Moon in Cancer";
            case 8:
                return "Moon in Leo";
            case 9:
                return "Moon in Virgo";
            case 10:
                return "Moon in Libra";
            case 11:
                return "Moon in Scorpio";
            case 12:
                return "Moon in Sagittarius";
            default:
                return "Unknown Moon Sign";
        }
    }

    private String determineAscendantSign(String zodiacSign) {
        switch (zodiacSign.toLowerCase()) {
            case "aries":
                return "Ascendant in Aries";
            case "taurus":
                return "Ascendant in Taurus";
            case "gemini":
                return "Ascendant in Gemini";
            case "cancer":
                return "Ascendant in Cancer";
            case "leo":
                return "Ascendant in Leo";
            case "virgo":
                return "Ascendant in Virgo";
            case "libra":
                return "Ascendant in Libra";
            case "scorpio":
                return "Ascendant in Scorpio";
            case "sagittarius":
                return "Ascendant in Sagittarius";
            case "capricorn":
                return "Ascendant in Capricorn";
            case "aquarius":
                return "Ascendant in Aquarius";
            case "pisces":
                return "Ascendant in Pisces";
            default:
                return "Unknown Ascendant Sign";
        }
    }


    private String determineZodiacSign(String initials) {
        switch (initials) {
            case "a":
            case "l":
            case "e":
            case "i":
            case "o":
                return "Aries";
            case "b":
            case "v":
            case "u":
            case "w":
                return "Taurus";
            case "k":
            case "chh":
            case "gh":
            case "q":
            case "c":
                return "Gemini";
            case "da":
            case "ha":
                return "Cancer";
            case "m":
            case "ta":
                return "Leo";
            case "p":
            case "tha":
                return "Virgo";
            case "r":
            case "t":
                return "Libra";
            case "n":
            case "y":
                return "Scorpio";
            case "bh":
            case "f":
            case "dh":
                return "Sagittarius";
            case "kh":
            case "j":
                return "Capricorn";
            case "g":
            case "s":
            case "sh":
                return "Aquarius";
            case "d":
            case "ch":
            case "z":
            case "th":
                return "Pisces";
            default:
                return "Not Found";
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
