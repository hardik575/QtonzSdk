package com.ads.qtonz.application;

import androidx.multidex.MultiDexApplication;

import com.ads.qtonz.config.QtonzAdConfig;
import com.ads.qtonz.util.AppUtil;
import com.ads.qtonz.util.SharePreferenceUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class AdsMultiDexApplication extends MultiDexApplication {

    protected QtonzAdConfig mQtonzAdConfig;
    protected List<String> listTestDevice;

    @Override
    public void onCreate() {
        super.onCreate();
        listTestDevice = new ArrayList<String>();
        mQtonzAdConfig = new QtonzAdConfig(this);
        if (SharePreferenceUtils.getInstallTime(this) == 0) {
            SharePreferenceUtils.setInstallTime(this);
        }
        AppUtil.currentTotalRevenue001Ad = SharePreferenceUtils.getCurrentTotalRevenue001Ad(this);
    }


}
