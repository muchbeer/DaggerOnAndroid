package raum.muchbeer.daggeronandroid;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import raum.muchbeer.daggeronandroid.di.DaggerAppComponent;

public class RealApplication extends DaggerApplication {


    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
