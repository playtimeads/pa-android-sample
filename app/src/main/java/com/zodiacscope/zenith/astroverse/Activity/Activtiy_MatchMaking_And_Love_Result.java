package com.zodiacscope.zenith.astroverse.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zodiacscope.zenith.astroverse.R;

import java.util.Random;

public class Activtiy_MatchMaking_And_Love_Result extends AppCompatActivity {

    private static final String[] COMPATIBILITY_PHRASES = {
            "Overall, {name1} and {name2} have a good match as they both share the same Sun Sign of {zodiac}. This indicates emotional compatibility and understanding between the two. However, there may be some differences in their Moon and Ascendant signs which can bring balance and complement each other's strengths and weaknesses.",
            "The match between {name1} and {name2} looks promising due to their common Sun Sign of {zodiac}. This shows a deep emotional connection. Differences in their Moon and Ascendant signs can help in balancing their personalities.",
            "{name1} and {name2} share a Sun Sign of {zodiac}, which suggests they have a strong emotional bond. Their differences in Moon and Ascendant signs can complement each other well, adding depth to their relationship.",
            "With the Sun Sign of {zodiac}, {name1} and {name2} are likely to understand each other well emotionally. The variations in their Moon and Ascendant signs can bring a unique balance to their relationship.",
            "{name1} and {name2} have a shared Sun Sign of {zodiac}, indicating a harmonious and emotionally compatible relationship. Differences in Moon and Ascendant signs can provide a balance, enhancing their strengths and offsetting weaknesses.",
            "The Sun Sign {zodiac} for both {name1} and {name2} points to a good match in terms of emotional compatibility. The differences in their Moon and Ascendant signs can bring balance and complement each other well.",
            "{name1} and {name2} share the same Sun Sign of {zodiac}, which indicates a strong emotional connection. Their Moon and Ascendant signs, although different, can complement each other, fostering understanding and harmony in their relationship.",
            "The Sun Sign {zodiac} is shared by {name1} and {name2}, suggesting a deep emotional understanding between them. Their contrasting Moon and Ascendant signs may bring balance and enrich their relationship with diverse perspectives.",
            "{name1} and {name2} both have the Sun Sign {zodiac}, signifying a strong emotional bond. Their distinct Moon and Ascendant signs can create a complementary dynamic, contributing to a fulfilling relationship.",
            "With {name1} and {name2} sharing the Sun Sign {zodiac}, there's a foundation of emotional compatibility. Their individual Moon and Ascendant signs offer opportunities for growth and mutual understanding.",
            "{name1} and {name2} share the Sun Sign {zodiac}, indicating a natural resonance in their emotional connection. Their Moon and Ascendant signs, while different, can enrich their relationship with diverse qualities and perspectives."
    };
    private static final String[] LOVE_DESCRIPTIONS = {
            "{name1} and {name2} are a perfect match. Their love is like a symphony of emotions,\nresonating with harmony and passion. They navigate life’s ups and downs together,\nfinding strength in each other. Their relationship is built on a foundation of trust and mutual respect,\nwhich only grows stronger with time. Every moment they share is filled with joy and laughter,\nmaking even the mundane seem extraordinary. They understand each other deeply,\noften communicating without words, and their bond is unbreakable.\nTogether, they create a world of love that is as beautiful as it is enduring.",
            "{name1} and {name2} share a bond that is both deep and profound. Their love is an unbreakable connection\nthat grows stronger with each passing day. They are each other’s confidants, best friends, and lovers,\nfinding in each other a safe haven from the world’s chaos. Their relationship is marked by a deep sense\nof understanding and empathy, allowing them to support each other through thick and thin.\nThey celebrate each other’s successes and comfort each other in times of sorrow.\nTheir love story is a testament to the enduring power of true love,\nshowing that when two hearts are meant to be together, nothing can stand in their way.",
            "The love between {name1} and {name2} is a beautiful journey filled with moments of joy, laughter,\nand mutual respect. They approach life as partners, tackling challenges together and celebrating triumphs\nwith equal fervor. Their relationship is characterized by open communication and a willingness\nto grow together. They inspire each other to be the best versions of themselves,\nfinding strength and motivation in their shared love. Every day is an adventure,\nand they cherish the little things that make their bond unique.\nTheir love is a source of immense happiness and fulfillment,\nproviding a solid foundation for a lifetime of shared memories.",
            "{name1} and {name2} are destined to be together. Their love story is a testament to the power\nof true love and commitment. From the moment they met, they felt a connection that was impossible to ignore.\nOver time, this connection has deepened into a profound and unshakeable bond.\nThey understand each other’s dreams and fears, offering unwavering support and encouragement.\nTheir relationship is built on a foundation of trust, loyalty, and a deep emotional connection.\nThey face life’s challenges hand in hand, knowing that together, they can overcome anything.\nTheir love is a beautiful journey that continues to unfold with each passing day.",
            "The relationship between {name1} and {name2} is one of mutual understanding and support.\nThey bring out the best in each other and thrive together. Their love is characterized by a deep sense\nof empathy and compassion, allowing them to navigate life’s ups and downs with grace and resilience.\nThey share a vision for the future, working together to build a life filled with happiness and fulfillment.\nTheir bond is strengthened by shared experiences and a deep emotional connection.\nThey find joy in each other’s presence, and their love is a source of inspiration and motivation.\nTogether, they create a beautiful tapestry of love and life.",
            "The connection between {name1} and {name2} is undeniable. Their love is a beautiful dance of passion and harmony.\nThey move through life with a shared sense of purpose and joy, finding happiness in each other’s company.\nTheir relationship is built on a foundation of trust, respect, and a deep emotional connection.\nThey support each other’s dreams and aspirations, working together to create a future filled with love and happiness.\nTheir bond is strengthened by their ability to communicate openly and honestly,\nand they find joy in the little moments they share. Their love is a source of immense strength and resilience.",
            "{name1} and {name2} share a love that is both tender and strong. Their relationship is built on trust, respect,\nand a deep emotional connection. They support each other through life’s challenges,\nfinding strength in their shared love. Their bond is characterized by a deep sense of empathy and understanding,\nallowing them to navigate life’s ups and downs with grace and resilience.\nThey celebrate each other’s successes and comfort each other in times of sorrow.\nTheir love is a source of immense happiness and fulfillment, providing a solid foundation\nfor a lifetime of shared memories. Together, they create a world of love that is as beautiful as it is enduring.",
            "The love between {name1} and {name2} is like a beacon of light, guiding them through the challenges of life\nwith grace and resilience. They navigate life’s ups and downs together, finding strength in each other.\nTheir relationship is built on a foundation of trust and mutual respect, which only grows stronger with time.\nEvery moment they share is filled with joy and laughter, making even the mundane seem extraordinary.\nThey understand each other deeply, often communicating without words, and their bond is unbreakable.\nTogether, they create a world of love that is as beautiful as it is enduring.\nTheir love is a source of immense strength and resilience.",
            "{name1} and {name2} are truly meant for each other. Their love is a beautiful tapestry of shared dreams and aspirations.\nThey approach life as partners, tackling challenges together and celebrating triumphs with equal fervor.\nTheir relationship is characterized by open communication and a willingness to grow together.\nThey inspire each other to be the best versions of themselves, finding strength and motivation in their shared love.\nEvery day is an adventure, and they cherish the little things that make their bond unique.\nTheir love is a source of immense happiness and fulfillment, providing a solid foundation\nfor a lifetime of shared memories.",
            "The bond between {name1} and {name2} is unshakeable. Their love is a source of strength and inspiration for both of them.\nThey share a vision for the future, working together to build a life filled with happiness and fulfillment.\nTheir relationship is built on a foundation of trust and mutual respect, which only grows stronger with time.\nEvery moment they share is filled with joy and laughter, making even the mundane seem extraordinary.\nThey understand each other deeply, often communicating without words, and their bond is unbreakable.\nTogether, they create a world of love that is as beautiful as it is enduring."
    };


