package raum.muchbeer.daggeronandroid.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import raum.muchbeer.daggeronandroid.R;
import raum.muchbeer.daggeronandroid.model.UserData;
import raum.muchbeer.daggeronandroid.views.main.users.UserViewModel;

public class MainUserAdapter extends RecyclerView.Adapter<MainUserAdapter.UserViewHolder> {

    private static final String LOG_TAG = MainUserAdapter.class.getSimpleName();
    private List<UserData> listsUsers = new ArrayList<>();

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.fragment_user_item,
                parent,
                false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        ((UserViewHolder)holder).bind(listsUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return listsUsers.size();
    }

    public void setListsUsers(List<UserData> listsUsers){
        this.listsUsers = listsUsers;
        notifyDataSetChanged();
    }
    public class UserViewHolder extends RecyclerView.ViewHolder {

        TextView userId, userDescription;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            userDescription = itemView.findViewById(R.id.tvPost);
            userId = itemView.findViewById(R.id.tvEmail);
        }

        public void bind(UserData userData){

            String userD = String.valueOf(userData.getId());
            Log.d(LOG_TAG, "tHE value of ID: " + userD);
            userId.setText(userD);
            userDescription.setText(userData.getTitle());
        }
    }
}
