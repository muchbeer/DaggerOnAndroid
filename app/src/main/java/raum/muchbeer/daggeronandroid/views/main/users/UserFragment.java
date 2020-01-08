package raum.muchbeer.daggeronandroid.views.main.users;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import raum.muchbeer.daggeronandroid.R;
import raum.muchbeer.daggeronandroid.adapter.MainUserAdapter;
import raum.muchbeer.daggeronandroid.adapter.VerticalSpaceItemDecoration;
import raum.muchbeer.daggeronandroid.viewmodel.ViewModelProviderFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends DaggerFragment {

private static final String LOG_TAG = UserFragment.class.getSimpleName();

private UserViewModel viewModel;
private RecyclerView recyclerView;
private ProgressBar progressBar;

@Inject
    MainUserAdapter userAdapter;

@Inject
    ViewModelProviderFactory viewModelFactory;


    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
       // super.onViewCreated(view, savedInstanceState);
recyclerView = view.findViewById(R.id.recycler_view);
progressBar = view.findViewById(R.id.progressBar);


viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel.class);
        init();
getListUsers();

    }

    private void getListUsers() {
        viewModel.observeUser().removeObservers(getViewLifecycleOwner());
        viewModel.observeUser().observe(getViewLifecycleOwner(), listOfUsers ->{
            if(listOfUsers !=null) {
            //    Toast.makeText(getActivity(), listOfUsers.toString(), Toast.LENGTH_LONG).show();
                Log.d(LOG_TAG, "tHE list of users is: " + listOfUsers);
                switch (listOfUsers.status){
                    case SUCCESS:
                        userAdapter.setListsUsers(listOfUsers.data);
                        progressBar.setVisibility(View.GONE);
                       // showProgrressBar(false);
                        break;
                    case LOADING:
                        progressBar.setVisibility(View.VISIBLE);
                        break;

                    case ERROR:
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), "something wrong", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        } );
    }

    private void init(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        VerticalSpaceItemDecoration itemDecoration = new VerticalSpaceItemDecoration(15);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(userAdapter);
    }

    private void showProgrressBar(Boolean isVisible){
        if(isVisible) { progressBar.setVisibility(View.VISIBLE); } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
