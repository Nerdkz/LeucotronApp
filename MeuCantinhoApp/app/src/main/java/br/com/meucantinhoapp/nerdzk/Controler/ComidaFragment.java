package br.com.meucantinhoapp.nerdzk.Controler;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import br.com.meucantinhoapp.nerdzk.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ComidaFragment extends Fragment {


    public static ListView listaComida;


    public ComidaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewComida = inflater.inflate(R.layout.fragment_comida, container, false);

        listaComida = viewComida.findViewById(R.id.comidaListId);


        return viewComida;
    }

}
