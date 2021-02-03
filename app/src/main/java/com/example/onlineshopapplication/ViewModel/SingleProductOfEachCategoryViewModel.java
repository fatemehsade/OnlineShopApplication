package com.example.onlineshopapplication.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlineshopapplication.model.Product;
import com.example.onlineshopapplication.repository.ProductRepository;
import com.example.onlineshopapplication.singleliveevent.SingleLiveEvent;

import java.util.List;

public class SingleProductOfEachCategoryViewModel extends AndroidViewModel {
    private ProductRepository mRepository;
    private LiveData<List<Product>> mProductByCategoryLiveData;
    private SingleLiveEvent<Boolean> mItemClickedSingleLiveEvent = new SingleLiveEvent<>();
    private MutableLiveData<Integer> mProductIdMutableLiveData = new MutableLiveData<>();
    private LiveData<Integer> mTotalPageLiveData;




    public SingleProductOfEachCategoryViewModel(@NonNull Application application) {
        super(application);

        mRepository = ProductRepository.getInstance(getApplication());
        mProductByCategoryLiveData = mRepository.getProductByCategoryMutableLiveData();
    }

    public LiveData<List<Product>> getProductByCategoryLiveData() {
        return mProductByCategoryLiveData;
    }

    public void getProductByCategory(int categoryId, int page) {
        mRepository.getProductByCategory(categoryId, page);
    }
    public SingleLiveEvent<Boolean> getItemClickedSingleLiveEvent() {
        return mItemClickedSingleLiveEvent;
    }
    public MutableLiveData<Integer> getProductIdMutableLiveData() {
        return mProductIdMutableLiveData;
    }

    public LiveData<Integer> getTotalPageLiveData() {
        return mTotalPageLiveData;
    }


}
