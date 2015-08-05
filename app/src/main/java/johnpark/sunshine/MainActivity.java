package johnpark.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    private final String TAG = this.getClass().getSimpleName();
    private MainActivityFragment mfa = new MainActivityFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment, mfa);
        Log.i(TAG, "onCreate");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // help action
                Intent in = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(in);
                return true;
            case R.id.action_refresh:
                // check for updates action
                mfa = (MainActivityFragment) getSupportFragmentManager().findFragmentByTag("MainActivityFragmentTag");
                mfa.update();
                return true;
            default:
                super.onOptionsItemSelected(item);
                return true;
        }

    }

}


