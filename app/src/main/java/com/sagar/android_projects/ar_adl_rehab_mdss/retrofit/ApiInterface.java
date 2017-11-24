package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit;

import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.dailyreport.DailyReportExpanded;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.dashboard.DashboardData;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.edituser.UserDetails;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.game.GameList;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.gamecomp.GameComparisonExpanded;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.gamereps.GameRepetitionExpanded;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.login.LoginRequest;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.login.LoginResponse;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.trainingfrequency.TrainingFreqExpanded;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.user.UserListResponse;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.usergame.UserGame;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.usergame.UserGameResponse;

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
    public Call<DashboardData> dashboardData(@Query("user_id") String userId);

    @GET("dailyreport.php")
    public Call<DailyReportExpanded> dailyReport(@Query("user_id") String userId,
                                                 @Query("game_id") String gameId,
                                                 @Query("offset") String offset,
                                                 @Query("count") String count,
                                                 @Query("from") String from,
                                                 @Query("to") String to);

    @GET("training-frequency.php")
    public Call<TrainingFreqExpanded> trainingFrequency(@Query("user_id") String userId,
                                                        @Query("offset") String offset,
                                                        @Query("count") String count,
                                                        @Query("from") String from,
                                                        @Query("to") String to);

    @GET("game-comparison.php")
    public Call<GameComparisonExpanded> gameComparison(@Query("user_id") String userId,
                                                       @Query("offset") String offset,
                                                       @Query("count") String count,
                                                       @Query("from") String from,
                                                       @Query("to") String to);

    @GET("game-repetation.php")
    public Call<GameRepetitionExpanded> gameRepetition(@Query("user_id") String userId,
                                                       @Query("game_id") String gameId,
                                                       @Query("offset") String offset,
                                                       @Query("count") String count,
                                                       @Query("from") String from,
                                                       @Query("to") String to);

    @GET("get-user-game.php")
    public Call<UserDetails> userGame(@Query("user_id") String userId);

    @POST("userGame")
    public Call<UserGameResponse> setUserGame(@Body UserGame userGame);
}
