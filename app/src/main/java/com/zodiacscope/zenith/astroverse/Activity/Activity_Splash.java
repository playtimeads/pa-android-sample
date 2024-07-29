package com.zodiacscope.zenith.astroverse.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.zodiacscope.zenith.astroverse.R;

public class Activity_Splash extends AppCompatActivity {

    private static final String PREFS_NAME = "IS_FIRST_TIME";
    private static final String KEY_FIRST_LAUNCH = "firstLaunch";
    private static final String KEY_USER_AGREED = "userAgreed";

    private TextView  tv2, tv3;
    public static Activity activity;
    private boolean isFirstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        activity = Activity_Splash.this;

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        isFirstTime = sharedPreferences.getBoolean(KEY_FIRST_LAUNCH, true);

        initViews();
        animateTextViews();
    }


    private void initViews() {
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);

        setVisibilityGone( tv2, tv3);
    }

    private void animateTextViews() {
        final Animation slideInLeft = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
        Handler handler = new Handler();

        animateWithDelay(handler, 500, tv2, slideInLeft);
        animateWithDelay(handler, 1500,tv3 , slideInLeft);

        handler.postDelayed(() -> {
            SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            boolean userAgreed = sharedPreferences.getBoolean(KEY_USER_AGREED, false);

            if (isFirstTime) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(KEY_FIRST_LAUNCH, false);
                editor.apply();
                startActivity(new Intent(Activity_Splash.this, Activity_Confirmation.class));
            } else if (!userAgreed) {
                startActivity(new Intent(Activity_Splash.this, Activity_Confirmation.class));
            } else {
                startActivity(new Intent(Activity_Splash.this, Activity_Home.class));
            }

        }, 5000);
    }

    private void animateWithDelay(Handler handler, long delayMillis, TextView textView, Animation animation) {
        handler.postDelayed(() -> {
            animateTextView(textView, animation);
        }, delayMillis);
    }

    private void animateTextView(TextView textView, Animation animation) {
        if (textView != null) {
            textView.startAnimation(animation);
            textView.setVisibility(View.VISIBLE);
        }
    }

    private void setVisibilityGone(View... views) {
        for (View view : views) {
            if (view != null) {
                view.setVisibility(View.INVISIBLE);
            }
        }
    }
}
