package com.android.newcrime.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;
import android.util.Xml;

import com.android.newcrime.XmlHandler;
import com.android.newcrime.utils.DateTimePicker;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by zwb on 2017/3/5.
 */

public class CrimeProvider {
    public static final String TAG = "CrimeProvider";
    public static final String TABLE_NAME = "crime";

    public static final String KEY_ID = "_id";

    public static final String CASE_ID_COLUMN = "case_id";
    public static final String CASE_NAME_COLUMN = "case_name";
    public static final String CASE_CREATE_TIME_COLUMN = "case_create_time";
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
    public static final String CASE_STATAUS_COLUMN = "case_status";


    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CASE_ID_COLUMN + " TEXT NOT NULL, " +
                    CASE_NAME_COLUMN + " TEXT NOT NULL, " +
                    CASE_CREATE_TIME_COLUMN + " TEXT NOT NULL, " +
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
                    CASE_LOCATION_5_FILE_COLUMN + " TEXT NOT NULL, " +
                    CASE_STATAUS_COLUMN + " TEXT NOT NULL)";


    private SQLiteDatabase db;

    private Context mContext;

    public CrimeProvider(Context context) {
        mContext = context;
        db = DatabasesHelper.getDatabase(context);
    }

    public void close() {
        db.close();
    }

    public CrimeItem insert(CrimeItem item) {
        ContentValues cv = new ContentValues();
        cv.put(CASE_ID_COLUMN, CrimeProvider.getUUID());
        cv.put(CASE_NAME_COLUMN, item.getCaseName());
        cv.put(CASE_CREATE_TIME_COLUMN, item.getCreateTime());
        cv.put(CASE_START_TIME_COLUMN, item.getCaseStartTime());
        cv.put(CASE_END_TIME_COLUMN, item.getCaseEndTime());
        cv.put(CASE_GPS_NAME_COLUMN, item.getGpsLocationName());
        cv.put(CASE_GPS_LAT_COLUMN, item.getGpsLat());
        cv.put(CASE_GPS_LON_COLUMN, item.getGpsLon());
        cv.put(CASE_LOCATION_1_COLUMN, item.getLocation1Name());
        cv.put(CASE_LOCATION_1_FILE_COLUMN, item.getLocation1FilePath());
        cv.put(CASE_LOCATION_2_COLUMN, item.getLocation2Name());
        cv.put(CASE_LOCATION_2_FILE_COLUMN, item.getLocation2FilePath());
        cv.put(CASE_LOCATION_3_COLUMN, item.getLocation3Name());
        cv.put(CASE_LOCATION_3_FILE_COLUMN, item.getLocation3FilePath());
        cv.put(CASE_LOCATION_4_COLUMN, item.getLocation4Name());
        cv.put(CASE_LOCATION_4_FILE_COLUMN, item.getLocation4FilePath());
        cv.put(CASE_LOCATION_5_COLUMN, item.getLocation5Name());
        cv.put(CASE_LOCATION_5_FILE_COLUMN, item.getLocation5FilePath());
        cv.put(CASE_STATAUS_COLUMN, item.getCaseStatus());

        long id = db.insert(TABLE_NAME, null, cv);
        item.setId(id);

        return item;
    }

