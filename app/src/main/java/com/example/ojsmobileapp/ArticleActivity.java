package com.example.ojsmobileapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.mindorks.placeholderview.*;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ArticleActivity extends AppCompatActivity {

    private PlaceHolderView placeHolderView;
    private int issueId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        placeHolderView = findViewById(R.id.placeHolderView);
        issueId = getIntent().getIntExtra("issue_id", -1);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://revistas.uteq.edu.ec/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getArticles(issueId).enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    for (Article article : response.body()) {
                        placeHolderView.addView(new ArticleItem(ArticleActivity.this, article));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    interface ApiService {
        @GET("pubs.php")
        Call<List<Article>> getArticles(@Query("i_id") int issueId);
    }

    class Article {
        int article_id;
        String title;
        String doi;
        String pdf;
        String html;
    }

    @Layout(R.layout.item_article)
    class ArticleItem {
        private Context context;
        private Article article;

        @View(R.id.txtTitle)
        private TextView txtTitle;

        @View(R.id.btnHtml)
        private Button btnHtml;

        @View(R.id.btnPdf)
        private Button btnPdf;

        public ArticleItem(Context context, Article article) {
            this.context = context;
            this.article = article;
        }

        @Resolve
        private void onResolved() {
            txtTitle.setText(article.title);
        }

        @Click(R.id.btnHtml)
        private void onHtmlClick() {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.html));
            context.startActivity(intent);
        }

        @Click(R.id.btnPdf)
        private void onPdfClick() {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.pdf));
            context.startActivity(intent);
        }
    }
}
