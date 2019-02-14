package br.com.meucantinhoapp.nerdzk;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.meucantinhoapp.nerdzk.Controler.BebidaFragment;
import br.com.meucantinhoapp.nerdzk.Controler.ComidaFragment;
import br.com.meucantinhoapp.nerdzk.Controler.SobremesaFragment;
import br.com.meucantinhoapp.nerdzk.Model.Bebida;
import br.com.meucantinhoapp.nerdzk.Model.Comida;
import br.com.meucantinhoapp.nerdzk.Model.Sobremesa;
import br.com.meucantinhoapp.nerdzk.Model.Tabela;


public class PratosActivity extends AppCompatActivity {

    public static SQLiteDatabase dataBase;


    private Button botaoComida;
    private Button botaoBebida;
    private Button botaoSobremesa;
    private EditText pesquisaID;
    Fragment fragmentAtual;
    int tipo = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pratos);



        try{

            //Pegando o ID dos Botões
            botaoComida = findViewById(R.id.bt_comida_Id);
            botaoBebida = findViewById(R.id.bt_bebida_Id);
            botaoSobremesa = findViewById(R.id.bt_sobremesa_Id);
            pesquisaID = findViewById(R.id.pesquisaIdText);


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

                tipo = 0;
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                try {
                    if (fragmentAtual!=null)
                    fragmentTransaction.remove(fragmentAtual);
                } catch (Exception e) {
                    Log.i("Viny","Foda-se");
                }

                ComidaFragment comidaFragment = new ComidaFragment();
                fragmentAtual = comidaFragment;

                fragmentTransaction.add(R.id.rl_container_fragmento, comidaFragment);


                fragmentTransaction.commit();
            }
        });

        botaoBebida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tipo = 1;

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                try {
                    if (fragmentAtual!=null)
                    fragmentTransaction.remove(fragmentAtual);
                } catch (Exception e) {
                    Log.i("Viny","Foda-se");
                }

                BebidaFragment bebidaFragment = new BebidaFragment();
                fragmentAtual = bebidaFragment;

                fragmentTransaction.add(R.id.rl_container_fragmento, bebidaFragment);


                fragmentTransaction.commit();
            }
        });

        botaoSobremesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tipo = 2;



                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                try {
                    if (fragmentAtual!=null)
                    fragmentTransaction.remove(fragmentAtual);
                } catch (Exception e) {
                    Log.i("Viny","Foda-se");
                }

                SobremesaFragment sobremesaFragment = new SobremesaFragment();
                fragmentAtual = sobremesaFragment;


                fragmentTransaction.add(R.id.rl_container_fragmento, sobremesaFragment);


                fragmentTransaction.commit();

            }
        });

        pesquisaID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    Log.i("Viny","onTextChanged");

                    if (tipo==0) {
                        ((ComidaFragment)fragmentAtual).onChangeText(charSequence);
                    } else  if (tipo==1) {
                        ((BebidaFragment)fragmentAtual).onChangeText(charSequence);
                    } else {
                        ((SobremesaFragment)fragmentAtual).onChangeText(charSequence);
                    }



                } catch (Exception e) {
                    Log.i("Viny","Bugou");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }


    private void pesquisar(String item ){

    }

}
