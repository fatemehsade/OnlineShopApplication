package com.example.onlineshopapplication.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlineshopapplication.model.Customer;
import com.example.onlineshopapplication.repository.ProductRepository;

public class SignupViewModel extends AndroidViewModel {
    private ProductRepository mRepository;
    private LiveData<Integer> mStatusCodePostCustomerLiveData;


    public SignupViewModel(@NonNull Application application) {
        super(application);

        mRepository = ProductRepository.getInstance(getApplication());
        mStatusCodePostCustomerLiveData = mRepository.getStatusCodePostCustomerMutableLiveData();
    }

    public LiveData<Integer> getStatusCodePostCustomerLiveData() {
        return mStatusCodePostCustomerLiveData;
    }

    public void postCustomer(String email) {
        mRepository.postCustomer(email);
    }

    public void insert(Customer customer) {
        mRepository.insert(customer);
    }
}
