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

import br.com.meucantinhoapp.nerdzk.Model.Bebida;
import br.com.meucantinhoapp.nerdzk.Model.Comida;
import br.com.meucantinhoapp.nerdzk.Model.Tabela;
import br.com.meucantinhoapp.nerdzk.R;

import static br.com.meucantinhoapp.nerdzk.PratosActivity.dataBase;




/**
 * A simple {@link Fragment} subclass.
 */
public class BebidaFragment extends Fragment {


    private ListView listaBebida;

    private Bebida bebida = new Bebida();

    private Tabela tabelaBebida = new Tabela();


    private ArrayAdapter<String> bebidasAdaptador;
    private ArrayList<Integer> ids;
    private ArrayList<String> itensBebida;



    public BebidaFragment() {
        // Required empty public constructor
    }


    public void onChangeText(CharSequence s) {
        Log.i("Viny","Me chamou onChangeText");
        bebidasAdaptador.getFilter().filter(s);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewBebida = inflater.inflate(R.layout.fragment_bebida, container, false);

        listaBebida = viewBebida.findViewById(R.id.bebidaListId);


        tabelaBebida.setNome("bebidas");
        tabelaBebida.setCampoPesquisa("bebida");

        Cursor cursor = dataBase.rawQuery("SELECT * FROM " + tabelaBebida.getNome() + " ORDER BY "
                + tabelaBebida.getCampoPesquisa() + " ASC", null);


        /* Adicionando os itens nos cardápios */
        if(cursor.getCount() <= 0) {
            adicionarBebida( bebida.getBebidas() );
        }

        /*
        else{
           dataBase.execSQL("DROP TABLE bebidas");
        }
        */




        //Log.i("LoL","nome: " + tabelaComida.getNome());
        recuperarItem(tabelaBebida.getNome(), tabelaBebida.getCampoPesquisa());



        return viewBebida;
    }




    private void adicionarBebida( String item[]){
        try{
            for(int i = 0; i < 3; i ++ ){

                dataBase.execSQL("INSERT INTO Bebidas (bebida) VALUES('" + item[i] + "')");
                listaBebida.setAdapter(bebidasAdaptador);

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
            itensBebida = new ArrayList<String>();


            bebidasAdaptador = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_expandable_list_item_2, android.R.id.text1, itensBebida);

            listaBebida.setAdapter(bebidasAdaptador);

            //Listar as Comidas
            boolean position = cursor.moveToFirst();

            while (position) {

                itensBebida.add(cursor.getString(indiceColunaAtributo));
                ids.add(Integer.parseInt(cursor.getString(indiceColunaId)));

                cursor.moveToNext();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
