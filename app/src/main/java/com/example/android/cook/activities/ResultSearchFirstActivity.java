package com.example.android.cook.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.android.cook.R;
import android.content.Intent;
import android.util.Log;
import com.example.android.cook.api.Utils;
import com.example.android.cook.adapter.FoodDetailAdapter;
import com.example.android.cook.model.FoodDetail;

import java.util.ArrayList;
import android.widget.TextView;
import 	android.widget.LinearLayout;
import android.widget.ArrayAdapter;
import android.os.AsyncTask;
import android.widget.ListView;


public class ResultSearchFirstActivity extends AppCompatActivity {

    //url given in api for getting json response for searching all food item with first letter as enetered by user
   public String First_letter_search = "https://www.themealdb.com/api/json/v1/1/search.php?f=";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //set it's activity to activity_result_search_first.. all changes will be reflected there
        setContentView(R.layout.activity_result_search_first);

      //  Intent i = getIntent();

        //getting the letter entered by user from intent
        String text = getIntent().getStringExtra("SEARCH");

        if(text!=null){
            First_letter_search+=text;
        }
        else{
            First_letter_search+="b";
        }


        Log.d("CAUTION",First_letter_search);


        RetrieveData thread= new RetrieveData();
        thread.execute(First_letter_search);

    }


    //updation of UI of activity_result_search_first post receive data from api
    private void updateUi(ArrayList<FoodDetail> food) {
        FoodDetailAdapter adapter = new FoodDetailAdapter(this,food);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }




    private class RetrieveData extends AsyncTask<String,Void,ArrayList<FoodDetail>>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected ArrayList<FoodDetail> doInBackground(String... strings) {
            if(strings.length<1 || strings[0]==null)
                return null;

            //myFood receives the final output we get from request to api
            //we will use this data to update our UI later in onPostExecute
            ArrayList<FoodDetail> myFood = Utils.searchFirstLetter(strings[0]);

            return myFood;
        }

        @Override
        protected void onPostExecute(ArrayList<FoodDetail> food) {
            if(food==null){
                return ;
            }
            updateUi(food);
        }
    }

}
