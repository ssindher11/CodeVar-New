package com.vit.codevar.ui.HelpSubmitFragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vit.codevar.LoginActivity;
import com.vit.codevar.R;
import com.vit.codevar.ui.HelpFragment.HelpFragment;

import java.util.Objects;


public class HelpSubmitFragment extends Fragment
{
    private CoordinatorLayout view2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_help_submit,container,false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = this.getArguments();
        String tempNode;
        String tempEmail=LoginActivity.email;
        final String userEmail=tempEmail.replace('.', '_');
        view2 = view.findViewById(R.id.layout);

        if(bundle != null)
        {
            tempNode=bundle.getString("Node");
        }
        else
        {
            tempNode="";
        }
        final String Node=tempNode;
        final String[] response = new String[1];
        final EditText helpProblem=view.findViewById(R.id.helpProblemEditText);
        Button submitProblem=view.findViewById(R.id.helpProblemSubmit);

        submitProblem.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(helpProblem.getText().toString().trim().equals(""))
                {
                    Snackbar.make(view2,"Kindly enter your problem",Snackbar.LENGTH_LONG)
                            .setActionTextColor(Color.WHITE).show();
                }
                else
                {
                    String Issue=helpProblem.getText().toString().trim();
                    DatabaseReference issueRef= FirebaseDatabase.getInstance().getReference().child(Node);
                    try
                    {
                        response[0] = "Request submitted";
                        issueRef.child(userEmail).setValue(Issue);

                        Snackbar.make(view2, response[0],Snackbar.LENGTH_LONG)
                                .setActionTextColor(Color.WHITE).show();

                        new CountDownTimer(3000,1000)
                        {
                            @Override
                            public void onTick(long l) {

                            }

                            @Override
                            public void onFinish() {
                                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.navigationFragment,new HelpFragment()).addToBackStack(null).commit();
                            }
                        }.start();
                    }
                    catch (Exception e)
                    {
                        response[0] = e.toString();
                        Snackbar.make(view2, response[0],Snackbar.LENGTH_LONG)
                                .setActionTextColor(Color.WHITE).show();
                    }


                }
            }
        });
    }
}