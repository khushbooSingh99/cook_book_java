package com.example.android.cook.activities;
import com.example.android.cook.model.FoodDetail;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.android.cook.api.Utils;
import com.example.android.cook.adapter.FoodDetailAdapter;

import android.util.Log;
import android.os.Bundle;
import com.example.android.cook.R;

import java.util.ArrayList;
import android.widget.TextView;
import 	android.widget.LinearLayout;
import android.widget.ArrayAdapter;
import android.os.AsyncTask;
import android.widget.ListView;

public class RandomActivity extends AppCompatActivity {

    //url given in api for getting json response for random food display
    public static final String RANDOM_REQUEST_URL =
            "https://www.themealdb.com/api/json/v1/1/random.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set it's activity to activity random.. all changes will be reflected there
        setContentView(R.layout.activity_random);


        RetrieveData thread=new RetrieveData();
        thread.execute(RANDOM_REQUEST_URL);

    }


    //updation of UI of activity_random
    private void updateUi(FoodDetail food) {
        TextView titleTextView = (TextView) findViewById(R.id.title);
        titleTextView.setText(food.getName());

        TextView linkTV= (TextView) findViewById(R.id.linkk);
        linkTV.setText(food.getLink());

        TextView idTV = (TextView) findViewById(R.id.num);
        idTV.setText(food.getId());
    }


    //computation runs on a background thread and result is published on the UI thread onPostExecute
    private class RetrieveData extends AsyncTask<String,Void,FoodDetail>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }


        @Override
        protected FoodDetail doInBackground(String... strings) {
            if(strings.length<1 || strings[0]==null)
                return null;

            //myFood receives the final output we get from request to api
            //we will use this data to update our UI later
            FoodDetail myFood = Utils.fetchRandomFood(strings[0]);

            return myFood;
        }

        @Override
        protected void onPostExecute(FoodDetail food) {
            if(food==null)
                return ;

            //goes to updation of UI
            updateUi(food);

        }
    }
}
