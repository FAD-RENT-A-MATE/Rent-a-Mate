package com.example.rentamate;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class CreateProfile extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback{

    public static final String TAG = "CreateProfile";
    private TextView userName;
    private ImageView picImage;
    private EditText etUserDescription;
    private RatingBar homeRatingBar;
    private Button btnSelectPic;
    private Button btnConfirm;

    private File photoFile;
    public String photoFileName = "photo.jpg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_profile);

        userName = findViewById(R.id.userName);
        picImage = findViewById(R.id.picImage);
        homeRatingBar = findViewById(R.id.homeRatingBar);
        etUserDescription = findViewById(R.id.etUserDescription);
        btnSelectPic = findViewById(R.id.btnSelectPic);
        btnConfirm = findViewById(R.id.btnConfirm);

        String objId = getIntent().getStringExtra("id");


        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String description = etUserDescription.getText().toString();
                float rating = homeRatingBar.getRating();
                photoFile = new  File(photoFileName);
                if (description.isEmpty()) {
                    Toast.makeText(CreateProfile.this, "Description cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (photoFile == null || picImage.getDrawable() == null) {
                    Toast.makeText(CreateProfile.this, "There is no image!", Toast.LENGTH_SHORT).show();
                    return;
                }
                ParseUser currentUser = ParseUser.getCurrentUser();
                createPost(description, currentUser, photoFile, Math.round(rating));
            }
        });

        btnSelectPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermission();
            }
        });



    }

        private static final int REQUEST_WRITE_PERMISSION = 786;

        @Override
        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (requestCode == REQUEST_WRITE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);
            }
        }



        private void requestPermission() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
            } else {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);
            }
        }

    // Returns the File for a photo stored on disk given the fileName
    public File getPhotoFileUri(String fileName) {
        // Get safe storage directory for photos
        // Use `getExternalFilesDir` on Context to access package-specific directories.
        // This way, we don't need to request external read/write runtime permissions.
        File mediaStorageDir = new File(CreateProfile.this.getExternalFilesDir(Environment.DIRECTORY_PICTURES), TAG);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
            Log.d(TAG, "failed to create directory");
        }

        // Return the file target for the photo based on filename
        return new File(mediaStorageDir.getPath() + File.separator + fileName);
    }

    public void createPost(String description, ParseUser currentUser, File photoFile, int rating) {

        Post post = new Post();
        post.setDescription(description);
        ParseFile file = new ParseFile(photoFile);

                post.setImage(file);
                post.setUser(currentUser);
                 post.setRating(rating);
                 post.setNumOfRates(0);

                post.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e != null) {
                            Log.e(TAG, "Error while saving", e);
                            Toast.makeText(CreateProfile.this, "Error while saving!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Log.i(TAG, "Profile was created!!");
                        Toast.makeText(CreateProfile.this, "Post save was successful!!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(CreateProfile.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                });



        // Saves the new object.
        // Notice that the SaveCallback is totally optional!
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                photoFileName = getPath(imageUri);
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                picImage.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(CreateProfile.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(CreateProfile.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }
    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(projection[0]);
        String filePath = cursor.getString(columnIndex);

        cursor.close();
        return filePath;
    }
}
