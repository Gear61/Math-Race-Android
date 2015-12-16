package com.randomappsinc.mathrace.Activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.randomappsinc.mathrace.Adapters.LeaderboardTabsAdapter;
import com.randomappsinc.mathrace.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by alexanderchiou on 12/5/15.
 */
public class LeaderboardsActivity extends StandardActivity {
    @Bind(R.id.tab_layout) TabLayout leaderboardNames;
    @Bind(R.id.view_pager) ViewPager leaderboardPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabbed_pages_layout);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        leaderboardPager.setAdapter(new LeaderboardTabsAdapter(getFragmentManager()));
        leaderboardNames.setupWithViewPager(leaderboardPager);
    }
}
