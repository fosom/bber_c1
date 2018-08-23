package com.bber.company.android.util;


import com.amap.api.services.core.PoiItem;

import java.util.List;

/**
 * Author: Bruce
 * Date: 2016/7/7
 * Version:
 * Describe:
 */
public interface InterfaceMap {
    public void  getPosSerchDate(List<PoiItem> poiItems, int type);
    public void  getClickLatLonPoint(double latitude, double longitude);
    public void  getLocation(String privince, String city,String district,double lat,double lng);
    public void  noLocation();
}
