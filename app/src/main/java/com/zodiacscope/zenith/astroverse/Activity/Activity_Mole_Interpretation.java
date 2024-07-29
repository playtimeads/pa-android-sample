package com.zodiacscope.zenith.astroverse.Activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.zodiacscope.zenith.astroverse.R;

import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class Activity_Mole_Interpretation extends AppCompatActivity {

    private ImageView iv_back;
    private TextView tv_header;
    private Spinner spinnerLocation;
    private TextView tvMoleInterpretation;
    private ImageView iv_nodata;
    private String[] faceLocations;
    private String[] bodyLocations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mole_interpretation);

        initViews();
        loadArrays();
        setupSpinner();
    }

    private void initViews() {
        iv_back = findViewById(R.id.iv_back);
        tv_header = findViewById(R.id.tv_header);
        tv_header.setSelected(true);
        tv_header.setText(R.string.mole_interpretaion);

        iv_nodata = findViewById(R.id.iv_nodata);
        tvMoleInterpretation = findViewById(R.id.tv_mole_interpretation);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        spinnerLocation = findViewById(R.id.sp_mole);

    }

    private void loadArrays() {
        faceLocations = getResources().getStringArray(R.array.face_locations);
        bodyLocations = getResources().getStringArray(R.array.body_locations);
    }

    private void setupSpinner() {
        String[] locations = mergeArrays(faceLocations, bodyLocations);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_spinner_custom, locations) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = view.findViewById(R.id.text_view_spinner_item);
                return view;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                parent.setPadding(0, 20, 0, 20);
                TextView textView = view.findViewById(R.id.text_view_spinner_dropdown_item);
                return view;
            }
        };
        adapter.setDropDownViewResource(R.layout.item_spinner_dropdown_custom);
        spinnerLocation.setGravity(Gravity.CENTER);
        spinnerLocation.setAdapter(adapter);

        spinnerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                iv_nodata.setVisibility(View.GONE);
                tvMoleInterpretation.setVisibility(View.VISIBLE);
                interpretMole();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                iv_nodata.setVisibility(View.VISIBLE);
                tvMoleInterpretation.setVisibility(View.GONE);
            }
        });
    }

    private void interpretMole() {
        String selectedLocation = spinnerLocation.getSelectedItem().toString();
        String interpretation = getRandomInterpretation(selectedLocation);
        tvMoleInterpretation.setText(interpretation);
    }

    private String getRandomInterpretation(String selectedLocation) {
        String[] parts = selectedLocation.split(" ");
        String locationType = parts[parts.length - 1];

        String[] relevantInterpretations = getRelevantInterpretations(locationType.toLowerCase());
        if (relevantInterpretations.length > 0) {
            Random random = new Random();
            int index = random.nextInt(relevantInterpretations.length);
            return relevantInterpretations[index].replace("{location}", selectedLocation);
        } else {
            return "No interpretation found for " + selectedLocation;
        }
    }

    private String[] getRelevantInterpretations(String locationType) {
        String[] relevantInterpretations = new String[0];
        switch (locationType.toLowerCase()) {
            case "forehead":
                relevantInterpretations = getResources().getStringArray(R.array.mole_interpretations_forehead);
                break;
            case "cheek":
                relevantInterpretations = getResources().getStringArray(R.array.mole_interpretations_cheek);
                break;
            case "nose":
                relevantInterpretations = getResources().getStringArray(R.array.mole_interpretations_nose);
                break;
            case "chin":
                relevantInterpretations = getResources().getStringArray(R.array.mole_interpretations_chin);
                break;
            case "eyebrow":
                relevantInterpretations = getResources().getStringArray(R.array.mole_interpretations_eyebrow);
                break;
            case "ear":
                relevantInterpretations = getResources().getStringArray(R.array.mole_interpretations_ear);
                break;
            case "lip":
                relevantInterpretations = getResources().getStringArray(R.array.mole_interpretations_lip);
                break;
            case "jawline":
                relevantInterpretations = getResources().getStringArray(R.array.mole_interpretations_jawline);
                break;
            case "arm":
                relevantInterpretations = getResources().getStringArray(R.array.mole_interpretations_arm);
                break;
            case "leg":
                relevantInterpretations = getResources().getStringArray(R.array.mole_interpretations_leg);
                break;
            case "back":
                relevantInterpretations = getResources().getStringArray(R.array.mole_interpretations_back);
                break;
            case "chest":
                relevantInterpretations = getResources().getStringArray(R.array.mole_interpretations_chest);
                break;
            case "stomach":
                relevantInterpretations = getResources().getStringArray(R.array.mole_interpretations_stomach);
                break;
            case "shoulder":
                relevantInterpretations = getResources().getStringArray(R.array.mole_interpretations_shoulder);
                break;
            case "hip":
                relevantInterpretations = getResources().getStringArray(R.array.mole_interpretations_hip);
                break;
            case "hand":
                relevantInterpretations = getResources().getStringArray(R.array.mole_interpretations_hand);
                break;
        }
        return relevantInterpretations;
    }

    private String[] mergeArrays(String[] arr1, String[] arr2) {
        String[] merged = new String[arr1.length + arr2.length];
        System.arraycopy(arr1, 0, merged, 0, arr1.length);
        System.arraycopy(arr2, 0, merged, arr1.length, arr2.length);
        return merged;
    }
}
