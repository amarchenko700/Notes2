package com.example.notes2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;

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
            noteDetailFragment.setArguments(getIntent().getExtras());
            //Bundle bundle = getIntent().getExtras();
            //Note note = (Note)bundle.getSerializable(NoteDetailFragment.KEY_ARG);
            noteDetailFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, noteDetailFragment)
                    .commit();
        }
    }
}