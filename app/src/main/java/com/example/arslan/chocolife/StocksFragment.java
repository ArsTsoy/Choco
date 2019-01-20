package com.example.arslan.chocolife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.arslan.chocolife.adapters.StockAdapter;
import com.example.arslan.chocolife.data.Stock;
import com.example.arslan.chocolife.utils.JSONUtils;
import com.example.arslan.chocolife.utils.NetworkUtils;

import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;


///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link StocksFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link StocksFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class StocksFragment extends Fragment  {

    private static final String myResultTag = "myResult";

    private StockAdapter stockAdapter;
    private RecyclerView recyclerViewStocks;



    private LoaderManager loaderManager;
    private static final int loaderStockId = 1;
//    private static final int loaderCategoryId = 2;

    private int page = 1;
    private boolean isLoading = false;
    private String methodOfSort;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("Stage","OnCreate");
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("Stage","OnCreateView");
        View view = inflater.inflate(R.layout.fragment_stocks, container, false);
        recyclerViewStocks = view.findViewById(R.id.recyclerViewStocks);
        stockAdapter = new StockAdapter();
        recyclerViewStocks.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewStocks.setAdapter(stockAdapter);
        loaderManager = LoaderManager.getInstance(this);

        try{

            Bundle bundle = this.getArguments();
            methodOfSort = bundle.getString("methodOfSort");
        }catch (NullPointerException e){
            methodOfSort = NetworkUtils.SORT_POPULARITY;

        }





        downloadData(methodOfSort, page, 1,1);
        stockAdapter.setOnReachEndListener(new StockAdapter.OnReachEndListener() {
            @Override
            public void onReachEnd() {
                if(!isLoading){
                    downloadData(methodOfSort, page, 1,1);
                }
            }
        });
        stockAdapter.setOnStockClickLstener(new StockAdapter.OnStockClickLstener() {
            @Override
            public void onStockClick(int position) {
                Intent detailIntent = new Intent(getContext(), Detail2Activity.class );
                int deal_id = stockAdapter.getStock(position).getDealId();
                detailIntent.putExtra("deal_id", Integer.toString(deal_id));


//                Toast.makeText(getContext(), Integer.toString(stockAdapter.getStock(position).getDealId()), Toast.LENGTH_SHORT).show();
                startActivity(detailIntent);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.i("Stage","OnActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }





    private void downloadData(String sort, int page, int town_id, int category_id) {
        URL url = NetworkUtils.buiildURLStocks(sort, page, town_id, category_id);
        Log.i(myResultTag, url.toString());
        Bundle bundle = new Bundle();
        bundle.putString("url", url.toString());

        /**
         * @param LoaderCallbacks - это слушатель изменений он реализован тут поэтому this
         */
        loaderManager.restartLoader(loaderStockId, bundle, loaderForStocks);
    }



    private LoaderManager.LoaderCallbacks<JSONObject> loaderForStocks = new LoaderManager.LoaderCallbacks<JSONObject>() {
        @NonNull
        @Override
        public Loader<JSONObject> onCreateLoader(int i, @Nullable Bundle bundle) {
            NetworkUtils.JSONLoader jsonLoader = new NetworkUtils.JSONLoader(getActivity(), bundle);
            jsonLoader.setOnStartLoadingListener(new NetworkUtils.JSONLoader.OnStartLoadingListener() {
                @Override
                public void onStartLoading() {
                    isLoading = true;
                }
            });
            return jsonLoader;
        }

        @Override
        public void onLoadFinished(@NonNull Loader<JSONObject> loader, JSONObject jsonObject) {
            ArrayList<Stock> allStocks = (ArrayList<Stock>) JSONUtils.getStocksFromJSON(jsonObject);
            Log.i(myResultTag, allStocks.toString());

            if (allStocks != null && !allStocks.isEmpty()) {
                stockAdapter.addStocks(allStocks);
            }
            loaderManager.destroyLoader(loaderStockId);
            page++;
            isLoading = false;
        }

        @Override
        public void onLoaderReset(@NonNull Loader<JSONObject> loader) {

        }
    };
}
