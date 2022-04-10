package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.example.project.R;

import java.util.ArrayList;

import Connectors.Classes.Artist;
import Navigation.ui.artist.ArtistFragment;

public class ArtistAdapter extends ArrayAdapter<Artist> {
    private final Context mContext;
    private ArrayList<Artist> artistList = new ArrayList<Artist>();
    private FragmentManager manager;

    public ArtistAdapter(@NonNull Context context, ArrayList<Artist> list) {
        super(context, 0 , list);
        mContext = context;
        artistList = list;
    }
    public void addManager(FragmentManager fm){
        manager=fm;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null){
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);
        }
        Artist currentArtist = artistList.get(position);

        TextView name = listItem.findViewById(R.id.textView_name);
        TextView rating = listItem.findViewById(R.id.ratingCount);
        TextView followers = listItem.findViewById(R.id.followerCount);

        rating.setText(Integer.toString(currentArtist.getPopularity()));
        followers.setText(Integer.toString(currentArtist.getFollowers()));
        name.setText(currentArtist.getName());

        ImageView image = listItem.findViewById(R.id.imageView_album);
        if(currentArtist.images.size()>0){
            Glide.with(image)
                    .load(currentArtist.getImages().get(0).getUrl())
                    .centerCrop()
                    .into(image);
        }

        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.beginTransaction().replace(R.id.fragment_container,new ArtistFragment(currentArtist),"artist").addToBackStack(null).commit();
            }
        });

        return listItem;
    }
}
