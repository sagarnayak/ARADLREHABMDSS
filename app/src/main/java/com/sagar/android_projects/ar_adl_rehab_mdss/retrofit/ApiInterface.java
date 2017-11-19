package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit;

import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.dailyreport.DailyReport;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.dashboard.DashboardData;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.gamecomp.GameComparison;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.game.GameList;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.gamereps.GameRepetation;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.login.LoginRequest;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.login.LoginResponse;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.trainingfrequency.TrainingFrequencyList;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.trainingresponse.TrainingSessionResponse;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.usergame.UserGame;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.usergame.UserGameResponse;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.user.UserListResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by sagar on 11/16/2017.
 */
public interface ApiInterface {

    @POST("login.php")
    public Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @GET("userlist.php/{offset}/{count}")
    public Call<UserListResponse> userList(@Path("offset") String offset, @Path("count") String count);

    @GET("gamelist.php/{offset}/{count}")
    public Call<GameList> gameList(@Path("offset") String offset, @Path("count") String count);

    @GET("dashboard.php")
    public Call<DashboardData> dashboadData(@Query("user_id") String userId);

    @GET("dailyReport/{gameId}/{offset}/{count}")
    public Call<DailyReport> dailyReport(@Path("gameId") String gameId,
                                         @Path("offset") String offset,
                                         @Path("count") String count,
                                         @Query("from") String from,
                                         @Query("to") String to);

    @GET("trainingFrequency/{offset}/{count}")
    public Call<TrainingFrequencyList> trainingFrequency(@Path("offset") String offset,
                                                         @Path("count") String count,
                                                         @Query("from") String from,
                                                         @Query("to") String to);

    @GET("agmeComparison/{offset}/{count}")
    public Call<ArrayList<GameComparison>> gameComparison(@Path("offset") String offset,
                                                          @Path("count") String count,
                                                          @Query("from") String from,
                                                          @Query("to") String to);

    @GET("trainingSessions/{gameId}/{date}")
    public Call<ArrayList<TrainingSessionResponse>> trainingSession(@Path("gameId") String gameId,
                                                                    @Path("date") String date);

    @GET("gameRepetation/{gameId}/{sessionId}")
    public Call<ArrayList<GameRepetation>> gameRepetation(@Path("gameId") String gameId,
                                                          @Path("sessionId") String sessionId);

    @GET("userGame/{userId}")
    public Call<ArrayList<UserGame>> userGame(@Path("userId") String userId);

    @POST("userGame")
    public Call<UserGameResponse> setUserGame(@Body UserGame userGame);
}
