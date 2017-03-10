package com.android.newcrime;

import android.content.Context;
import android.os.Environment;

import com.android.newcrime.databases.CrimeItem;
import com.android.newcrime.databases.CrimeProvider;
import com.android.newcrime.utils.CommonConst;
import com.android.newcrime.utils.DirTraversal;
import com.android.newcrime.utils.FileHelper;
import com.android.newcrime.utils.ZipUtils;

import java.io.File;
import java.util.LinkedList;

/**
 * Created by zwb on 2017/3/9.
 */

public class DataInitial {
    private Context mContext = null;

    public DataInitial(Context context){
        this.mContext = context;
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
}
