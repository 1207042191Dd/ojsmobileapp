package com.example.ojsmobileapp;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.View;

public class IssueItem {
    private Context context;
    private Issue issue;

    @View(R.id.issueTitle)
    private TextView issueTitle;

    public IssueItem(Context context, Issue issue) {
        this.context = context;
        this.issue = issue;
    }

    @Resolve
    private void onResolved() {
        issueTitle.setText("Vol. " + issue.volume + " No. " + issue.number + " (" + issue.year + ")");
    }

    @Click(R.id.issueTitle)
    private void onItemClick() {
        Intent intent = new Intent(context, ArticleActivity.class);
        intent.putExtra("issue_id", issue.issue_id);
        context.startActivity(intent);
    }
}

