package com.zodiacscope.zenith.astroverse.Activity;

import static com.zodiacscope.zenith.astroverse.Activity.Activity_Home.showToast;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zodiacscope.zenith.astroverse.R;

public class Activity_Confirmation extends AppCompatActivity {

    private static final String PREFS_NAME = "IS_FIRST_TIME";
    private static final String KEY_USER_AGREED = "userAgreed";

    Activity activity;
    CheckBox cv_agree;
    TextView tv_policy, tv_continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);


        activity = Activity_Confirmation.this;

        initView();
    }

    private void initView() {



        cv_agree = findViewById(R.id.cv_agree);
        tv_policy = findViewById(R.id.tv_policy);
        tv_policy.setSelected(true);
        tv_continue = findViewById(R.id.tv_continue);

        cv_agree.setChecked(false);

        tv_continue.setOnClickListener(v -> {
            if (cv_agree.isChecked()) {
                SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(KEY_USER_AGREED, true);
                editor.apply();
                startActivity(new Intent(activity, Activity_Home.class));
            } else {
                Toast.makeText(activity, "Agree to Terms and Conditions", Toast.LENGTH_SHORT).show();
            }
        });

        tv_policy.setOnClickListener(v -> {
            String url = "https://eazyearning.com/AstrozenithZodiac/Privacy.html";
            try {
                if (URLUtil.isValidUrl(url)) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                } else {
                    showToast(getApplicationContext(), "Invalid URL");
                }
            } catch (ActivityNotFoundException e) {
                showToast(getApplicationContext(), "Browser not found");
            }

        });
    }
}
