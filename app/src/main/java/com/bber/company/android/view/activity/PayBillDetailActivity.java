package com.bber.company.android.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bber.company.android.R;
import com.bber.company.android.bean.DpPayBean;
import com.bber.company.android.constants.Constants;
import com.bber.company.android.http.HttpUtil;
import com.bber.company.android.tools.Tools;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;


public class PayBillDetailActivity extends BaseAppCompatActivity{

    private DpPayBean dpPayBean;
    private TextView tv_our_order_id,tv_dp_order_id,tv_order_money,tv_order_paymoney;
    private TextView tv_order_status,tv_order_time,tv_order_method;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_paybill_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();
        setListeners();
        initData();
        
    }

    private void initViews() {
        tv_our_order_id = (TextView) findViewById(R.id.tv_our_order_id);
        tv_dp_order_id = (TextView) findViewById(R.id.tv_dp_order_id);
        tv_order_money = (TextView) findViewById(R.id.tv_order_money);
        tv_order_paymoney = (TextView) findViewById(R.id.tv_order_paymoney);
        tv_order_status = (TextView) findViewById(R.id.tv_order_status);
        tv_order_time = (TextView) findViewById(R.id.tv_order_time);
        tv_order_method = (TextView) findViewById(R.id.tv_order_method);

    }

    private void setListeners() {
    }

    private void initData() {
        title.setText(R.string.bill_detail);
        dpPayBean = (DpPayBean) getIntent().getBundleExtra("data").getSerializable("dpbean");

        tv_our_order_id.setText(dpPayBean.getPayCode());
        tv_dp_order_id.setText(dpPayBean.getDpCode());
        tv_order_money.setText("¥"+dpPayBean.getMoney());
        tv_order_paymoney.setText("¥"+dpPayBean.getActualMoney());
        tv_order_status.setText(getStatusString(dpPayBean.getStatus()));
        tv_order_time.setText(Tools.timeStamp2Date(dpPayBean.getCreateDate(),"yyyy-MM-dd HH:mm:ss"));
        tv_order_method.setText(getPayString(dpPayBean.getCode()));

    }

    /**
     * 获取支付状态
     */
    private String getStatusString(int status){
        String returnValue;
        int type = dpPayBean.getType();
        String typeName = "充值";
        if (type == 16) {
            typeName = "提现";
        }
        if (dpPayBean.getDpStatus() != 1) {
            return typeName + "失败";
        }
        switch (status){
            case 1:
                returnValue = typeName  + "成功";
                break;
            case 2:
                returnValue = typeName  + "失败";
                break;
            case 3:
                returnValue = typeName  + "异常";
                break;
            default:
                returnValue = typeName  + "中";
                break;
        }
        return returnValue;
    }


    /**
     * 获取支付状态
     */
    private String getPayString(int type){
        String returnValue = "其他方式";
        switch (type){
            case 30:
                returnValue = "支付宝";
                break;
            case 40:
                returnValue = "微信";
                break;
            case 51:
                returnValue = "银联";
                break;
        }
        return returnValue;
    }


}
