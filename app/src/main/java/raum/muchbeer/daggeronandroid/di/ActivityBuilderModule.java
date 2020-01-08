package raum.muchbeer.daggeronandroid.di;

import javax.inject.Scope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import raum.muchbeer.daggeronandroid.di.auth.AuthModule;
import raum.muchbeer.daggeronandroid.di.auth.AuthScope;
import raum.muchbeer.daggeronandroid.di.auth.AuthViewModelModule;
import raum.muchbeer.daggeronandroid.di.main.MainFragmentBuilderModule;
import raum.muchbeer.daggeronandroid.di.main.MainModule;
import raum.muchbeer.daggeronandroid.di.main.MainScope;
import raum.muchbeer.daggeronandroid.di.main.MainViewModelModule;
import raum.muchbeer.daggeronandroid.network.auth.AuthApi;
import raum.muchbeer.daggeronandroid.views.auth.AuthActivity;
import raum.muchbeer.daggeronandroid.views.main.MainActivity;

@Module
public abstract class ActivityBuilderModule {

    @AuthScope
    @ContributesAndroidInjector( modules = {AuthViewModelModule.class, AuthModule.class} )
    abstract AuthActivity contributeAuthActivity();


    @MainScope
    @ContributesAndroidInjector(modules = {MainFragmentBuilderModule.class, MainViewModelModule.class
                        , MainModule.class})
    abstract MainActivity contributeMainActivity();
}
