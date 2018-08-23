package com.bber.company.android.view.fragment;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bber.company.android.R;
import com.bber.company.android.bean.GlodBean;
import com.bber.company.android.bean.Level;
import com.bber.company.android.bean.MarqueeBean;
import com.bber.company.android.constants.Constants;
import com.bber.company.android.http.HttpUtil;
import com.bber.company.android.network.NetWork;
import com.bber.company.android.tools.JsonUtil;
import com.bber.company.android.tools.SharedPreferencesUtils;
import com.bber.company.android.tools.Tools;
import com.bber.company.android.util.CLog;
import com.bber.company.android.util.TimeUtil;
import com.bber.company.android.view.activity.KeyActivity;
import com.bber.company.android.view.activity.MainActivity;
import com.bber.company.android.view.activity.MyVIPActivity;
import com.bber.company.android.widget.MarqueeFactory;
import com.bber.company.android.widget.MarqueeView;
import com.bber.company.android.widget.MyToast;
import com.bber.company.android.widget.NoticeMFE;
import com.facebook.drawee.view.SimpleDraweeView;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


/**
 * Author: Bruce
 * Date: 2016/5/9
 * Version:
 * Describe:
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {

    protected NetWork netWork;
    private View view;
    private ImageView image_preferce;
    private TextView history_list, key_count, user_meau;
    private int buyerId;
    private MainActivity mainActivity;
    private MarqueeView marqueeView;
    private MarqueeFactory<TextView, String> marqueeFactory1;
    private TextView icon_point;
    private List<String> datas;

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String type = intent.getStringExtra("type");
            if (action.equals(Constants.ACTION_HOME)) {
                if ("addkey".equals(type)) {
                    int addkey = Tools.StringToInt(intent.getStringExtra("addKey"));
                    ((MainActivity) getActivity()).setUserAddTotalKey(addkey);
                }
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null);
        mainActivity = (MainActivity) getActivity();
        initViews();
        try {
            initData();
        } catch (Exception e) {
            CLog.i("发送错误信息" + e.getMessage());
            if (netWork == null) {

            }
        }
        //比较当前时间天是否过了0点
        compreTime();
        setListeners();
        CLog.i("onCreateView.HomeFragment");
        return view;
    }

    private void compreTime() {
        String yesterday = (String) SharedPreferencesUtils.get(mainActivity, "yesterday", "");
        TimeUtil timeUtil = new TimeUtil();
        //如果是今天
        if (!Tools.isEmpty(yesterday)) {
            if (timeUtil.getJudgetoDay(yesterday)) {
                icon_point.setVisibility(View.GONE);
            } else {
                icon_point.setVisibility(View.VISIBLE);
            }
        }
    }

    private void initViews() {
        toolbar = view.findViewById(R.id.toolbar);
        title = toolbar.findViewById(R.id.title);
        user_meau = toolbar.findViewById(R.id.user_meau);
        key_count = view.findViewById(R.id.my_key);

        history_list = toolbar.findViewById(R.id.history_list);
        image_preferce = view.findViewById(R.id.image_pre);
        marqueeView = view.findViewById(R.id.marqueeView);
        icon_point = view.findViewById(R.id.icon_point);
    }


    public void initData() {
        netWork = new NetWork(mainActivity);
        title.setText(R.string.app_chinse_name);
        buyerId = Tools.StringToInt(SharedPreferencesUtils.get(mainActivity, Constants.USERID, "-1") + "");

        //获取level相关信息
        getLevelInfo();
        getMarquee();
        registerBoradcastReceiver();
    }

    private void setListeners() {
        history_list.setOnClickListener(this);
        key_count.setOnClickListener(this);
        user_meau.setOnClickListener(this);
        image_preferce.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.history_list:
                break;
            case R.id.my_key:
                Intent intent = new Intent(mainActivity, MyVIPActivity.class);
                startActivity(intent);
                break;
            case R.id.user_meau:
                mainActivity.toggle();
                break;
            case R.id.image_pre:
                icon_point.setVisibility(View.GONE);
                TimeUtil time = new TimeUtil();
                SharedPreferencesUtils.put(mainActivity, "yesterday", time.getTime());

                Bundle bundle = new Bundle();
                String invitecode = (String) SharedPreferencesUtils.get(getActivity(), Constants.INVITECODE, "");
                bundle.putString("inviteCode", invitecode);
                intent = new Intent(getActivity(), KeyActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    public void getMarquee() {
        if (!netWork.isNetworkConnected()) {
            MyToast.makeTextAnim(mainActivity, R.string.no_network, 0, R.style.PopToast).show();
            return;
        }
        RequestParams params = new RequestParams();
        params.put("content-type", "application/x-www-form-urlencoded");
        final JsonUtil jsonUtil = new JsonUtil(mainActivity);
        String head = jsonUtil.httpHeadToJson(mainActivity);
        params.put("head", head);
        CLog.i("head" + head);
        HttpUtil.post(Constants.getInstance().getMarquee, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, final JSONObject jsonObject) {
                try {
                    String resultCode = jsonObject.getString("resultCode");
                    CLog.i("跑马灯: " + jsonObject.toString());
                    /*
                    {"currentPage":0,"dataCollection":[
	{"createTime":1527085347000,"endTime":1556986925000,"id":1026,"isPush":0,"marqueeCity":"ALL","marqueeContent":"充值终身会员福利多多，至尊享受VIP权益。","marqueeType":0,"sellerId":"","sellers":[],"startTime":1525406400000},
  {"createTime":1527011910000,"endTime":1558532722000,"id":1039,"isPush":0,"marqueeCity":"ALL","marqueeContent":"联系在线客服支付宝扫码充值，方便快捷！","marqueeType":0,"sellerId":"","sellers":[],"startTime":1526996712000}
  ],
  "resultCode":1,"resultMessage":"{\"vipGetWay\":{\"code\":0,\"url\":\"\"},\"pcPayDomain\":\"www.xxoopapa.net\"}","totalPage":0}
                     */
                    //1表示成功 0表示失败
                    if (resultCode.equals("1")) {
                        JSONArray dataCollection = jsonObject.getJSONArray("dataCollection");

                        String jsonObject1 = jsonObject.getString("resultMessage");
                        JSONObject jsonObject2 = new JSONObject(jsonObject1);

                        String vipGetWay = jsonObject2.getString("vipGetWay");
                        JsonUtil JsonUtil = new JsonUtil(getActivity());
                        GlodBean glodBean = JsonUtil.jsonToGlodBeanBean(vipGetWay);

                        String code = glodBean.getCode();
                        String url = glodBean.getUrl();
                        CLog.i("code" + code);
                        CLog.i("url" + url);
                        SharedPreferencesUtils.put(getActivity(), "code", code);
                        SharedPreferencesUtils.put(getActivity(), "url", url);

                        //跑马灯的文字内容
                        String data = dataCollection.toString();
                        final List<MarqueeBean> listMarqueeBean = jsonUtil.jsonToMarqueeBean(data);
                        datas = new ArrayList<String>();
                        datas.clear();
                        for (int j = 0; j < listMarqueeBean.size(); j++) {
                            datas.add(listMarqueeBean.get(j).marqueeContent);
                        }
                        marqueeFactory1 = new NoticeMFE(getActivity());
                        marqueeView.setMarqueeFactory(marqueeFactory1);
                        marqueeFactory1.setOnItemClickListener(new MarqueeFactory.OnItemClickListener<TextView, String>() {
                            @Override
                            public void onItemClickListener(MarqueeFactory.ViewHolder<TextView, String> holder) {
                                Toast.makeText(getActivity(), holder.data, Toast.LENGTH_SHORT).show();
                            }
                        });
                        marqueeFactory1.setData(datas);
                        marqueeView.startFlipping();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                CLog.i("getMarquee发送错误信息返回了了服务器错误" + throwable.getMessage());
                MyToast.makeTextAnim(mainActivity, R.string.getData_fail, 0, R.style.PopToast).show();
            }

            @Override
            public void onFinish() {

            }
        });
    }

    /**
     * 获取level相关信息
     */
    private void getLevelInfo() {
        if (!netWork.isNetworkConnected()) {
            MyToast.makeTextAnim(mainActivity, R.string.no_network, 0, R.style.PopToast).show();
            return;
        }
        RequestParams params = new RequestParams();
        final JsonUtil jsonUtil = new JsonUtil(mainActivity);
        String head = jsonUtil.httpHeadToJson(mainActivity);
        params.put("head", head);
        HttpUtil.get(Constants.getInstance().getSellerLevel, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, final JSONObject jsonObject) {

            }

            @Override
            public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                CLog.i("getLevelInfo发送错误信息返回了了服务器错误" + throwable.getMessage());
                MyToast.makeTextAnim(mainActivity, R.string.getData_fail, 0, R.style.PopToast).show();
            }

            @Override
            public void onFinish() {
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            CLog.i("onActivityResult发送错误信息返回了了服务器错误");
            if (data != null) {
                boolean updateIcon = data.getBooleanExtra("updateIcon", false);
                if (updateIcon) {
                    mainActivity.invalidateOptionsMenu();
                }
            }
        }
    }


    public void registerBoradcastReceiver() {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction(Constants.ACTION_HOME);
        getActivity().registerReceiver(mBroadcastReceiver, myIntentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


}
