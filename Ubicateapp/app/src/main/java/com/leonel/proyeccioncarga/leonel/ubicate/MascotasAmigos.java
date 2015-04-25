package com.leonel.proyeccioncarga.leonel.ubicate;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;


/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class MascotasAmigos extends Fragment {




    public MascotasAmigos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_mascotas_amigos, container, false);



        return v ;
    }





}
