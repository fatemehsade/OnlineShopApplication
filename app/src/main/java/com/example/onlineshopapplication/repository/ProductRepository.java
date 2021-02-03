package com.example.onlineshopapplication.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.onlineshopapplication.model.Category;
import com.example.onlineshopapplication.model.Product;
import com.example.onlineshopapplication.remote.ApiInterFaceService;
import com.example.onlineshopapplication.remote.CategoryListDeserializer;
import com.example.onlineshopapplication.remote.InstansRetrofit;
import com.example.onlineshopapplication.remote.ParseListProduct;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {
    private Context mContext;
    private static ProductRepository sInstance;
    private ApiInterFaceService mService,mCategoryService,mProductListService;


    private MutableLiveData<List<Product>> mBestProductMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mLatestProductMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mMostVisitedProductMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Integer> mTotalProductMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mSpecialProductMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Product> mProductMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mProductByCategoryMutableLiveData = new MutableLiveData<>();




    private ProductRepository(Context context) {
        mService = InstansRetrofit.getRetrofitInstance(
                new TypeToken<List<Product>>() {
                }.getType(),
                new ParseListProduct()).create(ApiInterFaceService.class);
        mCategoryService = InstansRetrofit.getRetrofitInstance(
                new TypeToken<List<Category>>() {
                }.getType(),
                new CategoryListDeserializer()).create(ApiInterFaceService.class);

        mProductListService = InstansRetrofit.getRetrofitInstance(
                new TypeToken<Product>() {
                }.getType(),
                new ParseListProduct()).create(ApiInterFaceService.class);

        mContext = context;
    }

    public static ProductRepository getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ProductRepository(context);
        }
        return sInstance;
    }

    public MutableLiveData<Integer> getTotalProductMutableLiveData() {
        return mTotalProductMutableLiveData;
    }

    public MutableLiveData<List<Product>> getBestProductMutableLiveData() {
        return mBestProductMutableLiveData;
    }

    public MutableLiveData<List<Product>> getLatestProductMutableLiveData() {
        return mLatestProductMutableLiveData;
    }

    public MutableLiveData<List<Product>> getMostVisitedProductMutableLiveData() {
        return mMostVisitedProductMutableLiveData;
    }

    public MutableLiveData<Product> getProductMutableLiveData() {
        return mProductMutableLiveData;
    }

    public MutableLiveData<List<Product>> getProductByCategoryMutableLiveData() {
        return mProductByCategoryMutableLiveData;
    }

    public void getBestProduct(String orderby, String order, int per_page) {
        mService
                .getBestProduct(orderby, order, per_page).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                mBestProductMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("TAG", t.getMessage(), t);
            }
        });
    }

    public void getLatestProduct(String orderby, String order, int per_page) {
        mService
                .getLatestProduct(orderby, order, per_page).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                mLatestProductMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("TAG", t.getMessage(), t);
            }
        });
    }

    public void getMostVisitedProduct(String orderby, String order, int per_page) {
        mService
                .getMostVisitedProduct(orderby, order, per_page)
                .enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        mMostVisitedProductMutableLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {
                        Log.e("TAG", t.getMessage(), t);
                    }
                });
    }


    public void getTotalProduct() {
        mService.getTotalProduct().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                mTotalProductMutableLiveData.setValue(Integer.valueOf(
                        response.headers().values("X-WP-Total").get(0)));
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("TAG", t.getMessage(), t);
            }
        });
    }

    public void getSpecialProduct(boolean featured, int per_page) {
        mService
                .getSpecialProduct(featured, per_page).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                mSpecialProductMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("TAG", t.getMessage(), t);
            }
        });
    }


    public void retrieveProduct(int id) {
        mService.retrieveProduct(id).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                mProductMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.e("TAG", t.getMessage(), t);
            }
        });
    }

    public void getProductByCategory(int categoryId) {
        mProductListService.getProductByCategory(categoryId).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                mProductByCategoryMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("TAG", t.getMessage(), t);
            }
        });
    }


}
