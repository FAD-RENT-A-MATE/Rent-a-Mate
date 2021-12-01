package Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.rentamate.CreateProfile;
import com.example.rentamate.EditDescription;
import com.example.rentamate.EditPassword;
import com.example.rentamate.LoginActivity;
import com.example.rentamate.R;
import com.parse.ParseUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    public static final String TAG = "SettingsFragment";
    Button btnPassword;
    Button btnLogout;
    Button btnCreateProfile;





    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);

    }

    // CRUD - CREATE | READ | UPDATE | DELETE

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnPassword = view.findViewById(R.id.btnPassword);
        btnLogout = view.findViewById(R.id.btnLogout);
        btnCreateProfile = view.findViewById(R.id.btnCreateProfile);


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logOut();
                Intent i = new Intent(getContext(), LoginActivity.class);
                startActivity(i);
            }
        });

        btnPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), EditPassword.class);
                startActivity(i);
            }
        });

        btnCreateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), CreateProfile.class);
                startActivity(i);
            }
        });
    }
}