package com.example.onlineshopapplication.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;

import com.example.onlineshopapplication.R;

import java.util.HashMap;

public class CartFragmentDirections {
    private CartFragmentDirections() {
    }

    @NonNull
    public static ActionNavigationCartToDetailFragment actionNavigationCartToDetailFragment() {
        return new ActionNavigationCartToDetailFragment();
    }

    @NonNull
    public static NavDirections actionNavigationCartToLoginFragment() {
        return new ActionOnlyNavDirections(R.id.action_navigation_cart_to_loginFragment);
    }

    @NonNull
    public static ActionNavigationCartToAddressFragment actionNavigationCartToAddressFragment() {
        return new ActionNavigationCartToAddressFragment();
    }

    public static class ActionNavigationCartToDetailFragment implements NavDirections {
        private final HashMap arguments = new HashMap();

        private ActionNavigationCartToDetailFragment() {
        }

        @NonNull
        public ActionNavigationCartToDetailFragment setId(int id) {
            this.arguments.put("id", id);
            return this;
        }

        @Override
        @SuppressWarnings("unchecked")
        @NonNull
        public Bundle getArguments() {
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
        public int getActionId() {
            return R.id.action_navigation_cart_to_detailFragment;
        }

        @SuppressWarnings("unchecked")
        public int getId() {
            return (int) arguments.get("id");
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }
            ActionNavigationCartToDetailFragment that = (ActionNavigationCartToDetailFragment) object;
            if (arguments.containsKey("id") != that.arguments.containsKey("id")) {
                return false;
            }
            if (getId() != that.getId()) {
                return false;
            }
            if (getActionId() != that.getActionId()) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int result = 1;
            result = 31 * result + getId();
            result = 31 * result + getActionId();
            return result;
        }

        @Override
        public String toString() {
            return "ActionNavigationCartToDetailFragment(actionId=" + getActionId() + "){"
                    + "id=" + getId()
                    + "}";
        }
    }

    public static class ActionNavigationCartToAddressFragment implements NavDirections {
        private final HashMap arguments = new HashMap();

        private ActionNavigationCartToAddressFragment() {
        }

        @NonNull
        public ActionNavigationCartToAddressFragment setEmail(@NonNull String email) {
            if (email == null) {
                throw new IllegalArgumentException("Argument \"email\" is marked as non-null but was passed a null value.");
            }
            this.arguments.put("email", email);
            return this;
        }

        @Override
        @SuppressWarnings("unchecked")
        @NonNull
        public Bundle getArguments() {
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
        public int getActionId() {
            return R.id.action_navigation_cart_to_addressFragment;
        }

        @SuppressWarnings("unchecked")
        @NonNull
        public String getEmail() {
            return (String) arguments.get("email");
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }
            ActionNavigationCartToAddressFragment that = (ActionNavigationCartToAddressFragment) object;
            if (arguments.containsKey("email") != that.arguments.containsKey("email")) {
                return false;
            }
            if (getEmail() != null ? !getEmail().equals(that.getEmail()) : that.getEmail() != null) {
                return false;
            }
            if (getActionId() != that.getActionId()) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int result = 1;
            result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
            result = 31 * result + getActionId();
            return result;
        }

        @Override
        public String toString() {
            return "ActionNavigationCartToAddressFragment(actionId=" + getActionId() + "){"
                    + "email=" + getEmail()
                    + "}";
        }
    }
}
