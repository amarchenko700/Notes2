package com.example.notes2.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.notes2.ui.DetailNoteActivity;
import com.example.notes2.domain.Note;
import com.example.notes2.domain.NotesRepository;
import com.example.notes2.R;

import java.io.Serializable;
import java.util.List;

public class NotesListFragment extends Fragment {

    private static final String KEY_NOTES = "KEY_NOTES";
    private boolean isLandscape;
    private Note note;
    private List<Note> notes;

    public static NotesListFragment newInstance(Note note) {
        NotesListFragment fragment = new NotesListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isLandscape = getResources().getBoolean(R.bool.isLandscape);
        if (getArguments() != null) {

        }
        if(savedInstanceState == null){
            notes = new NotesRepository().getNotes();
        }else {
            notes = (List<Note>) savedInstanceState.getSerializable(KEY_NOTES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            initListNotes(view);
        }else {
            note = (Note)savedInstanceState.getSerializable(NoteDetailFragment.KEY_CURRENT_NOTE);
            initListNotes(view);
            showNoteDetails(note);
        }
    }

    private void initListNotes(View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        // Оставляю для себя как пример на будущее
        //String[] namesNotes = getResources().getStringArray(R.array.namesNotes);
        Context context = getContext();
        for (Note currentNote : notes) {
            if (context != null) {
                TextView textView = new TextView(context);
                textView.setText(currentNote.getNameNote());
                textView.setTextSize(30);
                textView.setPadding(0, 20, 0, 20);
                linearLayout.addView(textView);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        note = currentNote;
                        showNoteDetails(currentNote);
                    }
                });
            }
        }
    }

    private void showNoteDetails(Note note) {
        if (isLandscape) {
            showNoteDetailsLand(note);
        } else {
            showNoteDetailsPort(note);
        }
    }

    private void showNoteDetailsLand(Note note) {
        //NoteDetailFragment noteDetailFragment = NoteDetailFragment.newInstance();
        NoteDetailFragment noteDetailFragment = new NoteDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(NoteDetailFragment.KEY_CURRENT_NOTE, note);
        noteDetailFragment.setArguments(bundle);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, noteDetailFragment)
                .commit();
    }

    private void showNoteDetailsPort(Note note) {
        Context context = getActivity();
        if (context != null) {
            Intent intent = new Intent(context, DetailNoteActivity.class);
            intent.putExtra(NoteDetailFragment.KEY_CURRENT_NOTE, note);
            startActivity(intent);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(NoteDetailFragment.KEY_CURRENT_NOTE, note);
        outState.putSerializable(KEY_NOTES, (Serializable) notes);
    }

    @Override
    public void onResume() {
        super.onResume();
        //getArguments()
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}