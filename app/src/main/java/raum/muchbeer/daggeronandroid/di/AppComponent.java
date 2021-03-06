package raum.muchbeer.daggeronandroid.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import raum.muchbeer.daggeronandroid.RealApplication;
import raum.muchbeer.daggeronandroid.pref.SessionManager;


@Singleton
@Component( modules = {
        AndroidSupportInjectionModule.class,
        ActivityBuilderModule.class,
        AppModule.class,
        ViewModelFactoryModule.class,
})
public interface AppComponent extends AndroidInjector<RealApplication> {

    SessionManager sessionManager();


    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
