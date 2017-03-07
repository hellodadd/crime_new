package com.android.newcrime.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.newcrime.databases.CrimeItem;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by zwb on 2017/3/5.
 */

public class CommonConst {
    public static final String PREFERENCES_NAME = "case_new_item";
    public static final String KEY_CASE_NAME = "case_name";
    public static final String KEY_CASE_START_TIME = "case_start_time";
    public static final String KEY_CASE_END_TIME = "case_end_time";
    public static final String KEY_CASE_GPS_NAME = "case_gps_name";
    public static final String KEY_CASE_GPS_LAT = "case_gps_lat";
    public static final String KEY_CASE_GPS_LON = "case_gps_lon";
    public static final String KEY_CASE_LOCATION_1 = "case_location_1";
    public static final String KEY_CASE_LOCATION_1_FILE = "case_location_1_file";
    public static final String KEY_CASE_LOCATION_2 = "case_location_2";
    public static final String KEY_CASE_LOCATION_2_FILE = "case_location_2_file";
    public static final String KEY_CASE_LOCATION_3 = "case_location_3";
    public static final String KEY_CASE_LOCATION_3_FILE = "case_location_3_file";
    public static final String KEY_CASE_LOCATION_4 = "case_location_4";
    public static final String KEY_CASE_LOCATION_4_FILE = "case_location_4_file";
    public static final String KEY_CASE_LOCATION_5 = "case_location_5";
    public static final String KEY_CASE_LOCATION_5_FILE = "case_location_5_file";

    public static final String KEY_CASE_COLLECTION_ING = "case_collection_ing";
    public static final String KEY_CASE_LOCATION_1_COLLECTION_ING = "case_location_1_ing";
    public static final String KEY_CASE_LOCATION_2_COLLECTION_ING = "case_location_2_ing";
    public static final String KEY_CASE_LOCATION_3_COLLECTION_ING = "case_location_3_ing";
    public static final String KEY_CASE_LOCATION_4_COLLECTION_ING = "case_location_4_ing";
    public static final String KEY_CASE_LOCATION_5_COLLECTION_ING = "case_location_5_ing";

    public static void setPreferences(Context context, String key, String value){
        SharedPreferences sp = context.getSharedPreferences(CommonConst.PREFERENCES_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void setPreferences(Context context, String key, long value){
        SharedPreferences sp = context.getSharedPreferences(CommonConst.PREFERENCES_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static void setPreferences(Context context, String key, boolean value){
        SharedPreferences sp = context.getSharedPreferences(CommonConst.PREFERENCES_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static long getPreferences(Context context, String key, long value){
        SharedPreferences sp = context.getSharedPreferences(CommonConst.PREFERENCES_NAME, MODE_PRIVATE);
        return sp.getLong(key, value);
    }

    public static boolean getPreferences(Context context, String key, boolean value){
        SharedPreferences sp = context.getSharedPreferences(CommonConst.PREFERENCES_NAME, MODE_PRIVATE);
        return sp.getBoolean(key, value);
    }

    public static String getPreferences(Context context, String key, String value){
        SharedPreferences sp = context.getSharedPreferences(CommonConst.PREFERENCES_NAME, MODE_PRIVATE);
        return sp.getString(key, value);
    }

    public static void initPreferences(Context context){
        SharedPreferences sp = context.getSharedPreferences(CommonConst.PREFERENCES_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        CrimeItem item = new CrimeItem();
        editor.putString(KEY_CASE_NAME, item.getCaseName());
        editor.putLong(KEY_CASE_START_TIME, item.getCaseStartTime());
        editor.putLong(KEY_CASE_END_TIME, item.getCaseEndTime());
        editor.putString(KEY_CASE_GPS_NAME, item.getGpsLocationName());
        editor.putString(KEY_CASE_GPS_LAT, item.getGpsLat());
        editor.putString(KEY_CASE_GPS_LON, item.getGpsLon());
        editor.putString(KEY_CASE_LOCATION_1, item.getLocation1Name());
        editor.putString(KEY_CASE_LOCATION_1_FILE, item.getLocation1FilePath());
        editor.putString(KEY_CASE_LOCATION_2, item.getLocation2Name());
        editor.putString(KEY_CASE_LOCATION_2_FILE, item.getLocation2FilePath());
        editor.putString(KEY_CASE_LOCATION_3, item.getLocation3Name());
        editor.putString(KEY_CASE_LOCATION_3_FILE, item.getLocation3FilePath());
        editor.putString(KEY_CASE_LOCATION_4, item.getLocation4Name());
        editor.putString(KEY_CASE_LOCATION_4_FILE, item.getLocation4FilePath());
        editor.putString(KEY_CASE_LOCATION_5, item.getLocation5Name());
        editor.putString(KEY_CASE_LOCATION_5_FILE, item.getLocation5FilePath());
        editor.putBoolean(KEY_CASE_COLLECTION_ING, false);
        editor.commit();
    }


}