    public boolean update(CrimeItem item) {
        String where = KEY_ID + "=" + item.getId();

        ContentValues cv = new ContentValues();
        cv.put(CASE_ID_COLUMN, item.getCaseId());
        cv.put(CASE_NAME_COLUMN, item.getCaseName());
        cv.put(CASE_CREATE_TIME_COLUMN, item.getCreateTime());
        cv.put(CASE_START_TIME_COLUMN, item.getCaseStartTime());
        cv.put(CASE_END_TIME_COLUMN, item.getCaseEndTime());
        cv.put(CASE_GPS_NAME_COLUMN, item.getGpsLocationName());
        cv.put(CASE_GPS_LAT_COLUMN, item.getGpsLat());
        cv.put(CASE_GPS_LON_COLUMN, item.getGpsLon());
        cv.put(CASE_LOCATION_1_COLUMN, item.getLocation1Name());
        cv.put(CASE_LOCATION_1_FILE_COLUMN, item.getLocation1FilePath());
        cv.put(CASE_LOCATION_2_COLUMN, item.getLocation2Name());
        cv.put(CASE_LOCATION_2_FILE_COLUMN, item.getLocation2FilePath());
        cv.put(CASE_LOCATION_3_COLUMN, item.getLocation3Name());
        cv.put(CASE_LOCATION_3_FILE_COLUMN, item.getLocation3FilePath());
        cv.put(CASE_LOCATION_4_COLUMN, item.getLocation4Name());
        cv.put(CASE_LOCATION_4_FILE_COLUMN, item.getLocation4FilePath());
        cv.put(CASE_LOCATION_5_COLUMN, item.getLocation5Name());
        cv.put(CASE_LOCATION_5_FILE_COLUMN, item.getLocation5FilePath());
        cv.put(CASE_STATAUS_COLUMN, item.getCaseStatus());

        boolean result = db.update(TABLE_NAME, cv, where, null) > 0;
        return result;
    }

    public boolean delete(long id) {
        String where = KEY_ID + "=" + id;
        return db.delete(TABLE_NAME, where, null) > 0;
    }

    public List<CrimeItem> getAll() {
        List<CrimeItem> result = new ArrayList<>();
        String where = KEY_ID + ">=" + 0;
        Cursor cursor = db.query(
                TABLE_NAME, null, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            CrimeItem item = new CrimeItem();
            item.setId(cursor.getInt(0));
            item.setCaseId(cursor.getString(cursor.getColumnIndex(CASE_ID_COLUMN)));
            item.setCaseName(cursor.getString(cursor.getColumnIndex(CASE_NAME_COLUMN)));
            item.setCreateTime(cursor.getLong(cursor.getColumnIndex(CASE_CREATE_TIME_COLUMN)));
            item.setCaseStartTime(cursor.getLong(cursor.getColumnIndex(CASE_START_TIME_COLUMN)));
            item.setCaseEndTime(cursor.getLong(cursor.getColumnIndex(CASE_END_TIME_COLUMN)));
            item.setGpsLocationName(cursor.getString(cursor.getColumnIndex(CASE_GPS_NAME_COLUMN)));
            item.setGpsLat(cursor.getString(cursor.getColumnIndex(CASE_GPS_LAT_COLUMN)));
            item.setGpsLon(cursor.getString(cursor.getColumnIndex(CASE_GPS_LON_COLUMN)));
            item.setLocation1Name(cursor.getString(cursor.getColumnIndex(CASE_LOCATION_1_COLUMN)));
            item.setLocation1FilePath(cursor.getString(cursor.getColumnIndex(CASE_LOCATION_1_FILE_COLUMN)));
            item.setLocation2Name(cursor.getString(cursor.getColumnIndex(CASE_LOCATION_2_COLUMN)));
            item.setLocation2FilePath(cursor.getString(cursor.getColumnIndex(CASE_LOCATION_2_FILE_COLUMN)));
            item.setLocation3Name(cursor.getString(cursor.getColumnIndex(CASE_LOCATION_3_COLUMN)));
            item.setLocation3FilePath(cursor.getString(cursor.getColumnIndex(CASE_LOCATION_3_FILE_COLUMN)));
            item.setLocation4Name(cursor.getString(cursor.getColumnIndex(CASE_LOCATION_4_COLUMN)));
            item.setLocation4FilePath(cursor.getString(cursor.getColumnIndex(CASE_LOCATION_4_FILE_COLUMN)));
            item.setLocation5Name(cursor.getString(cursor.getColumnIndex(CASE_LOCATION_5_COLUMN)));
            item.setLocation5FilePath(cursor.getString(cursor.getColumnIndex(CASE_LOCATION_5_FILE_COLUMN)));
            item.setCaseStatus(cursor.getString(cursor.getColumnIndex(CASE_STATAUS_COLUMN)));
            //String caseId = cursor.getString(cursor.getColumnIndex("case_id"));
            //Log.e("zwb", "zwb ------ caseid = " + caseId);
            result.add(item);
        }

        cursor.close();
        return result;
    }

