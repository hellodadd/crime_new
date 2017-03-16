package com.android.newcrime.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.android.newcrime.AppActivity;
import com.android.newcrime.CreateCrimeActivityP3;
import com.android.newcrime.R;
import com.android.newcrime.databases.CrimeItem;
import com.android.newcrime.databases.CrimeProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by zwb on 2017/3/5.
 */

public class CommonConst {
    public static final String PREFERENCES_NAME = "case_new_item";
    public static final String KEY_ID = "key_id";
    public static final String KEY_CASE_ID = "case_id";
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

    public static final String KEY_CASE_LOCATION_1_COLLECTION_DONE = "case_location_1_done";
    public static final String KEY_CASE_LOCATION_2_COLLECTION_DONE = "case_location_2_done";
    public static final String KEY_CASE_LOCATION_3_COLLECTION_DONE = "case_location_3_done";
    public static final String KEY_CASE_LOCATION_4_COLLECTION_DONE = "case_location_4_done";
    public static final String KEY_CASE_LOCATION_5_COLLECTION_DONE = "case_location_5_done";

    public static final String KEY_CASE_SAVE_OK = "case_save_ok";

    public static final String KEY_APP_FIRST_RUN = "app_first_run";

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
        editor.putLong(KEY_ID, item.getId());
        editor.putString(KEY_CASE_ID, item.getCaseId());
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
        editor.putBoolean(KEY_CASE_LOCATION_1_COLLECTION_ING, false);
        editor.putBoolean(KEY_CASE_LOCATION_2_COLLECTION_ING, false);
        editor.putBoolean(KEY_CASE_LOCATION_3_COLLECTION_ING, false);
        editor.putBoolean(KEY_CASE_LOCATION_4_COLLECTION_ING, false);
        editor.putBoolean(KEY_CASE_LOCATION_5_COLLECTION_ING, false);
        editor.putBoolean(KEY_CASE_LOCATION_1_COLLECTION_DONE, false);
        editor.putBoolean(KEY_CASE_LOCATION_2_COLLECTION_DONE, false);
        editor.putBoolean(KEY_CASE_LOCATION_3_COLLECTION_DONE, false);
        editor.putBoolean(KEY_CASE_LOCATION_4_COLLECTION_DONE, false);
        editor.putBoolean(KEY_CASE_LOCATION_5_COLLECTION_DONE, false);
        editor.putBoolean(KEY_CASE_SAVE_OK, false);
        editor.commit();
    }

    public static void initPreferences(Context context, CrimeItem item){
        SharedPreferences sp = context.getSharedPreferences(CommonConst.PREFERENCES_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        Log.e("zwb", "zwb ---- init keyid = " + item.getId());
        editor.putLong(KEY_ID, item.getId());
        editor.putString(KEY_CASE_ID, item.getCaseId());
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
        editor.putBoolean(KEY_CASE_LOCATION_1_COLLECTION_ING, false);
        editor.putBoolean(KEY_CASE_LOCATION_2_COLLECTION_ING, false);
        editor.putBoolean(KEY_CASE_LOCATION_3_COLLECTION_ING, false);
        editor.putBoolean(KEY_CASE_LOCATION_4_COLLECTION_ING, false);
        editor.putBoolean(KEY_CASE_LOCATION_5_COLLECTION_ING, false);
        String file1 = item.getLocation1FilePath();
        String file2 = item.getLocation2FilePath();
        String file3 = item.getLocation3FilePath();
        String file4 = item.getLocation4FilePath();
        String file5 = item.getLocation5FilePath();
        if(file1 == null || file1.isEmpty()){
            editor.putBoolean(KEY_CASE_LOCATION_1_COLLECTION_DONE, false);
        }else {
            editor.putBoolean(KEY_CASE_LOCATION_1_COLLECTION_DONE, true);
        }
        if(file2 == null || file2.isEmpty()){
            editor.putBoolean(KEY_CASE_LOCATION_2_COLLECTION_DONE, false);
        }else{
            editor.putBoolean(KEY_CASE_LOCATION_2_COLLECTION_DONE, true);
        }
        if(file3 == null || file3.isEmpty()){
            editor.putBoolean(KEY_CASE_LOCATION_3_COLLECTION_DONE, false);
        }else{
            editor.putBoolean(KEY_CASE_LOCATION_3_COLLECTION_DONE, true);
        }
        if(file4 == null || file4.isEmpty()){
            editor.putBoolean(KEY_CASE_LOCATION_4_COLLECTION_DONE, false);
        }else{
            editor.putBoolean(KEY_CASE_LOCATION_4_COLLECTION_DONE, true);
        }
        if(file5 == null || file4.isEmpty()){
            editor.putBoolean(KEY_CASE_LOCATION_5_COLLECTION_DONE, false);
        }else{
            editor.putBoolean(KEY_CASE_LOCATION_5_COLLECTION_DONE, true);
        }
        editor.putBoolean(KEY_CASE_SAVE_OK, false);
        editor.commit();
    }

    public static boolean isFirstCreateCase(Context context){
        String case_name = getPreferences(context, KEY_CASE_NAME, "");
        if(case_name == null || case_name.isEmpty()){
            return true;
        }
        return false;
    }

    public static boolean isAppFirstRun(Context context){
        return getPreferences(context, KEY_APP_FIRST_RUN, true);
    }

    public static void setAppFirstRun(Context context, boolean ok){
        setPreferences(context, KEY_APP_FIRST_RUN, ok);
    }

    public static boolean isCaseSaveOk(Context context){
        return getPreferences(context, KEY_CASE_SAVE_OK, false);
    }

    public static void setCaseSaveOK(Context context, boolean ok){
        setPreferences(context, KEY_CASE_SAVE_OK, ok);
    }

   public static boolean isCollectionDone(Context context, int location){
       if(location == 1){
           return CommonConst.getPreferences(context,
                   CommonConst.KEY_CASE_LOCATION_1_COLLECTION_DONE, false);
       }else if(location == 2){
           return CommonConst.getPreferences(context,
                   CommonConst.KEY_CASE_LOCATION_2_COLLECTION_DONE, false);
       }else if(location == 3){
           return CommonConst.getPreferences(context,
                   CommonConst.KEY_CASE_LOCATION_3_COLLECTION_DONE, false);
       }else if(location == 4){
           return CommonConst.getPreferences(context,
                   CommonConst.KEY_CASE_LOCATION_4_COLLECTION_DONE, false);
       }else if(location == 5){
           return CommonConst.getPreferences(context,
                   CommonConst.KEY_CASE_LOCATION_5_COLLECTION_DONE, false);
       }
       return false;
   }

    public static boolean isCollectionIng(Context context, int location){
        if(location == 1){
            return CommonConst.getPreferences(context,
                    CommonConst.KEY_CASE_LOCATION_1_COLLECTION_ING, false);
        }else if(location == 2){
            return CommonConst.getPreferences(context,
                    CommonConst.KEY_CASE_LOCATION_2_COLLECTION_ING, false);
        }else if(location == 3){
            return CommonConst.getPreferences(context,
                    CommonConst.KEY_CASE_LOCATION_3_COLLECTION_ING, false);
        }else if(location == 4){
            return CommonConst.getPreferences(context,
                    CommonConst.KEY_CASE_LOCATION_4_COLLECTION_ING, false);
        }else if(location == 5){
            return CommonConst.getPreferences(context,
                    CommonConst.KEY_CASE_LOCATION_5_COLLECTION_ING, false);
        }
        return false;
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


    public static void saveToDataBase(Context context){
        CrimeProvider provider = new CrimeProvider(context);
        CrimeItem item = new CrimeItem();
        item.setId(CommonConst.getPreferences(context,CommonConst.KEY_ID,0));
        item.setCaseId(CommonConst.getPreferences(context,CommonConst.KEY_CASE_ID,""));
        item.setCaseName(CommonConst.getPreferences(context,CommonConst.KEY_CASE_NAME, ""));
        item.setCaseStartTime(CommonConst.getPreferences(context,
                CommonConst.KEY_CASE_START_TIME, Calendar.getInstance().getTimeInMillis()));
        item.setCaseEndTime(CommonConst.getPreferences(context,
                CommonConst.KEY_CASE_END_TIME,Calendar.getInstance().getTimeInMillis()));
        item.setGpsLocationName(CommonConst.getPreferences(context,CommonConst.KEY_CASE_GPS_NAME,""));
        item.setGpsLat(CommonConst.getPreferences(context,CommonConst.KEY_CASE_GPS_LAT,""));
        item.setGpsLon(CommonConst.getPreferences(context,CommonConst.KEY_CASE_GPS_LON,""));
        item.setLocation1Name(CommonConst.getPreferences(context,CommonConst.KEY_CASE_LOCATION_1,""));
        item.setLocation1FilePath(CommonConst.getPreferences(context,CommonConst.KEY_CASE_LOCATION_1_FILE,""));
        item.setLocation2Name(CommonConst.getPreferences(context,CommonConst.KEY_CASE_LOCATION_2,""));
        item.setLocation2FilePath(CommonConst.getPreferences(context,CommonConst.KEY_CASE_LOCATION_2_FILE,""));
        item.setLocation3Name(CommonConst.getPreferences(context,CommonConst.KEY_CASE_LOCATION_3,""));
        item.setLocation3FilePath(CommonConst.getPreferences(context,CommonConst.KEY_CASE_LOCATION_3_FILE,""));
        item.setLocation4Name(CommonConst.getPreferences(context,CommonConst.KEY_CASE_LOCATION_4,""));
        item.setLocation4FilePath(CommonConst.getPreferences(context,CommonConst.KEY_CASE_LOCATION_4_FILE,""));
        item.setLocation5Name(CommonConst.getPreferences(context,CommonConst.KEY_CASE_LOCATION_5,""));
        item.setLocation5FilePath(CommonConst.getPreferences(context,CommonConst.KEY_CASE_LOCATION_5_FILE,""));

        long keyID = CommonConst.getPreferences(context,CommonConst.KEY_ID,0);
        if(keyID == 0){
            item.setCreateTime(Calendar.getInstance().getTimeInMillis());
            provider.insert(item);
        }else {
            provider.update(item);
        }
    }

    public static String getCaseInfo(Context context){
        String info = "";
        String caseName = CommonConst.getPreferences(context,CommonConst.KEY_CASE_NAME, "");
        String caseStartTime = DateTimePicker.getCurrentDashTime(
                CommonConst.getPreferences(context,
                        CommonConst.KEY_CASE_START_TIME,Calendar.getInstance().getTimeInMillis()));
        String caseEndTime = DateTimePicker.getCurrentDashTime(
                CommonConst.getPreferences(context,
                        CommonConst.KEY_CASE_END_TIME,Calendar.getInstance().getTimeInMillis()));
        String caseGps = CommonConst.getPreferences(context,CommonConst.KEY_CASE_GPS_NAME,"");
        String gpsLat = CommonConst.getPreferences(context,CommonConst.KEY_CASE_GPS_LAT,"");
        String gpsLon = CommonConst.getPreferences(context,CommonConst.KEY_CASE_GPS_LON,"");
        String location1 = CommonConst.getPreferences(context,CommonConst.KEY_CASE_LOCATION_1,"");
        String location2 = CommonConst.getPreferences(context,CommonConst.KEY_CASE_LOCATION_2,"");
        String location3 = CommonConst.getPreferences(context,CommonConst.KEY_CASE_LOCATION_3,"");
        String location4 = CommonConst.getPreferences(context,CommonConst.KEY_CASE_LOCATION_4,"");
        String location5 = CommonConst.getPreferences(context,CommonConst.KEY_CASE_LOCATION_5,"");

        String locationFile1 = CommonConst.getPreferences(context,CommonConst.KEY_CASE_LOCATION_1_FILE,"");
        String locationFile2 = CommonConst.getPreferences(context,CommonConst.KEY_CASE_LOCATION_2_FILE,"");
        String locationFile3 = CommonConst.getPreferences(context,CommonConst.KEY_CASE_LOCATION_3_FILE,"");
        String locationFile4 = CommonConst.getPreferences(context,CommonConst.KEY_CASE_LOCATION_4_FILE,"");
        String locationFile5 = CommonConst.getPreferences(context,CommonConst.KEY_CASE_LOCATION_4_FILE,"");

        String gpsLocation = "";
        if(gpsLat.isEmpty()){
            gpsLocation = context.getResources().getString(R.string.gps_location_fail);
        }else {
            gpsLocation = gpsLat + ", " + gpsLon;
        }
        String locationCollection1 = "";
        if(locationFile1.isEmpty()){
            locationCollection1 = context.getResources().getString(R.string.collection_fail);
        }else{
            locationCollection1 = context.getResources().getString(R.string.collection_over);
        }
        String locationCollection2 = "";
        if(locationFile2.isEmpty()){
            locationCollection2 = context.getResources().getString(R.string.collection_fail);
        }else{
            locationCollection2 = context.getResources().getString(R.string.collection_over);
        }
        String locationCollection3 = "";
        if(locationFile3.isEmpty()){
            locationCollection3 = context.getResources().getString(R.string.collection_fail);
        }else{
            locationCollection3 = context.getResources().getString(R.string.collection_over);
        }
        String locationCollection4 = "";
        if(locationFile4.isEmpty()){
            locationCollection4 = context.getResources().getString(R.string.collection_fail);
        }else{
            locationCollection4 = context.getResources().getString(R.string.collection_over);
        }
        String locationCollection5 = "";
        if(locationFile5.isEmpty()){
            locationCollection5 = context.getResources().getString(R.string.collection_fail);
        }else{
            locationCollection5 = context.getResources().getString(R.string.collection_over);
        }

        info = context.getResources().getString(R.string.case_name) + ": " + caseName + "\n"
                + context.getResources().getString(R.string.crime_time_start_info) + " " + caseStartTime + "\n"
                + context.getResources().getString(R.string.crime_time_end_info) + " " + caseEndTime + "\n"
                + context.getResources().getString(R.string.gps_string) + " " + caseGps + "\n"
                + context.getResources().getString(R.string.gps_location) + " " + gpsLocation + "\n"
                + context.getResources().getString(R.string.location1) + " " + location1 + "  " + locationCollection1 + "\n"
                + context.getResources().getString(R.string.location2) + " " + location2 + "  " + locationCollection2 + "\n"
                + context.getResources().getString(R.string.location3) + " " + location3 + "  " + locationCollection3 + "\n"
                + context.getResources().getString(R.string.location4) + " " + location4 + "  " + locationCollection4 + "\n"
                + context.getResources().getString(R.string.location5) + " " + location5 + "  " + locationCollection5 + "\n";

        return info;
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
