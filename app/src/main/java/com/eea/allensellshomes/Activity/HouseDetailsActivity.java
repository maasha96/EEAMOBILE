package com.eea.allensellshomes.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.eea.allensellshomes.Dto.AppointmentDto;
import com.eea.allensellshomes.Dto.FavoritesDto;
import com.eea.allensellshomes.Dto.HouseDto;
import com.eea.allensellshomes.R;
import com.eea.allensellshomes.Model.House;
import com.eea.allensellshomes.Service.AppointmentService;
import com.eea.allensellshomes.Service.FavoritesService;
import com.eea.allensellshomes.Service.HouseService;
import com.eea.allensellshomes.Service.RetrofitService;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HouseDetailsActivity extends AppCompatActivity implements View.OnClickListener{

    String email,token,user;
    Button share, call,makeAppointment,reserve,more,favorites;
    House house;
    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime;
    int i=0;
    ImageView back;
    private int mYear, mMonth, mDay, mHour, mMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detail);
        Initialize();

        SharedPreferences prefs = getSharedPreferences("shared",MODE_PRIVATE);
        email=  prefs.getString("email", "");
        token=prefs.getString("token","");
        user=prefs.getString("admin","");
        Intent intent=getIntent();
        Long id=intent.getLongExtra("id",1);
        retrofit2.Retrofit retrofit= RetrofitService.getRetrofit();
        HouseService houseService=retrofit.create(HouseService.class);
        Call<House> pp=houseService.getHouseById(id);
        pp.enqueue(new Callback<House>() {
                       @Override
                       public void onResponse(Call<House> call, Response<House> response) {
                           house = response.body();

                           List<SlideModel> slideModels=new ArrayList<>();

//                           if (house.getImagePath().isEmpty()){
//                               slideModels.add(new SlideModel("https://images.unsplash.com/photo-1570129477492-45c003edd2be?ixid=MXwxMjA3fDB8MHxzZWFyY2h8Mnx8aG91c2V8ZW58MHx8MHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60",house.getTitle()));
//                               slideModels.add(new SlideModel("https://images.unsplash.com/photo-1570129477492-45c003edd2be?ixid=MXwxMjA3fDB8MHxzZWFyY2h8Mnx8aG91c2V8ZW58MHx8MHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60",house.getTitle()));
//                               slideModels.add(new SlideModel("https://images.unsplash.com/photo-1570129477492-45c003edd2be?ixid=MXwxMjA3fDB8MHxzZWFyY2h8Mnx8aG91c2V8ZW58MHx8MHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60",house.getTitle()));
//
//                           }
//                           else{
//                               for(String houseImages:house.getImagePath()){
//                                   String image= houseImages;
//                                   int index = image.lastIndexOf('/');
//                                   String lastString = image.substring(index +1);
//                                   String updatedImage= RetrofitService.url()+"download/"+lastString;
//
//                                   slideModels.add(new SlideModel(updatedImage,house.getTitle()));
//                               }
//
//                           }

                           location.setText(house.getLocation());
                           description.setText(house.getDescription());
//                           price.setText("Rs :" + String.valueOf(house.getPrice()));
//                           lotSize.setText(house.getLotSize() + " sqft");
//                           room.setText(house.getNumOfRooms());
//                           baths.setText(house.getNumOfbBaths());

                           title.setText(house.getTitle());

                           ImageSlider imageSlider =findViewById(R.id.imageView7);
                           imageSlider.setImageList(slideModels,true);
                           imageSlider.startSliding(3000) ;// with new period
                           imageSlider.stopSliding();
                       }

                       @Override
                       public void onFailure(Call<House> call, Throwable t) {

                       }
                   });

                //reserve.setVisibility(View.GONE);
    }




    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }



