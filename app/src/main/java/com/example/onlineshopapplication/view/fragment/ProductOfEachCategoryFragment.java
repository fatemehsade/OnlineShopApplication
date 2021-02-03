package com.example.onlineshopapplication.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshopapplication.R;
import com.example.onlineshopapplication.ViewModel.ProductOfEachCategoryViewModel;
import com.example.onlineshopapplication.adapter.ProductAdapter;
import com.example.onlineshopapplication.databinding.FragmentProductOfEachCategoryBinding;
import com.example.onlineshopapplication.model.Product;

import java.util.List;


public class ProductOfEachCategoryFragment extends Fragment {

    private FragmentProductOfEachCategoryBinding mBinding;
    private ProductOfEachCategoryViewModel mViewModel;


    public static ProductOfEachCategoryFragment newInstance() {
        ProductOfEachCategoryFragment fragment = new ProductOfEachCategoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(ProductOfEachCategoryViewModel.class);
        setObserver();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_product_of_each_category,
                container,
                false);

        initRecyclerView();

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ProductOfEachCategoryFragmentArgs args = ProductOfEachCategoryFragmentArgs.fromBundle(getArguments());
        int id = args.getId();
        mViewModel.getProductByCategory(id);
    }

    private void setObserver() {
        mViewModel.getProductByCategoryLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                setupAdapter(products);
            }
        });
    }

    private void initRecyclerView() {
        mBinding.recyclerViewProductOfEachCategory.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setupAdapter(List<Product> products) {
        ProductAdapter productAdapter = new ProductAdapter(getContext(), 3, products);
        mBinding.recyclerViewProductOfEachCategory.setAdapter(productAdapter);
    }
}