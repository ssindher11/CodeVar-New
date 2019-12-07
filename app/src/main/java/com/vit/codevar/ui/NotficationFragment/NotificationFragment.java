package com.vit.codevar.ui.NotficationFragment;

import android.app.Notification;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vit.codevar.MainActivity;
import com.vit.codevar.R;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NotificationFragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_notification,container,false);
    }
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        MainActivity.fragmentTitle.setText(R.string.notificationsTitle);

        final List<NotificationRVData> RVData =new ArrayList<>();
        final DatabaseReference NotRef = FirebaseDatabase.getInstance().getReference().child("Notifications");
        NotRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    System.out.println(ds.getValue());
                    final NotificationRVData tempdat=ds.getValue(NotificationRVData.class);
                    RVData.add(tempdat);
                }
                Collections.reverse(RVData);
                RecyclerView recyclerView =view.findViewById(R.id.notificationRecyclerView);
                NotificationRecyclerViewAdapter adapter = new NotificationRecyclerViewAdapter(getContext(),RVData);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
            }
        });
    }
}
