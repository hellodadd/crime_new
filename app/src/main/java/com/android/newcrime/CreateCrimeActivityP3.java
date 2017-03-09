package com.android.newcrime;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.newcrime.databases.CrimeItem;
import com.android.newcrime.databases.CrimeProvider;
import com.android.newcrime.utils.ClearableEditText;
import com.android.newcrime.utils.CommonConst;
import com.android.newcrime.utils.DateTimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by zwb on 2017/3/5.
 */

public class CreateCrimeActivityP3 extends AppCompatActivity {
    ImageView mToolbarLeftButton;
    ImageView mToolbarRightButton;

    Button mBottomRightButton;
    Button mBottomLeftButton;

    ClearableEditText mLocationName4;
    ClearableEditText mLocationName5;

    TextView mLocationNumCount4;
    TextView mLocationNumCount5;

    Button mLocationCollection4;
    Button mLocationCollection5;

    boolean mIsCollectionIng;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.create_crime_layout_p3);

        mContext = getApplicationContext();

        mToolbarLeftButton = (ImageView)findViewById(R.id.toolbar_left_button);
        mToolbarLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mIsCollectionIng){
                    Toast.makeText(getApplicationContext(),
                            getResources().getString(R.string.msg_alert_collection_ing),Toast.LENGTH_LONG).show();
                }else{
                    onBackPressed();
                }
            }
        });
        mToolbarRightButton = (ImageView)findViewById(R.id.toolbar_right_button);
        mToolbarRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedata();
                Toast.makeText(getApplicationContext(),
                        getResources().getString(R.string.msg_alert_save),Toast.LENGTH_LONG).show();
            }
        });

        mLocationName4 = (ClearableEditText) findViewById(R.id.location_4_editview);
        mLocationName4.setFilters(new InputFilter[]{new InputFilter.LengthFilter(35)});
        mLocationName4.addTextChangedListener(new TextWatcher() {
            private String temp;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                temp = charSequence.toString();
                mLocationNumCount4.setText(temp.length() + "/35");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        String locationName4 = CommonConst.getPreferences(getApplicationContext(),
                CommonConst.KEY_CASE_LOCATION_4, "");
        if(locationName4 != null && !locationName4.isEmpty()){
            mLocationName4.setText(locationName4);
        }

        mLocationNumCount4 = (TextView)findViewById(R.id.location4_num_count);

        mLocationCollection4 = (Button)findViewById(R.id.location_collection_4_button);
        mLocationCollection4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mLocationName4.getText() == null || mLocationName4.getText().isEmpty()){
                    Toast.makeText(getApplicationContext(),
                            getResources().getString(R.string.location_name_empty),Toast.LENGTH_SHORT).show();
                }else {
                    CommonConst.setPreferences(getApplicationContext(),
                            CommonConst.KEY_CASE_LOCATION_4,mLocationName4.getText());
                    startCollectionLocation4();
                }
            }
        });

        String locatonFile4 = CommonConst.getPreferences(getApplicationContext(),
                CommonConst.KEY_CASE_LOCATION_4_FILE, "");
        if(locatonFile4 != null && !locatonFile4.isEmpty()){
            mLocationCollection4.setText(getResources().getString(R.string.collection_over));
        }

        mLocationName5 = (ClearableEditText) findViewById(R.id.location_5_editview);
        mLocationName5.setFilters(new InputFilter[]{new InputFilter.LengthFilter(35)});
        mLocationName5.addTextChangedListener(new TextWatcher() {
            private String temp;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                temp = charSequence.toString();
                mLocationNumCount5.setText(temp.length() + "/35");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        String locationName5 = CommonConst.getPreferences(getApplicationContext(),
                CommonConst.KEY_CASE_LOCATION_5, "");
        if(locationName5 != null && !locationName5.isEmpty()){
            mLocationName5.setText(locationName5);
        }
        mLocationNumCount5 = (TextView)findViewById(R.id.location5_num_count);
        mLocationCollection5 = (Button)findViewById(R.id.location_collection_5_button);
        mLocationCollection5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mLocationName5.getText() == null || mLocationName5.getText().isEmpty()){
                    Toast.makeText(getApplicationContext(),
                            getResources().getString(R.string.location_name_empty),Toast.LENGTH_SHORT).show();
                }else {
                    CommonConst.setPreferences(getApplicationContext(),
                            CommonConst.KEY_CASE_LOCATION_5,mLocationName5.getText());
                    startCollectionLocation5();
                }
            }
        });
        String locatonFile5 = CommonConst.getPreferences(getApplicationContext(),
                CommonConst.KEY_CASE_LOCATION_5_FILE, "");
        if(locatonFile5 != null && !locatonFile5.isEmpty()){
            mLocationCollection5.setText(getResources().getString(R.string.collection_over));
        }


        mBottomRightButton = (Button)findViewById(R.id.bottom_right_button);
        mBottomRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //saveCaseInfoXml(getApplicationContext());
                savedata();
                showSaveCaseInfoDialog();
            }
        });

        mBottomLeftButton = (Button)findViewById(R.id.bottom_left_button);
        mBottomLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        IntentFilter filter = new IntentFilter();
        filter.addAction(CommonConst.ACTION_RECEIVE_RESULT);
        registerReceiver(receiver, filter);

    }

    protected void onResume(){
        super.onResume();
        mIsCollectionIng = CommonConst.getPreferences(getApplicationContext(),
                CommonConst.KEY_CASE_COLLECTION_ING, false);
        if(mIsCollectionIng){
            mLocationCollection4.setClickable(false);
            mLocationCollection5.setClickable(false);

            mBottomRightButton.setEnabled(false);
        }else {
            mLocationCollection4.setClickable(true);
            mLocationCollection5.setClickable(true);

            mBottomRightButton.setEnabled(true);
        }

        String path4 = CommonConst.getPreferences(getApplicationContext(),
                CommonConst.KEY_CASE_LOCATION_4_FILE, "");
        if(path4 != null && !path4.isEmpty()){
            mLocationCollection4.setText(getString(R.string.collection_over));
        }
        String path5 = CommonConst.getPreferences(getApplicationContext(),
                CommonConst.KEY_CASE_LOCATION_5_FILE, "");
        if(path5 != null && !path5.isEmpty()){
            mLocationCollection5.setText(getString(R.string.collection_over));
        }
    }

    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    private void savedata(){
        if(mLocationName4.getText() != null && !mLocationName4.getText().isEmpty()){
            CommonConst.setPreferences(getApplicationContext(),
                    CommonConst.KEY_CASE_LOCATION_4,mLocationName4.getText());
        }

        if(mLocationName5.getText() != null && !mLocationName5.getText().isEmpty()){
            CommonConst.setPreferences(getApplicationContext(),
                    CommonConst.KEY_CASE_LOCATION_5,mLocationName5.getText());
        }
    }

    /**/
    private void startCollectionLocation4(){
        mIsCollectionIng = true;
        mLocationCollection4.setClickable(false);
        mLocationCollection4.setText(getString(R.string.collection_runing));

        mLocationCollection5.setClickable(false);

        mBottomRightButton.setEnabled(false);

        CommonConst.setPreferences(getApplicationContext(),
                CommonConst.KEY_CASE_COLLECTION_ING,true);
        CommonConst.setPreferences(getApplicationContext(),
                CommonConst.KEY_CASE_LOCATION_4_COLLECTION_ING,true);

        Intent it=new Intent();
        it.setAction("com.kuaikan.one_key");
        it.setComponent(new ComponentName("com.kuaikan.app.scenecollection",
                "com.kuaikan.app.scenecollection.OneKeyService"));

        mContext.startService(it);
    }

    private void startCollectionLocation5(){
        mIsCollectionIng = true;
        mLocationCollection4.setClickable(false);

        mLocationCollection5.setText(getString(R.string.collection_runing));

        mLocationCollection5.setClickable(false);

        mBottomRightButton.setEnabled(false);

        CommonConst.setPreferences(getApplicationContext(),
                CommonConst.KEY_CASE_COLLECTION_ING,true);
        CommonConst.setPreferences(getApplicationContext(),
                CommonConst.KEY_CASE_LOCATION_5_COLLECTION_ING,true);

        Intent it=new Intent();
        it.setAction("com.kuaikan.one_key");
        it.setComponent(new ComponentName("com.kuaikan.app.scenecollection",
                "com.kuaikan.app.scenecollection.OneKeyService"));

        mContext.startService(it);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(CommonConst.ACTION_RECEIVE_RESULT)){
                Log.i("zwb","zwb ----- receiver");
                mBottomRightButton.setEnabled(true);
                mIsCollectionIng = false;
                CommonConst.setPreferences(getApplicationContext(),
                        CommonConst.KEY_CASE_COLLECTION_ING,false);

                ArrayList<String> result= (ArrayList<String>) intent.getStringArrayListExtra("result");
                String file_path = (String) intent.getStringExtra("file_path");
                String uuid = (String) intent.getStringExtra("uuid");
                String path = CommonConst.copyToInternalPath(getApplicationContext(), file_path);

                if(CommonConst.getPreferences(getApplicationContext(),CommonConst.KEY_CASE_LOCATION_4_COLLECTION_ING,false)){
                    CommonConst.setPreferences(getApplicationContext(),
                            CommonConst.KEY_CASE_LOCATION_4_COLLECTION_ING,false);
                    mLocationCollection4.setText(getString(R.string.collection_over));
                    CommonConst.setPreferences(getApplicationContext(),
                            CommonConst.KEY_CASE_LOCATION_4_FILE, path);
                }else if(CommonConst.getPreferences(getApplicationContext(),CommonConst.KEY_CASE_LOCATION_5_COLLECTION_ING,false)){
                    CommonConst.setPreferences(getApplicationContext(),
                            CommonConst.KEY_CASE_LOCATION_5_COLLECTION_ING,false);
                    mLocationCollection5.setText(getString(R.string.collection_over));
                    CommonConst.setPreferences(getApplicationContext(),
                            CommonConst.KEY_CASE_LOCATION_5_FILE, path);
                }
            }
        }
    };

    private void saveCaseInfoXml(Context context){
        List<HashMap<String, String>> caseInfoList = new ArrayList<>();

        HashMap<String, String> caseInfo = new LinkedHashMap<String, String>();
        caseInfo.put("casename",CommonConst.getPreferences(context,CommonConst.KEY_CASE_NAME, ""));
        caseInfo.put("casestarttime",
                DateTimePicker.getCurrentDashDate(
                        CommonConst.getPreferences(context,
                                CommonConst.KEY_CASE_START_TIME,Calendar.getInstance().getTimeInMillis())));
        caseInfo.put("caseendtime",DateTimePicker.getCurrentDashDate(
                CommonConst.getPreferences(context,
                        CommonConst.KEY_CASE_END_TIME,Calendar.getInstance().getTimeInMillis())));
        caseInfo.put("gpslocation",CommonConst.getPreferences(context,CommonConst.KEY_CASE_GPS_NAME,""));
        caseInfo.put("gpslat",CommonConst.getPreferences(context,CommonConst.KEY_CASE_GPS_LAT,""));
        caseInfo.put("gpslon",CommonConst.getPreferences(context,CommonConst.KEY_CASE_GPS_LON,""));
        caseInfo.put("location1",CommonConst.getPreferences(context,CommonConst.KEY_CASE_LOCATION_1,""));
        caseInfo.put("location2",CommonConst.getPreferences(context,CommonConst.KEY_CASE_LOCATION_2,""));
        caseInfo.put("location3",CommonConst.getPreferences(context,CommonConst.KEY_CASE_LOCATION_3,""));
        caseInfo.put("location4",CommonConst.getPreferences(context,CommonConst.KEY_CASE_LOCATION_4,""));
        caseInfo.put("location5",CommonConst.getPreferences(context,CommonConst.KEY_CASE_LOCATION_5,""));

        caseInfoList.add(caseInfo);

        final Object[] obj = new Object[1];
        obj[0] = caseInfoList;

        XmlHandler xmlhandler = new XmlHandler();
        xmlhandler.createCaseInfoXmlFile(obj);
    }

    private void saveToDataBase(Context context){
        CrimeProvider provider = new CrimeProvider(context);
        CrimeItem item = new CrimeItem();
        item.setCaseName(CommonConst.getPreferences(context,CommonConst.KEY_CASE_NAME, ""));
        item.setCaseStartTime(CommonConst.getPreferences(context,
                CommonConst.KEY_CASE_START_TIME,Calendar.getInstance().getTimeInMillis()));
        item.setCaseEndTime(CommonConst.getPreferences(context,
                CommonConst.KEY_CASE_END_TIME,Calendar.getInstance().getTimeInMillis()));
        item.setGpsLocationName(CommonConst.getPreferences(context,CommonConst.KEY_CASE_GPS_NAME,""));
        item.setGpsLat(CommonConst.getPreferences(context,CommonConst.KEY_CASE_GPS_LAT,""));
        item.setGpsLon(CommonConst.getPreferences(context,CommonConst.KEY_CASE_GPS_LON,""));
        item.setLocation1Name(CommonConst.getPreferences(context,CommonConst.KEY_CASE_LOCATION_1,""));
        item.setLocation2Name(CommonConst.getPreferences(context,CommonConst.KEY_CASE_LOCATION_2,""));
        item.setLocation3Name(CommonConst.getPreferences(context,CommonConst.KEY_CASE_LOCATION_3,""));
        item.setLocation4Name(CommonConst.getPreferences(context,CommonConst.KEY_CASE_LOCATION_4,""));
        item.setLocation5Name(CommonConst.getPreferences(context,CommonConst.KEY_CASE_LOCATION_5,""));
        provider.insert(item);
    }

    private void showSaveCaseInfoDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.msg_case_info_confirm));
        builder.setMessage(getCaseInfo(getApplicationContext()));
        builder.setNegativeButton(getResources().getString(R.string.cancel),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //onBackPressed();
                        CommonConst.setCaseSaveOK(getApplicationContext(), false);
                    }
                });
        builder.setPositiveButton(getResources().getString(R.string.save),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //startActivity(new Intent(AppActivity.this, CreateActivity.class));
                        saveToDataBase(getApplicationContext());
                        CommonConst.setCaseSaveOK(getApplicationContext(), true);
                        finish();
                        startActivity(new Intent(CreateCrimeActivityP3.this, AppActivity.class));
                    }
                });
        builder.show();
    }

    private String getCaseInfo(Context context){
        String info = "";
        String caseName = CommonConst.getPreferences(context,CommonConst.KEY_CASE_NAME, "");
        String caseStartTime = DateTimePicker.getCurrentDashDate(
                CommonConst.getPreferences(context,
                        CommonConst.KEY_CASE_START_TIME,Calendar.getInstance().getTimeInMillis()));
        String caseEndTime = DateTimePicker.getCurrentDashDate(
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
            gpsLocation = getString(R.string.gps_location_fail);
        }else {
            gpsLocation = gpsLat + ", " + gpsLon;
        }
        String locationCollection1 = "";
        if(locationFile1.isEmpty()){
            locationCollection1 = getResources().getString(R.string.collection_fail);
        }else{
            locationCollection1 = getResources().getString(R.string.collection_over);
        }
        String locationCollection2 = "";
        if(locationFile2.isEmpty()){
            locationCollection2 = getResources().getString(R.string.collection_fail);
        }else{
            locationCollection2 = getResources().getString(R.string.collection_over);
        }
        String locationCollection3 = "";
        if(locationFile3.isEmpty()){
            locationCollection3 = getResources().getString(R.string.collection_fail);
        }else{
            locationCollection3 = getResources().getString(R.string.collection_over);
        }
        String locationCollection4 = "";
        if(locationFile4.isEmpty()){
            locationCollection4 = getResources().getString(R.string.collection_fail);
        }else{
            locationCollection4 = getResources().getString(R.string.collection_over);
        }
        String locationCollection5 = "";
        if(locationFile5.isEmpty()){
            locationCollection5 = getResources().getString(R.string.collection_fail);
        }else{
            locationCollection5 = getResources().getString(R.string.collection_over);
        }

        info = getResources().getString(R.string.case_name) + ": " + caseName + "\n"
                + getResources().getString(R.string.crime_time_start_info) + " " + caseStartTime + "\n"
                + getResources().getString(R.string.crime_time_end_info) + " " + caseEndTime + "\n"
                + getResources().getString(R.string.gps_string) + " " + caseGps + "\n"
                + getResources().getString(R.string.gps_location) + " " + gpsLocation + "\n"
                + getResources().getString(R.string.location1) + " " + location1 + "  " + locationCollection1 + "\n"
                + getResources().getString(R.string.location2) + " " + location2 + "  " + locationCollection2 + "\n"
                + getResources().getString(R.string.location3) + " " + location3 + "  " + locationCollection3 + "\n"
                + getResources().getString(R.string.location4) + " " + location4 + "  " + locationCollection4 + "\n"
                + getResources().getString(R.string.location5) + " " + location5 + "  " + locationCollection5 + "\n";

        return info;
    }
}
