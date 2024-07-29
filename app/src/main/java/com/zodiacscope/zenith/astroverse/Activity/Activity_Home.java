package com.zodiacscope.zenith.astroverse.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.URLUtil;
import android.widget.EdgeEffect;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import com.google.android.material.navigation.NavigationView;
import com.zodiacscope.zenith.astroverse.Custom_Views.Advance_3d_DrawerLayout;
import com.zodiacscope.zenith.astroverse.R;

import kotlin.jvm.internal.Intrinsics;

public class Activity_Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Activity activity;
    private ImageView iv_today_muhrat, iv_daily_horoscope, iv_match_making, iv_lucky_number, iv_kundli, iv_festival, iv_mole_interpretaion, iv_love_compability;
    private Advance_3d_DrawerLayout advnce_drawer;
    private TextView tv_version;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        activity = Activity_Home.this;

        initview();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initview() {

        iv_today_muhrat = (ImageView) findViewById(R.id.iv_today_muhrat);
        iv_daily_horoscope = (ImageView) findViewById(R.id.iv_today_horoscope);
        iv_match_making = (ImageView) findViewById(R.id.iv_match_making);
        iv_lucky_number = (ImageView) findViewById(R.id.iv_lucky_number);
        iv_kundli = (ImageView) findViewById(R.id.iv_kundli);
        iv_festival = (ImageView) findViewById(R.id.iv_festival);
        iv_mole_interpretaion = (ImageView) findViewById(R.id.iv_mole_interpretaion);
        iv_love_compability = (ImageView) findViewById(R.id.iv_love_compability);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        advnce_drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, advnce_drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        advnce_drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        tv_version = headerView.findViewById(R.id.tv_appversion);


        try {
            tv_version.setText("App Version : " + getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }

        advnce_drawer.setViewScale(GravityCompat.START, 0.95f);
        advnce_drawer.setRadius(GravityCompat.START, 30f);
        advnce_drawer.setViewElevation(GravityCompat.START, 20f);


        iv_today_muhrat.setOnClickListener(v -> {
            startActivity(new Intent(activity, Activity_SubhMuhrat.class));
        });

        iv_daily_horoscope.setOnClickListener(v -> {
            startActivity(new Intent(activity, Activity_Today_Horoscope.class));
        });

        iv_match_making.setOnClickListener(v -> {
            startActivity(new Intent(activity, Activtiy_MatchMaking_And_Love.class));
        });

        iv_lucky_number.setOnClickListener(v -> {
            startActivity(new Intent(activity, Activity_Lucky_Number.class));
        });

        iv_kundli.setOnClickListener(v -> {
            startActivity(new Intent(activity, Activity_Kundali.class));
        });

        iv_festival.setOnClickListener(v -> {
            startActivity(new Intent(activity, Activity_Festival.class));
        });

        iv_mole_interpretaion.setOnClickListener(v -> {
            startActivity(new Intent(activity, Activity_Mole_Interpretation.class));
        });

        iv_love_compability.setOnClickListener(v -> {
            startActivity(new Intent(activity, Activtiy_MatchMaking_And_Love.class).putExtra("which_page", v.getId()));
        });


    }


    @Override
    public void onBackPressed() {
        if (advnce_drawer.isDrawerOpen(GravityCompat.START)) {
            advnce_drawer.closeDrawer(GravityCompat.START);
        } else {
            Dialog dialog_view = new Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
            dialog_view.getWindow().setBackgroundDrawableResource(R.color.blacktrans_color);
            dialog_view.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog_view.setCancelable(true);
            dialog_view.setCanceledOnTouchOutside(true);
            dialog_view.setContentView(R.layout.dialog_exit_app);

            TextView tv_exit = dialog_view.findViewById(R.id.tv_exit);
            TextView tv_cancel = dialog_view.findViewById(R.id.tv_cancel);

            tv_exit.setOnClickListener(v -> {
                dialog_view.dismiss();
                new Handler().postDelayed(this::finishAffinity, 100);
            });

            tv_cancel.setOnClickListener(v -> dialog_view.dismiss());

            dialog_view.show();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        advnce_drawer.closeDrawer(GravityCompat.START);

        if (item.getItemId() == R.id.nav_share) {

            String appPackageName = getPackageName();
            String appName = getApplicationInfo().loadLabel(getPackageManager()).toString();
            String appPlayStoreLink = "https://play.google.com/store/apps/details?id=" + appPackageName;
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out this app: " + appName);
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Download " + appName + " from the Play Store: " + appPlayStoreLink);
            startActivity(Intent.createChooser(shareIntent, "Share " + appName + " via"));

        } else if (item.getItemId() == R.id.nav_privacy_policy) {
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
        } else if (item.getItemId() == R.id.nav_rateus){
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName()));
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }


        return true;
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


}
