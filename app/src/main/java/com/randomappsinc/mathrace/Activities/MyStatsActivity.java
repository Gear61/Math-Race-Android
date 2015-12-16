package com.randomappsinc.mathrace.Activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.randomappsinc.mathrace.Adapters.MyStatsAdapter;
import com.randomappsinc.mathrace.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by alexanderchiou on 12/5/15.
 */
public class MyStatsActivity extends StandardActivity {
    @Bind(R.id.tab_layout) TabLayout statPages;
    @Bind(R.id.view_pager) ViewPager statsPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabbed_pages_layout);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        statsPager.setAdapter(new MyStatsAdapter(getFragmentManager()));
        statPages.setupWithViewPager(statsPager);
    }
}
