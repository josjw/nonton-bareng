package com.joseph.nontonbareng.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.joseph.nontonbareng.R;
import com.joseph.nontonbareng.model.Movies;

public class DetailMovieActivity extends AppCompatActivity {
    ImageView imgDetailPoster, imgDetailBackdrop;
    TextView tvDetailTitle, tvDetailReleaseDate, tvDetailRating, tvDetailRatingViewer, tvDetailViewer, tvOverview;
    String a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        imgDetailPoster = findViewById(R.id.imgDetailPoster);
        imgDetailBackdrop = findViewById(R.id.imgDetailBackdrop);
        tvDetailTitle = findViewById(R.id.tvDetailTitle);
        tvDetailReleaseDate = findViewById(R.id.tvDetailReleaseDate);
        tvDetailRating = findViewById(R.id.tvDetailRating);
        tvDetailRatingViewer = findViewById(R.id.tvDetailRatingViewer);
        tvDetailViewer = findViewById(R.id.tvDetailViewer);
        tvOverview = findViewById(R.id.tvOverview);

        Movies movies = getIntent().getParcelableExtra("MOVIES");

        Glide.with(DetailMovieActivity.this)
                .load("https://image.tmdb.org/t/p/w500/" + movies.getImgDetailPoster())
                .into(imgDetailPoster);
        Glide.with(DetailMovieActivity.this)
                .load("https://image.tmdb.org/t/p/w500/" + movies.getImgDetailBackdrop())
                .into(imgDetailBackdrop);
        tvDetailTitle.setText(movies.getTitle());
        tvDetailReleaseDate.setText(movies.getReleaseDate());
        tvDetailRating.setText(movies.getRating());
        tvDetailRatingViewer.setText(movies.getRatingViewer());
        tvDetailViewer.setText(movies.getViewer());
        tvOverview.setText(movies.getOverview());

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(movies.getTitle());
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}