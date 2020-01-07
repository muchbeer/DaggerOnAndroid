package raum.muchbeer.daggeronandroid.views.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import raum.muchbeer.daggeronandroid.pref.SessionManager;
import raum.muchbeer.daggeronandroid.views.auth.AuthActivity;

public class BaseActivity extends DaggerAppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Inject
    SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        subscribeUser();
    }

    private void subscribeUser() {
        sessionManager.getAuthUser().observe(this, usersAuthenticated -> {
            if (usersAuthenticated !=null) {
                switch (usersAuthenticated.status){
                    case ERROR:
                        Log.d(LOG_TAG, "Problem is found: " + usersAuthenticated.message);
                        break;

                    case LOADING:

                        break;

                    case AUTHENTICATED:
                        Log.d(LOG_TAG, "authenticated: ");
                        break;

                    case NOT_AUTHENTICATED:
                        backToLogin();
                        break;

                }}
        });
    }

    private void backToLogin() {
        Intent loginActivity = new Intent(this, AuthActivity.class);
        startActivity(loginActivity);
    finish();}
}
