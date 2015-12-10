package com.randomappsinc.mathrace.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.randomappsinc.mathrace.API.Callbacks.GetLeaderboardCallback;
import com.randomappsinc.mathrace.API.RestClient;
import com.randomappsinc.mathrace.Adapters.LeaderboardAdapter;
import com.randomappsinc.mathrace.Models.Events.LeaderboardEvent;
import com.randomappsinc.mathrace.R;
import com.randomappsinc.mathrace.Utils.FormUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by alexanderchiou on 12/8/15.
 */
public class LeaderboardFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    public static final String RUN_TYPE_KEY = "runType";

    @Bind(R.id.parent) View parent;
    @Bind(R.id.loading_leaderboard) View loadingLeaderboard;
    @Bind(R.id.update_leaderboard) SwipeRefreshLayout updateLeaderboard;
    @Bind(R.id.leaderboard) ListView leaderboard;

    private String runType;
    private LeaderboardAdapter leaderboardAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.leaderboard, container, false);
        ButterKnife.bind(this, rootView);
        EventBus.getDefault().register(this);
        runType = getArguments().getString(RUN_TYPE_KEY);

        leaderboardAdapter = new LeaderboardAdapter(getActivity());
        leaderboard.setAdapter(leaderboardAdapter);
        updateLeaderboard.setColorSchemeResources(R.color.red, R.color.yellow, R.color.green, R.color.app_blue);
        updateLeaderboard.setOnRefreshListener(this);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchLeaderboard(true);
    }

    public void fetchLeaderboard(boolean automatic) {
        if (automatic) {
            updateLeaderboard.setRefreshing(true);
        }
        GetLeaderboardCallback callback = new GetLeaderboardCallback(runType);
        RestClient.getInstance().getMathRaceService().getLeaderboard(runType).enqueue(callback);
    }

    public void onEvent(LeaderboardEvent event) {
        if (event.getRunType().equals(runType)) {
            loadingLeaderboard.setVisibility(View.GONE);
            updateLeaderboard.setVisibility(View.VISIBLE);
            updateLeaderboard.setRefreshing(false);
            if (event.getLeadingRuns() != null) {
                leaderboardAdapter.setLeaderboard(event.getLeadingRuns());
            }
            else {
                FormUtils.showSnackbar(parent, getString(R.string.leaderboard_error));
                if (leaderboardAdapter.getCount() == 0) {

                }
            }
        }
    }

    @Override
    public void onRefresh() {
        fetchLeaderboard(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
