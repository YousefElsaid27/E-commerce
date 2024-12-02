package com.example.e_comerce.Activites;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Start LoginPage activity
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);

        // Finish MainActivity so it's not in the back stack
        finish();
    }
}
//new 