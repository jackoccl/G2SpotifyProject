package Connectors;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Connectors.Classes.Artist;
import Connectors.Classes.VolleyCallBack;
import Connectors.Classes.images;

public class FollowingService {
    private static String endpoint = "https://api.spotify.com/v1/me/following?";
    public Uri builtUri;
    private String type;
    private final SharedPreferences sharedPreferences;
    private final RequestQueue queue; // add or cancel network requests through request queue.

    public ArrayList<Artist> getFollowedArtists() {
        return followedArtists;
    }

    private ArrayList<Artist> followedArtists = new ArrayList<>();

    public FollowingService(Context context) {
        sharedPreferences = context.getSharedPreferences("SPOTIFY",0);
        queue = Volley.newRequestQueue(context);

    }

    public void followArtist(VolleyCallBack callback){

    }

    /**
     * @param callBack
     */
    public ArrayList<Artist> getFollowedArtists (VolleyCallBack callBack){
        type = "artist";
        builtUri = Uri.parse(endpoint).buildUpon()
                .appendQueryParameter("type",type)
                .build();

        followedArtists = new ArrayList<>();
        String endpoint = builtUri.toString();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, builtUri.toString(), null, response->{
            Gson gson = new Gson();
            JSONObject artistsObj = response.optJSONObject("artists");
            JSONArray itemsArray = artistsObj.optJSONArray("items");
            for(int n = 0;n<itemsArray.length();n++){
                try {
                    JSONObject object = itemsArray.getJSONObject(n);
                    Artist artist = gson.fromJson(object.toString(),Artist.class);
                    JSONArray imagesArray = object.optJSONArray("images");
                    ArrayList<images> list = new ArrayList<>();
                    if(imagesArray != null){
                        for(int i = 0;i<imagesArray.length();i++){
                            images img = gson.fromJson(imagesArray.getString(i),images.class);
                            list.add(img);
                        }
                    }
                    artist.setImages(list);
                    followedArtists.add(artist);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            callBack.onSuccess();
        },error -> {

        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                String token = sharedPreferences.getString("token", "");
                String auth = "Bearer " + token;
                headers.put("Authorization", auth);
                return headers;
            }
        };

        queue.add(jsonObjectRequest);
        return followedArtists;
    }
}
