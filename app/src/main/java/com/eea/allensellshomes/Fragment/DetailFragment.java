package com.eea.allensellshomes.Fragment;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eea.allensellshomes.R;

import java.util.ArrayList;
import java.util.List;


public class DetailFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public DetailFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_detail, container, false);
//        List<SlideModel> slideModels=new ArrayList<>();
//        slideModels.add(new SlideModel("https://picsum.photos/id/896/300/200","Image 1"));
//        slideModels.add(new SlideModel("https://picsum.photos/id/894/300/200","Image 2"));
//        slideModels.add(new SlideModel("https://picsum.photos/id/892/300/200","Image 3"));
//        slideModels.add(new SlideModel("https://picsum.photos/id/891/300/200","Image 4"));
//
//
//
//        ImageSlider imageSlider = view.findViewById(R.id.imageView7);
//        imageSlider.setImageList(slideModels,true);
//        imageSlider.startSliding(3000) ;// with new period
//        imageSlider.stopSliding();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }




    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}