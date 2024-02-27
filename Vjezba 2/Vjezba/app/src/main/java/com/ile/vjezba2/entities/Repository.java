package com.ile.vjezba2.entities;

import com.google.gson.annotations.SerializedName;

public class Repository {
    private String name;
    private Owner owner;
    @SerializedName("stargazers_count")
    private int stars;

    public Repository(String name, String avatar, int stars) {
        this.name = name;
        this.owner = new Owner(avatar);
        this.stars = stars;
    }

    private class Owner {
        @SerializedName("avatar_url")
        private String avatar;

        public Owner(String avatar) {
            this.avatar = avatar;
        }

        public String getAvatar() {
            return avatar;
        }
    }

    public String getName() {
        return name;
    }

    public String getAvatar() { return owner.getAvatar(); }

    public int getStars() {
        return stars;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "\tStars: " + this.stars + "\tAvatar: " + this.owner.getAvatar() + "\n";
    }
}
