package com.example.notes.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.notes.R;
import com.example.notes.model.AppDatabase;
import com.example.notes.model.Note;


public class CalendarFragment extends Fragment {

    private CalendarView calendarView;
    private Note noteForEdit;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CalendarFragment() {
        // Required empty public constructor
    }


    public static CalendarFragment newInstance(String param1, String param2) {
        CalendarFragment fragment = new CalendarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        Bundle bundle = this.getArguments();
        if (bundle != null){
            noteForEdit = new Note();
            noteForEdit.noteName = bundle.getString("name");
            noteForEdit.noteBody = bundle.getString("body");
            noteForEdit.noteDate = bundle.getString("date");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);
//        Button button = rootView.findViewById(R.id.button_save_date);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        calendarView = view.findViewById(R.id.calendarView);
        AppDatabase db = AppDatabase.getDbInstance(requireActivity().getApplicationContext());

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull final CalendarView view, final int year, final int month,
                                            final int dayOfMonth) {

                String date = dayOfMonth + "-" + month + "-" + year;
                Toast.makeText(getActivity(), "Date changed to " + date, Toast.LENGTH_SHORT).show();
                noteForEdit.noteDate = date;
                db.noteDao().insertNote(noteForEdit);
                getFragmentManager().popBackStack();
                requireActivity().recreate();
            }
        });
    }


}