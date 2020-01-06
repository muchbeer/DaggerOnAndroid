package raum.muchbeer.daggeronandroid.di.auth;


import androidx.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import raum.muchbeer.daggeronandroid.di.ViewModelKey;
import raum.muchbeer.daggeronandroid.views.auth.AuthViewModel;

@Module
public abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);
}
