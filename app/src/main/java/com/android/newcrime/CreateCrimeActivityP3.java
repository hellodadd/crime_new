package com.android.newcrime;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
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

import com.android.newcrime.utils.ClearableEditText;
import com.android.newcrime.utils.CommonConst;

import java.util.ArrayList;

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
}