    public CrimeItem getItem(String id){
        CrimeItem item = new CrimeItem();
        String where = CASE_ID_COLUMN + " = '" + id + "'";
        Cursor cursor = db.query(
                TABLE_NAME, null, where, null, null, null, null, null);
        if(cursor.moveToFirst()){
            //String name = cursor.getString(2);
            //Log.e("zwb", "zwb ----- name = " + name);
            item.setId(cursor.getInt(0));
            item.setCaseId(id);
            item.setCaseName(cursor.getString(2));
            item.setCreateTime(cursor.getLong(3));
            item.setCaseStartTime(cursor.getLong(4));
            item.setCaseEndTime(cursor.getLong(5));
            item.setGpsLocationName(cursor.getString(6));
            item.setGpsLat(cursor.getString(7));
            item.setGpsLon(cursor.getString(8));
            item.setLocation1Name(cursor.getString(9));
            item.setLocation1FilePath(cursor.getString(10));
            item.setLocation2Name(cursor.getString(11));
            item.setLocation2FilePath(cursor.getString(12));
            item.setLocation3Name(cursor.getString(13));
            item.setLocation3FilePath(cursor.getString(14));
            item.setLocation4Name(cursor.getString(15));
            item.setLocation4FilePath(cursor.getString(16));
            item.setLocation5Name(cursor.getString(17));
            item.setLocation5FilePath(cursor.getString(18));
            item.setCaseStatus(cursor.getString(19));
        }

        //cursor.close();
        return item;
    }

    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        //去掉“-”符号
        String replace = s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
        return replace;
    }

    private String getFileName(String path){
        String[] filename = path.split("/");
        String name = filename[filename.length - 1];
        return name;
    }

    public void createScenesInfoXml() {
        List<CrimeItem> mItem = new ArrayList<>();
        List<HashMap<String, String>> mBaseInfoList = new ArrayList<>();
        mItem = getAll();

        Log.d("zwb", "createScenesInfoXml size = " + mItem.size());
        if (mItem.size() > 0) {
            for (int i = 0; i < mItem.size(); i++) {
                CrimeItem item = mItem.get(i);

                //scene
                HashMap<String, String> mBaseInfo = new LinkedHashMap<String, String>();
                mBaseInfo.put("id", item.getCaseId());
                mBaseInfo.put("casename", item.getCaseName());
                mBaseInfo.put("createtime", DateTimePicker.getCurrentDashTime(item.getCreateTime()));
                mBaseInfo.put("casestarttime", DateTimePicker.getCurrentDashTime(item.getCaseStartTime()));
                mBaseInfo.put("caseendtime", DateTimePicker.getCurrentDashTime(item.getCaseEndTime()));
                mBaseInfo.put("gpslocation", item.getGpsLocationName());
                mBaseInfo.put("gpslat", item.getGpsLat());
                mBaseInfo.put("gpslon", item.getGpsLon());
                mBaseInfo.put("location1", item.getLocation1Name());
                String path1 = item.getLocation1FilePath();
                if(path1 != null && !path1.isEmpty()){
                    mBaseInfo.put("location1_file", getFileName(path1));
                }else {
                    mBaseInfo.put("location1_file", item.getLocation1FilePath());
                }
                mBaseInfo.put("location2", item.getLocation2Name());
                String path2 = item.getLocation2FilePath();
                if(path2 != null && !path2.isEmpty()){
                    mBaseInfo.put("location2_file", getFileName(path2));
                }else {
                    mBaseInfo.put("location2_file", item.getLocation2FilePath());
                }
                mBaseInfo.put("location3", item.getLocation3Name());
                String path3 = item.getLocation3FilePath();
                if(path3 != null && !path3.isEmpty()){
                    mBaseInfo.put("location3_file", getFileName(path3));
                }else {
                    mBaseInfo.put("location3_file", item.getLocation3FilePath());
                }
                mBaseInfo.put("location4", item.getLocation4Name());
                String path4 = item.getLocation4FilePath();
                if(path4 != null && !path4.isEmpty()){
                    mBaseInfo.put("location4_file", getFileName(path4));
                }else {
                    mBaseInfo.put("location4_file", item.getLocation4FilePath());
                }
                mBaseInfo.put("location5", item.getLocation5Name());
                String path5 = item.getLocation5FilePath();
                if(path5 != null && !path5.isEmpty()){
                    mBaseInfo.put("location5_file", getFileName(path5));
                }else {
                    mBaseInfo.put("location5_file", item.getLocation5FilePath());
                }
                mBaseInfo.put("status",item.getCaseStatus());
                mBaseInfoList.add(mBaseInfo);
            }
        }

        final Object[] obj = new Object[1];
        obj[0] = mBaseInfoList;

        XmlHandler xmlhandler = new XmlHandler();
        xmlhandler.createCaseInfoXmlFile(obj);
    }

    public CrimeItem createBaseMsgXml(String id){
        List<CrimeItem> mItem = new ArrayList<>();
        List<HashMap<String, String>> mBaseInfoList = new ArrayList<>();

        CrimeItem record = getItem(id);
        if(record == null)return null;
        mItem.add(record);
        if (mItem.size() > 0) {
            for (int i = 0; i < mItem.size(); i++) {
                CrimeItem item = mItem.get(i);

                //scene
                HashMap<String, String> mBaseInfo = new LinkedHashMap<String, String>();
                mBaseInfo.put("id", item.getCaseId());
                mBaseInfo.put("casename", item.getCaseName());
                mBaseInfo.put("createtime", DateTimePicker.getCurrentDashTime(item.getCreateTime()));
                mBaseInfo.put("casestarttime", DateTimePicker.getCurrentDashTime(item.getCaseStartTime()));
                mBaseInfo.put("caseendtime", DateTimePicker.getCurrentDashTime(item.getCaseEndTime()));
                mBaseInfo.put("gpslocation", item.getGpsLocationName());
                mBaseInfo.put("gpslat", item.getGpsLat());
                mBaseInfo.put("gpslon", item.getGpsLon());
                mBaseInfo.put("location1", item.getLocation1Name());
                String path1 = item.getLocation1FilePath();
                if(path1 != null && !path1.isEmpty()){
                    mBaseInfo.put("location1_file", getFileName(path1));
                }else {
                    mBaseInfo.put("location1_file", item.getLocation1FilePath());
                }
                mBaseInfo.put("location2", item.getLocation2Name());
                String path2 = item.getLocation2FilePath();
                if(path2 != null && !path2.isEmpty()){
                    mBaseInfo.put("location2_file", getFileName(path2));
                }else {
                    mBaseInfo.put("location2_file", item.getLocation2FilePath());
                }
                mBaseInfo.put("location3", item.getLocation3Name());
                String path3 = item.getLocation3FilePath();
                if(path3 != null && !path3.isEmpty()){
                    mBaseInfo.put("location3_file", getFileName(path3));
                }else {
                    mBaseInfo.put("location3_file", item.getLocation3FilePath());
                }
                mBaseInfo.put("location4", item.getLocation4Name());
                String path4 = item.getLocation4FilePath();
                if(path4 != null && !path4.isEmpty()){
                    mBaseInfo.put("location4_file", getFileName(path4));
                }else {
                    mBaseInfo.put("location4_file", item.getLocation4FilePath());
                }
                mBaseInfo.put("location5", item.getLocation5Name());
                String path5 = item.getLocation5FilePath();
                if(path5 != null && !path5.isEmpty()){
                    mBaseInfo.put("location5_file", getFileName(path5));
                }else {
                    mBaseInfo.put("location5_file", item.getLocation5FilePath());
                }
                mBaseInfo.put("status",item.getCaseStatus());

                mBaseInfoList.add(mBaseInfo);
            }
        }

        final Object[] obj = new Object[1];
        obj[0] = mBaseInfoList;

        XmlHandler xmlhandler = new XmlHandler();
        xmlhandler.createCaseInfoXmlFile(obj);

        return record;
    }
}

