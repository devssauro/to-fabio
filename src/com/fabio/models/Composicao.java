package com.fabio.models;

import java.util.List;

public class Composicao extends Elemento {
    private List<Elemento> elementos;

    public Composicao(int _id, String _descricao, float _quantidade, String _uniMedida, List<Elemento> _elementos) {
        super(_id, _descricao, _quantidade, _uniMedida);
        this.elementos = _elementos;
    }

    public void addElemento(Elemento elemento) {
        elementos.add(elemento);
    }

    @Override
    public float subTotal() {
        float _total = 0;
        for (Elemento elemento: this.elementos) {
            _total += elemento.subTotal();
        }
        System.out.println(String.format("%s, of %.2f", this.descricao, _total));
        return (float) (_total * this.quantidade);
    }

    @Override
    public String toString() {
        return String.format("Composição %s %s\n", this.id, this.descricao);
    }
}
