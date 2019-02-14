package br.com.meucantinhoapp.nerdzk.Controler;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import br.com.meucantinhoapp.nerdzk.Model.Bebida;
import br.com.meucantinhoapp.nerdzk.Model.Tabela;
import br.com.meucantinhoapp.nerdzk.R;

import static br.com.meucantinhoapp.nerdzk.PratosActivity.dataBase;

/**
 * A simple {@link Fragment} subclass.
 */
public class BebidaFragment extends Fragment {

    private Bebida bebida = new Bebida();
    private Tabela tabelaBebida = new Tabela();

    private ArrayAdapter<String> comidasAdaptador;
    private ArrayList<Integer> ids;
    private ArrayList<String> itensComida;

    public BebidaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        /* Adicionando os itens nos cardápios */
        //adicionarBebida( bebida.getBebidas() );

        tabelaBebida.setNome("bebidas");
        tabelaBebida.setCampoPesquisa("bebida");
        //recuperarItem(tabelaBebida.getNome(), tabelaBebida.getCampoPesquisa());

        return inflater.inflate(R.layout.fragment_bebida, container, false);
    }

    /*
    private void adicionarBebida( String item[] ){
        try{

            for(int i = 0; i < 3; i ++ ){

                dataBase.execSQL("INSERT INTO bebidas (bebida) VALUES('" + item[i] + "')");

                ComidaFragment.listaComida.setAdapter(comidasAdaptador);

            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    private void recuperarItem(String tabela, String atributo){
        try {


            //Recuperar os itens
            Cursor cursor = dataBase.rawQuery("SELECT * FROM " + tabela + " ORDER BY " + atributo + " ASC", null);

            //Cursor cursor = dataBase.rawQuery("delete FROM alimentos", null);

            if(cursor.getCount() > 0) {
                Log.i("LoL","aeeeeeeeeeeeee");
                cursor.moveToFirst();

                do {
                    String nome = cursor.getString(1);

                    Log.i("LoL",nome);
                }while(cursor.moveToNext());

            }
            else
            {
                Log.i("LoL","TABELA VAZIA");
            }




            //recuperar os ids das colunas
            int indiceColunaId = cursor.getColumnIndex("id");
            int indiceColunaAtributo = cursor.getColumnIndex( atributo );


            //Criando Adaptadores
            ids = new ArrayList<Integer>();
            itensComida = new ArrayList<String>();


            comidasAdaptador = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_expandable_list_item_2, android.R.id.text1, itensComida);

            listaComida.setAdapter(comidasAdaptador);

            //Listar as Comidas
            boolean position = cursor.moveToFirst();

            while (position) {

                itensComida.add(cursor.getString(indiceColunaAtributo));
                ids.add(Integer.parseInt(cursor.getString(indiceColunaId)));

                cursor.moveToNext();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    */

}
