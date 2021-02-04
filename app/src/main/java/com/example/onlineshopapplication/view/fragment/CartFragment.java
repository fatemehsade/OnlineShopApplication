package com.example.onlineshopapplication.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.onlineshopapplication.R;
import com.example.onlineshopapplication.adapter.ProductAdapter;
import com.example.onlineshopapplication.databinding.FragmentCartBinding;
import com.example.onlineshopapplication.model.Product;
import com.example.onlineshopapplication.utilities.Preferences;
import com.example.onlineshopapplication.ViewModel.SingleSharedDetailViewModel;

import java.util.List;

public class CartFragment extends Fragment {

    private FragmentCartBinding mBinding;
    private SingleSharedDetailViewModel mViewModel;
    private static final String TAG = "AlertDialogFragment";

    public static CartFragment newInstance() {
        Bundle args = new Bundle();
        CartFragment fragment = new CartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(requireActivity()).get(SingleSharedDetailViewModel.class);
        setObserver();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_cart,
                container,
                false);

        initRecyclerView();
        mBinding.btnContinueBuying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialogFragment fragment = AlertDialogFragment.newInstance(
                        mViewModel.getTotalAmountPaidMutableLiveData().getValue());
                fragment.show(getParentFragmentManager(), TAG);
            }
        });

        return mBinding.getRoot();
    }

    private void setObserver() {
        mViewModel.getItemClickedMutableLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isItemClicked) {
                if (isItemClicked) {
                    CartFragmentDirections.ActionNavigationCartToDetailFragment action =
                            CartFragmentDirections.actionNavigationCartToDetailFragment();
                    action.setId(mViewModel.getProductMutableLiveData().getValue().getId());
                    NavHostFragment.findNavController(CartFragment.this).navigate(action);
                }
            }
        });

        mViewModel.getProductListMutableLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                setupAdapter(products);
            }
        });

        mViewModel.getPriceMutableLiveData().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> prices) {
                calculateTotalPrice(prices);
            }
        });

        mViewModel.getAddClickedSingleLiveEvent().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isAddClicked) {
                if (isAddClicked) {
                    mViewModel.getPrices().add(mViewModel.getProductMutableLiveData().getValue().getPrice());
                    mViewModel.getPriceMutableLiveData().setValue(mViewModel.getPrices());
                }
            }
        });

        mViewModel.getDeleteClickedSingleLiveEvent().

                observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean isDeleteClicked) {
                        if (isDeleteClicked) {
                            mViewModel.getPrices().remove(mViewModel.getProductMutableLiveData().getValue().getPrice());
                            mViewModel.getPriceMutableLiveData().setValue(mViewModel.getPrices());
                            mViewModel.getProducts().remove(mViewModel.getProductMutableLiveData().getValue());
                            mViewModel.getProductListMutableLiveData().setValue(mViewModel.getProducts());
                        }
                    }
                });

        mViewModel.getRemoveClickedSingleLiveEvent().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isRemoveClicked) {
                if (isRemoveClicked) {
                    mViewModel.getPrices().remove(mViewModel.getProductMutableLiveData().getValue().getPrice());
                    mViewModel.getPriceMutableLiveData().setValue(mViewModel.getPrices());
                }
            }
        });

        mViewModel.getOkClickedSingleLiveEvent().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isOkClicked) {
                if (isOkClicked) {
                    if (Preferences.getIsLogin(getContext())) {
                        CartFragmentDirections.ActionNavigationCartToAddressFragment action =
                                CartFragmentDirections.actionNavigationCartToAddressFragment();
                        action.setEmail(Preferences.getEmail(getContext()));
                        NavHostFragment.findNavController(CartFragment.this).navigate(action);
                    } else {
                        NavHostFragment.findNavController(CartFragment.this)
                                .navigate(R.id.action_navigation_cart_to_loginFragment);
                    }
                }
            }
        });
    }

    private void initRecyclerView() {
        mBinding.recyclerViewCart.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setupAdapter(List<Product> products) {
        ProductAdapter productAdapter = new ProductAdapter(getContext(), mViewModel, 2, products);
        mBinding.recyclerViewCart.setAdapter(productAdapter);
    }

    private void calculateTotalPrice(List<String> prices) {
        Double totalPrice = 0.0;
        for (String price : prices) {
            totalPrice += Double.parseDouble(price);
        }
        mViewModel.getTotalAmountPaidMutableLiveData().setValue(String.valueOf(totalPrice));
        mBinding.setTotalPrice(String.valueOf(totalPrice));
    }
}
