package com.think360.schoolshare.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.think360.schoolshare.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by think360user on 16/1/17.
 */

public class AddChapterAdapter extends RecyclerView.Adapter<AddChapterAdapter.MyViewHolder> {

    private List<String> al ;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder  {
        private TextView addCity;


        public MyViewHolder(View view) {
            super(view);

            addCity = (TextView) view.findViewById(R.id.addCity);


        }


    }


    public AddChapterAdapter(Context mContext, List<String> chapterList) {
        this.mContext = mContext;

        this.al = chapterList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.add_chapter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {




        holder.addCity.setText(al.get(position));


    }



    @Override
    public int getItemCount() {
        return al.size();
    }


}