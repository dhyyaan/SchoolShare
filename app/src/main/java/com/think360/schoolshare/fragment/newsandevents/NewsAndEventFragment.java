package com.think360.schoolshare.fragment.newsandevents;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.think360.schoolshare.BR;
import com.think360.schoolshare.MySingleton;
import com.think360.schoolshare.R;
import com.think360.schoolshare.api.get.GetNewsEventsDetails;
import com.think360.schoolshare.baseurl.BaseUrl;
import com.think360.schoolshare.interfaces.OnFragmentInteractionListener;
import com.think360.schoolshare.interfaces.ServerCallBackObj2;
import com.think360.schoolshare.surinder.utils.RootFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewsAndEventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsAndEventFragment extends RootFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    private RecyclerView mRecyclerView;

    private NewsAndEventsRecyclerViewAdapter newsAndEventsRecyclerViewAdapter;

    private StickyListHeadersAdapter adapter;
    private List<NewsAndEvents.Event> list;

    public NewsAndEventFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsAndEventFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsAndEventFragment newInstance(String param1, String param2) {
        NewsAndEventFragment fragment = new NewsAndEventFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_and_event_new, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, BaseUrl.GET_EVENTS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(response);

                    if (jsonObject.has("status") && jsonObject.optBoolean("status")) {

                        Gson gson = new Gson();
                        NewsAndEvents newsAndEvents = gson.fromJson(response, NewsAndEvents.class);

                        newsAndEventsRecyclerViewAdapter = new NewsAndEventsRecyclerViewAdapter( newsAndEvents.getEvents());
                        mRecyclerView.setAdapter(newsAndEventsRecyclerViewAdapter);
                        list =   newsAndEvents.getEvents();

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

    }



    private class NewsAndEventsRecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder> {


        ArrayList<NewsAndEvents.Event> newsAndEventsArrayList;

        NewsAndEventsRecyclerViewAdapter(List<NewsAndEvents.Event> newsAndEventsList) {
            this.newsAndEventsArrayList = (ArrayList<NewsAndEvents.Event>) newsAndEventsList;
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.single_item_news_and_events_layout_binding, parent, false);
            return new MyViewHolder(viewDataBinding);

        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {

            ViewDataBinding viewDataBinding = holder.getViewDataBinding();
            holder.getViewDataBinding().getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

               new GetNewsEventsDetails(getActivity(), list.get(position).getId(), new ServerCallBackObj2() {
                        @Override
                        public void onSuccess(JSONObject jsonObj) {

                            transactFragment(R.id.fragContainer, EventsFragment.newInstance(jsonObj, ""));
                        }

                    }).addQueue();
                }
            });
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


    class MyAdapter extends BaseAdapter implements StickyListHeadersAdapter {


        private ArrayList<NewsAndEvents> newsAndEventsArrayList;
        private String[] countries;
        private LayoutInflater inflater;

        public MyAdapter(Context context, List<NewsAndEvents> newsAndEventsList) {
            inflater = LayoutInflater.from(context);
            this.newsAndEventsArrayList = (ArrayList<NewsAndEvents>) newsAndEventsList;
            countries = context.getResources().getStringArray(R.array.countries);
        }

        @Override
        public int getCount() {
            return newsAndEventsArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return newsAndEventsArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.single_item_news_and_events_layout, parent, false);
                holder.text = (TextView) convertView.findViewById(R.id.text);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.text.setText(countries[position]);

            return convertView;
        }

        @Override
        public View getHeaderView(int position, View convertView, ViewGroup parent) {
            HeaderViewHolder holder;
            if (convertView == null) {
                holder = new HeaderViewHolder();
                convertView = inflater.inflate(R.layout.header, parent, false);
                holder.text = (TextView) convertView.findViewById(R.id.text1);
                convertView.setTag(holder);
            } else {
                holder = (HeaderViewHolder) convertView.getTag();
            }
            //set header text as first char in name
            String headerText = "" + countries[position].subSequence(0, 1).charAt(0);
            holder.text.setText(headerText);
            return convertView;
        }

        @Override
        public long getHeaderId(int position) {
            //return the first character of the country as ID because this is what headers are based upon
            // return countries[position].subSequence(0, 1).charAt(0);
            return 0;
        }


        class HeaderViewHolder {
            TextView text;
        }

        class ViewHolder {
            TextView text;
        }

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}
