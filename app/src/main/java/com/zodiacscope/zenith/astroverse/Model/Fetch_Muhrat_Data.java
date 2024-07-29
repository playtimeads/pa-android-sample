package com.zodiacscope.zenith.astroverse.Model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.zodiacscope.zenith.astroverse.Database.Db_MuhratHelper;

import java.util.ArrayList;
import java.util.Date;

public class Fetch_Muhrat_Data {
    private Db_MuhratHelper db_muhratHelper;

    public Fetch_Muhrat_Data() {
        this.db_muhratHelper = Zodiac_MyApp.getDbHelper();
        createDB();
    }

    private void createDB() {
        try {
            this.db_muhratHelper.createDataBase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Muhurat_Model> get_muhat(String table, String dayOfWeek, int type, Date startTime, long interval) {
        ArrayList<Muhurat_Model> muhuratModelList = new ArrayList<>();

        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = this.db_muhratHelper.openDataBase();
            String query = "SELECT * FROM " + table + " WHERE day LIKE '%" + dayOfWeek + "%'";
            cursor = db.rawQuery(query, null);

            int columnIndex = 0;
            if (type == 0) {
                columnIndex = cursor.getColumnIndex("muhurat");
            } else if (type == 1) {
                columnIndex = cursor.getColumnIndex("muhurathindi");
            }

            while (cursor != null && cursor.moveToNext()) {
                Muhurat_Model muhuratModel = new Muhurat_Model();
                muhuratModel.setMuhurat(cursor.getString(columnIndex));
                muhuratModel.setStart(startTime);
                Date endTime = new Date(startTime.getTime() + interval);
                muhuratModel.setEnd(endTime);
                muhuratModelList.add(muhuratModel);
                startTime = endTime;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return muhuratModelList;
    }


}
