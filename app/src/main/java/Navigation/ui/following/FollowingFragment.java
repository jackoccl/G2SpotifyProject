package Navigation.ui.following;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.project.MainActivity;
import com.example.project.R;

import Adapters.ArtistAdapter;
import Connectors.Classes.VolleyCallBack;
import Connectors.FollowingService;


public class FollowingFragment extends Fragment {
    ListView followingList;
    Bundle bundle;
    FollowingService followingService;
    SwipeRefreshLayout swipeContainer;
    ArtistAdapter followingAdapter;
    public FollowingFragment(Bundle Bundle)  {
        bundle = Bundle;

    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_following,container,false);
        followingService = ((MainActivity)getActivity()).getFollowingService();

        swipeContainer = rootView.findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                followingService.getFollowedArtists(new VolleyCallBack() {
                    @Override
                    public void onSuccess() {
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
        followingAdapter = new ArtistAdapter(getContext(),followingService.getFollowedArtists());
        followingAdapter.addManager(getParentFragmentManager());
        followingList.setAdapter(followingAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}