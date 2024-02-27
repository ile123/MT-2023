package com.ile.obrana1.entities;

public class RetrofitCountry {
    private String name;
    private String region;
    private String currency;

    public RetrofitCountry() {

    }

    public RetrofitCountry(String name, String region, String currency) {
        this.name = name;
        this.region = region;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
