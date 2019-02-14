package br.com.meucantinhoapp.nerdzk.Controler;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.meucantinhoapp.nerdzk.Model.Comida;
import br.com.meucantinhoapp.nerdzk.Model.Tabela;
import br.com.meucantinhoapp.nerdzk.R;

import static br.com.meucantinhoapp.nerdzk.PratosActivity.dataBase;




/**
 * A simple {@link Fragment} subclass.
 */
public class ComidaFragment extends Fragment {


    private ListView listaComida;

    private Comida comida = new Comida();

    private Tabela tabelaComida = new Tabela();


    private ArrayAdapter<String> comidasAdaptador;
    private ArrayList<Integer> ids;
    private ArrayList<String> itensComida;



    public ComidaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewComida = inflater.inflate(R.layout.fragment_comida, container, false);

        listaComida = viewComida.findViewById(R.id.comidaListId);

        tabelaComida.setNome("comidas");
        tabelaComida.setCampoPesquisa("comida");

        Cursor cursor = dataBase.rawQuery("SELECT * FROM " + tabelaComida.getNome() + " ORDER BY "
                + tabelaComida.getCampoPesquisa() + " ASC", null);


        /* Adicionando os itens nos cardápios */
        if(cursor.getCount() <= 0) {
            adicionarComida( comida.getComidas() );
        }
        /*
        else{
           dataBase.execSQL("DROP TABLE comidas");
        }
        */



        //Log.i("LoL","nome: " + tabelaComida.getNome());
        recuperarItem(tabelaComida.getNome(), tabelaComida.getCampoPesquisa());

        return viewComida;
    }


    private void adicionarComida( String item[]){
        try{
            for(int i = 0; i < 3; i ++ ){

                dataBase.execSQL("INSERT INTO comidas (comida) VALUES('" + item[i] + "')");
                listaComida.setAdapter(comidasAdaptador);

            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    private void recuperarItem(String tabela, String atributo){
        try {


            //Recuperar os itens
            Cursor cursor = dataBase.rawQuery("SELECT  DISTINCT* FROM " + tabela + " ORDER BY " + atributo + " ASC", null);

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
}
