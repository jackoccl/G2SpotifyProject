package Connectors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.spotifyapi.R;

import java.util.ArrayList;

public class ArtistAdapter extends ArrayAdapter<Artist> {
    private Context mContext;
    private ArrayList<Artist> artistList = new ArrayList<Artist>();

    public ArtistAdapter(@NonNull Context context, ArrayList<Artist> list) {
        super(context, 0 , list);
        mContext = context;
        artistList = list;
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
        name.setText(currentArtist.getName());

        ImageView image = listItem.findViewById(R.id.imageView_album);
        if(currentArtist.images.size()>0){
            Glide.with(image)
                    .load(currentArtist.getImages().get(0).getUrl())
                    .centerCrop()
                    .into(image);
        }





        return listItem;
    }
}
