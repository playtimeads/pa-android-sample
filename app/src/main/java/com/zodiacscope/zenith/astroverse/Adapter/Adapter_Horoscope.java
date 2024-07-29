package com.zodiacscope.zenith.astroverse.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.zodiacscope.zenith.astroverse.R;
import java.util.List;

public class Adapter_Horoscope extends RecyclerView.Adapter<Adapter_Horoscope.ViewHolder> {

    private List<String> zodiacSigns;
    private List<Integer> zodiacImages;
    private OnItemClickListener listener;

    public Adapter_Horoscope(List<String> zodiacSigns, List<Integer> zodiacImages) {
        this.zodiacSigns = zodiacSigns;
        this.zodiacImages = zodiacImages;
    }

    public interface OnItemClickListener {
        void onItemClick(String zodiacSign);
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horoscope, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String zodiacSign = zodiacSigns.get(position);
        int zodiacImage = zodiacImages.get(position);

        holder.tvZodiacSign.setText(zodiacSign);
        holder.ivZodiacImage.setImageResource(zodiacImage);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(zodiacSign);
            }
        });
    }

    @Override
    public int getItemCount() {
        return zodiacSigns.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvZodiacSign;
        ImageView ivZodiacImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvZodiacSign = itemView.findViewById(R.id.tv_zodiac_sign);
            ivZodiacImage = itemView.findViewById(R.id.iv_zodiac_image);
        }
    }
}
