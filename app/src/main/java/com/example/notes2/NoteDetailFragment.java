package com.example.notes2;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.text.SimpleDateFormat;

public class NoteDetailFragment extends Fragment {

    public static final String KEY_ARG = "KEY_ARG";
    private Note note;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            note = (Note) getArguments().getSerializable(KEY_ARG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (note != null) {
            TextView nameNoteTV = view.findViewById(R.id.nameNote);
            TextView descNoteTV = view.findViewById(R.id.descriptionNote);
            TextView dateCreatedTV = view.findViewById(R.id.dateCreated);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");

            nameNoteTV.setText(note.getNameNote());
            descNoteTV.setText(note.getDesscriptionNote());
            dateCreatedTV.setText(simpleDateFormat.format(note.getDateCreate()));
        }
    }

}