package com.example.onlineshopapplication.view.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.onlineshopapplication.R;
import com.example.onlineshopapplication.adapter.SearchAdapter;
import com.example.onlineshopapplication.databinding.ActivitySearchableBinding;
import com.example.onlineshopapplication.model.Product;
import com.example.onlineshopapplication.ViewModel.SearchableViewModel;

import java.util.List;

public class SearchableActivity extends AppCompatActivity {
    private ActivitySearchableBinding mBinding;
    private SearchableViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_searchable);

        mViewModel = new ViewModelProvider(this).get(SearchableViewModel.class);
        setObserver();

        handleIntent();
    }



    private void handleIntent() {
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }
    }

    private void doMySearch(String query) {
        mViewModel.searchProducts(query);
    }

    private void setupAdapter(List<Product> products) {
        SearchAdapter adapter = new SearchAdapter(this, products);
        mBinding.listViewSearchResult.setAdapter(adapter);
    }

    private void setObserver() {
        mViewModel.getSearchProductLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                setupAdapter(products);
            }
        });
    }
}