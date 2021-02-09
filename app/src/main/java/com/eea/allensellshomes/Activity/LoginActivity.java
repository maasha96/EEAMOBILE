package com.eea.allensellshomes.Activity;

import androidx.appcompat.app.AppCompatActivity;

import com.eea.allensellshomes.Model.JwtResponse;
import com.eea.allensellshomes.Model.SignInRequest;
import com.eea.allensellshomes.R;
import com.eea.allensellshomes.Service.RetrofitService;
import com.eea.allensellshomes.Service.UserService;
import com.google.android.material.textfield.TextInputEditText;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView Sign_up_here;
    TextInputEditText Email, Password;
    Button signin;
    ImageView Go_Back;
    String email, password, value;
    Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Sign_up_here = findViewById(R.id.signup);
        Email = findViewById(R.id.username);
        Password = findViewById(R.id.user_password);
        Go_Back = findViewById(R.id.go_back_login);
        signin= findViewById(R.id.signIn);


        Intent intent=getIntent();
        value=intent.getStringExtra("sample");
        id=intent.getLongExtra("id",0);



        signin.setOnClickListener(this);
        Sign_up_here.setOnClickListener(this);


//        Go_Back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                previous();
//            }
//        });
    }

    public void previous(){
        if(value.equals("login")){
            Intent intent1=new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent1);
        }
        else if(value.equals("detail")){

            Intent intent=new Intent( LoginActivity.this, HouseDetailsActivity.class);
            intent.putExtra("id",id);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else {
            super.onBackPressed();
        }
    }

    public  boolean validate(){
        email=Email.getText().toString();
        password=Password.getText().toString();
        boolean flag=true;
        if(TextUtils.isEmpty(email)){
            Email.setError("Please Enter Email");
            flag=false;
        }
        else if(TextUtils.isEmpty(password)){
            Password.setError("Please Enter Password");
            flag=false;
        }  else if (Patterns.EMAIL_ADDRESS.matcher(Email.toString()).matches()){
            Email.setError("Email not valid");
            flag=false;
        }
        return flag;
    }


    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.signIn: /** Start a new Activity MyCards.java */
                if(validate()) {
                    retrofit2.Retrofit retrofit = RetrofitService.getRetrofit();
                    UserService userService = retrofit.create(UserService.class);
                    SignInRequest user = new SignInRequest(email, password);
                    Call<JwtResponse> pp = userService.loginUser(user);
                    pp.enqueue(new Callback<JwtResponse>() {
                        @Override
                        public void onResponse(Call<JwtResponse> call, Response<JwtResponse> response) {
                            JwtResponse j = (JwtResponse) response.body();
                            if (j != null) {
                                SharedPreferences.Editor editor = getSharedPreferences("shared", MODE_PRIVATE).edit();
                                editor.putString("email",j.getEmail());
                                editor.putString("token",j.getType()+" "+j.getToken());
                                editor.putString("admin",j.getRoles().get(0));
                                editor.apply();
                                List<String> roles=j.getRoles();
                                if(roles.get(0).equals("ROLE_ADMIN")){
                                    Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                                    intent.putExtra("command", "profile");
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    intent.putExtra("command", "profile");
                                    startActivity(intent);
                                    previous();
                                }
                            }
                            else {
                                Toast.makeText(LoginActivity.this, " Invalid User Credentials ", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<JwtResponse> call, Throwable thtow) {
                            if (thtow instanceof IOException) {
                                Toast.makeText(LoginActivity.this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                                // logging probably not necessary
                            } else {
                                Toast.makeText(LoginActivity.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                                // todo log to some central bug tracking service
                            }

                        }
                    });
                }
                break;

            case R.id.signup: previous();
                break;
        }

    }


}