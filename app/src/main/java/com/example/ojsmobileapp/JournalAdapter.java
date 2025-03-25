package com.example.ojsmobileapp;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class JournalAdapter extends RecyclerView.Adapter<JournalAdapter.ViewHolder> {

    private Context context;
    private List<Journal> journalList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Journal journal);
    }

    public JournalAdapter(Context context, List<Journal> journalList, OnItemClickListener listener) {
        this.context = context;
        this.journalList = journalList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_journal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Journal journal = journalList.get(position);
        holder.txtName.setText(journal.name);

        // Aplicamos Html.fromHtml() para mostrar la descripción con formato
        holder.txtDescription.setText(Html.fromHtml(journal.description, Html.FROM_HTML_MODE_LEGACY));

        Glide.with(context)
                .load(journal.portada)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(holder.imgCover);

        holder.itemView.setOnClickListener(v -> listener.onItemClick(journal));
    }

    @Override
    public int getItemCount() {
        return journalList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCover;
        TextView txtName, txtDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCover = itemView.findViewById(R.id.imgCover);
            txtName = itemView.findViewById(R.id.txtName);
            txtDescription = itemView.findViewById(R.id.txtDescription);
        }
    }
}
