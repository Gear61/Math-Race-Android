package com.randomappsinc.mathrace.Activities;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.randomappsinc.mathrace.Models.Problem;
import com.randomappsinc.mathrace.R;
import com.randomappsinc.mathrace.Utils.Constants;
import com.randomappsinc.mathrace.Utils.FormUtils;
import com.randomappsinc.mathrace.Utils.RaceUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;

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
    @Bind(R.id.problem) TextView problem;
    @Bind(R.id.answer) EditText answer;

    private String runType;
    private int numCorrect;
    private int numWrong;
    private Problem currentProblem;

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
        setUpNewProblem();
        answer.requestFocus();
        FormUtils.showKeyboard(this);
    }

    public void setUpNewProblem() {
        currentProblem = RaceUtils.generateProblem(runType);
        problem.setText(currentProblem.getProblemText());
    }

    public void processAnswer() {
        if (currentProblem.getAnswer().equals(answer.getText().toString())) {
            numCorrect++;
            numCorrectView.setText(String.valueOf(numCorrect));
        }
        else {
            numWrong++;
            numWrongView.setText(String.valueOf(numWrong));
        }
    }

    @OnEditorAction(R.id.answer)
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if ((actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_DOWN)
                || (actionId == EditorInfo.IME_ACTION_DONE)) {
            processAnswer();
            answer.setText("");
            setUpNewProblem();
            return true;
        }
        return false;
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
            FormUtils.hideKeyboard(this);
            raceLayout.setVisibility(View.GONE);
            startRace.setVisibility(View.VISIBLE);
        }
        return super.onOptionsItemSelected(item);
    }
}
