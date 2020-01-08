package raum.muchbeer.daggeronandroid.network.auth;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import raum.muchbeer.daggeronandroid.model.Users;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AuthApi {

    // /users/id
    //https://jsonplaceholder.typicode.com/users/1
    @GET("users/{id}")
    Flowable<Users> getUser(
            @Path("id") int id
    );
}
