package com.example.arslan.chocolife.utils;

import android.graphics.Movie;
import android.util.Log;

import com.example.arslan.chocolife.data.Category;
import com.example.arslan.chocolife.data.Stock;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONUtils {

    private static final String myTag = "myTag";


    private static final String KEY_RESULT = "result";

    //keys for stocks
    private static final String KEY_DEAL_ID = "deal_id";
    private static final String KEY_PRICE = "price";
    private static final String KEY_DISCOUNT = "discount";
    private static final String KEY_TITLE_SHORT = "title_short";
    private static final String KEY_REVIEWS_RATE = "reviews_rate";
    private static final String KEY_STOCK_TITLE = "title";
    private static final String KEY_BOUGHT = "bought";
    private static final String KEY_IMAGE_KIND = "image_kind";
    private static final String KEY_IMAGE_URL = "image_url";
    private static final String KEY_IMAGE_URL_WIDE = "image_url_wide";

//    private int category_id;
//    private String title;
//    private String title_translit;
//    private int deals_number;
//    private ArrayList<Category> sub_categories;

    //keys for categories
    private static final String KEY_CATEGORY_ID = "id";
    private static final String KEY_CATEGORY_TITLE = "title";
    private static final String KEY_CATEGORY_TITLE_TRANSLIT = "title_translit";
    private static final String KEY_DEALS_NUMBER = "deals_number";
    private static final String KEY_SUBCATEGORIES = "sub_categories";



    //    private String imageUrlWide;
    //    private String imageUrl;
    //    private String imageKind;
    //    private int bought;
    //    private String title;
    //    private double reviewsRate;
    //    private String titleShort;
    //    private int discount;
    //    private int price;
    //    private int dealId;


    public static List<Stock> getStocksFromJSON(JSONObject jsonObject) {
        ArrayList<Stock> result = new ArrayList<>();
        if (jsonObject == null) {
            return result;
        }
        try {
            JSONArray jsonArray = jsonObject.getJSONArray(KEY_RESULT);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject objectStock = jsonArray.getJSONObject(i);
                int deal_id = objectStock.getInt(KEY_DEAL_ID);
                int price = objectStock.getInt(KEY_PRICE);
                int discount = objectStock.getInt(KEY_DISCOUNT);
                String titleShort = objectStock.getString(KEY_TITLE_SHORT);
                double reviewsRate = objectStock.getDouble(KEY_REVIEWS_RATE);
                String title = objectStock.getString(KEY_STOCK_TITLE);
                int bought = objectStock.getInt(KEY_BOUGHT);
                String imageKind = objectStock.getString(KEY_IMAGE_KIND);
                String imageUrl = objectStock.getString(KEY_IMAGE_URL);
                String imageUrlWide = objectStock.getString(KEY_IMAGE_URL_WIDE);

                Stock stock = new Stock(deal_id,price,discount,titleShort,reviewsRate,title,bought,imageKind,imageUrl,imageUrlWide);
                result.add(stock);
            }
        } catch (JSONException e) {
            Log.e(myTag, e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

//    private int category_id;
//    private String title;
//    private String title_translit;
//    private int deals_number;
//    private ArrayList<Category> sub_categories;

    public static List<Category> getCategoriesFromJSON(JSONObject jsonObject){
        ArrayList<Category> result = new ArrayList<>();
        if (jsonObject == null) {
            return result;
        }
        try {
            JSONArray jsonArray = jsonObject.getJSONArray(KEY_RESULT);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject objectCategory = jsonArray.getJSONObject(i);
                int category_id = objectCategory.getInt(KEY_CATEGORY_ID);
                String title = objectCategory.getString(KEY_CATEGORY_TITLE);
                String title_translit = objectCategory.getString(KEY_CATEGORY_TITLE_TRANSLIT);
                int deals_number = objectCategory.getInt(KEY_DEALS_NUMBER);
                ArrayList<Category> sub_categories = new ArrayList<>();
                JSONArray jsonArraySubcategories = objectCategory.getJSONArray(KEY_SUBCATEGORIES);

                for (int j = 0; j < jsonArraySubcategories.length(); j++) {
                    JSONObject objectSubcategory = jsonArraySubcategories.getJSONObject(j);
                    int sCategory_id = objectSubcategory.getInt(KEY_CATEGORY_ID);
                    String sTitle = objectSubcategory.getString(KEY_CATEGORY_TITLE);
                    String sTitle_translit = objectSubcategory.getString(KEY_CATEGORY_TITLE_TRANSLIT);
                    int sDeals_number = objectSubcategory.getInt(KEY_DEALS_NUMBER);
                    Category subcategory = new Category(sCategory_id,sTitle,sTitle_translit,sDeals_number,new ArrayList<Category>());
                    sub_categories.add(subcategory);
                }
                Category category = new Category(category_id,title,title_translit,deals_number,sub_categories);
                result.add(category);
            }
        } catch (JSONException e) {
            Log.e(myTag, e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

}
