package com.fabio.models;

public class Insumo extends Elemento {
    private float preco;

    public Insumo(int _id, String _descricao, float _quantidade, String _uniMedida, float _preco) {
        super(_id, _descricao, _quantidade, _uniMedida);
        this.descricao = _descricao;
        this.preco = _preco;
    }

    @Override
    public float subTotal() {
//        System.out.println(String.format("%s, of %.2f", this.descricao, (float) (this.preco * this.quantidade)));
        return (float) (this.preco * this.quantidade);
    }

    @Override
    public String toString() {
        return String.format("Insumo %s %s\n", this.id, this.descricao);
    }
}
