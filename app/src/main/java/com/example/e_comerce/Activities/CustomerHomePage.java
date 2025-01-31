package com.example.e_comerce.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.e_comerce.JavaClasses.Cateogry;
import com.example.e_comerce.JavaClasses.Customer;
import com.example.e_comerce.JavaClasses.Product;
import com.example.e_comerce.JavaClasses.myadapter;
import com.example.e_comerce.JavaClasses.mylist;
import com.example.e_comerce.R;

import java.util.ArrayList;
import java.util.List;

public class CustomerHomePage extends AppCompatActivity {

    ListView listView;
    myadapter adapter;
    String[] title;
    int[] icon;

    List<Cateogry> ListOfCateogry;
    ArrayList<mylist> arraylist=new ArrayList<mylist>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_home_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListOfCateogry=new ArrayList<>();
        // Move the user retrieval and toast outside of the WindowInsetsCompat listener
        Customer loggedInUser = (Customer) getIntent().getSerializableExtra("loggedInUser"); // Note: changed from "loggedInUser" to match the sender

        // Now you can use loggedUser in your activity
        if (loggedInUser != null) {
            // Do something with the logged user, like setting a welcome message
            // For example:
            Toast.makeText(this, "Hello " + loggedInUser.UserName + loggedInUser.dateOfBirth.toString(), Toast.LENGTH_SHORT).show();
        }

        //Test Data "one category and one product in it Create Loop if you Want to set more " /////////////////////////////////////////////////////////////////////////

        //Get the product pic from its Location
        Bitmap ProductImage = BitmapFactory.decodeFile("/path/to/your/product.jpg");

        // Assign The products data
        Product Product=new Product(1,"First product Name",555.0,5,ProductImage);

        //Fill the list of products
        List<Product> ListOfProduct=new ArrayList<>();
        ListOfProduct.add(Product);

        //Get the Cateogry pic from its Location
        Bitmap CateogryImage = BitmapFactory.decodeFile("/path/to/your/cateogry.jpg");

        //assign cateogry data
        Cateogry cateogry=new Cateogry("First Cateogry Name",ListOfProduct,CateogryImage);

        //Fill the list of Cateogry

        ListOfCateogry.add(cateogry);


        //Finally u have this variable "ListOfCateogry" contain all data to be shown . Use only this variable because this is which i will pass to u from the data base



        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //how to show  image In The Image View

        //ImageView imageView = findViewById(R.id.yourImageView);

        //imageView.setImageBitmap(ListOfCateogry.get(0).Products.get(0).Image  );


        title = new String[]{"books", "Electronics", "Fashion", "Home", "Sports","Toys"};
        icon = new int[]{R.drawable.books, R.drawable.electronics, R.drawable.fashion, R.drawable.home_kitchen, R.drawable.sports_outdoors,R.drawable.toys_games};

        listView = findViewById(R.id.list);
        for (int i = 0; i < title.length; i++)
        {
            mylist myl=new mylist(title[i],icon[i]);
            arraylist.add(myl);
        }
        adapter=new myadapter(this,arraylist);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                mylist selectedItem = (mylist) adapter.getItem(position);


                String itemTitle = selectedItem.getTitle();

                if (itemTitle.equals("books")) {

                    Intent temp_intent = new Intent(CustomerHomePage.this, books_Activity.class);
                    startActivity(temp_intent);
                } else if (itemTitle.equals("Electronics")) {

                    Intent password_intent = new Intent(CustomerHomePage.this, Electronics_Activity.class);
                    startActivity(password_intent);
                } else if (itemTitle.equals("Fashion")) {

                    Intent light_intent = new Intent(CustomerHomePage.this, Fashion_Activity.class);
                    startActivity(light_intent);
                } else if (itemTitle.equals("Home")) {

                    Intent fan_intent = new Intent(CustomerHomePage.this, Home_Activity.class);
                    startActivity(fan_intent);
                } else if (itemTitle.equals("Sports")) {

                    Intent lcd_intent = new Intent(CustomerHomePage.this, Sports_Activity.class);
                    startActivity(lcd_intent);
                }
                else if (itemTitle.equals("Toys")) {

                    Intent lcd_intent = new Intent(CustomerHomePage.this, Toys_Activity.class);
                    startActivity(lcd_intent);
                }
            }
        });

    }
}