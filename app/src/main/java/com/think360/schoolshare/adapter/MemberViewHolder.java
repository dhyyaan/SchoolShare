package com.think360.schoolshare.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.think360.schoolshare.R;

/**
 * Created by think360user on 17/1/17.
 */
public class MemberViewHolder extends RecyclerView.ViewHolder {
    public TextView memberName;
    public MemberViewHolder(View itemView) {
        super(itemView);
        memberName = (TextView) itemView.findViewById(R.id.memberName);
    }
}
