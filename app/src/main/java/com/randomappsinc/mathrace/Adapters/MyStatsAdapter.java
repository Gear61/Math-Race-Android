package com.randomappsinc.mathrace.Adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.randomappsinc.mathrace.Fragments.MyStatsFragment;
import com.randomappsinc.mathrace.R;
import com.randomappsinc.mathrace.Utils.Constants;
import com.randomappsinc.mathrace.Utils.MyApplication;

/**
 * Created by alexanderchiou on 12/15/15.
 */
public class MyStatsAdapter extends FragmentStatePagerAdapter {
    public String[] runTypes;

    public MyStatsAdapter (FragmentManager fragmentManager) {
        super(fragmentManager);
        runTypes = MyApplication.get().getApplicationContext().getResources().getStringArray(R.array.run_types);
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.RUN_TYPE_KEY, runTypes[position]);
        MyStatsFragment myStatsFragment = new MyStatsFragment();
        myStatsFragment.setArguments(bundle);
        return myStatsFragment;
    }

    @Override
    public int getCount() {
        return runTypes.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return runTypes[position];
    }
}
