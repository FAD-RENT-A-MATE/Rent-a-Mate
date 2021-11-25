package com.example.rentamate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseQuery;
import com.parse.ParseUser;

public class SelectUser extends AppCompatActivity {

    public static final String TAG = "SelectUser";
    Button btnConfirm;
    Button btnCancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_user);

        btnConfirm = findViewById(R.id.btnConfirm);
        btnCancel = findViewById(R.id.btnCancel);

        String objId = getIntent().getStringExtra("id");


        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser currentUser = ParseUser.getCurrentUser();
                addSelectedBy(objId, currentUser);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void addSelectedBy(String ID, ParseUser user) {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);

        // Retrieve the object by id
        query.getInBackground(ID, (object, e) -> {
            if (e == null) {
                if (object.getSelectedBy() != null){
                    Toast.makeText(this, "User has already been selected: choose another", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Update the fields we want to
                object.put("selectedBy", user);
                Toast.makeText(this, "User has been selected", Toast.LENGTH_SHORT).show();

                // All other fields will remain the same
                object.saveInBackground();
                finish();
            } else {
                // something went wrong
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
