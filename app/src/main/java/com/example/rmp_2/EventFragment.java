package com.example.rmp_2;


import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.UUID;

public class EventFragment extends Fragment {
    private static final String ARG_EVENT_ID = "rmp_2.Event_id";
    private Event mEvent;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    public static EventFragment newInstance(UUID EventId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_EVENT_ID, EventId);
        EventFragment fragment = new EventFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID EventId = (UUID) getActivity().getIntent().getSerializableExtra(EventActivity.EXTRA_EVENT_ID);
        mEvent = EventLab.get(getActivity()).getEvent(EventId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_event, container, false);
        mTitleField = (EditText) v.findViewById(R.id.event_title);
        mTitleField.setText(mEvent.getmTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence c, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence c, int start, int before, int count) {
                mEvent.setmTitle(c.toString());
            }

            @Override
            public void afterTextChanged(Editable c) {

            }
        });

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E, d MMM yyyy");
        mDateButton = (Button) v.findViewById(R.id.event_date);
        mDateButton.setText(simpleDateFormat.format(mEvent.getmDate()));
        mDateButton.setEnabled(false);

        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.event_solved);
        mSolvedCheckBox.setChecked(mEvent.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            mEvent.setSolved(isChecked);
        });

        return v;

    }

    public void returnResult() {
        getActivity().setResult(Activity.RESULT_OK, null);
    }

}

