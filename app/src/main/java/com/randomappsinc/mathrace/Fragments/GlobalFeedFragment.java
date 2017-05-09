package com.randomappsinc.mathrace.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.randomappsinc.mathrace.API.ApiConstants;
import com.randomappsinc.mathrace.API.Callbacks.GetStoriesCallback;
import com.randomappsinc.mathrace.API.RestClient;
import com.randomappsinc.mathrace.Activities.RaceActivity;
import com.randomappsinc.mathrace.Adapters.GlobalFeedAdapter;
import com.randomappsinc.mathrace.Models.Events.StoriesEvent;
import com.randomappsinc.mathrace.R;
import com.randomappsinc.mathrace.Utils.Constants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by alexanderchiou on 12/5/15.
 */
public class GlobalFeedFragment extends Fragment
        implements SwipeRefreshLayout.OnRefreshListener, ListView.OnScrollListener {
    @Bind(R.id.loading) View loadingStories;
    @Bind(R.id.content) ListView stories;
    @Bind(R.id.super_parent) View parent;
    @Bind(R.id.fetch_new_content) SwipeRefreshLayout fetchNewStories;
    @Bind(R.id.no_content) TextView noStories;
    @Bind(R.id.pick_race) FloatingActionMenu pickRace;
    @Bind(R.id.addition_race) FloatingActionButton additionRace;
    @Bind(R.id.subtraction_race) FloatingActionButton subtractionRace;

    private GlobalFeedAdapter globalFeedAdapter;
    private int lastIndexToTrigger;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.global_feed, container, false);
        ButterKnife.bind(this, rootView);
        EventBus.getDefault().register(this);
        lastIndexToTrigger = 0;

        noStories.setText(R.string.no_stories_to_display);
        pickRace.getMenuIconView().setImageResource(R.drawable.race_icon);
        pickRace.setIconAnimated(false);
        additionRace.setImageDrawable(new IconDrawable(getActivity(),
                FontAwesomeIcons.fa_plus).colorRes(R.color.white));
        subtractionRace.setImageDrawable(new IconDrawable(getActivity(),
                FontAwesomeIcons.fa_minus).colorRes(R.color.white));
        globalFeedAdapter = new GlobalFeedAdapter(getActivity());
        stories.setAdapter(globalFeedAdapter);
        stories.setOnScrollListener(this);
        fetchNewStories.setColorSchemeResources(R.color.red, R.color.yellow, R.color.green, R.color.app_blue);
        fetchNewStories.setOnRefreshListener(this);

        return rootView;
    }

    public void fetchNewestStories() {
        GetStoriesCallback callback = new GetStoriesCallback(ApiConstants.NEWEST);
        RestClient.getInstance().getMathRaceService().getStories(ApiConstants.NEWEST, "0").enqueue(callback);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (globalFeedAdapter.getCount() != 0) {
            fetchNewStories(true);
        }
        else {
            if (fetchNewStories.getVisibility() == View.VISIBLE) {
                fetchNewStories.setRefreshing(true);
            }
            fetchNewestStories();
        }
    }

    @Subscribe
    public void onEvent(StoriesEvent event) {
        if (event.getMode().equals(ApiConstants.NEWEST)) {
            loadingStories.setVisibility(View.GONE);
            fetchNewStories.setVisibility(View.VISIBLE);

            if (event.getStories() == null || event.getStories().isEmpty()) {
                noStories.setVisibility(View.VISIBLE);
            }
            else {
                noStories.setVisibility(View.GONE);
            }
        }
        fetchNewStories.setRefreshing(false);
        if (event.getStories() != null) {
            switch (event.getMode()) {
                case ApiConstants.NEWEST:
                case ApiConstants.BEFORE:
                    globalFeedAdapter.appendStories(event.getStories());
                    break;
                case ApiConstants.AFTER:
                    globalFeedAdapter.prependStories(event.getStories());
            }
        }
        else {
            showErrorSnackbar();
        }
    }

    @Override
    public void onRefresh() {
        if (globalFeedAdapter.getCount() == 0) {
            fetchNewestStories();
        }
        else {
            fetchNewStories(false);
        }
    }

    public void fetchNewStories(boolean automatic) {
        if (automatic) {
            fetchNewStories.setRefreshing(true);
        }
        GetStoriesCallback callback = new GetStoriesCallback(ApiConstants.AFTER);
        RestClient.getInstance().getMathRaceService().getStories(ApiConstants.AFTER,
                String.valueOf(globalFeedAdapter.getNewestStoryId())).enqueue(callback);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {}

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        int bottomIndexSeen = firstVisibleItem + visibleItemCount;

        // If visible last item's position is the size of the list, then we've hit the bottom
        if (globalFeedAdapter.getExtraItem() == 1 && bottomIndexSeen == totalItemCount) {
            if (lastIndexToTrigger != bottomIndexSeen) {
                lastIndexToTrigger = bottomIndexSeen;
                GetStoriesCallback callback = new GetStoriesCallback(ApiConstants.BEFORE);
                RestClient.getInstance().getMathRaceService().getStories(ApiConstants.BEFORE,
                        String.valueOf(globalFeedAdapter.getOldestStoryId())).enqueue(callback);
            }
        }
    }

    public void showErrorSnackbar() {
        Snackbar storiesError = Snackbar.make(parent, R.string.fetch_stories_fail, Snackbar.LENGTH_LONG);
        View rootView = storiesError.getView();
        storiesError.getView().setBackgroundColor(getResources().getColor(R.color.app_blue));
        TextView textView = (TextView) rootView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        storiesError.getView().addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                pickRace.setTranslationY(0);
            }
        });
        storiesError.show();
    }

    @OnClick({R.id.addition_race, R.id.subtraction_race})
    public void startRace(View view) {
        pickRace.close(false);
        Intent intent = new Intent(getActivity(), RaceActivity.class);
        switch (view.getId()) {
            case R.id.addition_race:
                intent.putExtra(Constants.RUN_TYPE_KEY, Constants.ADDITION);
                break;
            case R.id.subtraction_race:
                intent.putExtra(Constants.RUN_TYPE_KEY, Constants.SUBTRACTION);
        }
        getActivity().startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }
}
