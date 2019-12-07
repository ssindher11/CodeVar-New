package com.vit.codevar.ui.HelpFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.vit.codevar.MainActivity;
import com.vit.codevar.R;
import com.vit.codevar.ui.HelpSubmitFragment.HelpSubmitFragment;

import java.util.Objects;

public class HelpFragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_help,container,false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        MainActivity.fragmentTitle.setText(R.string.helpTitle);
        Button ConsumableTeam=view.findViewById(R.id.helpConsumablesTeam);
        Button CodeparkRelated=view.findViewById(R.id.helpCodeparkRelated);
        Button VenueIssues=view.findViewById(R.id.helpVenueIssues);
        Button Others=view.findViewById(R.id.helpOthers);

        ConsumableTeam.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Bundle bundle = new Bundle();
                bundle.putString("Node","ConsumableTeam");
                Fragment fragment=new HelpSubmitFragment();
                fragment.setArguments(bundle);
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.navigationFragment,fragment).addToBackStack(null).commit();
            }
        });

        CodeparkRelated.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Bundle bundle = new Bundle();
                bundle.putString("Node","CodeparkRelated");
                Fragment fragment=new HelpSubmitFragment();
                fragment.setArguments(bundle);
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.navigationFragment,fragment).addToBackStack(null).commit();
            }
        });

        VenueIssues.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Bundle bundle = new Bundle();
                bundle.putString("Node","VenueIssues");
                Fragment fragment=new HelpSubmitFragment();
                fragment.setArguments(bundle);
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.navigationFragment,fragment).addToBackStack(null).commit();
            }
        });

        Others.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Bundle bundle = new Bundle();
                bundle.putString("Node","Others");
                Fragment fragment=new HelpSubmitFragment();
                fragment.setArguments(bundle);
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.navigationFragment,fragment).addToBackStack(null).commit();
            }
        });
    }
}
