package raum.muchbeer.daggeronandroid.views.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import raum.muchbeer.daggeronandroid.R;
import raum.muchbeer.daggeronandroid.viewmodel.ViewModelProviderFactory;

public class AuthActivity extends DaggerAppCompatActivity {

    private static final String TAG = "AuthActivity";

  @Inject
    Drawable logo;

    @Inject
    RequestManager requestManager;

    private AuthViewModel viewModel;

    @Inject
    ViewModelProviderFactory providerFactory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel.class);
       setLogo();
    }

    private void setLogo() {
      requestManager.load(logo)
                    .into((ImageView) findViewById(R.id.login_logo));
   }
}
