package com.example.e_comerce.Activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.e_comerce.JavaClasses.Admin;
import com.example.e_comerce.R;

public class AdminHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_home_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Move the user retrieval and toast outside of the WindowInsetsCompat listener
        Admin loggedInUser = (Admin) getIntent().getSerializableExtra("loggedInUser"); // Note: changed from "loggedInUser" to match the sender

        // Now you can use loggedUser in your activity
        if (loggedInUser != null) {
            // Do something with the logged user, like setting a welcome message
            // For example:
            Toast.makeText(this, "Hello " + loggedInUser.UserName, Toast.LENGTH_SHORT).show();
        }

    }

}