package com.android.newcrime;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Button;

/**
 * Created by zwb on 2017/3/5.
 */

public class CreateCrimeActivityP3 extends AppCompatActivity {
    Button mToolbarLeftButton;
    Button mToolbarRightButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.create_crime_layout_p3);

        mToolbarLeftButton = (Button)findViewById(R.id.toolbar_left_button);
        mToolbarRightButton = (Button)findViewById(R.id.toolbar_right_button);
        mToolbarLeftButton.setText(getString(R.string.save));
        mToolbarRightButton.setText(getString(R.string.cancel));

    }
}
