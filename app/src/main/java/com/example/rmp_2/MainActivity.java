package com.example.rmp_2;

import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.Fragment;
import java.util.UUID;


public class MainActivity extends SingleFragmentActivity {
    private static final String EXTRA_EVENT_ID = "rmp_2.Event_id";

    public static Intent newIntent(Context packageContext, UUID eventID) {
        Intent intent = new Intent(packageContext, MainActivity.class);
        intent.putExtra(EXTRA_EVENT_ID, eventID);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID eventID = (UUID) getIntent().getSerializableExtra(EXTRA_EVENT_ID);
        return EventFragment.newInstance(eventID);
    }
}