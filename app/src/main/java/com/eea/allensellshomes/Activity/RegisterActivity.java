package com.eea.allensellshomes.Activity;

import androidx.appcompat.app.AppCompatActivity;

import com.eea.allensellshomes.Model.User;
import com.eea.allensellshomes.R;
import com.eea.allensellshomes.Service.RetrofitService;
import com.eea.allensellshomes.Service.UserService;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {


    EditText FullName, Email, Password, ContactNo, Conform_Password;
    Button Register;
    TextView SignIn_here;
    String fullName, email, password, contactNo, confirm_password;
    ImageView Go_back;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        FullName = findViewById(R.id.name);
        Email = findViewById(R.id.email);
        ContactNo = findViewById(R.id.number);
        Password = findViewById(R.id.password);
        Conform_Password = findViewById(R.id.confirmPassword);
        Register = findViewById(R.id.register);
        SignIn_here = findViewById(R.id.login);
        Go_back = findViewById(R.id.backicon);

        Go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                previous();
            }

        });

        SignIn_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            }
        });


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initialize();
                if(Validate()) {
                    User user = new User(email, fullName, contactNo, password);
                    retrofit2.Retrofit retrofit = RetrofitService.getRetrofit();
                    UserService userService = retrofit.create(UserService.class);
                    Call<User> pp = userService.registerUser(user);
                    pp.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            User r = response.body();
                            if (r != null) {
                                Toast.makeText(getApplicationContext(), "User Registration successful", Toast.LENGTH_LONG).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable throwable) {
                            Toast.makeText(getApplicationContext(), "User Registration  unsuccessful", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });



    }


    public void previous(){
        super.onBackPressed();
    }

    public Boolean Validate(){
        Boolean flag=true;
        if(TextUtils.isEmpty(email)){
            Email.setError("Email cannot be empty");
            flag=false;
        }
        else if(TextUtils.isEmpty(fullName)){
            FullName.setError("Fullname cannot be empty");
            flag=false;
        }
        else if(TextUtils.isEmpty(password)){
            Password.setError("Password cannot be empty");
            flag=false;
        }
        else if(TextUtils.isEmpty(String.valueOf(contactNo))){
            ContactNo.setError("Number cannot be empty");
            flag=false;
        }
        else if(TextUtils.isEmpty(confirm_password)){
            Conform_Password.setError("Confirm Password cannot be empty");
            flag=false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email.toString()).matches()){
            Email.setError("Email not valid");
            flag=false;
        }
        return flag;

    }

    public void initialize(){
        fullName=FullName.getText().toString();
        email=Email.getText().toString();
        password=Password.getText().toString();
        confirm_password=Conform_Password.getText().toString();
        contactNo=ContactNo.getText().toString();
    }




}