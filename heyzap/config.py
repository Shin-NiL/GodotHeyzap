def can_build(plat):
	return plat=="android"

def configure(env):
	if (env['platform'] == 'android'):
		env.android_add_java_dir("android/src")
		env.android_add_to_manifest("android/AndroidManifestChunk.xml")
		env.android_add_to_attributes("android/AndroidAttributesChunk.xml")
		env.android_add_dependency("compile 'com.google.android.gms:play-services-ads:11.+'")
		env.android_add_dependency("compile 'com.google.android.gms:play-services-location:11.+'")
		env.android_add_dependency("compile fileTree(dir: '../../../modules/heyzap/android/libs', include: '*.jar')")
		env.android_add_flat_dir("../../../modules/heyzap/android/libs")
		env.android_add_dependency("compile(name:'AudienceNetwork-4.27.1', ext:'aar')")
		env.android_add_dependency("compile(name:'fractionalmedia-sdk-1.5.1', ext:'aar')")
		env.android_add_dependency("compile(name:'fyber-sdk-8.21.0', ext:'aar')")
		env.android_add_dependency("compile(name:'mopub-sdk-4.15.0', ext:'aar')")
		env.android_add_dependency("compile(name:'mopub-sdk-banner-4.15.0', ext:'aar')")
		env.android_add_dependency("compile(name:'mopub-sdk-base-4.15.0', ext:'aar')")
		env.android_add_dependency("compile(name:'mopub-sdk-interstitial-4.15.0', ext:'aar')")
		env.android_add_dependency("compile(name:'mopub-sdk-rewardedvideo-4.15.0', ext:'aar')")
		env.android_add_dependency("compile(name:'unityads-sdk-2.2.1', ext:'aar')")
		env.android_add_dependency("compile(name:'hyprmx-sdk-4.3', ext:'aar')")

