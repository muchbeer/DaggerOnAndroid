package raum.muchbeer.daggeronandroid.pref;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import javax.inject.Inject;
import javax.inject.Singleton;

import raum.muchbeer.daggeronandroid.model.Users;
import raum.muchbeer.daggeronandroid.views.auth.AuthSecurity;

@Singleton
public class SessionManager {

    private static final String TAG = SessionManager.class.getSimpleName();

    // data
    private MediatorLiveData<AuthSecurity<Users>> cachedUser = new MediatorLiveData<>();

    @Inject
    public SessionManager() {
    }

    public void authenticateWithID(final LiveData<AuthSecurity<Users>> sourceUser) {
        if(cachedUser !=null) {
            cachedUser.setValue(AuthSecurity.loading((Users)null));
            cachedUser.addSource(sourceUser, observerUser->{
                cachedUser.setValue(observerUser);
                cachedUser.removeSource(sourceUser);
            });
        } else { Log.d(TAG, "AUTH with Id has been detected. Retrieving user from cache");}
    }

    public void logOut() {
        Log.d(TAG, "logOut: logging out...");
        cachedUser.setValue(AuthSecurity.<Users>logout());  }

    public LiveData<AuthSecurity<Users>> getAuthUser(){ return cachedUser;  }


}
