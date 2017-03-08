package com.android.newcrime;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

import com.android.newcrime.utils.ClearableEditText;
import com.android.newcrime.utils.CommonConst;

import java.util.ArrayList;

/**
 * Created by zwb on 2017/3/5.
 */

public class CreateCrimeActivityP2 extends AppCompatActivity {

    ImageView mToolbarLeftButton;
    ImageView mToolbarRightButton;

    Button mBottomRightButton;
    Button mBottomLeftButton;

    ClearableEditText mLocationName1;
    ClearableEditText mLocationName2;
    ClearableEditText mLocationName3;

    TextView mLocationNumCount1;
    TextView mLocationNumCount2;
    TextView mLocationNumCount3;

    Button mLocationCollection1;
    Button mLocationCollection2;
    Button mLocationCollection3;

    boolean mIsCollectionIng;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.create_crime_layout_p2);

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

        mLocationName1 = (ClearableEditText) findViewById(R.id.location_1_editview);
        mLocationName1.setFilters(new InputFilter[]{new InputFilter.LengthFilter(35)});
        mLocationName1.addTextChangedListener(new TextWatcher() {
            private String temp;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                temp = charSequence.toString();
                mLocationNumCount1.setText(temp.length() + "/35");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        String locationName1 = CommonConst.getPreferences(getApplicationContext(),
                CommonConst.KEY_CASE_LOCATION_1, "");
        if(locationName1 != null && !locationName1.isEmpty()){
            mLocationName1.setText(locationName1);
        }

        mLocationNumCount1 = (TextView)findViewById(R.id.location1_num_count);

        mLocationCollection1 = (Button)findViewById(R.id.location_collection_1_button);
        mLocationCollection1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mLocationName1.getText() == null || mLocationName1.getText().isEmpty()){
                    Toast.makeText(getApplicationContext(),
                            getResources().getString(R.string.location_name_empty),Toast.LENGTH_SHORT).show();
                }else {
                    CommonConst.setPreferences(getApplicationContext(),
                            CommonConst.KEY_CASE_LOCATION_1,mLocationName1.getText());
                    startCollectionLocation1();
                }
            }
        });

        String locatonFile1 = CommonConst.getPreferences(getApplicationContext(),
                CommonConst.KEY_CASE_LOCATION_1_FILE, "");
        if(locatonFile1 != null && !locatonFile1.isEmpty()){
            mLocationCollection1.setText(getResources().getString(R.string.collection_over));
        }

        mLocationName2 = (ClearableEditText) findViewById(R.id.location_2_editview);
        mLocationName2.setFilters(new InputFilter[]{new InputFilter.LengthFilter(35)});
        mLocationName2.addTextChangedListener(new TextWatcher() {
            private String temp;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                temp = charSequence.toString();
                mLocationNumCount2.setText(temp.length() + "/35");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        String locationName2 = CommonConst.getPreferences(getApplicationContext(),
                CommonConst.KEY_CASE_LOCATION_2, "");
        if(locationName2 != null && !locationName2.isEmpty()){
            mLocationName2.setText(locationName2);
        }
        mLocationNumCount2 = (TextView)findViewById(R.id.location2_num_count);
        mLocationCollection2 = (Button)findViewById(R.id.location_collection_2_button);
        mLocationCollection2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mLocationName2.getText() == null || mLocationName2.getText().isEmpty()){
                    Toast.makeText(getApplicationContext(),
                            getResources().getString(R.string.location_name_empty),Toast.LENGTH_SHORT).show();
                }else {
                    CommonConst.setPreferences(getApplicationContext(),
                            CommonConst.KEY_CASE_LOCATION_2,mLocationName2.getText());
                    startCollectionLocation2();
                }
            }
        });
        String locatonFile2 = CommonConst.getPreferences(getApplicationContext(),
                CommonConst.KEY_CASE_LOCATION_2_FILE, "");
        if(locatonFile2 != null && !locatonFile2.isEmpty()){
            mLocationCollection2.setText(getResources().getString(R.string.collection_over));
        }

        mLocationName3 = (ClearableEditText) findViewById(R.id.location_3_editview);
        mLocationName3.setFilters(new InputFilter[]{new InputFilter.LengthFilter(35)});
        mLocationName3.addTextChangedListener(new TextWatcher() {
            private String temp;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                temp = charSequence.toString();
                mLocationNumCount3.setText(temp.length() + "/35");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        String locationName3 = CommonConst.getPreferences(getApplicationContext(),
                CommonConst.KEY_CASE_LOCATION_3, "");
        if(locationName3 != null && !locationName3.isEmpty()){
            mLocationName3.setText(locationName3);
        }
        mLocationNumCount3 = (TextView)findViewById(R.id.location3_num_count);
        mLocationCollection3 = (Button)findViewById(R.id.location_collection_3_button);
        mLocationCollection3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mLocationName3.getText() == null || mLocationName3.getText().isEmpty()){
                    Toast.makeText(getApplicationContext(),
                            getResources().getString(R.string.location_name_empty),Toast.LENGTH_SHORT).show();
                }else {
                    CommonConst.setPreferences(getApplicationContext(),
                            CommonConst.KEY_CASE_LOCATION_3,mLocationName3.getText());
                    startCollectionLocation3();
                }
            }
        });
        String locatonFile3 = CommonConst.getPreferences(getApplicationContext(),
                CommonConst.KEY_CASE_LOCATION_3_FILE, "");
        if(locatonFile3 != null && !locatonFile3.isEmpty()){
            mLocationCollection3.setText(getResources().getString(R.string.collection_over));
        }


        mBottomRightButton = (Button)findViewById(R.id.bottom_right_button);
        mBottomRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedata();
                startActivity(new Intent(CreateCrimeActivityP2.this, CreateCrimeActivityP3.class));
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
            mLocationCollection1.setClickable(false);
            mLocationCollection2.setClickable(false);
            mLocationCollection3.setClickable(false);

            mBottomRightButton.setEnabled(false);
        }else {
            mLocationCollection1.setClickable(true);
            mLocationCollection2.setClickable(true);
            mLocationCollection3.setClickable(true);

            mBottomRightButton.setEnabled(true);
        }

        String path1 = CommonConst.getPreferences(getApplicationContext(),
                CommonConst.KEY_CASE_LOCATION_1_FILE, "");
        if(path1 != null && !path1.isEmpty()){
            mLocationCollection1.setText(getString(R.string.collection_over));
        }
        String path2 = CommonConst.getPreferences(getApplicationContext(),
                CommonConst.KEY_CASE_LOCATION_2_FILE, "");
        if(path2 != null && !path2.isEmpty()){
            mLocationCollection2.setText(getString(R.string.collection_over));
        }
        String path3 = CommonConst.getPreferences(getApplicationContext(),
                CommonConst.KEY_CASE_LOCATION_3_FILE, "");
        if(path3 != null && !path3.isEmpty()){
            mLocationCollection3.setText(getString(R.string.collection_over));
        }
    }

    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    private void savedata(){
        if(mLocationName1.getText() != null && !mLocationName1.getText().isEmpty()){
            CommonConst.setPreferences(getApplicationContext(),
                    CommonConst.KEY_CASE_LOCATION_1,mLocationName1.getText());
        }

        if(mLocationName2.getText() != null && !mLocationName2.getText().isEmpty()){
            CommonConst.setPreferences(getApplicationContext(),
                    CommonConst.KEY_CASE_LOCATION_2,mLocationName2.getText());
        }

        if(mLocationName3.getText() != null && !mLocationName3.getText().isEmpty()){
            CommonConst.setPreferences(getApplicationContext(),
                    CommonConst.KEY_CASE_LOCATION_3,mLocationName3.getText());
        }
    }

    /**/
    private void startCollectionLocation1(){
        mIsCollectionIng = true;
        mLocationCollection1.setClickable(false);
        mLocationCollection1.setText(getString(R.string.collection_runing));

        mLocationCollection2.setClickable(false);
        mLocationCollection3.setClickable(false);

        mBottomRightButton.setEnabled(false);

        CommonConst.setPreferences(getApplicationContext(),
                CommonConst.KEY_CASE_COLLECTION_ING,true);
        CommonConst.setPreferences(getApplicationContext(),
                CommonConst.KEY_CASE_LOCATION_1_COLLECTION_ING,true);

        Intent it=new Intent();
        it.setAction("com.kuaikan.one_key");
        it.setComponent(new ComponentName("com.kuaikan.app.scenecollection",
                "com.kuaikan.app.scenecollection.OneKeyService"));

        mContext.startService(it);
    }

    private void startCollectionLocation2(){
        mIsCollectionIng = true;
        mLocationCollection1.setClickable(false);

        mLocationCollection2.setText(getString(R.string.collection_runing));

        mLocationCollection2.setClickable(false);
        mLocationCollection3.setClickable(false);

        mBottomRightButton.setEnabled(false);

        CommonConst.setPreferences(getApplicationContext(),
                CommonConst.KEY_CASE_COLLECTION_ING,true);
        CommonConst.setPreferences(getApplicationContext(),
                CommonConst.KEY_CASE_LOCATION_2_COLLECTION_ING,true);

        Intent it=new Intent();
        it.setAction("com.kuaikan.one_key");
        it.setComponent(new ComponentName("com.kuaikan.app.scenecollection",
                "com.kuaikan.app.scenecollection.OneKeyService"));

        mContext.startService(it);
    }

    private void startCollectionLocation3(){
        mIsCollectionIng = true;
        mLocationCollection1.setClickable(false);

        mLocationCollection2.setText(getString(R.string.collection_runing));

        mLocationCollection2.setClickable(false);
        mLocationCollection3.setClickable(false);

        mBottomRightButton.setEnabled(false);

        CommonConst.setPreferences(getApplicationContext(),
                CommonConst.KEY_CASE_COLLECTION_ING,true);
        CommonConst.setPreferences(getApplicationContext(),
                CommonConst.KEY_CASE_LOCATION_3_COLLECTION_ING,true);

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

                if(CommonConst.getPreferences(getApplicationContext(),CommonConst.KEY_CASE_LOCATION_1_COLLECTION_ING,false)){
                    CommonConst.setPreferences(getApplicationContext(),
                            CommonConst.KEY_CASE_LOCATION_1_COLLECTION_ING,false);
                    mLocationCollection1.setText(getString(R.string.collection_over));
                    CommonConst.setPreferences(getApplicationContext(),
                            CommonConst.KEY_CASE_LOCATION_1_FILE, path);
                }else if(CommonConst.getPreferences(getApplicationContext(),CommonConst.KEY_CASE_LOCATION_2_COLLECTION_ING,false)){
                    CommonConst.setPreferences(getApplicationContext(),
                            CommonConst.KEY_CASE_LOCATION_2_COLLECTION_ING,false);
                    mLocationCollection2.setText(getString(R.string.collection_over));
                    CommonConst.setPreferences(getApplicationContext(),
                            CommonConst.KEY_CASE_LOCATION_2_FILE, path);
                }else if(CommonConst.getPreferences(getApplicationContext(),CommonConst.KEY_CASE_LOCATION_3_COLLECTION_ING,false)){
                    CommonConst.setPreferences(getApplicationContext(),
                            CommonConst.KEY_CASE_LOCATION_3_COLLECTION_ING,false);
                    mLocationCollection3.setText(getString(R.string.collection_over));
                    CommonConst.setPreferences(getApplicationContext(),
                            CommonConst.KEY_CASE_LOCATION_3_FILE, path);
                }
            }
        }
    };
}
