package com.think360.schoolshare.fragment.members;


import android.databinding.BindingConversion;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by think360 on 08/03/17.
 */

public class BindingConvertions {

    @BindingConversion
    public static String convertDate(long date) {

        SimpleDateFormat toFormat = new SimpleDateFormat("dd MMM");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return toFormat.format(calendar.getTime());

    }

    @BindingConversion
    public static String convertDate(String date) {

        return date;

    }

    public String getTimeAgo(long time) {

        final int SECOND_MILLIS = 1000;
        final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
        final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
        final int DAY_MILLIS = 24 * HOUR_MILLIS;

        if (time < 1000000000000L) {
            // if timestamp given in seconds, convert to millis
            time *= 1000;
        }

        long now = System.currentTimeMillis();
        if (time > now || time <= 0) {
            return null;
        }

        // TODO: localize
        final long diff = now - time;
        if (diff < MINUTE_MILLIS) {
            return "just now";
        } else if (diff < 2 * MINUTE_MILLIS) {
            return "a minute ago";
        } else if (diff < 50 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + " minutes ago";
        } else if (diff < 90 * MINUTE_MILLIS) {
            return "an hour ago";
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS + " hours ago";
        } else if (diff < 48 * HOUR_MILLIS) {
            return "yesterday";
        } else {
            return diff / DAY_MILLIS + " days ago";
        }
    }
}
