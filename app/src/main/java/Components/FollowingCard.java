package Components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.spotifyapi.R;

public class FollowingCard extends CardView{

    private CardView card;

    public FollowingCard(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initControl(context);
    }


    private void initControl(Context context) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.card_following,this);
    }
}
