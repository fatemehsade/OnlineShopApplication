package com.example.onlineshopapplication.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;


import com.example.onlineshopapplication.database.CustomerDataBase;
import com.example.onlineshopapplication.model.Category;
import com.example.onlineshopapplication.model.Customer;
import com.example.onlineshopapplication.model.Order;
import com.example.onlineshopapplication.model.Product;
import com.example.onlineshopapplication.remote.CategoryListDeserializer;
import com.example.onlineshopapplication.remote.ProductDeserializer;
import com.example.onlineshopapplication.remote.ProductListDeserializer;
import com.example.onlineshopapplication.remote.ProductService;
import com.example.onlineshopapplication.remote.RetrofitInstance;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {
    private Context mContext;
    private ProductService mProductListService, mProductService, mCategoryService;
    private static ProductRepository sInstance;
    private CustomerDataBase mDataBase;

    private MutableLiveData<Integer> mTotalProductMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mBestProductMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mLatestProductMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mMostVisitedProductMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mSpecialProductMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Product> mProductMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mProductByCategoryMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Integer> mTotalPageMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Integer> mStatusCodePostCustomerMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Integer> mStatusCodePostOrderMutableLiveData = new MutableLiveData<>();

    private static final String TAG = ProductRepository.class.getSimpleName();

    private ProductRepository(Context context) {
        mProductListService = RetrofitInstance.getRetrofitInstance(
                new TypeToken<List<Product>>() {
                }.getType(),
                new ProductListDeserializer()).create(ProductService.class);

        mProductService = RetrofitInstance.getRetrofitInstance(
                new TypeToken<Product>() {
                }.getType(),
                new ProductDeserializer()).create(ProductService.class);

        mCategoryService = RetrofitInstance.getRetrofitInstance(
                new TypeToken<List<Category>>() {
                }.getType(),
                new CategoryListDeserializer()).create(ProductService.class);

        mContext = context;
        mDataBase = CustomerDataBase.getInstance(mContext.getApplicationContext());
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

    public MutableLiveData<List<Product>> getSpecialProductMutableLiveData() {
        return mSpecialProductMutableLiveData;
    }

    public MutableLiveData<Product> getProductMutableLiveData() {
        return mProductMutableLiveData;
    }

    public MutableLiveData<List<Product>> getProductByCategoryMutableLiveData() {
        return mProductByCategoryMutableLiveData;
    }

    public MutableLiveData<Integer> getTotalPageMutableLiveData() {
        return mTotalPageMutableLiveData;
    }

    public MutableLiveData<Integer> getStatusCodePostCustomerMutableLiveData() {
        return mStatusCodePostCustomerMutableLiveData;
    }

    public MutableLiveData<Integer> getStatusCodePostOrderMutableLiveData() {
        return mStatusCodePostOrderMutableLiveData;
    }

    public void insert(Customer customer) {
        mDataBase.getCustomerDao().insert(customer);
    }

    public List<Customer> getCustomers() {
        return mDataBase.getCustomerDao().getCustomers();
    }

    public void getTotalProduct() {
        mProductListService.getTotalProduct().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                mTotalProductMutableLiveData.setValue(Integer.valueOf(
                        response.headers().values("X-WP-Total").get(0)));
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }

    public void getBestProduct(String orderby, String order, int per_page) {
        mProductListService
                .getBestProduct(orderby, order, per_page).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                mBestProductMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }

    public void getLatestProduct(String orderby, String order, int per_page) {
        mProductListService
                .getLatestProduct(orderby, order, per_page).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                mLatestProductMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }

    public void getMostVisitedProduct(String orderby, String order, int per_page) {
        mProductListService
                .getMostVisitedProduct(orderby, order, per_page)
                .enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        mMostVisitedProductMutableLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {
                        Log.e(TAG, t.getMessage(), t);
                    }
                });
    }

    public void getSpecialProduct(boolean featured, int per_page) {
        mProductListService
                .getSpecialProduct(featured, per_page).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                mSpecialProductMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }

    public void retrieveProduct(int id) {
        mProductService.retrieveProduct(id).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                mProductMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }

    public void getProductByCategory(int categoryId, int page) {
        mProductListService.getProductByCategory(categoryId, page).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                mProductByCategoryMutableLiveData.setValue(response.body());
                mTotalPageMutableLiveData.setValue(Integer.valueOf(response.headers().values("x-wp-totalpages").get(0)));
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }

    public void postCustomer(String email) {
        mProductListService.postCustomer(email).enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                mStatusCodePostCustomerMutableLiveData.setValue(response.code());
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }

    public void postOrder(String email) {
        mProductListService.postOrder(email).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                mStatusCodePostOrderMutableLiveData.setValue(response.code());
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }
}
