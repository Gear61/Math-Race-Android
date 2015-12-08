package com.randomappsinc.mathrace.Models.Events;

import com.randomappsinc.mathrace.API.Models.RunStory;

import java.util.List;

/**
 * Created by alexanderchiou on 12/7/15.
 */
public class StoriesEvent {
    private String mode;
    private List<RunStory> stories;

    public StoriesEvent(String mode, List<RunStory> stories) {
        this.mode = mode;
        this.stories = stories;
    }

    public String getMode() {
        return mode;
    }

    public List<RunStory> getStories() {
        return stories;
    }
}
