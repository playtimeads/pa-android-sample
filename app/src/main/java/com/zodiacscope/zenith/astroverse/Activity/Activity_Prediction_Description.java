package com.zodiacscope.zenith.astroverse.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zodiacscope.zenith.astroverse.R;

public class Activity_Prediction_Description extends AppCompatActivity {

    Activity activity;
    TextView tv_header;
    TextView tv_desc, tv_sign_name;
    private ImageView iv_back, iv_sign_images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediction_description);

        activity = Activity_Prediction_Description.this;

        initview();
    }

    private void initview() {

        tv_header = (TextView) findViewById(R.id.tv_header);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_desc = (TextView) findViewById(R.id.tv_desc);
        iv_sign_images = (ImageView) findViewById(R.id.iv_sign_images);
        tv_sign_name = (TextView) findViewById(R.id.tv_sign_name);

        tv_header.setSelected(true);

        String title = getIntent().getStringExtra("prediction_header");
        String str_head = TextUtils.capitalizeFirstLetter(title);
        String desc = getIntent().getStringExtra("prediction_desc");
        int image = getIntent().getIntExtra("zodiac_images", 0);


        tv_header.setText("Your Today Horoscope Details");
        tv_desc.setText(desc);

        iv_sign_images.setImageResource(image);
        tv_sign_name.setText(str_head);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    public static class TextUtils {
        public static String capitalizeFirstLetter(String text) {
            if (text == null || text.length() == 0) {
                return text;
            }
            return text.substring(0, 1).toUpperCase() + text.substring(1);
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

