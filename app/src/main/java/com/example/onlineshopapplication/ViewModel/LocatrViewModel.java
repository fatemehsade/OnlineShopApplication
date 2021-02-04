package com.example.onlineshopapplication.ViewModel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.location.Location;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlineshopapplication.model.Customer;
import com.example.onlineshopapplication.repository.ProductRepository;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;


public class LocatrViewModel extends AndroidViewModel {
    private ProductRepository mRepository;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private MutableLiveData<Location> mLocationMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<String> mAddressMutableLiveData = new MutableLiveData<>();
    private LiveData<Integer> mStatusCodePostOrderLiveData;
    private MutableLiveData<String> mFinalAddressMutableLiveData = new MutableLiveData<>();

    public LocatrViewModel(@NonNull Application application) {
        super(application);
        mRepository = ProductRepository.getInstance(getApplication());
        mStatusCodePostOrderLiveData = mRepository.getStatusCodePostOrderMutableLiveData();
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getApplication());
    }

    public LiveData<Location> getLocationLiveData() {
        return mLocationMutableLiveData;
    }

    public MutableLiveData<String> getAddressMutableLiveData() {
        return mAddressMutableLiveData;
    }

    public LiveData<Integer> getStatusCodePostOrderLiveData() {
        return mStatusCodePostOrderLiveData;
    }

    public MutableLiveData<String> getFinalAddressMutableLiveData() {
        return mFinalAddressMutableLiveData;
    }

    public void postOrder(String email) {
        mRepository.postOrder(email);
    }

    public Customer getCustomer(String email) {
        return mRepository.getCustomer(email);
    }

    public void updateCustomer(Customer customer) {
        mRepository.updateCustomer(customer);
    }

    @SuppressLint("MissingPermission")
    public void getCurrentLocation() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(0);
        locationRequest.setNumUpdates(1);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                mLocationMutableLiveData.setValue(locationResult.getLocations().get(0));
            }
        };

        mFusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper());
    }
}

