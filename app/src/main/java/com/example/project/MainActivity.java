package com.example.project;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

import Adapters.ArtistAdapter;
import Connectors.Classes.Artist;
import Connectors.Classes.VolleyCallBack;
import Connectors.SearchService;
import Navigation.ui.following.FollowingFragment;
import Navigation.ui.home.HomeFragment;
import Navigation.ui.searching.SearchFragment;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences msharedPreferences;

    private EditText searchField;


    private ArtistAdapter mAdapter;



    private ArrayList<Artist> searchResults;
    private ArrayList<Artist> topArtistsArray;
    private ArrayList<Artist> followedArtistsArray;

    private SearchService searchService;
    private Connectors.ItemService itemService;
    private Connectors.FollowingService followingService;

    private BottomNavigationView bottomNav;

    private Bundle bundle;
    //TO DO
    // Fragments for Followed and Top Artists
    // VIEW PAGER

    protected void intialApiCalls(VolleyCallBack callBack){ // DO THIS BETTER?
        itemService.getTopItems(new VolleyCallBack() {
            @Override
            public void onSuccess() {
                topArtistsArray = itemService.getArtists();
                followingService.getFollowedArtists(new VolleyCallBack() {
                    @Override
                    public void onSuccess() {
                        followedArtistsArray = followingService.getFollowedArtists();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment(bundle,followingService,itemService)).commit();

                    }
                });
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);
        msharedPreferences = getSharedPreferences("SPOTIFY",0);
        bundle = new Bundle();
        bottomNav = findViewById(R.id.bottom_navigation);

        itemService = new Connectors.ItemService(getApplicationContext());
        followingService = new Connectors.FollowingService(getApplicationContext());

        intialApiCalls(new VolleyCallBack() {
            @Override
            public void onSuccess() {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment(bundle,followingService,itemService)).commit();
            }
        });







        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.navigation_home:
                            selectedFragment =  new HomeFragment(bundle,followingService,itemService);
                            break;
                        case R.id.navigation_following:
                            selectedFragment =  new FollowingFragment(bundle,followingService);
                            break;
                        case R.id.navigation_search:
                            selectedFragment =  new SearchFragment();
                            break;



                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                    return true;
                }
            });









/*
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
*/




    }

    private final View.OnClickListener add2Listener = v ->
    {

    };

    @Override
    protected void onStart() {
        super.onStart();
        /*
        String clientid = msharedPreferences.getString("CLIENT_ID","");
        System.out.println(clientid);
        ConnectionParams connectionParams =
                new ConnectionParams.Builder(msharedPreferences.getString("CLIENT_ID",""))
                        .setRedirectUri(msharedPreferences.getString("REDIRECT_URI",""))
                        .showAuthView(true)
                        .build();

        SpotifyAppRemote.connect(this, connectionParams,
                new Connector.ConnectionListener() {

                    @Override
                    public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                        mSpotifyAppRemote = spotifyAppRemote;
                        Log.d("MainActivity", "Connected! Yay!");

                        // Now you can start interacting with App Remote
                        connected();
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.e("MainActivity", throwable.getMessage(), throwable);

                        // Something went wrong when attempting to connect! Handle errors here
                    }
                });
*/
    }

    private void connected() {
    }

    private class ItemService {
    }
}
