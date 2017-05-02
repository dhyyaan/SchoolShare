package com.think360.schoolshare.fragment.members;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.think360.schoolshare.BR;
import com.think360.schoolshare.R;
import com.think360.schoolshare.adapter.RecyclerBindingAdapter;
import com.think360.schoolshare.interfaces.OnFragmentInteractionListener;
import com.think360.schoolshare.surinder.utils.RootFragment;
import com.think360.schoolshare.surinder.xrecyclerview.XRecyclerView;

import java.util.AbstractList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MembersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MembersFragment extends RootFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MembersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MembersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MembersFragment newInstance(String param1, String param2) {
        MembersFragment fragment = new MembersFragment();
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
        return inflater.inflate(R.layout.fragment_members, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final XRecyclerView rv_Members = (XRecyclerView) view.findViewById(R.id.rv_Members);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rv_Members.setLayoutManager(mLayoutManager);
        rv_Members.setItemAnimator(new DefaultItemAnimator());


/*        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://think360.co/prabhat/alumni/alumni_api/index.php/12345/get-member?chapter_id=3", new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(response);

                    if (jsonObject.has("status") && jsonObject.optBoolean("status")) {

                        Gson gson = new Gson();
                        Members members = gson.fromJson(response, Members.class);
                        MembersAdapter membersAdapter = new MembersAdapter(members.getData());
                        rv_Members.setAdapter(membersAdapter);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        MySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);*/


        GetMembers membersAPI = getRetroObject().create(GetMembers.class);


        Call<List<Members>> call = membersAPI.getMembersByGroupId("2");
        call.enqueue(new Callback<List<Members>>() {
            @Override
            public void onResponse(Call<List<Members>> call, Response<List<Members>> response) {

                if (response.isSuccessful()) {

                    RecyclerBindingAdapter<Members> recyclerBindingAdapter = new RecyclerBindingAdapter<Members>(R.layout.single_item_member_binding, BR.data, (AbstractList) response.body());
                    rv_Members.setAdapter(recyclerBindingAdapter);

                } else System.out.print(response.errorBody());
            }

            @Override
            public void onFailure(Call<List<Members>> call, Throwable t) {
                t.printStackTrace();
            }
        });
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


    public class MembersAdapter extends RecyclerView.Adapter<MemberViewHolder> {

        private List<Members> membersModelArrayList;

        public MembersAdapter(List<Members> membersModelArrayList) {
            this.membersModelArrayList = membersModelArrayList;
        }

        @Override
        public MemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.single_item_member_binding, parent, false);


            return new MemberViewHolder(viewDataBinding);


        }

        @Override
        public void onBindViewHolder(MemberViewHolder holder, int position) {

            ViewDataBinding viewDataBinding = holder.getViewDataBinding();
            viewDataBinding.setVariable(BR.data, membersModelArrayList.get(position));

            viewDataBinding.executePendingBindings();
        }


        @Override
        public int getItemCount() {
            return membersModelArrayList.size();
        }


    }

    public class MemberViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding mViewDataBinding;

        public MemberViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            mViewDataBinding = viewDataBinding;


        }

        private ViewDataBinding getViewDataBinding() {
            return mViewDataBinding;
        }
    }
}
