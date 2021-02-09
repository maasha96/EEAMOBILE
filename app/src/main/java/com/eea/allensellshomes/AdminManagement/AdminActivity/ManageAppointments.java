package com.eea.allensellshomes.AdminManagement.AdminActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.eea.allensellshomes.AdminManagement.AdminAdapter.TabAdapter;
import com.eea.allensellshomes.AdminManagement.AdminFragment.ManageAppointments.ConfirmFragment;
import com.eea.allensellshomes.AdminManagement.AdminFragment.ManageAppointments.HistoryFragment;
import com.eea.allensellshomes.AdminManagement.AdminFragment.ManageProducts.PendingFragment;
import com.eea.allensellshomes.R;
import com.google.android.material.tabs.TabLayout;

public class ManageAppointments extends AppCompatActivity {

    ImageView back;
    TextView title;
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_appointments);

        back=findViewById(R.id.backicon);
        title=findViewById(R.id.title);
        title.setText("Manage Appointments");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                previous();
            }
        });
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new PendingFragment(), "Pending");
        adapter.addFragment(new ConfirmFragment(), "Confirm");
        adapter.addFragment(new HistoryFragment(), "History");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
    public void previous(){
        super.onBackPressed();
    }
}