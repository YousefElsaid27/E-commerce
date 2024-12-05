package com.example.e_comerce.Activites;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.e_comerce.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class ForgetPassword extends AppCompatActivity {

    private TextInputEditText etEmail;
    MaterialButton btnResetPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        // Edge-to-Edge Setup (If Needed)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Email Input
        etEmail = findViewById(R.id.etEmail);


        handleResetPassword();


    }

    private void handleResetPassword() {
         btnResetPassword = findViewById(R.id.btnResetPassword);
        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString().trim();
                if (!email.isEmpty()) {

                    SendVerificationCode(email) ;
                    Toast.makeText(ForgetPassword.this, "Email: " + email, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ForgetPassword.this, "Please enter a valid email!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void SendVerificationCode(String email) {

    }
}
