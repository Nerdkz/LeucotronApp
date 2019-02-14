package br.com.meucantinhoapp.nerdzk;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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


public class PratosActivity extends AppCompatActivity {

    public static SQLiteDatabase dataBase;


    private Button botaoComida;
    private Button botaoBebida;
    private Button botaoSobremesa;


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


        }catch (Exception e){
            e.printStackTrace();
        }




        /* Pegando a ação de Click dos botões */
        botaoComida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                ComidaFragment comidaFragment = new ComidaFragment();

                fragmentTransaction.add(R.id.rl_container_fragmento, comidaFragment);


                fragmentTransaction.commit();
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

    }

    private void pesquisar( String item ){

    }

}
