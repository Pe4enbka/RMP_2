package com.example.rmp_2;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import java.util.UUID;

public class EventActivity extends SingleFragmentActivity {
    public static final String EXTRA_EVENT_ID = "rmp_2.Event_id";
    public static Intent newIntent(Context packageContext, UUID EventId) {
        Intent intent = new Intent(packageContext, EventActivity.class);
        intent.putExtra(EXTRA_EVENT_ID, EventId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID EventId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_EVENT_ID);
        return EventFragment.newInstance(EventId);
    }
}