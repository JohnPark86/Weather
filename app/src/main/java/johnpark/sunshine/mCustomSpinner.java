package johnpark.sunshine;

import android.content.Context;
import android.util.Log;
import android.widget.Spinner;

/**
 * Created by admin on 7/26/15.
 */
public class mCustomSpinner extends Spinner {
    private final String TAG = this.getClass().getSimpleName();
    OnItemSelectedListener listener;

    public mCustomSpinner(Context context) {
        super(context);
        Log.i(TAG, "mCustomSpinner");
    }

    @Override
    public void setSelection(int position) {
        super.setSelection(position);
        if (listener != null)
            listener.onItemSelected(null, null, position, 0);

    }

    public void setOnItemSelectedEvenIfUnchangedListener(
            OnItemSelectedListener listener) {
        this.listener = listener;
    }
}
