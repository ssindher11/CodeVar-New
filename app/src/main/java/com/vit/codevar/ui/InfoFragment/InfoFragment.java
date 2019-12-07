package com.vit.codevar.ui.InfoFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vit.codevar.MainActivity;
import com.vit.codevar.R;

public class InfoFragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_info,container,false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        MainActivity.fragmentTitle.setText(R.string.informationTitle);

        String htmlText="<div><span>CodeVar is a national level Code-A-Thon which is planned to be conducted in three phases as below</span><ul><li>Phase 1: Online Screening</li><li>Phase 1: Online Screening 2</li><li>Phase 2: Code-A-Thon at Vellore Institute of Technology</li></ul><p><b>Phase 1 - Online Screening</b></p><div> This is the first event to encourage programmers towards open coding wherein you can solve any question from a list of 1000+ questions with difficulties ranging from easy to advance. This will give equal chances to all the levels of programmers. Top 100 participants in the leaderboard will be invited to Vellore Institute of Technology, Vellore to participate in the code-a-thon which is scheduled to be conducted on 7th - 8th of December, 2019. </div><p><b>Phase 1 - Online Screening 2</b></p><div> This is the first event to encourage programmers towards open coding wherein you can solve any question from a list of 1000+ questions with difficulties ranging from easy to advance. This will give equal chances to all the levels of programmers. Top 140 participants in the leaderboard will be invited to Vellore Institute of Technology, Vellore to participate in the code-a-thon which is scheduled to be conducted on 7th - 8th of December, 2019.<br><p><code>Points to note:</code></p><ul><li><code>Screening 2 starts at 10AM IST on 30-11-2019 and will end at 4PM on 01-12-2019. </code></li></ul><ul><li><code>If you are in top 100 you are not allowed to participate in this round, unless explicitly told by our team.       </code></li></ul><ul><li><code>Results will be out on 01-12-2019, an email will be sent to you if you are in top 140.        </code></li></ul><ul><li><code>If we are unable to get 240 participants by the end of screening round 2, 100 participants will be called after 240 and so on till we are able to get 240 participants for the final round.        </code></li></ul><ul><li><code>It is advised that you don't miss any examinations, we will not be taking any responsibility for the same. </code></li></ul><ul><li><code>We will not be covering your travel expenses, so please don't request for the same. </code></li></ul><ul><li><code>The event will be providing you internship opportunities so bring all possible documents with you that you think is important, again we are not responsible for any of your belongings. </code></li></ul><ul><li><code>Screening 2 is same as Screening 1.</code></li></ul></div><p><b>Phase 2 - CodeVar - Code-A-Thon</b></p><div> Top 240 participants in phase 1 will be invited to Vellore Institute of Technology, Vellore to participate in a 24 hour Code-A-Thon on 7th and 8th of December 2019. The Code-A-Thon will test your basics in programming and your expertise in data structures and algorithms. Questions will range from MCQ's to Company specific problems. </div></div>";
        TextView AboutTv=view.findViewById(R.id.AboutUs);
        AboutTv.setText(HtmlCompat.fromHtml(htmlText, 0));
    }
}
