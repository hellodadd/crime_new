package com.android.newcrime;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.android.newcrime.utils.CommonConst;

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
                if(!CommonConst.isCaseSaveOk(getApplicationContext())) {
                    showMsgAlertDialog();
                }else {
                    startActivity(new Intent(AppActivity.this, CreateActivity.class));
                }
            }
        });

        mListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AppActivity.this, ListActivity.class));
            }
        });

    }

    private void showMsgAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.msg_alert));
        builder.setMessage(getResources().getString(R.string.msg_alert_content));
        builder.setNegativeButton(getResources().getString(R.string.msg_alert_continue),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(AppActivity.this, CreateActivity.class);
                        intent.putExtra("continue",true);
                        startActivity(intent);
                    }
                });
        builder.setPositiveButton(getResources().getString(R.string.msg_alert_new_create),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(AppActivity.this, CreateActivity.class));
                    }
                });
        builder.show();
    }
}
