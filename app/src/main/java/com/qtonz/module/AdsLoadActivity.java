package com.qtonz.module;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ads.qtonz.ads.QtonzAd;
import com.ads.qtonz.funtion.AdCallback;
import com.ads.qtonz.util.AppConstant;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.LoadAdError;

import org.jetbrains.annotations.Nullable;

import static com.qtonz.module.QtonzApp.mApNativeAd;

public class AdsLoadActivity extends AppCompatActivity {

    int checkId = 0;

    View banerView;
    FrameLayout fr_ads;
    FrameLayout layoutAdNative;
    ShimmerFrameLayout shimmer_native;
    ShimmerFrameLayout shimmerContainerNative;
    TextView txtView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads_load);

        banerView = findViewById(R.id.banerView);
        fr_ads = findViewById(R.id.fr_ads);
        shimmer_native = findViewById(R.id.shimmer_native);
        txtView = findViewById(R.id.txtView);
        shimmerContainerNative = findViewById(R.id.shimmerContainerNative);
        layoutAdNative = findViewById(R.id.layoutAdNative);

        checkId = getIntent().getIntExtra("id", 0);
        if (checkId == 1) {
            banerView.setVisibility(View.VISIBLE);
            fr_ads.setVisibility(View.GONE);
            QtonzAd.getInstance().loadBanner(this, QtonzApp.getApplication().bannerId);

        } else if (checkId == 2) {
            fr_ads.setVisibility(View.GONE);

            banerView.setVisibility(View.VISIBLE);


            QtonzAd.getInstance().loadCollapsibleBanner(this, QtonzApp.getApplication().bannerCollapsingId, AppConstant.CollapsibleGravity.BOTTOM, new AdCallback());

        } else if (checkId == 3) {
            banerView.setVisibility(View.GONE);
            fr_ads.setVisibility(View.VISIBLE);

            QtonzAd.getInstance().loadNativeAd(this, QtonzApp.getApplication().nativeId, R.layout.native_medium, fr_ads, shimmer_native, new AdCallback() {
                @Override
                public void onAdFailedToLoad(@Nullable LoadAdError i) {
                    super.onAdFailedToLoad(i);
                    fr_ads.removeAllViews();
                }

                @Override
                public void onAdFailedToShow(@Nullable AdError adError) {
                    super.onAdFailedToShow(adError);
                    fr_ads.removeAllViews();
                }
            });

        } else if (checkId == 4) {
            banerView.setVisibility(View.GONE);
            fr_ads.setVisibility(View.VISIBLE);

            if (mApNativeAd != null) {
                QtonzAd.getInstance().populateNativeAdView(this, mApNativeAd, fr_ads, shimmer_native);
            }
        } else if (checkId == 5) {
            txtView.setVisibility(View.VISIBLE);
            banerView.setVisibility(View.GONE);
            fr_ads.setVisibility(View.GONE);
        } else if (checkId == 6) {
            txtView.setVisibility(View.VISIBLE);
            banerView.setVisibility(View.GONE);
            fr_ads.setVisibility(View.GONE);
        } else if (checkId == 7) {
            txtView.setText("RewardAd Loded");
            txtView.setVisibility(View.VISIBLE);
            banerView.setVisibility(View.GONE);
            fr_ads.setVisibility(View.GONE);
        } else if (checkId == 8) {
            txtView.setText("three tap to Loded ad");
            txtView.setVisibility(View.VISIBLE);
            banerView.setVisibility(View.GONE);
            fr_ads.setVisibility(View.GONE);
        } else if (checkId == 9) {

            banerView.setVisibility(View.GONE);
            fr_ads.setVisibility(View.GONE);
            layoutAdNative.setVisibility(View.VISIBLE);


            QtonzAd.getInstance().loadNativeAd(this, QtonzApp.getApplication().nativeId, R.layout.native_large, layoutAdNative, shimmerContainerNative, new AdCallback() {
                @Override
                public void onAdFailedToLoad(@Nullable LoadAdError i) {
                    super.onAdFailedToLoad(i);
                    fr_ads.removeAllViews();
                }

                @Override
                public void onAdFailedToShow(@Nullable AdError adError) {
                    super.onAdFailedToShow(adError);
                    fr_ads.removeAllViews();
                }
            });

        }
    }
}