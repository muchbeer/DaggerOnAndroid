package raum.muchbeer.daggeronandroid.views.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import raum.muchbeer.daggeronandroid.R;
import raum.muchbeer.daggeronandroid.views.main.profile.ProfileFragment;

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

testFragment();
    }
    private void testFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, new ProfileFragment())
                .commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                sessionManager.logOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
