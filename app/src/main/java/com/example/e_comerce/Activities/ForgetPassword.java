package com.example.e_comerce.Activites;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.e_comerce.DatabaseAccess.AdminDatabase;
import com.example.e_comerce.DatabaseAccess.AdminDbAccess;
import com.example.e_comerce.DatabaseAccess.CustomerDatabase;
import com.example.e_comerce.DatabaseAccess.CustomerDbAccess;
import com.example.e_comerce.JavaClasses.EmailSender;
import com.example.e_comerce.R;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.security.SecureRandom;

public class ForgetPassword extends AppCompatActivity {
    String verificationCode;
    private TextInputEditText etEmail;
    private MaterialButton btnResetPassword;
    private AdminDbAccess adminDbAccess;
    private CustomerDbAccess customerDbAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        // Edge-to-Edge Setup
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Database Helper
        adminDbAccess = new AdminDbAccess(this);

        customerDbAccess=new CustomerDbAccess(this);


        // Initialize Email Input
        etEmail = findViewById(R.id.etEmail);

        handleResetPassword();
    }

    private void handleResetPassword() {
        btnResetPassword = findViewById(R.id.btnResetPassword);
        btnResetPassword.setOnClickListener(view -> {
            String email = etEmail.getText().toString().trim();
            if (!TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

                  SendVerificationCode(email);
            } else {
                Toast.makeText(ForgetPassword.this, "Please enter a valid email!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void SendVerificationCode(String email) {
        // Check if email exists in database
        if (adminDbAccess.CheckUserExists()customerDbAccess()) {
            // Generate a secure random verification code
             verificationCode = generateVerificationCode();


            // Send email with verification code
            sendPasswordResetEmail(email, verificationCode);

            Toast.makeText(this, "Verification code sent to your email", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No account found with this email", Toast.LENGTH_SHORT).show();
        }
    }

    private String generateVerificationCode() {
        // Generate a 6-digit verification code
        SecureRandom random = new SecureRandom();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    private void sendPasswordResetEmail(String email, String verificationCode) {
        // Send email
        EmailSender.sendVerificationCode(email, verificationCode);
    }
}