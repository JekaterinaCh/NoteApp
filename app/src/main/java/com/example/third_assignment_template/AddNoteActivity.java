package com.example.third_assignment_template;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class AddNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
    }

    public void onAddNoteClick(View view) {
        EditText txtNoteTitle = findViewById(R.id.txtNoteTitle);
        EditText txtNoteContent = findViewById(R.id.txtNoteContent);
        if(txtNoteTitle.getText().toString().length() > 0 && txtNoteContent.getText().toString().length() > 0) {
        String concatenated = txtNoteTitle.getText().toString() + ": "+ txtNoteContent.getText().toString();

        //https://stackoverflow.com/questions/14034803/misbehavior-when-trying-to-store-a-string-set-using-sharedpreferences
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor spEd = sp.edit();
        Set<String> oldSet = sp.getStringSet("notes", new HashSet<String>());
        Set<String> newStrSet = new HashSet<String>();
        newStrSet.add(concatenated);
        newStrSet.addAll(oldSet);

        spEd.putStringSet("notes",newStrSet);
        spEd.apply();

        finish();
        }
        else{
            Toast.makeText(AddNoteActivity.this, "Fill empty fields!", Toast.LENGTH_LONG).show();
        }
    }
}
