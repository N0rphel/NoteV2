package com.example.notev2;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class display_notes extends Fragment {
    private ArrayList<Note> noteList;
    private RecyclerView recyclerView;
    private display_adapter noteAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display_notes, container, false);

        // Initialize RecyclerView and set its adapter
        noteList = ((MainActivity) requireActivity()).getNoteList();
        recyclerView = view.findViewById(R.id.recyclerView);
        noteAdapter = new display_adapter(noteList, new display_adapter.ItemClickListener() {
            @Override
            public void onItemClick(Note note) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("SelectedNote", note);

                edit_notes ed = new edit_notes();
                ed.setArguments(bundle);

                //Toast.makeText(getContext(), note.getId(), Toast.LENGTH_SHORT).show();

                 FragmentTransaction transaction = requireFragmentManager().beginTransaction();
                 transaction.replace(R.id.frameLayout,ed);
                 transaction.addToBackStack(null);
                 transaction.commit();
            }
        }, new display_adapter.DeleteClickListener(){

            @Override
            public void onDeleteClick(Note note) {
                Toast.makeText(getContext(),"clicked",Toast.LENGTH_SHORT).show();
                int pos = findNoteByPosition(note);
                if(pos != -1){
                    noteList.remove(pos);
                    noteAdapter.notifyDataSetChanged();
                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(noteAdapter);

        return view;
    }
    private int findNoteByPosition(Note note){
        int targetId = note.getId();
        for(int i = 0; i < noteList.size(); i++){
            if(noteList.get(i).getId() == targetId){
                return i;
            }
        }
        return -1;
    }
}
