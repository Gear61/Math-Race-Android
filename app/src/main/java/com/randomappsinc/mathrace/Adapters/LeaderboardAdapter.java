package com.randomappsinc.mathrace.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.randomappsinc.mathrace.API.Models.LeadingRun;
import com.randomappsinc.mathrace.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by alexanderchiou on 12/9/15.
 */
public class LeaderboardAdapter extends BaseAdapter {
    private Context context;
    private List<LeadingRun> leadingRuns;

    public LeaderboardAdapter(Context context) {
        this.context = context;
        this.leadingRuns = new ArrayList<>();
    }

    public void setLeaderboard(List<LeadingRun> newLeaderboard) {
        leadingRuns.clear();
        leadingRuns.addAll(newLeaderboard);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return leadingRuns.size();
    }

    @Override
    public LeadingRun getItem(int position) {
        return leadingRuns.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    public class LeadingRunViewHolder {
        @Bind(R.id.placing) TextView placing;

        public LeadingRunViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LeadingRunViewHolder holder;
        if (view == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(R.layout.leading_run_cell, parent, false);
            holder = new LeadingRunViewHolder(view);
            view.setTag(holder);
        }
        else {
            holder = (LeadingRunViewHolder) view.getTag();
        }

        holder.placing.setText(String.valueOf(position + 1));

        return view;
    }
}
