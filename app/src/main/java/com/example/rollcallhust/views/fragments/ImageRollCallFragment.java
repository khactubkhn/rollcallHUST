package com.example.rollcallhust.views.fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.example.rollcallhust.R;
import com.example.rollcallhust.adapters.ImageGridViewAdapter;
import com.example.rollcallhust.networks.ApiClient;
import com.example.rollcallhust.views.activities.RollCallDetailActivity;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImageRollCallFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageRollCallFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    GridView gvImageRollCall;
    ImageGridViewAdapter imageGridViewAdapter;


    public ImageRollCallFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ImageRollCallFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ImageRollCallFragment newInstance(String param1, String param2) {
        ImageRollCallFragment fragment = new ImageRollCallFragment();
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
        View v = inflater.inflate(R.layout.fragment_image_roll_call, container, false);
        gvImageRollCall = v.findViewById(R.id.gvImageRollCall);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initGridView();
    }

    public void initGridView(){
        ArrayList<Bitmap> data = new ArrayList<>();
        Call<ResponseBody> call = ApiClient.getService().fetchCaptcha("https://res.cloudinary.com/defpietpy/image/upload/v1554219293/o4ezrewt2plumdqjimda.png");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        // display the image data in a ImageView or save it
                        Bitmap bmp = BitmapFactory.decodeStream(response.body().byteStream());
                        data.add(bmp);
                        data.add(bmp);
                        data.add(bmp);
                        data.add(bmp);
                        data.add(bmp);
                        data.add(bmp);
                        data.add(bmp);
                        data.add(bmp);
                        data.add(bmp);
                        data.add(bmp);
                        data.add(bmp);
                        data.add(bmp);
                        data.add(bmp);
                        data.add(bmp);
                        data.add(bmp);
                        data.add(bmp);
                        data.add(bmp);
                        data.add(bmp);
                        data.add(bmp);
                        data.add(bmp);
                        data.add(bmp);
                        imageGridViewAdapter = new ImageGridViewAdapter(getContext(), R.layout.image_roll_call_item, data);
                        gvImageRollCall.setAdapter(imageGridViewAdapter);
                    } else {
                        // TODO
                    }
                } else {
                    // TODO
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // TODO
            }
        });
    }

}
