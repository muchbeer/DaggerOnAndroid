package raum.muchbeer.daggeronandroid.views.main.users;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import raum.muchbeer.daggeronandroid.model.UserData;
import raum.muchbeer.daggeronandroid.network.user.MainApi;
import raum.muchbeer.daggeronandroid.pref.SessionManager;

public class UserViewModel extends ViewModel {

    private static final String LOG_TAG = UserViewModel.class.getSimpleName();

    //inject
    final private SessionManager sessionManager;
    final private MainApi mainApi;

    private MediatorLiveData<UserState<List<UserData>>> userPost;
@Inject
    public UserViewModel(SessionManager sessionManager, MainApi mainApi) {
    this.sessionManager = sessionManager;
    this.mainApi = mainApi;
    Log.d(LOG_TAG, "VIiew Model for UserViewModel is workiing");
}

    public LiveData<UserState<List<UserData>>> observeUser() {

    if(userPost ==null) {
        userPost = new MediatorLiveData<>();
        userPost.setValue(UserState.loading(null));

    final LiveData<UserState<List<UserData>>> getUsers = LiveDataReactiveStreams.fromPublisher(

            //the best approach is to create a single  viewModel and access it from there
            //or you can use Shared Preference to pass the Key parameter
            mainApi.getPostsFromUser(sessionManager.getAuthUser().getValue().data.getId())
                                            .onErrorReturn(new Function<Throwable, List<UserData>>() {
                                                @Override
                                                public List<UserData> apply(Throwable throwable) throws Exception {
                                                   Log.d(LOG_TAG, "tHE error throw is: " + throwable);
                                                   UserData userError = new UserData();
                                                   userError.setId(-1);
                                                   ArrayList<UserData> returnError= new ArrayList<>();
                                                   returnError.add(userError);
                                                   return returnError;

                                                }
                                            })
                                            .map(new Function<List<UserData>, UserState<List<UserData>>>() {
                                                @Override
                                                public UserState<List<UserData>> apply(List<UserData> userData) throws Exception {

                                                   if(userData.size()>0) {
                                                       if(userData.get(0).getId() == -1) {
                                                           return UserState.error("Something is wrong", null);
                                                       }
                                                   }Log.d(LOG_TAG, "tHE user collected is: "+ userData);

                                                    return UserState.success(userData);

                                                }
                                            })
                                                .subscribeOn(Schedulers.io())
                                            );

                            userPost.addSource(getUsers, listUserState -> {
                                userPost.setValue(listUserState);
                                userPost.removeSource(getUsers);
                                Log.d(LOG_TAG, "tHE mediator Data is: "+ listUserState);
                            });
                           }
                    return userPost;
                        }

}
