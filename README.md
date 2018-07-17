[![Donate](https://img.shields.io/badge/Donate-PayPal-green.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=3MJE3M4FMJYGN&lc=BR&item_name=Shin%2dNiL%27s%20Github&item_number=Github&currency_code=USD&bn=PP%2dDonationsBF%3abtn_donate_SM%2egif%3aNonHosted)

# GodotHeyzap
[Heyzap](https://www.heyzap.com/) Module for [Godot Game Engine](https://godotengine.org/) (Android only by while)

![Screenshot](/images/screenshot.png)


Compiling
-------------

1. Copy the 'heyzap' folder from the root of this repository and its contents and put it inside of the modules folder inside of godot source code directory       
2. [compile the godot source for android](http://docs.godotengine.org/en/stable/reference/compiling_for_android.html);

Avaliable Functions:
---------------------
    init(instance_id, publisher_id)
        - integer instanceId => use get_instance_ID()
        - String publisher_id => your Heyzap publisher id
    startTestActivity()
    loadBanner(onTop)
        - Boolean onTop => if it's true, load the banner on top of screen, else load on the bottom
    showBanner()
    hideBanner()
    displayInterstitial()
    fetchVideoAd()
    isVideoAdAvailable() Boolean
    displayVideoAd()
    fetchIncentivizedAd()
    isIncentivizedAdAvailable() Boolean
    displayIncentivizedAd()
   


Callback Functions:
---------------------
    _on_network_callback(network, event)
        - String network => Network name, eg: "heyzap", "facebook", "unityads", "applovin", "vungle", "chartboost", "adcolony", "admob", "hyprmx" 
        - String event => Event name, eg: "initialized", "show", "available", "hide", "fetch_failed", "click", "dismiss", "incentivized_result_complete", "incentivized_result_incomplete", "audio_starting", "audio_finished", "banner-loaded", "banner-click", "banner-hide", "banner-dismiss", "banner-fetch_failed", "leave_application", "display_failed" 
    _on_banner_loaded()
    _on_banner_error()
    _on_interstitial_hide()
    _on_interstitial_available()
    _on_insterstitial_failed_to_fetch()
    _on_video_hide()
    _on_video_available()
    _on_video_failed_to_fetch()
    _on_incentivized_hide()
    _on_incentivized_available()
    _on_incentivized_failed_to_fetch()
    _on_incentivized_complete()
    _on_incentivized_incomplete()

Sample Code
-----------
Please don't forget to check the demo project to see how the things works on the GDScript side.
You can find a quick tutorial [here](https://shinnil.blogspot.com.br/2017/03/tutorial-using-heyzap-godot-module.html).


Known Issues
------------
Due to some technical difficulties the following networks wasn't linked in this module:
- Adcolony;
- Tapjoy;
- Vungle.