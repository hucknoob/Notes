package com.example.notes.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.notes.R;
import com.example.notes.model.AppDatabase;
import com.example.notes.model.Note;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NoteFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Note note;


    public NoteFragment() {
        // Required empty public constructor
    }


    public static NoteFragment newInstance(String param1, String param2) {
        NoteFragment fragment = new NoteFragment();
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


        }


    }

    private EditText editTextName;
    private EditText editTextBody;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_note, container, false);
        editTextName = rootView.findViewById(R.id.edit_text_name);
        editTextBody = rootView.findViewById(R.id.edit_text_body);
        rootView.findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNote();
            }
        });

        Bundle bundle = this.getArguments();
        if (bundle != null){
            note = new Note();
            note.noteName = bundle.getString("name");
            note.noteBody = bundle.getString("body");
            note.noteDate = bundle.getString("date");

            editTextName.setText(note.noteName);
            editTextBody.setText(note.noteBody);
        }

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void saveNote() {
        AppDatabase db = AppDatabase.getDbInstance(requireActivity().getApplicationContext());

        Note note = new Note();
        note.noteName = editTextName.getText().toString();
        note.noteBody = editTextBody.getText().toString();
        note.noteDate = getDate();
        db.noteDao().insertNote(note);
        getFragmentManager().popBackStack();
        requireActivity().recreate();
//        requireActivity().getSupportFragmentManager().popBackStack();

    }

    private String getDate(){
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        return date;
    }
}