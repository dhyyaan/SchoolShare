package com.think360.schoolshare.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.think360.schoolshare.model.CommentItem;

import java.util.List;
import com.think360.schoolshare.R;
/**
 * Created by think360user on 1/2/17.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {

    private Context mContext;
    private List<CommentItem> commentList;

    public class MyViewHolder extends RecyclerView.ViewHolder  {
        private TextView id,commenter_name,time,comment;
        private ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            id = (TextView) view.findViewById(R.id.iD);
            commenter_name = (TextView) view.findViewById(R.id.commenter_name);
            time = (TextView) view.findViewById(R.id.time);
            comment = (TextView) view.findViewById(R.id.comment);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }


    public CommentAdapter(Context mContext, List<CommentItem> collectionList) {
        this.mContext = mContext;
        this.commentList = collectionList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comments_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        CommentItem item = commentList.get(position);
        holder.id.setText(item.getId());
        holder.commenter_name.setText(item.getCommenterName());
        holder.time.setText(item.getTime());
        holder.comment.setText(item.getComment());

        // loading album cover using Picasso library


        Picasso.with(mContext)
                .load(item.getThumbnail())
                //.placeholder(R.drawable.placeholder)   // optional
                //	.error(R.drawable.error)      // optional
                //  .resize(70,70)                        // optional
                .into(holder.thumbnail);
    }



    @Override
    public int getItemCount() {
        return commentList.size();
    }


}