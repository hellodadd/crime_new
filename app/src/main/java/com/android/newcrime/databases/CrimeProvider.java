package com.android.newcrime.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.UUID;

/**
 * Created by zwb on 2017/3/5.
 */

public class CrimeProvider {
    public static final String TABLE_NAME = "crime";

    public static final String KEY_ID = "_id";

    public static final String CASE_ID_COLUMN = "case_id";
    public static final String CASE_NAME_COLUMN = "case_name";
    public static final String CASE_START_TIME_COLUMN = "case_start_time";
    public static final String CASE_END_TIME_COLUMN = "case_end_time";
    public static final String CASE_GPS_NAME_COLUMN = "case_gps_name";
    public static final String CASE_GPS_LAT_COLUMN = "case_gps_lat";
    public static final String CASE_GPS_LON_COLUMN = "case_gps_lon";
    public static final String CASE_LOCATION_1_COLUMN = "case_location_1";
    public static final String CASE_LOCATION_1_FILE_COLUMN = "case_location_1_file";
    public static final String CASE_LOCATION_2_COLUMN = "case_location_2";
    public static final String CASE_LOCATION_2_FILE_COLUMN = "case_location_2_file";
    public static final String CASE_LOCATION_3_COLUMN = "case_location_3";
    public static final String CASE_LOCATION_3_FILE_COLUMN = "case_location_3_file";
    public static final String CASE_LOCATION_4_COLUMN = "case_location_4";
    public static final String CASE_LOCATION_4_FILE_COLUMN = "case_location_4_file";
    public static final String CASE_LOCATION_5_COLUMN = "case_location_5";
    public static final String CASE_LOCATION_5_FILE_COLUMN = "case_location_5_file";


    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CASE_ID_COLUMN + " TEXT NOT NULL, " +
                    CASE_NAME_COLUMN + " TEXT NOT NULL, " +
                    CASE_START_TIME_COLUMN + " TEXT NOT NULL, " +
                    CASE_END_TIME_COLUMN + " TEXT NOT NULL, " +
                    CASE_GPS_NAME_COLUMN + " TEXT NOT NULL, " +
                    CASE_GPS_LAT_COLUMN + " TEXT NOT NULL, " +
                    CASE_GPS_LON_COLUMN + " TEXT NOT NULL, " +
                    CASE_LOCATION_1_COLUMN + " TEXT NOT NULL, " +
                    CASE_LOCATION_1_FILE_COLUMN + " TEXT NOT NULL, " +
                    CASE_LOCATION_2_COLUMN + " TEXT NOT NULL, " +
                    CASE_LOCATION_2_FILE_COLUMN + " TEXT NOT NULL, " +
                    CASE_LOCATION_3_COLUMN + " TEXT NOT NULL, " +
                    CASE_LOCATION_3_FILE_COLUMN + " TEXT NOT NULL, " +
                    CASE_LOCATION_4_COLUMN + " TEXT NOT NULL, " +
                    CASE_LOCATION_4_FILE_COLUMN + " TEXT NOT NULL, " +
                    CASE_LOCATION_5_COLUMN + " TEXT NOT NULL, " +
                    CASE_LOCATION_5_FILE_COLUMN + " TEXT NOT NULL)";

    private SQLiteDatabase db;

    private Context mContext;

    public CrimeProvider(Context context){
        mContext = context;
        db = DatabasesHelper.getDatabase(context);
    }

    public void close(){
        db.close();
    }

    public CrimeItem insert(CrimeItem item) {
        ContentValues cv = new ContentValues();
        cv.put(CASE_ID_COLUMN, CrimeProvider.getUUID());
        cv.put(CASE_NAME_COLUMN, item.getCaseName());
        cv.put(CASE_START_TIME_COLUMN, item.getCaseStartTime());
        cv.put(CASE_END_TIME_COLUMN, item.getCaseEndTime());
        cv.put(CASE_GPS_NAME_COLUMN, item.getGpsLocationName());
        cv.put(CASE_GPS_LAT_COLUMN, item.getGpsLat());
        cv.put(CASE_GPS_LON_COLUMN, item.getGpsLon());
        cv.put(CASE_LOCATION_1_COLUMN, item.getLocation1Name());
        cv.put(CASE_LOCATION_1_FILE_COLUMN, item.getLocation1FilePath());
        cv.put(CASE_LOCATION_2_COLUMN,  item.getLocation2Name());
        cv.put(CASE_LOCATION_2_FILE_COLUMN, item.getLocation2FilePath());
        cv.put(CASE_LOCATION_3_COLUMN, item.getLocation3Name());
        cv.put(CASE_LOCATION_3_FILE_COLUMN, item.getLocation3FilePath());
        cv.put(CASE_LOCATION_4_COLUMN, item.getLocation4Name());
        cv.put(CASE_LOCATION_4_FILE_COLUMN, item.getLocation4FilePath());
        cv.put(CASE_LOCATION_5_COLUMN, item.getLocation5Name());
        cv.put(CASE_LOCATION_5_FILE_COLUMN, item.getLocation5FilePath());

        long id = db.insert(TABLE_NAME, null, cv);
        item.setId(id);

        return item;
    }

    public boolean update(CrimeItem item) {
        String where = KEY_ID + "=" + item.getId();

        ContentValues cv = new ContentValues();

        cv.put(CASE_ID_COLUMN, item.getCaseId());
        cv.put(CASE_NAME_COLUMN, item.getCaseName());
        cv.put(CASE_START_TIME_COLUMN, item.getCaseStartTime());
        cv.put(CASE_END_TIME_COLUMN, item.getCaseEndTime());
        cv.put(CASE_GPS_NAME_COLUMN, item.getGpsLocationName());
        cv.put(CASE_GPS_LAT_COLUMN, item.getGpsLat());
        cv.put(CASE_GPS_LON_COLUMN, item.getGpsLon());
        cv.put(CASE_LOCATION_1_COLUMN, item.getLocation1Name());
        cv.put(CASE_LOCATION_1_FILE_COLUMN, item.getLocation1FilePath());
        cv.put(CASE_LOCATION_2_COLUMN,  item.getLocation2Name());
        cv.put(CASE_LOCATION_2_FILE_COLUMN, item.getLocation2FilePath());
        cv.put(CASE_LOCATION_3_COLUMN, item.getLocation3Name());
        cv.put(CASE_LOCATION_3_FILE_COLUMN, item.getLocation3FilePath());
        cv.put(CASE_LOCATION_4_COLUMN, item.getLocation4Name());
        cv.put(CASE_LOCATION_4_FILE_COLUMN, item.getLocation4FilePath());
        cv.put(CASE_LOCATION_5_COLUMN, item.getLocation5Name());
        cv.put(CASE_LOCATION_5_FILE_COLUMN, item.getLocation5FilePath());

        return db.update(TABLE_NAME, cv, where, null) > 0;
    }

    public boolean delete(long id) {
        String where = KEY_ID + "=" + id;
        return db.delete(TABLE_NAME, where , null) > 0;
    }

    public static String getUUID(){
        String s = UUID.randomUUID().toString();
        //去掉“-”符号
        String replace = s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
        return replace;
    }
}
