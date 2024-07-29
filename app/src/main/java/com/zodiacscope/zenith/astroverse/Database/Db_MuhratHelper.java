package com.zodiacscope.zenith.astroverse.Database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class Db_MuhratHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "muhrat_db";
    String DB_PATH;
    private final Context myContext;
    private SQLiteDatabase myDataBase;

    @Override 
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public Db_MuhratHelper(Context context) {
        super(context, DB_NAME, (SQLiteDatabase.CursorFactory) null, 1);
        this.DB_PATH = null;
        this.myContext = context;
        this.DB_PATH = "/data/data/com.zodiacscope.zenith.astroverse/databases/" + DB_NAME;
    }

    public void createDataBase() throws IOException {
        if (checkDataBase()) {
            return;
        }
        getReadableDatabase();
        try {
            copyDataBase();
        } catch (IOException unused) {
            throw new Error("Error copying database");
        }
    }

    private boolean checkDataBase() {
        try {
            return new File(this.DB_PATH + DB_NAME).exists();
        } catch (SQLiteException unused) {
            return false;
        }
    }

    private void copyDataBase() throws IOException {
        InputStream open = this.myContext.getAssets().open(DB_NAME);
        FileOutputStream fileOutputStream = new FileOutputStream(this.DB_PATH + DB_NAME);
        byte[] bArr = new byte[1024];
        while (true) {
            int read = open.read(bArr);
            if (read > 0) {
                fileOutputStream.write(bArr, 0, read);
            } else {
                fileOutputStream.flush();
                fileOutputStream.close();
                open.close();
                return;
            }
        }
    }

    public SQLiteDatabase openDataBase() throws SQLException {
        this.myDataBase = SQLiteDatabase.openDatabase(this.DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READONLY);
        return this.myDataBase;
    }

    @Override
    public synchronized void close() {
        if (this.myDataBase != null) {
            this.myDataBase.close();
        }
        super.close();
    }

}