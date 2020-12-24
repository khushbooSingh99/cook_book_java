package com.example.android.cook.api;
import com.example.android.cook.model.FoodDetail;

import com.example.android.cook.adapter.FoodDetailAdapter;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


//this class has all the utility function for handling api request, receive, parsing and computing

public final class Utils {

    public static final String LOG_TAG = Utils.class.getSimpleName();



    //for performing the random search request from api
    public static FoodDetail fetchRandomFood(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);
        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        // Extract relevant fields from the JSON response and create an FoodDetail object
        FoodDetail food = extractFeatureFromJson(jsonResponse);

        //return the response
        return food;
    }


    //for performing the search foods with given first letter from api
    public static ArrayList<FoodDetail> searchFirstLetter(String requestUrl){

        URL url = createUrl(requestUrl);
        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        // Extract relevant fields from the JSON response and create an ArrayList<FoodDetail> object
        ArrayList<FoodDetail> result = extractListFromJson(jsonResponse);
        //return the response
        return result ;

    }

    //for converting the given string to URL for performing network requests
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }


    //requesting the api via http and then converting the json response to string
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(5000 /* milliseconds */);
            urlConnection.setConnectTimeout(5000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the food JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    //extracting relevant fields from json
    private static FoodDetail extractFeatureFromJson(String foodJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(foodJSON)) {
            return null;
        }

        try {
            JSONObject baseJsonResponse = new JSONObject(foodJSON);
            JSONArray featureArray = baseJsonResponse.getJSONArray("meals");

            // If there are results in the features array
            if (featureArray.length() > 0) {
                // Extract out the first feature (which is a food detail)
                JSONObject meal = featureArray.getJSONObject(0);

                // Extract out the name, mealId, and youTube link
                String number = meal.getString("idMeal");
                String name = meal.getString("strMeal");
                String link = meal.getString("strYoutube");

                // Create a new FoodDetail object
                return new FoodDetail(number, name, link);
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the food JSON results", e);
        }
        return null;
    }

    //string json
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


    //extracting relevant fields from json and creating array of all items
    private static ArrayList<FoodDetail> extractListFromJson(String foodJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(foodJSON)) {
            return null;
        }

        try {
            ArrayList<FoodDetail> result = new ArrayList<FoodDetail>() ;
            JSONObject baseJsonResponse = new JSONObject(foodJSON);
            JSONArray featureArray = baseJsonResponse.getJSONArray("meals");

            // If there are results in the features array
            if (featureArray.length() > 0) {
                // Extract out the first feature

                for(int i=0;i<featureArray.length();i++){
                    JSONObject meal = featureArray.getJSONObject(i);

                    String number = meal.getString("idMeal");
                    String name = meal.getString("strMeal");
                    String link = meal.getString("strYoutube");

                    result.add(new FoodDetail(number, name, link));

                }

                return result;
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the food JSON results", e);
        }
        return null;
    }

}
