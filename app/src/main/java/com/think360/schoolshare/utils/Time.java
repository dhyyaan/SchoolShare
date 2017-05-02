package com.think360.schoolshare.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by think360 on 02/03/17.
 */

public class Time {

    //1 minute = 60 seconds
    //1 hour = 60 x 60 = 3600
    //1 day = 3600 x 24 = 86400
    public static String printDateDifference(String date) {
        Date endDate = null,startDate=null;


      try {

            SimpleDateFormat simpleDateFormat =
                    new SimpleDateFormat("yyyy-M-dd hh:mm:ss");
            startDate = simpleDateFormat.parse(date);

            Calendar c = Calendar.getInstance();
            String formattedDate = simpleDateFormat.format(c.getTime());
            endDate = simpleDateFormat.parse(formattedDate);



        } catch (ParseException e) {
            e.printStackTrace();
        }





        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

       // System.out.println("startDate : " + startDate);
       // System.out.println("endDate : " + endDate);
       // System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        // System.out.printf(
        //        "%d days, %d hours, %d minutes, %d seconds%n",
        //      elapsedDays,
        //     elapsedHours, elapsedMinutes, elapsedSeconds);
       return elapsedDays+" days";

    }
}
