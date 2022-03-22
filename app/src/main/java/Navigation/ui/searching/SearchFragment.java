package Navigation.ui.searching;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.project.R;


public class SearchFragment extends Fragment {
    @Override
    public void onStart() {
        super.onStart();
        searchField = (EditText)getView().findViewById(R.id.SearchText);
    }

    private EditText searchField;
    private ListView searchList;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_searching,container,false);


    }

    /*
            searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Artist a = searchResults.get(i);
                System.out.println(a.images);
                if(a.id != null){
                }
            }
        });
     */
    /*
      private final void searchArtist(){
        searchService = new SearchService(getApplicationContext(),searchField.getText().toString());

        searchService.Search(new VolleyCallBack() { // volley callback called async through request queue in search method
            @Override
            public void onSuccess() {
                searchResults = searchService.getArtists();
                mAdapter = new ArtistAdapter(getApplicationContext(),searchResults);

                ViewGroup.LayoutParams lp = listView.getLayoutParams();
                lp.height=600;
                listView.setLayoutParams(lp);

                listView.setAdapter(mAdapter);

                OnBackPressedCallback callback = new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        listView.setAdapter(null);
                        lp.height=0;
                        listView.setLayoutParams(lp);
                        remove();
                    }
                };

                getOnBackPressedDispatcher().addCallback(callback);
            }
        });

    }
     */

@Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}