package br.com.meucantinhoapp.nerdzk.Controler;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.meucantinhoapp.nerdzk.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SobremesaFragment extends Fragment {


    public SobremesaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sobremesa, container, false);
    }

}