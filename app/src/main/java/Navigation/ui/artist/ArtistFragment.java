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

import com.example.project.R;

import Connectors.Classes.Artist;


public class ArtistFragment extends Fragment {
    Artist artist;
    TextView name;
    Button follow;
    public ArtistFragment(Artist a) {
        artist=a;
    }

    @Override
    public void onStart() {
        super.onStart();
        name = (TextView)getView().findViewById(R.id.TV_ArtistName);
        name.setText(artist.name);
        follow = (Button)getView().findViewById(R.id.BTN_follow);
        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // follow
                // TO DO CREATE FOLLOW SERVICE
            }
        });

    }

    private EditText searchField;
    private ListView searchList;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        return inflater.inflate(R.layout.fragment_artist,container,false);


    }



@Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}