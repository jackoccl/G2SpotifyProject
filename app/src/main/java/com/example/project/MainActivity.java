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
import Connectors.FollowingService;
import Connectors.ItemService;
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
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment(bundle)).commit();

                    }
                });
            }
        });

    }

    public SearchService getSearchService() { return searchService; }
    public FollowingService getFollowingService(){
        return followingService;
    }
    public ItemService getItemService(){
        return itemService;
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
        searchService = new Connectors.SearchService(getApplicationContext());

        intialApiCalls(new VolleyCallBack() {
            @Override
            public void onSuccess() {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment(bundle)).commit();
            }
        });






        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.navigation_home:
                            selectedFragment =  new HomeFragment(bundle);
                            break;
                        case R.id.navigation_following:
                            selectedFragment =  new FollowingFragment(bundle);
                            break;
                        case R.id.navigation_search:
                            selectedFragment =  new SearchFragment();
                            break;


                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                    return true;
                }
            });
    }

    private final View.OnClickListener add2Listener = v ->
    {

    };


}
