package raum.muchbeer.daggeronandroid.views.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import raum.muchbeer.daggeronandroid.R;
import raum.muchbeer.daggeronandroid.viewmodel.ViewModelProviderFactory;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener{

    private static final String TAG = "AuthActivity";
    private static final String LOG_TAG = AuthActivity.class.getSimpleName();

    private ProgressBar progressBar;
    private EditText userId;
    private TextView txtViewUser;
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

        userId = findViewById(R.id.user_id_input);
        findViewById(R.id.login_button).setOnClickListener(this);
        progressBar = findViewById(R.id.progress_bar);
        txtViewUser = findViewById(R.id.txtUsername);
        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel.class);
       setLogo();
       retrieveUserData();
    }

    private void retrieveUserData(){

        viewModel.obserseUserState().observe(this, usersAuthSecurity -> {
            if (usersAuthSecurity !=null) {
            switch (usersAuthSecurity.status){
                case ERROR:
                    showProgrressBar(false);
                    Toast.makeText(getApplicationContext(), "Problem ", Toast.LENGTH_LONG).show();
                    break;

                case LOADING:
                    showProgrressBar(true);
                    break;

                case AUTHENTICATED:
                    showProgrressBar(false);
                    Toast.makeText(getApplicationContext(), "Welcome: "+
                            usersAuthSecurity.data.getUsername(), Toast.LENGTH_LONG).show();
                    txtViewUser.setText("Welcome: " + usersAuthSecurity.data.getUsername());
                    break;

                case NOT_AUTHENTICATED:
                    showProgrressBar(false);
                    break;

            }}
        });
      /*  viewModel.obserseLiveData().observe(this, users -> {
            if(users !=null) {
                Log.d(LOG_TAG, "Username logged is: "+ users);
                Toast.makeText(getApplicationContext(), "Welcome: "+ users.getUsername(), Toast.LENGTH_LONG).show();


            } else {
                Toast.makeText(getApplicationContext(), "Problem", Toast.LENGTH_LONG).show();
            }
        });*/
    }

    private void showProgrressBar(Boolean isVisible){
        if(isVisible) { progressBar.setVisibility(View.VISIBLE); } else {
            progressBar.setVisibility(View.GONE);
        }
    }
    private void setLogo() {
      requestManager.load(logo)
                    .into((ImageView) findViewById(R.id.login_logo));
   }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.login_button:
                loginRequest();
                break;
        }
    }

    private void loginRequest() {
        if(TextUtils.isEmpty(userId.getText().toString())) { return; }
        viewModel.authenticateWithID(Integer.parseInt(userId.getText().toString()));  }
}
