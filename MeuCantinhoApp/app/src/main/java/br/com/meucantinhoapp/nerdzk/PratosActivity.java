package br.com.meucantinhoapp.nerdzk;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.meucantinhoapp.nerdzk.Controler.ComidaFragment;
import br.com.meucantinhoapp.nerdzk.Model.Bebida;
import br.com.meucantinhoapp.nerdzk.Model.Comida;
import br.com.meucantinhoapp.nerdzk.Model.Sobremesa;
import br.com.meucantinhoapp.nerdzk.Model.Tabela;

import static br.com.meucantinhoapp.nerdzk.Controler.ComidaFragment.listaComida;

public class PratosActivity extends AppCompatActivity {

    private SQLiteDatabase dataBase;


    private ArrayAdapter<String> comidasAdaptador;
    private ArrayList<Integer> ids;
    private ArrayList<String> itensComida;

    private Button botaoComida;
    private Button botaoBebida;
    private Button botaoSobremesa;

    private Comida comida = new Comida();
    private Bebida bebida = new Bebida();
    private Sobremesa sobremesa = new Sobremesa();

    private Tabela tabelaComida = new Tabela();
    private Tabela tabelaBebida = new Tabela();
    private Tabela tabelaSobremesa = new Tabela();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pratos);

        try{

            //Pegando o ID dos Botões
            botaoComida = findViewById(R.id.bt_comida_Id);
            botaoBebida = findViewById(R.id.bt_bebida_Id);
            botaoSobremesa = findViewById(R.id.bt_sobremesa_Id);


            //Banco de Dados
            dataBase = openOrCreateDatabase("CantinhoApp", MODE_PRIVATE, null);


            /* TABELAS */
            dataBase.execSQL("CREATE TABLE IF NOT EXISTS comidas(id INTEGER PRIMARY KEY AUTOINCREMENT,comida VARCHAR)");
            dataBase.execSQL("CREATE TABLE IF NOT EXISTS bebidas(id INTEGER PRIMARY KEY AUTOINCREMENT,bebida VARCHAR)");
            dataBase.execSQL("CREATE TABLE IF NOT EXISTS sobremesas(id INTEGER PRIMARY KEY AUTOINCREMENT,sobremesa VARCHAR)");

            /* Adicionando os itens nos cardápios */
            adicionarComida( comida.getComidas() );
            adicionarBebida( bebida.getBebidas() );
            adicionarSobremesa( sobremesa.getSobremesas() );

        }catch (Exception e){
            e.printStackTrace();
        }




        /* Pegando a ação de Click dos botões */
        botaoComida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tabelaComida.setNome("comidas");
                tabelaComida.setCampoPesquisa("comida");
                Log.i("LoL","nome: " + tabelaComida.getNome());
                recuperarItem(tabelaComida.getNome(), tabelaComida.getCampoPesquisa());
            }
        });

        botaoBebida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tabelaBebida.setNome("bebidas");
                tabelaBebida.setCampoPesquisa("bebida");
                recuperarItem(tabelaBebida.getNome(), tabelaBebida.getCampoPesquisa());
            }
        });

        botaoSobremesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tabelaSobremesa.setNome("sobremesas");
                tabelaSobremesa.setCampoPesquisa("sobremesa");
                recuperarItem(tabelaSobremesa.getNome(), tabelaSobremesa.getCampoPesquisa());
            }
        });

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


            comidasAdaptador = new ArrayAdapter<String>(getApplicationContext(),
                    android.R.layout.simple_expandable_list_item_2, android.R.id.text1, itensComida);

            ComidaFragment.listaComida.setAdapter(comidasAdaptador);

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


    private void adicionarComida( String item[]){
        try{
            for(int i = 0; i < 3; i ++ ){

                dataBase.execSQL("INSERT INTO comidas (comida) VALUES('" + item[i] + "')");
                ComidaFragment.listaComida.setAdapter(comidasAdaptador);

            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }


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


    private void adicionarSobremesa( String item[] ){
        try{

            for(int i = 0; i < 3; i++){

                dataBase.execSQL("INSERT INTO sobremesas (sobremesa) VALUES('" + item[i] + "')");

                ComidaFragment.listaComida.setAdapter(comidasAdaptador);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
