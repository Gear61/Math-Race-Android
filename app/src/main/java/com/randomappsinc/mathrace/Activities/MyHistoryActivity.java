package com.randomappsinc.mathrace.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.randomappsinc.mathrace.Adapters.MyHistoryAdapter;
import com.randomappsinc.mathrace.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by alexanderchiou on 12/13/15.
 */
public class MyHistoryActivity extends StandardActivity {
    @Bind(R.id.no_history) TextView noHistory;
    @Bind(R.id.my_history) ListView myHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_history);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MyHistoryAdapter adapter = new MyHistoryAdapter(this);
        if (adapter.getCount() != 0) {
            noHistory.setVisibility(View.GONE);
            myHistory.setAdapter(new MyHistoryAdapter(this));
        }
    }
}
