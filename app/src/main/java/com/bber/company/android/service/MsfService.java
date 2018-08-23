package com.bber.company.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.bber.company.android.constants.Constants;
import com.bber.company.android.tools.SharedPreferencesUtils;
import com.bber.company.android.tools.Tools;
import com.bber.company.android.util.CLog;
/**
 */
public class MsfService extends Service {

    private static MsfService mInstance = null;
    private final IBinder binder = new MyBinder();
    public String mUserName, mPassword;
    private boolean isLogin = true;//false:表示注册， true:表示登录
    public static MsfService getInstance() {

        return mInstance;

    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        String userid = SharedPreferencesUtils.get(this, Constants.USERID, "-1") + "";
        this.mUserName = userid + "buyer";
        this.mPassword = Tools.md5("c" + mUserName);
        mInstance = this;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        CLog.i( "onStartCommand");
        if (intent != null) {
            this.isLogin = intent.getBooleanExtra("isLogin", true);
            String userid = SharedPreferencesUtils.get(this, Constants.USERID, "-1") + "";
            this.mUserName = userid + "buyer";
            this.mPassword = Tools.md5("c" + mUserName);
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        CLog.i( "---- service onDestroy");
        super.onDestroy();
    }

    public class MyBinder extends Binder {
        public MsfService getService() {
            return MsfService.this;
        }
    }

}
