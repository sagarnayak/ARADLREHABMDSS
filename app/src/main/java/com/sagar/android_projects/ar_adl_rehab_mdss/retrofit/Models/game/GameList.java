package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.game;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sagar on 11/16/2017.
 */

public class GameList {
    @SerializedName("data")
    private ArrayList<Game> data;

    public GameList() {
    }

    public GameList(ArrayList<Game> data) {
        this.data = data;
    }

    public ArrayList<Game> getData() {
        return data;
    }

    public void setData(ArrayList<Game> data) {
        this.data = data;
    }
}
