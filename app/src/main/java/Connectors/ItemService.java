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

public class ItemService {
    private static String ENDPOINT = "https://api.spotify.com/v1/me/top/artists?";
    public Uri builtUri;
    private final String type;
    private final String time_range;
    private final String offset;
    private final String limit;
    private final SharedPreferences sharedPreferences;
    private final RequestQueue queue; // add or cancel network requests through request queue.

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    private ArrayList<Artist> artists = new ArrayList<>();

    public ItemService(Context context) {
        sharedPreferences = context.getSharedPreferences("SPOTIFY",0);
        queue = Volley.newRequestQueue(context);

        type = "artists";
        time_range = "medium_term";
        limit = "20";
        offset = "5";

        builtUri = Uri.parse(ENDPOINT).buildUpon()
                    .appendQueryParameter("time_range",time_range)
                    .appendQueryParameter("offset",offset)
                    .appendQueryParameter("limit",limit)
                        .build();
    }

    /**
     * @param callBack
     */
    public ArrayList<Artist> getTopItems (VolleyCallBack callBack){
        String endpoint = builtUri.toString();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, builtUri.toString(), null, response->{
            Gson gson = new Gson();
            JSONArray itemsArray = response.optJSONArray("items");
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
                    artists.add(artist);

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
        return artists;
    }

}
