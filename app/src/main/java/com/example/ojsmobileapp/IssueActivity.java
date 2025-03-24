package com.example.ojsmobileapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

public class IssueActivity extends AppCompatActivity {

    private PlaceHolderView placeHolderView;
    private int journalId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue);

        placeHolderView = findViewById(R.id.placeHolderView);
        journalId = getIntent().getIntExtra("journal_id", -1);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://revistas.uteq.edu.ec/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getIssues(journalId).enqueue(new Callback<List<Issue>>() {
            @Override
            public void onResponse(Call<List<Issue>> call, Response<List<Issue>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    for (Issue issue : response.body()) {
                        placeHolderView.addView(new IssueItem(IssueActivity.this, issue));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Issue>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    interface ApiService {
        @GET("issues.php")
        Call<List<Issue>> getIssues(@Query("j_id") int journalId);
    }

    class Issue {
        int issue_id;
        String volume;
        String number;
        String year;
    }

    @Layout(R.layout.item_issue)
    class IssueItem {
        private Context context;
        private Issue issue;

        @View(R.id.txtVolume)
        private TextView txtVolume;

        @View(R.id.txtYear)
        private TextView txtYear;

        public IssueItem(Context context, Issue issue) {
            this.context = context;
            this.issue = issue;
        }

        @Resolve
        private void onResolved() {
            txtVolume.setText("Volumen " + issue.volume + " - Número " + issue.number);
            txtYear.setText("Año: " + issue.year);
        }

        @Click(R.id.itemView)
        private void onItemClick() {
            Intent intent = new Intent(context, ArticleActivity.class);
            intent.putExtra("issue_id", issue.issue_id);
            context.startActivity(intent);
        }
    }
}
