package com.example.onlineshopapplication.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.navigation.NavArgs;

import java.util.HashMap;

public class AddressFragmentArgs implements NavArgs {

    private final HashMap arguments = new HashMap();

    private AddressFragmentArgs() {
    }

    private AddressFragmentArgs(HashMap argumentsMap) {
        this.arguments.putAll(argumentsMap);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public static AddressFragmentArgs fromBundle(@NonNull Bundle bundle) {
        AddressFragmentArgs __result = new AddressFragmentArgs();
        bundle.setClassLoader(AddressFragmentArgs.class.getClassLoader());
        if (bundle.containsKey("email")) {
            String email;
            email = bundle.getString("email");
            if (email == null) {
                throw new IllegalArgumentException("Argument \"email\" is marked as non-null but was passed a null value.");
            }
            __result.arguments.put("email", email);
        } else {
            __result.arguments.put("email", "null");
        }
        return __result;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getEmail() {
        return (String) arguments.get("email");
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle toBundle() {
        Bundle __result = new Bundle();
        if (arguments.containsKey("email")) {
            String email = (String) arguments.get("email");
            __result.putString("email", email);
        } else {
            __result.putString("email", "null");
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
        AddressFragmentArgs that = (AddressFragmentArgs) object;
        if (arguments.containsKey("email") != that.arguments.containsKey("email")) {
            return false;
        }
        if (getEmail() != null ? !getEmail().equals(that.getEmail()) : that.getEmail() != null) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AddressFragmentArgs{"
                + "email=" + getEmail()
                + "}";
    }

    public static class Builder {
        private final HashMap arguments = new HashMap();

        public Builder(AddressFragmentArgs original) {
            this.arguments.putAll(original.arguments);
        }

        public Builder() {
        }

        @NonNull
        public AddressFragmentArgs build() {
            AddressFragmentArgs result = new AddressFragmentArgs(arguments);
            return result;
        }

        @NonNull
        public Builder setEmail(@NonNull String email) {
            if (email == null) {
                throw new IllegalArgumentException("Argument \"email\" is marked as non-null but was passed a null value.");
            }
            this.arguments.put("email", email);
            return this;
        }

        @SuppressWarnings("unchecked")
        @NonNull
        public String getEmail() {
            return (String) arguments.get("email");
        }
    }
}
