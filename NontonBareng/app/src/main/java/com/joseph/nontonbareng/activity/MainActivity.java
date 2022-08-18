package com.joseph.nontonbareng.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import com.joseph.nontonbareng.R;
import com.joseph.nontonbareng.adapter.MovieAdapter;
import com.joseph.nontonbareng.config.DBHelper;
import com.joseph.nontonbareng.model.LoginUser;
import com.joseph.nontonbareng.model.Movies;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Movies> MovieList;
    private static String URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=8fbd2de25dfd709013a804c4fbcf41c2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Layout untuk menempatkan data yang di ambil
        MovieList = new ArrayList<>();
        recyclerView = findViewById(R.id.rvMovie);

        GetAPI getAPI = new GetAPI();
        getAPI.execute();
    }

    public class GetAPI extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            String current = "";

            try {
                java.net.URL url;
                HttpURLConnection urlConnection = null;

                try {
                    url = new URL(URL);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream is = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);

                    int data = isr.read();
                    while(data != -1) {
                        current += (char) data;
                        data = isr.read();
                    }

                    return current;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return current;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    Movies parcel = new Movies();
                    parcel.setTitle(jsonObject1.getString("title"));
                    parcel.setReleaseDate(jsonObject1.getString("release_date"));
                    parcel.setRating(jsonObject1.getString("vote_average"));
                    parcel.setImgPoster(jsonObject1.getString("poster_path"));

                    parcel.setRatingViewer(jsonObject1.getString("vote_count"));
                    parcel.setViewer(jsonObject1.getString("popularity"));
                    parcel.setOverview(jsonObject1.getString("overview"));
                    parcel.setImgDetailPoster(jsonObject1.getString("poster_path"));
                    parcel.setImgDetailBackdrop(jsonObject1.getString("backdrop_path"));

                    MovieList.add(parcel);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            InsertIntoRecyclerView(MovieList);
        }
    }

    private void InsertIntoRecyclerView(List<Movies> movieList) {
        MovieAdapter adapter = new MovieAdapter(this, movieList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.languages:
                Intent intentL = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(intentL);
                return true;
            case R.id.about:
                Intent intentA = new Intent(this, AboutActivity.class);
                startActivity(intentA);
                return true;
            case R.id.exit:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}