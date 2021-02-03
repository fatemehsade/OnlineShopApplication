package com.example.onlineshopapplication.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.onlineshopapplication.model.Product;
import com.example.onlineshopapplication.repository.ProductRepository;

import java.util.List;

public class ProductOfEachCategoryViewModel extends AndroidViewModel {
    private ProductRepository mRepository;
    private LiveData<List<Product>> mProductByCategoryLiveData;

    public ProductOfEachCategoryViewModel(@NonNull Application application) {
        super(application);

        mRepository = ProductRepository.getInstance(getApplication());
        mProductByCategoryLiveData = mRepository.getProductByCategoryMutableLiveData();
    }

    public LiveData<List<Product>> getProductByCategoryLiveData() {
        return mProductByCategoryLiveData;
    }

    public void getProductByCategory(int categoryId) {
        mRepository.getProductByCategory(categoryId);
    }
}
