package br.com.meucantinhoapp.nerdzk.Controler;

public class Item {

    private int imagem;
    private String nome;
    private String descricao;
    private String preco;

    public Item( int imagem, String nome, String descricao, String preco){
        this.imagem = imagem;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }


    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }
}
