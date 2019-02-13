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

public class PratosActivity extends AppCompatActivity {

    private SQLiteDatabase dataBase;
    private ListView listaComidas;

    private ArrayAdapter<String> comidasAdaptador;
    private ArrayList<Integer> ids;
    private ArrayList<String> itensComida;

    private Button botaoComida;
    private Button botaoBebida;
    private Button botaoSobremesa;


    private String comidas [] = {"strogonoff de frangom . frango , alho , mostarda , ketchup , maionese , cebola . R$ 27,00",
            "Feijoada . feijão preto , bacon , costela , carne seca , carne defumada . RS 30,00",
            "Beirute . pão sírio , maionese , bife de carne bolvina , ovo , tomate , alface . R$ 32,00"};

    private String bebidas[] = {"Coca-Cola . R$ 7,00", "Pepsi . R$ 6,00", "Fanta . R$ 5,50"};

    private String sobremesas [] = {"Pudim . leite condensado , leite , ovos , açúcar . R$ 3,00",
            "Brigadeiro . leite condensado , margarina , chocolate em pó , chocolate granulado . R$ 2,50",
            "Muusse de Limão . leite condensado , creme de leite , suco puro de limão , bis de limão . R$ 4,00"};






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pratos);

        try{

            //Pegando o ID dos Botões
            botaoComida = findViewById(R.id.bt_comida_Id);
            botaoBebida = findViewById(R.id.bt_bebida_Id);
            botaoSobremesa = findViewById(R.id.bt_sobremesa_Id);

            //Pegando a ação de Click dos botões
            botaoComida.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {



                }
            });

            botaoBebida.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {



                }
            });

            botaoSobremesa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {



                }
            });


            //Banco de Dados

            dataBase = openOrCreateDatabase("CantinhoApp", MODE_PRIVATE, null);

            /* TABELAS */

            dataBase.execSQL("CREATE TABLE IF NOT EXISTS alimentos(id INTEGER PRIMARY KEY AUTOINCREMENT,comida VARCHAR)");
            dataBase.execSQL("CREATE TABLE IF NOT EXISTS bebidas(id INTEGER PRIMARY KEY AUTOINCREMENT,bebida VARCHAR)");
            dataBase.execSQL("CREATE TABLE IF NOT EXISTS sobremesas(id INTEGER PRIMARY KEY AUTOINCREMENT,sobremesa VARCHAR)");



            adicionarComida( comidas );
            recuperarComidas();

            adicionarBebida( bebidas );


        }catch (Exception e){
            e.printStackTrace();
        }

    }


    private void recuperarComidas(){
        try {
            //Recuperar as comidas
            Cursor cursor = dataBase.rawQuery("SELECT * FROM alimentos ORDER BY comida ASC", null);

            //Cursor cursor = dataBase.rawQuery("delete FROM alimentos", null);

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

                itensComida.add(cursor.getString(indiceColunaComida));
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

                dataBase.execSQL("INSERT INTO alimentos (comida, ingrediente, preco) VALUES('" + item[i] + "')");
                listaComidas.setAdapter(comidasAdaptador);

            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        recuperarComidas();
    }


    private void adicionarBebida( String item[] ){
        try{

            for(int i = 0; i < 3; i ++ ){

                dataBase.execSQL("INSERT INTO bebidas (bebida, ingrediente, preco) VALUES('" + item[i] + "')");

                listaComidas.setAdapter(comidasAdaptador);

            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        recuperarComidas();
    }


    private void adicionarSobremesa( String item[], String ingrediente[], String preco[] ){
        try{

            for(int i = 0; i < 3; i++){

                dataBase.execSQL("INSERT INTO sobremesas (sobremesa, ingrediente, preco) " +
                        "VALUES('" + item[i] + ',' +ingrediente[i] + ','+ preco[i] + "')");

                listaComidas.setAdapter(comidasAdaptador);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        recuperarComidas();
    }
}
