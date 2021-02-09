package com.eea.allensellshomes.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.eea.allensellshomes.Activity.AdminActivity;
import com.eea.allensellshomes.Fragment.HomeFragment;
import com.eea.allensellshomes.Fragment.ProfileFragment;
import com.eea.allensellshomes.Fragment.ProfileLoginFragment;
//import com.eea.allensellshomes.Fragment.Search;
import com.eea.allensellshomes.Fragment.ViewMoreFragment;
import com.eea.allensellshomes.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListner);

        Intent intent = getIntent();
        String command = intent.getStringExtra("command");
        if(command== null)
        {
            command="home";
        }
        switch (command)
        {
            case "profile" : getSupportFragmentManager().beginTransaction().replace(R.id.layout, new ProfileLoginFragment()).commit();
                break;
            case "home":getSupportFragmentManager().beginTransaction().replace(R.id.layout,new HomeFragment()).commit();
                break;
            default: getSupportFragmentManager().beginTransaction().replace(R.id.layout,new HomeFragment()).commit();
                break;
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListner= new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment slectedFragment=null;
                    SharedPreferences prefs = getSharedPreferences("shared",MODE_PRIVATE);
                    String email=  prefs.getString("email", "");
                    String role=  prefs.getString("admin", "");

                    switch (menuItem.getItemId()){
                        case R.id.nav_home: slectedFragment=new HomeFragment();
                            break;
//                        case R.id.nav_search: slectedFragment=new Search();
//                            break;
                        case R.id.nav_profile:
                            if(email==null||email==""){
                                slectedFragment=new ProfileLoginFragment();
                            }
                            else {
                                if (role.equals("ROLE_ADMIN")){
                                    Intent intent=new Intent(getApplicationContext(), AdminActivity.class);
                                    startActivity(intent);
                                    break;
                                }
                                else {
                                    slectedFragment = new ProfileFragment();
                                }
                            }
                            break;
                        case R.id.nav_settings: slectedFragment=new ViewMoreFragment();
                            break;
                    }
                    if (role.equals("ROLE_ADMIN")){

                    }
                    else {
                        getSupportFragmentManager().beginTransaction().replace(R.id.layout, slectedFragment).commit();
                    }
                    return true;
                }
            };
}