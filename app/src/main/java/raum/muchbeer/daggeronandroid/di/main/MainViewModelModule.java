package raum.muchbeer.daggeronandroid.di.main;

import androidx.lifecycle.ViewModel;

import dagger.Binds;
import dagger.MapKey;
import dagger.Module;
import dagger.multibindings.IntoMap;
import raum.muchbeer.daggeronandroid.di.ViewModelKey;
import raum.muchbeer.daggeronandroid.views.main.profile.ProfileViewModel;
import raum.muchbeer.daggeronandroid.views.main.users.UserFragment;
import raum.muchbeer.daggeronandroid.views.main.users.UserViewModel;

@Module
public abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindProfileViewModel(ProfileViewModel profileViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel.class)
    public abstract ViewModel bindUserViewModel(UserViewModel userViewModel);
}
