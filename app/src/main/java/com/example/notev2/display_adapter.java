package com.example.notev2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class display_adapter extends RecyclerView.Adapter<display_adapter.NoteViewHolder> {
    private ArrayList<Note> noteList;
    private ItemClickListener itemClickListener;
    private DeleteClickListener deleteClickListener;

    public display_adapter(ArrayList<Note> noteList, ItemClickListener itemClickListener, DeleteClickListener deleteClickListener) {
        this.noteList = noteList;
        this.itemClickListener = itemClickListener;
        this.deleteClickListener = deleteClickListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.titleTextView.setText(note.getTitle());
        holder.contentTextView.setText(note.getDescription());

        holder.itemView.setOnClickListener(view -> {
            itemClickListener.onItemClick(noteList.get(position));
        });

        holder.delete.setOnClickListener(view -> {
            deleteClickListener.onDeleteClick(noteList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public interface ItemClickListener{
        void onItemClick(Note notes);
    }

    public interface DeleteClickListener{
        void onDeleteClick(Note note);
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView contentTextView;
        ImageButton delete;

        public NoteViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            contentTextView = itemView.findViewById(R.id.contentTextView);
            delete = itemView.findViewById(R.id.trashButton);
        }
    }
}
