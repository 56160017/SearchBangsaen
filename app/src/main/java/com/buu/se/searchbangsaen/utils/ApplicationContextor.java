package com.buu.se.searchbangsaen.utils;

import android.content.Context;

/**
 * Created by Dell on 17/01/2560.
 */

public class ApplicationContextor {
    private static ApplicationContextor ourInstance;
    private Context context;

    public static ApplicationContextor getInstance() {
        if (ourInstance == null) {
            ourInstance = new ApplicationContextor();
        }
        return ourInstance;
    }

    private ApplicationContextor() {
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
