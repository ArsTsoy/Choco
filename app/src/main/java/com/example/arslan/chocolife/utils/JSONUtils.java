package com.example.arslan.chocolife.utils;

import android.graphics.Movie;
import android.util.Log;

import com.example.arslan.chocolife.data.Category;
import com.example.arslan.chocolife.data.Place;
import com.example.arslan.chocolife.data.Stock;
import com.example.arslan.chocolife.data.StockFile;
import com.example.arslan.chocolife.data.StockInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONUtils {

    private static final String myTag = "myTag";


    private static final String KEY_RESULT = "result";
    private static final String KEY_TITLE = "title";
    private static final String KEY_TITLE_SHORT = "title_short";
    private static final String KEY_REVIEWS_RATE = "reviews_rate";
    private static final String KEY_BOUGHT = "bought";
    private static final String KEY_PRICE = "price";


    //keys for stock detail info
    private static final String KEY_IMAGES = "images";
    private static final String KEY_REVIEWS_NUMBER = "reviews_number";
    private static final String KEY_ECONOMY = "economy";
    private static final String KEY_TIMEOUT = "timeout";
    private static final String KEY_PROTECTION = "protection";

    private static final String KEY_PLACES = "places";
    private static final String KEY_PLACES_ADDRESS = "address";
    private static final String KEY_PLACES_LAT = "lat";
    private static final String KEY_PLACES_LON = "lon";
    private static final String KEY_PLACES_SCHEDULE = "schedule";
    private static final String KEY_PLACES_PHONES = "phones";

    private static final String KEY_SOCIAL_LINKS = "social_links";
    private static final String KEY_SOCIAL_LINKS_TWITTER = "twitter";
    private static final String KEY_SOCIAL_LINKS_FACEBOOK = "facebook";
    private static final String KEY_SOCIAL_LINKS_ODNOKLASSNIKI = "odnoklassniki";
    private static final String KEY_SOCIAL_LINKS_VKONTAKTE = "vkontakte";
    private static final String KEY_SOCIAL_LINKS_YOUTUBE = "youtube";
    private static final String KEY_SOCIAL_LINKS_MOIMIR = "moimir";
    private static final String KEY_SOCIAL_LINKS_INSTAGRAM = "instagram";


    private static final String KEY_FILES = "files";
    private static final String KEY_FILES_LINK = "link";
    private static final String KEY_FILES_NAME = "name";
    private static final String KEY_FILES_EXTENSION = "extension";


    private static final String KEY_EXPIRY_TIME = "expiry_time";
    private static final String KEY_OFFERS = "offers";
    private static final String KEY_TERMS = "terms";
    private static final String KEY_FEATURES = "features";

    private static final String KEY_TITLE_LONG = "title_long";
    private static final String KEY_DEAL_KIND = "deal_kind";
    private static final String KEY_INTERESTED = "interested";
    private static final String KEY_IS_FREE = "is_free";
    private static final String KEY_FULL_PRICE = "full_price";


    //keys for stocks
    private static final String KEY_DEAL_ID = "deal_id";
    private static final String KEY_DISCOUNT = "discount";

    private static final String KEY_IMAGE_KIND = "image_kind";
    private static final String KEY_IMAGE_URL = "image_url";
    private static final String KEY_IMAGE_URL_WIDE = "image_url_wide";

    //keys for categories
    private static final String KEY_CATEGORY_ID = "id";
    private static final String KEY_CATEGORY_TITLE_TRANSLIT = "title_translit";
    private static final String KEY_DEALS_NUMBER = "deals_number";
    private static final String KEY_SUBCATEGORIES = "sub_categories";

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
                String title = objectStock.getString(KEY_TITLE);
                int bought = objectStock.getInt(KEY_BOUGHT);
                String imageKind = objectStock.getString(KEY_IMAGE_KIND);
                String imageUrl = objectStock.getString(KEY_IMAGE_URL);
                String imageUrlWide = objectStock.getString(KEY_IMAGE_URL_WIDE);

                Stock stock = new Stock(deal_id, price, discount, titleShort, reviewsRate, title, bought, imageKind, imageUrl, imageUrlWide);
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

    public static List<Category> getCategoriesFromJSON(JSONObject jsonObject) {
        ArrayList<Category> result = new ArrayList<>();
        if (jsonObject == null) {
            return result;
        }
        try {
            JSONArray jsonArray = jsonObject.getJSONArray(KEY_RESULT);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject objectCategory = jsonArray.getJSONObject(i);
                int category_id = objectCategory.getInt(KEY_CATEGORY_ID);
                String title = objectCategory.getString(KEY_TITLE);
                String title_translit = objectCategory.getString(KEY_CATEGORY_TITLE_TRANSLIT);
                int deals_number = objectCategory.getInt(KEY_DEALS_NUMBER);
                ArrayList<Category> sub_categories = new ArrayList<>();
                JSONArray jsonArraySubcategories = objectCategory.getJSONArray(KEY_SUBCATEGORIES);

                for (int j = 0; j < jsonArraySubcategories.length(); j++) {
                    JSONObject objectSubcategory = jsonArraySubcategories.getJSONObject(j);
                    int sCategory_id = objectSubcategory.getInt(KEY_CATEGORY_ID);
                    String sTitle = objectSubcategory.getString(KEY_TITLE);
                    String sTitle_translit = objectSubcategory.getString(KEY_CATEGORY_TITLE_TRANSLIT);
                    int sDeals_number = objectSubcategory.getInt(KEY_DEALS_NUMBER);
                    Category subcategory = new Category(sCategory_id, sTitle, sTitle_translit, sDeals_number, new ArrayList<Category>());
                    sub_categories.add(subcategory);
                }
                Category category = new Category(category_id, title, title_translit, deals_number, sub_categories);
                result.add(category);
            }
        } catch (JSONException e) {
            Log.e(myTag, e.getMessage());
            e.printStackTrace();
        }
        return result;
    }


    public static StockInfo getStockInfoFromJSON(JSONObject jsonObject) {
        StockInfo result;
        if (jsonObject == null) {
            return null;
        }
        try {
            JSONObject objectStockInfo = jsonObject.getJSONObject(KEY_RESULT);

            JSONArray jsonArrayImages = objectStockInfo.getJSONArray(KEY_IMAGES);
            ArrayList<String> images = new ArrayList<>();
            for (int j = 0; j < jsonArrayImages.length(); j++) {
                images.add(jsonArrayImages.getString(j));
                Log.i("myImages", jsonArrayImages.getString(j));
            }
            String title_short = objectStockInfo.getString(KEY_TITLE_SHORT);
            String title = objectStockInfo.getString(KEY_TITLE);
            double reviews_rate = objectStockInfo.getDouble(KEY_REVIEWS_RATE);
            int bought_number = objectStockInfo.getInt(KEY_BOUGHT);
            int reviews_number = objectStockInfo.getInt(KEY_REVIEWS_NUMBER);
            int price = objectStockInfo.getInt(KEY_PRICE);
            int economy = objectStockInfo.getInt(KEY_ECONOMY);
            String timeout = objectStockInfo.getString(KEY_TIMEOUT);

            String protection = objectStockInfo.getString(KEY_PROTECTION);

            JSONArray jsonArrayPlaces = objectStockInfo.getJSONArray(KEY_PLACES);
            ArrayList<Place> places = new ArrayList<>();
            for (int j = 0; j < jsonArrayPlaces.length(); j++) {
                JSONObject jsonObjectPlace = jsonArrayPlaces.getJSONObject(j);
                String address = jsonObjectPlace.getString(KEY_PLACES_ADDRESS);
                double lat = jsonObjectPlace.getDouble(KEY_PLACES_LAT);
                double lon = jsonObjectPlace.getDouble(KEY_PLACES_LON);
                String schedule = jsonObjectPlace.getString(KEY_PLACES_SCHEDULE);
                JSONArray jsonArrayPhones = jsonObjectPlace.getJSONArray(KEY_PLACES_PHONES);
                ArrayList<String> phones = new ArrayList<>();
                for (int k = 0; k < jsonArrayPhones.length(); k++) {
                    phones.add(jsonArrayPhones.getString(k));
                }
                Place newPlace = new Place(address, lat, lon, schedule, phones);
                places.add(newPlace);
            }

            JSONObject jsonObjectSocialLinks = objectStockInfo.getJSONObject(KEY_SOCIAL_LINKS);
            HashMap<String, String> socialLinks = new HashMap<>();
            socialLinks.put(KEY_SOCIAL_LINKS_TWITTER, jsonObjectSocialLinks.getString(KEY_SOCIAL_LINKS_TWITTER));
            socialLinks.put(KEY_SOCIAL_LINKS_FACEBOOK, jsonObjectSocialLinks.getString(KEY_SOCIAL_LINKS_FACEBOOK));
            socialLinks.put(KEY_SOCIAL_LINKS_ODNOKLASSNIKI, jsonObjectSocialLinks.getString(KEY_SOCIAL_LINKS_ODNOKLASSNIKI));
            socialLinks.put(KEY_SOCIAL_LINKS_VKONTAKTE, jsonObjectSocialLinks.getString(KEY_SOCIAL_LINKS_VKONTAKTE));
            socialLinks.put(KEY_SOCIAL_LINKS_YOUTUBE, jsonObjectSocialLinks.getString(KEY_SOCIAL_LINKS_YOUTUBE));
            socialLinks.put(KEY_SOCIAL_LINKS_MOIMIR, jsonObjectSocialLinks.getString(KEY_SOCIAL_LINKS_MOIMIR));
            socialLinks.put(KEY_SOCIAL_LINKS_INSTAGRAM, jsonObjectSocialLinks.getString(KEY_SOCIAL_LINKS_INSTAGRAM));

            JSONArray jsonArrayFiles = objectStockInfo.getJSONArray(KEY_FILES);
            ArrayList<StockFile> stockFileArrayList = new ArrayList<>();
            for (int j = 0; j < jsonArrayFiles.length(); j++) {
                JSONObject jsonObjectFile = jsonArrayFiles.getJSONObject(j);
                String link = jsonObjectFile.getString(KEY_FILES_LINK);
                String name = jsonObjectFile.getString(KEY_FILES_NAME);
                String extension = jsonObjectFile.getString(KEY_FILES_EXTENSION);
                stockFileArrayList.add(new StockFile(link, name, extension));
            }

            String terms = objectStockInfo.getString(KEY_TERMS);
            String features = objectStockInfo.getString(KEY_FEATURES);

            result = new StockInfo.Builder(
                    images,
                    title_short,
                    title,
                    reviews_rate,
                    bought_number,
                    reviews_number,
                    price, economy,
                    timeout,
                    protection,
                    places,
                    socialLinks,
                    stockFileArrayList,
                    terms,
                    features
            )
                    .build();
//
//                result = new StockInfo.Builder(images,title_short,title,reviews_rate,bought_number,reviews_number,price,economy,expiry_time,protection,places,socialLinks,stockFileArrayList)
            return result;

        } catch (JSONException e) {
            Log.e(myTag, e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


    public static String getDealTermFromJSON(JSONObject jsonObject) {
        String result;
        if (jsonObject == null) {
            return null;
        }
        try {
            JSONObject objectStockTerm = jsonObject.getJSONObject(KEY_RESULT);
//            Log.i("json", objectStockTerm.toString());
            result = objectStockTerm.getString(KEY_TERMS);
//            Log.i("json", objectStockTerm.getString(KEY_TERMS));
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String getDealFeaturesFromJSON(JSONObject jsonObject) {
        String result;
        if (jsonObject == null) {
            return null;
        }
        try {
            JSONObject objectStockFeatures = jsonObject.getJSONObject(KEY_RESULT);
            result = objectStockFeatures.getString(KEY_FEATURES);
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
