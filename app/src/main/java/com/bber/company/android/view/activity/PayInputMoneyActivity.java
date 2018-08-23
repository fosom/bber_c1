package com.bber.company.android.view.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bber.company.android.R;
import com.bber.company.android.constants.Constants;
import com.bber.company.android.tools.JsonUtil;
import com.bber.company.android.tools.SharedPreferencesUtils;
import com.bber.company.android.util.CLog;
import com.bber.company.android.view.customcontrolview.DialogView;
import com.bber.company.android.widget.MyToast;
import com.loopj.android.http.RequestParams;

public class PayInputMoneyActivity extends BaseAppCompatActivity implements View.OnClickListener{

    private EditText et_money_card_text;
    private EditText et_money_pin_text;
    private Button btn_payNext;
    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_payinput;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        setListeners();
        initData();
    }

    private void initViews() {
        title = toolbar.findViewById(R.id.title);
        btn_payNext = findViewById(R.id.btn_payNext);
        et_money_card_text = findViewById(R.id.et_money_card);
        et_money_pin_text = findViewById(R.id.et_money_pin);
    }

    private void setListeners() {
        btn_payNext.setOnClickListener(this);
    }

    private void initData() {
        title.setText("充值");
        et_money_card_text.setText("");
        et_money_card_text.setEnabled(true);
        et_money_pin_text.setText("");
        et_money_pin_text.setEnabled(true);
    }

    /**
     * 充值
     * 购买VIP
     * *
     */
    private void payCMoney(String cardno, String cardpin) {
        CLog.i("payCMoney:" + cardno+", "+cardpin);
        if (!netWork.isNetworkConnected()) {
            MyToast.makeTextAnim(this, R.string.no_network, 0, R.style.PopToast).show();
            return;
        }

        DialogView.show(PayInputMoneyActivity.this, true);
        RequestParams params = new RequestParams();
        final JsonUtil jsonUtil = new JsonUtil(this);
        String head = jsonUtil.httpHeadToJson(this);
        final String userId = SharedPreferencesUtils.get(this, Constants.USERID, "") + "";
        params.put("head", head);
        params.put("buyerUserId", userId);
        params.put("cardno", cardno);
        params.put("cardpin", cardpin);

        /*
        HttpUtil.post(Constants.getInstance().payCMoney, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, final JSONObject jsonObject) {
                try {
                    JSONObject dataCollection = jsonObject.getJSONObject("dataCollection");
                    CLog.i( "支付返回的dataCollection : " + dataCollection.toString());
                    String status = dataCollection.getString("status");
                    String massage = dataCollection.getString("massage");
                    if ("1".equals(status)) {

                    } else {
                        MyToast.makeTextAnim(PayInputMoneyActivity.this, massage, 0, R.style.PopToast).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                MyToast.makeTextAnim(PayInputMoneyActivity.this, R.string.getData_fail, 0, R.style.PopToast).show();
            }

            @Override
            public void onFinish() { // 完成后调用，失败，成功，都要调用
                DialogView.dismiss(PayInputMoneyActivity.this);
            }
        });
        */
    }



    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_payNext:
                final String strCard = et_money_card_text.getText().toString();
                final String strCardpin = et_money_pin_text.getText().toString();

                if (strCard.length() != 18) {
                    MyToast.makeTextAnim(this, R.string.err_pay_cardwrong, 0, R.style.PopToast).show();
                    return;
                }
                if (strCardpin.length() != 6) {
                    MyToast.makeTextAnim(this, R.string.err_pay_cardwrong, 0, R.style.PopToast).show();
                    return;
                }
                payCMoney(strCard, strCardpin);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
