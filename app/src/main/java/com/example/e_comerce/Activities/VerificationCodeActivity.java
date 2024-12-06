package com.example.e_comerce.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.e_comerce.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class VerificationCodeActivity extends AppCompatActivity {
    private TextInputEditText etVerificationCode;
    private MaterialButton btnVerify;
    private String originalVerificationCode;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code);

        // Edge-to-Edge Setup
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Retrieve verification code and email from previous activity
        originalVerificationCode = getIntent().getStringExtra("VERIFICATION_CODE");
        userEmail = getIntent().getStringExtra("USER_EMAIL");

        // Initialize Views
        etVerificationCode = findViewById(R.id.etVerificationCode);
        btnVerify = findViewById(R.id.btnVerify);

        // Setup Verify Button
        btnVerify.setOnClickListener(view -> verifyCode());

        // Setup Resend Code
        findViewById(R.id.tvResendCode).setOnClickListener(view -> resendVerificationCode());
    }

    private void verifyCode() {
        String enteredCode = etVerificationCode.getText().toString().trim();

        // Validate input
        if (TextUtils.isEmpty(enteredCode) || enteredCode.length() != 6) {
            Toast.makeText(this, "Please enter a valid 6-digit code", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if entered code matches the original code
        if (enteredCode.equals(originalVerificationCode)) {
            // Code is correct, proceed to reset password
            Toast.makeText(this, "correct verification code", Toast.LENGTH_SHORT).show();

            Intent resetPasswordIntent = new Intent(this, ResetPasswordActivity.class);
            resetPasswordIntent.putExtra("USER_EMAIL", userEmail);
            startActivity(resetPasswordIntent);
            finish();

        } else {
            Toast.makeText(this, "Incorrect verification code", Toast.LENGTH_SHORT).show();
        }
    }

    private void resendVerificationCode() {
        // You'll need to modify the ForgetPassword activity to send a new verification code
        // This could involve calling the original method to generate and send a new code
        Intent intent = new Intent(this, ForgetPassword.class);
        intent.putExtra("RESEND_CODE", true);
        intent.putExtra("USER_EMAIL", userEmail);
        startActivity(intent);
        finish();
    }
}