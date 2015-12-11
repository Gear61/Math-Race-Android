package com.randomappsinc.mathrace.API.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by alexanderchiou on 12/7/15.
 */
public class RunStory implements Parcelable {
    @SerializedName("user_tag")
    @Expose
    private String userTag;

    @SerializedName("num_correct")
    @Expose
    private int numCorrect;

    @SerializedName("num_wrong")
    @Expose
    private int numWrong;

    @SerializedName("run_type")
    @Expose
    private String runType;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("time_occurred")
    @Expose
    private long timeOccurred;

    public RunStory(String userTag, int numCorrect, int numWrong, String runType) {
        this.userTag = userTag;
        this.numCorrect = numCorrect;
        this.numWrong = numWrong;
        this.runType = runType;
    }

    public int getNumWrong() {
        return numWrong;
    }

    public String getUserTag() {
        return userTag;
    }

    public int getNumCorrect() {
        return numCorrect;
    }

    public int getId() {
        return id;
    }

    public String getRunType() {
        return runType;
    }

    public long getTimeOccurred() {
        return timeOccurred;
    }

    protected RunStory(Parcel in) {
        numWrong = in.readInt();
        userTag = in.readString();
        numCorrect = in.readInt();
        id = in.readInt();
        runType = in.readString();
        timeOccurred = in.readLong();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(numWrong);
        dest.writeString(userTag);
        dest.writeInt(numCorrect);
        dest.writeInt(id);
        dest.writeString(runType);
        dest.writeLong(timeOccurred);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<RunStory> CREATOR = new Parcelable.Creator<RunStory>() {
        @Override
        public RunStory createFromParcel(Parcel in) {
            return new RunStory(in);
        }

        @Override
        public RunStory[] newArray(int size) {
            return new RunStory[size];
        }
    };
}
