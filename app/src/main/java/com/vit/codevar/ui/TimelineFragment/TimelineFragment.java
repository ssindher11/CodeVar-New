package com.vit.codevar.ui.TimelineFragment;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.txusballesteros.widgets.FitChart;
import com.txusballesteros.widgets.FitChartValue;
import com.vit.codevar.MainActivity;
import com.vit.codevar.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;


public class TimelineFragment extends Fragment
{
    private FitChart timelineChart;
    private Long startTime, endTime, currentTime, doneTime;
    private TextView currentRoundName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_timeline,container,false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity.fragmentTitle.setText(R.string.timelineTitle);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int Width = displayMetrics.widthPixels;
        int Height = displayMetrics.heightPixels;

        final LinearLayout l1=view.findViewById(R.id.timeline1);
        final LinearLayout l2=view.findViewById(R.id.timeline2);
        final LinearLayout l3=view.findViewById(R.id.timeline3);
        final LinearLayout l4=view.findViewById(R.id.timeline4);
        final LinearLayout l5=view.findViewById(R.id.timeline5);
        final LinearLayout l6=view.findViewById(R.id.timeline6);
        final LinearLayout l7=view.findViewById(R.id.timeline7);
        final LinearLayout l8=view.findViewById(R.id.timeline8);
        final LinearLayout l9=view.findViewById(R.id.timeline9);
        final LinearLayout l10=view.findViewById(R.id.timeline10);
        final LinearLayout l11=view.findViewById(R.id.timeline11);
        final LinearLayout l12=view.findViewById(R.id.timeline12);
        final LinearLayout l13=view.findViewById(R.id.timeline13);

        l1.setLayoutParams(new LinearLayout.LayoutParams(Width/3,Height/10));
        l2.setLayoutParams(new LinearLayout.LayoutParams(Width/3,Height/10));
        l3.setLayoutParams(new LinearLayout.LayoutParams(Width/3,Height/10));
        l4.setLayoutParams(new LinearLayout.LayoutParams(Width/3,Height/10));
        l5.setLayoutParams(new LinearLayout.LayoutParams(Width/3,Height/10));
        l6.setLayoutParams(new LinearLayout.LayoutParams(Width/3,Height/10));
        l7.setLayoutParams(new LinearLayout.LayoutParams(Width/3,Height/10));
        l8.setLayoutParams(new LinearLayout.LayoutParams(Width/3,Height/10));
        l9.setLayoutParams(new LinearLayout.LayoutParams(Width/3,Height/10));
        l10.setLayoutParams(new LinearLayout.LayoutParams(Width/3,Height/10));
        l11.setLayoutParams(new LinearLayout.LayoutParams(Width/3,Height/10));
        l12.setLayoutParams(new LinearLayout.LayoutParams(Width/3,Height/10));
        l13.setLayoutParams(new LinearLayout.LayoutParams(Width/3,Height/10));

        timelineChart = view.findViewById(R.id.timelineChart);
        currentRoundName = view.findViewById(R.id.currentRoundName);
        timelineChart.setMinValue(0);
        timelineChart.setMaxValue(1590);
        startTime = 1575721800000L; //7 DEC 2019 6:00 PM
        endTime = 1575817200000L; //8 DEC 2019 8:30 PM

        final Resources resources = getResources();
        final Collection<FitChartValue> values = new ArrayList<>();

