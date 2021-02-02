package com.example.onlineshopapplication.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InstansRetrofit {
    public static Retrofit getRetrofitInstance(Type type, Object typeAdapter) {

        return new Retrofit.Builder()
                .baseUrl("https://woocommerce.maktabsharif.ir/wp-json/wc/v3/")
                .addConverterFactory(createGsonConverter(type, typeAdapter))
                .build();
    }

    public static Converter.Factory createGsonConverter(Type type, Object typeAdapter) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(type, typeAdapter);
        Gson gson = gsonBuilder.create();
        return GsonConverterFactory.create(gson);
    }
}
