package com.vit.codevar;

import com.google.gson.annotations.SerializedName;

public class UserData {

    @SerializedName("username")
    public String username;

    @SerializedName("name")
    public Name name;

    @SerializedName("profile_image")
    public String profile_image;

    @SerializedName("stats")
    public Stats stats;

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }
}
