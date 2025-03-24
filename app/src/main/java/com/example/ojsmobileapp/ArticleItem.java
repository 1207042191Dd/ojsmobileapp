package com.example.ojsmobileapp;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.View;

public class ArticleItem {
    private Context context;
    private Article article;

    @View(R.id.articleTitle)
    private TextView articleTitle;

    @View(R.id.btnHtml)
    private Button btnHtml;

    public ArticleItem(Context context, Article article) {
        this.context = context;
        this.article = article;
    }

    @Resolve
    private void onResolved() {
        articleTitle.setText(article.title);
    }

    @Click(R.id.btnHtml)
    private void onHtmlClick() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(article.doi));
        context.startActivity(intent);
    }
}
