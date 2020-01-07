package raum.muchbeer.daggeronandroid.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import raum.muchbeer.daggeronandroid.di.auth.AuthModule;
import raum.muchbeer.daggeronandroid.di.auth.AuthViewModelModule;
import raum.muchbeer.daggeronandroid.di.main.MainFragmentBuilderModule;
import raum.muchbeer.daggeronandroid.di.main.MainViewModelModule;
import raum.muchbeer.daggeronandroid.network.auth.AuthApi;
import raum.muchbeer.daggeronandroid.views.auth.AuthActivity;
import raum.muchbeer.daggeronandroid.views.main.MainActivity;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector( modules = {AuthViewModelModule.class, AuthModule.class} )
    abstract AuthActivity contributeAuthActivity();


    @ContributesAndroidInjector(modules = {MainFragmentBuilderModule.class, MainViewModelModule.class})
    abstract MainActivity contributeMainActivity();
}
