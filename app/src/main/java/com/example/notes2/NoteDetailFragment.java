package com.example.notes2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteDetailFragment extends Fragment {

    public static final String KEY_CURRENT_NOTE = "KEY_CURRENT_NOTE";
    private Note note;
    private ActivityResultLauncher<Date> launcher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            note = (Note) getArguments().getSerializable(KEY_CURRENT_NOTE);
        }

        launcher = registerForActivityResult(new LoginResultContract(), new ActivityResultCallback<Date>() {
                    @Override
                    public void onActivityResult(Date result) {
                        if (result != null) {
                            note.setDateCreate(result);
                            showNoteInfo(note, getView());
                        }
                    }
                }
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (note != null) {
            TextView dateCreatedTV = view.findViewById(R.id.dateCreated);

            dateCreatedTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    launcher.launch(note.getDateCreate());
//                    getChildFragmentManager()
//                            .beginTransaction()
//                            .replace(R.id.changeDateCreateContainer, new ChangeDateCreateFragment())
//                            .addToBackStack("changeDateCreated")
//                            .commit();
                }
            });

            showNoteInfo(note, view);
        }
    }

    private void showNoteInfo(Note note, View view) {
        TextView nameNoteTV = view.findViewById(R.id.nameNote);
        TextView descNoteTV = view.findViewById(R.id.descriptionNote);
        TextView dateCreatedTV = view.findViewById(R.id.dateCreated);

        nameNoteTV.setText(note.getNameNote());
        descNoteTV.setText(note.getDesscriptionNote());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");

        dateCreatedTV.setText(simpleDateFormat.format(note.getDateCreate()));
    }

    public static class LoginResultContract extends ActivityResultContract<Date, Date> {

        @NonNull
        @Override
        public Intent createIntent(@NonNull Context context, Date input) {
            Intent intent = new Intent(context, ChangeDateCreateActivity.class);
            intent.putExtra(ChangeDateCreateActivity.KEY_RESULT_CHANGE_DATE_CREATED, input);
            return intent;
        }

        @Override
        public Date parseResult(int resultCode, @Nullable Intent intent) {

            if (resultCode == Activity.RESULT_OK && intent != null) {
                Date dateCreated = (Date) intent.getSerializableExtra(ChangeDateCreateActivity.KEY_RESULT_CHANGE_DATE_CREATED);
                return dateCreated;
            }
            return null;
        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_CURRENT_NOTE, note);
    }
}