package com.rajkumarrajan.sampleapplication.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rajkumarrajan.sampleapplication.API.API;
import com.rajkumarrajan.sampleapplication.Adapter.RecyclerActivityAdapter;
import com.rajkumarrajan.sampleapplication.Pojo.MyPojo;
import com.rajkumarrajan.sampleapplication.R;
import com.rajkumarrajan.sampleapplication.SqliteDB.MyDB;
import com.rajkumarrajan.sampleapplication.Support.MyPreference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.rajkumarrajan.sampleapplication.Service.Service.createService;

public class RecyclerActivity extends AppCompatActivity {

    //Our RecyclerView variable
    RecyclerView RecyclerViewMain;
    //LinearLayoutManager variable for handling RecyclerView
    LinearLayoutManager llm1;
    //MyPreference is a supportive class which help in performing basic and often operation
    MyPreference myPreference;
    //ProgressDialog for displaying while waiting for data from server
    private ProgressDialog progressBar;

    List<MyPojo> myPojos;
    //SqLite Class
    MyDB myDB;
    //Adapter for RecyclerView
    RecyclerActivityAdapter recyclerActivityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        RecyclerViewMain = (RecyclerView) findViewById(R.id.RecyclerViewMain);

        llm1 = new LinearLayoutManager(this);
        llm1.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerViewMain.setLayoutManager(llm1);
        RecyclerViewMain.setHasFixedSize(true);

        myPreference = new MyPreference(this);
        myDB = new MyDB(this);

        if (myPreference.isInternetOn()) {
            progressBar = new ProgressDialog(this);
            progressBar.setCancelable(true);
            progressBar.setMessage("Processing ...");
            progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressBar.setProgress(0);
            progressBar.setMax(100);
            progressBar.show();


            API apiService = createService(API.class);
            Call<List<MyPojo>> call = apiService.MY_POJO_CALL();
            call.enqueue(new Callback<List<MyPojo>>() {
                @Override
                public void onResponse(Call<List<MyPojo>> call, Response<List<MyPojo>> response) {
                    progressBar.dismiss();
//                    myPojos = new ArrayList<>();
//                    myPojos.addAll(response.body());

//                    myDB.InsertDataToJsonPlaceHolderTable(response.body());
                    recyclerActivityAdapter = new RecyclerActivityAdapter(RecyclerActivity.this, response.body());
                    RecyclerViewMain.setAdapter(recyclerActivityAdapter);

                }
                @Override
                public void onFailure(Call<List<MyPojo>> call, Throwable t) {
                    progressBar.dismiss();
                    Toast.makeText(RecyclerActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Toast.makeText(this, "Check your internet connection", Toast.LENGTH_SHORT).show();
        }
    }


}
