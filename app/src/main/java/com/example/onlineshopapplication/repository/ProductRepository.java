package com.example.onlineshopapplication.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.onlineshopapplication.database.CustomerDataBase;
import com.example.onlineshopapplication.model.Category;
import com.example.onlineshopapplication.model.Customer;
import com.example.onlineshopapplication.model.Order;
import com.example.onlineshopapplication.model.Product;
import com.example.onlineshopapplication.model.Review;
import com.example.onlineshopapplication.remote.CategoryListDeserializer;
import com.example.onlineshopapplication.remote.ProductDeserializer;
import com.example.onlineshopapplication.remote.ProductListDeserializer;
import com.example.onlineshopapplication.remote.ProductService;
import com.example.onlineshopapplication.remote.RetrofitInstance;
import com.example.onlineshopapplication.remote.ReviewDeserializer;
import com.example.onlineshopapplication.remote.ReviewListDeserializer;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {
    private Context mContext;
    private ProductService mProductListService, mProductService, mCategoryService, mReviewListService, mReviewService;
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
    private MutableLiveData<List<Review>> mReviewListMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Review> mReviewMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Review> mUpdateReviewMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Category>> mCategoryListMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mSearchProductMutableLiveData = new MutableLiveData<>();


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

        mReviewListService = RetrofitInstance.getRetrofitInstance(
                new TypeToken<List<Review>>() {
                }.getType(),
                new ReviewListDeserializer()).create(ProductService.class);

        mReviewService = RetrofitInstance.getRetrofitInstance(
                new TypeToken<Review>() {
                }.getType(),
                new ReviewDeserializer()).create(ProductService.class);

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

    public MutableLiveData<List<Review>> getReviewListMutableLiveData() {
        return mReviewListMutableLiveData;
    }

    public MutableLiveData<Review> getReviewMutableLiveData() {
        return mReviewMutableLiveData;
    }

    public MutableLiveData<Review> getUpdateReviewMutableLiveData() {
        return mUpdateReviewMutableLiveData;
    }

    public MutableLiveData<List<Category>> getCategoryListMutableLiveData() {
        return mCategoryListMutableLiveData;
    }

    public MutableLiveData<List<Product>> getSearchProductMutableLiveData() {
        return mSearchProductMutableLiveData;
    }

    public void insert(Customer customer) {
        mDataBase.getCustomerDao().insert(customer);
    }

    public List<Customer> getCustomers() {
        return mDataBase.getCustomerDao().getCustomers();
    }

    public Customer getCustomer(String email) {
        return mDataBase.getCustomerDao().getCustomer(email);
    }

    public void updateCustomer(Customer customer) {
        mDataBase.getCustomerDao().updateCustomer(customer);
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

    public void getBestProduct(String orderby, String order) {
        mProductListService
                .getBestProduct(orderby, order).enqueue(new Callback<List<Product>>() {
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

    public void getLatestProduct(String orderby, String order) {
        mProductListService
                .getLatestProduct(orderby, order).enqueue(new Callback<List<Product>>() {
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

    public void getMostVisitedProduct(String orderby, String order) {
        mProductListService
                .getMostVisitedProduct(orderby, order)
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

    public void getSpecialProduct(boolean featured) {
        mProductListService
                .getSpecialProduct(featured).enqueue(new Callback<List<Product>>() {
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

    public void getReviews(int id) {
        mReviewListService.getReviews(id).enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                mReviewListMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }

    public void postReview(int productId, String content, String name, String email, int rating) {
        mReviewService.postReview(productId, content, name, email, rating).enqueue(new Callback<Review>() {
            @Override
            public void onResponse(Call<Review> call, Response<Review> response) {
                mReviewMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Review> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }

    public void deleteReview(int id) {
        mReviewService.deleteReview(id).enqueue(new Callback<Review>() {
            @Override
            public void onResponse(Call<Review> call, Response<Review> response) {
                Log.d(TAG, "delete is successful");
            }

            @Override
            public void onFailure(Call<Review> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }

    public void updateReview(int id, String content, String name, int rating) {
        mReviewService.updateReview(id, content, name, rating).enqueue(new Callback<Review>() {
            @Override
            public void onResponse(Call<Review> call, Response<Review> response) {
                mUpdateReviewMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Review> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }

    public List<Product> getProducts() {
        try {
            Response<List<Product>> response = mProductListService.getProducts().execute();
            return response.body();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
            return null;
        }
    }

    public void getCategory(int page) {
        mCategoryService.getCategory(page).enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                mCategoryListMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }

    public void searchProducts(String search) {
        mProductListService.searchProducts(search).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                mSearchProductMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }
}

