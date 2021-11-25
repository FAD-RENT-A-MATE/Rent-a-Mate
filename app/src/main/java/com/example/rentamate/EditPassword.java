package com.example.rentamate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseUser;

public class EditPassword extends AppCompatActivity {

    public static final String TAG = "EditPassword";
    EditText etEditPassword;
    Button btnSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_password);

        etEditPassword = findViewById(R.id.etEditPassword);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = etEditPassword.getText().toString();
                if (password.isEmpty()) {
                    Toast.makeText(EditPassword.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                updatePassword(password);
            }
        });
    }

    public void updatePassword(String password) {
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // Other attributes than "email" will remain unchanged!
            currentUser.put("password", password);

            // Saves the object.
            currentUser.saveInBackground(e -> {
                if(e==null){
                    //Save successful
                    Toast.makeText(this, "Save Successful", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    // Something went wrong while saving
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
