package br.com.meucantinhoapp.nerdzk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.meucantinhoapp.nerdzk.Controler.Item;
import br.com.meucantinhoapp.nerdzk.Controler.ListaAdapterItem;

public class ListaItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_item);

        ArrayList<Item> listaItens = new ArrayList<Item>();

        Item a = new Item(R.drawable.comida_1, "Item A", "Comida Exemplo A", "R$10,00");
        Item b = new Item(R.drawable.ic_launcher_background, "Item B", "Comida Exemplo B", "R$10,00");
        Item c = new Item(R.drawable.ic_launcher_foreground, "Item C", "Comida Exemplo C", "R$10,00");
        Item d = new Item(R.drawable.comida_1, "Item D", "Comida Exemplo D", "R$10,00");

        listaItens.add(a);
        listaItens.add(b);
        listaItens.add(c);
        listaItens.add(d);

        ListaAdapterItem adapterItem = new ListaAdapterItem(this, listaItens);
       // ListView listView = findViewById(R.id.);

    }
}
