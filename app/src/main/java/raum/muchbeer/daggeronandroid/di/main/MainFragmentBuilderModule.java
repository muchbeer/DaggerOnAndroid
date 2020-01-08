package raum.muchbeer.daggeronandroid.di.main;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import raum.muchbeer.daggeronandroid.views.main.profile.ProfileFragment;
import raum.muchbeer.daggeronandroid.views.main.users.UserFragment;

@Module
public abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();

    @ContributesAndroidInjector
    abstract UserFragment contributeUserFragment();
}
