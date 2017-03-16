package com.android.newcrime;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
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

import static com.android.newcrime.utils.CommonConst.isCollectionDone;

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

    LoadingButton mLocationCollection1;
    LoadingButton mLocationCollection2;
    LoadingButton mLocationCollection3;

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
                showSaveCaseInfoDialog(getApplicationContext());
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
                if(mLocationNumCount1 != null)
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

        mLocationCollection1 = (LoadingButton) findViewById(R.id.location_collection_1_button);
        mLocationCollection1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isCollectionDone(getApplicationContext() , 1)){
                    Intent showRet = new Intent("android.intent.action.kuaikan.show_result");
                    showRet.putExtra("xml", CommonConst.getPreferences(getApplicationContext(),
                            CommonConst.KEY_CASE_LOCATION_1_FILE, ""));
                    showRet.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(showRet);
                    return;
                }
                if(mLocationName1.getText() == null || mLocationName1.getText().isEmpty()){
                    Toast.makeText(getApplicationContext(),
                            getResources().getString(R.string.location_name_empty),Toast.LENGTH_SHORT).show();
                }else {
                    CommonConst.setPreferences(getApplicationContext(),
                            CommonConst.KEY_CASE_LOCATION_1,mLocationName1.getText());
                    startCollectionLocation1();
                    ((LoadingButton) view).showLoading();
                }
            }
        });

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
                if(mLocationNumCount2 != null)
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
        mLocationCollection2 = (LoadingButton)findViewById(R.id.location_collection_2_button);
        mLocationCollection2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isCollectionDone(getApplicationContext() , 2)){
                    Intent showRet = new Intent("android.intent.action.kuaikan.show_result");
                    showRet.putExtra("xml", CommonConst.getPreferences(getApplicationContext(),
                            CommonConst.KEY_CASE_LOCATION_2_FILE, ""));
                    showRet.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(showRet);
                    return;
                }
                if(mLocationName2.getText() == null || mLocationName2.getText().isEmpty()){
                    Toast.makeText(getApplicationContext(),
                            getResources().getString(R.string.location_name_empty),Toast.LENGTH_SHORT).show();
                }else {
                    CommonConst.setPreferences(getApplicationContext(),
                            CommonConst.KEY_CASE_LOCATION_2,mLocationName2.getText());
                    startCollectionLocation2();
                    ((LoadingButton)view).showLoading();
                }
            }
        });


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
                if(mLocationNumCount3 != null)
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
        mLocationCollection3 = (LoadingButton) findViewById(R.id.location_collection_3_button);
        mLocationCollection3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isCollectionDone(getApplicationContext() , 3)){
                    Intent showRet = new Intent("android.intent.action.kuaikan.show_result");
                    showRet.putExtra("xml", CommonConst.getPreferences(getApplicationContext(),
                            CommonConst.KEY_CASE_LOCATION_3_FILE, ""));
                    showRet.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(showRet);
                    return;
                }
                if(mLocationName3.getText() == null || mLocationName3.getText().isEmpty()){
                    Toast.makeText(getApplicationContext(),
                            getResources().getString(R.string.location_name_empty),Toast.LENGTH_SHORT).show();
                }else {
                    CommonConst.setPreferences(getApplicationContext(),
                            CommonConst.KEY_CASE_LOCATION_3,mLocationName3.getText());
                    startCollectionLocation3();
                    ((LoadingButton)view).showLoading();
                }
            }
        });



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
        }else {
            mLocationCollection1.setClickable(true);
            mLocationCollection2.setClickable(true);
            mLocationCollection3.setClickable(true);

        }

        if(CommonConst.isCollectionIng(getApplicationContext(), 1)
                || CommonConst.isCollectionIng(getApplicationContext(), 2)
                || CommonConst.isCollectionIng(getApplicationContext(), 3)){
            mBottomRightButton.setEnabled(false);
        }else{
            mBottomRightButton.setEnabled(true);
        }


        if(isCollectionDone(getApplicationContext(),1)){
            mLocationCollection1.setBackground(getResources().getDrawable(R.drawable.button_succses));
            mLocationCollection1.setText(getString(R.string.collection_over));
            mLocationCollection1.showButtonText();
        }

        if(CommonConst.isCollectionIng(getApplicationContext(), 1)){
            mLocationCollection1.setText(getString(R.string.collection_runing));
            mLocationCollection1.showLoading();
        }

        if(isCollectionDone(getApplicationContext(),2)){
            mLocationCollection2.setBackground(getResources().getDrawable(R.drawable.button_succses));
            mLocationCollection2.setText(getString(R.string.collection_over));
            mLocationCollection2.showButtonText();
        }

        if(CommonConst.isCollectionIng(getApplicationContext(), 2)){
            mLocationCollection2.setText(getString(R.string.collection_runing));
            mLocationCollection2.showLoading();
        }

        if(isCollectionDone(getApplicationContext(),3)){
            mLocationCollection3.setBackground(getResources().getDrawable(R.drawable.button_succses));
            mLocationCollection3.setText(getString(R.string.collection_over));
            mLocationCollection3.showButtonText();
        }

        if(CommonConst.isCollectionIng(getApplicationContext(), 3)){
            mLocationCollection3.setText(getString(R.string.collection_runing));
            mLocationCollection3.showLoading();
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

        mLocationCollection3.setText(getString(R.string.collection_runing));

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

                mLocationCollection1.setClickable(true);
                mLocationCollection2.setClickable(true);
                mLocationCollection3.setClickable(true);

                ArrayList<String> result= (ArrayList<String>) intent.getStringArrayListExtra("result");
                String file_path = (String) intent.getStringExtra("file_path");
                String uuid = (String) intent.getStringExtra("uuid");
                String path = CommonConst.copyToInternalPath(getApplicationContext(), file_path);

                Log.d("zwb","zwb ---------- path " + path);

                if(CommonConst.getPreferences(getApplicationContext(),CommonConst.KEY_CASE_LOCATION_1_COLLECTION_ING,false)){
                    CommonConst.setPreferences(getApplicationContext(),
                            CommonConst.KEY_CASE_LOCATION_1_COLLECTION_ING,false);
                    CommonConst.setPreferences(getApplicationContext(),
                            CommonConst.KEY_CASE_LOCATION_1_COLLECTION_DONE,true);
                    mLocationCollection1.setBackground(getResources().getDrawable(R.drawable.button_succses));
                    mLocationCollection1.setText(getString(R.string.collection_over));
                    mLocationCollection1.showButtonText();
                    CommonConst.setPreferences(getApplicationContext(),
                            CommonConst.KEY_CASE_LOCATION_1_FILE, path);
                }else if(CommonConst.getPreferences(getApplicationContext(),CommonConst.KEY_CASE_LOCATION_2_COLLECTION_ING,false)){
                    CommonConst.setPreferences(getApplicationContext(),
                            CommonConst.KEY_CASE_LOCATION_2_COLLECTION_ING,false);
                    CommonConst.setPreferences(getApplicationContext(),
                            CommonConst.KEY_CASE_LOCATION_2_COLLECTION_DONE,true);
                    mLocationCollection2.setBackground(getResources().getDrawable(R.drawable.button_succses));
                    mLocationCollection2.setText(getString(R.string.collection_over));
                    mLocationCollection2.showButtonText();
                    CommonConst.setPreferences(getApplicationContext(),
                            CommonConst.KEY_CASE_LOCATION_2_FILE, path);
                }else if(CommonConst.getPreferences(getApplicationContext(),CommonConst.KEY_CASE_LOCATION_3_COLLECTION_ING,false)){
                    CommonConst.setPreferences(getApplicationContext(),
                            CommonConst.KEY_CASE_LOCATION_3_COLLECTION_ING,false);
                    CommonConst.setPreferences(getApplicationContext(),
                            CommonConst.KEY_CASE_LOCATION_3_COLLECTION_DONE,true);
                    mLocationCollection3.setBackground(getResources().getDrawable(R.drawable.button_succses));
                    mLocationCollection3.setText(getString(R.string.collection_over));
                    mLocationCollection3.showButtonText();
                    CommonConst.setPreferences(getApplicationContext(),
                            CommonConst.KEY_CASE_LOCATION_3_FILE, path);
                }
            }
        }
    };

    public void showSaveCaseInfoDialog(final Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(context.getResources().getString(R.string.msg_case_info_confirm));
        builder.setMessage(CommonConst.getCaseInfo(context));
        builder.setNegativeButton(context.getResources().getString(R.string.cancel),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //onBackPressed();
                        CommonConst.setCaseSaveOK(context, false);
                    }
                });
        builder.setPositiveButton(context.getResources().getString(R.string.save),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //startActivity(new Intent(AppActivity.this, CreateActivity.class));
                        CommonConst.saveToDataBase(context);
                        CommonConst.setCaseSaveOK(context, true);
                        Toast.makeText(context,
                                context.getResources().getString(R.string.case_save_done),
                                Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(CreateCrimeActivityP2.this, AppActivity.class));
                    }
                });
        builder.show();
    }
}
