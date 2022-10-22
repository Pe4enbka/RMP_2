package com.example.rmp_2;

import androidx.fragment.app.Fragment;

public class EventListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new EventListFragment();
    }
}
