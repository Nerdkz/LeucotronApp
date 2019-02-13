package br.com.meucantinhoapp.nerdzk.Controler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.meucantinhoapp.nerdzk.R;

public class ListaAdapterItem extends ArrayAdapter<Item> {

    private Context context;
    private ArrayList<Item> lista;

    public ListaAdapterItem(Context context, ArrayList<Item> lista){
        super(context, 0, lista);
        this.context = context;
        this.lista = lista;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Item itemPosicao = this.lista.get(position);

        convertView = LayoutInflater.from(this.context).inflate(R.layout.item, null);

        //Imagem do meu Layout
        ImageView imageView = convertView.findViewById(R.id.ImageViewItemID);
        imageView.setImageResource(itemPosicao.getImagem());


        //textos do meu Layout
        TextView textoNome = convertView.findViewById(R.id.ItemNomeID);
        textoNome.setText(itemPosicao.getNome());

        TextView textoDescricao = convertView.findViewById(R.id.ItemDescricaoId);
        textoDescricao.setText(itemPosicao.getDescricao());

        TextView textoPreco = convertView.findViewById(R.id.ItemPrecoId);
        textoDescricao.setText(itemPosicao.getPreco());

        return convertView;
    }
}
