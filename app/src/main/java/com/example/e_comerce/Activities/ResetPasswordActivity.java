package com.example.e_comerce.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.e_comerce.DatabaseAccess.DbAdminAccses;
import com.example.e_comerce.DatabaseAccess.DbCustomerAccses;
import com.example.e_comerce.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class ResetPasswordActivity extends AppCompatActivity {
    private TextInputEditText etNewPassword, etConfirmPassword;
    private MaterialButton btnResetPassword;
    private String userEmail;
    private DbAdminAccses adminDbAccess;
    private DbCustomerAccses customerDbAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        // Edge-to-Edge Setup
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Database Helpers
        adminDbAccess = new DbAdminAccses(this);
        customerDbAccess = new DbCustomerAccses(this);

        // Get user email from previous activity
        userEmail = getIntent().getStringExtra("USER_EMAIL");

        // Initialize Views
        etNewPassword = findViewById(R.id.etNewPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnResetPassword = findViewById(R.id.btnResetPassword);

        // Setup Reset Password Button
        btnResetPassword.setOnClickListener(view -> resetPassword());
    }

    private void resetPassword() {
        String newPassword = etNewPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();


        // Determine if it's an admin or customer and update password accordingly
        boolean isUpdated = false;
        if (adminDbAccess.CheckUserExists(userEmail)) {
            isUpdated = adminDbAccess.updatePassword(userEmail, newPassword);
        } else if (customerDbAccess.CheckUserExists(userEmail)) {
            isUpdated = customerDbAccess.updatePassword(userEmail, newPassword);
        }

        if (isUpdated) {
            Toast.makeText(this, "Password reset successfully", Toast.LENGTH_SHORT).show();
            Intent Login = new Intent(this, LoginPage.class);

            startActivity(Login);
            finish(); // Close the activity
        } else {
            Toast.makeText(this, "Failed to reset password", Toast.LENGTH_SHORT).show();
        }
    }
}