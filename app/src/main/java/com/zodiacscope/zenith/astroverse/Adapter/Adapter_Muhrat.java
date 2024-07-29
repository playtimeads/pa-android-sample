package com.zodiacscope.zenith.astroverse.Adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zodiacscope.zenith.astroverse.Model.Muhurat_Model;
import com.zodiacscope.zenith.astroverse.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Adapter_Muhrat extends BaseAdapter {
    private static SimpleDateFormat dateFormat;
    private static int sel_pos;
    private ArrayList<Muhurat_Model> muhurat_models;
    private LayoutInflater inflater;

    @Override
    public long getItemId(int i) {
        return i;
    }

    public Adapter_Muhrat(Context context, ArrayList<Muhurat_Model> arrayList, int i) {
        muhurat_models = arrayList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        sel_pos = i;
        dateFormat = new SimpleDateFormat("hh:mm a");
    }

    @Override
    public int getCount() {
        return this.muhurat_models.size();
    }

    @Override
    public Object getItem(int i) {
        return this.muhurat_models.get(i);
    }

    @SuppressLint({"RestrictedApi", "ResourceAsColor"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            ViewHolder viewHolder2 = new ViewHolder();
            View inflate = this.inflater.inflate(R.layout.itme_adapter_muhrat, (ViewGroup) null);
            viewHolder2.muhurat = (TextView) inflate.findViewById(R.id.tv_muhratname);
            viewHolder2.startTime = (TextView) inflate.findViewById(R.id.tv_firsttime);
            viewHolder2.endTime = (TextView) inflate.findViewById(R.id.tv_endtime);
            inflate.setTag(viewHolder2);
            viewHolder = viewHolder2;
            view = inflate;
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (i == sel_pos) {
            view.setBackgroundResource(R.drawable.all_usebg);
        }
        viewHolder.setText(this.muhurat_models.get(i));
        return view;
    }


    public static class ViewHolder {
        public TextView endTime;
        public TextView muhurat;
        public TextView startTime;

        public void setText(Muhurat_Model muhuratModel) {
            this.muhurat.setText(muhuratModel.getMuhurat());
            this.startTime.setText(Adapter_Muhrat.dateFormat.format(muhuratModel.getStart()));
            this.endTime.setText(Adapter_Muhrat.dateFormat.format(muhuratModel.getEnd()));
        }
    }
}