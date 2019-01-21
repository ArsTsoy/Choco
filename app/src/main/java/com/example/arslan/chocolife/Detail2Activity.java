package com.example.arslan.chocolife;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import com.example.arslan.chocolife.DetailFragments.TabInfoFragment;
import com.example.arslan.chocolife.DetailFragments.TabTermsFragment;
import com.example.arslan.chocolife.adapters.SectionsPagerAdapter;

public class Detail2Activity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private SectionsPagerAdapter sectionsPagerAdapter;
    private ViewPager viewPager;
    private String deal_id;
//    private OnDataPass onDataPass;






    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);


        Bundle extras = getIntent().getExtras();
        if(extras!=null) {

            deal_id = extras.getString("deal_id");
//            Toast.makeText(this, "deal_id = " + deal_id, Toast.LENGTH_SHORT).show();
//            onDataPass.onDataPass(deal_id);
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.container);

        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager){
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        Bundle bundle = new Bundle();
        bundle.putString("deal_id", deal_id);

        TabInfoFragment tabInfoFragment = new TabInfoFragment();
        tabInfoFragment.setArguments(bundle);
        TabTermsFragment tabTermsFragment = new TabTermsFragment();
        tabTermsFragment.setArguments(bundle);

        adapter.addFragment(tabInfoFragment, "Информация");
        adapter.addFragment(tabTermsFragment, "Условия");
//        adapter.addFragment(tabInfoFragment, "Условия");
//        adapter.addFragment(tabInfoFragment, "Особенности");
//        adapter.addFragment(new Tab1Fragment(), "TAB1");
//        adapter.addFragment(new Tab2Fragment(), "TAB2");
//        adapter.addFragment(new Tab3Fragment(), "TAB3");
        viewPager.setAdapter(adapter);
    }







}
