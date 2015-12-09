package com.randomappsinc.mathrace.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.randomappsinc.mathrace.API.Models.RunStory;
import com.randomappsinc.mathrace.R;
import com.randomappsinc.mathrace.Utils.FeedUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by alexanderchiou on 12/7/15.
 */
public class StoriesAdapter extends BaseAdapter {
    private Context context;
    private List<RunStory> stories;

    public StoriesAdapter(Context context) {
        this.context = context;
        this.stories = new ArrayList<>();
    }

    public void prependStories(List<RunStory> newStories) {
        for (RunStory story : newStories) {
            stories.add(0, story);
        }
        notifyDataSetChanged();
    }

    public void appendStories(List<RunStory> oldStories) {
        for (RunStory story : oldStories) {
            stories.add(story);
        }
        notifyDataSetChanged();
    }

    public int getNewestStoryId() {
        if (!stories.isEmpty()) {
            return getItem(0).getId();
        }
        return 0;
    }

    public int getOldestStoryId() {
        if (!stories.isEmpty()) {
            return getItem(getCount() - 1).getId();
        }
        return 0;
    }

    @Override
    public int getCount() {
        return stories.size();
    }

    @Override
    public RunStory getItem(int position) {
        return stories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    public class StoryViewHolder {
        @Bind(R.id.user_tag) TextView userTag;
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
            view = vi.inflate(R.layout.story_list_item, parent, false);
            holder = new StoryViewHolder(view);
            view.setTag(holder);
        }
        else {
            holder = (StoryViewHolder) view.getTag();
        }

        holder.userTag.setText(getItem(position).getUserTag());
        holder.runType.setText(getItem(position).getRunType());
        holder.numCorrect.setText(String.valueOf(getItem(position).getNumCorrect()));
        holder.numWrong.setText(String.valueOf(getItem(position).getNumWrong()));
        holder.timestamp.setText(FeedUtils.humanizeUnixTime(getItem(position).getTimeOccurred()));

        return view;
    }
}
