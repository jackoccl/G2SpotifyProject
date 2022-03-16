package Components;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.example.spotifyapi.R;

import Connectors.Artist;

public class FollowingCard extends CardView{

    private CardView card;

    private TextView Name;
    private TextView FollowerCount;
    private TextView PopularityCount;

    private ImageView CoverImage;
    private ImageView tempImage;

    private Artist a;

    public FollowingCard(@NonNull Context context,Artist artist) {
        super(context);
        initControl(context);
        Name.setText(artist.getName());
        FollowerCount.setText(String.valueOf(artist.getFollowers()));
        PopularityCount.setText(String.valueOf(artist.getPopularity()));
        if(artist.images.size() > 0 ){
            Glide.with(this)
                    .load(artist.getImages().get(0).getUrl())
                    .centerCrop()
                    .into(CoverImage);
        }
    }


    private void initControl(Context context) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.card_following,this);


        Name = (TextView)findViewById(R.id.textBandName);
        FollowerCount = (TextView)findViewById(R.id.followerCount);
        PopularityCount = (TextView)findViewById(R.id.ratingCount);
        CoverImage = (ImageView)findViewById(R.id.imageCover);
        tempImage=CoverImage;
    }
}
