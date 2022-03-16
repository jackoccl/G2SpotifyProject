package com.example.spotifyapi;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

import Adapters.ArtistAdapter;
import Connectors.Artist;
import Connectors.SearchService;
import Connectors.TopItemsService;
import Connectors.VolleyCallBack;
import Navigation.ui.home.HomeFragment;
import Navigation.ui.following.FollowingFragment;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences msharedPreferences;

    private EditText searchField;



    private ListView listView;
    private ArtistAdapter mAdapter;


    private ArrayList<Artist> searchResults;
    private ArrayList<Artist> topItems;

    private SearchService searchService;
    private TopItemsService itemService;

    private BottomNavigationView bottomNav;

    private ListView searchList;

    //TO DO
    // Fragments for Followed and Top Artists
    // VIEW PAGER


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        msharedPreferences = getSharedPreferences("SPOTIFY",0);

        searchField = (EditText)findViewById(R.id.SearchText);
        searchList = (ListView)findViewById(R.id.listSearch);

        bottomNav = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();


        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.navigation_home:
                            selectedFragment =  new HomeFragment();
                            break;
                        case R.id.navigation_following:
                            selectedFragment =  new FollowingFragment();
                            break;


                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                    return true;
                }
            });

        itemService = new TopItemsService(getApplicationContext());
        itemService.getTopItems(new VolleyCallBack() {
            @Override
            public void onSuccess() {
                topItems = itemService.getArtists();


            }
        });


        searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Artist a = searchResults.get(i);
                System.out.println(a.images);
                if(a.id != null){
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

        searchService.Search(new VolleyCallBack() { // volley callback called async through request queue in search method
            @Override
            public void onSuccess() {
                searchResults = searchService.getArtists();
                mAdapter = new ArtistAdapter(getApplicationContext(),searchResults);

                ViewGroup.LayoutParams lp = listView.getLayoutParams();
                lp.height=600;
                listView.setLayoutParams(lp);

                listView.setAdapter(mAdapter);

                OnBackPressedCallback callback = new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        listView.setAdapter(null);
                        lp.height=0;
                        listView.setLayoutParams(lp);
                        remove();
                    }
                };

                getOnBackPressedDispatcher().addCallback(callback);
            }
        });

    }
    private final View.OnClickListener add2Listener = v ->
    {

    };

    private class ItemService {
    }
}
