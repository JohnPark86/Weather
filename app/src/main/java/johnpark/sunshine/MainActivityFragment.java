package johnpark.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements AsyncResponse{

    private final String TAG = this.getClass().getSimpleName();
    private ArrayList <String> list = new ArrayList<>();
    private ListView lv;
    private ForeCastLoader  f;
    private ArrayAdapter<String> adapter;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                R.layout.list_item_forecast,
                R.id.list_item_forecast_textview, list);
        f = new ForeCastLoader();
        f.delegate = this;
        f.execute("94043");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_main, container, false);

        lv = (ListView)view.findViewById(R.id.listview_forecast);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(getActivity().getApplicationContext(), DetailView.class);
                in.putExtra("tempData", adapter.getItem(position));
                startActivity(in);
            }
        });
        lv.setAdapter(adapter);
        return view;
    }

    public void update()
    {
        f = new ForeCastLoader();
        f.delegate = this;
        f.execute("87592");
    }

    public void processFinish(String[] output)
    {
        list.clear();
        for(String results:output)
        {
            list.add(results);
        }
        adapter.notifyDataSetChanged();
    }
}