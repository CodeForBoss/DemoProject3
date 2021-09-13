package com.example.demoproject3;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRest {
    private static final String BASE_URL = "https://res.im.digitalistic.co/";
    public static Retrofit retrofit = null;
    public static Retrofit getRetroFit(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
