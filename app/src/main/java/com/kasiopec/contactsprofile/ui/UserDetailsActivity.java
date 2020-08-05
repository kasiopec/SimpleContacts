package com.kasiopec.contactsprofile.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.github.abdularis.civ.AvatarImageView;
import com.kasiopec.contactsprofile.R;
import com.kasiopec.contactsprofile.database.User;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class UserDetailsActivity extends AppCompatActivity {
    TextView name, emailValue, addressValue, phoneNumberValue;
    AvatarImageView userImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        Objects.requireNonNull(getSupportActionBar()).hide();

        Intent intent = getIntent();
        User passedUser = (User) intent.getSerializableExtra("passedUser");

        name = findViewById(R.id.userDetailsName);
        emailValue = findViewById(R.id.emailValue);
        addressValue = findViewById(R.id.addressValue);
        phoneNumberValue = findViewById(R.id.phoneValue);
        userImage = findViewById(R.id.userDetailsPhoto);

        if (passedUser != null) {
            name.setText(passedUser.getName());
            emailValue.setText(getString(R.string.user_details_email_value, passedUser.getEmail()));
            addressValue.setText(getString(R.string.user_details_address_value, passedUser.getCity(), passedUser.getStreet()));
            phoneNumberValue.setText(getString(R.string.user_details_phone_value, passedUser.getPhoneNumber()));
            //Opening default dial app to call the number
            phoneNumberValue.setOnClickListener(view -> {
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                        Uri.fromParts("tel", phoneNumberValue.getText().toString(),
                                null));
                startActivity(phoneIntent);
            });
            char userNameChar = passedUser.getName().charAt(0);
            if (passedUser.getImageUrl().equals("")) {
                userImage.setState(AvatarImageView.SHOW_INITIAL);
                userImage.setText(String.valueOf(userNameChar));
            } else {
                userImage.setState(AvatarImageView.SHOW_IMAGE);
                Picasso.get().load(passedUser.getImageUrl()).into(userImage);
            }
        }


    }
}