package com.example.onlineshopapplication.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlineshopapplication.repository.ProductRepository;

public class AddressViewModel extends AndroidViewModel {
    private ProductRepository mRepository;
    private LiveData<Integer> mStatusCodePostOrderLiveData;

    public AddressViewModel(@NonNull Application application) {
        super(application);

        mRepository = ProductRepository.getInstance(getApplication());
        mStatusCodePostOrderLiveData = mRepository.getStatusCodePostOrderMutableLiveData();
    }

    public LiveData<Integer> getStatusCodePostOrderLiveData() {
        return mStatusCodePostOrderLiveData;
    }

    public void postOrder(String email) {
        mRepository.postOrder(email);
    }
}
