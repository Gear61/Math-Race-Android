package com.randomappsinc.mathrace.Utils;

import android.app.Application;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.joanzapata.iconify.fonts.IoniconsModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by alexanderchiou on 12/5/15.
 */
public final class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Iconify.with(new FontAwesomeModule())
               .with(new IoniconsModule());

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);

        instance = this;
    }

    public static MyApplication instance;

    public static MyApplication get() {
        return instance;
    }
}
