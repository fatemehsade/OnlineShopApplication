package com.example.onlineshopapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshopapplication.R;
import com.example.onlineshopapplication.databinding.ReviewAdapterItemBinding;
import com.example.onlineshopapplication.model.Review;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewHolder> {
    private Context mContext;
    private List<Review> mReviews;

    public ReviewAdapter(Context context, List<Review> reviews) {
        mContext = context;
        mReviews = reviews;
    }

    @NonNull
    @Override
    public ReviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReviewHolder(DataBindingUtil.inflate(
                LayoutInflater.from(mContext),
                R.layout.review_adapter_item,
                parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewHolder holder, int position) {
        holder.bindReview(mReviews.get(position));
    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }

    public class ReviewHolder extends RecyclerView.ViewHolder {
        private ReviewAdapterItemBinding mBinding;

        public ReviewHolder(ReviewAdapterItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bindReview(Review review) {
            mBinding.setReview(review);
        }
    }
}
