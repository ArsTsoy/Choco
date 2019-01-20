package com.example.arslan.chocolife.data;

import java.util.ArrayList;


public class Offer {

    public static final String PAY_METHOD_BALANCE = "balance";
    public static final String PAY_METHOD_TERMINAL = "terminal";
    public static final String PAY_METHOD_CARD = "card";
    public static final String PAY_METHOD_INTERNET_BANKING = "internetbanking";
    public static final String PAY_METHOD_PROCESSING = "processing";
    public static final String PAY_METHOD_QIWIWALLET = "qiwiwallet";
    public static final String PAY_METHOD_CLOUD_PAYMENTS = "cloudpayments";


    private int order;
    private int price;
    private int offer_id;
    private String expiry_time;
    private int full_price;
    private int bought_count;
    private String text;
    private int discount;
    private String group;
    private int available_number;
    private ArrayList<String> pay_methods;
    private int deal_id;


    public Offer(int order, int price, int offer_id, String expiry_time, int full_price, int bought_count, String text, int discount, String group, int available_number, ArrayList<String> pay_methods, int deal_id) {
        this.order = order;
        this.price = price;
        this.offer_id = offer_id;
        this.expiry_time = expiry_time;
        this.full_price = full_price;
        this.bought_count = bought_count;
        this.text = text;
        this.discount = discount;
        this.group = group;
        this.available_number = available_number;
        this.pay_methods = pay_methods;
        this.deal_id = deal_id;
    }


    public int getOrder() {
        return order;
    }

    public int getPrice() {
        return price;
    }

    public int getOffer_id() {
        return offer_id;
    }

    public String getExpiry_time() {
        return expiry_time;
    }

    public int getFull_price() {
        return full_price;
    }

    public int getBought_count() {
        return bought_count;
    }

    public String getText() {
        return text;
    }

    public int getDiscount() {
        return discount;
    }

    public String getGroup() {
        return group;
    }

    public int getAvailable_number() {
        return available_number;
    }

    public ArrayList<String> getPay_methods() {
        return pay_methods;
    }

    public int getDeal_id() {
        return deal_id;
    }
}
