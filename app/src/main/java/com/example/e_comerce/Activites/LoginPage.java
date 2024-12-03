


package com.example.e_comerce.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
///import
import com.example.e_comerce.DatabaseAccess.AdminDbAccess;
import com.example.e_comerce.DatabaseAccess.CustomerDbAccess;
import com.example.e_comerce.DatabaseAccess.RememberedUser;
import com.example.e_comerce.DatabaseAccess.RememberedListAccess;
import com.example.e_comerce.JavaClasses.SignIn;
import com.example.e_comerce.JavaClasses.User;
import com.example.e_comerce.R;
import com.google.android.material.checkbox.MaterialCheckBox;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends AppCompatActivity {

    private List<RememberedUser> RememberUserList;
    private List<String> usernames;

    AutoCompleteTextView usernameEditText;
    EditText passwordEditText;
    Button loginButton;
    RadioGroup userTypeRadioGroup;
    SignIn signIn;
    boolean isAuthenticated;
    String username;
    String password;
    User loggedInUser;
    RememberedListAccess rememberedUserManager;
    MaterialCheckBox rememberMeCheckbox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Initialize credentials
        initializeRememberList();
        initializeUIComponents();
        //log in
        loginButton.setOnClickListener(view -> handleLogin());
    }




    private void initializeRememberList() {
        // Initialize the list of credentials
        rememberedUserManager=new RememberedListAccess();
        RememberUserList =rememberedUserManager.GetRememberedUsers();
        // Populate usernames list
        usernames = new ArrayList<>();
        for (RememberedUser User : RememberUserList) {
            usernames.add(User.UserName);
        }
    }


    private void initializeUIComponents() {
        usernameEditText = findViewById(R.id.UsernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        userTypeRadioGroup = findViewById(R.id.userTypeRadioGroup);
        rememberMeCheckbox = findViewById(R.id.rememberMeCheckbox);

        // Set up AutoComplete for usernames
        ArrayAdapter<String> usernameAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                usernames
        );
        usernameEditText.setAdapter(usernameAdapter);
        usernameEditText.setInputType(android.text.InputType.TYPE_CLASS_TEXT);
        // Set up listener for username selection
        usernameEditText.setOnItemClickListener((parent, view, position, id) -> {
            // Get the selected username
            String selectedUsername = (String) parent.getItemAtPosition(position);
            // Find and set the corresponding password
            for (RememberedUser User : RememberUserList) {
                if (User.UserName.equals(selectedUsername)) {
                    passwordEditText.setText(User.Password);
                    break;
                }
            }
        });
    }




    private void handleLogin() {
        // Get input text from the fields
        username = usernameEditText.getText().toString().trim();
        password = passwordEditText.getText().toString().trim();
        // Check if both fields are filled
        if (!username.isEmpty() && !password.isEmpty()) {
            int selectedRadioId= userTypeRadioGroup.getCheckedRadioButtonId();
            // Initialize SignIn based on selected user type
            if (selectedRadioId == R.id.CustomerRadioButton) {
                signIn = new SignIn(new CustomerDbAccess());
            } else {
                signIn = new SignIn(new AdminDbAccess());
            }
            SigningIn(signIn,selectedRadioId);
        } else {
            // Show toast message if one or both fields are empty
            Toast.makeText(LoginPage.this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
        }

    }

    private void SigningIn(SignIn signIn, int selectedRadioId) {

        // Authenticate the user
        loggedInUser = signIn.Authenticate(username, password);
        if (loggedInUser != null) {
            // User authentication successful
            HandleRememberMe();
            navigateToHomePage(selectedRadioId);
        }else
            Toast.makeText(LoginPage.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
    }

    private void HandleRememberMe() {
        if (rememberMeCheckbox.isChecked()) {
            // Push the user to be remembered in future
            rememberedUserManager.AddRememberedUser(loggedInUser);
            Toast.makeText(LoginPage.this, "You will be remembered!", Toast.LENGTH_SHORT).show();
        }
    }


    private void navigateToHomePage(int selectedId) {
        Intent intent;
        if (selectedId == R.id.CustomerRadioButton) {
            intent = new Intent(this, CustomerHomePage.class);
            intent.putExtra("loggedInUser", loggedInUser);
        } else {
            intent = new Intent(this, AdminHomePage.class);
            intent.putExtra("loggedInUser", loggedInUser);
        }
        startActivity(intent);
    }
}

