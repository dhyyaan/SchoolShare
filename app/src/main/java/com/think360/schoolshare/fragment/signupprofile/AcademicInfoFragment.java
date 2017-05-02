package com.think360.schoolshare.fragment.signupprofile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.think360.schoolshare.R;
import com.think360.schoolshare.activity.SignUpActivity;
import com.think360.schoolshare.adapter.AddChapterAdapter;
import com.think360.schoolshare.fragment.dialog.SelectChaptersDailogFragment;
import com.think360.schoolshare.interfaces.OnDataBack;
import com.think360.schoolshare.interfaces.OnItemClickListener;
import com.think360.schoolshare.utils.RecyclerItemClickListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;



/**
 * Created by think360user on 9/1/17.
 */
public class AcademicInfoFragment extends Fragment {
    private List<String> years = new ArrayList<>();
    private  int thisYear = Calendar.getInstance().get(Calendar.YEAR);

    private RecyclerView recyclerView;
    private AddChapterAdapter adapter;
    private List<String> chapterList;
    public static List<String> chapterListIds = new ArrayList<>();
    private ImageView imageViewStep4;
    private View line3;
   // public static final JSONObject academicInfo = new JSONObject();

    private  FragmentManager fm ;
   // private String yeayOfJoinning,yearOfLeaving;
    private EditText addmitionNo,etHouse;
    private ArrayAdapter<String> batchOfAdapter,yOJAdapter,yOLAdapter;
    // newInstance constructor for creating fragment with arguments
    public static AcademicInfoFragment newInstance() {


        return new AcademicInfoFragment();
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for (int i = 1970; i <= thisYear; i++) {
            years.add(Integer.toString(i));
        }


        batchOfAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                years)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
            View v = super.getView(position, convertView, parent);
            if (position == 0) {
                ((TextView) v.findViewById(android.R.id.text1)).setText("None");
                ((TextView) v.findViewById(android.R.id.text1)).setHint("None");
            }
            return v;
        }
        };
        yOJAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,
                years);
        yOLAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,
                years);




    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.academic_info_fragment, container, false);
        chapterList = new ArrayList<>();

        imageViewStep4  = (ImageView)getActivity().findViewById(R.id.imageViewStep4);
        line3 = getActivity().findViewById(R.id.line3);
        addmitionNo = (EditText)view.findViewById(R.id.etAdmissionNo);
        etHouse = (EditText)view.findViewById(R.id.etHouse);

        final Spinner batchOfSpinner = (Spinner) view.findViewById(R.id.spinnerBatchOf);


        batchOfSpinner.setAdapter(batchOfAdapter);

        batchOfSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                PersonalInfoFragment. params.put("batch_of", batchOfSpinner.getSelectedItem().toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final Spinner yOJSpinner = (Spinner) view.findViewById(R.id.spinnerYearOfJoin);
        yOJSpinner.setAdapter(yOJAdapter);
        yOJSpinner.setSelection(yOJAdapter.getPosition(String.valueOf(Calendar.getInstance().get(Calendar.YEAR))));
        yOJSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                PersonalInfoFragment. params.put("year_of_joining", yOJSpinner.getSelectedItem().toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final Spinner yearOfLeavingSpinner = (Spinner) view.findViewById(R.id.spinnerYeayOfLeaving);
        yearOfLeavingSpinner.setAdapter(yOLAdapter);
        yearOfLeavingSpinner.setSelection(yOLAdapter.getPosition(String.valueOf(Calendar.getInstance().get(Calendar.YEAR))));
        yearOfLeavingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                PersonalInfoFragment. params.put("year_of_leaving", yearOfLeavingSpinner.getSelectedItem().toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        AppCompatButton buttonNext = (AppCompatButton) view.findViewById(R.id.btnNext);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if  ( addmitionNo.getText().length()>0 && !batchOfAdapter.getItem(1).equals("None")) {
                    imageViewStep4.setImageResource(R.mipmap.step_4_2);
                    line3.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.color1));
                    ((SignUpActivity) getContext()).setCurrentPosition(3);

                        PersonalInfoFragment. params.put("admission_no", addmitionNo.getText().toString());
                        PersonalInfoFragment. params.put("house", etHouse.getText().toString());


                       for (Map.Entry<String,String> entry : PersonalInfoFragment. params.entrySet()) {
                            String key = entry.getKey();
                            String value = entry.getValue();
                            // do stuff
                            Log.d(key,value);
                        }

                         // Log.e("academicInfo ",academicInfo.toString());

                }
                else {
                    Toast.makeText(getActivity(),getResources().getString(R.string.text_empty_admition),Toast.LENGTH_SHORT).show();
                }
            }


        });
        recyclerView = (RecyclerView)view. findViewById(R.id.rvaddChapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);


        adapter = new AddChapterAdapter(getActivity(),chapterList);

        recyclerView.setAdapter(adapter);
        ImageView ivSelectChap = (ImageView) view.findViewById(R.id.ivSelectChap);
        ivSelectChap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                fm = getActivity().getSupportFragmentManager();
                final LayoutInflater inflater = LayoutInflater.from(getActivity());

                SelectChaptersDailogFragment dFragment = new  SelectChaptersDailogFragment();
                dFragment.setCallBack(new OnDataBack() {
                    @Override
                    public void onSet(String id,String city) {

//for selected citys
                        if(chapterList.size()<=1){
                        chapterList.add(city);
                        Set<String> citySet = new LinkedHashSet<>(chapterList);
                        chapterList.clear();
                        chapterList.addAll(citySet);

                        adapter.notifyDataSetChanged();
//for selected item ids
                        chapterListIds.add(id);
                        Set<String> citySetIds = new LinkedHashSet<>(chapterListIds);
                        chapterListIds.clear();
                        chapterListIds.addAll(citySetIds);
                        }else{
                            Toast.makeText(getActivity(),getResources().getString(R.string.text_select_chap),Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                // Show DialogFragment
                dFragment.show(fm, "Dialog Fragment");
            }
        });




        // recyclerView.addItemDecoration(new GridSpacingItemDecoration(getApplicationContext(),2,5, true));
        //  recyclerView.setItemAnimator(new DefaultItemAnimator());
      recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(),new OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // TODO Handle item click

                        chapterList.remove(position);
                        adapter.notifyDataSetChanged();
                        chapterListIds.remove(position);
                    }
                })
        );


        return view;
    }



}