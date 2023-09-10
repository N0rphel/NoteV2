package com.example.notev2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Note> noteList = new ArrayList<>();
    private display_adapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the RecyclerView Adapter
        noteAdapter = new display_adapter(noteList, new display_adapter.ItemClickListener() {
            @Override
            public void onItemClick(Note notes) {

            }
        }, new display_adapter.DeleteClickListener() {
            @Override
            public void onDeleteClick(Note note) {

            }
        });

        // Load the DisplayNotesFragment by default
        loadFragment(new display_notes());

        // Handle the "+" button click to switch to AddNotesFragment
        findViewById(R.id.addButton).setOnClickListener(view -> loadFragment(new add_notes()));
    }

    // Load a fragment into the FrameLayout
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.addToBackStack(null); // Allow back navigation
        transaction.commit();
    }

    // Get the ArrayList of notes
    public ArrayList<Note> getNoteList() {
        return noteList;
    }

    // Notify the RecyclerView adapter when data changes
    public void notifyDataChanged() {
        if (noteAdapter != null) {
            noteAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
        // Check if the current fragment is not already the DisplayNotesFragment
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frameLayout);
        if (!(currentFragment instanceof display_notes)) {
            // Replace the current fragment with the DisplayNotesFragment
            loadFragment(new display_notes());
        } else {
            // If the current fragment is already the DisplayNotesFragment, perform the default back action
            super.onBackPressed();
        }
    }
}
