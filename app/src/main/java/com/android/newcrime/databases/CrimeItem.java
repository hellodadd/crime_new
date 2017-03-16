package com.android.newcrime.databases;

import java.io.Serializable;
import java.io.StringReader;
import java.util.Calendar;

/**
 * Created by zwb on 2017/3/5.
 */

public class CrimeItem implements Serializable{
    private long id;
    private String mCaseId;
    private String mCaseName;
    private long mCreateTime;
    private long mCaseStartTime;
    private long mCaseEndTime;
    private String mGpsLocationName;
    private String mGpsLat;
    private String mGpsLon;
    private String mLocation1Name;
    private String mLocation1FilePath;
    private String mLocation2Name;
    private String mLocation2FilePath;
    private String mLocation3Name;
    private String mLocation3FilePath;
    private String mLocation4Name;
    private String mLocation4FilePath;
    private String mLocation5Name;
    private String mLocation5FilePath;
    private String mCaseStatus;

    public CrimeItem(){
        Calendar c = Calendar.getInstance();
        long time = c.getTimeInMillis();

        this.id = 0;
        this.mCaseId = "";
        this.mCaseName = "";
        this.mCreateTime = time;
        this.mCaseStartTime = time - 60 * 60000;
        this.mCaseEndTime = time - 30 * 60000;
        this.mGpsLocationName = "";
        this.mGpsLat = "";
        this.mGpsLon = "";
        this.mLocation1Name = "";
        this.mLocation1FilePath = "";
        this.mLocation2Name = "";
        this.mLocation2FilePath = "";
        this.mLocation3Name = "";
        this.mLocation3FilePath = "";
        this.mLocation4Name = "";
        this.mLocation4FilePath = "";
        this.mLocation5Name = "";
        this.mLocation5FilePath = "";
        this.mCaseStatus = "0";
    }

    public long getId(){
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCaseId(){
        return this.mCaseId;
    }

    public void setCaseId(String caseId){
        this.mCaseId = caseId;
    }

    public String getCaseName(){
        return this.mCaseName;
    }

    public void setCaseName(String caseName){
        this.mCaseName = caseName;
    }

    public long getCreateTime(){
        return this.mCreateTime;
    }

    public void setCreateTime(long time){
        this.mCreateTime = time;
    }

    public long getCaseStartTime(){
        return this.mCaseStartTime;
    }

    public void setCaseStartTime(long time){
        this.mCaseStartTime = time;
    }

    public long getCaseEndTime(){
        return this.mCaseEndTime;
    }

    public void setCaseEndTime(long time){
        this.mCaseEndTime = time;
    }

    public String getGpsLocationName(){
        return this.mGpsLocationName;
    }

    public void setGpsLocationName(String gpsLocationName){
        this.mGpsLocationName = gpsLocationName;
    }

    public String getGpsLat(){
        return this.mGpsLat;
    }

    public void setGpsLat(String gpsLat){
        this.mGpsLat = gpsLat;
    }

    public String getGpsLon(){
        return this.mGpsLon;
    }

    public void setGpsLon(String gpsLon){
        this.mGpsLon = gpsLon;
    }

    public String getLocation1Name(){
        return this.mLocation1Name;
    }

    public void setLocation1Name(String location1Name){
        this.mLocation1Name = location1Name;
    }

    public String getLocation1FilePath(){
        return this.mLocation1FilePath;
    }

    public void setLocation1FilePath(String path){
        this.mLocation1FilePath = path;
    }

    public String getLocation2Name(){
        return this.mLocation2Name;
    }

    public void setLocation2Name(String location2Name){
        this.mLocation2Name = location2Name;
    }

    public String getLocation2FilePath(){
        return this.mLocation2FilePath;
    }

    public void setLocation2FilePath(String path){
        this.mLocation2FilePath = path;
    }

    public String getLocation3Name(){
        return this.mLocation3Name;
    }

    public void setLocation3Name(String location3Name){
        this.mLocation3Name = location3Name;
    }

    public String getLocation3FilePath(){
        return this.mLocation3FilePath;
    }

    public void setLocation3FilePath(String path){
        this.mLocation3FilePath = path;
    }

    public String getLocation4Name(){
        return this.mLocation4Name;
    }

    public void setLocation4Name(String name){
        this.mLocation4Name = name;
    }

    public String getLocation4FilePath(){
        return this.mLocation4FilePath;
    }

    public void setLocation4FilePath(String path){
        this.mLocation4FilePath = path;
    }

    public String getLocation5Name(){
        return this.mLocation5Name;
    }

    public void setLocation5Name(String name){
        this.mLocation5Name = name;
    }

    public String getLocation5FilePath(){
        return this.mLocation5FilePath;
    }

    public void setLocation5FilePath(String path){
        this.mLocation5FilePath = path;
    }

    public String  getCaseStatus(){
        return this.mCaseStatus;
    }

    public void setCaseStatus(String status){
        this.mCaseStatus = status;
    }
}
