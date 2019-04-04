package com.example.rollcallhust.views.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rollcallhust.R;
import com.example.rollcallhust.adapters.RVAdapter;
import com.example.rollcallhust.adapters.RVClassDetailAdapter;
import com.example.rollcallhust.models.ClassDetailResponse;
import com.example.rollcallhust.models.RollCall;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListRollCallFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListRollCallFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView rvRollCall;
    RVClassDetailAdapter rvClassDetailAdapter;

    List<RollCall> rollCallList= null;


    public ListRollCallFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListRollCallFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListRollCallFragment newInstance(String param1, String param2) {
        ListRollCallFragment fragment = new ListRollCallFragment();
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
        View v = inflater.inflate(R.layout.fragment_list_roll_call, container, false);
        rvRollCall = v.findViewById(R.id.rvRollCall);
        return v;
    }

    public void onLoadRollCall(List<RollCall> rollCallList){
        this.rollCallList = rollCallList;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvClassDetailAdapter = new RVClassDetailAdapter(rollCallList, getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvRollCall.setLayoutManager(layoutManager);
        rvRollCall.setAdapter(rvClassDetailAdapter);
    }
}
