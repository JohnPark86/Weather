package johnpark.sunshine;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 7/27/15.
 */
public class mCustomSpinnerAdapter extends BaseAdapter {
    private final String TAG = this.getClass().getSimpleName();

    public static class ViewHolder {
        public LinearLayout mBaseLayout;
        public TextView tv;
    }

    private List<String> mItems;
    private String[] mDropdown;
    private Context ctx;

    public mCustomSpinnerAdapter(Context c) {
        Log.i(TAG, "mCustomerSpinnerAdapter");
        ctx = c;
        mItems = new ArrayList<String>();

        mItems.add("Weekly");
        mItems.add("Settings");
        mItems.add("Refresh");
        mItems.add("");

        // used to fill String array with dates in specified format
        mDropdown = new String[mItems.size()];
        mDropdown = mItems.toArray(mDropdown);

    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        LayoutInflater mInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder;

        if(convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.spinner_text_layout, parent, false);

            viewHolder.mBaseLayout = (LinearLayout)convertView.findViewById(R.id.linearSpinner);
            viewHolder.tv = (TextView) convertView.findViewById(R.id.spinnerText1);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(position < getCount() - 1) {
            viewHolder.tv.setText(mItems.get(position));
        }
        else {
            Log.i(TAG, "getDropDownView");
            viewHolder.tv.setHeight(0);
            viewHolder.mBaseLayout.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));
            viewHolder.mBaseLayout.setVisibility(View.GONE);
        }

        parent.setVerticalScrollBarEnabled(false);

        return convertView;
    }
}