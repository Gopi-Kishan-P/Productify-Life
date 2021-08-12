package com.miniproject.productifylife.models;

public class TempData {
    final String title;
    final String coins;

    public TempData(String title, String coins) {
        this.title = title;
        this.coins = coins;
    }

    public String getCoins() {
        return this.coins;
    }

    public String getTitle() {
        return this.title;
    }

}
