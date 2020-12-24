package com.example.android.cook.adapter;

import com.example.android.cook.model.FoodDetail;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.TextView;

import android.view.LayoutInflater;
import java.util.ArrayList;
import android.content.Context;
import android.widget.ImageView;
import android.app.Activity;
import android.media.MediaPlayer;
import android.widget.Button;
import com.example.android.cook.R;

//adapter used for efficient display of listItems of the foods after search result in hand

public class FoodDetailAdapter extends ArrayAdapter<FoodDetail>  {
    public FoodDetailAdapter(Context context, ArrayList<FoodDetail> objects) {
        super(context, 0, objects);
    }
    // @NonNull

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // return super.getView(position, convertView, parent);
        View listItem = convertView;

        if(listItem == null){
            listItem= LayoutInflater.from(getContext()).inflate(R.layout.list_view,parent,false);
        }

        //accessing the different views of the list view that makes up the activity_result_search_first
        FoodDetail food=getItem(position);

        TextView name = (TextView) listItem.findViewById(R.id.food_name);
        name.setText(food.getName());

        TextView id = (TextView) listItem.findViewById(R.id.food_id);
        id.setText(food.getId());

        TextView uTube = (TextView) listItem.findViewById(R.id.youTube_link);
        uTube.setText(food.getLink());

        return listItem;

    }
}
