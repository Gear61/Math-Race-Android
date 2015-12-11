package com.randomappsinc.mathrace.Activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.randomappsinc.mathrace.R;
import com.randomappsinc.mathrace.Utils.Constants;

import butterknife.ButterKnife;

/**
 * Created by alexanderchiou on 12/10/15.
 */
public class RaceActivity extends StandardActivity {
    private String runType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.race);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        runType = getIntent().getStringExtra(Constants.RUN_TYPE_KEY);
        setTitle(runType + getString(R.string.race));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.race_menu, menu);
        menu.findItem(R.id.start_new_race).setIcon(
                new IconDrawable(this, FontAwesomeIcons.fa_refresh)
                        .colorRes(R.color.white)
                        .actionBarSize());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.start_new_race) {
        }
        return super.onOptionsItemSelected(item);
    }
}
