package com.example.arslan.chocolife.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.arslan.chocolife.DetailFragments.InfoStockFragment;

public class TabsPagerFragmentAdapter extends FragmentPagerAdapter {

    private String [] tabs;

    public TabsPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
        tabs = new String[]{
                "Tab1",
                "Tab2",
                "Tab3",
                "Tab4",
                "Tab5",
                "Tab6"
        };
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0:
                return InfoStockFragment.getInstance();

            case 1:
                return InfoStockFragment.getInstance();

            case 2:
                return InfoStockFragment.getInstance();

        }
        return null;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
