package com.example.onlineshopapplication.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshopapplication.R;
import com.example.onlineshopapplication.databinding.FragmentDetailBinding;


public class DetailFragment extends Fragment {
    private FragmentDetailBinding mBinding;



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
}