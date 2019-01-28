package com.example.arslan.chocolife.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkUtils {

    private static final String BASE_URL_STOCKS = "https://chocolife.me/mobileapi/v3_3/deals?";
    private static final String BASE_URL_CATEGORIES = "https://chocolife.me/mobileapi/v3_3/categories?";
    private static final String BASE_URL_DEAL_INFO = "https://chocolife.me/mobileapi/v3/deal/?";


    private static final String PARAMS_DEAL_ID = "deal_id";



    private static final String PARAMS_SORT_BY = "sort";
    private static final String PARAMS_PAGE = "page";
    private static final String PARAMS_TOWN_ID = "town_id";
    private static final String PARAMS_CATEGORY_ID = "category_id";


//    null, price_asc, price_desc, popular, rating_desc, new
    public static final String SORT_PRICE_ASC = "price_asc";
    public static final String SORT_PRICE_DESC = "price_desc";
    public static final String SORT_POPULARITY = "popular";
    public static final String SORT_RATING_DESC = "rating_desc";
    public static final String SORT_NEW = "new";

    public static final int DEFAULT_CATEGORY_ID = 1;



    public static URL buiildURLStocks(String sort, int page, int town_id, int category_id){
        URL result = null;
        Uri uri = Uri.parse(BASE_URL_STOCKS).buildUpon()
                .appendQueryParameter(PARAMS_SORT_BY, sort)
                .appendQueryParameter(PARAMS_PAGE, Integer.toString(page))
                .appendQueryParameter(PARAMS_TOWN_ID, Integer.toString(town_id))
                .appendQueryParameter(PARAMS_CATEGORY_ID, Integer.toString(category_id))
                .build();
        try {
            result = new URL(uri.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static URL buildURLCategories(int town_id){
        URL result = null;
        Uri uri = Uri.parse(BASE_URL_CATEGORIES).buildUpon()
                .appendQueryParameter(PARAMS_TOWN_ID, Integer.toString(town_id))
                .build();
        try {
            result = new URL(uri.toString());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static URL buildURLDealInfo(String deal_id){

        URL result = null;
        Uri uri = Uri.parse(BASE_URL_DEAL_INFO).buildUpon()
                .appendQueryParameter(PARAMS_DEAL_ID, deal_id)
                .build();
        try {
            result = new URL(uri.toString());
//            Log.i("myImages", uri.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return result;

    }



    public static class JSONLoader extends AsyncTaskLoader<JSONObject>{

        private Bundle bundle;
        private OnStartLoadingListener onStartLoadingListener;


        public interface OnStartLoadingListener{
            void onStartLoading();
        }

        public void setOnStartLoadingListener(OnStartLoadingListener onStartLoadingListener) {
            this.onStartLoadingListener = onStartLoadingListener;
        }

        public JSONLoader(@NonNull Context context, Bundle bundle) {
            super(context);
            this.bundle = bundle;
        }

        @Override
        protected void onStartLoading() {
            super.onStartLoading();
            if(onStartLoadingListener != null){
                onStartLoadingListener.onStartLoading();
            }
            forceLoad();
        }

        @Nullable
        @Override
        public JSONObject loadInBackground() {
            if(bundle == null){
                return null;
            }
            String urlAsString = bundle.getString("url");
            URL url = null;
            try {
                url = new URL(urlAsString);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            JSONObject result = null;
            if(url == null){
                return null;
            }
            HttpURLConnection urlConnection = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = "";
                StringBuilder builder = new StringBuilder();
                while ((line = reader.readLine()) != null){
                    builder.append(line);
                }
                result = new JSONObject(builder.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if(urlConnection != null){
                    urlConnection.disconnect();
                }

            }
            return result;
        }
    }
}
