package com.example.arslan.chocolife.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StockInfo {

    //required parametrs
    private ArrayList<String> images;
    private String title_short;
    private String title;
    private double reviews_rate;
    private int bought_number;
    private int reviews_number;
    private int price;
    private int economy;
    private String expiry_time;
    private String protection;
    private ArrayList<Place> places;
    private HashMap<String,String> social_links;
    private ArrayList<StockFile> stockFiles;
    private String terms;
    private String features;



    //optional parametrs;
    private ArrayList<Offer> offers;
    private String title_long;
    private String deal_kind;
    private int interested;
    private boolean is_free;
    private int full_price;
    private ArrayList<Town> towns;
    private int discount;
    private String title_popup;
    private int quetions_number;


    private StockInfo(Builder builder) {
        this.images = builder.bImages;
        this.title_short = builder.bTitle_short;
        this.title = builder.bTitle;
        this.reviews_rate = builder.bReviews_rate;
        this.bought_number = builder.bBought_number;
        this.reviews_number = builder.bReviews_number;
        this.price = builder.bPrice;
        this.economy = builder.bEconomy;
        this.expiry_time = builder.bExpiry_time;
        this.protection = builder.bProtection;
        this.places = builder.bPlaces;
        this.social_links = builder.bSocial_links;
        this.stockFiles = builder.bStockFiles;
        this.terms = builder.bTerms;
        this.features = builder.bFeatures;

        this.offers = builder.bOffers;
        this.title_long = builder.bTitle_long;
        this.deal_kind = builder.bDeal_kind;
        this.interested = builder.bInterested;
        this.is_free = builder.bIs_free;
        this.full_price = builder.bFull_price;
        this.towns = builder.bTowns;
        this.discount = builder.bDiscount;
        this.title_popup = builder.bTitle_popup;
        this.quetions_number = builder.bQuetions_number;
    }


    public ArrayList<String> getImages() {
        return images;
    }

    public String getTitle_short() {
        return title_short;
    }

    public String getTitle() {
        return title;
    }

    public double getReviews_rate() {
        return reviews_rate;
    }

    public int getBought_number() {
        return bought_number;
    }

    public int getReviews_number() {
        return reviews_number;
    }

    public int getPrice() {
        return price;
    }

    public int getEconomy() {
        return economy;
    }

    public String getExpiry_time() {
        return expiry_time;
    }

    public String getProtection() {
        return protection;
    }

    public ArrayList<Place> getPlaces() {
        return places;
    }

    public HashMap<String,String> getSocial_links() {
        return social_links;
    }

    public ArrayList<StockFile> getStockFiles() {
        return stockFiles;
    }

    public ArrayList<Offer> getOffers() {
        return offers;
    }

    public String getTerms() {
        return terms;
    }

    public String getFeatures() {
        return features;
    }

    public String getTitle_long() {
        return title_long;
    }

    public String getDeal_kind() {
        return deal_kind;
    }

    public int getInterested() {
        return interested;
    }

    public boolean isIs_free() {
        return is_free;
    }

    public int getFull_price() {
        return full_price;
    }

    public ArrayList<Town> getTowns() {
        return towns;
    }

    public int getDiscount() {
        return discount;
    }

    public String getTitle_popup() {
        return title_popup;
    }

    public int getQuetions_number() {
        return quetions_number;
    }

    public static class Builder{

        //required parametrs
        private ArrayList<String> bImages;
        private String bTitle_short;
        private String bTitle;
        private double bReviews_rate;
        private int bBought_number;
        private int bReviews_number;
        private int bPrice;
        private int bEconomy;
        private String bExpiry_time;
        private String bProtection;
        private ArrayList<Place> bPlaces;
        private HashMap<String,String> bSocial_links;
        private ArrayList<StockFile> bStockFiles;
        private String bTerms;
        private String bFeatures;



        //optional parametrs;
        private ArrayList<Offer> bOffers;
        private String bTitle_long;
        private String bDeal_kind;
        private int bInterested;
        private boolean bIs_free;
        private int bFull_price;
        private ArrayList<Town> bTowns;
        private int bDiscount;
        private String bTitle_popup;
        private int bQuetions_number;


        public Builder(
                ArrayList<String> bImages,
                String bTitle_short,
                String bTitle,
                double bReviews_rate,
                int bBought_number,
                int bReviews_number,
                int bPrice,
                int bEconomy,
                String bExpiry_time,
                String bProtection,
                ArrayList<Place> bPlaces,
                HashMap<String,String> bSocial_links,
                ArrayList<StockFile> bStockFiles,

                String bTerms,
                String bFeatures
        ) {
            this.bImages = new ArrayList<>(bImages);
            this.bTitle_short = bTitle_short;
            this.bTitle = bTitle;
            this.bReviews_rate = bReviews_rate;
            this.bBought_number = bBought_number;
            this.bReviews_number = bReviews_number;
            this.bPrice = bPrice;
            this.bEconomy = bEconomy;
            this.bExpiry_time = bExpiry_time;
            this.bProtection = bProtection;
            this.bPlaces = new ArrayList<>(bPlaces);
            this.bSocial_links = bSocial_links;
            this.bStockFiles = new ArrayList<>(bStockFiles);

            this.bTerms = bTerms;
            this.bFeatures = bFeatures;
        }

        public Builder offer(ArrayList<Offer> offers){
            this.bOffers = new ArrayList<>(offers);
            return this;
        }


        public Builder title_long(String val){
            this.bTitle_long = val;
            return this;
        }

        public Builder deal_kind(String val){
            this.bDeal_kind = val;
            return this;
        }

        public Builder interested(int val){
            this.bInterested = val;
            return this;
        }

        public Builder is_free(boolean val){
            this.bIs_free = val;
            return this;
        }

        public Builder full_price(int val){
            this.bFull_price = val;
            return this;
        }

        public Builder towns(List<Town> val){
            this.bTowns = new ArrayList<>(val);
            return this;
        }

        public Builder discount(int val){
            this.bDiscount = val;
            return this;
        }

        public Builder title_popup(String val){
            this.bTitle_popup = val;
            return this;
        }

        public Builder question_number(int val){
            this.bQuetions_number = val;
            return this;
        }

        public StockInfo build(){
            return new StockInfo(this);
        }
    }
}
