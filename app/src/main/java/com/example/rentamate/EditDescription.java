package com.example.rentamate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseQuery;

public class EditDescription extends AppCompatActivity {

    public static final String TAG = "EditDescription";
    EditText etEditDescription;
    Button btnSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_description);

        etEditDescription = findViewById(R.id.etEditDescription);
        btnSave = findViewById(R.id.btnSave);

        String objId = getIntent().getStringExtra("id");


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String description = etEditDescription.getText().toString();
                if (description.isEmpty()) {
                    Toast.makeText(EditDescription.this, "Description cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(EditDescription.this, "Description save successful!", Toast.LENGTH_SHORT).show();
                updateBio(objId, description);
            }
        });
    }

    public void updateBio(String ID, String description) {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);

        // Retrieve the object by id
        query.getInBackground(ID, (object, e) -> {
            if (e == null) {
                // Update the fields we want to
                object.put("description", description);

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
