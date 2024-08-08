package pa.android.sample.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.playtimeads.PlaytimeAds;
import com.playtimeads.listeners.OfferWallInitListener;

import pa.android.sample.R;

public class MainActivity extends AppCompatActivity {
    private Button btnOpenOfferwall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        // Initialize SDK before opening it
        initPlaytimeSDK();

        btnOpenOfferwall = findViewById(R.id.btnOpenOfferwall);
        btnOpenOfferwall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check whether SDK is initialized. If initialized, open offerwall
                if (PlaytimeAds.getInstance().isInitialized()) {
                    PlaytimeAds.getInstance().open(MainActivity.this);
                } else {
                    Toast.makeText(MainActivity.this, "PlaytimeSDK is not initialized", Toast.LENGTH_SHORT).show();
                    initPlaytimeSDK();
                }
            }
        });
    }

    private void initPlaytimeSDK() {
        String userID = "1"; // Replace it with your logged-in user id
        String applicationKey = "a6408d9bb69dad30"; // Replace it with your application id
        PlaytimeAds.getInstance().destroy();
        PlaytimeAds.getInstance().init(MainActivity.this, applicationKey, userID, new OfferWallInitListener() {
            @Override
            public void onInitSuccess() {
                Log.e("PLAYTIME SDK", "PLAYTIME SDK onInitSuccess======");
            }

            @Override
            public void onAlreadyInitializing() {
                Log.e("PLAYTIME SDK", "PLAYTIME SDK onAlreadyInitializing======");
            }

            @Override
            public void onInitFailed(String error) {
                Log.e("PLAYTIME SDK", "PLAYTIME SDK onInitFailed======" + error);
            }
        });
    }
}