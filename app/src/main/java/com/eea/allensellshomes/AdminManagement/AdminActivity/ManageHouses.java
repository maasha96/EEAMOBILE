package com.eea.allensellshomes.AdminManagement.AdminActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.eea.allensellshomes.AdminManagement.AdminAdapter.TabAdapter;
import com.eea.allensellshomes.AdminManagement.AdminFragment.ManageHouse.AddHouseFragment;
import com.eea.allensellshomes.AdminManagement.AdminFragment.ManageHouse.ViewAllHousesFragment;
import com.eea.allensellshomes.R;
import com.google.android.material.tabs.TabLayout;

public class ManageHouses extends AppCompatActivity {


    ImageView back;
    TextView title;
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_houses);

        back=findViewById(R.id.backicon);
        title=findViewById(R.id.title);
        title.setText("Manage Vehicles");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                previous();
            }
        });
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new AddHouseFragment(), "Add House");
        adapter.addFragment(new ViewAllHousesFragment(), "My Houses");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    public void previous(){
        super.onBackPressed();
    }
}