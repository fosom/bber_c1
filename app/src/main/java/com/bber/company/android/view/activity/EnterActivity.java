package com.bber.company.android.view.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.bber.company.android.R;
import com.bber.company.android.app.AppManager;
import com.bber.company.android.app.MyApplication;
import com.bber.company.android.constants.Constants;
import com.bber.company.android.http.HttpUtil;
import com.bber.company.android.listener.HomeWatcher;
import com.bber.company.android.network.NetWork;
import com.bber.company.android.tools.FlurryTypes;
import com.bber.company.android.tools.JsonUtil;
import com.bber.company.android.tools.SharedPreferencesUtils;
import com.bber.company.android.tools.Tools;
import com.bber.company.android.util.CLog;
import com.bber.company.android.widget.MyToast;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 登陆注册
 */
public class EnterActivity extends Activity implements View.OnClickListener {

    private TextView btn_enter;
    private Button btn_login;
    private MyApplication app;
    private int inviteCodeSwitch = 0;
    private int forciblyInput = 0;
    private int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;

    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 隐藏应用程序的标题栏，即当前activity的标题栏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_enter);
        app = MyApplication.getContext();
        app.allActivity.add(this);
        AppManager.getAppManager().addActivity(this);
        initViews();
        setListeners();
        initData();
    }

    private void initViews() {
        btn_enter = findViewById(R.id.btn_enter);
        btn_login = findViewById(R.id.btn_login);
    }


    private void setListeners() {
        btn_enter.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    private void initData() {
        getInviteCodeSwitch();
        HomeWatcher.mCamHomeKeyDown = false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        FlurryTypes.onStartSession(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        FlurryTypes.onEndSession(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_enter:
                if (inviteCodeSwitch == 1) {
                    intent = new Intent(EnterActivity.this, RegFirActivity.class);
                    intent.putExtra("city", city);
                    intent.putExtra("forciblyInput", forciblyInput);
                    startActivity(intent);
                } else {
                    intent = new Intent(EnterActivity.this, RegistereActivity.class);
                    intent.putExtra("city", city);
                    startActivity(intent);
                }
                break;
            case R.id.btn_login:
                intent = new Intent(EnterActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void getInviteCodeSwitch() {
        RequestParams params = new RequestParams();
        String head = new JsonUtil(this).httpHeadToJson(this);
        params.put("head", head);
        params.put("type", 1);
        HttpUtil.post(Constants.getInstance().getInviteCodeSwitch, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, final JSONObject jsonObject) {
                if (Tools.jsonResult(EnterActivity.this, jsonObject, null)) {
                    return;
                }
                try {
                    JSONObject dataCollection = jsonObject.getJSONObject("dataCollection");
                    inviteCodeSwitch = dataCollection.getInt("inviteCodeSwitch");
                    forciblyInput = dataCollection.getInt("forciblyInput");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                MyToast.makeTextAnim(EnterActivity.this, R.string.getData_fail, 0, R.style.PopToast).show();
            }

            @Override
            public void onFinish() {
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            app.exitApp();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
