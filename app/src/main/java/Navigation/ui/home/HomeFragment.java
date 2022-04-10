package Navigation.ui.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.project.R;

import java.util.ArrayList;

import Adapters.ArtistPagerAdapter;
import Connectors.Classes.Artist;
import Connectors.Classes.VolleyCallBack;
import Connectors.FollowingService;
import Connectors.ItemService;

public class HomeFragment extends Fragment {
    SharedPreferences msharedPreferences;
    TextView Greeting;
    ViewPager2 TopItemPager;
    ViewPager2 TopItemPager2;

    ItemService topItemsService;
    FollowingService followingService;

    ArrayList<Artist> TopItemsArray= new ArrayList<>();
    ArrayList<Artist> FollowedArtistArray= new ArrayList<Artist>();

    Bundle bundle;
    private ArtistPagerAdapter TopItemsAdapter;
    private ArtistPagerAdapter FollowedItemsAdapter;


    public HomeFragment(Bundle Bundle, FollowingService FollowingService, ItemService ItemService) {
        bundle=Bundle;
        topItemsService = ItemService;
        followingService = FollowingService;

    }


    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home,container,false);

        TopItemsArray = topItemsService.getArtists();
        FollowedArtistArray = followingService.getFollowedArtists();

        TopItemPager = (ViewPager2)rootView.findViewById(R.id.TopItemPager);
        TopItemPager2 = (ViewPager2)rootView.findViewById(R.id.TopItemPager2);


        Greeting = (TextView)rootView.findViewById(R.id.YourTop);
        TopItemPager = (ViewPager2)rootView.findViewById(R.id.TopItemPager);

        TopItemsAdapter = new ArtistPagerAdapter(TopItemsArray);
        TopItemsAdapter.addManager(getParentFragmentManager());

        FollowedItemsAdapter = new ArtistPagerAdapter(FollowedArtistArray);
        FollowedItemsAdapter.addManager(getParentFragmentManager());

        TopItemPager.setAdapter(TopItemsAdapter);
        TopItemPager2.setAdapter(FollowedItemsAdapter);



        TopItemPager.setClipToPadding(false);
        TopItemPager.setPadding(0,0,0,0);
        TopItemPager.setPageTransformer(new MarginPageTransformer(0));


                /*
                Animation fadeIn = new AlphaAnimation(0,1);
                fadeIn.setInterpolator(new DecelerateInterpolator());
                fadeIn.setDuration(2000);

                AnimationSet animation= new AnimationSet(false);
                animation.addAnimation(fadeIn);

                Greeting.startAnimation(fadeIn);
                TopItemPager.startAnimation(fadeIn);
                */

        //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ArtistFragment()).commit();

        followingService.getFollowedArtists(new VolleyCallBack() {
            @Override
            public void onSuccess() {
                FollowedArtistArray = followingService.getFollowedArtists();
            }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}