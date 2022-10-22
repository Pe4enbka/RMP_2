package com.example.rmp_2;

import androidx.fragment.app.Fragment;

public class EventActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new EventFragment();
    }
}