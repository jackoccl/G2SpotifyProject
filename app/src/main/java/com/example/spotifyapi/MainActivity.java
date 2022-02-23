package com.example.spotifyapi;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import Components.FollowingCard;
import Connectors.Artist;
import Connectors.ArtistAdapter;
import Connectors.SearchService;
import Connectors.VolleyCallBack;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences msharedPreferences;

    private EditText searchField;

    private FollowingCard TestCard;

    private ListView listView;
    private ArtistAdapter mAdapter;


    private ArrayList<Artist> searchResults;
    private SearchService searchService;

    private ListView searchList;

    public String q = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        msharedPreferences = getSharedPreferences("SPOTIFY",0);

        String USERID = msharedPreferences.getString("userid", "");


        searchField = (EditText)findViewById(R.id.SearchText);
        searchList = (ListView)findViewById(R.id.listSearch);
        TestCard = (FollowingCard)findViewById(R.id.card_test);

        searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Artist a = searchResults.get(i);
                System.out.println(a.images);
                if(a.id != null){
                    TestCard.setInfo(a);
                }
            }
        });



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
        listView = findViewById(R.id.listSearch);

        searchService.Search(new VolleyCallBack() {
            @Override
            public void onSuccess() {
                System.out.println("Success");
                searchResults = searchService.getArtists();
                mAdapter = new ArtistAdapter(getApplicationContext(),searchResults);

                ViewGroup.LayoutParams lp = listView.getLayoutParams();
                lp.height=500;
                listView.setLayoutParams(lp);

                listView.setAdapter(mAdapter);
                /*searchResults = searchService.getArtists();
                Artist a = searchResults.get(0);
                System.out.println(a.images);
                if(a.id != null){
                    TestCard.setInfo(a);
                }*/

            }
        });

    }
    private final View.OnClickListener add2Listener = v ->
    {

    };

}
