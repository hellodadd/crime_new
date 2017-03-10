package com.android.newcrime;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
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

import org.w3c.dom.Text;

import java.util.Calendar;

import javax.xml.datatype.Duration;

import static com.android.newcrime.utils.CommonConst.initPreferences;

public class CreateActivity extends AppCompatActivity {

    Toolbar  mToolbar;
    ImageView mToolbarLeftButton;
    ImageView   mToolbarRightButton;
    TextView mToolbarTitile;
    Button   mCreateCrimeButton;
    ClearableEditText mNameEditText;
    TextView mNameCount;

    private Context mContext;
    private CrimeProvider mCrimeProvider;

    private boolean mIsContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();
        mCrimeProvider = new CrimeProvider(mContext);

        if(getIntent() != null){
            mIsContinue = getIntent().getBooleanExtra("continue", false);
        }else{
            mIsContinue = false;
        }

        if(!mIsContinue) {
            initPreferences(mContext);
        }

        CommonConst.setCaseSaveOK(mContext, false);

        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        mToolbarTitile = (TextView) findViewById(R.id.toolbar_title);
        mToolbarTitile.setText(getString(R.string.create_crime));
        mToolbarLeftButton = (ImageView) findViewById(R.id.toolbar_left_button);
        mToolbarRightButton = (ImageView)findViewById(R.id.toolbar_right_button);
        mToolbarRightButton.setVisibility(View.GONE);
        mToolbarLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mNameEditText = (ClearableEditText)findViewById(R.id.input_crime_name_edit);
        mNameEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(35)});
        mNameCount = (TextView)findViewById(R.id.name_number_count);

        String caseName = CommonConst.getPreferences(getApplicationContext(),
                CommonConst.KEY_CASE_NAME,"");
        if(caseName == null || caseName.isEmpty()) {
            mNameEditText.setText(DateTimePicker.getCurrentDate(Calendar.getInstance().getTimeInMillis()));
        }else{
            mNameEditText.setText(caseName);
        }
        mNameEditText.addTextChangedListener(mTextWatch);

        mNameCount.setText(mNameEditText.getText().length() + "/35");



        mCreateCrimeButton = (Button)findViewById(R.id.create_crime_button);
        if(mIsContinue){
            mCreateCrimeButton.setText(getString(R.string.next_step));
        }
        mCreateCrimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               createNewCase();
                //mCrimeProvider.createBaseMsgXml("d8818f0feaec4c049eff8c6e0ba796ba");
            }
        });
    }

    private void createNewCase(){
        if(mNameEditText.getText() == null || mNameEditText.getText().isEmpty()){
            Toast.makeText(mContext, getString(R.string.case_name_empty), Toast.LENGTH_SHORT).show();
        }else {
            CommonConst.setPreferences(getApplicationContext(),
                    CommonConst.KEY_CASE_NAME,mNameEditText.getText());
            Intent intent = new Intent(CreateActivity.this, com.android.newcrime.CreateCrimeActivity.class);
            intent.putExtra("continue", mIsContinue);
            startActivity(intent);
        }
    }

    private TextWatcher mTextWatch = new TextWatcher(){
        private String temp;

        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
            temp = charSequence.toString();
            mNameCount.setText(temp.length() + "/35");
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };
}
