package com.example.onlineshopapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshopapplication.R;

import com.example.onlineshopapplication.databinding.CartProductAdapterItemBinding;
import com.example.onlineshopapplication.databinding.HomeProductAdapterItemBinding;
import com.example.onlineshopapplication.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private int mViewType;
    private List<Product> mProducts;

    public ProductAdapter(Context context, int viewType, List<Product> products) {
        mContext = context;
        mViewType = viewType;
        mProducts = products;
    }

    public List<Product> getProducts() {
        return mProducts;
    }

    public void setProducts(List<Product> products) {
        mProducts = products;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 2) {
            return new CartProductHolder(DataBindingUtil.inflate(
                    LayoutInflater.from(mContext),
                    R.layout.cart_product_adapter_item,
                    parent,
                    false));
        } else {
            return new HomeProductHolder(DataBindingUtil.inflate(
                    LayoutInflater.from(mContext),
                    R.layout.home_product_adapter_item,
                    parent,
                    false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HomeProductHolder) {
            ((HomeProductHolder) holder).bindProduct(mProducts.get(position));
        }
        if (holder instanceof CartProductHolder) {
            ((CartProductHolder) holder).bindProduct(mProducts.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }


    public class HomeProductHolder extends RecyclerView.ViewHolder {
        private HomeProductAdapterItemBinding mBinding;

        public HomeProductHolder(HomeProductAdapterItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bindProduct(Product product) {
            mBinding.setProduct(product);
        }
    }

    public class CartProductHolder extends RecyclerView.ViewHolder {
        private CartProductAdapterItemBinding mBinding;

        public CartProductHolder(CartProductAdapterItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bindProduct(Product product) {
            mBinding.setProduct(product);
        }
    }
}
