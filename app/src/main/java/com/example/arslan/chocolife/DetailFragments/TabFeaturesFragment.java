package com.example.arslan.chocolife.DetailFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.arslan.chocolife.R;
import com.example.arslan.chocolife.utils.JSONUtils;
import com.example.arslan.chocolife.utils.NetworkUtils;

import org.json.JSONObject;

import java.net.URL;

public class TabFeaturesFragment extends Fragment {
    private final String TAG = "TabFeaturesFragment";
    private static final int LAYOUT = R.layout.fragment_features_tab;
    private WebView webViewFeaturesTab;
    private LoaderManager loaderManager;
    private final int loaderFeatureId = 4;
    private String deal_id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(LAYOUT, container, false);
        webViewFeaturesTab = view.findViewById(R.id.webViewFeatrure);
        try {
            Bundle bundle = this.getArguments();
            deal_id = bundle.getString("deal_id");
//            Log.i(TAG, deal_id);
        } catch (NullPointerException e) {
            Toast.makeText(getActivity(), "Error terms Fragment", Toast.LENGTH_SHORT).show();
        }
//        configureWebView();
        loaderManager = LoaderManager.getInstance(this);
        downloadData(deal_id);
        return view;
    }

    private void downloadData(String deal_id) {
        URL url = NetworkUtils.buildURLDealInfo(deal_id);
        Bundle bundle = new Bundle();
        bundle.putString("url", url.toString());
        loaderManager.restartLoader(loaderFeatureId, bundle, loaderForTerm);
    }


    private LoaderManager.LoaderCallbacks<JSONObject> loaderForTerm = new LoaderManager.LoaderCallbacks<JSONObject>() {
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
//            Log.i("json", jsonObject.toString());
            String term = JSONUtils.getDealFeaturesFromJSON(jsonObject);
//            Log.i(TAG, term);
            webViewFeaturesTab.loadDataWithBaseURL(null, term,"text/html", "utf-8", null);


            loaderManager.destroyLoader(loaderFeatureId);

//            isLoading = false;
        }

        @Override
        public void onLoaderReset(@NonNull Loader<JSONObject> loader) {

        }
    };
}
