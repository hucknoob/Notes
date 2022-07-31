package com.example.notes.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.R;
import com.example.notes.model.AppDatabase;
import com.example.notes.model.Note;

import java.util.List;

public class MainFragment extends Fragment implements NoteListAdapter.HandleNoteClick {

    private NoteListAdapter noteListAdapter;
    private View rootView;
    private RecyclerView recyclerView;
    private Note noteForEdit;
    private AppDatabase appDatabase;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        Button button = rootView.findViewById(R.id.add_note_button);

        initRecyclerView();
        loadNoteList();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPortraitNoteFragment();
            }
        });

        return rootView;
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        noteListAdapter = new NoteListAdapter(requireContext(), this);
        recyclerView.setAdapter(noteListAdapter);
    }

    private void loadNoteList() {
        appDatabase = AppDatabase.getDbInstance(requireContext());
        List<Note> noteList = appDatabase.noteDao().getAllUsers();
        noteListAdapter.setNoteList(noteList);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    private void showPortraitNoteFragment() {

        NoteFragment fragment = NoteFragment.newInstance("", "");


        if (isLandscape()){
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container_note, fragment)
                    .addToBackStack("")
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        } else {
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .addToBackStack("")
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        }

    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    @Override
    public void itemClick(Note note) {
        Bundle bundle = new Bundle();
        bundle.putString("name", note.noteName);
        bundle.putString("body", note.noteBody);
        bundle.putString("date", note.noteDate);

//        CheckFragment checkFragment = new CheckFragment();
//        checkFragment.setArguments(bundle);

        NoteFragment noteFragment = new NoteFragment();
        noteFragment.setArguments(bundle);

        if (isLandscape()){
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack("")
                    .replace(R.id.fragment_container_note, noteFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        } else {
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack("")
                    .add(R.id.fragment_container, noteFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        }


    }

    @Override
    public void removeItem(Note note) {
        appDatabase.noteDao().delete(note);
        requireActivity().recreate();
    }

    @Override
    public void editItem(Note note) {
//        this.noteForEdit = note;

        Bundle bundle = new Bundle();
        bundle.putString("name", note.noteName);
        bundle.putString("body", note.noteBody);
        bundle.putString("date", note.noteDate);

        CalendarFragment calendarFragment = new CalendarFragment();
        calendarFragment.setArguments(bundle);

        appDatabase.noteDao().delete(note);

        if(isLandscape()){
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack("")
                    .replace(R.id.fragment_container_note, calendarFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        } else {
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack("")
                    .add(R.id.fragment_container, calendarFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        }

    }

}