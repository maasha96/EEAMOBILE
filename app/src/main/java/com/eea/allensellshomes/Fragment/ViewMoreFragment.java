package com.eea.allensellshomes.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eea.allensellshomes.AdminManagement.AdminAdapter.TabAdapter;
import com.eea.allensellshomes.R;
import com.google.android.material.tabs.TabLayout;


public class ViewMoreFragment extends Fragment {

    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public ViewMoreFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_view_more, container, false);
        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);
        adapter = new TabAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new ReviewsFragment(), "Reviews");
        adapter.addFragment(new ContacUsFragment(), "Contact Us");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return  view;


    }


}