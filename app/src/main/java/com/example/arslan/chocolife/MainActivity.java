package com.example.arslan.chocolife;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.example.arslan.chocolife.adapters.CategoryAdapter;
//import com.example.arslan.chocolife.adapters.StockAdapter;
import com.example.arslan.chocolife.data.Category;
//import com.example.arslan.chocolife.data.Stock;
import com.example.arslan.chocolife.utils.JSONUtils;
import com.example.arslan.chocolife.utils.NetworkUtils;

import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private static final int LAYOUT = R.layout.activity_main;

    private static final String myResultTag = "myResult";
    private static int methodOfSort = 0;
    private int openedCategoryPosition = 0;

//    private Toolbar toolbar;
   



    private CategoryAdapter categoryAdapter;
    private LoaderManager loaderManager;
    private static final int loaderId = 10;
    private RecyclerView recyclerViewCategories;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment mFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_stocks:
                    mFragment = new StocksFragment();
                    break;
                case R.id.navigation_purchase:
                    mFragment = new PurchaseFragment();
                    break;
                case R.id.navigation_goods:
                    mFragment = new ProfileFragment();
                    break;
                case R.id.navigation_profile:

                    mFragment = new ProfileFragment();
                    break;
            }
            if (mFragment != null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, mFragment);
                fragmentTransaction.commit();
                return true;
            } else {
                return false;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);



//        initToolbar();


        categoryAdapter = new CategoryAdapter();
        recyclerViewCategories = findViewById(R.id.recyclerViewParent);
        recyclerViewCategories.setAdapter(categoryAdapter);
        recyclerViewCategories.setLayoutManager(new LinearLayoutManager(this));

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        loaderManager = LoaderManager.getInstance(this);
//        categoryAdapter.setOnCategoryClickListener(new CategoryAdapter.OnCategoryClickListener() {
//
//            /**
//             * Subcategory записаны правильно
//             * @param position
//             */
//            @Override
//            public void onCategoryClick(int position) {
//                Toast.makeText(getApplicationContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();
//                ArrayList<Category> categoriesList = (ArrayList<Category>) categoryAdapter.getCategories();
//
//
//
//////                Log.i("myCategories", categoriesList.get(position).getSub_categories().toString());
//
//                ArrayList<RecyclerView> recyclerViewArrayList = categoryAdapter.getRecyclerViewSubcategoriesAsArray();
////                Log.i("myCategories", recyclerViewArrayList.toString());
//                recyclerViewArrayList.get(position).setVisibility(View.VISIBLE);
//                recyclerViewArrayList.get(openedCategoryPosition).setVisibility(View.GONE);
//                openedCategoryPosition = position;
//
////                .get(position).setVisibility(View.VISIBLE)
//            }
//        });
////        recyclerViewTest.setAdapter(stockAdapter);
//
////        downloadData(NetworkUtils.SORT_POPULARITY,1,1,1);
        downloadData(1);

    }

//    private void initToolbar() {
//        toolbar = findViewById(R.id.includeToolBar);
//        toolbar.setLogo(R.drawable.logo);
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem menuItem) {
//                int id = menuItem.getItemId();
//                switch (id) {
//                    case R.id.item_sort:
//                        showAlertDialogSortButtonClicked();
//                        break;
//                    case R.id.item_map:
//                        break;
//                    case R.id.item_favourite:
//                        break;
//                }
//                return false;
//            }
//        });
//        toolbar.inflateMenu(R.menu.menu);
//    }


    private void downloadData(int town_id){
        URL url = NetworkUtils.buildURLCategories(town_id);
        Log.i(myResultTag, url.toString());
        Bundle bundle =  new Bundle();
        bundle.putString("url", url.toString());

        /**
         * @param LoaderCallbacks - это слушатель изменений он реализован тут поэтому this
         */
        loaderManager.restartLoader(loaderId, bundle, loaderForCategories);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item_sort:
                showAlertDialogSortButtonClicked();
                break;
            case R.id.item_map:
                break;
            case R.id.item_favourite:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void showAlertDialogSortButtonClicked() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        String[] sort = getResources().getStringArray(R.array.methodOfSort);
        builder.setSingleChoiceItems(sort, methodOfSort, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Bundle bundle = new Bundle();

                switch (which) {
                    case 0:
                        bundle.putString("methodOfSort", NetworkUtils.SORT_POPULARITY);
                        break;
                    case 1:
                        bundle.putString("methodOfSort", NetworkUtils.SORT_RATING_DESC);
                        break;
                    case 2:
                        bundle.putString("methodOfSort", NetworkUtils.SORT_NEW);
                        break;
                    case 3:
                        bundle.putString("methodOfSort", NetworkUtils.SORT_PRICE_ASC);
                        break;
                    case 4:
                        bundle.putString("methodOfSort", NetworkUtils.SORT_PRICE_DESC);
                        break;
                }
                Fragment mFragment = new StocksFragment();
                mFragment.setArguments(bundle);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, mFragment);
                fragmentTransaction.commit();



            }
        });


        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private LoaderManager.LoaderCallbacks<JSONObject> loaderForCategories = new LoaderManager.LoaderCallbacks<JSONObject>() {
        @NonNull
        @Override
        public Loader<JSONObject> onCreateLoader(int i, @Nullable Bundle bundle) {
            NetworkUtils.JSONLoader jsonLoader = new NetworkUtils.JSONLoader(getApplicationContext(), bundle);
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
            ArrayList<Category> allCategories = (ArrayList<Category>) JSONUtils.getCategoriesFromJSON(jsonObject);
            Log.i(myResultTag, allCategories.toString());

            if (allCategories != null && !allCategories.isEmpty()) {
                categoryAdapter.setCategories(allCategories);
            }
            loaderManager.destroyLoader(loaderId);
        }

        @Override
        public void onLoaderReset(@NonNull Loader<JSONObject> loader) {

        }
    };


}
