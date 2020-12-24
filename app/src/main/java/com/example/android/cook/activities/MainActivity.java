package com.example.android.cook.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import com.example.android.cook.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button randomFood = (Button) findViewById(R.id.random);

        randomFood.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the view random food is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {Random ACtivity class}
                Intent randomIntent = new Intent(MainActivity.this, RandomActivity.class);

                // Start the new activity
                startActivity(randomIntent);
            }
        });

        Button searchFood = (Button) findViewById(R.id.search_food);

        searchFood.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the search food is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {SearchFirstActivity}
                Intent searchIntent = new Intent(MainActivity.this, SearchFirstActivity.class);

                // Start the new activity
                startActivity(searchIntent);
            }
        });
    }
}