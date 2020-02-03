package com.example.futapp.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.futapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class EstadisticaPartido1Fragment extends Fragment {


    public EstadisticaPartido1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_estadistica_partido1, container, false);
    }

}
