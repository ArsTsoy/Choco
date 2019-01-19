package com.example.arslan.chocolife.DetailFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arslan.chocolife.R;


public class InfoStockFragment extends Fragment {

    private static final int LAYOUT = R.layout.fragment_info_stock;
    private View view;


    public InfoStockFragment() {
        // Required empty public constructor
    }



    public static InfoStockFragment getInstance(){
        Bundle args = new Bundle();
        InfoStockFragment fragment = new InfoStockFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        return view;
    }
}
