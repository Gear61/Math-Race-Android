package com.randomappsinc.mathrace.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.randomappsinc.mathrace.Persistence.Database.DatabaseManager;
import com.randomappsinc.mathrace.Persistence.Database.RunDO;
import com.randomappsinc.mathrace.R;
import com.randomappsinc.mathrace.Utils.FeedUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by alexanderchiou on 12/13/15.
 */
public class MyHistoryAdapter extends BaseAdapter {
    private Context context;
    private List<RunDO> stories;

    public MyHistoryAdapter(Context context) {
        this.context = context;
        this.stories = DatabaseManager.get().getHistory();
    }

    @Override
    public int getCount() {
        return stories.size();
    }

    @Override
    public RunDO getItem(int position) {
        return stories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    public class StoryViewHolder {
        @Bind(R.id.run_type) TextView runType;
        @Bind(R.id.num_correct) TextView numCorrect;
        @Bind(R.id.num_wrong) TextView numWrong;
        @Bind(R.id.timestamp) TextView timestamp;

        public StoryViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        StoryViewHolder holder;
        if (view == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(R.layout.my_history_cell, parent, false);
            holder = new StoryViewHolder(view);
            view.setTag(holder);
        }
        else {
            holder = (StoryViewHolder) view.getTag();
        }

        holder.runType.setText(getItem(position).getRunType());
        holder.numCorrect.setText(String.valueOf(getItem(position).getNumCorrect()));
        holder.numWrong.setText(String.valueOf(getItem(position).getNumWrong()));
        holder.timestamp.setText(FeedUtils.humanizeUnixTime(getItem(position).getTimeOccurred()));

        return view;
    }
}
