package Connectors;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

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

public class SearchService {

    private static final String ENDPOINT = "https://api.spotify.com/v1/search?";
    private String url;
    private final String type;
    private final SharedPreferences sharedPreferences;
    private final RequestQueue queue;
    private ArrayList<Artist> artists = new ArrayList<>();

    public SearchService(Context context, String q) {
        q = q.replaceAll(" ","%20");
        sharedPreferences = context.getSharedPreferences("SPOTIFY",0);
        queue = Volley.newRequestQueue(context);

        type = "artist";
        url = String.format(ENDPOINT+"q=%s&type=%s",q, type);
    }

    public ArrayList<Artist> getArtists(){
        return artists;
    }

    public ArrayList<Artist> Search(final VolleyCallBack callBack) {
        String endpoint = url;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, endpoint, null, response -> {
                    Gson gson = new Gson();
                    JSONObject artistsObj = response.optJSONObject("artists");
                    JSONArray itemsArray = artistsObj.optJSONArray("items");
                    for(int n=0;n<itemsArray.length();n++){
                        try {
                            JSONObject object = itemsArray.getJSONObject(n);
                            Artist artist = gson.fromJson(object.toString(), Artist.class);
                            artists.add(artist);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    callBack.onSuccess();

                }, error -> {
                    // TODO: Handle error
                    Log.e("ERROR",error.getMessage());

                }) {
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
