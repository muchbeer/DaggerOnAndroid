package raum.muchbeer.daggeronandroid.views.main.profile;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import raum.muchbeer.daggeronandroid.model.Users;
import raum.muchbeer.daggeronandroid.pref.SessionManager;
import raum.muchbeer.daggeronandroid.views.auth.AuthSecurity;

public class ProfileViewModel extends ViewModel {

private static final String LOG_TAG = ProfileViewModel.class.getSimpleName();

private final SessionManager sessionManager;
@Inject
    public ProfileViewModel(SessionManager sessionManager) {
        Log.d(LOG_TAG, "pROFILE has been instantiate correct");
        this.sessionManager = sessionManager;
    }

    public LiveData<AuthSecurity<Users>> obserserUsers() {  return sessionManager.getAuthUser();  }
}
