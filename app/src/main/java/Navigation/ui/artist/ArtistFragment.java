package Navigation.ui.artist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.project.MainActivity;
import com.example.project.R;

import Connectors.Classes.Artist;
import Connectors.Classes.VolleyCallBack;
import Connectors.FollowingService;


public class ArtistFragment extends Fragment {
    Artist artist;
    TextView name;
    Button follow;
    FollowingService followingService;
    private boolean isFollowed;
    public ArtistFragment(Artist a) {
        artist=a;
    }
    Fragment frg;

    @Override
    public void onStart() {
        super.onStart();
        name = (TextView)getView().findViewById(R.id.TV_ArtistName);
        name.setText(artist.name);
        follow = (Button)getView().findViewById(R.id.BTN_follow);

        if(isFollowed){
            follow.setText("Unfollow");
            follow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // follow
                    // TO DO CREATE FOLLOW SERVICE
                    followingService.unfollowArtistById(artist.id,new VolleyCallBack(){
                        @Override
                        public void onSuccess() {
                            System.out.println("Unfollowed");
                            followingService.getFollowedArtists().remove(artist);
                            followingService.getFollowedArtists(new VolleyCallBack() {
                                @Override
                                public void onSuccess() {
                                    getParentFragmentManager().beginTransaction().detach(frg).commit();
                                    getParentFragmentManager().beginTransaction().attach(frg).commit();
                                }
                            });
                        }
                    });

                }
            });
        }else{
            follow.setText("Follow");
            follow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // follow
                    // TO DO CREATE FOLLOW SERVICE
                    followingService.followArtistById(artist.id,new VolleyCallBack(){
                        @Override
                        public void onSuccess() {
                            followingService.getFollowedArtists().add(artist);
                            followingService.getFollowedArtists(new VolleyCallBack() {
                                @Override
                                public void onSuccess() {
                                    getParentFragmentManager().beginTransaction().detach(frg).commit();
                                    getParentFragmentManager().beginTransaction().attach(frg).commit();
                                }
                            });
                        }
                    });

                }
            });

        }



    }

    private EditText searchField;
    private ListView searchList;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        frg = ((MainActivity)getActivity()).getSupportFragmentManager().findFragmentByTag("artist");
        followingService = ((MainActivity)getActivity()).getFollowingService();
        for(Artist a:followingService.getFollowedArtists()){
            if(a.id.equals(artist.id)){
                isFollowed =true;
            }
        }
        return inflater.inflate(R.layout.fragment_artist,container,false);


    }



@Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}