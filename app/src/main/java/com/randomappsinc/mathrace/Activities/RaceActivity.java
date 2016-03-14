package com.randomappsinc.mathrace.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.randomappsinc.mathrace.API.Models.RunStory;
import com.randomappsinc.mathrace.Models.Problem;
import com.randomappsinc.mathrace.Persistence.PreferencesManager;
import com.randomappsinc.mathrace.R;
import com.randomappsinc.mathrace.Utils.Constants;
import com.randomappsinc.mathrace.Utils.FormUtils;
import com.randomappsinc.mathrace.Utils.RaceUtils;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;

/**
 * Created by alexanderchiou on 12/10/15.
 */
public class RaceActivity extends StandardActivity {
    public static final String STARTING_TIME = "1:00";
    public static final int STARTING_SECONDS = 60;

    @Bind(R.id.start_race) View startRace;
    @Bind(R.id.race_layout) View raceLayout;
    @Bind(R.id.timer) TextView timer;
    @Bind(R.id.num_correct) TextView numCorrectView;
    @Bind(R.id.num_wrong) TextView numWrongView;
    @Bind(R.id.problem) TextView problem;
    @Bind(R.id.answer) EditText answer;
    @Bind(R.id.submit_run) View submitRun;
    @Bind(R.id.retry) View retry;

    private String runType;
    private int numCorrect;
    private int numWrong;
    private Problem currentProblem;
    private int secondsRemaining;

    private Handler timerHandler = new Handler();
    private Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            secondsRemaining--;
            int secondsLeft = secondsRemaining % 60;
            int minutesLeft = secondsRemaining / 60;
            timer.setText(String.format("%d:%02d", minutesLeft, secondsLeft));
            if (secondsRemaining == 0) {
                endRace();
            }
            else {
                timerHandler.postDelayed(this, 1000);
            }
        }
    };

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
    public void startRace() {
        startFreshRace();
    }

    public void startFreshRace() {
        startRace.setVisibility(View.GONE);
        submitRun.setVisibility(View.GONE);
        retry.setVisibility(View.GONE);
        problem.setVisibility(View.VISIBLE);
        answer.setVisibility(View.VISIBLE);
        raceLayout.setVisibility(View.VISIBLE);
        timer.setText(STARTING_TIME);
        numCorrect = 0;
        numWrong = 0;
        numCorrectView.setText(String.valueOf(numCorrect));
        numWrongView.setText(String.valueOf(numWrong));
        setUpNewProblem();
        answer.requestFocus();
        FormUtils.showKeyboard(this);
        secondsRemaining = STARTING_SECONDS;
        timerHandler.postDelayed(timerRunnable, 1000);
    }

    public void setUpNewProblem() {
        currentProblem = RaceUtils.generateProblem(runType);
        problem.setText(currentProblem.getProblemText());
    }

    public void processAnswer() {
        try {
            if (currentProblem.getAnswer() == Integer.parseInt(answer.getText().toString())) {
                numCorrect++;
                numCorrectView.setText(String.valueOf(numCorrect));
            } else {
                numWrong++;
                numWrongView.setText(String.valueOf(numWrong));
            }
        }
        catch (NumberFormatException e) {
            numWrong++;
            numWrongView.setText(String.valueOf(numWrong));
        }
    }

    @OnEditorAction(R.id.answer)
    public boolean onEditorAction(int actionId, KeyEvent event) {
        if ((actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_DOWN)
                || (actionId == EditorInfo.IME_ACTION_DONE)) {
            processAnswer();
            answer.setText("");
            setUpNewProblem();
            return true;
        }
        return false;
    }

    public void endRace() {
        timerHandler.removeCallbacks(timerRunnable);
        FormUtils.hideKeyboard(this);
        problem.setVisibility(View.GONE);
        answer.setVisibility(View.GONE);
        submitRun.setVisibility(View.VISIBLE);
        retry.setVisibility(View.VISIBLE);
        answer.setText("");
    }

    @OnClick(R.id.submit_run)
    public void submitRun() {
        Intent intent = new Intent(this, EvaluationActivity.class);
        RunStory run = new RunStory(PreferencesManager.get().getUserTag(), numCorrect, numWrong, runType);
        intent.putExtra(Constants.RUN_KEY, run);
        startActivity(intent);
        raceLayout.setVisibility(View.GONE);
        startRace.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.retry)
    public void retry() {
        raceLayout.setVisibility(View.GONE);
        startRace.setVisibility(View.VISIBLE);
    }

    public void resetRace() {
        FormUtils.hideKeyboard(this);
        timerHandler.removeCallbacks(timerRunnable);
        raceLayout.setVisibility(View.GONE);
        startRace.setVisibility(View.VISIBLE);
    }

    public void showPickRaceDialog() {
        int currentChoiceIndex = Arrays.asList(getResources().getStringArray(R.array.run_types)).indexOf(runType);

        new MaterialDialog.Builder(this)
                .title(R.string.pick_race)
                .items(R.array.run_types)
                .itemsCallbackSingleChoice(currentChoiceIndex, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        runType = text.toString();
                        setTitle(runType + getString(R.string.race));
                        resetRace();
                        return true;
                    }
                })
                .positiveText(R.string.choose)
                .negativeText(android.R.string.no)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.race_menu, menu);
        menu.findItem(R.id.pick_race).setIcon(
                new IconDrawable(this, FontAwesomeIcons.fa_list)
                        .colorRes(R.color.white)
                        .actionBarSize());
        menu.findItem(R.id.start_new_race).setIcon(
                new IconDrawable(this, FontAwesomeIcons.fa_refresh)
                        .colorRes(R.color.white)
                        .actionBarSize());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.pick_race:
                FormUtils.hideKeyboard(this);
                showPickRaceDialog();
                break;
            case R.id.start_new_race:
                resetRace();
        }
        return super.onOptionsItemSelected(item);
    }
}
