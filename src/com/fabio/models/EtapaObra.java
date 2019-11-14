package com.fabio.models;

import java.util.List;

public class EtapaObra {
    private String descricao;
    private List<Composicao> composicoes;

    public double total() {
        float _total = 0;
        for (Composicao composicao : composicoes) {
            _total += composicao.subTotal();
        }
        System.out.println(String.format("%s, of %.2f", this.descricao, _total));
        return _total;
    }
}
