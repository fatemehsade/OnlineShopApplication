package com.example.onlineshopapplication.remote;

import com.example.onlineshopapplication.model.Category;
import com.example.onlineshopapplication.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterFaceService {

    @GET("products?consumer key=ck_15465e254b749cb895e00d64387c8b7f96aba938 & " +
            "consumer secret=cs_80931093385005fa89e3eedbdb96bc6d5f70e7ec")
    Call<List<Product>> getBestProduct(@Query("orderby") String orderby,
                                       @Query("order") String order, @Query("per_page") int per_page);

    @GET("products?consumer key=ck_15465e254b749cb895e00d64387c8b7f96aba938 & " +
            "consumer secret=cs_80931093385005fa89e3eedbdb96bc6d5f70e7ec")
    Call<List<Product>> getLatestProduct(@Query("orderby")
                                                 String orderby, @Query("order")
            String order, @Query("per_page") int per_page);

    @GET("products?consumer key=ck_15465e254b749cb895e00d64387c8b7f96aba938 & " +
            "consumer secret=cs_80931093385005fa89e3eedbdb96bc6d5f70e7ec")
    Call<List<Product>> getMostVisitedProduct(@Query("orderby")
                                                      String orderby, @Query("order")
            String order, @Query("per_page") int per_page);


    @GET("products?consumer key=ck_15465e254b749cb895e00d64387c8b7f96aba938 & " +
            "consumer secret=cs_80931093385005fa89e3eedbdb96bc6d5f70e7ec")
    Call<List<Product>> getTotalProduct();


    @GET("products?consumer key=ck_15465e254b749cb895e00d64387c8b7f96aba938 & " +
            "consumer secret=cs_80931093385005fa89e3eedbdb96bc6d5f70e7ec")
    Call<List<Product>> getSpecialProduct(@Query("featured") boolean featured, @Query("per_page") int per_page);

    @GET("products?consumer key=ck_15465e254b749cb895e00d64387c8b7f96aba938 & " +
            "consumer secret=cs_80931093385005fa89e3eedbdb96bc6d5f70e7ec")
    Call<List<Category>> getCategory(@Query("page") int page);

    @GET("products?consumer key=ck_15465e254b749cb895e00d64387c8b7f96aba938 & " +
            "consumer secret=cs_80931093385005fa89e3eedbdb96bc6d5f70e7ec")
    Call<Product> retrieveProduct(@Path("id") int id);

    @GET("products?consumer key=ck_15465e254b749cb895e00d64387c8b7f96aba938 & " +
            "consumer secret=cs_80931093385005fa89e3eedbdb96bc6d5f70e7ec")
    Call<List<Product>> getProductByCategory(@Query("category") int categoryId, @Query("page") int page);



}
