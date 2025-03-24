package com.example.ojsmobileapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.*;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {

    private PlaceHolderView placeHolderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        placeHolderView = findViewById(R.id.placeHolderView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://revistas.uteq.edu.ec/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getJournals().enqueue(new Callback<List<Journal>>() {
            @Override
            public void onResponse(Call<List<Journal>> call, Response<List<Journal>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    for (Journal journal : response.body()) {
                        placeHolderView.addView(new JournalItem(MainActivity.this, journal));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Journal>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    interface ApiService {
        @GET("journals.php")
        Call<List<Journal>> getJournals();
    }

    class Journal {
        int journal_id;
        String name;
        String description;
        String cover;
    }

    @Layout(R.layout.item_journal)
    class JournalItem {
        private Context context;
        private Journal journal;

        @View(R.id.txtName)
        private TextView txtName;

        @View(R.id.txtDescription)
        private TextView txtDescription;

        @View(R.id.imgCover)
        private ImageView imgCover;

        public JournalItem(Context context, Journal journal) {
            this.context = context;
            this.journal = journal;
        }

        @Resolve
        private void onResolved() {
            txtName.setText(journal.name);
            txtDescription.setText(journal.description);
            Glide.with(context).load(journal.cover).into(imgCover);
        }

        @Click(R.id.itemView)
        private void onItemClick() {
            Intent intent = new Intent(context, IssueActivity.class);
            intent.putExtra("journal_id", journal.journal_id);
            context.startActivity(intent);
        }
    }
}
