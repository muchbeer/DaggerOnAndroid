package raum.muchbeer.daggeronandroid.views.main.profile;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import raum.muchbeer.daggeronandroid.R;
import raum.muchbeer.daggeronandroid.model.Users;
import raum.muchbeer.daggeronandroid.viewmodel.ViewModelProviderFactory;


public class ProfileFragment extends DaggerFragment {

private static final String LOG_TAG = ProfileFragment.class.getSimpleName();

private ProfileViewModel profileViewModel;
    private TextView email, username, website;

@Inject
 ViewModelProviderFactory providerFactory;

    public ProfileFragment() {    }

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        email = view.findViewById(R.id.email);
        username = view.findViewById(R.id.username);
        website = view.findViewById(R.id.website);
        profileViewModel = ViewModelProviders.of(this, providerFactory).get(ProfileViewModel.class);

        retrieveObservedUser();
    }

    private void retrieveObservedUser() {

        profileViewModel.obserserUsers().removeObservers(getViewLifecycleOwner());
        profileViewModel.obserserUsers().observe(getViewLifecycleOwner(), usersProfile->{

            if (usersProfile !=null) {
                switch (usersProfile.status){
                    case AUTHENTICATED:
                        Log.d(LOG_TAG, "onChanged: ProfileFragment: AUTHENTICATED... " +
                                "Authenticated as: " + usersProfile.data.getEmail());
                        setUserDetails(usersProfile.data);
                        break;

                    case ERROR:
                        setErrorDetails(usersProfile.message);
                        break;
                }
            }
        });
    }

    private void setErrorDetails(String message){
        email.setText(message);
        username.setText("error");
        website.setText("error");
    }

    private void setUserDetails(Users user){
        email.setText(user.getEmail());
        username.setText(user.getUsername());
        website.setText(user.getWebsite());
    }
}
