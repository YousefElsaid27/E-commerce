package com.example.e_comerce.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_comerce.R;
import com.google.android.material.card.MaterialCardView;

public class SignUp extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        MaterialCardView cardAdmin = findViewById(R.id.cardAdmin);
        MaterialCardView cardCustomer = findViewById(R.id.cardCustomer);
        ImageButton btnBack = findViewById(R.id.btnBack);

        cardAdmin.setOnClickListener(v -> navigateToAdminSignUp());
        cardCustomer.setOnClickListener(v -> navigateToCustomerSignUp());
        btnBack.setOnClickListener(v -> onBackPressed());
    }

    private void navigateToAdminSignUp() {
        Intent intent = new Intent(this, AdminSignUp.class);
        startActivity(intent);
    }

    private void navigateToCustomerSignUp() {
        Intent intent = new Intent(this, CustomerSignUp.class);
        startActivity(intent);
    }
}
