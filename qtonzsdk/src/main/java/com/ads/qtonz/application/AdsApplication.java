package com.ads.qtonz.application;

import android.app.Application;

import com.ads.qtonz.config.QtonzAdConfig;
import com.ads.qtonz.util.AppUtil;
import com.ads.qtonz.util.SharePreferenceUtils;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public abstract class AdsApplication extends Application {

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
