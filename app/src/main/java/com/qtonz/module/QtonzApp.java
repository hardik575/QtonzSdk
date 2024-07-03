package com.qtonz.module;

import com.ads.qtonz.admob.Admob;
import com.ads.qtonz.admob.AppOpenManager;
import com.ads.qtonz.ads.QtonzAd;
import com.ads.qtonz.ads.wrapper.ApNativeAd;
import com.ads.qtonz.application.AdsMultiDexApplication;
import com.ads.qtonz.billing.AppPurchase;
import com.ads.qtonz.config.AdjustConfig;
import com.ads.qtonz.config.QtonzAdConfig;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class QtonzApp extends AdsMultiDexApplication {

    public static ApNativeAd mApNativeAd;

    public MutableLiveData<ApNativeAd> nativeAdsLanguage = new MutableLiveData<>();
    public MutableLiveData<ApNativeAd> nativeAdsLanguage2 = new MutableLiveData<>();

    public String intterialID = "ca-app-pub-3940256099942544/1033173712";
    public String bannerId = "ca-app-pub-3940256099942544/9214589741";
    public String nativeId = "ca-app-pub-3940256099942544/2247696110";
    public String bannerCollapsingId = "ca-app-pub-3940256099942544/2014213617";
    public String rewardAdID = "ca-app-pub-3940256099942544/5224354917";
    public String appopenResumeId = "ca-app-pub-3940256099942544/9257395921";

    private static QtonzApp context;
    public MutableLiveData<Boolean> isAdCloseSplash = new MutableLiveData<>();

    public static QtonzApp getApplication() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initAds();
        initBilling();
    }

    private void initAds() {
        String environment = BuildConfig.DEBUG ? QtonzAdConfig.ENVIRONMENT_DEVELOP : QtonzAdConfig.ENVIRONMENT_PRODUCTION;
        mQtonzAdConfig = new QtonzAdConfig(this, environment);

        AdjustConfig adjustConfig = new AdjustConfig(true, getString(R.string.adjust_token));
        mQtonzAdConfig.setAdjustConfig(adjustConfig);
        mQtonzAdConfig.setFacebookClientToken(getString(R.string.facebook_client_token));
        mQtonzAdConfig.setAdjustTokenTiktok(getString(R.string.tiktok_token));
        mQtonzAdConfig.setIdAdResume(appopenResumeId);

        QtonzAd.getInstance().init(this, mQtonzAdConfig);
        Admob.getInstance().setDisableAdResumeWhenClickAds(true);
        Admob.getInstance().setOpenActivityAfterShowInterAds(true);
        AppOpenManager.getInstance().disableAppResumeWithActivity(MainActivity.class);
    }

    private void initBilling() {
        List<String> listIAP = new ArrayList<>();
        listIAP.add("android.test.purchased");
        List<String> listSub = new ArrayList<>();
        AppPurchase.getInstance().initBilling(this, listIAP, listSub);
    }
}
