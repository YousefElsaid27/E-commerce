package com.example.age_calculate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button age=(Button) findViewById(R.id.button);


        age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate_age c=new calculate_age();
                EditText year=(EditText)findViewById(R.id.editTextText);

                int num=Integer.parseInt(year.getText().toString());
                c.set_age(num);
                int y=c.age();

                EditText output=(EditText)findViewById(R.id.editTextText2);
                output.setText(String.valueOf(y));
            }
        });

    }
}