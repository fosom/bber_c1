package com.bber.company.android.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bber.company.android.R;
import com.bber.company.android.constants.Constants;
import com.bber.company.android.tools.SharedPreferencesUtils;
import com.bber.company.android.widget.MyToast;

import java.net.Inet4Address;


/***
 * 我的钱包  充值体现
 */
public class MyWalletActivity extends BaseAppCompatActivity implements View.OnClickListener {

    private TextView HistoryList;
    private RelativeLayout rl_recharge,rl_bank_card;
    private double money;
    private TextView my_balance;


    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_mywallet;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        setListeners();
        initData();

    }

    private void initViews() {
        HistoryList = (TextView) toolbar.findViewById(R.id.btn_change);
        rl_recharge = (RelativeLayout) findViewById(R.id.rl_recharge);
        rl_bank_card = (RelativeLayout) findViewById(R.id.rl_bank_card);
        my_balance = (TextView) findViewById(R.id.my_balance);
    }

    private void setListeners() {
        HistoryList.setOnClickListener(this);
        rl_recharge.setOnClickListener(this);
        rl_bank_card.setOnClickListener(this);
    }

    private void initData() {
        title.setText(R.string.my_wallet);
        HistoryList.setVisibility(View.VISIBLE);
        HistoryList.setText(R.string.my_bill_detail);
//        money = getIntent().getDoubleExtra("money",0);
        String userMoney = (String) SharedPreferencesUtils.get(this, Constants.USER_MONEY,"");
        money = Double.parseDouble(userMoney);
        my_balance.setText(money+"");
    }



    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_change:
                intent = new Intent(MyWalletActivity.this,PayBillListActivity.class);
                startActivity(intent);
                break;
            //游戏币充值
            case R.id.rl_recharge:
                intent = new Intent(MyWalletActivity.this,PayInputMoneyActivity.class);
                startActivity(intent);
                break;

        }
    }
}
