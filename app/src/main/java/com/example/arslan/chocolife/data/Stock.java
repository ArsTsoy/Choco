package com.example.arslan.chocolife.data;

public class Stock {
    private int dealId;
    private int price;
    private int discount;
    private String titleShort;
    private double reviewsRate;
    private String title;
    private int bought;
    private String imageKind;
    private String imageUrl;
    private String imageUrlWide;

    public Stock(int dealId, int price, int discount, String titleShort, double reviewsRate, String title, int bought, String imageKind, String imageUrl, String imageUrlWide) {
        this.dealId = dealId;
        this.price = price;
        this.discount = discount;
        this.titleShort = titleShort;
        this.reviewsRate = reviewsRate;
        this.title = title;
        this.bought = bought;
        this.imageKind = imageKind;
        this.imageUrl = imageUrl;
        this.imageUrlWide = imageUrlWide;
    }

    public int getDealId() {
        return dealId;
    }

    public void setDealId(int dealId) {
        this.dealId = dealId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getTitleShort() {
        return titleShort;
    }

    public void setTitleShort(String titleShort) {
        this.titleShort = titleShort;
    }

    public double getReviewsRate() {
        return reviewsRate;
    }

    public void setReviewsRate(double reviewsRate) {
        this.reviewsRate = reviewsRate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBought() {
        return bought;
    }

    public void setBought(int bought) {
        this.bought = bought;
    }

    public String getImageKind() {
        return imageKind;
    }

    public void setImageKind(String imageKind) {
        this.imageKind = imageKind;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrlWide() {
        return imageUrlWide;
    }

    public void setImageUrlWide(String imageUrlWide) {
        this.imageUrlWide = imageUrlWide;
    }


    @Override
    public String toString() {
        return titleShort;
    }
}
