package com.example.third_assignment_template;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DeleteNoteActivity extends AppCompatActivity {

    Spinner dropdownSelect;
    private ArrayList<String> notesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        this.dropdownSelect = (Spinner) findViewById(R.id.dropdownSelect);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.notesList = new ArrayList<String>(sp.getStringSet("notes", new HashSet<String>()));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, notesList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.dropdownSelect.setAdapter(arrayAdapter);
    }

    public void onDeleteNoteClick(View view) {
        String selected = this.dropdownSelect.getSelectedItem().toString();

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor spEd = sp.edit();
        Set<String> oldSet = sp.getStringSet("notes", new HashSet<String>());
        Set<String> newStrSet = new HashSet<String>();
        oldSet.remove(selected);
        newStrSet.addAll(oldSet);

        spEd.putStringSet("notes",newStrSet);
        spEd.apply();

        finish();
    }
}
