package johnpark.sunshine;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;


/**
 * A placeholder fragment containing a simple view.
 */
public class DetailViewFragment extends Fragment {

    private final String TAG = this.getClass().getSimpleName();
    private TextView weekDay;
    private TextView date;
    private TextView hi;
    private TextView low;
    private TextView humidity;
    private TextView pressure;
    private TextView description;
    private TextView temp;
    private TextView wind;
    private ImageView iv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_detail_view, container, false);
        String Item = getActivity().getIntent().getExtras().getString("tempData");
        String[] vals = Item.split("-");

        initialize(rootView);
        Log.i("Result: ", Item);
        HashMap<String,String> weekdays = new HashMap<>();
        generateDays(weekdays);

        String[] dayDate = vals[0].split("\\s+");
        weekDay.setText(weekdays.get(dayDate[0]));
        date.setText(dayDate[1] + " " + dayDate[2]);
        description.setText(vals[1].trim());
        setImage(vals[1]);

        String[] hilo = vals[2].split("/");
        hi.setText("hi: " + hilo[0]);
        low.setText("lo: " + hilo[1]);

        humidity.setText("humidity" + vals[3] + "%");
        pressure.setText("pressure: " + vals[4] + "hPa");
        wind.setText("wind: " + vals[5] + "km/h");
        temp.setText(vals[6]);
        temp.bringToFront();

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.i(TAG, "onCreateOptions");
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void generateDays(HashMap<String, String> weekdays) {
        weekdays.put("Mon","Monday");
        weekdays.put("Tue","Tuesday");
        weekdays.put("Wed","Wednesday");
        weekdays.put("Thu","Thursday");
        weekdays.put("Fri", "Friday");
        weekdays.put("Sat", "Saturday");
        weekdays.put("Sun", "Sunday");
    }

    private void initialize(View rootView) {
        weekDay = (TextView)rootView.findViewById(R.id.currentDay);
        date = (TextView)rootView.findViewById(R.id.date);
        hi = (TextView)rootView.findViewById(R.id.tempHigh);
        low = (TextView)rootView.findViewById(R.id.tempLow);
        humidity = (TextView)rootView.findViewById(R.id.humidity);
        pressure = (TextView)rootView.findViewById(R.id.pressure);
        description = (TextView)rootView.findViewById(R.id.forecast);
        temp = (TextView)rootView.findViewById(R.id.tempMain);
        wind = (TextView)rootView.findViewById(R.id.wind);
        iv = (ImageView)rootView.findViewById(R.id.imageView);
    }

    private void setImage(String val) {
        Resources resources = getResources();
        if(val.trim().equals("Clear"))
            iv.setImageDrawable(resources.getDrawable(R.drawable.art_clear));
        if(val.trim().equals("Rain"))
            iv.setImageDrawable(resources.getDrawable(R.drawable.art_storm));
        if(val.trim().equals("Fog"))
            iv.setImageDrawable(resources.getDrawable(R.drawable.art_fog));
        if(val.trim().equals("Light Clouds"))
            iv.setImageDrawable(resources.getDrawable(R.drawable.art_light_clouds));
        if(val.trim().equals("Light Rain"))
            iv.setImageDrawable(resources.getDrawable(R.drawable.art_light_rain));
        if(val.trim().equals("Snow"))
            iv.setImageDrawable(resources.getDrawable(R.drawable.art_snow));
        if(val.trim().equals("Clouds"))
            iv.setImageDrawable(resources.getDrawable(R.drawable.art_clouds));
    }
}