//    @Override
//    public void onBackPressed() {
//        getSupportFragmentManager().beginTransaction().replace(R.id.layout,new Search()).commit();
//    }

    TextView location,description, price,room,baths,garages,title,category, lotSize;
    public void Initialize(){

        back=findViewById(R.id.backicon);
        back.setOnClickListener(this);
        makeAppointment=findViewById(R.id.appointment);
        makeAppointment.setOnClickListener(this);
        reserve=findViewById(R.id.reserve);
        reserve.setOnClickListener(this);
        more=findViewById(R.id.more);
        more.setOnClickListener(this);
        favorites=findViewById(R.id.favorites);
        favorites.setOnClickListener(this);
        share = findViewById(R.id.share);
        share.setOnClickListener(this);
        call = findViewById(R.id.call);
        call.setOnClickListener(this);
        category=findViewById(R.id.category);
        description=findViewById(R.id.description);
        price=findViewById(R.id.price);
        room=findViewById(R.id.room);
        baths=findViewById(R.id.baths);
        garages=findViewById(R.id.garages);
        title=findViewById(R.id.title);
        location=findViewById(R.id.location);
        lotSize=findViewById(R.id.lotsize);

    }
    public void previous(){
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){

            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "Hey check out my app at: https://play.google.com/store/apps/details?id=");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;

            case R.id.call:
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+Uri.encode("0774779345")));
                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(callIntent);
                break;

            case R.id.backicon:
                previous();
                break;

            case R.id.more:

                break;

            case R.id.appointment:
                if ((email==null||email=="")&&(!user.equals("USER_ROLE"))){
                    bottomNav();
                }
                else {
                    final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
                    LayoutInflater inflater = this.getLayoutInflater();
                    View dialogView = inflater.inflate(R.layout.add_appointment, null);

                    final EditText editText = (EditText) dialogView.findViewById(R.id.edt_comment);
                    Button button1 = (Button) dialogView.findViewById(R.id.buttonSubmit);
                    Button button2 = (Button) dialogView.findViewById(R.id.buttonCancel);

                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialogBuilder.dismiss();
                        }
                    });
                    button1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // DO SOMETHINGS
                            final SimpleDateFormat myDateFormat = new SimpleDateFormat("\"MM-dd_Y'T'HH:mm:ss.SSSZ\"", java.util.Locale.getDefault());

                            SwitchDateTimeDialogFragment dateTimeDialogFragment = SwitchDateTimeDialogFragment.newInstance(
                                    "Title example",
                                    "OK",
                                    "Cancel"
                            );
// Assign values
                            dateTimeDialogFragment.startAtCalendarView();
                            dateTimeDialogFragment.set24HoursMode(true);
                            dateTimeDialogFragment.setMinimumDateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
                            dateTimeDialogFragment.setMaximumDateTime(new GregorianCalendar(2025, Calendar.DECEMBER, 31).getTime());
                            dateTimeDialogFragment.setDefaultDateTime(new GregorianCalendar(2020, Calendar.JUNE, 4, 15, 20).getTime());
// Define new day and month format
                            try {
                                dateTimeDialogFragment.setSimpleDateMonthAndDayFormat(new SimpleDateFormat("MM-dd_Y'T'HH:mm:ss.SSSZ"));
                            } catch (SwitchDateTimeDialogFragment.SimpleDateMonthAndDayFormatException e) {
                                Log.e("", e.getMessage());
                            }
