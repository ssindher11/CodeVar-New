package com.vit.codevar.ui.NotficationFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vit.codevar.R;

import java.util.List;

public class NotificationRecyclerViewAdapter extends RecyclerView.Adapter<NotificationRecyclerViewAdapter.ViewHolder>
{
    private List<NotificationRVData> mData;
    private Context context;

    // data is passed into the constructor
    NotificationRecyclerViewAdapter(Context context, List<NotificationRVData> data)
    {
        this.context=context;
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_rv_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        NotificationRVData Rvdata = mData.get(position);
        holder.notificationTVRVTitle.setText(Rvdata.message);
        holder.notificationTVRVTime.setText(Rvdata.time);
    }

    // total number of rows
    @Override
    public int getItemCount()
    {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView notificationTVRVTitle;
        TextView notificationTVRVTime;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            notificationTVRVTitle = itemView.findViewById(R.id.notificationTVRVTitle);
            notificationTVRVTime = itemView.findViewById(R.id.notificationTVRVTime);
        }
    }

}
