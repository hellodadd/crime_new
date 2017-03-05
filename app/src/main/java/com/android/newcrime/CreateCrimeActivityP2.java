package com.android.newcrime;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.android.newcrime.utils.ClearableEditText;
import com.android.newcrime.utils.CommonConst;

/**
 * Created by zwb on 2017/3/5.
 */

public class CreateCrimeActivityP2 extends AppCompatActivity {

    Button mToolbarLeftButton;
    Button mToolbarRightButton;

    Button mBottomRightButton;

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

        mIsCollectionIng = CommonConst.getPreferences(getApplicationContext(),
                CommonConst.KEY_CASE_COLLECTION_ING, false);

        mToolbarLeftButton = (Button)findViewById(R.id.toolbar_left_button);
        mToolbarRightButton = (Button)findViewById(R.id.toolbar_right_button);
        mToolbarLeftButton.setText(getString(R.string.save));
        mToolbarRightButton.setText(getString(R.string.cancel));

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
        mLocationNumCount1 = (TextView)findViewById(R.id.location1_num_count);
        mLocationCollection1 = (Button)findViewById(R.id.location_collection_1_button);
        mLocationCollection1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCollectionLocation1();
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
                mLocationNumCount2.setText(temp.length() + "/35");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mLocationNumCount2 = (TextView)findViewById(R.id.location2_num_count);
        mLocationCollection2 = (Button)findViewById(R.id.location_collection_2_button);
        mLocationCollection2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCollectionLocation2();
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
                mLocationNumCount3.setText(temp.length() + "/35");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mLocationNumCount3 = (TextView)findViewById(R.id.location3_num_count);
        mLocationCollection3 = (Button)findViewById(R.id.location_collection_3_button);
        mLocationCollection3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCollectionLocation3();
            }
        });

        if(mIsCollectionIng){
            mLocationCollection1.setClickable(false);
            mLocationCollection2.setClickable(false);
            mLocationCollection3.setClickable(false);
        }

        mBottomRightButton = (Button)findViewById(R.id.bottom_right_button);
        mBottomRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateCrimeActivityP2.this, CreateCrimeActivityP3.class));
            }
        });

    }

    /**/
    private void startCollectionLocation1(){
        mLocationCollection1.setClickable(false);
        mLocationCollection1.setText(getString(R.string.collection_runing));

        mLocationCollection2.setClickable(false);
        mLocationCollection3.setClickable(false);

        CommonConst.setPreferences(getApplicationContext(),
                CommonConst.KEY_CASE_COLLECTION_ING,false);

        Intent it=new Intent();
        it.setAction("com.kuaikan.one_key");
        it.setComponent(new ComponentName("com.kuaikan.app.scenecollection",
                "com.kuaikan.app.scenecollection.NonSimOneKeyService"));

        mContext.startService(it);
    }

    private void startCollectionLocation2(){
        mLocationCollection1.setClickable(false);

        mLocationCollection2.setText(getString(R.string.collection_runing));

        mLocationCollection2.setClickable(false);
        mLocationCollection3.setClickable(false);

        CommonConst.setPreferences(getApplicationContext(),
                CommonConst.KEY_CASE_COLLECTION_ING,false);

        Intent it=new Intent();
        it.setAction("com.kuaikan.one_key");
        it.setComponent(new ComponentName("com.kuaikan.app.scenecollection",
                "com.kuaikan.app.scenecollection.NonSimOneKeyService"));

        mContext.startService(it);
    }

    private void startCollectionLocation3(){
        mLocationCollection1.setClickable(false);

        mLocationCollection2.setText(getString(R.string.collection_runing));

        mLocationCollection2.setClickable(false);
        mLocationCollection3.setClickable(false);

        CommonConst.setPreferences(getApplicationContext(),
                CommonConst.KEY_CASE_COLLECTION_ING,false);

        Intent it=new Intent();
        it.setAction("com.kuaikan.one_key");
        it.setComponent(new ComponentName("com.kuaikan.app.scenecollection",
                "com.kuaikan.app.scenecollection.NonSimOneKeyService"));

        mContext.startService(it);
    }
}
