package com.partal.finalfftools.Ads;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdListener;
import com.partal.finalfftools.R;
import java.util.ArrayList;
import java.util.List;

public class MetaAds {

    private final Activity act;
    private RewardedVideoAd mRewardedAd;
    private InterstitialAd mInterstitialAd;
    public static String Banner = "372168027110470_372171870443419";
    public static String Interstitial = "423257617517282_446668848509492";
    public static String Rewarded = "372168027110470_372172547110018";
    public static String Native = "423257617517282_446668848509492";

    public interface BannerListener {
        void onLoad();
        void onClick();
        void onFailed(String reason);
    }
    public interface InterstitialListener {
        void onComplete();
        void onClick();
        void onFailed(String reason);
    }
    public interface RewardListener {
        void onComplete();
        void onClick();
        void onFailed(String reason);
    }
    public interface NativeListener {
        void onLoad();
        void onClick();
        void onFailed(String reason);
    }

    public MetaAds(Activity act) {
        this.act = act;
        AudienceNetworkAds.initialize(act);
    }

    public void setAdUnit(String banner,String interstitial,String reward,String rectangle) {
        if(!banner.equals("")) {
            Banner = banner;
        }
        if(!interstitial.equals("")) {
            Interstitial = interstitial;
        }
        if(!reward.equals("")) {
            Rewarded = reward;
        }
        if(!rectangle.equals("")) {
            Native = rectangle;
        }
    }

    public void showInterstitial() {
        mInterstitialAd = new InterstitialAd(act,Interstitial);
        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {}
            @Override
            public void onInterstitialDismissed(Ad ad) {}
            @Override
            public void onError(Ad ad, AdError adError) {}
            @Override
            public void onAdLoaded(Ad ad) {
                mInterstitialAd.show();
            }
            @Override
            public void onAdClicked(Ad ad) {}
            @Override
            public void onLoggingImpression(Ad ad) {}
        };
        mInterstitialAd.loadAd(mInterstitialAd.buildLoadAdConfig().withAdListener(interstitialAdListener).build());
    }

    void showInterstitial(InterstitialListener interstitialListener) {
        mInterstitialAd = new InterstitialAd(act,Interstitial);
        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {}
            @Override
            public void onInterstitialDismissed(Ad ad) {
                interstitialListener.onComplete();
            }
            @Override
            public void onError(Ad ad, AdError adError) {
                interstitialListener.onFailed(adError.getErrorMessage());
            }
            @Override
            public void onAdLoaded(Ad ad) {
                mInterstitialAd.show();
            }
            @Override
            public void onAdClicked(Ad ad) {
                interstitialListener.onClick();
            }
            @Override
            public void onLoggingImpression(Ad ad) {}
        };
        mInterstitialAd.loadAd(mInterstitialAd.buildLoadAdConfig().withAdListener(interstitialAdListener).build());
    }

    public void showRewarded() {
        mRewardedAd = new RewardedVideoAd(act,Rewarded);
        RewardedVideoAdListener adListener = new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoCompleted() {}
            @Override
            public void onRewardedVideoClosed() {}
            @Override
            public void onError(Ad ad, AdError adError) {}
            @Override
            public void onAdLoaded(Ad ad) {
                mRewardedAd.show();
            }
            @Override
            public void onAdClicked(Ad ad) {}
            @Override
            public void onLoggingImpression(Ad ad) {}
        };
        mRewardedAd.loadAd(mRewardedAd.buildLoadAdConfig().withAdListener(adListener).build());
    }

    public void showRewarded(RewardListener rewardListener) {
        mRewardedAd = new RewardedVideoAd(act,Rewarded);
        RewardedVideoAdListener adListener = new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoCompleted() {
                rewardListener.onComplete();
            }
            @Override
            public void onRewardedVideoClosed() {}
            @Override
            public void onError(Ad ad, AdError adError) {
                rewardListener.onFailed(adError.getErrorMessage());
            }
            @Override
            public void onAdLoaded(Ad ad) {
                mRewardedAd.show();
            }
            @Override
            public void onAdClicked(Ad ad) {
                rewardListener.onClick();
            }
            @Override
            public void onLoggingImpression(Ad ad) {}
        };
        mRewardedAd.loadAd(mRewardedAd.buildLoadAdConfig().withAdListener(adListener).build());
    }

    public void loadNative(NativeAdLayout view) {
        NativeAd nativeAd = new NativeAd(act,Native);
        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {}
            @Override
            public void onAdLoaded(Ad ad) {
                inflateAd(nativeAd,view);
            }
            @Override
            public void onAdClicked(Ad ad) {}
            @Override
            public void onLoggingImpression(Ad ad) {}
            @Override
            public void onMediaDownloaded(Ad ad) {}
        };
        nativeAd.loadAd(nativeAd.buildLoadAdConfig().withAdListener(nativeAdListener).build());
    }

    public void loadNative(NativeAdLayout view,NativeListener nativeListener) {
        NativeAd nativeAd = new NativeAd(act,Native);
        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                nativeListener.onFailed(adError.getErrorMessage());
            }
            @Override
            public void onAdLoaded(Ad ad) {
                inflateAd(nativeAd,view);
                nativeListener.onLoad();
            }
            @Override
            public void onAdClicked(Ad ad) {
                nativeListener.onClick();
            }
            @Override
            public void onLoggingImpression(Ad ad) {}
            @Override
            public void onMediaDownloaded(Ad ad) {}
        };
        nativeAd.loadAd(nativeAd.buildLoadAdConfig().withAdListener(nativeAdListener).build());
    }

    public void loadBanner(LinearLayout view) {
        AdView adView = new AdView(act,Banner,AdSize.BANNER_HEIGHT_50);
        view.addView(adView);
        adView.loadAd();
    }

    public void loadBanner(LinearLayout view,BannerListener bannerListener) {
        AdView adView = new AdView(act,Banner,AdSize.BANNER_HEIGHT_50);
        view.addView(adView);
        adView.loadAd(adView.buildLoadAdConfig().withAdListener(new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                bannerListener.onFailed(adError.getErrorMessage());
            }
            @Override
            public void onAdLoaded(Ad ad) {
                bannerListener.onLoad();
            }
            @Override
            public void onAdClicked(Ad ad) {
                bannerListener.onClick();
            }
            @Override
            public void onLoggingImpression(Ad ad) {}
        }).build());
    }


    private void inflateAd(NativeAd nativeAd, NativeAdLayout nativeAdLayout) {
        nativeAd.unregisterView();
        View adView = LayoutInflater.from(act).inflate(R.layout.meta_native,nativeAdLayout,false);
        nativeAdLayout.addView(adView);
        LinearLayout adChoicesContainer = adView.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(act, nativeAd, nativeAdLayout);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);
        MediaView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
        MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
        TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
        TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
        Button nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);
        nativeAdTitle.setText(nativeAd.getAdvertiserName());
        nativeAdBody.setText(nativeAd.getAdBodyText());
        nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
        nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
        sponsoredLabel.setText(nativeAd.getSponsoredTranslation());
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);
        nativeAd.registerViewForInteraction(adView, nativeAdMedia, nativeAdIcon, clickableViews);
    }
}
