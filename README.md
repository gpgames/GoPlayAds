# GoPlayAds
A simple Android library to cross promote your apps.
<br/>Currently includes a Dialog & Interstitial Ad fetched from a json stored on a site/server.


Primary Goal:
<br/>To keep it Simple & including Ads support like AdMob's Ad (Native, Interstitial)

## Gradle
[![Download](https://api.bintray.com/packages/goplay/maven/goplayAds/images/download.svg) ](https://bintray.com/goplay/maven/goplayAds/_latestVersion)
<br/>Adding GoPlayAds in your App - 
```gradle
dependencies {
    implementation 'com.goplay:goplayAds:1.0.01'
}
```

## Json Array Schema
Json Array Schema that you'll have to put on a server:
```json 
   {
    "apps":

     [{
       "app_title": "App Name (Dialog)",
       "app_desc": "Your App's description",
       "app_icon": "https:// URL to Icon",
       "app_header_image": "https:// URL to Header Image",
       "app_uri": "http:// URL or Package Name - com.package.name",
       "app_rating": "4.5",
       "app_cta_text": "Install",
       "app_price": "Free",
       "app_adType": "dialog"
     },

     {
       "app_interstitial_url": "IMAGE URL",
       "app_uri": "http:// URL or Package Name - com.package.name",
       "app_adType": "interstitial"
     },
      
     {
     "app_title": "App Name 2 (Native Ad)",
     "app_desc": "Your App's Description",
     "app_icon": "https:// URL to Icon",
     "app_header_image": "https:// URL to Header Image",
     "app_uri": "http:// URL or Package Name - com.package.name",
     "app_rating": "4.5",
     "app_cta_text": "Install",
     "app_price": "Free",
     "app_adType": "native"
     }]
    }
```


<br/>Some of the Assets like App Title, App Description, Icons & call to Action Text & Package Name are necessary!
<!-- <br/>Code Examples will be added later, till then you can check Sample App!-->

## GoPlayAdsDialog
<!--![Screenshot](https://github.com/gpgames/GoPlayAds/blob/master/screenshots/#.png) ![Screenshot](https://github.com/gpgames/GoPlayAds/blob/master/screenshots/#.png)-->

GoPlayAdsDialog is a Beautifully Styled Dialog which shows your Ad Assets like Header Image, App Icon, App Title & Description, Call to Action Button, Star Ratings & Price of the App.
<br/>The library internally uses `Palette API` to color the CTA Button by fetching the `Dominant Color` from Icon or Header Bitmap, whichever available.

<br/>Following is an example of GoPlayAdsDialog -     
```java
GoPlayAdsDialog goplayAds = new GoPlayAdsDialog(MainActivity.this);
goplayAds.setUrl(adURL); //URL to Json File
goplayAds.hideIfAppInstalled(true); //An App's Ad won't be shown if it is Installed on the Device.
goplayAds.setCardCorners(100); // Set CardView's corner radius.
goplayAds.setCtaCorner(100); //Set CTA Button's background radius.
goplayAds.setForceLoadFresh(false); //Fetch Json everytime loadAds() is called, true by default, if set to false, Json File's Response is kept untill App is closed!
goplayAds.showHeaderIfAvailable(false); //Show Header Image if available, true by default
goplayAds.loadAds();
```
             
<br/>You can check if the Ad is loaded via - 
```java
goplayAds.isAdLoaded();
//returns true if loaded, false otherwise!
```
    
<br/>You can also add a Listener to GoPlayAdsDialog,
```java
goplayAds.setAdListener(new AdListener() {
    @Override
    public void onAdLoadFailed() {}
    
    @Override
    public void onAdLoaded() {
        //Show AdDialog as soon as it is loaded.
        goplayAds.showAd();
    }
             
    @Override
    public void onAdClosed() {}
    
    @Override
    public void onAdShown() {}
     
    @Override
    public void onApplicationLeft() {}
});
```

**<br/>NOTE: You cannot Customize the Dialog except for the CardView's Corner Radius & CTA Button's Background Radius!**
<br/>Use `GoPlayAdsNative` instead :)

## GoPlayAdsNative
<!--![Screenshots](https://github.com/gpgames/GoPlayAds/blob/master/screenshots/#.png)-->

GoPlayAdsNative is the type of Ad where you can pass your own views which includes Ad Assets just like AdMob's `NativeAdvancedUnified`.
<br/>The `setNativeAdView()` method in `GoPlayAdsNative` accepts two types of object to specify your View containing Ad Assets.
* GoPlayAdsNativeAdView Object,
* View Object containing Ad Assets.

#### GoPlayAdsNativeView
If you use a `GoPlayAdsNativeView`, you'll need to pass the ids of the Assets (Icon, Call to Action View, Header Image etc) in a `GoPlayAdsNativeView` in their respective setter methods
and then set that object to the GoPlayAdsNative's `setNativeView()` .
<br/>Following is an example of `GoPlayAdsNativeView` -
```java
final Relativelayout adLayout = findViewById(R.id.adLayout); //Ad Assets inside a ViewGroup
adlayout.setVisibility(View.GONE):
```
```java
GoPlayAdsNativeView nativeView = new GoPlayAdsNativeView();
nativeView.setTitleView((TextView) findViewById(R.id.appinstall_headline));
nativeView.setDescriptionView((TextView) findViewById(R.id.appinstall_body));
nativeView.setIconView((ImageView) findViewById(R.id.appinstall_app_icon));
nativeView.setHeaderImageView((ImageView) findViewById(R.id.large));
nativeView.setCallToActionView(findViewById(R.id.appinstall_call_to_action));
nativeView.setPriceView((TextView) findViewById(R.id.price));
nativeView.setRatingsView((RatingBar) findViewById(R.id.rating));
``` 

### Passing a View object in GoPlayAdsNative
You can also pass a View in the `setNativeAdView()`, however there are some rules you'll need to follow!
<br/>You'll need to use the same `IDs` for your Ad Assets mentioned below - 
<table class="tg">
  <tr>
    <th class="tg-uys7">Ad Assets</th>
    <th class="tg-uys7">IDs</th>
  </tr>
  <tr>
    <td class="tg-c3ow">Header Image</td>
    <td class="tg-c3ow">goplayAds_header_image</td>
  </tr>
  <tr>
    <td class="tg-c3ow">App Icon</td>
    <td class="tg-c3ow">goplayAds_app_icon</td>
  </tr>
  <tr>
    <td class="tg-c3ow">Title</td>
    <td class="tg-c3ow">goplayAds_title</td>
  </tr>
  <tr>
    <td class="tg-c3ow">Description</td>
    <td class="tg-c3ow">goplayAds_description</td>
  </tr>
  <tr>
    <td class="tg-c3ow">Price</td>
    <td class="tg-c3ow">goplayAds_price</td>
  </tr>
  <tr>
    <td class="tg-c3ow">RatingBar</td>
    <td class="tg-c3ow">goplayAds_rating</td>
  </tr>
</table>

#### Loading GoPlayAdsNative
```java
GoPlayAdsNative goplayAdsNative = new GoPlayAdsNative(NativeAdActivity.this);
goplayAdsNative.setNativeAdView(nativeView); //GoPlayAdsNativeView Object
goplayAdsNative.setNativeNativeView(adLayout); //View Object
goplayAdsNative.setUrl(adUrl);
goplayAdsNative.setNativeAdListener(new NativeAdListener() {
    @Override
    public void onAdLoaded() {
        adLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAdLoadFailed() {
        Toast.makeText(NativeAdActivity.this, "Failed", Toast.LENGTH_SHORT).show();
    }
});
goplayAdsNative.loadAds();
```
<br/>Check if NativeAd is loaded - `goplayAdsNative.isAdLoaded();`
<br/>Additionally, you can define your own 'Call to Action' Button's action by using a `CallToActionListener`, for e.g.
```java
goplayAdsNative.setCallToActionListener(new NativeAdListener.CallToActionListener() {
            @Override
            public void onCallToActionClicked(View view) {
                //Do your Stuff!
            }
        });
```
<br/>**Note: If you don't implement the CTAListener, default implementation is used which navigates the user to PlayStore or Website depending on the passed argument to the "app_uri" object in json, when clicked.**

## GoPlayAdsInterstitial
<!--![Screenshots](https://github.com/gpgames/GoPlayAds/blob/master/screenshots/#.png)-->

GoPlayAds also supports Interstitial Ad support just like AdMob has one!
<br/>GoPlayAdsInterstitial shows an Image fetched from your Json & navigates the User to Google Play if you specified a Package Name or the Website otherwise.

<br/>Following is an example of GoPlayAdsInterstitial -
```java
final GoPlayAdsInterstitial interstitial = new GoPlayAdsInterstitial(MainActivity.this);
interstitial.setUrl(adURL);
interstitial.setAdListener(new AdListener() {
    @Override
    public void onAdLoadFailed() {}

    @Override
    public void onAdLoaded() {}

    @Override
    public void onAdClosed() {}

    @Override
    public void onAdShown() {}

    @Override
    public void onApplicationLeft() {}
});
interstitial.loadAd();
```

Just like the GoPlayAdsDialog, you can check if the Interstitial is Loaded in the same way - `interstitial.isAdLoaded();`

And show Interstitial like - `interstitial.show();`

## Clearing the Cache.
GoPlayAds uses Glide for Image Loading and Caching, therefore you should clear its cache periodically by calling the following method -
```java
GoPlayAdsHelper.clearGlideCache(MainActivity.this);
```

## ProGuard
```
-keep class org.jsoup.**
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
```
