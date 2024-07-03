package com.qtonz.module;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ads.qtonz.ads.QtonzAd;
import com.ads.qtonz.ads.wrapper.ApInterstitialAd;
import com.ads.qtonz.ads.wrapper.ApNativeAd;
import com.ads.qtonz.funtion.AdCallback;
import com.ads.qtonz.funtion.RewardCallback;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;

import org.jetbrains.annotations.Nullable;

public class MbitActivity extends AppCompatActivity {

    AppCompatButton btnLoadBannerAd;
    AppCompatButton btnLoadBannerAdCollapsing;
    AppCompatButton btnLoadNativeAd;
    AppCompatButton btnPreLoadLoadNativeAd;
    AppCompatButton btnPreLoadLoadInersial;
    AppCompatButton btnLoadLoadInersial;
    AppCompatButton btnPreLoadRewadAdShow;
    AppCompatButton btnthreeTapToInter;
    AppCompatButton btnNativeLarge;

    private ApInterstitialAd mInterstitialAd;

    private RewardedAd rewardedAds;
    private boolean isEarn = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mbit);
        btnLoadBannerAdCollapsing = findViewById(R.id.btnLoadBannerAdCollapsing);
        btnLoadBannerAd = findViewById(R.id.btnLoadBannerAd);
        btnLoadNativeAd = findViewById(R.id.btnLoadNativeAd);
        btnPreLoadLoadNativeAd = findViewById(R.id.btnPreLoadLoadNativeAd);
        btnPreLoadLoadInersial = findViewById(R.id.btnPreLoadLoadInersial);
        btnLoadLoadInersial = findViewById(R.id.btnLoadLoadInersial);
        btnPreLoadRewadAdShow = findViewById(R.id.btnPreLoadRewadAdShow);
        btnthreeTapToInter = findViewById(R.id.btnthreeTapToInter);
        btnNativeLarge = findViewById(R.id.btnNativeLarge);

        preLoadNativeAd();
        preLoadIntersialAd();
        preRewardAdShow();

        Intent intent = new Intent(MbitActivity.this, AdsLoadActivity.class);
        btnLoadBannerAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("id", 1);
                startActivity(intent);
            }
        });

        btnLoadBannerAdCollapsing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("id", 2);
                startActivity(intent);
            }
        });

        btnLoadNativeAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("id", 3);
                startActivity(intent);
            }
        });
        btnPreLoadLoadNativeAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("id", 4);
                startActivity(intent);
            }
        });
        btnPreLoadLoadInersial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QtonzAd.getInstance().forceShowInterstitial(MbitActivity.this, mInterstitialAd, new AdCallback() {
                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
                        intent.putExtra("id", 5);
                        startActivity(intent);

                    }
                }, true);
//                QtonzAd.getInstance().forceShowInterstitial(MbitActivity.this, mInterstitialAd, new AdCallback());
//
//                intent.putExtra("id", 5);
//                startActivity(intent);
            }
        });

        btnLoadLoadInersial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                QtonzAd.getInstance().loadAndShowIntersialAd(MbitActivity.this, QtonzApp.getApplication().intterialID, new AdCallback() {
                    @Override
                    public void onNextAction() {
                        super.onNextAction();
                        intent.putExtra("id", 6);
                        startActivity(intent);
                    }

                    @Override
                    public void onAdFailedToShow(@androidx.annotation.Nullable AdError adError) {
                        super.onAdFailedToShow(adError);
                        intent.putExtra("id", 6);
                        startActivity(intent);
                    }
                });


            }
        });

        btnPreLoadRewadAdShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isEarn = false;
                QtonzAd.getInstance().showRewardAds(MbitActivity.this, rewardedAds, new RewardCallback() {
                    @Override
                    public void onUserEarnedReward(RewardItem var1) {
                        isEarn = true;
                    }

                    @Override
                    public void onRewardedAdClosed() {
                        if (isEarn) {
                            intent.putExtra("id", 7);
                            startActivity(intent);

                        }
                    }

                    @Override
                    public void onRewardedAdFailedToShow(int codeError) {

                    }

                    @Override
                    public void onAdClicked() {

                    }
                });

            }
        });

        btnthreeTapToInter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd != null) {
                    QtonzAd.getInstance().showintersialClickCount(MbitActivity.this, mInterstitialAd.getInterstitialAd(), new AdCallback() {

                        @Override
                        public void onAdClosed() {
                            super.onAdClosed();
                            intent.putExtra("id", 8);
                            startActivity(intent);
                        }
                    });
                } else {
                    intent.putExtra("id", 8);
                    startActivity(intent);
                }

            }
        });
        btnNativeLarge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("id", 9);
                startActivity(intent);
            }
        });

    }

    private void preRewardAdShow() {
        QtonzAd.getInstance().initRewardAds(this, QtonzApp.getApplication().rewardAdID, new AdCallback() {
            @Override
            public void onRewardAdLoaded(RewardedAd rewardedAd) {
                super.onRewardAdLoaded(rewardedAd);
                rewardedAds = rewardedAd;
                Toast.makeText(MbitActivity.this, "Ads Reward Ready", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void preLoadIntersialAd() {

        QtonzAd.getInstance().getInterstitialAds(MbitActivity.this, QtonzApp.getApplication().intterialID, new AdCallback() {
            @Override
            public void onApInterstitialLoad(@Nullable ApInterstitialAd apInterstitialAd) {
                super.onApInterstitialLoad(apInterstitialAd);
                mInterstitialAd = apInterstitialAd;
                Toast.makeText(MbitActivity.this, "Ads Ready", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void preLoadNativeAd() {

        QtonzAd.getInstance().loadNativeAdResultCallback(this, QtonzApp.getApplication().nativeId, R.layout.native_medium, new AdCallback() {
            @Override
            public void onNativeAdLoaded(ApNativeAd nativeAd) {
                super.onNativeAdLoaded(nativeAd);

                QtonzApp.mApNativeAd = nativeAd;
            }

            @Override
            public void onAdFailedToLoad(@Nullable LoadAdError i) {
                super.onAdFailedToLoad(i);

                QtonzApp.mApNativeAd = null;
            }

            @Override
            public void onAdFailedToShow(@Nullable AdError adError) {
                super.onAdFailedToShow(adError);

                QtonzApp.mApNativeAd = null;
            }
        });

    }
}