package com.fabio.models;

public abstract class Elemento {
    protected int id;
    protected String descricao;
    protected float quantidade;
    private String uniMedida;

    public Elemento(int _id, String _descricao, float _quantidade, String _uniMedida) {
        this.id = _id;
        this.descricao = _descricao;
        this.quantidade = _quantidade;
        this.uniMedida = _uniMedida;
    }

    public abstract float subTotal();

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("%s %s\n", this.id, this.descricao);
    }
}
