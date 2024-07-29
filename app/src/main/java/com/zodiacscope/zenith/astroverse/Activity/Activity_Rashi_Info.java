package com.zodiacscope.zenith.astroverse.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.snackbar.Snackbar;
import com.zodiacscope.zenith.astroverse.Adapter.Adapter_Rashi_Info;
import com.zodiacscope.zenith.astroverse.Model.DepthPageTransformer;
import com.zodiacscope.zenith.astroverse.Model.Rash_Info_Model;
import com.zodiacscope.zenith.astroverse.R;

import java.util.ArrayList;
import java.util.List;

public class Activity_Rashi_Info extends AppCompatActivity {

    private Activity activity;
    private ImageView ivBack;
    private TextView tvHeader,tv_rashi_info;
    private ViewPager vpRashi;
    private Adapter_Rashi_Info adapterRashiInfo;
    private List<Rash_Info_Model> rashInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rashi_info);

        activity = Activity_Rashi_Info.this;

        initView();
        initData();
        initViewPager();
    }

    private void initView() {
        tvHeader = findViewById(R.id.tv_header);
        ivBack = findViewById(R.id.iv_back);
        tv_rashi_info =  findViewById(R.id.tv_rashi_info);
        tvHeader.setSelected(true);
        tv_rashi_info.setSelected(true);

        ivBack.setOnClickListener(v -> onBackPressed());
    }

    private void initData() {
        rashInfoList = new ArrayList<>();
        rashInfoList.add(new Rash_Info_Model("Aries (The Ram)", "મેષ", "मेष", R.drawable.ig_aries, "અ , લ , ઇ", "अ ,  च ,  चू ,  चे ,  ला ,  ली ,  लू ,  ले", "A, L, E, I, O", "0°"));
        rashInfoList.add(new Rash_Info_Model("Taurus (The Bull)", "વૃષભ", "वृषभ", R.drawable.ig_taurus, "બ , વ , ઉ", "उ ,  ए ,  ई ,  औ ,  द ,  दी ,  वो", " B, V, U, W", "30°"));
        rashInfoList.add(new Rash_Info_Model("Gemini (The Twins)", "મિથુન", "मिथुन", R.drawable.ig_gemini, "ક , છ , ઘ", "के ,  को ,  क ,  घ ,  छ ,  ह ,  ड", "K, CHH, GH, Q, C", "60°"));
        rashInfoList.add(new Rash_Info_Model("Cancer (The Crab)", "કર્ક", "कर्क", R.drawable.ig_cancer, "ડ , હ", "ह ,  हे ,  हो ,  डा ,  ही ,  डो", "Da, Ha", "90°"));
        rashInfoList.add(new Rash_Info_Model("Leo (The Lion)", "સિંહ", "सिंह", R.drawable.ig_leo, "મ , ટ", "म ,  मे ,  मी ,  टे ,  टा ,  टी ", "M, ta", "120°"));
        rashInfoList.add(new Rash_Info_Model("Virgo (The Maiden)", "કન્યા", "कन्या", R.drawable.ig_virgo, "પ , ઠ , ણ", "प ,  ष ,  ण ,  पे ,  पो ,  प", "P, Tha", "150°"));
        rashInfoList.add(new Rash_Info_Model("Libra (The Scales)", "તુલા", "तुला", R.drawable.ig_libra, "ર , ત", "रे ,  रो ,  रा ,  ता ,  ते ,  तू", "R, T", "180°"));
        rashInfoList.add(new Rash_Info_Model("Scorpio (The Scorpion)", "વૃશ્ચિક", "वृश्चिक", R.drawable.ig_scorpio, "ન , ય", "लो  ,  ने  ,  नी ,  नू ,  या ,  यी", "N, Y", "210°"));
        rashInfoList.add(new Rash_Info_Model("Sagittarius (Centaur The Archer)", "ધન", "धनु", R.drawable.ig_sagittarius, "ભ , ધ , ફ , ઢ", "धा ,  ये ,  यो ,  भी ,  भू ,  फा ,  ढा", "BH, F, DH", "240°"));
        rashInfoList.add(new Rash_Info_Model("Capricorn (\"Goat-horned\" The Sea-Goat)", "મકર", "मकर", R.drawable.ig_capricorn, "ખ , જ", "जा ,  जी ,  खो ,  खू ,  ग ,  गी ,  भो", "KH, J", "270°"));
        rashInfoList.add(new Rash_Info_Model("Aquarius (The Water Bearer)", "કુંભ", "कुंभ", R.drawable.ig_aquarius, "ગ , સ , શ , ષ", "गे ,  गो ,  सा ,  सू ,  से ,  सो ,  द", "G, S, Sh", "300°"));
        rashInfoList.add(new Rash_Info_Model("Pisces (The Fish)", "મીન", "मीन", R.drawable.ig_pisces, "દ , ચ , થ , ઝ", "दी ,  चा ,  ची ,  झ ,  दो ,  दू", "D, CH, Z, TH", "330°"));
    }

    private void initViewPager() {
        vpRashi = findViewById(R.id.vp_rashi);
        adapterRashiInfo = new Adapter_Rashi_Info(this, rashInfoList);
        vpRashi.setAdapter(adapterRashiInfo);
        vpRashi.setPageTransformer(false, new DepthPageTransformer(R.id.iv_rashi_image));
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