    private Activity activity;
    private ImageView iv_back;
    private TextView tv_header, tv_result;
    private ProgressBar progressBar;
    private String str_love_name, str_love_place, str_love_bd, str_love_bt, str_partner_name, str_partner_place, str_partner_bd, str_partner_bt;
    private String str_love_zodiac, str_love_moon, str_love_ascendant, str_partner_zodiac, str_partner_moon, str_partner_ascendant;
    private int viewtype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activtiy_match_making_and_love_result);

        activity = Activtiy_MatchMaking_And_Love_Result.this;

        initview();
        get_data();
    }

    private void initview() {
        iv_back = findViewById(R.id.iv_back);
        tv_header = findViewById(R.id.tv_header);
        tv_header.setSelected(true);
        tv_result = findViewById(R.id.tv_result);
        progressBar = findViewById(R.id.progress_bar);

        iv_back.setOnClickListener(v -> onBackPressed());
    }

    @SuppressLint("SetTextI18n")
    private void get_data() {
        progressBar.setVisibility(View.VISIBLE);
        tv_result.setVisibility(View.GONE);

        str_love_name = getIntent().getStringExtra("love_name");
        str_love_place = getIntent().getStringExtra("love_place");
        str_love_bd = getIntent().getStringExtra("love_bd");
        str_love_bt = getIntent().getStringExtra("love_bt");
        str_partner_name = getIntent().getStringExtra("partner_name");
        str_partner_place = getIntent().getStringExtra("partner_place");
        str_partner_bd = getIntent().getStringExtra("partner_bd");
        str_partner_bt = getIntent().getStringExtra("partner_bt");

        str_love_zodiac = getIntent().getStringExtra("zodiac_sign");
        str_partner_zodiac = getIntent().getStringExtra("partner_zodiac_sign");

        str_love_moon = getIntent().getStringExtra("love_moon");
        str_partner_moon = getIntent().getStringExtra("partner_moon");

        str_love_ascendant = getIntent().getStringExtra("love_ascendant");
        str_partner_ascendant = getIntent().getStringExtra("partner_ascendant");

        viewtype = getIntent().getIntExtra("view_type", 0);

        Random random = new Random();
        int random_number = random.nextInt(100) + 1;

        if (viewtype == R.id.btn_submit) {
            tv_header.setText("Love Compatibility Result");
            tv_result.setText("Your Name : " + str_love_name + "\n" + "Your Love Name :" + str_partner_name + "\n" + "Your Birth date : " + str_love_bd + "\n" + "Your Love Birth Date : " + str_partner_bd + "\n\n" + "According this details you and your partner love compatibility is : " + random_number + " %" + "\n" + generateRandom_LoveDesc(str_love_name, str_partner_name));
            progressBar.postDelayed(() -> {
                progressBar.setVisibility(View.GONE);
                tv_result.setVisibility(View.VISIBLE);
            }, 1000);
        } else {
            tv_header.setText("Match Making Result");
            tv_result.setText("Based on the information provided, I have checked the Kundli of both " + str_love_name + " and " + str_partner_name + " for match-making. Here are the details:\n\n" +
                    str_love_name + "'s Kundli:\n" +
                    "- Sun Sign: " + str_love_zodiac + "\n" +
                    "- Moon Sign: " + str_love_moon + "\n" +
                    "- Ascendant Sign: " + str_love_ascendant + "\n\n" +
                    str_partner_name + "'s Kundli:\n" +
                    "- Sun Sign: " + str_partner_zodiac + "\n" +
                    "- Moon Sign: " + str_partner_moon + "\n" +
                    "- Ascendant Sign: " + str_partner_ascendant + "\n\n" +
                    generateRandomCompatibilityText(str_love_name, str_partner_name, str_love_zodiac) + "\n\n" +
                    "In conclusion, " + str_love_name + " and " + str_partner_name + " have a good match based on their Kundli. It is recommended for them to communicate openly, understand each other's differences, and support each other to have a harmonious relationship.");

            progressBar.postDelayed(() -> {
                progressBar.setVisibility(View.GONE);
                tv_result.setVisibility(View.VISIBLE);
            }, 3000);

        }
    }

    private String generateRandom_LoveDesc(String name1, String name2) {
        String phrase = LOVE_DESCRIPTIONS[new Random().nextInt(LOVE_DESCRIPTIONS.length)];
        phrase = phrase.replace("{name1}", name1);
        phrase = phrase.replace("{name2}", name2);
        return phrase;
    }

    private String generateRandomCompatibilityText(String name1, String name2, String zodiac) {
        String phrase = COMPATIBILITY_PHRASES[new Random().nextInt(COMPATIBILITY_PHRASES.length)];
        phrase = phrase.replace("{name1}", name1);
        phrase = phrase.replace("{name2}", name2);
        phrase = phrase.replace("{zodiac}", zodiac);
        return phrase;
    }
}
