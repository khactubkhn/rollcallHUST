package com.example.rollcallhust.views.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rollcallhust.R;
import com.example.rollcallhust.models.ClassDetailResponse;
import com.example.rollcallhust.utils.DateTimeUtils;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClasInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClasInfoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView tvClassNameDetail;
    TextView tvClassCodeDetail;
    TextView tvTermClassDetail;
    TextView tvSizeClassDetail;
    TextView tvFromClassDetail;
    TextView tvToClassDetail;

    ClassDetailResponse classDetailResponse;

    public ClasInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClasInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClasInfoFragment newInstance(String param1, String param2) {
        ClasInfoFragment fragment = new ClasInfoFragment();
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
        View v = inflater.inflate(R.layout.fragment_clas_info, container, false);
        tvClassNameDetail = v.findViewById(R.id.tvClassNameDetail);
        tvClassCodeDetail = v.findViewById(R.id.tvClassCodeDetail);
        tvTermClassDetail = v.findViewById(R.id.tvTermClassDetail);
        tvSizeClassDetail = v.findViewById(R.id.tvSizeClassDetail);
        tvFromClassDetail = v.findViewById(R.id.tvFromClassDetail);
        tvToClassDetail = v.findViewById(R.id.tvToClassDetail);
        return v;
    }

    public void onLoadDate(ClassDetailResponse classDetailResponse){
        this.classDetailResponse = classDetailResponse;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvClassNameDetail.setText(classDetailResponse.getClassName());
        tvClassCodeDetail.setText(classDetailResponse.getClassCode());
        tvTermClassDetail.setText(classDetailResponse.getTerm());
        tvSizeClassDetail.setText(classDetailResponse.getSize() + "");
        tvFromClassDetail.setText(DateTimeUtils.formatDate(classDetailResponse.getFrom()));
        tvToClassDetail.setText(DateTimeUtils.formatDate(classDetailResponse.getTo()));
    }
}
