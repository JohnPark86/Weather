package johnpark.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;


public class DetailView extends ActionBarActivity
{
    private final String TAG = this.getClass().getSimpleName();
    DetailViewFragment detailFrag = new DetailViewFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment, detailFrag);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // help action
                Intent in = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(in);
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                super.onOptionsItemSelected(item);
                return true;
        }
    }

}