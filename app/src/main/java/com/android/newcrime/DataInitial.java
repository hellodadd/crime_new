package com.android.newcrime;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.util.Xml;

import com.amap.api.maps.MapsInitializer;
import com.android.newcrime.databases.CrimeItem;
import com.android.newcrime.databases.CrimeProvider;
import com.android.newcrime.utils.CommonConst;
import com.android.newcrime.utils.DirTraversal;
import com.android.newcrime.utils.FileHelper;
import com.android.newcrime.utils.ZipUtils;

import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zwb on 2017/3/9.
 */

public class DataInitial {
    private Context mContext = null;

    public DataInitial(Context context){
        this.mContext = context;
    }

    //Command 1
    public void createDeviceMsgXml() {
        XmlHandler xmlhandler = new XmlHandler();
        String deviceid = Build.SERIAL;//(SystemProperties.get("ro.serialno"));
        String initstatus = "1";
        String swversion = "";
        String mapversion= MapsInitializer.getVersion();

       // SharedPreferences prefs = mContext.getSharedPreferences("InitialDevice", 0);
       // initstatus = prefs.getString("Initial", "0");

        try {
            PackageManager manager = mContext.getPackageManager();
            PackageInfo info = manager.getPackageInfo(mContext.getPackageName(), 0);
            swversion = info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        xmlhandler.createDeviceMsg(deviceid, initstatus, swversion, mapversion);
    }

    //Command 11
    public boolean CreateBaseMsg(){
        CrimeProvider mCrimeProvider = new CrimeProvider(mContext);
        mCrimeProvider.createScenesInfoXml();
        return true;
    }

    //Command 12
    public boolean CreateBaseMsgIdZip(String id){
        CrimeProvider mCrimeProvider = new CrimeProvider(mContext);
        CrimeItem crimeItem = mCrimeProvider.createBaseMsgXml(id);

        if(crimeItem==null) return false;

        String catchPath = Environment.getExternalStorageDirectory()+"/BaseMsg/";
        File cacheDir = new File(catchPath);
        if(!cacheDir.exists()) {
            cacheDir.mkdir();
        }

        try {
            CommonConst.copyFile(FileHelper.newFile(CommonConst.CASE_INFO_XML).toString(),
                    catchPath+CommonConst.CASE_INFO_XML);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String location1path = crimeItem.getLocation1FilePath();
        if(location1path != null && !location1path.isEmpty()){
            String[] filename = location1path.split("/");
            CommonConst.copyFile(location1path, catchPath+filename[filename.length-1]);
        }
        String location2path = crimeItem.getLocation2FilePath();
        if(location2path != null && !location2path.isEmpty()){
            String[] filename = location2path.split("/");
            CommonConst.copyFile(location2path, catchPath+filename[filename.length-1]);
        }
        String location3path = crimeItem.getLocation3FilePath();
        if(location3path != null && !location3path.isEmpty()){
            String[] filename = location3path.split("/");
            CommonConst.copyFile(location3path, catchPath+filename[filename.length-1]);
        }
        String location4path = crimeItem.getLocation4FilePath();
        if(location4path != null && !location4path.isEmpty()){
            String[] filename = location4path.split("/");
            CommonConst.copyFile(location4path, catchPath+filename[filename.length-1]);
        }
        String location5path = crimeItem.getLocation5FilePath();
        if(location5path != null && !location5path.isEmpty()){
            String[] filename = location5path.split("/");
            CommonConst.copyFile(location5path, catchPath+filename[filename.length-1]);
        }
        try{
            LinkedList<File> files = DirTraversal.listLinkedFiles(catchPath);
            String backupFileName = "/" + CommonConst.CASE_INFO_ZIP;
            File file = DirTraversal.getFilePath(Environment.getExternalStorageDirectory().getAbsolutePath(), backupFileName);
            ZipUtils.zipFiles(files, file);
            CommonConst.deleteFiles(cacheDir);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    //Command 14
    public boolean deleteSceneInfo(){
        XmlHandler xmlhandler = new XmlHandler();
        List<String> object = xmlhandler.deleteSceneInfoCmd();
        List result = new ArrayList<String>();

        Log.e("zwb", "zwb ------ deleteSceneInfo = " + object);

        if(object == null || object.size()==0) return false;

        CrimeProvider mCrime = new CrimeProvider(mContext);
        for(int i=0;i<object.size();i++){
            String id = object.get(i);
            Log.e("zwb", "zwb ------ deleteSceneInfo id = " + id);
            CrimeItem mCrimeItem = mCrime.getItem(id);
            if(mCrimeItem != null) {
                mCrime.delete(mCrimeItem.getId());
                result.add(id);
            }else{
                Log.d("Anita","Cannot get the scene id from databases");
            }
        }

        if(result.size()!=0){
            xmlhandler.createSuccessDeleteMsgFile(result);
            return true;
        }else{
            return false;
        }
    }

    //Command 13
    public boolean WriteSceneNo(){
        XmlHandler xmlhandler = new XmlHandler();
        String[] object = xmlhandler.writeSceneIdCmd();

        if(object == null || object.length==0) return false;

        String id = object[0];
        String SceneNo = object[1];
        CrimeProvider mCrime = new CrimeProvider(mContext);
        CrimeItem mCrimeItem = mCrime.getItem(id);

        if(mCrimeItem == null || SceneNo.length() ==0) return false;
        //if(mCrimeItem.getComplete().equalsIgnoreCase("0")) return false;

        //mCrimeItem.setComplete("2");
       // mCrimeItem.setCreateTime(Calendar.getInstance().getTimeInMillis());
        mCrimeItem.setCaseStatus(SceneNo);
        mCrime.update(mCrimeItem);

        return true;
    }
}
