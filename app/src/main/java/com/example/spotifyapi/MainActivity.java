package com.example.spotifyapi;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import Connectors.Artist;
import Connectors.SearchService;
import Connectors.Song;
import Connectors.SongService;
import Connectors.VolleyCallBack;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences msharedPreferences;

    private Button addBtn2;
    private EditText searchField;


    private SongService songService;
    private ArrayList<Song> recentlyPlayedTracks;
    private ArrayList<Artist> searchResults;
    private SearchService searchService;

    public String q = "";

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        msharedPreferences = getSharedPreferences("SPOTIFY",0);

        String AUTH_TOKEN = msharedPreferences.getString("token", "");
        String USERID = msharedPreferences.getString("userid", "");

        textView = findViewById(R.id.tv_welcome);
        textView.setText(getString(R.string.Text_Welcome)+" "+ USERID);

        searchField = (EditText)findViewById(R.id.SearchText);


        addBtn2 = (Button) findViewById(R.id.add2);

        SharedPreferences sharedPreferences = this.getSharedPreferences("SPOTIFY", 0);

        addBtn2.setOnClickListener(add2Listener);
    }


    private final View.OnClickListener add2Listener = v -> {
        if(searchField.getText().length()>0){
            searchService = new SearchService(getApplicationContext(),searchField.getText().toString());
            searchService.Search(new VolleyCallBack() {
                @Override
                public void onSuccess() {
                    System.out.println("Success");
                    searchResults = searchService.getArtists();

                    Artist a = searchResults.get(0);

                    textView.setText("Name: "+a.getName()+" Popularity: "+a.getPopularity());
                }
            });

        }

    };





}