package com.example.notev2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class edit_notes extends Fragment {
    private View view;
    private Note selectedNote;
    private ArrayList<Note> notes;
    private EditText title, content;
    Button editSave;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_edit_notes, container, false);

        title = view.findViewById(R.id.editTitle);
        content = view.findViewById(R.id.editContent);
        editSave = view.findViewById(R.id.editSave);

        // Retrieve the selected note from the arguments bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            selectedNote = bundle.getParcelable("SelectedNote");
            if (selectedNote != null) {
                // Set the title and content EditText fields with the selected note's data
                title.setText(selectedNote.getTitle());
                content.setText(selectedNote.getDescription());
                //Toast.makeText(getContext(), String.valueOf(selectedNote.getId()), Toast.LENGTH_SHORT).show();
            }
        }

        editSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editedTitle = title.getText().toString();
                String editedContent = content.getText().toString();

                notes = ((MainActivity) requireActivity()).getNoteList();

                int pos = findNoteByPosition(selectedNote);

                if(pos != -1){
                    selectedNote.setTitle(editedTitle);
                    selectedNote.setDescription(editedContent);

                    notes.set(pos, selectedNote);

                }
            }
        });

        return view;
    }
    private int findNoteByPosition(Note note){
        int targetId = note.getId();
        for(int i = 0; i < notes.size(); i++){
            if(notes.get(i).getId() == targetId){
                return i;
            }
        }
        return -1;
    }
}
