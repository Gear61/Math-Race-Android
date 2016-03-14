package com.randomappsinc.mathrace.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.randomappsinc.mathrace.Persistence.PreferencesManager;
import com.randomappsinc.mathrace.R;
import com.randomappsinc.mathrace.Utils.FormUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by alexanderchiou on 12/11/15.
 */
public class ChooseUserTagActivity extends StandardActivity {
    @Bind(R.id.instructions) TextView instructions;
    @Bind(R.id.user_tag) EditText userTag;
    @Bind(R.id.parent) View parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_user_tag);
        ButterKnife.bind(this);
        if (!PreferencesManager.get().getUserTag().isEmpty()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            instructions.setText(R.string.update_tag);
            userTag.setText(PreferencesManager.get().getUserTag());
            userTag.setSelection(PreferencesManager.get().getUserTag().length());
        }
    }

    @OnClick(R.id.submit)
    public void submitTag() {
        FormUtils.hideKeyboard(this);
        String newTag = userTag.getText().toString();
        if (newTag.trim().isEmpty()) {
            FormUtils.showSnackbar(parent, getString(R.string.blank_tag));
        }
        else {
            PreferencesManager.get().setUserTag(newTag);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        if (!PreferencesManager.get().getUserTag().isEmpty()) {
            super.onBackPressed();
        }
    }
}
