package com.example.rentamate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseQuery;

public class SubmitRating extends AppCompatActivity {

    public static final String TAG = "SubmitRating";
    EditText etSubmitRating;
    Button btnSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit_rating);

        etSubmitRating = findViewById(R.id.etSubmitRating);
        btnSave = findViewById(R.id.btnSave);

        String objId = getIntent().getStringExtra("id");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etSubmitRating.getText().length() == 0){
                    Toast.makeText(SubmitRating.this, "Must be between 1-10", Toast.LENGTH_SHORT).show();
                    return;
                }
                int newRating = Integer.valueOf(etSubmitRating.getText().toString());
                if (newRating < 1 || newRating > 10) {
                    Toast.makeText(SubmitRating.this, "Must be between 1-10", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(SubmitRating.this, "submitted " + newRating, Toast.LENGTH_SHORT).show();
                addRating(objId, newRating);
            }
        });
    }

    public void addRating(String ID, int newRating){
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);

        // Retrieve the object by id
        query.getInBackground(ID, (object, e) -> {
            if (e == null) {
                // Update the fields we want to
                int numOfRates = object.getNumOfRates();
                int currentRating = object.getRating();

                if (numOfRates == 0){
                    object.put("rating", newRating);
                    object.put("numOfRates", numOfRates + 1);
                }

                if (numOfRates != 0){
                    object.put("numOfRates", numOfRates + 1);
                    numOfRates = object.getNumOfRates();
                    int rating = (newRating + currentRating)/numOfRates;
                    object.put("rating", rating);
                }


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
