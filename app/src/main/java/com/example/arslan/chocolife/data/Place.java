package com.example.arslan.chocolife.data;

import java.util.ArrayList;

public class Place {
    private String address;
    private double lat;
    private double lon;
    private String schedule;
    private ArrayList<String> phones;

    public Place(String address, double lat, double lon, String schedule, ArrayList<String> phones) {
        this.address = address;
        this.lat = lat;
        this.lon = lon;
        this.schedule = schedule;
        this.phones = new ArrayList<>(phones);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public ArrayList<String> getPhones() {
        if(phones != null){
            return phones;
        }
        throw new NullPointerException();
    }



    public void addNewPhone(String phone) {
        this.phones.add(phone);
    }
}
