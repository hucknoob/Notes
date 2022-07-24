package com.example.notes.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.R;
import com.example.notes.model.Note;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.MyViewHolder> {

    private Context context;
    private List<Note> noteList;
    private HandleNoteClick clickListener;

    public NoteListAdapter(Context context, HandleNoteClick clickListener){
        this.context = context;
        this.clickListener = clickListener;
    }

    public void setNoteList(List<Note> noteList){
        this.noteList = noteList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);
       return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteListAdapter.MyViewHolder holder, int position) {

        holder.tvName.setText(this.noteList.get(position).noteName);
        holder.tvDate.setText(this.noteList.get(position).noteDate);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.itemClick(noteList.get(position));
            }
        });

        holder.removeNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.removeItem(noteList.get(position));
            }
        });

        holder.editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.editItem(noteList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        if(noteList == null || noteList.size() == 0){
            return 0;
        } else {
            return this.noteList.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvDate;
        ImageView removeNote;
        ImageView editDate;

        public MyViewHolder(View view){
            super(view);
            tvName = view.findViewById(R.id.tvNoteName);
            tvDate = view.findViewById(R.id.tvNoteDate);
            removeNote = view.findViewById(R.id.removeNote);
            editDate = view.findViewById(R.id.editNote);
        }
    }

    public interface HandleNoteClick{
        void itemClick(Note note);
        void removeItem(Note note);
        void editItem(Note note);
    }
}
