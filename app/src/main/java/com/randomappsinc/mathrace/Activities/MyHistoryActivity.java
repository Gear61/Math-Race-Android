package com.randomappsinc.mathrace.Activities;

import android.os.Bundle;
import android.widget.ListView;

import com.randomappsinc.mathrace.Adapters.MyHistoryAdapter;
import com.randomappsinc.mathrace.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by alexanderchiou on 12/13/15.
 */
public class MyHistoryActivity extends StandardActivity {
    @Bind(R.id.my_history) ListView myHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_history);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myHistory.setAdapter(new MyHistoryAdapter(this));
    }
}
