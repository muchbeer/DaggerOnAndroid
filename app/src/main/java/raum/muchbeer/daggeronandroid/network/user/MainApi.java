package raum.muchbeer.daggeronandroid.network.user;

import java.util.List;

import io.reactivex.Flowable;
import raum.muchbeer.daggeronandroid.model.UserData;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainApi {

    // /posts?userId=1/
    //https://jsonplaceholder.typicode.com/posts?userId=2
    @GET("posts")
    Flowable<List<UserData>> getPostsFromUser(
            @Query("userId") int id
    );
}
