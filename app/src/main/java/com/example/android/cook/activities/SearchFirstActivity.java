package com.example.android.cook.activities;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.android.cook.R;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.util.Log;

public class SearchFirstActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set it's activity to activity_search_first.. all changes will be reflected there
        setContentView(R.layout.activity_search_first);

        //to get the user input
        EditText letterEntered = (EditText) findViewById(R.id.enter_one_letter);


        Button goSearch = (Button) findViewById(R.id.search_first);
        goSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent searchIntent = new Intent(SearchFirstActivity.this, ResultSearchFirstActivity.class);

                //the user input data in editText field for further processing
                searchIntent.putExtra("SEARCH", letterEntered.getText().toString());
                // Start the new activity
                startActivity(searchIntent);

                //just for error checks
                Log.d("FIRST",letterEntered.getText().toString());
            }
        });

    }


}
