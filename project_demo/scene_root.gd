extends Node

var heyzap = null
var publisher_id = "YOUR HEYZAP PUBLISHER ID"

func _ready():
	if(Globals.has_singleton("Heyzap")):
		heyzap = Globals.get_singleton("Heyzap")
		heyzap.init(get_instance_ID(), publisher_id)


#callbacks
func _on_network_callback(network, event):
	print(network + " - " + event)

func _on_banner_loaded():
	print("_on_banner_loaded")
	
func _on_banner_error():
	print("_on_banner_error")
	
func _on_interstitial_hide():
	print("_on_interstitial_hide")
	
func _on_interstitial_available():
	print("_on_interstitial_available")

func _on_insterstitial_failed_to_fetch():
	print("_on_insterstitial_failed_to_fetch")
	
func _on_video_hide():
	print("_on_video_hide")
	
func _on_video_available():
	print("_on_video_available")
	
func _on_video_failed_to_fetch():
	print("_on_video_failed_to_fetch")
	
func _on_incentivized_hide():
	print("_on_incentivized_hide")
	
func _on_incentivized_available():
	print("_on_incentivized_available")
	
func _on_incentivized_failed_to_fetch():
	print("_on_incentivized_failed_to_fetch")
	
func _on_incentivized_complete():
	print("_on_incentivized_complete")
	
func _on_incentivized_incomplete():
	print("_on_incentivized_incomplete")

#######

func _on_button_pressed():
	if heyzap:
		heyzap.startTestActivity();


func _on_button1_pressed():
	if heyzap:
		heyzap.loadBanner(false);


func _on_button2_pressed():
	if heyzap:
		heyzap.showBanner();


func _on_button3_pressed():
	if heyzap:
		heyzap.hideBanner();


func _on_button4_pressed():
	if heyzap:
		heyzap.displayInterstitial();


func _on_button5_pressed():
	if heyzap:
		if heyzap.isVideoAdAvailable():
			heyzap.displayVideoAd()
			heyzap.fetchVideoAd()


func _on_button6_pressed():
	if heyzap:
		if heyzap.isIncentivizedAdAvailable():
			heyzap.displayIncentivizedAd()
			heyzap.fetchIncentivizedAd()


func _on_button7_pressed():
	if heyzap:
		heyzap.fetchVideoAd();


func _on_button8_pressed():
	if heyzap:
		heyzap.fetchIncentivizedAd();
