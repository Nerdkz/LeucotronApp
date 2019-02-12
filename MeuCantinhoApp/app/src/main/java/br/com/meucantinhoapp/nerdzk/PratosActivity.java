package br.com.meucantinhoapp.nerdzk;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PratosActivity extends AppCompatActivity {

    private SQLiteDatabase dataBase;
    private ListView listaComidas;

    private ArrayAdapter<String> comidasAdaptador;
    private ArrayList<Integer> ids;
    private ArrayList<String> itensComida;


    private String comidaNome = "Coca-Cola      Refrigerante     R$ 6,00";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pratos);

        try{

            listaComidas = findViewById(R.id.listaComidaId);

            //Banco de Dados

            dataBase = openOrCreateDatabase("CantinhoApp", MODE_PRIVATE, null);

            dataBase.execSQL("CREATE TABLE IF NOT EXISTS alimentos(id INTEGER PRIMARY KEY AUTOINCREMENT,comida VARCHAR)");
            //dataBase.execSQL("CREATE TABLE IF NOT EXISTS alimentos(id INTEGER PRIMARY KEY AUTOINCREMENT,comida VARCHAR)");

            adicionarComida(comidaNome);
            recuperarComidas();


        }catch (Exception e){
            e.printStackTrace();
        }

    }


    private void recuperarComidas(){
        try {
            //Recuperar as comidas




            Cursor cursor = dataBase.rawQuery("SELECT * FROM alimentos", null);


            if(cursor.getCount() > 0) {

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
            int indiceColunaComida = cursor.getColumnIndex("comida");


            //Criando Adaptadores
            ids = new ArrayList<Integer>();
            itensComida = new ArrayList<String>();


            comidasAdaptador = new ArrayAdapter<String>(getApplicationContext(),
                    android.R.layout.simple_expandable_list_item_2, android.R.id.text1, itensComida);

            listaComidas.setAdapter(comidasAdaptador);

            //Listar as Comidas
            boolean position = cursor.moveToFirst();

            while (position) {


                itensComida.add(cursor.getString(indiceColunaComida));
                ids.add(Integer.parseInt(cursor.getString(indiceColunaId)));

                cursor.moveToNext();

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    private void adicionarComida( String texto ){
        try{

            dataBase.execSQL("INSERT INTO alimentos (comida) VALUES('VInicius')");
            listaComidas.setAdapter(comidasAdaptador);

        }
        catch (Exception e){
            e.printStackTrace();
        }
        recuperarComidas();
    }

}
