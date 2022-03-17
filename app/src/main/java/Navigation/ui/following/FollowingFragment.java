package Navigation.ui.following;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.spotifyapi.R;

import java.util.ArrayList;

import Adapters.ArtistAdapter;
import Connectors.Classes.Artist;
import Connectors.Classes.VolleyCallBack;
import Connectors.FollowingService;


public class FollowingFragment extends Fragment {
    ListView followingList;
    ArrayList<Artist> followedArtistsArray = new ArrayList<>();
    Bundle bundle;
    FollowingService followingService;
    SwipeRefreshLayout swipeContainer;

    public FollowingFragment(Bundle Bundle, FollowingService FollowingService) {
        bundle = Bundle;
        followingService = FollowingService;
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_following,container,false);

        swipeContainer = rootView.findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                followingService.getFollowedArtists(new VolleyCallBack() {
                    @Override
                    public void onSuccess() {
                        followedArtistsArray = followingService.getFollowedArtists();
                        System.out.println(followedArtistsArray.get(0).name);
                        followingList.setAdapter(new ArtistAdapter(getContext(),followedArtistsArray));
                        swipeContainer.setRefreshing(false);
                    }
                });

            }
        });
        return rootView;


    }

    @Override
    public void onStart() {
        super.onStart();
        followingList = getView().findViewById(R.id.listViewFollowing);
        followedArtistsArray = followingService.getFollowedArtists();
        followingList.setAdapter(new ArtistAdapter(getContext(),followedArtistsArray));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}