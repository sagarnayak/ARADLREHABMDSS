package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit;

import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.dailyreport.DailyReportExpanded;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.dashboard.DashboardData;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.edituser.EditUserResponse;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.edituser.UserDetails;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.game.GameList;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.gamecomp.GameComparisonExpanded;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.gamereps.GameRepetitionExpanded;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.login.LoginRequest;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.login.LoginResponse;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.trainingfrequency.TrainingFreqExpanded;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.user.UserListResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {

    @POST("login.php")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @GET("userlist.php/{offset}/{count}")
    Call<UserListResponse> userList(@Path("offset") String offset, @Path("count") String count);

    @GET("gamelist.php/{offset}/{count}")
    Call<GameList> gameList(@Path("offset") String offset, @Path("count") String count);

    @GET("dashboard.php")
    Call<DashboardData> dashboardData(@Query("user_id") String userId);

    @GET("dailyreport.php")
    Call<DailyReportExpanded> dailyReport(@Query("user_id") String userId,
                                          @Query("game_id") String gameId,
                                          @Query("offset") String offset,
                                          @Query("count") String count,
                                          @Query("from") String from,
                                          @Query("to") String to);

    @GET("training-frequency.php")
    Call<TrainingFreqExpanded> trainingFrequency(@Query("user_id") String userId,
                                                 @Query("offset") String offset,
                                                 @Query("count") String count,
                                                 @Query("from") String from,
                                                 @Query("to") String to);

    @GET("game-comparison.php")
    Call<GameComparisonExpanded> gameComparison(@Query("user_id") String userId,
                                                @Query("offset") String offset,
                                                @Query("count") String count,
                                                @Query("from") String from,
                                                @Query("to") String to);

    @GET("game-repetation.php")
    Call<GameRepetitionExpanded> gameRepetition(@Query("user_id") String userId,
                                                @Query("game_id") String gameId,
                                                @Query("offset") String offset,
                                                @Query("count") String count,
                                                @Query("from") String from,
                                                @Query("to") String to);

    @GET("get-user-game.php")
    Call<UserDetails> userGame(@Query("user_id") String userId);

    @POST("set-user-game.php")
    Call<EditUserResponse> editUser(@Body com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.edituser.EditUser editUser,
                                    @Query("user_id") String userId);
}
