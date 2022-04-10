package Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.R;

import java.util.ArrayList;

import Connectors.Classes.Artist;
import Navigation.ui.artist.ArtistFragment;

public class ArtistPagerAdapter extends RecyclerView.Adapter<ArtistPagerAdapter.ViewHolder> {

    ArrayList<Artist> TopItemArtists = new ArrayList<>();
    FragmentManager manager;

    public ArtistPagerAdapter(ArrayList<Artist> artists){
        TopItemArtists = artists;

    }
    public void addManager(FragmentManager fm){
        manager=fm;
    }



    @NonNull
    @Override
    public ArtistPagerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_following,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistPagerAdapter.ViewHolder holder, int position) {
        Artist artist = TopItemArtists.get(position);

        holder.Name.setText(TopItemArtists.get(position).name);
        holder.FollowerCount.setText(String.valueOf(artist.getFollowers()));
        holder.PopularityCount.setText(String.valueOf(artist.getPopularity()));
        if(artist.images.size() > 0 ){
            Glide.with(holder.CoverImage)
                    .load(artist.getImages().get(0).getUrl())
                    .centerCrop()
                    .into(holder.CoverImage);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                manager.beginTransaction().replace(R.id.fragment_container,new ArtistFragment(artist)).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return TopItemArtists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView Name;
        private TextView FollowerCount;
        private TextView PopularityCount;

        private ImageView CoverImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Name = (TextView)itemView.findViewById(R.id.textBandName);
            FollowerCount = (TextView)itemView.findViewById(R.id.followerCount);
            PopularityCount = (TextView)itemView.findViewById(R.id.ratingCount);
            CoverImage = (ImageView)itemView.findViewById(R.id.imageCover);


        }

    }
}


