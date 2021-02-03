package com.example.onlineshopapplication.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshopapplication.R;
import com.example.onlineshopapplication.ViewModel.DetailViewModel;
import com.example.onlineshopapplication.adapter.SliderAdapter;
import com.example.onlineshopapplication.databinding.FragmentDetailBinding;
import com.example.onlineshopapplication.model.Product;


public class DetailFragment extends Fragment {
    private FragmentDetailBinding mBinding;
    private DetailViewModel mViewModel;




    public DetailFragment() {
        // Required empty public constructor
    }


    public static DetailFragment newInstance() {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        setObserver();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_detail,
                container,
                false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DetailFragmentArgs args = DetailFragmentArgs.fromBundle(getArguments());
        int id = args.getId();
        mViewModel.retrieveProduct(id);
    }

    private void setObserver() {
        mViewModel.getProductLiveData().observe(this, new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                initViews(product);
            }
        });
    }

    private void initViews(Product product) {
        mBinding.setProduct(product);
        SliderAdapter sliderAdapter = new SliderAdapter(getContext(), product.getImageUrl());
        mBinding.imgProductSlider.setSliderAdapter(sliderAdapter);
    }
}