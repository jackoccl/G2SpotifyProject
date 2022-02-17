package com.example.spotifyapi;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import Components.FollowingCard;
import Connectors.Artist;
import Connectors.SearchService;
import Connectors.Song;
import Connectors.SongService;
import Connectors.VolleyCallBack;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences msharedPreferences;

    private Button addBtn2;
    private EditText searchField;

    private FollowingCard TestCard;

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

        String USERID = msharedPreferences.getString("userid", "");


        searchField = (EditText)findViewById(R.id.SearchText);
        TestCard = (FollowingCard)findViewById(R.id.card_test);


        addBtn2 = (Button) findViewById(R.id.add2);
        addBtn2.setOnClickListener(add2Listener);

        textView = findViewById(R.id.tv_welcome);
        textView.setText(getString(R.string.Text_Welcome)+" "+ USERID);

        searchField.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent keyEvent) {
                if(keyEvent.getAction()==KeyEvent.ACTION_DOWN){
                    switch(keyCode){
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            if(searchField.getText().length()>0){
                                searchArtist();
                            }
                            return true;


                    }
                }
                return false;
            }
        });


    }
    private final void searchArtist(){
        searchService = new SearchService(getApplicationContext(),searchField.getText().toString());
        searchService.Search(new VolleyCallBack() {
            @Override
            public void onSuccess() {
                System.out.println("Success");
                searchResults = searchService.getArtists();
                Artist a = searchResults.get(0);
                System.out.println(a.images);
                if(a.id != null){
                    TestCard.setInfo(a);
                }



            }
        });
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
                    TestCard.setInfo(a);

                }
            });

        }

    };

}
