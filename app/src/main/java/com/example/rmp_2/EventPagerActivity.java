package com.example.rmp_2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;
import java.util.UUID;

public class EventPagerActivity extends FragmentActivity {
    private static final String EXTRA_EVENT_ID = "com.bignerdranch.android.criminalintent.event_id";
    private ViewPager mViewPager;
    private List<Event> mEvents;

    public static Intent newIntent (Context packageContext, UUID eventId){
        Intent intent = new Intent(packageContext, EventPagerActivity.class);
        intent.putExtra(EXTRA_EVENT_ID, eventId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_pager);

        UUID eventId = (UUID) getIntent().getSerializableExtra(EXTRA_EVENT_ID);
        mViewPager =(ViewPager) findViewById(R.id.activity_event_pager_view_pager);
        mEvents = EventLab.get(this).getEvents();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Event event = mEvents.get(position);
                return EventFragment.newInstance(event.getmId());
            }

            @Override
            public int getCount() {
                return mEvents.size();
            }
        });
        for(int i = 0; i<mEvents.size(); i++){
            if(mEvents.get(i).getmId().equals(eventId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}