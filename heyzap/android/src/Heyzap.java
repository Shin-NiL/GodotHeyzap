package org.godotengine.godot;

import android.app.Activity;
import android.util.Log;
import android.widget.FrameLayout;
import android.view.ViewGroup.LayoutParams;
import android.view.View;
import android.view.Gravity;

import com.heyzap.sdk.ads.HeyzapAds;
import com.heyzap.sdk.ads.BannerAdView;
import com.heyzap.sdk.ads.InterstitialAd;
import com.heyzap.sdk.ads.VideoAd;
import com.heyzap.sdk.ads.IncentivizedAd;
import com.heyzap.sdk.ads.HeyzapAds.NetworkCallbackListener;

import com.heyzap.sdk.ads.HeyzapAds.BannerListener;
import com.heyzap.sdk.ads.HeyzapAds.BannerError;

import com.heyzap.sdk.ads.HeyzapAds.OnStatusListener;
import com.heyzap.sdk.ads.HeyzapAds.OnIncentiveResultListener;

public class Heyzap extends Godot.SingletonBase {

    private Activity activity;
    private boolean initialized;

	private BannerAdView bannerAdView = null;
	private FrameLayout bannerLayout = null; 
    private FrameLayout.LayoutParams layoutParams = null; 
    private int instanceId = 0;

    private static final String TAG = "Heyzap";

    public void init(final int newInstanceId, final String publisherID) {
                            
        if (!initialized) {
            activity.runOnUiThread(new Runnable() {
                @Override 
                public void run() {
                    instanceId = newInstanceId;
                    HeyzapAds.start(publisherID, activity);
                    // create and add the banner view to Godot layout
                    bannerLayout = ((Godot) activity).layout;
                    bannerAdView = new BannerAdView(activity);
                    bannerLayout.addView(bannerAdView);

                    setupCallbacks();
                    initialized = true;
                    Log.d("godot", TAG + " Init");
                }
            });
        }
    }


    public void loadBanner(final boolean isOnTop) {
        activity.runOnUiThread(new Runnable() {
            @Override 
            public void run() {
                layoutParams = new FrameLayout.LayoutParams(
                            FrameLayout.LayoutParams.MATCH_PARENT,
                            FrameLayout.LayoutParams.WRAP_CONTENT                        
                );

                if (isOnTop) {
                    layoutParams.gravity = Gravity.TOP;
                } else {
                    layoutParams.gravity = Gravity.BOTTOM;
                }

                bannerAdView.setLayoutParams(layoutParams);
                bannerLayout.bringToFront();
                bannerAdView.load();
                Log.d("godot", TAG + " Load Banner");
            }
        });
    }

    public void showBanner() {
        activity.runOnUiThread(new Runnable() {
            @Override 
            public void run() {
            	if (bannerAdView.getVisibility() == View.VISIBLE) return;
				bannerAdView.setVisibility(View.VISIBLE);
                Log.d("godot", TAG + " Show Banner");    
            }
        });
    }

	public void hideBanner() {
		activity.runOnUiThread(new Runnable() {
			@Override 
            public void run() {
				if (bannerAdView.getVisibility() == View.GONE) return;
				bannerAdView.setVisibility(View.GONE);
				Log.d("godot", TAG + " Hide Banner");
			}
		});
    }

    public void displayInterstitial() {
    	activity.runOnUiThread(new Runnable() {
			@Override 
            public void run() {
                InterstitialAd.display(activity);
				Log.d("godot", TAG + " Display Interstitial");
			}
		});    
    }

    public void fetchVideoAd() {
        VideoAd.fetch();
        Log.d("godot", TAG + " Fetch VideoAd");
    }

    public boolean isVideoAdAvailable() {
        return VideoAd.isAvailable();
    }

    public void displayVideoAd() {
    	activity.runOnUiThread(new Runnable() {
			@Override 
            public void run() {
                VideoAd.display(activity);
				Log.d("godot", TAG + " Display VideoAd");
			}
		});   
    }

    public void fetchIncentivizedAd() {
        IncentivizedAd.fetch();
        Log.d("godot", TAG + " Fetch IncentivizedAd");
    }

    public boolean isIncentivizedAdAvailable() {
        return IncentivizedAd.isAvailable();
    }

    public void displayIncentivizedAd() {
    	activity.runOnUiThread(new Runnable() {
			@Override 
            public void run() {
                IncentivizedAd.display(activity);
				Log.d("godot", TAG + " Display IncentivizedAd");
			}
		});   
    }    

    public void startTestActivity() {
        HeyzapAds.startTestActivity(activity);
    }

    @Override
    protected void onMainPause() {
        if (initialized) {
            //;
        }
    }

    @Override
    protected void onMainResume() {
        if (initialized) {
            //;
        }
    }

    @Override
    protected void onMainDestroy() {
        if (initialized) {
            bannerAdView.destroy();
            super.onMainDestroy();
        }
    }



    //-- callbacks

