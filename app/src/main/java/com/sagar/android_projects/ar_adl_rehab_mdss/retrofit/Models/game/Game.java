package com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.game;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sagar on 11/16/2017.
 */

public class Game {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;

    public Game() {
    }

    public Game(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
