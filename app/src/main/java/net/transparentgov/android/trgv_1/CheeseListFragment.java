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

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CheeseListFragment extends Fragment {


    public String area_;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {





        RecyclerView rv = (RecyclerView) inflater.inflate(
                R.layout.fragment_cheese_list, container, false);
        setupRecyclerView(rv);
        return rv;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {

        area_ = this.getArguments().getString("area_name");


        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new SimpleStringRecyclerViewAdapter(getActivity(),

                getSublistByArea(area_)));
    }



    /*private List<String> getRandomSublist(String[] array, int amount) {
        ArrayList<String> list = new ArrayList<>(amount);
        Random random = new Random();
        while (list.size() < amount) {
            list.add(array[random.nextInt(array.length)]);
        }
        return list;
    }*/



    private List<String> getSublistByArea(String __area) {
        ArrayList<String> list = new ArrayList<>();

         if (__area.equals("city")) {

             list.add("city_zoning");
             list.add("city_general_land_use");
             list.add("city_address");
             list.add("city_parcels");
             list.add("city_streets");
             list.add("city_parks");
             list.add("city_schools");


             list.add("city_law_beat");
             list.add("city_law_district");
             list.add("city_law_geoproximity");
             list.add("city_law_reportarea");
             list.add("city_fire_district");

             list.add("city_neighbor");
             list.add("city_fire_geoproximity");
             list.add("city_fire_district");
             list.add("city_fire_agency");
             list.add("city_fire_station_boundary");
         }



        if (__area.equals("Santa_Monica")) {

            list.add("Santa_Monica_Zoning");
            list.add("Santa_Monica_Bike_Route");
            list.add("Santa_Monica_Parcels");
            list.add("Santa_Monica_Streets");
            list.add("Santa_Monica_Buildings");
            list.add("Santa_Monica_Speed_Limit");
            list.add("Santa_Monica_Streets_Sweeping");



        }




        if (__area.equals("Newport_Beach")) {

            list.add("Newport_Beach_Zoning");
            list.add("Newport_Beach_Address");
            list.add("Newport_Beach_Parcels");
            list.add("Newport_Beach_Streets");


            list.add("Newport_Beach_Right_Of_Way");
            list.add("Newport_Beach_Parks");
            list.add("Newport_Beach_General_Land_Use");



        }



        if (__area.equals("Palo_Alto")) {

            list.add("Palo_Alto_Zoning");
            list.add("Palo_Alto_Address");
            list.add("Palo_Alto_Parcels");
            list.add("Palo_Alto_Streets");

            list.add("Palo_Alto_Building");
            list.add("Palo_Alto_PAN_Areas");
            list.add("Palo_Alto_Right_of_way");




        }




        if (__area.equals("Shoreline")) {

            list.add("Shoreline_Zoning");
            list.add("Shoreline_Land_Use_Comp_Plan");
            list.add("Shoreline_Address_Central");
            list.add("Shoreline_Tax_Parcel_Central");
            list.add("Shoreline_Buildings");
            list.add("Shoreline_Park");
            list.add("Shoreline_Neighborhood");
            list.add("Shoreline_Monument");
            list.add("Shoreline_Land_Mark");
            list.add("Shoreline_Encumbrance");
            list.add("Shoreline_Art");
            list.add("Shoreline_Street");
            list.add("Shoreline_Street_Light");

            list.add("Shoreline_Sidewalk");
            list.add("Shoreline_Pavement_Condition");
            list.add("Shoreline_Pavement");
            list.add("Shoreline_Intersections");
            list.add("Shoreline_Guard_Rail");
            list.add("Shoreline_Fence");
            list.add("Shoreline_Curb_Ramp");
            list.add("Shoreline_Curb");
            list.add("Shoreline_Bridge");
            list.add("Shoreline_Bike_Facility");
            list.add("Shoreline_Railroad");






        }






        if (__area.equals("New_York")) {

            list.add("New_York_Zone_Districts");
            list.add("New_York_Commercial_Zone");
            list.add("New_York_Boroughs");
            list.add("New_York_Neighborhoods");
            list.add("New_York_Community_Districts");
            list.add("New_York_Building_Demolition");
            list.add("New_York_Building");
            list.add("New_York_Parks");
            list.add("New_York_Parking_Lots");


        }





        if (__area.equals("Chicago")) {

            list.add("Chicago_Zoning");
            list.add("Chicago_Planning_Districts");
            list.add("Chicago_Planning_Regions");
            list.add("Chicago_Community");
            list.add("Chicago_Neighborhoods");
            list.add("Chicago_Industrial_Corridors");
            list.add("Chicago_Enterprises_Communities");
            list.add("Chicago_Landmark_Districts");
            list.add("Chicago_Conservation_Areas");
            list.add("Chicago_Empowerment_Zones");
            list.add("Chicago_Police_Beat");
            list.add("Chicago_Police_District");
            list.add("Chicago_Major_Streets");
            list.add("Chicago_Railroads");
            list.add("Chicago_Streets_Sweeping");
            list.add("Chicago_Bike_Routes");
            list.add("Chicago_Census_Tracts");
            list.add("Chicago_Hospitals");
            list.add("Chicago_Schools");
            list.add("Chicago_High_School_Attendance_Boundaries");
            list.add("Chicago_Parks");
            list.add("Chicago_Hydro");
            list.add("Chicago_Forestry");


        }




        if (__area.equals("San_Francisco")) {

            list.add("San_Francisco_Downtown_Zoning");
            list.add("San_Francisco_Downtown_Address");
            list.add("San_Francisco_Downtown_Land_Use");
            list.add("San_Francisco_Zoning_Districts");
            list.add("San_Francisco_Height_And_Bulk_Districts");
            list.add("San_Francisco_Blocks");
            list.add("San_Francisco_Special_Sign_Districts");
            list.add("San_Francisco_Special_Sign_Districts_Scenic_Streets");
            list.add("San_Francisco_Special_Use_Districts");
            list.add("San_Francisco_Major_Streets");
            list.add("San_Francisco_Streets");
            list.add("San_Francisco_Street_Structures");
            list.add("San_Francisco_Curb_Island");
            list.add("San_Francisco_Building");
            list.add("San_Francisco_Zipcode");
            list.add("San_Francisco_Realtor_Neighborhoods");
            list.add("San_Francisco_Schools_Public");
            list.add("San_Francisco_Schools_Public_Address");
            list.add("San_Francisco_City_Land");
            list.add("San_Francisco_City_Facility");

        }




        if (__area.equals("Los_Angeles")) {

            list.add("Los_Angeles_Zoning");
            list.add("Los_Angeles_General_Land_Use");
            list.add("Los_Angeles_Communities");
            list.add("Los_Angeles_Neighborhood_Councils");
            list.add("Los_Angeles_Area_Planning_Commissions");
            list.add("Los_Angeles_Communities_And_Planning_Areas");
            list.add("Los_Angeles_Specific_Plans");
            list.add("Los_Angeles_Other_Districts");
            list.add("Los_Angeles_Historic_Preservation_Overlay_Zone");
            list.add("Los_Angeles_Historic_Cultural_Monuments");
            list.add("Los_Angeles_Federal_Renewal_Community");
            list.add("Los_Angeles_State_Enterprise_Zone");
            list.add("Los_Angeles_Business_Improvement_District");
            list.add("Los_Angeles_Targeted_Neighborhood_Initiative");


        }



        if (__area.equals("county")) {

            list.add("county_cities");
            list.add("county_rails");
            list.add("county_parks");
            list.add("county_water");
            list.add("county_education_facility");
            list.add("county_hospitals");
            list.add("county_fire_stations");

        }






        return list;
    }




    public static class SimpleStringRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleStringRecyclerViewAdapter.ViewHolder> {

        private final TypedValue mTypedValue = new TypedValue();
        private int mBackground;
        private List<String> mValues;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public String mBoundString;



            public final View mView;
            public final ImageView mImageView;
            public final TextView mTextView;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mImageView = (ImageView) view.findViewById(R.id.avatar);
                mTextView = (TextView) view.findViewById(android.R.id.text1);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mTextView.getText();
            }
        }

        public String getValueAt(int position) {
            return mValues.get(position);
        }

        public SimpleStringRecyclerViewAdapter(Context context, List<String> items) {
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            mBackground = mTypedValue.resourceId;
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
            view.setBackgroundResource(mBackground);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mBoundString = mValues.get(position);

            holder.mTextView.setText(mValues.get(position));

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, CheeseDetailActivity.class);



                    intent.putExtra(CheeseDetailActivity.EXTRA_NAME, holder.mBoundString);




                    context.startActivity(intent);



                }
            });

            Glide.with(holder.mImageView.getContext())
                    .load(Cheeses.getRandomCheeseDrawable())
                    .fitCenter()
                    .into(holder.mImageView);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }
    }






}
