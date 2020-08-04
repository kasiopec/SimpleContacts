package com.kasiopec.contactsprofile.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.abdularis.civ.AvatarImageView;
import com.kasiopec.contactsprofile.R;
import com.kasiopec.contactsprofile.retrofit.UserData;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.widget.TextView;
import java.util.List;


public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.UserViewHolder> {
    Context context;
    List<UserData> usersList;

    public UsersListAdapter(Context context, List<UserData> usersList) {
        this.context = context;
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_item,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.name.setText(usersList.get(position).getName());
        holder.email.setText(usersList.get(position).getEmail());
        char userNameChar = usersList.get(position).getName().charAt(0);
        holder.userPhoto.setText(String.valueOf(userNameChar));

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

     static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView name, email;
         AvatarImageView userPhoto;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.userName);
            email = (TextView) itemView.findViewById(R.id.userEmail);
            userPhoto = (AvatarImageView) itemView.findViewById(R.id.userPhoto);
        }
    }

}
