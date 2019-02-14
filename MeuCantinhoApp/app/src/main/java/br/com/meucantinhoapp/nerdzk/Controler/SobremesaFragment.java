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

import br.com.meucantinhoapp.nerdzk.Model.Sobremesa;
import br.com.meucantinhoapp.nerdzk.Model.Tabela;
import br.com.meucantinhoapp.nerdzk.R;

import static br.com.meucantinhoapp.nerdzk.PratosActivity.dataBase;

/**
 * A simple {@link Fragment} subclass.
 */
public class SobremesaFragment extends Fragment {

    private Sobremesa sobremesa = new Sobremesa();

    private Tabela tabelaSobremesa = new Tabela();

    private ArrayAdapter<String> sobremesaAdaptador;

    private ArrayList<Integer> ids;

    private ArrayList<String> itensComida;



    public SobremesaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        /* Adicionando os itens nos cardápios */
        adicionarSobremesa( sobremesa.getSobremesas() );

        tabelaSobremesa.setNome("sobremesas");
        tabelaSobremesa.setCampoPesquisa("sobremesa");
        //recuperarItem(tabelaSobremesa.getNome(), tabelaSobremesa.getCampoPesquisa());

        return inflater.inflate(R.layout.fragment_sobremesa, container, false);
    }


    private void adicionarSobremesa( String item[] ){
        try{

            for(int i = 0; i < 3; i++){

                dataBase.execSQL("INSERT INTO sobremesas (sobremesa) VALUES('" + item[i] + "')");

                //listaComida.setAdapter(sobremesaAdaptador);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    /*
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


            sobremesaAdaptador = new ArrayAdapter<String>(getContext(),
                    android.R.layout.simple_expandable_list_item_2, android.R.id.text1, itensComida);

            sobremesaAdaptador.setAdapter(sobremesaAdaptador);

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
