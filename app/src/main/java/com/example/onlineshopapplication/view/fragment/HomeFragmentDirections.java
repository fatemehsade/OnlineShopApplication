package com.example.onlineshopapplication.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;

import com.example.onlineshopapplication.R;

import java.util.HashMap;

public class HomeFragmentDirections {
    private HomeFragmentDirections() {
    }

    @NonNull
    public static ActionNavigationHomeToDetailFragment actionNavigationHomeToDetailFragment() {
        return new ActionNavigationHomeToDetailFragment();
    }

    public static class ActionNavigationHomeToDetailFragment implements NavDirections {
        private final HashMap arguments = new HashMap();

        private ActionNavigationHomeToDetailFragment() {
        }

        @NonNull
        public ActionNavigationHomeToDetailFragment setId(int id) {
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
            return R.id.action_navigation_home_to_detailFragment;
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
            ActionNavigationHomeToDetailFragment that = (ActionNavigationHomeToDetailFragment) object;
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
            return "ActionNavigationHomeToDetailFragment(actionId=" + getActionId() + "){"
                    + "id=" + getId()
                    + "}";
        }
    }
}
