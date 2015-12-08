package com.randomappsinc.mathrace.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class GlobalFeedFragment extends Fragment {
    @Bind(R.id.loading_stories) View loadingStories;
    @Bind(R.id.stories) ListView stories;
    @Bind(R.id.parent) View parent;

    private StoriesAdapter storiesAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.feed, container, false);
        ButterKnife.bind(this, rootView);
        EventBus.getDefault().register(this);

        storiesAdapter = new StoriesAdapter(getActivity());
        stories.setAdapter(storiesAdapter);

        GetStoriesCallback callback = new GetStoriesCallback(ApiConstants.NEWEST);
        RestClient.getInstance().getMathRaceService().getStories(ApiConstants.NEWEST, "0").enqueue(callback);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void onEvent(StoriesEvent event) {
        loadingStories.setVisibility(View.GONE);
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
}
