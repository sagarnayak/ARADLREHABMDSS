package com.sagar.android_projects.ar_adl_rehab_mdss.util;

import java.util.Random;


public class Color {
    public static int generateRandomColor() {
        Random rand = new Random();

        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);

        return android.graphics.Color.rgb(r, g, b);
    }

    @SuppressWarnings("unused")
    public static int generateSimilarRandomColor() {
        Random mRandom = new Random();
        // This is the base color which will be mixed with the generated one
        final int baseColor = android.graphics.Color.CYAN;

        final int baseRed = android.graphics.Color.red(baseColor);
        final int baseGreen = android.graphics.Color.green(baseColor);
        final int baseBlue = android.graphics.Color.blue(baseColor);

        final int red = (baseRed + mRandom.nextInt(256)) / 2;
        final int green = (baseGreen + mRandom.nextInt(256)) / 2;
        final int blue = (baseBlue + mRandom.nextInt(256)) / 2;

        return android.graphics.Color.rgb(red, green, blue);
    }
}
