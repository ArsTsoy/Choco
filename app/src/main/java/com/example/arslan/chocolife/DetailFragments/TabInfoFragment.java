package com.example.arslan.chocolife.DetailFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.arslan.chocolife.OnDataPass;
import com.example.arslan.chocolife.R;
import com.example.arslan.chocolife.data.Place;
import com.example.arslan.chocolife.data.Stock;
import com.example.arslan.chocolife.data.StockInfo;
import com.example.arslan.chocolife.utils.DateUtils;
import com.example.arslan.chocolife.utils.JSONUtils;
import com.example.arslan.chocolife.utils.NetworkUtils;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;

public class TabInfoFragment extends Fragment implements OnMapReadyCallback {

    private static final int LAYOUT = R.layout.fragment_info_tab;
    private String deal_id;
    private LoaderManager loaderManager;
    private int loaderStockInfoId = 1;

    private ViewFlipper view_flipper;
    private TextView textViewDetailTitleShort;
    private TextView textViewDetailTitle;
    private TextView textViewReviewsRate;
    private TextView textViewBoughtNumber;
    private TextView textViewReviewsCount;
    private TextView textViewPriceStockInfo;
    private TextView textViewEconomyStockInfo;

    private TextView textViewDealTimeout;
    private TextView textViewProtection;

    private GoogleMap googleMap;
    private MapView mapViewAddress;

    private double lat;
    private double lon;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(LAYOUT, container, false);

        view_flipper = view.findViewById(R.id.view_flipper);
        textViewDetailTitleShort = view.findViewById(R.id.textViewDetailTitleShort);
        textViewDetailTitle = view.findViewById(R.id.textViewDetailTitle);
        textViewReviewsRate = view.findViewById(R.id.textViewReviewsRate);
        textViewBoughtNumber = view.findViewById(R.id.textViewBoughtNumber);
        textViewReviewsCount = view.findViewById(R.id.textViewReviewsCount);
        textViewPriceStockInfo = view.findViewById(R.id.textViewPriceStockInfo);
        textViewEconomyStockInfo = view.findViewById(R.id.textViewEconomyStockInfo);

        textViewDealTimeout = view.findViewById(R.id.textViewDealTimeout);
        textViewProtection = view.findViewById(R.id.textViewProtection);
//        mapViewAddress  = view.findViewById(R.id.mapViewAddress);
//        mapViewAddress.onCreate(savedInstanceState);
//        mapViewAddress.getMapAsync(this);

        configureViewFlipper();
        loaderManager = LoaderManager.getInstance(this);
        try {
            Bundle bundle = this.getArguments();
            deal_id = bundle.getString("deal_id");

            downloadData(deal_id);
        } catch (NullPointerException e) {
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        LatLng deal_location = new LatLng(lat,lon);
        this.googleMap.addMarker(new MarkerOptions().position(deal_location).title("Sydney Marker"));
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(deal_location));
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }

    private void createMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) SupportMapFragment.newInstance();
        mapFragment.getMapAsync(this);
        getChildFragmentManager().beginTransaction().replace(R.id.mapLayout, mapFragment).commit();
    }

    private void setLatLon(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    private void configureMapView() {

    }

    private void configureViewFlipper() {
        view_flipper.setFlipInterval(3000);
        view_flipper.setAutoStart(true);
    }


    private void downloadData(String deal_id) {
        URL url = NetworkUtils.buildURLDealInfo(deal_id);
        Bundle bundle = new Bundle();
        bundle.putString("url", url.toString());

        loaderManager.restartLoader(loaderStockInfoId, bundle, loaderForStockInfo);
    }

    private void addImageToViewFlipper(String image_url){
        ImageView newImage = new ImageView(getActivity());
        newImage.setAdjustViewBounds(true);
        newImage.setScaleType(ImageView.ScaleType.FIT_XY);
        Picasso.get().load(image_url).into(newImage);
        view_flipper.addView(newImage);
    }


    private LoaderManager.LoaderCallbacks<JSONObject> loaderForStockInfo = new LoaderManager.LoaderCallbacks<JSONObject>() {
        @NonNull
        @Override
        public Loader<JSONObject> onCreateLoader(int i, @Nullable Bundle bundle) {
            NetworkUtils.JSONLoader jsonLoader = new NetworkUtils.JSONLoader(getActivity(), bundle);
//            jsonLoader.setOnStartLoadingListener(new NetworkUtils.JSONLoader.OnStartLoadingListener() {
//                @Override
//                public void onStartLoading() {
//                    isLoading = true;
//                }
//            });
            return jsonLoader;
        }

        @Override
        public void onLoadFinished(@NonNull Loader<JSONObject> loader, JSONObject jsonObject) {
            StockInfo stockInfo = JSONUtils.getStockInfoFromJSON(jsonObject);

            ArrayList<String> images = stockInfo.getImages();
            if(images != null && !images.isEmpty()){
                for (int i = 0; i < images.size(); i++) {
                    addImageToViewFlipper(images.get(i));
                }
            }
            textViewDetailTitleShort.setText(stockInfo.getTitle_short());
            textViewDetailTitle.setText(stockInfo.getTitle());
            textViewReviewsRate.setText(Double.toString(stockInfo.getReviews_rate()));
            textViewBoughtNumber.setText(String.format(getResources().getString(R.string.tab_info_bought_number), Integer.toString(stockInfo.getBought_number())));
            textViewReviewsCount.setText(String.format(getResources().getString(R.string.tab_info_reviews_number), Integer.toString(stockInfo.getReviews_number())));
            textViewPriceStockInfo.setText(String.format(getResources().getString(R.string.tab_info_price_from), Integer.toString(stockInfo.getPrice())));
            textViewEconomyStockInfo.setText(String.format(getResources().getString(R.string.tab_info_economy_from), Integer.toString(stockInfo.getEconomy())));

            switch (stockInfo.getProtection()){
                case "standart":
                    textViewProtection.setText(String.format(getResources().getString(R.string.tab_info_protection),"Стандартная"));
                    break;
                case "extended":
                    textViewProtection.setText(String.format(getResources().getString(R.string.tab_info_protection),"Расширенная"));
                    break;
                case "absolute":
                    textViewProtection.setText(String.format(getResources().getString(R.string.tab_info_protection),"Абсолютная"));
                    break;
            }
            try {
                String dateAsString = DateUtils.getDate(stockInfo.getTimeout(),"yyyy-MM-dd HH:mm:ss", "d MMMM yyyy", new Locale("ru"));
                textViewDealTimeout.setText(String.format(getResources().getString(R.string.tab_info_dealTimeout), dateAsString));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            ArrayList<Place> places = stockInfo.getPlaces();
            Place place = places.get(0);
            setLatLon(place.getLat(), place.getLon());
            createMap();

            loaderManager.destroyLoader(loaderStockInfoId);
//            page++;
//            isLoading = false;
        }

        @Override
        public void onLoaderReset(@NonNull Loader<JSONObject> loader) {

        }
    };


}
