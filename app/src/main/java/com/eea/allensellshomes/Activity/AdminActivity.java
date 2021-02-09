package com.eea.allensellshomes.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.eea.allensellshomes.AdminManagement.AdminActivity.ManageAppointments;
import com.eea.allensellshomes.AdminManagement.AdminActivity.ManageHouses;
import com.eea.allensellshomes.AdminManagement.AdminActivity.ManageReservations;
import com.eea.allensellshomes.AdminManagement.AdminActivity.ManageUsers;
import com.eea.allensellshomes.R;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class AdminActivity extends AppCompatActivity {

    CardView manageHouse,manageUsers,manageAppointment,manageReservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Toolbar toolbar=findViewById(R.id.toolbars);
//        setSupportActionBar(toolbar);

        manageHouse=findViewById(R.id.manageHouse);
        manageUsers=findViewById(R.id.manageUsers);
        manageAppointment=findViewById(R.id.manageAppointments);
        manageReservation=findViewById(R.id.manageReservation);

        manageHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), ManageHouses.class);
                startActivity(intent);

            }
        });
        manageUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), ManageUsers.class);
                startActivity(intent);

            }
        });
        manageAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), ManageAppointments.class);
                startActivity(intent);

            }
        });
        manageReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), ManageReservations.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                SharedPreferences preferences =getSharedPreferences("shared", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}