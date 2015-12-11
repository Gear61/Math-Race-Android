package com.randomappsinc.mathrace.Activities;

import android.os.Bundle;

import com.randomappsinc.mathrace.R;

import butterknife.ButterKnife;

/**
 * Created by alexanderchiou on 12/10/15.
 */
public class EvaluationActivity extends StandardActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboards);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
