package com.rajkumarrajan.sampleapplication.API;

import com.google.gson.JsonObject;
import com.rajkumarrajan.sampleapplication.Pojo.MyPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {

    @GET("albums")
    Call<List<MyPojo>> MY_POJO_CALL();

    //userSignup
//    @POST("Purohit/register")
//    Call<SignUpPojo> SIGN_UP_POJO_CALL(@Body JsonObject requestParameter);
//
//    //Get Distance from Google Map API
//    //https://maps.googleapis.com/maps/api/directions/json?origin=13.008906,80.217450&destination=13.008658,80.217836&key=asdfghjklqwer
//    @GET("directions/json")
//    Call<GoogleMapDirection> GOOGLE_MAP_DIRECTION_CALL(@Query("origin") String origin, @Query("destination") String destination,
//                                                       @Query("key") String key);
}
