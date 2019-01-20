package com.example.arslan.chocolife.data;

public class Town {
    private String title;
    private int id_town;

    public Town(String title, int id_town) {
        this.title = title;
        this.id_town = id_town;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id_town;
    }

    public void setId(int id_town) {
        this.id_town = id_town;
    }
}
