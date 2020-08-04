package com.kasiopec.contactsprofile.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.abdularis.civ.AvatarImageView;
import com.kasiopec.contactsprofile.R;
import com.kasiopec.contactsprofile.database.User;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.widget.TextView;

import java.util.List;


public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.UserViewHolder> {
    Context context;
    List<User> usersList;
    ItemClickHandler clickHandler;

    public UsersListAdapter(Context context, List<User> usersList, ItemClickHandler clickHandler) {
        this.clickHandler = clickHandler;
        this.context = context;
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.name.setText(usersList.get(position).getName());
        holder.email.setText(usersList.get(position).getEmail());
        char userNameChar = usersList.get(position).getName().charAt(0);

        if (usersList.get(position).getImageUrl().equals("")) {
            holder.userPhoto.setText(String.valueOf(userNameChar));
        } else {
            Picasso.get().load(usersList.get(position).getImageUrl()).into(holder.userPhoto);
        }
        holder.userCard.setOnClickListener(view -> clickHandler.onItemClicked(usersList.get(position)));
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public void updateData(List<User> users) {
        usersList = users;
        notifyDataSetChanged();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView name, email;
        AvatarImageView userPhoto;
        CardView userCard;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.userDetailsName);
            email = itemView.findViewById(R.id.userDetailsEmail);
            userPhoto = itemView.findViewById(R.id.userDetailsPhoto);
            userCard = itemView.findViewById(R.id.userCard);

        }
    }

}
