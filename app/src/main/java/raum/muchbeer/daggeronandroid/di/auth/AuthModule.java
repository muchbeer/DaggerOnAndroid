package raum.muchbeer.daggeronandroid.di.auth;

import dagger.Module;
import dagger.Provides;
import raum.muchbeer.daggeronandroid.network.auth.AuthApi;
import retrofit2.Retrofit;

@Module
public abstract class AuthModule {

    @Provides
    static AuthApi provideSessionApi(Retrofit retrofit){
        return retrofit.create(AuthApi.class);
    }
}
