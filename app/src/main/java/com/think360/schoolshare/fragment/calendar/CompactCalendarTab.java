package com.think360.schoolshare.fragment.calendar;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.gson.Gson;
import com.think360.schoolshare.BR;
import com.think360.schoolshare.MySingleton;
import com.think360.schoolshare.R;
import com.think360.schoolshare.api.get.GetNewsEventsDetails;
import com.think360.schoolshare.baseurl.BaseUrl;
import com.think360.schoolshare.fragment.newsandevents.EventsFragment;
import com.think360.schoolshare.fragment.newsandevents.NewsAndEvents;
import com.think360.schoolshare.interfaces.ServerCallBackObj2;
import com.think360.schoolshare.surinder.utils.RootFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CompactCalendarTab extends RootFragment {


    @ColorInt
    public static final int APPCOLOR = 0xFFFF5252;
    List<NewsAndEvents.Event> newsAndEventsList = new ArrayList<>();
    private TextView tv_month;
    private NewsAndEventsRecyclerViewAdapter newsAndEventsRecyclerViewAdapter;
    private static final String TAG = "MainActivity";
    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    private SimpleDateFormat dateFormatForDisplaying = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a", Locale.getDefault());
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());
    private boolean shouldShow = true;
    private CompactCalendarView compactCalendarView;

    private List<NewsAndEvents.Event> list;

    public static CompactCalendarTab newInstance(String param1, String param2) {
        CompactCalendarTab fragment = new CompactCalendarTab();
        // Bundle args = new Bundle();
        //  args.putString(ARG_PARAM1, param1);
        ////  args.putString(ARG_PARAM2, param2);
        //   fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_tab, container, false);


        final RecyclerView bookingsRecyclerView = (RecyclerView) v.findViewById(R.id.bookings_listview);
        final ImageView showPreviousMonthBut = (ImageView) v.findViewById(R.id.prev_button);
        final ImageView showNextMonthBut = (ImageView) v.findViewById(R.id.next_button);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        bookingsRecyclerView.setLayoutManager(layoutManager);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, BaseUrl.GET_EVENTS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(response);

                    if (jsonObject.has("status") && jsonObject.optBoolean("status")) {

                        Gson gson = new Gson();
                        NewsAndEvents newsAndEvents = gson.fromJson(response, NewsAndEvents.class);

                        newsAndEventsList = newsAndEvents.getEvents();
                        newsAndEventsRecyclerViewAdapter = new NewsAndEventsRecyclerViewAdapter(newsAndEventsList);
                        bookingsRecyclerView.setAdapter(newsAndEventsRecyclerViewAdapter);
                        // Add event 1 on Sun, 07 Jun 2015 18:20:51 GMT
                        for (NewsAndEvents.Event event : newsAndEvents.getEvents()) {
                            Date date;
                            if (event.getStartDate() != null) {
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                try {
                                    date = simpleDateFormat.parse(event.getStartDate());
                                    compactCalendarView.addEvent(new Event(APPCOLOR, date.getTime(), event));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        compactCalendarView.invalidate();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        MySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);


        //   final ArrayAdapter adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, mutableBookings);
        //bookingsRecyclerView.setAdapter(adapter);
        compactCalendarView = (CompactCalendarView) v.findViewById(R.id.compactcalendar_view);


        // below allows you to configure color for the current day in the month
        // compactCalendarView.setCurrentDayBackgroundColor(getResources().getColor(R.color.black));
        // below allows you to configure colors for the current day the user has selected
        // compactCalendarView.setCurrentSelectedDayBackgroundColor(getResources().getColor(R.color.dark_red));
        compactCalendarView.setUseThreeLetterAbbreviation(true);
        compactCalendarView.setFirstDayOfWeek(Calendar.MONDAY);

        //loadEventsForYear(2017);
        compactCalendarView.invalidate();


        // below line will display Sunday as the first day of the week
        // compactCalendarView.setShouldShowMondayAsFirstDay(false);

        // disable scrolling calendar
        // compactCalendarView.shouldScrollMonth(false);

        // show days from other months as greyed out days
        // compactCalendarView.displayOtherMonthDays(true);

        // show Sunday as first day of month
        // compactCalendarView.setShouldShowMondayAsFirstDay(false);

        //set initial title

        tv_month = (TextView) v.findViewById(R.id.tv_month);
        tv_month.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));

        //set title on calendar scroll
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {


                tv_month.setText(dateFormatForMonth.format(dateClicked));
                List<Event> bookingsFromMap = compactCalendarView.getEvents(dateClicked);
                Log.d(TAG, "inside onclick " + dateFormatForDisplaying.format(dateClicked));
                if (bookingsFromMap != null) {
                    Log.d(TAG, bookingsFromMap.toString());
                    newsAndEventsList.clear();
                    for (Event booking : bookingsFromMap) {
                        newsAndEventsList.add((NewsAndEvents.Event) booking.getData());
                    }
                    newsAndEventsRecyclerViewAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                tv_month.setText(dateFormatForMonth.format(firstDayOfNewMonth));
            }
        });

        showPreviousMonthBut.setOnClickListener(v12 -> compactCalendarView.showPreviousMonth());

        showNextMonthBut.setOnClickListener(v1 -> compactCalendarView.showNextMonth());


        compactCalendarView.setAnimationListener(new CompactCalendarView.CompactCalendarAnimationListener() {
            @Override
            public void onOpened() {
            }

            @Override
            public void onClosed() {
            }
        });


        // uncomment below to show indicators above small indicator events
        // compactCalendarView.shouldDrawIndicatorsBelowSelectedDays(true);

        // uncomment below to open onCreate
        //openCalendarOnCreate(v);
        if (!compactCalendarView.isAnimating()) {
            if (shouldShow) {
                compactCalendarView.showCalendar();
                compactCalendarView.showCalendarWithAnimation();
            } else {
                compactCalendarView.hideCalendar();
                compactCalendarView.hideCalendarWithAnimation();
            }
            shouldShow = !shouldShow;
        }
        return v;
    }


    @Override
    public void onResume() {
        super.onResume();
        tv_month.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));
        // Set to current day on resume to set calendar to latest day
        // toolbar.setTitle(dateFormatForMonth.format(new Date()));
    }


    private class NewsAndEventsRecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder> {


        ArrayList<NewsAndEvents.Event> newsAndEventsArrayList;

        NewsAndEventsRecyclerViewAdapter(List<NewsAndEvents.Event> newsAndEventsList) {
            this.newsAndEventsArrayList = (ArrayList<NewsAndEvents.Event>) newsAndEventsList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.single_item_news_and_events_layout_calendar_binding, parent, false);
            return new MyViewHolder(viewDataBinding);

        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {

            ViewDataBinding viewDataBinding = holder.getViewDataBinding();
            holder.getViewDataBinding().getRoot().setOnClickListener(v -> new GetNewsEventsDetails(getActivity(), newsAndEventsArrayList.get(position).getId(), new ServerCallBackObj2() {
                @Override
                public void onSuccess(JSONObject jsonObj) {
                    transactFragment(R.id.fragContainer, EventsFragment.newInstance(jsonObj, ""));
                }

            }).addQueue());
            viewDataBinding.setVariable(BR.event, newsAndEventsArrayList.get(position));

        }

        @Override
        public int getItemCount() {
            return newsAndEventsArrayList.size();
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding mViewDataBinding;

        public MyViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            this.mViewDataBinding = viewDataBinding;
            mViewDataBinding.executePendingBindings();
        }

        public ViewDataBinding getViewDataBinding() {
            return mViewDataBinding;
        }


    }
}