package raum.muchbeer.daggeronandroid.di.main;

import dagger.Module;
import dagger.Provides;
import raum.muchbeer.daggeronandroid.adapter.MainUserAdapter;
import raum.muchbeer.daggeronandroid.network.user.MainApi;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @MainScope
    @Provides
    static MainUserAdapter provideUserAdapter(){
        return new MainUserAdapter();
    }

    @MainScope
    @Provides
   static MainApi provideMainApi(Retrofit retrofit) {
        return retrofit.create(MainApi.class);
    }
}
