package raum.muchbeer.daggeronandroid.di.main;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import raum.muchbeer.daggeronandroid.views.main.profile.ProfileFragment;

@Module
public abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();
}
