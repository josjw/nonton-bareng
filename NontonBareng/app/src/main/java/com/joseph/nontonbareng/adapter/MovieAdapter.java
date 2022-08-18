package com.joseph.nontonbareng.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.joseph.nontonbareng.R;
import com.joseph.nontonbareng.activity.DetailMovieActivity;
import com.joseph.nontonbareng.model.Movies;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    private Context context;
    private List<Movies> MovieList;

    public MovieAdapter(Context context, List<Movies> MovieList) {
        this.context = context;
        this.MovieList = MovieList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_movie,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Movies movies = MovieList.get(position);

        Glide.with(context).load("https://image.tmdb.org/t/p/w500/" + movies.getImgPoster()).into(holder.imgPoster);
        holder.tvTitle.setText(movies.getTitle());
        holder.tvReleaseDate.setText(movies.getReleaseDate());
        holder.tvRating.setText(movies.getRating());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailMovieActivity.class);
                intent.putExtra("MOVIES", movies);
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return MovieList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPoster;
        TextView tvTitle, tvReleaseDate, tvRating;

        ImageView imgDetailPoster, imgDetailBackdrop;
        TextView tvDetailTitle, tvDetailReleaseDate, tvDetailRating, tvDetailRatingViewer, tvDetailViewer, tvOverview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPoster = itemView.findViewById(R.id.imgPoster);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvReleaseDate = itemView.findViewById(R.id.tvReleaseDate);
            tvRating = itemView.findViewById(R.id.tvRating);

            imgDetailPoster = itemView.findViewById(R.id.imgDetailPoster);
            imgDetailBackdrop = itemView.findViewById(R.id.imgDetailBackdrop);
            tvDetailTitle = itemView.findViewById(R.id.tvDetailTitle);
            tvDetailReleaseDate = itemView.findViewById(R.id.tvDetailReleaseDate);
            tvDetailRating = itemView.findViewById(R.id.tvDetailRating);
            tvDetailRatingViewer = itemView.findViewById(R.id.tvDetailRatingViewer);
            tvDetailViewer = itemView.findViewById(R.id.tvDetailViewer);
            tvOverview = itemView.findViewById(R.id.tvOverview);
        }
    }
}
