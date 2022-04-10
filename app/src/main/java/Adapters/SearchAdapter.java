package Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.R;

import java.util.ArrayList;

import Connectors.Classes.Artist;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>implements Filterable {
    ArrayList<Artist> searchList;
    ArrayList<Artist> filterSearchList;
    TextView name;
    TextView rating;
    TextView followers;
    ImageView image;
    public class SearchViewHolder extends RecyclerView.ViewHolder {
        public SearchViewHolder(@NonNull View listItem) {
            super(listItem);
            name = listItem.findViewById(R.id.textView_name);
            rating = listItem.findViewById(R.id.ratingCount);
            followers = listItem.findViewById(R.id.followerCount);
            image = listItem.findViewById(R.id.imageView_album);
        }
    }
    SearchAdapter(ArrayList<Artist> searchResults){
        searchList = searchResults;
        filterSearchList = new ArrayList<>(searchList);
    }
    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Artist currentArtist = searchList.get(position);
        rating.setText(Integer.toString(currentArtist.getPopularity()));
        followers.setText(Integer.toString(currentArtist.getFollowers()));
        name.setText(currentArtist.getName());

        if(currentArtist.images.size()>0){
            Glide.with(image)
                    .load(currentArtist.getImages().get(0).getUrl())
                    .centerCrop()
                    .into(image);
        }
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }


}
