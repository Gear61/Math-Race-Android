package com.randomappsinc.mathrace.Utils;

import android.app.Application;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.joanzapata.iconify.fonts.IoniconsModule;

/**
 * Created by alexanderchiou on 12/5/15.
 */
public final class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Iconify.with(new FontAwesomeModule())
               .with(new IoniconsModule());
        instance = this;
    }

    public static MyApplication instance;

    public static MyApplication get() {
        return instance;
    }
}
