package com.example.notes2;

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

import java.util.List;

public class NotesListFragment extends Fragment {

    private boolean isLandscape;

    public static NotesListFragment newInstance(Note note) {
        NotesListFragment fragment = new NotesListFragment();
        //Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isLandscape = getResources().getBoolean(R.bool.isLandscape);
        if (getArguments() != null) {
            //    mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListNotes(view);
    }

    private void initListNotes(View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        // Оставляю для себя как пример на будущее
        //String[] namesNotes = getResources().getStringArray(R.array.namesNotes);
        List<Note> namesNotes = new NotesRepository().getNotes();
        Context context = getContext();
        for (Note note : namesNotes) {
            if (context != null) {
                TextView textView = new TextView(context);
                textView.setText(note.getNameNote());
                textView.setTextSize(30);
                textView.setPadding(0, 20, 0, 20);
                linearLayout.addView(textView);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showNoteDetails(note);
                    }
                });
            }
        }
    }

    private void showNoteDetails(Note note) {
        if(isLandscape){
            showNoteDetailsLand(note);
        }else {
            showNoteDetailsPort(note);
        }
    }

    private void showNoteDetailsLand(Note note){
        NoteDetailFragment noteDetailFragment = NoteDetailFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putSerializable(NoteDetailFragment.KEY_ARG, note);
        noteDetailFragment.setArguments(bundle);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, noteDetailFragment)
                .commit();
    }

    private void showNoteDetailsPort(Note note){
        Context context = getActivity();
        if (context != null) {
            Intent intent = new Intent(context, DetailNoteActivity.class);
            intent.putExtra(NoteDetailFragment.KEY_ARG, note);
            startActivity(intent);
        }
    }


}