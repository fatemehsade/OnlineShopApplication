package com.example.onlineshopapplication.view.fragment;

import android.os.Bundle;
import android.util.Log;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshopapplication.R;
import com.example.onlineshopapplication.adapter.ReviewAdapter;
import com.example.onlineshopapplication.adapter.SliderAdapter;
import com.example.onlineshopapplication.databinding.FragmentDetailBinding;
import com.example.onlineshopapplication.model.Product;
import com.example.onlineshopapplication.model.Review;
import com.example.onlineshopapplication.service.PollService;
import com.example.onlineshopapplication.ViewModel.SingleSharedDetailViewModel;

import java.util.List;

public class DetailFragment extends Fragment {
    private FragmentDetailBinding mBinding;
    private SingleSharedDetailViewModel mViewModel;
    private ReviewAdapter mAdapter;
    private Product mProduct;
    private int mId;

    public static DetailFragment newInstance() {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mViewModel = new ViewModelProvider(requireActivity()).get(SingleSharedDetailViewModel.class);
        DetailFragmentArgs args = DetailFragmentArgs.fromBundle(getArguments());
        mId = args.getId();
        mViewModel.retrieveProduct(mId);
        mViewModel.getReviews(mId);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_detail,
                container,
                false);

        initToolbar();
        initRecyclerView();
        setListener();

        return mBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int id = getArguments().getInt(PollService.BUNDLE_LAST_ID);
        mViewModel.retrieveProduct(id);
        setObserver();
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.detail_menu, menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_add_to_cart:
                mViewModel.getProducts().add(mProduct);
                mViewModel.getProductListMutableLiveData().setValue(mViewModel.getProducts());
                if (mProduct.getPrice() != null) {
                    mViewModel.getPrices().add(mProduct.getPrice());
                    mViewModel.getPriceListMutableLiveData().setValue(mViewModel.getPrices());
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void initToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(mBinding.detailToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(null);
    }


    private void initRecyclerView() {
        mBinding.recyclerViewReview.setLayoutManager(new LinearLayoutManager(
                getContext(),
                RecyclerView.HORIZONTAL,
                true));
    }


    private void setListener() {
        mBinding.btnAddReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailFragmentDirections.ActionDetailFragmentToReviewFragment action =
                        DetailFragmentDirections.actionDetailFragmentToReviewFragment();
                action.setId(mId);
                NavHostFragment.findNavController(DetailFragment.this).navigate(action);
            }
        });
    }


    private void setObserver() {
        mViewModel.getRetrieveProductLiveData().observe(getViewLifecycleOwner(), new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                mProduct = product;
                initViews(product);
            }
        });


        mViewModel.getReviewListLiveData().observe(getViewLifecycleOwner(), new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviews) {
                setupAdapter(reviews);
            }
        });
    }


    private void initViews(Product product) {
        if (product != null) {
            mBinding.setProduct(product);
            SliderAdapter sliderAdapter = new SliderAdapter(getContext(), product.getImageUrl());
            mBinding.imgProductSlider.setSliderAdapter(sliderAdapter);
        }
    }


    private void setupAdapter(List<Review> reviews) {
        if (mAdapter == null) {
            mAdapter = new ReviewAdapter(getContext(), reviews);
            mBinding.recyclerViewReview.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }
}