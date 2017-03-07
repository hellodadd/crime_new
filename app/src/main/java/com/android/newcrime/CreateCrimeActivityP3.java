package com.android.newcrime;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by zwb on 2017/3/5.
 */

public class CreateCrimeActivityP3 extends AppCompatActivity {
    ImageView mToolbarLeftButton;
    ImageView mToolbarRightButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.create_crime_layout_p3);

        mToolbarLeftButton = (ImageView)findViewById(R.id.toolbar_left_button);
        mToolbarRightButton = (ImageView)findViewById(R.id.toolbar_right_button);
    }
}
