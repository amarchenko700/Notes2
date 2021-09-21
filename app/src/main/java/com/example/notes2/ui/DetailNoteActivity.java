package com.example.notes2.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.notes2.R;
import com.example.notes2.fragments.NoteDetailFragment;

public class DetailNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_note);

        boolean isLandscape = getResources().getBoolean(R.bool.isLandscape);
        if(isLandscape){
            finish();
            return;
        }

        if(savedInstanceState==null){
            NoteDetailFragment noteDetailFragment = new NoteDetailFragment();
            Bundle bundle = getIntent().getExtras();
            noteDetailFragment.setArguments(bundle);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, noteDetailFragment)
                    .commit();
        }
    }
}