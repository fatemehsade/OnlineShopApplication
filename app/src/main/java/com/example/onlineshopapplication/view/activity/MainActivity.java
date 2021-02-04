package com.example.onlineshopapplication.view.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.onlineshopapplication.R;
import com.example.onlineshopapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements NavController.OnDestinationChangedListener {
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(mBinding.navView, navController);
        navController.addOnDestinationChangedListener(this::onDestinationChanged);

    }

    @Override
    public void onDestinationChanged(@NonNull NavController controller,
                                     @NonNull NavDestination destination,
                                     @Nullable Bundle arguments) {
        if (destination.getId() == R.id.loginFragment || destination.getId() == R.id.signupFragment
                || destination.getId() == R.id.addressFragment) {
            mBinding.navView.setVisibility(View.GONE);
        } else {
            mBinding.navView.setVisibility(View.VISIBLE);
        }
    }
}