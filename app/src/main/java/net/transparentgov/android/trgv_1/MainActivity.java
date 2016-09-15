/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.transparentgov.android.trgv_1;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 */
public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "http://transparentgov.net", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        switch (AppCompatDelegate.getDefaultNightMode()) {
            case AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM:
                menu.findItem(R.id.menu_night_mode_system).setChecked(true);
                break;
            case AppCompatDelegate.MODE_NIGHT_AUTO:
                menu.findItem(R.id.menu_night_mode_auto).setChecked(true);
                break;
            case AppCompatDelegate.MODE_NIGHT_YES:
                menu.findItem(R.id.menu_night_mode_night).setChecked(true);
                break;
            case AppCompatDelegate.MODE_NIGHT_NO:
                menu.findItem(R.id.menu_night_mode_day).setChecked(true);
                break;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.menu_night_mode_system:
                setNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
            case R.id.menu_night_mode_day:
                setNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case R.id.menu_night_mode_night:
                setNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case R.id.menu_night_mode_auto:
                setNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setNightMode(@AppCompatDelegate.NightMode int nightMode) {
        AppCompatDelegate.setDefaultNightMode(nightMode);

        if (Build.VERSION.SDK_INT >= 11) {
            recreate();
        }
    }

    private void setupViewPager(ViewPager viewPager) {





        Adapter adapter = new Adapter(getSupportFragmentManager());
        /*adapter.addFragment(new CheeseListFragment(), "Category 1");
        adapter.addFragment(new CheeseListFragment(), "Category 2");
        adapter.addFragment(new CheeseListFragment(), "Category 3");*/








        CheeseListFragment city_fragment = new CheeseListFragment();
        Bundle city_bundle = new Bundle();
        city_bundle.putString("area_name", "city" );
        city_fragment.setArguments(city_bundle);
        adapter.addFragment(city_fragment, "city");


        CheeseListFragment Santa_Monica_fragment = new CheeseListFragment();
        Bundle Santa_Monica_bundle = new Bundle();
        Santa_Monica_bundle.putString("area_name", "Santa_Monica" );
        Santa_Monica_fragment.setArguments(Santa_Monica_bundle);
        adapter.addFragment(Santa_Monica_fragment, "Santa_Monica");



        CheeseListFragment Newport_Beach_fragment = new CheeseListFragment();
        Bundle Newport_Beach_bundle = new Bundle();
        Newport_Beach_bundle.putString("area_name", "Newport_Beach" );
        Newport_Beach_fragment.setArguments(Newport_Beach_bundle);
        adapter.addFragment(Newport_Beach_fragment, "Newport_Beach");



        CheeseListFragment Palo_Alto_fragment = new CheeseListFragment();
        Bundle Palo_Alto_bundle = new Bundle();
        Palo_Alto_bundle.putString("area_name", "Palo_Alto" );
        Palo_Alto_fragment.setArguments(Palo_Alto_bundle);
        adapter.addFragment(Palo_Alto_fragment, "Palo_Alto");



        CheeseListFragment Shoreline_fragment = new CheeseListFragment();
        Bundle Shoreline_bundle = new Bundle();
        Shoreline_bundle.putString("area_name", "Shoreline" );
        Shoreline_fragment.setArguments(Shoreline_bundle);
        adapter.addFragment(Shoreline_fragment, "Shoreline");



        CheeseListFragment New_York_fragment = new CheeseListFragment();
        Bundle New_York_bundle = new Bundle();
        New_York_bundle.putString("area_name", "New_York" );
        New_York_fragment.setArguments(New_York_bundle);
        adapter.addFragment(New_York_fragment, "New_York");


        CheeseListFragment Chicago_fragment = new CheeseListFragment();
        Bundle Chicago_bundle = new Bundle();
        Chicago_bundle.putString("area_name", "Chicago" );
        Chicago_fragment.setArguments(Chicago_bundle);
        adapter.addFragment(Chicago_fragment, "Chicago");


        CheeseListFragment San_Francisco_fragment = new CheeseListFragment();
        Bundle San_Francisco_bundle = new Bundle();
        San_Francisco_bundle.putString("area_name", "San_Francisco" );
        San_Francisco_fragment.setArguments(San_Francisco_bundle);
        adapter.addFragment(San_Francisco_fragment, "San_Francisco");


        CheeseListFragment Los_Angeles_fragment = new CheeseListFragment();
        Bundle Los_Angeles_bundle = new Bundle();
        Los_Angeles_bundle.putString("area_name", "Los_Angeles" );
        Los_Angeles_fragment.setArguments(Los_Angeles_bundle);
        adapter.addFragment(Los_Angeles_fragment, "Los_Angeles");



        /*adapter.addFragment(new CheeseListFragment(), "Newport Beach");
        adapter.addFragment(new CheeseListFragment(), "Palo Alto");
        adapter.addFragment(new CheeseListFragment(), "Shoreline");
        adapter.addFragment(new CheeseListFragment(), "New York");
        adapter.addFragment(new CheeseListFragment(), "Chicago");
        adapter.addFragment(new CheeseListFragment(), "San Francisco");*/
       // adapter.addFragment(new CheeseListFragment(), "Los Angeles");






        viewPager.setAdapter(adapter);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
