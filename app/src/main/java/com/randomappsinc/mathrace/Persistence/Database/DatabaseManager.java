package com.randomappsinc.mathrace.Persistence.Database;

import android.content.Context;

import com.randomappsinc.mathrace.Utils.MyApplication;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by alexanderchiou on 12/13/15.
 */
public class DatabaseManager {
    private static DatabaseManager instance;

    public static DatabaseManager get() {
        if (instance == null) {
            instance = getSync();
        }
        return instance;
    }

    private static synchronized DatabaseManager getSync() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    private Realm realm;

    private DatabaseManager() {
        Context context = MyApplication.get().getApplicationContext();
        realm = Realm.getInstance(context);
    }

    public boolean didThisRun(long runId) {
        return realm.where(RunDO.class).equalTo("id", runId).findFirst() != null;
    }

    public void addRun(RunDO run) {
        try {
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(run);
            realm.commitTransaction();
        }
        catch (Exception e) {
            realm.cancelTransaction();
        }
    }

    public List<RunDO> getHistory() {
        RealmResults<RunDO> history = realm.where(RunDO.class).findAll();
        history.sort("id", Sort.DESCENDING);
        return history;
    }
}
