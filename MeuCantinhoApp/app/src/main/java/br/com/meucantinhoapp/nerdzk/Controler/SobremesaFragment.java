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
import br.com.meucantinhoapp.nerdzk.Model.Sobremesa;
import br.com.meucantinhoapp.nerdzk.Model.Tabela;
import br.com.meucantinhoapp.nerdzk.R;

import static br.com.meucantinhoapp.nerdzk.PratosActivity.dataBase;




/**
 * A simple {@link Fragment} subclass.
 */
public class SobremesaFragment extends Fragment {


    private ListView listaSobremesa;

    private Sobremesa sobremesa = new Sobremesa();

    private Tabela tabelaSobremesa = new Tabela();


    private ArrayAdapter<String> sobremesasAdaptador;
    private ArrayList<Integer> ids;
    private ArrayList<String> itensSobremesa;



    public SobremesaFragment() {
        // Required empty public constructor
    }


    public void onChangeText(CharSequence s) {
        Log.i("Viny","Me chamou onChangeText");
        sobremesasAdaptador.getFilter().filter(s);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewComida = inflater.inflate(R.layout.fragment_comida, container, false);

        listaSobremesa = viewComida.findViewById(R.id.comidaListId);


        tabelaSobremesa.setNome("sobremesas");
        tabelaSobremesa.setCampoPesquisa("sobremesa");

        Cursor cursor = dataBase.rawQuery("SELECT * FROM " + tabelaSobremesa.getNome() + " ORDER BY "
                + tabelaSobremesa.getCampoPesquisa() + " ASC", null);


        /* Adicionando os itens nos cardápios */
        if(cursor.getCount() <= 0) {
            adicionarSobremesa( sobremesa.getSobremesas() );
        }

         else{
           //dataBase.execSQL("DROP TABLE sobremesas");
        }









        //Log.i("LoL","nome: " + tabelaComida.getNome());
        recuperarItem(tabelaSobremesa.getNome(), tabelaSobremesa.getCampoPesquisa());



        return viewComida;
    }




    private void adicionarSobremesa( String item[]){
        try{
            for(int i = 0; i < 3; i ++ ){

                dataBase.execSQL("INSERT INTO sobremesas (sobremesa) VALUES('" + item[i] + "')");
                listaSobremesa.setAdapter(sobremesasAdaptador);

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
            itensSobremesa = new ArrayList<String>();


            sobremesasAdaptador = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_expandable_list_item_2, android.R.id.text1, itensSobremesa);

            listaSobremesa.setAdapter(sobremesasAdaptador);

            //Listar as Comidas
            boolean position = cursor.moveToFirst();

            while (position) {

                itensSobremesa.add(cursor.getString(indiceColunaAtributo));
                ids.add(Integer.parseInt(cursor.getString(indiceColunaId)));

                cursor.moveToNext();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
