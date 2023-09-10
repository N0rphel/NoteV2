package com.example.notev2;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Random;

public class add_notes extends Fragment {
    private ArrayList<Note> noteList;
    private int id = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_notes, container, false);

        // Initialize UI components
        EditText editTextTitle = view.findViewById(R.id.noteTitle);
        EditText editTextContent = view.findViewById(R.id.noteContent);
        Button saveNoteBtn = view.findViewById(R.id.saveButton);


        // Get the ArrayList of notes
        noteList = ((MainActivity) requireActivity()).getNoteList();

        saveNoteBtn.setOnClickListener(v -> {
            String title = editTextTitle.getText().toString();
            String description = editTextContent.getText().toString();
            int id = generateRand();

            if (!title.isEmpty() && !description.isEmpty()) {
                // Create a new Note and add it to the list
                Note newNote = new Note(title, description, id);
                noteList.add(newNote);
                Toast.makeText(getContext(), "Note saved successfully", Toast.LENGTH_SHORT).show();

                // Clear input fields
                editTextTitle.setText("");
                editTextContent.setText("");

                // Switch back to DisplayNotesFragment
                FragmentTransaction transaction = requireFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout, new display_notes());
                transaction.addToBackStack(null);
                transaction.commit();

                // Notify the RecyclerView adapter of data change
                ((MainActivity) requireActivity()).notifyDataChanged();
            }
        });

        return view;
    }

    public int generateRand(){
        double rand = Math.random();

        return (int)(rand * 100);
    }
}
