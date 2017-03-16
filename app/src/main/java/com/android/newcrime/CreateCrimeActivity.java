package com.android.newcrime;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.android.newcrime.utils.ClearableEditText;
import com.android.newcrime.utils.CommonConst;
import com.android.newcrime.utils.DateTimePicker;

import java.util.Calendar;

/**
 * Created by zwb on 2017/3/5.
 */

public class CreateCrimeActivity extends AppCompatActivity {

    Toolbar mToolbar;
    ImageView mLeftButton;
    ImageView mRightButton;//save
    Button mTimeStartDatePicker;
    Button mTimeStartTimePicker;
    Button mTimeEndDatePicker;
    Button mTimeEndTimePicker;

    ClearableEditText mGpsInputEdit;
    TextView mGpsNameCount;
    Button mGpsLocationButton;

    Button mNextButton;
    Button mPrevButton;

    private Calendar mCalendar;

    private boolean mIsContinue;

    public AMapLocationClient mLocationClient = null;
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    if(mGpsLocationButton != null && !isGpsLocationDone(getApplicationContext())){
                        mGpsLocationButton.setText(amapLocation.getLatitude() + "N" + "," +
                                amapLocation.getLongitude() + "E");
                        CommonConst.setPreferences(getApplicationContext(),
                                CommonConst.KEY_CASE_GPS_LAT, amapLocation.getLatitude() + "N");
                        CommonConst.setPreferences(getApplicationContext(),
                                CommonConst.KEY_CASE_GPS_LON, amapLocation.getLongitude() + "E");
                    }
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                    mGpsLocationButton.setText(getString(R.string.gps_location_fail));
                }
            }

        }
    };
    public AMapLocationClientOption mLocationOption = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.create_crime_laytou);

        mCalendar = Calendar.getInstance();

        if(getIntent() != null){
            mIsContinue = getIntent().getBooleanExtra("continue",false);
        }else {
            mIsContinue = false;
        }

        mLocationClient = new AMapLocationClient(getApplicationContext());
        mLocationClient.setLocationListener(mLocationListener);
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Device_Sensors);
        mLocationOption.setOnceLocationLatest(true);
        mLocationOption.setNeedAddress(true);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();


        mToolbar = (Toolbar) findViewById(R.id.toolbar_create_crime);
        mToolbar.setTitle(getString(R.string.create_crime));

        mLeftButton = (ImageView) findViewById(R.id.toolbar_left_button);
        mLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mRightButton = (ImageView) findViewById(R.id.toolbar_right_button);
        mRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mGpsInputEdit.getText() != null){
                    CommonConst.setPreferences(getApplicationContext(),
                            CommonConst.KEY_CASE_GPS_NAME,mGpsInputEdit.getText());
                    //insert to db;
                    Toast.makeText(getApplicationContext(),
                            getResources().getString(R.string.msg_alert_save),Toast.LENGTH_LONG).show();
                }
            }
        });

        long timeStart = CommonConst.getPreferences(getApplicationContext(),
                CommonConst.KEY_CASE_START_TIME,mCalendar.getTimeInMillis() - 60 * 60000);
        long timeEnd = CommonConst.getPreferences(getApplicationContext(),
                CommonConst.KEY_CASE_END_TIME,mCalendar.getTimeInMillis() - 30 * 60000);
        mTimeStartDatePicker = (Button) findViewById(R.id.crime_time_start_picker_date);
        if(mIsContinue) {
            mTimeStartDatePicker.setText(DateTimePicker.getCurrentDate(timeStart));
        }else{
            mTimeStartDatePicker.setText(DateTimePicker.getCurrentDate(mCalendar.getTimeInMillis() - 60 * 60000));
        }
        mTimeStartDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(mTimeStartDatePicker, 0);
            }
        });

        mTimeStartTimePicker = (Button) findViewById(R.id.crime_time_start_picker_time);
        if(mIsContinue) {
            mTimeStartTimePicker.setText(DateTimePicker.getCurrentTime(timeStart));
        }else {
            mTimeStartTimePicker.setText(DateTimePicker.getCurrentTime(mCalendar.getTimeInMillis() - 60 * 60000));
        }
        mTimeStartTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(mTimeStartTimePicker, 1);
            }
        });

        mTimeEndDatePicker = (Button) findViewById(R.id.crime_time_end_picker_date);
        if(mIsContinue) {
            mTimeEndDatePicker.setText(DateTimePicker.getCurrentDate(timeEnd));
        }else {
            mTimeEndDatePicker.setText(DateTimePicker.getCurrentDate(mCalendar.getTimeInMillis() - 30 * 60000));
        }
        mTimeEndDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(mTimeEndDatePicker, 2);
            }
        });

        mTimeEndTimePicker = (Button) findViewById(R.id.crime_time_end_picker_time);
        if(mIsContinue){
            mTimeEndTimePicker.setText(DateTimePicker.getCurrentTime(timeEnd));
        }else {
            mTimeEndTimePicker.setText(DateTimePicker.getCurrentTime(mCalendar.getTimeInMillis() - 30 * 60000));
        }
        mTimeEndTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(mTimeEndTimePicker, 3);
            }
        });

        mGpsInputEdit = (ClearableEditText) findViewById(R.id.gps_input_editview);
        mGpsInputEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(35)});
        mGpsInputEdit.addTextChangedListener(mTextWatch);

        mGpsNameCount = (TextView) findViewById(R.id.gps_name_count);

        String gpsName = CommonConst.getPreferences(getApplicationContext(),
                CommonConst.KEY_CASE_GPS_NAME, "");
        if(gpsName != null && !gpsName.isEmpty()){
            mGpsInputEdit.setText(gpsName);
        }
        mGpsLocationButton = (Button) findViewById(R.id.gps_info_button);
        if(isGpsLocationDone(getApplicationContext())){
            String lat = CommonConst.getPreferences(getApplicationContext(), CommonConst.KEY_CASE_GPS_LAT, "");
            String lon = CommonConst.getPreferences(getApplicationContext(), CommonConst.KEY_CASE_GPS_LON, "");
            mGpsLocationButton.setText(lat + "," + lon);
        }else {
            mGpsLocationButton.setText(getResources().getString(R.string.gps_location_ing));
        }

        mNextButton = (Button) findViewById(R.id.bottom_right_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonConst.setPreferences(getApplicationContext(),
                        CommonConst.KEY_CASE_GPS_NAME, mGpsInputEdit.getText());
                startActivity(new Intent(CreateCrimeActivity.this, CreateCrimeActivityP2.class));
            }
        });

        mPrevButton = (Button)findViewById(R.id.bottom_left_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    protected void onDestroy(){
        super.onDestroy();
    }


    private TextWatcher mTextWatch = new TextWatcher() {
        private String temp;

        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
            temp = charSequence.toString();
            mGpsNameCount.setText(temp.length() + "/35");
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

    private boolean isGpsLocationDone(Context context){
        String lat = CommonConst.getPreferences(context, CommonConst.KEY_CASE_GPS_LAT, "");
        String lon = CommonConst.getPreferences(context, CommonConst.KEY_CASE_GPS_LON, "");
        if(lat == null || lat.isEmpty()){
            return false;
        }

        if(lon == null || lat.isEmpty()){
            return false;
        }

        return true;
    }

    private void showDateTimeDialog(final Button timeButton, final int type) {
        // Create the dialog
        final Dialog mDateTimeDialog = new Dialog(this);
        // Inflate the root layout
        final RelativeLayout mDateTimeDialogView = (RelativeLayout) getLayoutInflater().inflate(R.layout.date_time_dialog, null);
        // Grab widget instance
        final DateTimePicker mDateTimePicker = (DateTimePicker) mDateTimeDialogView.findViewById(R.id.DateTimePicker);

        if (type == 0 || type == 2) {
            mDateTimePicker.clickDate();
        } else {
            mDateTimePicker.clickTime();
        }

        // Update demo TextViews when the "OK" button is clicked
        ((Button) mDateTimeDialogView.findViewById(R.id.SetDateTime)).setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                mDateTimePicker.clearFocus();
                // TODO Auto-generated method stub
                long time = mDateTimePicker.get().getTimeInMillis();
                if (type == 0 || type == 2) {
                    timeButton.setText(DateTimePicker.getCurrentDate(time));
                } else {
                    timeButton.setText(DateTimePicker.getCurrentTime(time));
                }

                if(type == 0){
                    CommonConst.setPreferences(getApplicationContext(),
                            CommonConst.KEY_CASE_START_TIME, time);
                }else if(type == 1){
                    CommonConst.setPreferences(getApplicationContext(),
                            CommonConst.KEY_CASE_START_TIME, time);
                }else if(type == 2){
                    CommonConst.setPreferences(getApplicationContext(),
                            CommonConst.KEY_CASE_END_TIME, time);
                }else if(type == 3){
                    CommonConst.setPreferences(getApplicationContext(),
                            CommonConst.KEY_CASE_END_TIME, time);
                }
                mDateTimeDialog.dismiss();
            }
        });

        // Cancel the dialog when the "Cancel" button is clicked
        ((Button) mDateTimeDialogView.findViewById(R.id.CancelDialog)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mDateTimeDialog.cancel();
            }
        });

        // Reset Date and Time pickers when the "Reset" button is clicked
        ((Button) mDateTimeDialogView.findViewById(R.id.ResetDateTime)).setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                mDateTimePicker.reset();
            }
        });

        // No title on the dialog window
        mDateTimeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Set the dialog content view
        mDateTimeDialog.setContentView(mDateTimeDialogView);
        // Display the dialog
        mDateTimeDialog.show();
    }

}
