package com.example.onlineshopapplication.remote;

import com.example.onlineshopapplication.model.Category;
import com.example.onlineshopapplication.model.Customer;
import com.example.onlineshopapplication.model.Order;
import com.example.onlineshopapplication.model.Product;
import com.example.onlineshopapplication.model.Review;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductService {

    @GET("products?consumer key=ck_15465e254b749cb895e00d64387c8b7f96aba938 & " +
            "consumer secret=cs_80931093385005fa89e3eedbdb96bc6d5f70e7ec")
    Call<List<Product>> getTotalProduct();

    @GET("products?consumer key=ck_15465e254b749cb895e00d64387c8b7f96aba938 & " +
            "consumer secret=cs_80931093385005fa89e3eedbdb96bc6d5f70e7ec")
    Call<List<Product>> getBestProduct(@Query("orderby") String orderby, @Query("order") String order);

    @GET("products?consumer key=ck_15465e254b749cb895e00d64387c8b7f96aba938 & " +
            "consumer secret=cs_80931093385005fa89e3eedbdb96bc6d5f70e7ec")
    Call<List<Product>> getLatestProduct(@Query("orderby") String orderby, @Query("order") String order);

    @GET("products?consumer key=ck_15465e254b749cb895e00d64387c8b7f96aba938 & " +
            "consumer secret=cs_80931093385005fa89e3eedbdb96bc6d5f70e7ec")
    Call<List<Product>> getMostVisitedProduct(@Query("orderby") String orderby, @Query("order") String order);

    @GET("products?consumer key=ck_15465e254b749cb895e00d64387c8b7f96aba938 & " +
            "consumer secret=cs_80931093385005fa89e3eedbdb96bc6d5f70e7ec")
    Call<List<Product>> getSpecialProduct(@Query("featured") boolean featured);

    @GET("products?consumer key=ck_15465e254b749cb895e00d64387c8b7f96aba938 & " +
            "consumer secret=cs_80931093385005fa89e3eedbdb96bc6d5f70e7ec")
    Call<List<Category>> getCategory(@Query("page") int page);

    @GET("products?consumer key=ck_15465e254b749cb895e00d64387c8b7f96aba938 & " +
            "consumer secret=cs_80931093385005fa89e3eedbdb96bc6d5f70e7ec")
    Call<Product> retrieveProduct(@Path("id") int id);

    @GET("products?consumer key=ck_15465e254b749cb895e00d64387c8b7f96aba938 & " +
            "consumer secret=cs_80931093385005fa89e3eedbdb96bc6d5f70e7ec")
    Call<List<Product>> getProductByCategory(@Query("category") int categoryId, @Query("page") int page);

    @GET("products?consumer key=ck_15465e254b749cb895e00d64387c8b7f96aba938 & " +
            "consumer secret=cs_80931093385005fa89e3eedbdb96bc6d5f70e7ec")
    Call<List<Review>> getReviews(@Query("product") int id);

    @FormUrlEncoded
    @GET("products?consumer key=ck_15465e254b749cb895e00d64387c8b7f96aba938 & " +
            "consumer secret=cs_80931093385005fa89e3eedbdb96bc6d5f70e7ec")
    Call<Customer> postCustomer(@Field("email") String email);

    @FormUrlEncoded
    @GET("products?consumer key=ck_15465e254b749cb895e00d64387c8b7f96aba938 & " +
            "consumer secret=cs_80931093385005fa89e3eedbdb96bc6d5f70e7ec")
    Call<Order> postOrder(@Field("email") String email);

    @FormUrlEncoded
    @GET("products?consumer key=ck_15465e254b749cb895e00d64387c8b7f96aba938 & " +
            "consumer secret=cs_80931093385005fa89e3eedbdb96bc6d5f70e7ec")
    Call<Review> postReview(@Field("product_id") int productId,
                            @Field("review") String review,
                            @Field("reviewer") String reviewer,
                            @Field("reviewer_email") String reviewerEmail,
                            @Field("rating") int rating);

    @GET("products?consumer key=ck_15465e254b749cb895e00d64387c8b7f96aba938 & " +
            "consumer secret=cs_80931093385005fa89e3eedbdb96bc6d5f70e7ec")
    Call<Review> deleteReview(@Path("id") int id);

    @GET("products?consumer key=ck_15465e254b749cb895e00d64387c8b7f96aba938 & " +
            "consumer secret=cs_80931093385005fa89e3eedbdb96bc6d5f70e7ec")
    Call<Review> updateReview(@Path("id") int id,
                              @Query("review") String review,
                              @Query("reviewer") String reviewer,
                              @Query("rating") int rating);

    @GET("products?consumer key=ck_15465e254b749cb895e00d64387c8b7f96aba938 & " +
            "consumer secret=cs_80931093385005fa89e3eedbdb96bc6d5f70e7ec")
    Call<List<Product>> getProducts();

    @GET("products?consumer key=ck_15465e254b749cb895e00d64387c8b7f96aba938 & " +
            "consumer secret=cs_80931093385005fa89e3eedbdb96bc6d5f70e7ec")
    Call<List<Product>> searchProducts(@Query("search") String search);

}
