package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.game;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class GameList {
    @SerializedName("data")
    private ArrayList<Game> data;

    @SuppressWarnings("unused")
    public GameList() {
    }

    @SuppressWarnings("unused")
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
