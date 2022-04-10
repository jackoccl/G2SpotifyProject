package Navigation.ui.searching;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.example.project.MainActivity;
import com.example.project.R;
import com.jakewharton.rxbinding4.widget.RxTextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import Adapters.ArtistAdapter;
import Connectors.Classes.Artist;
import Connectors.Classes.VolleyCallBack;
import Connectors.SearchService;

public class SearchFragment extends Fragment {
    @Override
    public void onStart() {
        super.onStart();
        listView = getView().findViewById(R.id.ListView);
        searchField = getView().findViewById(R.id.SearchText);
        RxTextView.textChanges(searchField).debounce(500, TimeUnit.MILLISECONDS).subscribe(textChanged->{
            if(searchField.getText().length()>0){
                searchArtist(searchField.getText().toString());
            }
        });
    }

    private EditText searchField;
    private ListView listView;
    private SearchService searchService;
    private ArrayList<Artist> searchResults;
    ImageView searchIcon;
    SearchView searchView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        searchService = ((MainActivity)getActivity()).getSearchService();

        return inflater.inflate(R.layout.fragment_searching,container,false);


    }
    private final void searchArtist(String q){
        searchService.Search(q,new VolleyCallBack() { // volley callback called async through request queue in search method
            @Override
            public void onSuccess() {
                searchResults = searchService.getArtists();
                ArtistAdapter mAdapter = new ArtistAdapter(getActivity(), searchResults);
                mAdapter.addManager(getParentFragmentManager());

                ViewGroup.LayoutParams lp = listView.getLayoutParams();
                lp.height=1000;
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
                getActivity().getOnBackPressedDispatcher().addCallback(callback);
            }
        });

    }
    /* OLD SEARCH METHOD
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

     */

@Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}