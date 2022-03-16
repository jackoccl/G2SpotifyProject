package Navigation.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.spotifyapi.R;

import java.util.ArrayList;

import Adapters.ArtistPagerAdapter;
import Components.FollowingCard;
import Connectors.Artist;
import Connectors.TopItemsService;
import Connectors.VolleyCallBack;

public class HomeFragment extends Fragment {

    TextView Greeting;
    ViewPager2 TopItemPager;
    TopItemsService topItemsService;
    ArrayList<Artist> TopItemsArray= new ArrayList<>();
    ArrayList<FollowingCard> CardArray;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        topItemsService = new TopItemsService(getContext());
        topItemsService.getTopItems(new VolleyCallBack() {
            @Override
            public void onSuccess() {
                TopItemsArray = topItemsService.getArtists();
                TopItemPager = (ViewPager2)getView().findViewById(R.id.TopItemPager);
                ArtistPagerAdapter adapter = new ArtistPagerAdapter(TopItemsArray);
                TopItemPager.setAdapter(adapter);
                System.out.println(TopItemsArray.get(0).name);
                TopItemPager.setClipToPadding(false);
                TopItemPager.setClipChildren(false);
                TopItemPager.setOffscreenPageLimit(3);
                TopItemPager.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);

                Animation fadeIn = new AlphaAnimation(0,1);
                fadeIn.setInterpolator(new DecelerateInterpolator());
                fadeIn.setDuration(2000);

                AnimationSet animation= new AnimationSet(false);
                animation.addAnimation(fadeIn);

                Greeting.startAnimation(fadeIn);
                TopItemPager.startAnimation(fadeIn);

                TopItemPager.setVisibility(View.VISIBLE);
                Greeting.setVisibility(View.VISIBLE);



            }
        });

        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Greeting = (TextView)getView().findViewById(R.id.YourTop);
        TopItemPager = (ViewPager2)getView().findViewById(R.id.TopItemPager);
        TopItemPager.setVisibility(View.INVISIBLE);
        Greeting.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}