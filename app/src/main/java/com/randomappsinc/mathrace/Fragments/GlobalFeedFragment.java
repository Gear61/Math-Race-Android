package com.randomappsinc.mathrace.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.randomappsinc.mathrace.API.ApiConstants;
import com.randomappsinc.mathrace.API.Callbacks.GetStoriesCallback;
import com.randomappsinc.mathrace.API.RestClient;
import com.randomappsinc.mathrace.Adapters.StoriesAdapter;
import com.randomappsinc.mathrace.Models.Events.StoriesEvent;
import com.randomappsinc.mathrace.R;
import com.randomappsinc.mathrace.Utils.FormUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by alexanderchiou on 12/5/15.
 */
public class GlobalFeedFragment extends Fragment
        implements SwipeRefreshLayout.OnRefreshListener, ListView.OnScrollListener {
    @Bind(R.id.loading_stories) View loadingStories;
    @Bind(R.id.stories) ListView stories;
    @Bind(R.id.parent) View parent;
    @Bind(R.id.fetch_new_stories) SwipeRefreshLayout fetchNewStories;

    private StoriesAdapter storiesAdapter;
    private int lastIndexToTrigger;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.feed, container, false);
        ButterKnife.bind(this, rootView);
        EventBus.getDefault().register(this);
        lastIndexToTrigger = 0;

        storiesAdapter = new StoriesAdapter(getActivity());
        stories.setAdapter(storiesAdapter);
        stories.setOnScrollListener(this);
        fetchNewStories.setColorSchemeResources(R.color.red, R.color.yellow, R.color.green, R.color.app_blue);
        fetchNewStories.setOnRefreshListener(this);

        GetStoriesCallback callback = new GetStoriesCallback(ApiConstants.NEWEST);
        RestClient.getInstance().getMathRaceService().getStories(ApiConstants.NEWEST, "0").enqueue(callback);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (storiesAdapter.getCount() != 0) {
            fetchNewStories(true);
        }
    }

    public void onEvent(StoriesEvent event) {
        loadingStories.setVisibility(View.GONE);
        fetchNewStories.setVisibility(View.VISIBLE);
        fetchNewStories.setRefreshing(false);
        if (event.getStories() != null) {
            switch (event.getMode()) {
                case ApiConstants.NEWEST:
                case ApiConstants.BEFORE:
                    storiesAdapter.appendStories(event.getStories());
                    break;
                case ApiConstants.AFTER:
                    storiesAdapter.prependStories(event.getStories());
            }
        }
        else {
            FormUtils.showSnackbar(parent, getString(R.string.fetch_stories_fail));
        }
    }

    @Override
    public void onRefresh() {
        fetchNewStories(false);
    }

    public void fetchNewStories(boolean automatic) {
        if (automatic) {
            fetchNewStories.setRefreshing(true);
        }
        GetStoriesCallback callback = new GetStoriesCallback(ApiConstants.AFTER);
        RestClient.getInstance().getMathRaceService().getStories(ApiConstants.AFTER,
                String.valueOf(storiesAdapter.getNewestStoryId())).enqueue(callback);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {}

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        final int bottomIndexSeen = firstVisibleItem + visibleItemCount;

        // If visible last item's position is the size of the list, then we've hit the bottom
        if (storiesAdapter.getExtraItem() == 1 && bottomIndexSeen == totalItemCount) {
            if (lastIndexToTrigger != bottomIndexSeen) {
                lastIndexToTrigger = bottomIndexSeen;
                GetStoriesCallback callback = new GetStoriesCallback(ApiConstants.BEFORE);
                RestClient.getInstance().getMathRaceService().getStories(ApiConstants.BEFORE,
                        String.valueOf(storiesAdapter.getOldestStoryId())).enqueue(callback);
            }
        }
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
