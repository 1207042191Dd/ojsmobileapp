package com.example.ojsmobileapp;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.View;
import com.squareup.picasso.Picasso;

public class JournalItem {
    private Context context;
    private Journal journal;

    @View(R.id.journalCover)
    private ImageView journalCover;

    @View(R.id.journalTitle)
    private TextView journalTitle;

    public JournalItem(Context context, Journal journal) {
        this.context = context;
        this.journal = journal;
    }

    @Resolve
    private void onResolved() {
        journalTitle.setText(journal.name);
        Picasso.get().load(journal.cover).into(journalCover);
    }

    @Click(R.id.journalTitle)
    private void onItemClick() {
        Intent intent = new Intent(context, IssueActivity.class);
        intent.putExtra("journal_id", journal.journal_id);
        context.startActivity(intent);
    }
}