// Set listener
                            dateTimeDialogFragment.setOnButtonClickListener(new SwitchDateTimeDialogFragment.OnButtonClickListener() {
                                @RequiresApi(api = Build.VERSION_CODES.O)
                                @Override
                                public void onPositiveButtonClick(java.util.Date date) {
                                    Toast.makeText(HouseDetailsActivity.this, myDateFormat.format(date), Toast.LENGTH_SHORT).show();
                                    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd_Y'T'HH:mm:ss.SSSZ");

                                    String dates= sdf.format(date);
//                            Date aaaaaa=new Date("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

                                    try {
                                        Date appointmentDate=(Date)sdf.parse(dates);
                                        AppointmentDto appointmentDTO=new AppointmentDto(editText.getText().toString(),appointmentDate,house.getId(),email);
                                        Retrofit retrofit = RetrofitService.getRetrofit();
                                        AppointmentService appointmentService = retrofit.create(AppointmentService.class);
                                        Call<Object> pp = appointmentService.makeAppointments(appointmentDTO,token);
                                        pp.enqueue(new Callback<Object>() {
                                            @Override
                                            public void onResponse(Call<Object> call, Response<Object> response) {
                                                Object j =  response.body();
                                                if (j .equals( true)) {
                                                    Toast.makeText(HouseDetailsActivity.this, "SucessFully Made Appointment for house"+ j, Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                            @Override
                                            public void onFailure(Call<Object> call, Throwable thtow) {
                                            }
                                        });
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                }
                                @Override
                                public void onNegativeButtonClick(java.util.Date date) {

                                }
                            });

                            dateTimeDialogFragment.show(getSupportFragmentManager(), "dialog_time");



                            dialogBuilder.dismiss();
                        }
                    });

                    dialogBuilder.setView(dialogView);
                    dialogBuilder.show();




                }

                break;
            case R.id.reserve:
                if ((email==null||email=="")&&(!user.equals("USER_ROLE"))){
                    bottomNav();
                }
                else {
                    new AlertDialog.Builder(this)
                            .setTitle("Are You Sure You Want to Reserve house?")
                            .setMessage("Please visit us to confirm your reservation within 3 working days to avoid any inconvenience as your reservation will be cancelled")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    Retrofit retrofit = RetrofitService.getRetrofit();
                                    HouseService houseService = retrofit.create(HouseService.class);
                                    HouseDto houseDto = new HouseDto(house.getId(), "Reserved", email);
                                    Call<Object> pp = houseService.updateHouse(houseDto, token);
                                    pp.enqueue(new Callback<Object>() {
                                        @Override
                                        public void onResponse(Call<Object> call, Response<Object> response) {
                                            Object j = response.body();
                                            if (j.equals(true) ) {
                                                Toast.makeText(HouseDetailsActivity.this, "SucessFully Reserved house", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                        @Override
                                        public void onFailure(Call<Object> call, Throwable thtow) {
                                        }
                                    });
                                }
                            })
                            .setNegativeButton("Cancel", null).show();
                }
                break;

            case R.id.favorites:
                if ((email==null||email=="")&&(!user.equals("USER_ROLE"))){
                    bottomNav();
                }
                else{
                    FavoritesDto savelist=new FavoritesDto(house.getId(),email);
                    retrofit2.Retrofit retrofit = RetrofitService.getRetrofit();
                    FavoritesService favoritesService = retrofit.create(FavoritesService.class);
                    Call<ResponseBody> pp = favoritesService.addSaveList(savelist,token);
                    pp.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            try {
                                String Response = response.body().string();
                                if(Response.equals("Added To Favorite List")){
                                    Toast.makeText(getApplicationContext(), "House added to Favorites Successfully", Toast.LENGTH_LONG).show();
                                }
                                else if(Response.equals("House Already Exists in your Favorites")){
                                    Toast.makeText(getApplicationContext(), "House Already added to Favorites", Toast.LENGTH_LONG).show();
                                }
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(), "House Already added to Favorites", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable thtow) {
                            Toast.makeText(HouseDetailsActivity.this, thtow.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                break;


            case  R.id.btn_date:
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;
            case R.id.btn_time:final Calendar calendar = Calendar.getInstance();
                mHour = calendar.get(Calendar.HOUR_OF_DAY);
                mMinute = calendar.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                txtTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
                break;

        }
    }

    public static java.sql.Timestamp convertToSqlDateTime(Date utilDate){
        return new java.sql.Timestamp(utilDate.getTime());
    }

    public void bottomNav(){
        final BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(this,R.style.BottomSheetDialogTheme);
        View bottomSheetView= LayoutInflater.from(this.getApplicationContext()).inflate(R.layout.layout_bottom_sheet,(LinearLayout)findViewById(R.id.bottomSheetContainer));
        Button login=bottomSheetView.findViewById(R.id.signIn);
        Button regsiter=bottomSheetView.findViewById(R.id.signup);

        regsiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), RegisterActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(getApplicationContext(), LoginActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent1.putExtra("id", house.getId());
                intent1.putExtra("sample", "detail");
                startActivity(intent1);

            }
        });
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();

    }
    private List<ThreadLocal<SimpleDateFormat>> threadLocals = new  ArrayList<ThreadLocal<SimpleDateFormat>>();

    public Date parseDate(String dateStr) throws ParseException {
        for (ThreadLocal<SimpleDateFormat> tl : threadLocals) {
            SimpleDateFormat sdf = tl.get();
            try {
                return sdf.parse(dateStr);
            } catch (ParseException e) {
                // Ignore and try next date parser
            }
        }
        // All parsers failed
        return null;
    }
}
