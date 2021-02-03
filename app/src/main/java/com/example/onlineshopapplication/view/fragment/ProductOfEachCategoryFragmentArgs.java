package com.example.onlineshopapplication.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.navigation.NavArgs;

import java.util.HashMap;

public class ProductOfEachCategoryFragmentArgs implements NavArgs {
    private final HashMap arguments = new HashMap();

    private ProductOfEachCategoryFragmentArgs() {
    }

    private ProductOfEachCategoryFragmentArgs(HashMap argumentsMap) {
        this.arguments.putAll(argumentsMap);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public static ProductOfEachCategoryFragmentArgs fromBundle(@NonNull Bundle bundle) {
        ProductOfEachCategoryFragmentArgs __result = new ProductOfEachCategoryFragmentArgs();
        bundle.setClassLoader(ProductOfEachCategoryFragmentArgs.class.getClassLoader());
        if (bundle.containsKey("id")) {
            int id;
            id = bundle.getInt("id");
            __result.arguments.put("id", id);
        } else {
            __result.arguments.put("id", 0);
        }
        return __result;
    }

    @SuppressWarnings("unchecked")
    public int getId() {
        return (int) arguments.get("id");
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle toBundle() {
        Bundle __result = new Bundle();
        if (arguments.containsKey("id")) {
            int id = (int) arguments.get("id");
            __result.putInt("id", id);
        } else {
            __result.putInt("id", 0);
        }
        return __result;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        ProductOfEachCategoryFragmentArgs that = (ProductOfEachCategoryFragmentArgs) object;
        if (arguments.containsKey("id") != that.arguments.containsKey("id")) {
            return false;
        }
        if (getId() != that.getId()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + getId();
        return result;
    }

    @Override
    public String toString() {
        return "ProductOfEachCategoryFragmentArgs{"
                + "id=" + getId()
                + "}";
    }

    public static class Builder {
        private final HashMap arguments = new HashMap();

        public Builder(ProductOfEachCategoryFragmentArgs original) {
            this.arguments.putAll(original.arguments);
        }

        public Builder() {
        }

        @NonNull
        public ProductOfEachCategoryFragmentArgs build() {
            ProductOfEachCategoryFragmentArgs result = new ProductOfEachCategoryFragmentArgs(arguments);
            return result;
        }

        @NonNull
        public Builder setId(int id) {
            this.arguments.put("id", id);
            return this;
        }

        @SuppressWarnings("unchecked")
        public int getId() {
            return (int) arguments.get("id");
        }
    }
}
