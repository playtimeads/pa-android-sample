package com.zodiacscope.zenith.astroverse.Model;

import android.os.Build;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

public class DepthPageTransformer implements ViewPager.PageTransformer {

    private int id;
    private int border = 10;
    private float speed = 0.2f;

    public DepthPageTransformer(int id) {
        this.id = id;
    }

    @Override
    public void transformPage(View view, float position) {
        View parallaxView = view.findViewById(id);

        if (parallaxView != null && Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
            if (position >= -1 && position <= 1) {
                float width = parallaxView.getWidth();
                parallaxView.setTranslationX(-(position * width * speed));

                float scale = ((float) view.getWidth() - border) / view.getWidth();

                view.setScaleX(scale);
                view.setScaleY(scale);
            }
        }
    }
}
