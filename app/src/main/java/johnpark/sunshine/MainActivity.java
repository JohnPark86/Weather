package johnpark.sunshine;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;

import static android.app.ActionBar.NAVIGATION_MODE_LIST;
import static android.app.ActionBar.OnNavigationListener;


public class MainActivity extends ActionBarActivity
                    implements OnNavigationListener, android.support.v7.app.ActionBar.OnNavigationListener {

    private final String TAG = this.getClass().getSimpleName();
    private boolean mNaviFirstHit = true;
    MainActivityFragment mfa;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        mfa = new MainActivityFragment();
        transaction.add(R.id.fragment, mfa, "MainActivityFragmentTag");

        String[] dropdownValues = getResources().getStringArray(R.array.nav_list);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(actionBar.getThemedContext(),
                android.R.layout.simple_spinner_item, android.R.id.text1,
                dropdownValues);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setNavigationMode(NAVIGATION_MODE_LIST);
        actionBar.setListNavigationCallbacks(adapter, this);
    }

    @Override
    public boolean onNavigationItemSelected(int position, long id) {
        if (mNaviFirstHit) {
            mNaviFirstHit = false;
            return true;
        }



        if(position==1)
        {
            Intent in = new Intent(getApplicationContext(),SettingsActivity.class);
            startActivity(in);
        }
        if(position==2)
        {
            mfa = (MainActivityFragment) getSupportFragmentManager().findFragmentByTag("MainActivityFragmentTag");
            mfa.update();
        }
        return true;
    }
}
