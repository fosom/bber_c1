package com.bber.company.android.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bber.company.android.R;
import com.bber.company.android.constants.Constants;
import com.bber.company.android.listener.IactionListener;
import com.bber.company.android.tools.SharedPreferencesUtils;
import java.text.DecimalFormat;


/***
 * 我的钱包  充值体现
 */
public class MyVIPActivity extends BaseAppCompatActivity implements View.OnClickListener ,IactionListener<Object>{

    private TextView HistoryList;
    private RelativeLayout rl_recharge,rl_bank_card,walert_relatlayout,vash_relat;
    private double money;
    private TextView my_balance;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_my_vip;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        setListeners();
        initData();
    }

    private void initViews() {
        HistoryList = toolbar.findViewById(R.id.btn_change);
        rl_recharge = findViewById(R.id.rl_recharge);

        rl_bank_card = findViewById(R.id.rl_bank_card);
        vash_relat = findViewById(R.id.vash_relat);
        walert_relatlayout = findViewById(R.id.walert_relatlayout);
        my_balance = findViewById(R.id.my_balance);
    }

    private void setListeners() {
        HistoryList.setOnClickListener(this);
        rl_recharge.setOnClickListener(this);
        rl_bank_card.setOnClickListener(this);
        vash_relat.setOnClickListener(this);
        walert_relatlayout.setOnClickListener(this);
    }

    private void initData() {
        title.setText(R.string.my_wallet);
        HistoryList.setVisibility(View.VISIBLE);
        HistoryList.setText(R.string.my_bill_detail);
        String userMoney = (String) SharedPreferencesUtils.get(this, Constants.USER_MONEY,"");
        money = Double.parseDouble(userMoney);
        String format = new DecimalFormat("0.00").format(money);
        my_balance.setText(format);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            //我的会员
            case R.id.btn_change:
                intent = new Intent(MyVIPActivity.this,PayBillListActivity.class);
                startActivity(intent);
                break;
            //购买会员
            case R.id.rl_recharge:
                intent = new Intent(MyVIPActivity.this,Buy_vipActivity.class);
                startActivity(intent);
                break;
            //钱包充值
            case R.id.walert_relatlayout:
                intent = new Intent(MyVIPActivity.this, PayInputMoneyActivity.class);
                startActivity(intent);
                break;
            //现金券
            case R.id.vash_relat:
                intent = new Intent(MyVIPActivity.this, VoucherActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void SuccessCallback(Object o) {

    }

    @Override
    public void FailCallback(String result) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
