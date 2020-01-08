package raum.muchbeer.daggeronandroid.views.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import raum.muchbeer.daggeronandroid.R;
import raum.muchbeer.daggeronandroid.views.main.profile.ProfileFragment;
import raum.muchbeer.daggeronandroid.views.main.users.UserFragment;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        init();
    }


    private void init() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
    }

/*
    private void testFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, new UserFragment())
                .commit();
    }
*/


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

            case android.R.id.home:
                //if drawer is open please close the drawer
                if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                } else { return false ; }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){


            case R.id.nav_profile:{
                //Hardcode the process of clear the back stack
                NavOptions navOptions = new NavOptions.Builder()
                        .setPopUpTo(R.id.nav_graph, true)
                        .build();

                Navigation.findNavController(this, R.id.nav_host_fragment)
                .navigate(R.id.profileScreen, null, navOptions);
                break;
            }

            case R.id.nav_users:{

                if(ifValidDestination(R.id.userScreen)) {
                    Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.userScreen);
                }

                break;     }
        }
        menuItem.setChecked(true);
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        // will help thee humberger to open but not close the humbeger still will close the back button
        //this will be solved by adding R.id.home in the menuOptionItemSelected to close the menu
        return NavigationUI.navigateUp(Navigation.findNavController(this,
                        R.id.nav_host_fragment),
                        drawerLayout);
    }

    /*eliminate keep stacking in the back stack by check prevent that fragment from being added in
    the back if it is already in the view;*/
    private boolean ifValidDestination(int destination) {
       return destination != Navigation.findNavController(this, R.id.nav_host_fragment)
                                    .getCurrentDestination().getId();
    }
}
