package com.vit.codevar.ui.NotficationFragment;

public class NotificationRVData
{
    String message;
    String time;
    String date;

    public NotificationRVData(String Date, String title,String Time)
    {
        message = title;
        time = Time;
        date=Date;
    }

    public NotificationRVData() {
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }
}
