package com.zodiacscope.zenith.astroverse.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zodiacscope.zenith.astroverse.Interface.Festival_Api_Interface;
import com.zodiacscope.zenith.astroverse.Model.Festival_Response;
import com.zodiacscope.zenith.astroverse.R;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Festival_Fragment extends Fragment {

    private static final String ARG_MONTH = "month";
    private TextView resultTextView;
    private Festival_Api_Interface festivalApiInterface;
    private ProgressCallback progressCallback;

    public interface ProgressCallback {
        void showProgress();
        void hideProgress();
    }

    public Festival_Fragment() {

    }

    public static Festival_Fragment newInstance(String month, ProgressCallback callback) {
        Festival_Fragment fragment = new Festival_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_MONTH, month);
        fragment.setArguments(args);
        fragment.setProgressCallback(callback);
        return fragment;
    }

    private void setProgressCallback(ProgressCallback callback) {
        this.progressCallback = callback;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_festival, container, false);
        resultTextView = view.findViewById(R.id.tv_festival_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://eazyearning.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();

        festivalApiInterface = retrofit.create(Festival_Api_Interface.class);

        if (getArguments() != null) {
            String month = getArguments().getString(ARG_MONTH);
            fetchFestivalDetails(month);
        }

        return view;
    }

    private void fetchFestivalDetails(String month) {
        if (progressCallback != null) {
            progressCallback.showProgress();
        }

        String details = "{\"month\":\"" + month + "\"}";

        Call<Festival_Response> call = festivalApiInterface.getFestivalDetails(details);
        call.enqueue(new Callback<Festival_Response>() {
            @Override
            public void onResponse(Call<Festival_Response> call, Response<Festival_Response> response) {
                if (progressCallback != null) {
                    progressCallback.hideProgress();
                }

                if (response.isSuccessful() && response.body() != null) {
                    List<Festival_Response.IndiaFestival> festivals = response.body().getIndiaFastival();
                    if (festivals == null || festivals.isEmpty()) {
                        showError("No festival details found for the selected month.");
                    } else {
                        displayFestivalDetails(festivals);
                    }
                } else {
                    showError("No festival details found for the selected month.");
                }
            }

            @Override
            public void onFailure(Call<Festival_Response> call, Throwable t) {
                if (progressCallback != null) {
                    progressCallback.hideProgress();
                }
                showError("Failed to fetch details. Please try again later.");
            }
        });
    }

    private void displayFestivalDetails(List<Festival_Response.IndiaFestival> festivals) {
        StringBuilder result = new StringBuilder();
        for (Festival_Response.IndiaFestival festival : festivals) {
            result.append("Festival : ").append(festival.getName()).append("\n")
                    .append("Date : ").append(festival.getDate()).append("\n")
                    .append("Weekday : ").append(festival.getWeekday()).append("\n")
                    .append("Notes : ").append(festival.getNotes()).append("\n\n");
        }
        resultTextView.setText(result.toString());
    }

    private void showError(String message) {
        resultTextView.setText("");
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
