package raum.muchbeer.daggeronandroid.views.auth;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import raum.muchbeer.daggeronandroid.model.Users;
import raum.muchbeer.daggeronandroid.network.auth.AuthApi;

public class AuthViewModel extends ViewModel {

    private static String LOG_TAG = AuthViewModel.class.getSimpleName();

    private final AuthApi authApi;

@Inject
    public AuthViewModel(AuthApi authApi) {

    this.authApi = authApi;
        Log.d(LOG_TAG, "tHE dagger has successful inject viewmodel");

    authApi.getUser(5)
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
                });
    }
}
