package com.example.onlineshopapplication.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshopapplication.R;
import com.example.onlineshopapplication.ViewModel.Home;
import com.example.onlineshopapplication.adapter.ProductAdapter;
import com.example.onlineshopapplication.databinding.FragmentHomeBinding;
import com.example.onlineshopapplication.model.Product;

import java.util.List;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding mBinding;
    private Home mHomeViewModel;



    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mHomeViewModel = new ViewModelProvider(this).get(Home.class);
        mHomeViewModel.getTotalProductLiveData();
        setObserver();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding= DataBindingUtil.inflate(
                inflater,R.layout.fragment_home,
                container,
                false);
        return mBinding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu,menu);
    }

    private void initToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(null);
    }

    private void initRecyclerView() {
        mBinding.recyclerViewBetter.setLayoutManager(
                new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, true));
        mBinding.recyclerViewRecent.setLayoutManager(
                new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, true));
        mBinding.recyclerViewView.setLayoutManager(
                new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, true));
    }

    private void setObserver() {
        mHomeViewModel.getTotalProductLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer totalProduct) {
                mHomeViewModel.getBestProduct("rating", "desc", totalProduct);
                mHomeViewModel.getLatestProduct("date", "desc", totalProduct);
                mHomeViewModel.getMostVisitedProduct("popularity", "desc", totalProduct);
            }
        });

        mHomeViewModel.getBestProductLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> bestProducts) {
                mBinding.setBestProduct(getString(R.string.best_product_title));
                setupBestProductAdapter(bestProducts);
            }
        });

        mHomeViewModel.getLatestProductLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> latestProducts) {
                mBinding.setRecentProduct(getString(R.string.latest_product_title));
                setupLatestProductAdapter(latestProducts);
            }
        });

        mHomeViewModel.getMostVisitedProductLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> mostVisitedProducts) {
                mBinding.setViewProduct(getString(R.string.most_visited_product_title));
                setupMostVisitedProductAdapter(mostVisitedProducts);
            }
        });
    }


    private void setupBestProductAdapter(List<Product> bestProducts) {
        ProductAdapter bestProductAdapter = new ProductAdapter(getContext(), bestProducts);
        mBinding.recyclerViewBetter.setAdapter(bestProductAdapter);
    }

    private void setupLatestProductAdapter(List<Product> latestProducts) {
        ProductAdapter latestProductAdapter = new ProductAdapter(getContext(), latestProducts);
        mBinding.recyclerViewRecent.setAdapter(latestProductAdapter);
    }

    private void setupMostVisitedProductAdapter(List<Product> mostVisitedProducts) {
        ProductAdapter mostVisitedProductAdapter = new ProductAdapter(getContext(), mostVisitedProducts);
        mBinding.recyclerViewView.setAdapter(mostVisitedProductAdapter);
    }

}