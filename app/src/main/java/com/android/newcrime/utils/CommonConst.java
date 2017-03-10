package com.android.newcrime.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;

import com.android.newcrime.databases.CrimeItem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

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

    public static final String KEY_CASE_SAVE_OK = "case_save_ok";

    public static final String ACTION_RECEIVE_RESULT = "com.kuaikan.send_result";

    public static final String CASE_INFO_XML = "CaseInfo.xml";
    public static final String CASE_INFO_ZIP = "CaseInfo.zip";

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

    public static boolean isFirstCreateCase(Context context){
        String case_name = getPreferences(context, KEY_CASE_NAME, "");
        if(case_name == null || case_name.isEmpty()){
            return true;
        }
        return false;
    }

    public static boolean isCaseSaveOk(Context context){
        return getPreferences(context, KEY_CASE_SAVE_OK, false);
    }

    public static void setCaseSaveOK(Context context, boolean ok){
        setPreferences(context, KEY_CASE_SAVE_OK, ok);
    }

    public static boolean isCaseInputOver(Context context){
        /*String case_name = getPreferences(context, KEY_CASE_NAME, "");
        if(case_name == null || case_name.isEmpty()){
            return false;
        }*/
        String gps_name =  getPreferences(context, KEY_CASE_GPS_NAME, "");
        if(gps_name == null || gps_name.isEmpty()){
            return false;
        }
        String gps_lat =  getPreferences(context, KEY_CASE_GPS_LAT, "");
        if(gps_lat == null || gps_lat.isEmpty()){
            return false;
        }
        String location1 =  getPreferences(context, KEY_CASE_LOCATION_1, "");
        if(location1 == null || location1.isEmpty()){
            return false;
        }
        String location1_file =  getPreferences(context, KEY_CASE_LOCATION_1_FILE, "");
        if(location1_file == null || location1_file.isEmpty()){
            return false;
        }
        String location2 =  getPreferences(context, KEY_CASE_LOCATION_2, "");
        if(location2 == null || location2.isEmpty()){
            return false;
        }
        String location2_file =  getPreferences(context, KEY_CASE_LOCATION_2_FILE, "");
        if(location2_file == null || location2_file.isEmpty()){
            return false;
        }
        String location3 =  getPreferences(context, KEY_CASE_LOCATION_3, "");
        if(location3 == null || location3.isEmpty()){
            return false;
        }
        String location3_file =  getPreferences(context, KEY_CASE_LOCATION_3_FILE, "");
        if(location3_file == null || location3_file.isEmpty()){
            return false;
        }
        String location4 =  getPreferences(context, KEY_CASE_LOCATION_4, "");
        if(location4 == null || location4.isEmpty()){
            return false;
        }
        String location4_file =  getPreferences(context, KEY_CASE_LOCATION_4_FILE, "");
        if(location4_file == null || location4_file.isEmpty()){
            return false;
        }
        String location5 =  getPreferences(context, KEY_CASE_LOCATION_5, "");
        if(location5 == null || location5.isEmpty()){
            return false;
        }
        String location5_file =  getPreferences(context, KEY_CASE_LOCATION_5_FILE, "");
        if(location5_file == null || location5_file.isEmpty()){
            return false;
        }

        return true;
    }

    public static String copyToInternalPath(Context context, String OldPath){
        String NewPath = "";
        File mediaStorageDir = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "Report");
        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                return NewPath;
            }
        }
        String[] filename = OldPath.split("/");
        NewPath = new File(mediaStorageDir.getPath() + File.separator + filename[filename.length-1]).toString();
        Log.d("Anita", "new path = "+NewPath);
        copyFile(OldPath, NewPath);
        deleteFiles(new File(OldPath));
        return NewPath;
    }

    /**
     * Copy file
     * @param oldPath String Original path
     * @param newPath String Copy path
     * @return boolean
     */
    public static void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) {
                InputStream inStream = new FileInputStream(oldPath);
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ( (byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread;
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void deleteFiles(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }

        if(file.isDirectory()){
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                file.delete();
                return;
            }

            for (int i = 0; i < childFiles.length; i++) {
                deleteFiles(childFiles[i]);
            }
            file.delete();
        }
    }


}
