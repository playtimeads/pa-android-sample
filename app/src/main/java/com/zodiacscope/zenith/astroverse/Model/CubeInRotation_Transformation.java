package com.zodiacscope.zenith.astroverse.Model;

import android.view.View;

import androidx.viewpager2.widget.ViewPager2;

public class CubeInRotation_Transformation implements  ViewPager2.PageTransformer {
    @Override
    public void transformPage(View page, float position) {

        page.setCameraDistance(30000);


        if (position < -1){
            page.setAlpha(0);

        }
        else if (position <= 0){
            page.setAlpha(1);
            page.setPivotX(page.getWidth());
            page.setRotationY(90*Math.abs(position));

        }
        else if (position <= 1){
            page.setAlpha(1);
            page.setPivotX(0);
            page.setRotationY(-90*Math.abs(position));

        }
        else{
            page.setAlpha(0);

        }


    }
}