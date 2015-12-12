package com.randomappsinc.mathrace.Activities;

import android.os.Bundle;

import com.randomappsinc.mathrace.R;
import com.randomappsinc.mathrace.Utils.Constants;

import butterknife.ButterKnife;

/**
 * Created by alexanderchiou on 12/11/15.
 */
public class ChooseUserTagActivity extends StandardActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_user_tag);
        ButterKnife.bind(this);
        if (!getIntent().getBooleanExtra(Constants.FIRST_TIME_KEY, true)) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