    protected void setupCallbacks() {

        HeyzapAds.setNetworkCallbackListener(new NetworkCallbackListener() {
            @Override
            public void onNetworkCallback(String network, String event) {
                GodotLib.calldeferred(instanceId, "_on_network_callback", new Object[]{ network, event });
            }
        });

        bannerAdView.setBannerListener(new BannerListener() {
            @Override
            public void onAdClicked(BannerAdView b) {
                // The ad has been clicked by the user.
            }

            @Override
            public void onAdLoaded(BannerAdView b) {
                // The ad has been loaded.
                GodotLib.calldeferred(instanceId, "_on_banner_loaded", new Object[]{});
            }
    
            @Override
            public void onAdError(BannerAdView b, BannerError bannerError) {
                // There was an error loading the ad.
                GodotLib.calldeferred(instanceId, "_on_banner_error", new Object[]{});
            }
        });

        InterstitialAd.setOnStatusListener(new OnStatusListener() {

            @Override
            public void onShow(String tag) {
                // Ad is now showing
            }
        
            @Override
            public void onClick(String tag) {
                // Ad was clicked on. You can expect the user to leave your application temporarily.
            }

            @Override
            public void onHide(String tag) {
                // Ad was closed. The user has returned to your application.
                GodotLib.calldeferred(instanceId, "_on_interstitial_hide", new Object[]{});
            }

            @Override
            public void onFailedToShow(String tag) {
                // Display was called but there was no ad to show
            }
        
            @Override
            public void onAvailable(String tag) {
                // An ad has been successfully fetched
                GodotLib.calldeferred(instanceId, "_on_interstitial_available", new Object[]{});
            }
        
            @Override
            public void onFailedToFetch(String tag) {
                // No ad was able to be fetched
                GodotLib.calldeferred(instanceId, "_on_insterstitial_failed_to_fetch", new Object[]{});
            }

            @Override
            public void onAudioStarted() {
                // The ad about to be shown will require audio. Any background audio should be muted.
            }

            @Override
            public void onAudioFinished() {
                // The ad being shown no longer requires audio. Any background audio can be resumed.
            }
        });

        VideoAd.setOnStatusListener(new OnStatusListener() {

            @Override
            public void onShow(String tag) {
                // Ad is now showing
            }
        
            @Override
            public void onClick(String tag) {
                // Ad was clicked on. You can expect the user to leave your application temporarily.
            }

            @Override
            public void onHide(String tag) {
                // Ad was closed. The user has returned to your application.
                GodotLib.calldeferred(instanceId, "_on_video_hide", new Object[]{});
            }

            @Override
            public void onFailedToShow(String tag) {
                // Display was called but there was no ad to show
            }
        
            @Override
            public void onAvailable(String tag) {
                // An ad has been successfully fetched
                GodotLib.calldeferred(instanceId, "_on_video_available", new Object[]{});
            }
        
            @Override
            public void onFailedToFetch(String tag) {
                // No ad was able to be fetched
                GodotLib.calldeferred(instanceId, "_on_video_failed_to_fetch", new Object[]{});
            }

            @Override
            public void onAudioStarted() {
                // The ad about to be shown will require audio. Any background audio should be muted.
            }

            @Override
            public void onAudioFinished() {
                // The ad being shown no longer requires audio. Any background audio can be resumed.
            }
        });

        IncentivizedAd.setOnStatusListener(new OnStatusListener() {

            @Override
            public void onShow(String tag) {
                // Ad is now showing
            }
        
            @Override
            public void onClick(String tag) {
                // Ad was clicked on. You can expect the user to leave your application temporarily.
            }

            @Override
            public void onHide(String tag) {
                // Ad was closed. The user has returned to your application.
                GodotLib.calldeferred(instanceId, "_on_incentivized_hide", new Object[]{});
            }

            @Override
            public void onFailedToShow(String tag) {
                // Display was called but there was no ad to show
            }
        
            @Override
            public void onAvailable(String tag) {
                // An ad has been successfully fetched
                GodotLib.calldeferred(instanceId, "_on_incentivized_available", new Object[]{});
            }
        
            @Override
            public void onFailedToFetch(String tag) {
                // No ad was able to be fetched
                GodotLib.calldeferred(instanceId, "_on_incentivized_failed_to_fetch", new Object[]{});
            }

            @Override
            public void onAudioStarted() {
                // The ad about to be shown will require audio. Any background audio should be muted.
            }

            @Override
            public void onAudioFinished() {
                // The ad being shown no longer requires audio. Any background audio can be resumed.
            }
        });


        IncentivizedAd.setOnIncentiveResultListener(new OnIncentiveResultListener() {
            @Override
            public void onComplete(String tag) {
                // Give the player their reward
                GodotLib.calldeferred(instanceId, "_on_incentivized_complete", new Object[]{});
            }
        
            @Override
            public void onIncomplete(String tag) {
                // Don't give the player their reward, and tell them why
                GodotLib.calldeferred(instanceId, "_on_incentivized_incomplete", new Object[]{});
            }
        });

    }

    static public Godot.SingletonBase initialize(Activity p_activity) {
        return new Heyzap(p_activity);
    }

    public Heyzap(Activity activity) {
        registerClass("Heyzap", new String[]{
            "init",
            "startTestActivity",
            "loadBanner",
            "showBanner",
            "hideBanner",
            "displayInterstitial",
            "fetchVideoAd",
            "isVideoAdAvailable",
            "displayVideoAd",
            "fetchIncentivizedAd",
            "isIncentivizedAdAvailable",
            "displayIncentivizedAd"
        });
        this.activity = activity;
    }
}
