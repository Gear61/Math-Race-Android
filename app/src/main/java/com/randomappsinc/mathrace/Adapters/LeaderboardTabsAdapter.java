package com.randomappsinc.mathrace.Adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.randomappsinc.mathrace.Fragments.LeaderboardFragment;
import com.randomappsinc.mathrace.R;
import com.randomappsinc.mathrace.Utils.Constants;
import com.randomappsinc.mathrace.Utils.MyApplication;


/**
 * Created by alexanderchiou on 12/8/15.
 */
public class LeaderboardTabsAdapter extends FragmentStatePagerAdapter {
    public String[] runTypes;

    public LeaderboardTabsAdapter (FragmentManager fragmentManager) {
        super(fragmentManager);
        runTypes = MyApplication.get().getApplicationContext().getResources().getStringArray(R.array.run_types);
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.RUN_TYPE_KEY, runTypes[position]);
        LeaderboardFragment leaderboardFragment = new LeaderboardFragment();
        leaderboardFragment.setArguments(bundle);
        return leaderboardFragment;
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
