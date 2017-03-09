package com.android.newcrime;

import android.content.Context;

import com.android.newcrime.databases.CrimeProvider;

/**
 * Created by zwb on 2017/3/9.
 */

public class DataInitial {
    private Context mContext = null;

    public DataInitial(Context context){
        this.mContext = context;
    }

    //Command 11
    public boolean CreateBaseMsg(){
        CrimeProvider mCrimeProvider = new CrimeProvider(mContext);
        mCrimeProvider.createScenesInfoXml();
        return true;
    }
}
