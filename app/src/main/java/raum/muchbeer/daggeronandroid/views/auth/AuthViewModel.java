package raum.muchbeer.daggeronandroid.views.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import raum.muchbeer.daggeronandroid.model.Users;
import raum.muchbeer.daggeronandroid.network.auth.AuthApi;
import raum.muchbeer.daggeronandroid.pref.SessionManager;

public class AuthViewModel extends ViewModel {

    private static String LOG_TAG = AuthViewModel.class.getSimpleName();

   private final AuthApi authApi;

    SessionManager sessionManager;
  //  private MediatorLiveData<AuthSecurity<Users>> authUsers = new MediatorLiveData<>();

@Inject
    public AuthViewModel(AuthApi authApi, SessionManager sessionManager) {
this.sessionManager=sessionManager;
    this.authApi = authApi;
        Log.d(LOG_TAG, "tHE dagger has successful inject viewmodel");



   /* authApi.getUser(5)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Users>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Users users) {
                        Log.d(LOG_TAG, "onNext: " + users.getEmail());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(LOG_TAG, "ERROR IS: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });*/
    }

    public void authenticateWithID(int userID) {
Log.d(LOG_TAG, "Attempting to Loggin...");
        sessionManager.authenticateWithID(queryUserID(userID));   }

private LiveData<AuthSecurity<Users>> queryUserID(final int userID) {
    return LiveDataReactiveStreams.fromPublisher(
            authApi.getUser(userID)
                    .onErrorReturn(new Function<Throwable, Users>() {
                        @Override
                        public Users apply(Throwable throwable) throws Exception {
                            Users setError = new Users();
                            setError.setId(-1);
                            return setError;
                        }
                    })
                    .map(new Function<Users, AuthSecurity<Users>>() {
                        @Override
                        public AuthSecurity<Users> apply(Users users) throws Exception {

                            if(users.getId() ==-1) {
                                return AuthSecurity.error("could not authenticate", null);
                            }
                            return AuthSecurity.authenticated(users);
                        }
                    })
                    .subscribeOn(Schedulers.io())
    );
}

    public LiveData<AuthSecurity<Users>> obserseUserState(){  return sessionManager.getAuthUser();  }
}
