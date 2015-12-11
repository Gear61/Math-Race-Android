package com.randomappsinc.mathrace.Activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.randomappsinc.mathrace.R;
import com.randomappsinc.mathrace.Utils.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by alexanderchiou on 12/10/15.
 */
public class RaceActivity extends StandardActivity {
    public static final String STARTING_TIME = "2:00";

    @Bind(R.id.start_race) View startRace;
    @Bind(R.id.race_layout) View raceLayout;
    @Bind(R.id.timer) TextView timer;
    @Bind(R.id.num_correct) TextView numCorrectView;
    @Bind(R.id.num_wrong) TextView numWrongView;

    private String runType;
    private int numCorrect;
    private int numWrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.race);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        runType = getIntent().getStringExtra(Constants.RUN_TYPE_KEY);
        setTitle(runType + getString(R.string.race));
    }

    @OnClick(R.id.start_race)
    public void startRace(View view) {
        startFreshRace();
    }

    public void startFreshRace() {
        startRace.setVisibility(View.GONE);
        raceLayout.setVisibility(View.VISIBLE);
        timer.setText(STARTING_TIME);
        numCorrect = 0;
        numWrong = 0;
        numCorrectView.setText(String.valueOf(numCorrect));
        numWrongView.setText(String.valueOf(numWrong));
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
            raceLayout.setVisibility(View.GONE);
            startRace.setVisibility(View.VISIBLE);
        }
        return super.onOptionsItemSelected(item);
    }
}
