package com.zodiacscope.zenith.astroverse.Activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zodiacscope.zenith.astroverse.Adapter.Adapter_Muhrat;
import com.zodiacscope.zenith.astroverse.Model.Fetch_Muhrat_Data;
import com.zodiacscope.zenith.astroverse.Model.Muhrat_Session;
import com.zodiacscope.zenith.astroverse.Model.Muhurat_Model;
import com.zodiacscope.zenith.astroverse.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;


public class Activity_SubhMuhrat extends Activity implements View.OnClickListener {
    private static boolean is_closertimer;
    static Activity activity;
    private static Date time_left;
    private Adapter_Muhrat adapter_muhrat;
    private Button btn_day;
    private Button btn_night;
    private TextView tv_datedisplay;
    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(1, i);
            calendar.set(2, i2);
            calendar.set(5, i3);
            event_date = calendar.getTime();
            setCurrentMuhurat();
        }
    };
    private int in_day;
    private TextView tv_day_display;
    private Date event_date;
    private boolean is_reserve;
    private int View_type;
    private int month;
    private ImageView btn_pickdate;
    private int year;
    public ListView list_muhrat;
    private TextView tv_pos;
    private ImageView iv_back;
    private TextView tv_header;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_subh_muhrat);

        initviews();

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(1);
        month = calendar.get(2);
        in_day = calendar.get(5);

        btn_pickdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(0);
            }
        });

        setCurrentMuhurat();
        getApplicationContext();
        startTimer();
    }

    protected static void startTimer() {
        try {
            ValueAnimator valueAnimator = new ValueAnimator();
            valueAnimator.setObjectValues(0, 1000);
            valueAnimator.setDuration(1000L);
            valueAnimator.setRepeatCount(-1);
            valueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override

                public void onAnimationStart(Animator animator) {
                    Activity_SubhMuhrat.setTimeLeft();
                }

                @Override

                public void onAnimationRepeat(Animator animator) {
                    Activity_SubhMuhrat.setTimeLeft();
                }
            });
            valueAnimator.start();
        } catch (Exception unused) {
        }
    }

    public static void setTimeLeft() {
        Activity_SubhMuhrat activity_subhMuhrat = (Activity_SubhMuhrat) activity;
        try {
            Calendar calendar = Calendar.getInstance();
            Date date = new Date();
            calendar.setTime(date);
            long time = (time_left.getTime() - date.getTime()) % 86400000;
            long time1 = time % 3600000;
            long time2 = (time1 % 60000) / 1000;
            if (time2 < 0) {
                activity_subhMuhrat.event_date = new Date();
                activity_subhMuhrat.setCurrentMuhurat();
            }
        } catch (Exception unused) {
            unused.printStackTrace();
        }
    }

    private void initviews() {
        activity = this;
        is_closertimer = false;
        event_date = new Date();

        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_header = (TextView) findViewById(R.id.tv_header);
        tv_header.setSelected(true);
        tv_header.setText(R.string.today_muhrat);

        iv_back.setOnClickListener(v -> {
            onBackPressed();
        });

        tv_pos = (TextView) findViewById(R.id.tv_muhrat_pos);
        tv_datedisplay = (TextView) findViewById(R.id.tv_datedisplay);
        tv_day_display = (TextView) findViewById(R.id.tv_day);
        btn_pickdate = (ImageView) findViewById(R.id.iv_pickdate);
        btn_day = (Button) findViewById(R.id.btn_day);
        btn_night = (Button) findViewById(R.id.btn_night);
        list_muhrat = (ListView) findViewById(R.id.list_muhrat);


        btn_day.setOnClickListener(this);
        btn_night.setOnClickListener(this);
    }

    @Override
    protected Dialog onCreateDialog(int i) {
        Muhrat_Session.incrementInteractionCount();
        if (i != 0) {
            return null;
        }
        return new DatePickerDialog(this, R.style.CustomDatePickerDialog, dateSetListener, year, month, in_day);
    }

    public void setCurrentMuhurat() {
        try {
            is_reserve = false;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            int hours = event_date.getHours();
            if (hours >= 6 && hours < 18) {
                Log.e("setCurrentMuhurat1", "hour: " + hours);
                day_choghadiya();
            } else if (hours >= 18 && hours < 24) {
                Log.e("setCurrentMuhurat2", "hour: " + hours);
                night_choghadiya();
            } else {
                Log.e("setCurrentMuhurat3", "hour: " + hours);
                is_reserve = true;
                event_date = new Date(event_date.getTime() - 86400000);
                night_choghadiya();
            }
            tv_datedisplay.setText(simpleDateFormat.format(event_date));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        activity = this;
    }

    public void day_choghadiya() {
        try {
            String format = new SimpleDateFormat("EEEE", new Locale("en")).format(event_date);
            tv_day_display.setText(format);
            Fetch_Muhrat_Data fetchMuhratData = new Fetch_Muhrat_Data();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(event_date);
            calendar.set(11, 6);
            calendar.set(12, 0);
            calendar.set(13, 0);
            Log.e("day_choghadiya ", "cal: " + calendar.getTime() + " :" + calendar.get(10) + ":" + calendar.get(11));
            ArrayList<Muhurat_Model> choghadiyaGPS = fetchMuhratData.get_muhat("day_choghadiya", format, View_type, calendar.getTime(), 5400000L);
            int i2 = -1;
            Iterator<Muhurat_Model> it = choghadiyaGPS.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Muhurat_Model next = it.next();
                i2++;
                if (next.getEnd().after(event_date)) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
                    tv_pos.setText(next.getMuhurat() + " : " + simpleDateFormat.format(next.getStart()) + "- " + simpleDateFormat.format(next.getEnd()));
                    time_left = next.getEnd();
                    break;
                }
            }
            adapter_muhrat = new Adapter_Muhrat(this, choghadiyaGPS, i2);
            list_muhrat.setAdapter((ListAdapter) adapter_muhrat);
            adapter_muhrat.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void night_choghadiya() {
        Date date;
        try {
            String format = new SimpleDateFormat("EEEE", new Locale("en")).format(event_date);
            tv_day_display.setText(format);
            Fetch_Muhrat_Data fetchMuhratData = new Fetch_Muhrat_Data();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(event_date);
            calendar.set(11, 18);
            calendar.set(12, 0);
            calendar.set(13, 0);

            ArrayList<Muhurat_Model> choghadiyaGPS = fetchMuhratData.get_muhat("night_choghadiya", format, View_type, calendar.getTime(), 5400000L);
            int i2 = -1;
            Iterator<Muhurat_Model> it = choghadiyaGPS.iterator();
            while (true) {
                if (!it.hasNext()) {
                    i2 = 10;
                    break;
                }
                Muhurat_Model next = it.next();
                i2++;
                if (is_reserve) {
                    date = new Date(event_date.getTime() + 86400000);
                } else {
                    date = event_date;
                }
                Log.e("setTimeLeft " + is_reserve, "mTimeLeft : " + date + " after: " + next.getEnd());
                if (date.before(next.getEnd())) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
                    tv_pos.setText(next.getMuhurat() + " : " + simpleDateFormat.format(next.getStart()) + "-" + simpleDateFormat.format(next.getEnd()));
                    time_left = next.getEnd();
                    break;
                }
            }
            adapter_muhrat = new Adapter_Muhrat(this, choghadiyaGPS, i2);
            list_muhrat.setAdapter((ListAdapter) adapter_muhrat);
            adapter_muhrat.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        Muhrat_Session.incrementInteractionCount();

        if (view.getId() == R.id.btn_day) {
            btn_day.setBackgroundResource(R.drawable.btn_select);
            btn_night.setBackgroundResource(R.drawable.btn_unselect);
            day_choghadiya();
        } else if (view.getId() == R.id.btn_night) {
            btn_night.setBackgroundResource(R.drawable.btn_select);
            btn_day.setBackgroundResource(R.drawable.btn_unselect);
            night_choghadiya();
        }

    }
}