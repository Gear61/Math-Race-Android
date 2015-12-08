package com.randomappsinc.mathrace.API;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by alexanderchiou on 12/7/15.
 */
public class RestClient {
    private static RestClient instance;
    private MathRaceService mathRaceService;

    public static RestClient getInstance() {
        if (instance == null) {
            instance = new RestClient();
        }
        return instance;
    }

    private RestClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mathRaceService = retrofit.create(MathRaceService.class);
    }

    public MathRaceService getMathRaceService() {
        return mathRaceService;
    }
}
