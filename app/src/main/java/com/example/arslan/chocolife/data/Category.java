package com.example.arslan.chocolife.data;

import java.util.ArrayList;

public class Category {
    private int category_id;
    private String title;
    private String title_translit;
    private int deals_number;
    private ArrayList<Category> sub_categories;


    public Category(int category_id, String title, String title_translit, int deals_number, ArrayList<Category> sub_categories) {
        this.category_id = category_id;
        this.title = title;
        this.title_translit = title_translit;
        this.deals_number = deals_number;
        this.sub_categories = sub_categories;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_translit() {
        return title_translit;
    }

    public void setTitle_translit(String title_translit) {
        this.title_translit = title_translit;
    }

    public int getDeals_number() {
        return deals_number;
    }

    public void setDeals_number(int deals_number) {
        this.deals_number = deals_number;
    }

    public ArrayList<Category> getSub_categories() {
        return sub_categories;
    }

    public void setSub_categories(ArrayList<Category> sub_categories) {
        this.sub_categories = sub_categories;
    }
    public boolean isHasSubcategories(){
        return (sub_categories.size()>0);
    }


    @Override
    public String toString() {
        return title;
    }
}
