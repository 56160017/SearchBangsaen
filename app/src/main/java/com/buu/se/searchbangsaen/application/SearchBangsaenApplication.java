package com.buu.se.searchbangsaen.application;

import android.app.Application;
import android.support.v7.appcompat.BuildConfig;
import android.util.Log;

import com.buu.se.searchbangsaen.R;
import com.buu.se.searchbangsaen.utils.ApplicationContextor;
import com.buu.se.searchbangsaen.utils.SearchBangsaenSingleton;

import java.util.UUID;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Dell on 16/01/2560.
 */

public class SearchBangsaenApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
          //  Log.i("AlexaApplication", SigningKey.getCertificateMD5Fingerprint(this));
        }
        ApplicationContextor.getInstance().setContext(this);
        SearchBangsaenSingleton.getInstance().setSessionId(UUID.randomUUID().toString().replaceAll("-", ""));
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/cloud/Cloud-Light.otf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
}