        if(Calendar.getInstance().getTimeInMillis() <= endTime)
        {
            new CountDownTimer(endTime-startTime,60000) {
                @Override
                public void onTick(long l) {
                    currentTime = Calendar.getInstance().getTimeInMillis();
                    doneTime = (currentTime-startTime)/(1000*60);
                    Log.i("INFO",String.valueOf(doneTime));

                    if(doneTime < 0)
                    {
                        //Event Not Started
                        values.add(new FitChartValue(150f, resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.funBreakInactive)));
                        values.add(new FitChartValue(30f, resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakInactive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakInactive)));
                        values.add(new FitChartValue(240f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(30f, resources.getColor(R.color.eliminationInactive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.funBreakInactive)));
                        values.add(new FitChartValue(300f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(210f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakInactive)));

                        TextView UpNext=view.findViewById(R.id.timelineUpNext);
                        UpNext.setVisibility(View.VISIBLE);
                        currentRoundName.setText(R.string.defaultRoundName);

                        TextView CurTV=(TextView) l1.getChildAt(0);
                        CurTV.setTextColor(Color.parseColor("#E5AB02"));
                        l2.getParent().requestChildFocus(l2,l2);
                    }
                    else if(doneTime >=0  && doneTime <=150 )
                    {
                        currentRoundName.setText(R.string.participantsArrival);
                        values.add(new FitChartValue(doneTime,resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(150-doneTime, resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.funBreakInactive)));
                        values.add(new FitChartValue(30f, resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakInactive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakInactive)));
                        values.add(new FitChartValue(240f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(30f, resources.getColor(R.color.eliminationInactive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.funBreakInactive)));
                        values.add(new FitChartValue(300f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(210f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakInactive)));

                        TextView CurTV=(TextView) l2.getChildAt(0);
                        CurTV.setTextColor(Color.parseColor("#E5AB02"));
                        l3.getParent().requestChildFocus(l3,l3);
                    }
                    else if(doneTime >150  && doneTime <=210 )
                    {
                        currentRoundName.setText(R.string.inauguration);
                        values.add(new FitChartValue(150f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(doneTime-150,resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(60-(doneTime-150), resources.getColor(R.color.funBreakInactive)));
                        values.add(new FitChartValue(30f, resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakInactive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakInactive)));
                        values.add(new FitChartValue(240f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(30f, resources.getColor(R.color.eliminationInactive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.funBreakInactive)));
                        values.add(new FitChartValue(300f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(210f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakInactive)));

                        TextView CurTV=(TextView) l3.getChildAt(0);
                        CurTV.setTextColor(Color.parseColor("#E5AB02"));
                        l4.getParent().requestChildFocus(l4,l4);
                    }
                    else if(doneTime >210  && doneTime <=240 )
                    {
                        currentRoundName.setText(R.string.biometric);
                        values.add(new FitChartValue(150f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(doneTime-210,resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(30-(doneTime-210), resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakInactive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakInactive)));
                        values.add(new FitChartValue(240f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(30f, resources.getColor(R.color.eliminationInactive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.funBreakInactive)));
                        values.add(new FitChartValue(300f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(210f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakInactive)));

                        TextView CurTV=(TextView) l4.getChildAt(0);
                        CurTV.setTextColor(Color.parseColor("#E5AB02"));
                        l5.getParent().requestChildFocus(l5,l5);
                    }
                    else if(doneTime >240  && doneTime <=330 )
                    {
                        currentRoundName.setText(R.string.intro);
                        values.add(new FitChartValue(150f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(30f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(doneTime-240,resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(90-(doneTime-240), resources.getColor(R.color.funBreakInactive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakInactive)));
                        values.add(new FitChartValue(240f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(30f, resources.getColor(R.color.eliminationInactive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.funBreakInactive)));
                        values.add(new FitChartValue(300f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(210f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakInactive)));

                        TextView CurTV=(TextView) l5.getChildAt(0);
                        CurTV.setTextColor(Color.parseColor("#E5AB02"));
                        l6.getParent().requestChildFocus(l6,l6);
                    }
                    else if(doneTime >330  && doneTime <=390 )
                    {
                        currentRoundName.setText(R.string.roundOne);
                        values.add(new FitChartValue(150f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(30f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(doneTime-330,resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(60-(doneTime-330), resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakInactive)));
                        values.add(new FitChartValue(240f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(30f, resources.getColor(R.color.eliminationInactive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.funBreakInactive)));
                        values.add(new FitChartValue(300f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(210f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakInactive)));

                        TextView CurTV=(TextView) l6.getChildAt(0);
                        CurTV.setTextColor(Color.parseColor("#E5AB02"));
                        l7.getParent().requestChildFocus(l7,l7);
                    }
                    else if(doneTime >390  && doneTime <=480 )
                    {
                        currentRoundName.setText(R.string.pizza);
                        values.add(new FitChartValue(150f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(30f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(doneTime-390,resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(90-(doneTime-390), resources.getColor(R.color.funBreakInactive)));
                        values.add(new FitChartValue(240f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(30f, resources.getColor(R.color.eliminationInactive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.funBreakInactive)));
                        values.add(new FitChartValue(300f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(210f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakInactive)));

                        TextView CurTV=(TextView) l7.getChildAt(0);
                        CurTV.setTextColor(Color.parseColor("#E5AB02"));
                        l8.getParent().requestChildFocus(l8,l8);
                    }
                    else if(doneTime >480  && doneTime <=720 )
                    {
                        currentRoundName.setText(R.string.roundTwo);
                        values.add(new FitChartValue(150f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(30f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(doneTime-480,resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(240-(doneTime-480), resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(30f, resources.getColor(R.color.eliminationInactive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.funBreakInactive)));
                        values.add(new FitChartValue(300f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(210f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakInactive)));

                        TextView CurTV=(TextView) l8.getChildAt(0);
                        CurTV.setTextColor(Color.parseColor("#E5AB02"));
                        l9.getParent().requestChildFocus(l9,l9);
                    }
                    else if(doneTime >720  && doneTime <=750 )
                    {
                        currentRoundName.setText(R.string.result);
                        values.add(new FitChartValue(150f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(30f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(240f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(doneTime-720,resources.getColor(R.color.eliminationActive)));
                        values.add(new FitChartValue(30-(doneTime-720), resources.getColor(R.color.eliminationInactive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.funBreakInactive)));
                        values.add(new FitChartValue(300f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(210f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakInactive)));

                        TextView CurTV=(TextView) l9.getChildAt(0);
                        CurTV.setTextColor(Color.parseColor("#E5AB02"));
                        l10.getParent().requestChildFocus(l10,l10);
                    }
                    else if(doneTime >750  && doneTime <=930 )
                    {
                        currentRoundName.setText(R.string.breakfast);
                        values.add(new FitChartValue(150f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(30f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(240f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(30f, resources.getColor(R.color.eliminationActive)));
                        values.add(new FitChartValue(doneTime-750,resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(180-(doneTime-750), resources.getColor(R.color.funBreakInactive)));
                        values.add(new FitChartValue(300f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(210f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakInactive)));

                        TextView CurTV=(TextView) l10.getChildAt(0);
                        CurTV.setTextColor(Color.parseColor("#E5AB02"));
                        l11.getParent().requestChildFocus(l11,l11);
                    }
                    else if(doneTime >930  && doneTime <=1230 )
                    {
                        currentRoundName.setText(R.string.roundThree);
                        values.add(new FitChartValue(150f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(30f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(240f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(30f, resources.getColor(R.color.eliminationActive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(doneTime-930,resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(300-(doneTime-930), resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(210f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakInactive)));

                        TextView CurTV=(TextView) l11.getChildAt(0);
                        CurTV.setTextColor(Color.parseColor("#E5AB02"));
                        l12.getParent().requestChildFocus(l12,l12);
                    }
                    else if(doneTime >1230  && doneTime <=1290 )
                    {
                        currentRoundName.setText(R.string.lunch);
                        values.add(new FitChartValue(150f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(30f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(240f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(30f, resources.getColor(R.color.eliminationActive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(300f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(doneTime-1230,resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(60-(doneTime-1230), resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(210f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakInactive)));

                        TextView CurTV=(TextView) l12.getChildAt(0);
                        CurTV.setTextColor(Color.parseColor("#E5AB02"));
                        l13.getParent().requestChildFocus(l13,l13);
                    }
                    else if(doneTime >1290  && doneTime <=1500 )
                    {
                        currentRoundName.setText(R.string.roundFour);
                        values.add(new FitChartValue(150f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(30f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(240f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(30f, resources.getColor(R.color.eliminationActive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(300f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(doneTime-1290,resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(210-(doneTime-1290), resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakInactive)));

                        TextView CurTV=(TextView) l13.getChildAt(0);
                        CurTV.setTextColor(Color.parseColor("#E5AB02"));
                        l13.getParent().requestChildFocus(l13,l13);
                    }
                    else if(doneTime >1500  && doneTime <=1590 )
                    {
                        currentRoundName.setText(R.string.breakEnd);
                        values.add(new FitChartValue(150f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(30f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(240f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(30f, resources.getColor(R.color.eliminationActive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(300f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(210f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(doneTime-1500,resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(90-(doneTime-1500), resources.getColor(R.color.funBreakInactive)));
                    }
                    timelineChart.setValues(values);
                }

                @Override
                public void onFinish() {
                    //Event Over
                    values.add(new FitChartValue(150f, resources.getColor(R.color.foodActive)));
                    values.add(new FitChartValue(60f, resources.getColor(R.color.funBreakActive)));
                    values.add(new FitChartValue(30f, resources.getColor(R.color.foodActive)));
                    values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakActive)));
                    values.add(new FitChartValue(60f, resources.getColor(R.color.roundActive)));
                    values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakActive)));
                    values.add(new FitChartValue(240f, resources.getColor(R.color.roundActive)));
                    values.add(new FitChartValue(30f, resources.getColor(R.color.eliminationActive)));
                    values.add(new FitChartValue(180f, resources.getColor(R.color.funBreakActive)));
                    values.add(new FitChartValue(300f, resources.getColor(R.color.roundActive)));
                    values.add(new FitChartValue(60f, resources.getColor(R.color.foodActive)));
                    values.add(new FitChartValue(210f, resources.getColor(R.color.roundActive)));
                    values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakActive)));
                }
            }.start();
        }
        else
        {
            //Event Over
            values.add(new FitChartValue(150f, resources.getColor(R.color.foodActive)));
            values.add(new FitChartValue(60f, resources.getColor(R.color.funBreakActive)));
            values.add(new FitChartValue(30f, resources.getColor(R.color.foodActive)));
            values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakActive)));
            values.add(new FitChartValue(60f, resources.getColor(R.color.roundActive)));
            values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakActive)));
            values.add(new FitChartValue(240f, resources.getColor(R.color.roundActive)));
            values.add(new FitChartValue(30f, resources.getColor(R.color.eliminationActive)));
            values.add(new FitChartValue(180f, resources.getColor(R.color.funBreakActive)));
            values.add(new FitChartValue(300f, resources.getColor(R.color.roundActive)));
            values.add(new FitChartValue(60f, resources.getColor(R.color.foodActive)));
            values.add(new FitChartValue(210f, resources.getColor(R.color.roundActive)));
            values.add(new FitChartValue(90f, resources.getColor(R.color.funBreakActive)));

            currentRoundName.setText(R.string.eventEnd);

            timelineChart.setValues(values);
        }
    }
}
