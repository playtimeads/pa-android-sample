package com.zodiacscope.zenith.astroverse.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.zodiacscope.zenith.astroverse.Model.Rash_Info_Model;
import com.zodiacscope.zenith.astroverse.R;

import java.util.List;

public class Adapter_Rashi_Info extends PagerAdapter {

    private Context context;
    private List<Rash_Info_Model> rashInfoList;

    public Adapter_Rashi_Info(Context context, List<Rash_Info_Model> rashInfoList) {
        this.context = context;
        this.rashInfoList = rashInfoList;
    }

    @Override
    public int getCount() {
        return rashInfoList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rashi_fields, container, false);

        ImageView iv_rashi_image = view.findViewById(R.id.iv_rashi_image);
        TextView tv_rashi_engname = view.findViewById(R.id.tv_rashi_engname);
        TextView tv_rashi_engkey = view.findViewById(R.id.tv_rashi_engkey);
        TextView tv_rashi_gujname = view.findViewById(R.id.tv_rashi_gujname);
        TextView tv_rashi_gujkey = view.findViewById(R.id.tv_rashi_gujkey);
        TextView tv_rashi_hindiname = view.findViewById(R.id.tv_rashi_hindiname);
        TextView tv_rashi_hindikey = view.findViewById(R.id.tv_rashi_hindikey);
        TextView tv_ecliptic_longitude = view.findViewById(R.id.tv_ecliptic_longitude);

        tv_rashi_engname.setSelected(true);
        tv_rashi_engkey.setSelected(true);
        tv_rashi_gujname.setSelected(true);
        tv_rashi_gujkey.setSelected(true);
        tv_rashi_hindiname.setSelected(true);
        tv_rashi_hindikey.setSelected(true);
        tv_ecliptic_longitude.setSelected(true);

        Rash_Info_Model rashInfo = rashInfoList.get(position);

        iv_rashi_image.setImageResource(rashInfo.getImageResourceId());

        tv_rashi_engname.setText("English Pronunciation -" + " " + rashInfo.getNameEnglish());
        tv_rashi_gujname.setText("ગુજરાતી ઉચ્ચાર -" + " " + rashInfo.getNameGujarati());
        tv_rashi_hindiname.setText("हिन्दी उच्चारण -" + " " + rashInfo.getNameHindi());

        tv_rashi_engkey.setText("English Initials -" + " " + rashInfo.getSr_englishnamakhsar());
        tv_rashi_gujkey.setText("ગુજરાતી નામાક્ષર -" + " " + rashInfo.get_gujnamakshar());
        tv_rashi_hindikey.setText("हिंदी आद्याक्षर -" + " " + rashInfo.getSr_hindinamakshar());

        tv_ecliptic_longitude.setText("Ecliptic Longitude -" + " " + rashInfo.getLongitude());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
