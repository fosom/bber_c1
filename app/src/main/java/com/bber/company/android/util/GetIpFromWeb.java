package com.bber.company.android.util;


import android.util.Log;

import com.bber.company.android.tools.AESEncryptDecrypt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Bruce
 * Date: 2016/7/5
 * Version:
 * Describe:从论坛上面抓取IP地址
 */
public class GetIpFromWeb{

    private String mContent;
    private Map IpListMap;
    private InterfaceSetIpList setIpList;

    public void getWebFromUrl(InterfaceSetIpList setIpList) {
        String tianyaBlog = "http://blog.tianya.cn/post-7262518-116383233-1.shtml";
        this.setIpList = setIpList;
        Log.i("lando_getIpFromWeb.getWebFromUrl", setIpList.toString()+"; "+tianyaBlog);
        getWebContent(tianyaBlog);
    }

    public void getWebContent(final String myUrl) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                StringBuffer sb = new StringBuffer();
                try
                {
                    java.net.URL url = new java.net.URL(myUrl);
                    BufferedReader in = new BufferedReader(new InputStreamReader(url
                            .openStream()));
                    String line;
                    while ((line = in.readLine()) != null) {
                        sb.append(line);
                    }
                    Log.i("lando_GetIPFromWeb.getWebContent", myUrl+"\r"+sb.toString());
                    in.close();
                }catch(Exception e){
                    sb.append(e.toString());
                }
                mContent = sb.toString();
                getContentIpList();
            }
        }).start();
    }

    /**
     * 从天涯论坛获取最新的IP地址。
     * {order=13.75.91.138:80,
     push-server=13.75.91.138:8011,
     im-server=23.97.75.96,
     im-file-server=13.75.91.138:8383,
     version=1.0, bberUser=13.75.91.138:80}
     */
    private void getContentIpList(){
        if (mContent != null && mContent.length() > 8) {
            int forwardIndex = mContent.indexOf(".测试数句位:");
            int backIndex = mContent.indexOf("。");
            if (forwardIndex == -1 || backIndex == -1){
                return;
            }
            String jieguo = mContent.substring(forwardIndex + 7, backIndex);
            String postStr = AESEncryptDecrypt.decode(jieguo);
            String postStrFirst = AESEncryptDecrypt.decodeByUs(postStr);
            IpListMap = transStringToMap(postStrFirst);
            String im = (String) IpListMap.get("im-server");
            String im_file = (String) IpListMap.get("im-file-server");
            String bberUser = (String) IpListMap.get("bberUser");
            String order = (String) IpListMap.get("order");
            String push_server = (String) IpListMap.get("push-server");
            String h5_server = (String) IpListMap.get("h5-server");
            String file_path = (String) IpListMap.get("file-visit-path");
            String bbergame = (String) IpListMap.get("file-bbergame-path");
            Log.i("lando_GetIpFromWeb.java->getContentIpList",IpListMap.toString());
            setIpList.setIpList(im, im_file, bberUser, order, push_server,h5_server,file_path,bbergame);

        }
    }

    /**
     * 方法名称:transStringToMap
     * 传入参数:mapString 形如 username'chenziwen^password'1234
     * 返回值:Map
     */
    public static Map transStringToMap(String mapString){
        Map<String,String> mMap = new HashMap<>();
        String[] kvs = mapString.split(",");//拆成key 和value 的组合
        for(String kv:kvs){
            mMap.put(kv.substring(0,kv.indexOf(':')),kv.substring(kv.indexOf(':')+1));
        }
        return mMap;
    }

}
