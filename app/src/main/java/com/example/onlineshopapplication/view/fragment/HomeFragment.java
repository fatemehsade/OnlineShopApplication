package com.example.onlineshopapplication.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshopapplication.R;
import com.example.onlineshopapplication.adapter.ProductAdapter;
import com.example.onlineshopapplication.adapter.SliderAdapter;
import com.example.onlineshopapplication.databinding.FragmentHomeBinding;
import com.example.onlineshopapplication.model.Product;
import com.example.onlineshopapplication.ViewModel.SingleHomeViewModel;

import java.util.List;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding mBinding;
    private SingleHomeViewModel mViewModel;


    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mViewModel = new ViewModelProvider(this).get(SingleHomeViewModel.class);
        mViewModel.getTotalProduct();
        setObserver();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_home,
                container,
                false);

        initToolbar();
        initRecyclerView();

        return mBinding.getRoot();
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu, menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(mBinding.homeToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(null);
    }

    private void initRecyclerView() {
        mBinding.recyclerViewBestProduct.setLayoutManager(
                new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, true));
        mBinding.recyclerViewBestProduct.addItemDecoration(new DividerItemDecoration(
                mBinding.recyclerViewBestProduct.getContext(), DividerItemDecoration.VERTICAL));
        mBinding.recyclerViewLatestProduct.setLayoutManager(
                new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, true));
        mBinding.recyclerViewMostVisitedProduct.setLayoutManager(
                new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, true));
        mBinding.recyclerViewMostVisitedProduct.addItemDecoration(new DividerItemDecoration(
                mBinding.recyclerViewMostVisitedProduct.getContext(), DividerItemDecoration.VERTICAL));
    }

    private void setObserver() {
        mViewModel.getTotalProductLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer totalProduct) {
                mViewModel.getBestProduct("rating", "desc", totalProduct);
                mViewModel.getLatestProduct("date", "desc", totalProduct);
                mViewModel.getMostVisitedProduct("popularity", "desc", totalProduct);
                mViewModel.getSpecialProduct(false, totalProduct);
            }
        });

        mViewModel.getBestProductLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> bestProducts) {
                mBinding.setBestProductTitle(getString(R.string.best_product_title));
                setupBestProductAdapter(bestProducts);
            }
        });

        mViewModel.getLatestProductLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> latestProducts) {
                mBinding.setLatestProductTitle(getString(R.string.latest_product_title));
                setupLatestProductAdapter(latestProducts);
            }
        });

        mViewModel.getMostVisitedProductLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> mostVisitedProducts) {
                mBinding.setMostVisitedProductTitle(getString(R.string.most_visited_product_title));
                setupMostVisitedProductAdapter(mostVisitedProducts);
            }
        });

        mViewModel.getSpecialProductLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> specialProducts) {
                mBinding.setSpecialProductTitle(getString(R.string.special_product_title));
                setupSpecialProductAdapter(specialProducts);
            }
        });

        mViewModel.getItemClickedSingleLiveEvent().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isItemClicked) {
                if (isItemClicked) {
                    mViewModel.getProductIdLiveData().observe(getViewLifecycleOwner(), new Observer<Integer>() {
                        @Override
                        public void onChanged(Integer id) {
                            HomeFragmentDirections.ActionNavigationHomeToDetailFragment action =
                                    HomeFragmentDirections.actionNavigationHomeToDetailFragment();
                            action.setId(id);
                            NavHostFragment.findNavController(HomeFragment.this).navigate(action);
                        }
                    });
                }
            }
        });
    }


    private void setupBestProductAdapter(List<Product> bestProducts) {
        ProductAdapter bestProductAdapter = new ProductAdapter(getContext(), mViewModel, 1, bestProducts);
        mBinding.recyclerViewBestProduct.setAdapter(bestProductAdapter);
    }

    private void setupLatestProductAdapter(List<Product> latestProducts) {
        ProductAdapter latestProductAdapter = new ProductAdapter(getContext(), mViewModel, 1, latestProducts);
        mBinding.recyclerViewLatestProduct.setAdapter(latestProductAdapter);
    }

    private void setupMostVisitedProductAdapter(List<Product> mostVisitedProducts) {
        ProductAdapter mostVisitedProductAdapter = new ProductAdapter(getContext(), mViewModel, 1, mostVisitedProducts);
        mBinding.recyclerViewMostVisitedProduct.setAdapter(mostVisitedProductAdapter);
    }

    private void setupSpecialProductAdapter(List<Product> specialProducts) {
        List<String> urls = mViewModel.getUrl(specialProducts);
        mBinding.sliderViewSpecialProduct.setSliderAdapter(new SliderAdapter(getContext(), urls));
    }
}