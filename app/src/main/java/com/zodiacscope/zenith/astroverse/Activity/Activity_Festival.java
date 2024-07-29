package com.zodiacscope.zenith.astroverse.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.zodiacscope.zenith.astroverse.Fragment.Festival_Fragment;
import com.zodiacscope.zenith.astroverse.Model.CubeInRotation_Transformation;
import com.zodiacscope.zenith.astroverse.R;

import java.util.Calendar;

public class Activity_Festival extends AppCompatActivity  {

    private ImageView iv_back;
    private TextView tv_header;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festival);

        initView();

        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        viewPager.setCurrentItem(currentMonth, false);

        viewPager.setPageTransformer(new CubeInRotation_Transformation());
    }

    private void initView() {
        iv_back = findViewById(R.id.iv_back);
        tv_header = findViewById(R.id.tv_header);
        tv_header.setSelected(true);
        tv_header.setText(R.string.festival);

        iv_back.setOnClickListener(v -> onBackPressed());

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        progressBar = findViewById(R.id.progress_bar);

        viewPager.setAdapter(new MonthPagerAdapter(this));

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            tab.setText(getMonthName(position));
        }).attach();
    }

    private String getMonthName(int position) {
        String[] months = getResources().getStringArray(R.array.months_array);
        return months[position];
    }

    private class MonthPagerAdapter extends FragmentStateAdapter {

        private static final int NUM_PAGES = 12;

        public MonthPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            String[] months = getResources().getStringArray(R.array.months_array);
            return Festival_Fragment.newInstance(months[position], new Festival_Fragment.ProgressCallback() {
                @Override
                public void showProgress() {
                    progressBar.setVisibility(View.VISIBLE);
                }

                @Override
                public void hideProgress() {
                    progressBar.setVisibility(View.GONE);
                }
            });
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }


}
