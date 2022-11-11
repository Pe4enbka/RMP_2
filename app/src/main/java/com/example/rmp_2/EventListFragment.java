package com.example.rmp_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventListFragment extends Fragment {
    private static final int REQUEST_EVENT = 1;

    private RecyclerView mEventRecyclerView;
    private EventAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_event_list, container, false);
        mEventRecyclerView = (RecyclerView) v.findViewById(R.id.event_recycler_view);
        mEventRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    public void updateUI() {
        EventLab eventLab = EventLab.get(getActivity());
        List<Event> events = eventLab.getEvents();
        if (mAdapter == null) {
            mAdapter = new EventAdapter(events);
            mEventRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class EventHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Event mEvent;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;

        public EventHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_event_title_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_event_date_view);
            mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_event_checkBox);
        }

        public void bindEvent(Event event) {
            mEvent = event;
            mTitleTextView.setText(mEvent.getmTitle());
            mDateTextView.setText(mEvent.getmDate().toString());
            mSolvedCheckBox.setChecked(mEvent.isSolved());
        }

        @Override
        public void onClick(View v) {
            Intent intent = EventActivity.newIntent(getActivity(), mEvent.getmId());
            startActivityForResult(intent, REQUEST_EVENT);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_EVENT) {
            // Обработка результата
        }
    }

    private class EventAdapter extends RecyclerView.Adapter<EventHolder> {
        private List<Event> mEvents;

        public EventAdapter(List<Event> events) {
            mEvents = events;
        }

        @Override
        public EventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View v = layoutInflater.inflate(R.layout.list_item_event, parent, false);
            return new EventHolder(v);
        }

        @Override
        public void onBindViewHolder(EventHolder holder, int position) {
            Event event = mEvents.get(position);
            holder.bindEvent(event);
        }

        @Override
        public int getItemCount() {
            return mEvents.size();
        }

    }

}
