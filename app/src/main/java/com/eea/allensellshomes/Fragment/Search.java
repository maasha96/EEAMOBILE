//package com.eea.allensellshomes.Fragment;
//
//import android.graphics.drawable.GradientDrawable;
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//import androidx.viewpager.widget.ViewPager;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//
//import com.eea.allensellshomes.Adapter.TabAdapter;
//import com.eea.allensellshomes.R;
//import com.google.android.material.tabs.TabLayout;
//
//public class Search extends Fragment {
//
//    public Search() {
//        // Required empty public constructor
//    }
//    private TabAdapter adapter;
//    private TabLayout tabLayout;
//    private ViewPager viewPager;
//
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }
//    private int[] tabIcons = {
//            R.drawable.search_24dp,
//
//    };
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view= inflater.inflate(R.layout.fragment_search, container, false);
//
//        viewPager = view.findViewById(R.id.viewPager);
//        tabLayout = view.findViewById(R.id.tabLayout);
//        adapter = new TabAdapter(getActivity().getSupportFragmentManager());
//
//        adapter.addFragment(new SearchFragment(), "Search");
//        adapter.addFragment(new ModelSearch(), "Model Search");
//        View root = tabLayout.getChildAt(0);
//        if (root instanceof LinearLayout) {
//            ((LinearLayout) root).setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
//            GradientDrawable drawable = new GradientDrawable();
//            drawable.setColor(getResources().getColor(R.color.black));
//            drawable.setSize(2, 1);
//            ((LinearLayout) root).setDividerPadding(10);
//            ((LinearLayout) root).setDividerDrawable(drawable);
//        }
//
//
//        viewPager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(viewPager);
//        setTabIcons(tabIcons);
//        return  view;
//    }
//
//    public void setTabIcons(int[] tabIcons) {
//        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
//        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
//    }
//}