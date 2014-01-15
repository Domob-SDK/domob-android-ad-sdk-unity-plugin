package cn.domob.android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import cn.domob.android.ads.DomobAdEventListener;
import cn.domob.android.ads.DomobAdManager.ErrorCode;
import cn.domob.android.ads.DomobAdView;
import cn.domob.android.ads.DomobInterstitialAd;
import cn.domob.android.ads.DomobInterstitialAdListener;

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

public class UnityActivity extends UnityPlayerActivity {

	private DomobAdView mAdview320x50;
	private DomobAdView mAdviewFlexibleAdView;
	private DomobInterstitialAd mInterstitialAd;
	
	private Context mContext = null;

	private String mKeyword;
	private String mUserGender;
	private String mUserBirthdayStr;
	private String mUserPostcode;
	private String mDefaultCamera;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		mKeyword = null;
		mUserGender = null;
		mUserBirthdayStr = null;
		mUserPostcode = null;
		mDefaultCamera = "Main Camera";
	}
	
	public void setCamera(String camera){
		mDefaultCamera = camera;
	}
	
	public void setKeyword(String paramKeyword) {
		mKeyword = paramKeyword;
	}

	public void setUserGender(String paramUserGender) {
		mUserGender = paramUserGender;
	}

	public void setUserBirthdayStr(String paramUserBirthdayStr) {
		mUserBirthdayStr = paramUserBirthdayStr;
	}

	public void setUserPostcode(String paramUserPostcode) {
		mUserPostcode = paramUserPostcode;
	}

	// ad sdk banner
	public void addBannerAd(final String paramPublisherID,
			final String paramInlinePPID,
			final String paramDomobAdView_INLINE_SIZE,final String isTop) {

		((Activity) mContext).runOnUiThread(new Runnable() {

			@Override
			public void run() {
				Log.i("----->>>>addBannerAd", "paramPublisherID:"
						+ paramPublisherID + "paramInlinePPID:"
						+ paramInlinePPID
						+ "paramDomobAdView_INLINE_SIZE:"
						+ paramDomobAdView_INLINE_SIZE);
				mAdview320x50 = new DomobAdView(mContext, paramPublisherID,
						paramInlinePPID, paramDomobAdView_INLINE_SIZE);
				if (mKeyword != null && !mKeyword.equals("")
						&& mKeyword.length() != 0) {
					mAdview320x50.setKeyword(mKeyword);
				}
				if (mUserBirthdayStr != null && !mUserBirthdayStr.equals("")
						&& mUserBirthdayStr.length() != 0) {
					mAdview320x50.setUserBirthdayStr(mUserBirthdayStr);
				}

				if (mUserGender != null && !mUserGender.equals("")
						&& mUserGender.length() != 0) {
					mAdview320x50.setUserGender(mUserGender);
				}

				if (mUserPostcode != null && !mUserPostcode.equals("")
						&& mUserPostcode.length() != 0) {
					mAdview320x50.setUserPostcode(mUserPostcode);
				}

				mAdview320x50.setAdEventListener(new DomobAdEventListener() {

					@Override
					public void onDomobAdReturned(DomobAdView adView) {
						Log.i("DomobSDKDemo", "onDomobAdReturned");
						UnityPlayer.UnitySendMessage(mDefaultCamera,
								"onDomobAdReturned", "");
					}

					@Override
					public void onDomobAdOverlayPresented(
							DomobAdView adView) {
						Log.i("DomobSDKDemo", "overlayPresented");
						UnityPlayer.UnitySendMessage(mDefaultCamera,
								"onDomobAdOverlayPresented", "");
					}

					@Override
					public void onDomobAdOverlayDismissed(
							DomobAdView adView) {
						Log.i("DomobSDKDemo", "Overrided be dismissed");
						UnityPlayer.UnitySendMessage(mDefaultCamera,
								"onDomobAdOverlayDismissed", "");
					}

					@Override
					public void onDomobAdClicked(DomobAdView arg0) {
						Log.i("DomobSDKDemo", "onDomobAdClicked");
						UnityPlayer.UnitySendMessage(mDefaultCamera,
								"onDomobAdClicked", "");
					}

					@Override
					public void onDomobAdFailed(DomobAdView arg0,
							ErrorCode arg1) {
						Log.i("DomobSDKDemo", "onDomobAdFailed");
						UnityPlayer.UnitySendMessage(mDefaultCamera,
								"onDomobAdFailed", "");
					}

					@Override
					public void onDomobLeaveApplication(DomobAdView arg0) {
						Log.i("DomobSDKDemo", "onDomobLeaveApplication");
						UnityPlayer.UnitySendMessage(mDefaultCamera,
								"onDomobLeaveApplication", "");
					}

					@Override
					public Context onDomobAdRequiresCurrentContext() {
						return mContext;
					}
				});
				
				RelativeLayout mLayout = new RelativeLayout(mContext);
				RelativeLayout.LayoutParams mLayoutParams = new RelativeLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				mLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
				if(isTop.equals("isTop")){
					mLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);		
				}else{
					mLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
				}
				mAdview320x50.setLayoutParams(mLayoutParams);
				mLayout.addView(mAdview320x50);
				addContentView(mLayout, new LinearLayout.LayoutParams(
						LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));

			}
		});
	}

	public void addFlexibleBannerAd(final String paramPublisherID,
			final String paramFlexibleInlinePPID, final String isTop) {

		((Activity) mContext).runOnUiThread(new Runnable() {

			@Override
			public void run() {
				Log.i("----->>>>addFlexibleBannerAd", "paramPublisherID:"
						+ paramPublisherID + "paramFlexibleInlinePPID:"
						+ paramFlexibleInlinePPID + "isTop:" + isTop);
				mAdviewFlexibleAdView = new DomobAdView(mContext,
						paramPublisherID, paramFlexibleInlinePPID,
						DomobAdView.INLINE_SIZE_FLEXIBLE);

				if (mKeyword != null && !mKeyword.equals("")
						&& mKeyword.length() != 0) {
					mAdview320x50.setKeyword(mKeyword);
				}
				if (mUserBirthdayStr != null && !mUserBirthdayStr.equals("")
						&& mUserBirthdayStr.length() != 0) {
					mAdview320x50.setUserBirthdayStr(mUserBirthdayStr);
				}

				if (mUserGender != null && !mUserGender.equals("")
						&& mUserGender.length() != 0) {
					mAdview320x50.setUserGender(mUserGender);
				}

				if (mUserPostcode != null && !mUserPostcode.equals("")
						&& mUserPostcode.length() != 0) {
					mAdview320x50.setUserPostcode(mUserPostcode);
				}

				mAdviewFlexibleAdView
						.setAdEventListener(new DomobAdEventListener() {

							@Override
							public void onDomobAdReturned(DomobAdView adView) {
								Log.i("DomobSDKDemo", "onDomobAdReturned");
								UnityPlayer.UnitySendMessage(mDefaultCamera,
										"onDomobFlexibleAdReturned", "");
							}

							@Override
							public void onDomobAdOverlayPresented(
									DomobAdView adView) {
								Log.i("DomobSDKDemo", "overlayPresented");
								UnityPlayer.UnitySendMessage(mDefaultCamera,
										"onDomobFlexibleAdOverlayPresented", "");
							}

							@Override
							public void onDomobAdOverlayDismissed(
									DomobAdView adView) {
								Log.i("DomobSDKDemo", "Overrided be dismissed");
								UnityPlayer.UnitySendMessage(mDefaultCamera,
										"onDomobFlexibleAdOverlayDismissed", "");
							}

							@Override
							public void onDomobAdClicked(DomobAdView arg0) {
								Log.i("DomobSDKDemo", "onDomobAdClicked");
								UnityPlayer.UnitySendMessage(mDefaultCamera,
										"onDomobFlexibleAdClicked", "");
							}

							@Override
							public void onDomobAdFailed(DomobAdView arg0,
									ErrorCode arg1) {
								Log.i("DomobSDKDemo", "onDomobAdFailed");
								UnityPlayer.UnitySendMessage(mDefaultCamera,
										"onDomobFlexibleAdFailed", "");
							}

							@Override
							public void onDomobLeaveApplication(DomobAdView arg0) {
								Log.i("DomobSDKDemo", "onDomobLeaveApplication");
								UnityPlayer.UnitySendMessage(mDefaultCamera,
										"onDomobFlexibleLeaveApplication", "");
							}

							@Override
							public Context onDomobAdRequiresCurrentContext() {
								return mContext;
							}
						});

				RelativeLayout mLayout = new RelativeLayout(mContext);
				RelativeLayout.LayoutParams mLayoutParams = new RelativeLayout.LayoutParams(
						LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
				if(isTop.equals("isTop")){
					mLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
				}else{
					mLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
				}
				mAdviewFlexibleAdView.setLayoutParams(mLayoutParams);
				mLayout.addView(mAdviewFlexibleAdView);
				addContentView(mLayout, new LinearLayout.LayoutParams(
						LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			}
		});
	}

	public void addInterstitialAd(final String paramPublisherID,
			final String paramInterstitialPPID,
			final String paramDomobInterstitialAd_INTERSITIAL_SIZE) {

		((Activity) mContext).runOnUiThread(new Runnable() {

			@Override
			public void run() {
				mInterstitialAd = new DomobInterstitialAd(mContext,
						paramPublisherID, paramInterstitialPPID,
						paramDomobInterstitialAd_INTERSITIAL_SIZE);
				mInterstitialAd
						.setInterstitialAdListener(new DomobInterstitialAdListener() {
							@Override
							public void onInterstitialAdReady() {
								Log.i("DomobSDKDemo", "onAdReady");
								mInterstitialAd.showInterstitialAd(mContext);
								UnityPlayer.UnitySendMessage(mDefaultCamera,
										"onInterstitialAdReady", "");
							}

							@Override
							public void onLandingPageOpen() {
								Log.i("DomobSDKDemo", "onLandingPageOpen");
								UnityPlayer.UnitySendMessage(mDefaultCamera,
										"onLandingPageOpen", "");
							}

							@Override
							public void onLandingPageClose() {
								Log.i("DomobSDKDemo", "onLandingPageClose");
								UnityPlayer.UnitySendMessage(mDefaultCamera,
										"onLandingPageClose", "");
							}

							@Override
							public void onInterstitialAdPresent() {
								Log.i("DomobSDKDemo", "onInterstitialAdPresent");
								UnityPlayer.UnitySendMessage(mDefaultCamera,
										"onInterstitialAdPresent", "");
							}

							@Override
							public void onInterstitialAdDismiss() {
								mInterstitialAd.loadInterstitialAd();
								Log.i("DomobSDKDemo", "onInterstitialAdDismiss");
								UnityPlayer.UnitySendMessage(mDefaultCamera,
										"onInterstitialAdDismiss", "");
							}

							@Override
							public void onInterstitialAdFailed(ErrorCode arg0) {
								Log.i("DomobSDKDemo", "onInterstitialAdFailed");
								UnityPlayer.UnitySendMessage(mDefaultCamera,
										"onInterstitialAdFailed", "");
							}

							@Override
							public void onInterstitialAdLeaveApplication() {
								Log.i("DomobSDKDemo",
										"onInterstitialAdLeaveApplication");
								UnityPlayer.UnitySendMessage(mDefaultCamera,
										"onInterstitialAdLeaveApplication", "");
								

							}

							@Override
							public void onInterstitialAdClicked(
									DomobInterstitialAd arg0) {
								Log.i("DomobSDKDemo", "onInterstitialAdClicked");
								UnityPlayer.UnitySendMessage(mDefaultCamera,
										"onInterstitialAdClicked", "");
							}
						});

				mInterstitialAd.loadInterstitialAd();

			}
		});
	}
}