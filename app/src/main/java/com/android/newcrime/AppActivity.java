package com.android.newcrime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Created by zwb on 2017/3/5.
 */

public class AppActivity extends AppCompatActivity {

    Button mCreateButton;
    Button mListButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);

        mCreateButton = (Button)findViewById(R.id.imageButton_create);
        mListButton = (Button)findViewById(R.id.imageButton_list);

        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AppActivity.this, CreateActivity.class));
            }
        });

    }
}
