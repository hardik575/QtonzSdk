package com.qtonz.module;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ads.qtonz.admob.AdsConsentManager;
import com.ads.qtonz.admob.AppOpenManager;
import com.ads.qtonz.ads.QtonzAd;
import com.ads.qtonz.ads.wrapper.ApNativeAd;
import com.ads.qtonz.funtion.AdCallback;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.LoadAdError;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    AdsConsentManager adsConsentManager;

    boolean isEnableUMP = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        String android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        String deviceId = md5(android_id).toUpperCase();

        if (isEnableUMP) {

            adsConsentManager = new AdsConsentManager(this);

            adsConsentManager.requestUMP(

                    true,

                    deviceId,

                    false,

                    canRequestAds -> runOnUiThread(this::loadSplash)

            );
//
//            adsConsentManager.requestUMP(
//                    canRequestAds -> runOnUiThread(this::loadSplash));

        } else {

            QtonzAd.getInstance().initAdsNetwork();

            loadSplash();

        }


    }

    public String md5(final String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
        }
        return "";
    }
    private void loadSplash() {

        preLoadNativeAd();

        AppOpenManager.getInstance().loadOpenAppAdSplash(
                this,
                "ca-app-pub-3940256099942544/9257395921",
                5000,
                20000,
                false,
                new AdCallback() {
                    @Override
                    public void onAdSplashReady() {
                        super.onAdSplashReady();

                        Log.e("EEEEEEE", "==>" + "onAdSplashReady");

                        if (isDestroyed() || isFinishing()) return;
                        showAdsOpenAppSplash();
                    }

                    @Override
                    public void onNextAction() {
                        super.onNextAction();
                        Log.e("EEEEEEE", "==>" + "onNextAction");
                        if (isDestroyed() || isFinishing()) return;
                        QtonzApp.getApplication().isAdCloseSplash.postValue(true);
                        navigateToNextScreen();
                    }

                    @Override
                    public void onAdFailedToLoad(@Nullable LoadAdError i) {
                        super.onAdFailedToLoad(i);
                        Log.e("EEEEEEE", "==>" + "onAdFailedToLoad");
                        if (isDestroyed() || isFinishing()) return;
                        QtonzApp.getApplication().isAdCloseSplash.postValue(true);
                        navigateToNextScreen();
                    }
                }
        );


//        QtonzAd.getInstance().loadSplashInterstitialAds(this, BuildConfig.ad_interstitial_splash, 25000, 5000, new AdCallback() {
//            @Override
//            public void onNextAction() {
//                super.onNextAction();
//                startActivity(new Intent(SplashActivity.this, MbitActivity.class));
//                finish();
//            }
//        });
    }

    private void showAdsOpenAppSplash() {
        Log.e("AAAA", "showAdsOpenAppSplash");
        AppOpenManager.getInstance().showAppOpenSplash(
                this,
                new AdCallback() {
                    @Override
                    public void onNextAction() {
                        super.onNextAction();
                        if (isDestroyed() || isFinishing()) return;
                        navigateToNextScreen();
                    }

                    @Override
                    public void onAdFailedToShow(@Nullable AdError adError) {
                        super.onAdFailedToShow(adError);
                        if (isDestroyed() || isFinishing()) return;
                        QtonzApp.getApplication().isAdCloseSplash.postValue(true);
                        navigateToNextScreen();
                    }

                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
                        if (isDestroyed() || isFinishing()) return;
                        QtonzApp.getApplication().isAdCloseSplash.postValue(true);
                        navigateToNextScreen();
                    }
                }
        );
    }

    private void navigateToNextScreen() {

        if (isDestroyed() || isFinishing()) {
            return;
        }
        startActivity(new Intent(SplashActivity.this, LangugeActivity.class));
        finish();

    }

    public void preLoadNativeAd() {

        QtonzAd.getInstance().loadNativeAdResultCallback(this, QtonzApp.getApplication().nativeId, R.layout.native_large, new AdCallback() {
            @Override
            public void onNativeAdLoaded(ApNativeAd nativeAd) {
                super.onNativeAdLoaded(nativeAd);

                QtonzApp.getApplication().nativeAdsLanguage.setValue(nativeAd);
            }

            @Override
            public void onAdFailedToLoad(@org.jetbrains.annotations.Nullable LoadAdError i) {
                super.onAdFailedToLoad(i);

                QtonzApp.getApplication().nativeAdsLanguage = null;
            }

            @Override
            public void onAdFailedToShow(@org.jetbrains.annotations.Nullable AdError adError) {
                super.onAdFailedToShow(adError);

                QtonzApp.getApplication().nativeAdsLanguage = null;
            }
        });


        QtonzAd.getInstance().loadNativeAdResultCallback(this, QtonzApp.getApplication().nativeId, R.layout.native_large, new AdCallback() {
            @Override
            public void onNativeAdLoaded(ApNativeAd nativeAd) {
                super.onNativeAdLoaded(nativeAd);

                QtonzApp.getApplication().nativeAdsLanguage2.setValue(nativeAd);
            }

            @Override
            public void onAdFailedToLoad(@org.jetbrains.annotations.Nullable LoadAdError i) {
                super.onAdFailedToLoad(i);

                QtonzApp.getApplication().nativeAdsLanguage2 = null;
            }

            @Override
            public void onAdFailedToShow(@org.jetbrains.annotations.Nullable AdError adError) {
                super.onAdFailedToShow(adError);

                QtonzApp.getApplication().nativeAdsLanguage2 = null;
            }
        });

    }
}
