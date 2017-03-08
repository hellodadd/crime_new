package com.android.newcrime;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.newcrime.utils.CommonConst;

import java.util.ArrayList;

/**
 * Created by server on 17-3-7.
 */

public class CollectionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ArrayList<String> result= (ArrayList<String>) intent.getStringArrayListExtra("result");


        String file_path = (String) intent.getStringExtra("file_path");
        String uuid = (String) intent.getStringExtra("uuid");
        String path = CommonConst.copyToInternalPath(context, file_path);
        Log.d("Anita","uuid from another service = "+uuid);
        Log.d("Anita","file_path from another service = "+file_path);
    }
}
