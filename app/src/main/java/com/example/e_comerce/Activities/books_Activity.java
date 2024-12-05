package com.example.e_comerce.Activities;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.e_comerce.JavaClasses.productAdapter;
import com.example.e_comerce.JavaClasses.productmodel;
import com.example.e_comerce.R;

import java.util.ArrayList;

public class books_Activity extends AppCompatActivity {

    ListView listView;
    productAdapter adapter;
    String[] title;
    String[] describe;
    int[] icon;
    ArrayList<productmodel> arraylist=new ArrayList<productmodel>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_books);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        title = new String[]{"The Great Gatsby", "The Catcher in the Rye", "1984", "To Kill a Mockingbird"};
        describe= new String[]{"100", "100", "199", "100"};
        icon = new int[]{R.drawable.book1, R.drawable.book2, R.drawable.book3, R.drawable.book4};

        listView = findViewById(R.id.booklist);
        for (int i = 0; i < title.length; i++)
        {
            productmodel myl=new productmodel(title[i],describe[i],icon[i]);
            arraylist.add(myl);
        }
        adapter=new productAdapter(this,arraylist);

        listView.setAdapter(adapter);
    }
}